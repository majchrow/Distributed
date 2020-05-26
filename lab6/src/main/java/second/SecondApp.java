package second;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import second.client.ClientActor;
import second.server.ServerActor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class SecondApp {


    public static Connection connection = null;
    public static Statement statement = null;

    static {
        try {
            // create a database connection
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:app.db");
            statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS queries");
            statement.executeUpdate("CREATE TABLE queries (id INTEGER, name STRING UNIQUE, count INTEGER)");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection with app.db failed");
            System.exit(1);
        }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        File configFile = new File("second.conf");
        Config config = ConfigFactory.parseFile(configFile);
        final ActorSystem system = ActorSystem.create("local_system", config);
        ActorRef client = system.actorOf(Props.create(ClientActor.class), "client");
        final ActorRef server = system.actorOf(Props.create(ServerActor.class), "server");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 50; ++i) {
            client = system.actorOf(Props.create(ClientActor.class));
            client.tell("x", null);
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

        if (connection != null) {
            connection.close();
        }
    }
}
