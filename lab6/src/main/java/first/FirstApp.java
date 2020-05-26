package first;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import first.client.ClientActor;
import first.server.ServerActor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class FirstApp {


    public static void main(String[] args) throws IOException {

        File configFile = new File("first.conf");
        Config config = ConfigFactory.parseFile(configFile);
        final ActorSystem system = ActorSystem.create("local_system", config);
        ActorRef client = system.actorOf(Props.create(ClientActor.class), "client");
        final ActorRef server = system.actorOf(Props.create(ServerActor.class), "server");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 200; ++i) {
            client = system.actorOf(Props.create(ClientActor.class));
            client.tell("name_" + i, null);
        }
        while (true) {
            System.out.println("Product name:");
            String line = br.readLine();
            if (line.equals("q")) {
                break;
            }
            client.tell(line, null);     // send message to actor
        }
        system.eventStream().setLogLevel(Logging.WarningLevel());

        system.terminate();
    }
}
