import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Stream;

public class Transport {


    public static void main(String[] argv) throws Exception {

        System.out.println("Transport starting");

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        Service[] resources = getResources();
        channel.basicQos(1, false); // Per consumer limit
        channel.basicQos(5, true); // Per channel limit
        handleAdmin(channel);
        handleResources(resources, channel);
        channel.exchangeDeclare(Utils.TRANSPORT_EXCHANGE_NAME, BuiltinExchangeType.TOPIC);


    }

    public static Service[] getResources() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            printUsage();
            String[] resources = Arrays.stream(br.readLine().split(",")).map(String::trim).toArray(String[]::new);
            if (isValid(resources)) {
                return Stream.of(resources).map(Integer::valueOf).map(Service::getById).toArray(Service[]::new);
            } else {
                printFailure();
            }
        }

    }

    public static void printUsage() {
        System.out.println("Resources to use (separated by comma): ");
        System.out.println("1 - passenger transport");
        System.out.println("2 - items transport");
        System.out.println("3 - satellites transport");
    }

    public static boolean isValid(String[] resources) {
        String[] properResource = {"1", "2", "3"};
        return resources.length == 2 && Arrays.asList(properResource).containsAll(Arrays.asList(resources));
    }

    public static void printFailure() {
        System.out.println("You must choose exactly 2 resources: ");
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

    public static Consumer createPassengerConsumer(Channel channel) {
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("Received from passenger: " + message);
                parseAndSendMessage(message, channel);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
    }

    public static Consumer createItemConsumer(Channel channel) {
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("Received from item: " + message);
                parseAndSendMessage(message, channel);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
    }

    public static Consumer createSatellitesConsumer(Channel channel) {
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("Received from satellite: " + message);
                parseAndSendMessage(message, channel);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
    }

    private static void handleAdmin(Channel channel) throws IOException {
        channel.exchangeDeclare(Utils.ADMIN_EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String adminTransportQueueName = Utils.ADMIN_TRANSPORT_QUEUE + Utils.getTimestamp();
        channel.queueDeclare(adminTransportQueueName, false, false, false, null);
        channel.queueBind(adminTransportQueueName, Utils.ADMIN_EXCHANGE_NAME, Utils.ADMIN_ALL_ROUTING_KEY);
        channel.queueBind(adminTransportQueueName, Utils.ADMIN_EXCHANGE_NAME, Utils.ADMIN_TRANSPORT_ROUTING_KEY);
        System.out.println("Created queue " + adminTransportQueueName +
                " with routing keys: " + Utils.ADMIN_ALL_ROUTING_KEY + ", " + Utils.ADMIN_TRANSPORT_ROUTING_KEY);
        channel.basicConsume(adminTransportQueueName, false, createAdminMessageConsumer(channel));
    }

    private static void handleResources(Service[] resources, Channel channel) throws IOException {
        channel.exchangeDeclare(Utils.AGENCY_EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        for (Service resource : resources) {
            switch (resource) {
                case PASSENGER:
                    String passengerQueueName = Utils.PASSENGER_SHARED_QUEUE;
                    channel.queueDeclare(passengerQueueName, false, false, false, null);
                    channel.queueBind(passengerQueueName, Utils.AGENCY_EXCHANGE_NAME, Utils.PASSENGER_ROUTING_KEY);
                    System.out.println("Created queue " + passengerQueueName +
                            " with routing keys: " + Utils.PASSENGER_ROUTING_KEY);
                    channel.basicConsume(passengerQueueName, false, createPassengerConsumer(channel));
                    break;
                case ITEM:
                    String itemQueueName = Utils.ITEM_SHARED_QUEUE;
                    channel.queueDeclare(itemQueueName, false, false, false, null);
                    channel.queueBind(itemQueueName, Utils.AGENCY_EXCHANGE_NAME, Utils.ITEM_ROUTING_KEY);
                    System.out.println("Created queue " + itemQueueName +
                            " with routing keys: " + Utils.ITEM_ROUTING_KEY);
                    channel.basicConsume(itemQueueName, false, createItemConsumer(channel));
                    break;
                case SATELLITE:
                    String satelliteQueueName = Utils.SATELLITE_SHARED_QUEUE;
                    channel.queueDeclare(satelliteQueueName, false, false, false, null);
                    channel.queueBind(satelliteQueueName, Utils.AGENCY_EXCHANGE_NAME, Utils.SATELLITE_ROUTING_KEY);
                    System.out.println("Created queue " + satelliteQueueName +
                            " with routing keys: " + Utils.SATELLITE_ROUTING_KEY);
                    channel.basicConsume(satelliteQueueName, false, createSatellitesConsumer(channel));
                    break;
            }
        }

    }

    private static void parseAndSendMessage(String message, Channel channel) throws IOException {
        String[] splitedByColon = message.split(":");
        String agencyName = splitedByColon[1].split(",")[0].trim();
        String task = splitedByColon[2].trim();
        String msg = "Task " + task + " done";
        channel.basicPublish(Utils.TRANSPORT_EXCHANGE_NAME,
                agencyName,
                null,
                msg.getBytes(StandardCharsets.UTF_8));
    }

}
