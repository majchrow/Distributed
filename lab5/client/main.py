import argparse
import os
import subprocess

from kazoo.client import KazooClient


def create_parser():
    parser = argparse.ArgumentParser()
    parser.add_argument(
        '--program',
        help='Model type to train on',
        type=str,
        default="gnome-calculator")
    return parser


class Zookeeper(object):
    HOSTS = [
        '127.0.0.1:2181'
    ]

    def __enter__(self):
        return self

    def __exit__(self, type, value, traceback):
        self._kill_subprocess()
        self.zk.stop()

    def __init__(self, app):
        self.app = app.strip().split()
        self.subprocess = None
        self.descendants = set()
        self.zk = KazooClient(hosts=self.HOSTS)
        self.zk.start()
        self.zk.ensure_path("/")
        self.lock = self.zk.Lock("/lock")
        self._set_z_watcher()

    def _start_subprocess(self):
        if not self.subprocess:
            self.subprocess = subprocess.Popen(args=self.app)

    def _kill_subprocess(self):
        if self.subprocess:
            self.subprocess.kill()
            self.subprocess = None

    def _set_children_watchers(self, path):
        if path not in self.descendants:
            self.descendants.add(path)
            if self.zk.exists(path):
                children = self.zk.get_children(path)
                for child in children:
                    new_path = os.path.join(path, child)
                    self._set_children_watchers(new_path)
            self._create_child_watcher(path)

    def _create_child_watcher(self, path):
        @self.zk.ChildrenWatch(path, send_event=True)
        def child_watcher(children, event):
            if event:
                path = event.path
                with self.lock:
                    diff_list = list({os.path.join(path, child) for child in children} - self.descendants)
                if len(diff_list) > 0:
                    for child in diff_list:
                        self._set_children_watchers(child)
                        with self.lock:
                            print("Current descendants:", len(self.descendants) - 1)
                else:
                    self._clean(path, children)

    def _clean(self, path, children):
        with self.lock:
            old_paths = {x for x in self.descendants if x.startswith(path) if x != path}
            new_paths = {os.path.join(path, child) for child in children}
            new_paths_extended = {descendant for descendant in self.descendants if
                                  any(descendant.startswith(new_path) for new_path in new_paths)}
            paths = old_paths - new_paths_extended

            for old_path in paths:
                self.descendants.remove(old_path)

    def _set_z_watcher(self):
        @self.zk.DataWatch('/z')
        def data_watcher(data, stat, event):
            if stat:
                self._start_subprocess()
                self._set_children_watchers("/z")
            else:
                self._kill_subprocess()
                self.descendants = set()

    def _print_tree(self):
        def _print_recursive(indent, node):
            print("│  " * indent, "├─", node, sep="")oc
            for child in self.zk.get_children(node):
                path = os.path.join(node, child)
                if self.zk.exists(path):
                    _print_recursive(indent + 1, path)

        if not self.zk.exists("/z"):
            print("No node /z for printing tree")
        else:
            print("/z")
            for child in self.zk.get_children("z"):
                _print_recursive(0, os.path.join("/z", child))

    def _print_usage(self):
        print("q - quit")
        print("t - print tree")

    def handle(self):
        while True:

            self._print_usage()
            x = input('Command: ')
            if x == "q":
                break
            elif x == "t":
                self._print_tree()
            else:
                print("Unknown command")


if __name__ == '__main__':
    app = create_parser().parse_args().program
    with Zookeeper(app) as client:
        client.handle()
