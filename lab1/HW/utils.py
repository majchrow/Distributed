import logging
import os
import time


def enable_logger(dir="logs", filename="logfile", extension=".log", level=logging.INFO):
    logdir = os.path.join(os.getcwd(), dir)
    if not os.path.exists(logdir):
        os.makedirs(logdir)
    timestamp = str(time.time()).split('.')[0]
    filename = filename + str(timestamp) + extension
    logfile = os.path.join(logdir, filename)
    logging.basicConfig(
        level=level,
        format="%(asctime)s [%(levelname)s] %(message)s",
        handlers=[
            logging.FileHandler(logfile),
            logging.StreamHandler()
        ]
    )
