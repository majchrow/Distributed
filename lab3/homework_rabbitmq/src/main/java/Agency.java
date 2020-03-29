import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Agency {

    public static void main(String[] argv) throws Exception {

        // info
        System.out.println("Agency starting");

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        // get agency name
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter agency name: ");
        String agencyName = br.readLine();

        handleAdmin(channel);
        handleTransport(agencyName, channel);

        while (true) {
            printUsage();
            String message = br.readLine();
            if ("exit".equals(message)) {
                break;
            } else if (isValid(message)) {
                Service service = Service.getById(Integer.parseInt(message));
                sendMessage(service, agencyName, channel);
            }
        }
    }

    private static void handleAdmin(Channel channel) throws IOException {
        channel.exchangeDeclare(Utils.ADMIN_EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String adminAgencyQueueName = Utils.ADMIN_AGENCY_QUEUE + Utils.getTimestamp();
        channel.queueDeclare(adminAgencyQueueName, false, false, false, null);
        channel.queueBind(adminAgencyQueueName, Utils.ADMIN_EXCHANGE_NAME, Utils.ADMIN_ALL_ROUTING_KEY);
        channel.queueBind(adminAgencyQueueName, Utils.ADMIN_EXCHANGE_NAME, Utils.ADMIN_AGENCY_ROUTING_KEY);
        System.out.println("Created queue " + adminAgencyQueueName +
                " with routing keys: " + Utils.ADMIN_ALL_ROUTING_KEY + ", " + Utils.ADMIN_AGENCY_ROUTING_KEY);
        channel.basicConsume(adminAgencyQueueName, false, createAdminMessageConsumer(channel));
    }

    private static void handleTransport(String agencyName, Channel channel) throws IOException {
        channel.exchangeDeclare(Utils.TRANSPORT_EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String transportQueueName = agencyName + Utils.getTimestamp();
        channel.queueDeclare(transportQueueName, false, false, false, null);
        channel.queueBind(transportQueueName, Utils.TRANSPORT_EXCHANGE_NAME, agencyName);
        System.out.println("Created queue " + transportQueueName +
                " with routing keys: " + agencyName);
        channel.basicConsume(transportQueueName, false, createTransportConsumer(channel));
    }

    public static Consumer createAdminMessageConsumer(Channel channel) {
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("Received from admin: " + message);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
    }

    public static Consumer createTransportConsumer(Channel channel) {
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("Received from transport: " + message);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
    }

    private static void printUsage() {
        System.out.println("Resources to send (you can choose only one): ");
        System.out.println("1 - passenger transport");
        System.out.println("2 - items transport");
        System.out.println("3 - satellites transport");
    }

    private static boolean isValid(String resource) {
        return resource.equals("1") || resource.equals("2") || resource.equals("3");
    }

    private static void sendMessage(Service service, String agencyName, Channel channel) throws IOException {
        String message = "Agency: " + agencyName + ", task: " + Utils.getTimestamp();
        switch (service) {
            case PASSENGER:
                channel.basicPublish(Utils.AGENCY_EXCHANGE_NAME,
                        Utils.PASSENGER_ROUTING_KEY,
                        null,
                        message.getBytes(StandardCharsets.UTF_8));
                break;
            case ITEM:
                channel.basicPublish(Utils.AGENCY_EXCHANGE_NAME,
                        Utils.ITEM_ROUTING_KEY,
                        null,
                        message.getBytes(StandardCharsets.UTF_8));
                break;
            case SATELLITE:
                channel.basicPublish(Utils.AGENCY_EXCHANGE_NAME,
                        Utils.SATELLITE_ROUTING_KEY,
                        null,
                        message.getBytes(StandardCharsets.UTF_8));
                break;
        }
        System.out.println("Sent: " + message);
    }


}
