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
        final Channel channelInAgency = connection.createChannel();
        final Channel channelInPremium = connection.createChannel();
//        final Channel channelOut = connection.createChannel();

        Service[] resources = getResources();
        handleAdmin(channelInPremium);
        handleResources(resources, channelInAgency);

        // TODO SENDING
//        channelOut.exchangeDeclare(Utils.TRANSPORT_EXCHANGE_NAME, BuiltinExchangeType.TOPIC);


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
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("Received from admin: " + message);
            }
        };
    }

    public static Consumer createPassengerConsumer(Channel channel) {
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("Received from passenger: " + message);
            }
        };
    }

    public static Consumer createItemConsumer(Channel channel) {
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("Received from item: " + message);
            }
        };
    }

    public static Consumer createSatellitesConsumer(Channel channel) {
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("Received from satellite: " + message);
            }
        };
    }

    private static void handleAdmin(Channel channelInPremium) throws IOException {
        channelInPremium.exchangeDeclare(Utils.ADMIN_EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String adminTransportQueueName = Utils.ADMIN_TRANSPORT_QUEUE + Utils.getTimestamp();
        channelInPremium.queueBind(adminTransportQueueName, Utils.ADMIN_EXCHANGE_NAME, Utils.ADMIN_ALL_ROUTING_KEY);
        channelInPremium.queueBind(adminTransportQueueName, Utils.ADMIN_EXCHANGE_NAME, Utils.ADMIN_TRANSPORT_ROUTING_KEY);
        System.out.println("Created queue " + adminTransportQueueName +
                " with routing keys: " + Utils.ADMIN_ALL_ROUTING_KEY + ", " + Utils.ADMIN_TRANSPORT_ROUTING_KEY);
        channelInPremium.basicConsume(adminTransportQueueName, false, createAdminMessageConsumer(channelInPremium));
    }

    private static void handleResources(Service[] resources, Channel channelInAgency) throws IOException {
        channelInAgency.exchangeDeclare(Utils.AGENCY_EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        for (Service resource : resources) {
            switch (resource) {
                case PASSENGER:
                    String passengerQueueName = Utils.PASSENGER_SHARED_QUEUE;
                    channelInAgency.queueBind(passengerQueueName, Utils.AGENCY_EXCHANGE_NAME, Utils.PASSENGER_ROUTING_KEY);
                    System.out.println("Created queue " + passengerQueueName +
                            " with routing key " + Utils.PASSENGER_ROUTING_KEY);
                    channelInAgency.basicConsume(passengerQueueName, false, createPassengerConsumer(channelInAgency));
                case ITEM:
                    String itemQueueName = Utils.ITEM_SHARED_QUEUE;
                    channelInAgency.queueBind(itemQueueName, Utils.AGENCY_EXCHANGE_NAME, Utils.ITEM_ROUTING_KEY);
                    System.out.println("Created queue " + itemQueueName +
                            " with routing key " + Utils.ITEM_ROUTING_KEY);
                    channelInAgency.basicConsume(itemQueueName, false, createItemConsumer(channelInAgency));
                case SATELLITE:
                    String satelliteQueueName = Utils.SATELLITE_SHARED_QUEUE;
                    channelInAgency.queueBind(satelliteQueueName, Utils.AGENCY_EXCHANGE_NAME, Utils.SATELLITE_ROUTING_KEY);
                    System.out.println("Created queue " + satelliteQueueName +
                            " with routing key " + Utils.SATELLITE_ROUTING_KEY);
                    channelInAgency.basicConsume(satelliteQueueName, false, createSatellitesConsumer(channelInAgency));
            }
        }

    }
}
