import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Z2_Producer {

    public static void main(String[] argv) throws Exception {

        // info
        System.out.println("Z2 PRODUCER");

        // connection & channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // get routing key
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter routing key: ");
        String routingKey = br.readLine();

        // exchange
//        String EXCHANGE_NAME = "exchange1";
//        String EXCHANGE_NAME = "exchange2";
        String EXCHANGE_NAME = "exchange3";
//        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
//        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        while (true) {

            // read msg

            System.out.println("Enter message: ");
            String message = br.readLine();

            // break condition
            if ("exit".equals(message)) {
                break;
            }

            // publish
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
            System.out.println("Sent: " + message);
        }
    }
}
