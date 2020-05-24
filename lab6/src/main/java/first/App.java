package first;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import first.client.ClientActor;
import first.server.ServerActor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {


    public static void main(String[] args) throws IOException {

        final ActorSystem system = ActorSystem.create("local_system");
        ActorRef client = system.actorOf(Props.create(ClientActor.class), "client");
        final ActorRef server = system.actorOf(Props.create(ServerActor.class), "server");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        for (int i = 0; i < 5; ++i) {
//            client = system.actorOf(Props.create(ClientActor.class));
//            client.tell("x", null);
//        }
        while (true) {
            System.out.println("Product name:");
            String line = br.readLine();
            if (line.equals("q")) {
                break;
            }
            client.tell("Request:" + line, null);     // send message to actor
        }
        system.eventStream().setLogLevel(Logging.WarningLevel());

        system.terminate();
    }
}
