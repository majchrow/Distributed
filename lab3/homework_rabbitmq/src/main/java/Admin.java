import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Admin {

    public static void main(String[] argv) throws Exception {

        // info
        System.out.println("Admin starting");

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();


        String adminQueueName = Utils.ADMIN_QUEUE + Utils.getTimestamp(); // To allow all the admins getting the messages
        addHandler(channel, Utils.ADMIN_EXCHANGE_NAME, adminQueueName);
        addHandler(channel, Utils.TRANSPORT_EXCHANGE_NAME, adminQueueName);
        addHandler(channel, Utils.AGENCY_EXCHANGE_NAME, adminQueueName);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            printUsage();
            String message = br.readLine();
            if ("exit".equals(message)) {
                break;
            } else if (isValid(message)) {
                System.out.println("Message to send:");
                String msg = br.readLine();
                if ("back".equals(msg)) {
                    break;
                }
                switch (Integer.parseInt(message)) {
                    case 1:
                        channel.basicPublish(Utils.ADMIN_EXCHANGE_NAME,
                                Utils.ADMIN_TRANSPORT_ROUTING_KEY,
                                null,
                                msg.getBytes(StandardCharsets.UTF_8));
                        break;
                    case 2:
                        channel.basicPublish(Utils.ADMIN_EXCHANGE_NAME,
                                Utils.ADMIN_AGENCY_ROUTING_KEY,
                                null,
                                msg.getBytes(StandardCharsets.UTF_8));
                        break;
                    case 3:
                        channel.basicPublish(Utils.ADMIN_EXCHANGE_NAME,
                                Utils.ADMIN_ALL_ROUTING_KEY,
                                null,
                                msg.getBytes(StandardCharsets.UTF_8));
                        break;
                }
                System.out.println("Sent: " + msg);
            }
        }
    }

    private static void addHandler(Channel channel, String Exchange, String adminQueueName) throws IOException {
        channel.exchangeDeclare(Exchange, BuiltinExchangeType.TOPIC);
        channel.queueDeclare(adminQueueName, false, false, false, null);
        channel.queueBind(adminQueueName, Exchange, Utils.ADMIN_ROUTING_KEY);
        System.out.println("Exchange " + Exchange + ", Queue " + adminQueueName +
                "handler with routing keys: " + Utils.ADMIN_ROUTING_KEY);
        channel.basicConsume(adminQueueName, false, createAdminMessageConsumer(channel));
    }

    public static Consumer createAdminMessageConsumer(Channel channel) {
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("Received message: " + message);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
    }

    private static boolean isValid(String resource) {
        return resource.equals("1") || resource.equals("2") || resource.equals("3");
    }

    private static void printUsage() {
        System.out.println("Resources to send (you can choose only one): ");
        System.out.println("1 - transport");
        System.out.println("2 - agency");
        System.out.println("3 - all");
    }

}
