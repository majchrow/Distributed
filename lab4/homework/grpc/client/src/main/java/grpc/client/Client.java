package grpc.client;

import grpc.proto.Country;
import grpc.proto.Request;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Client {
    private final ManagedChannel channel;
    private final Map<Integer, CVStub> stubs;


    public Client(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid needing certificates.
                .usePlaintext(/*true*/)
                .build();
        stubs = new ConcurrentHashMap<>();
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client("localhost", 50051);
        client.test();
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void test() throws InterruptedException {
        try {
            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                filterStreams();
                printUsage();
                String message = br.readLine();
                if (message.equals("exit")) {
                    System.out.println("Exiting");
                    break;
                } else if (message.equals("n")) {
                    System.out.println("Creating new stream");
                    CVStub cvStub = new CVStub(channel);
                    Integer nextKey = getNextKey();
                    System.out.println(nextKey);
                    stubs.put(nextKey, cvStub);
                } else if (isValidMessage(message)) {
                    Integer key = Integer.parseInt(message);
                    System.out.println("You are in existing stream number " + key);
                    CVStub cvStub = stubs.get(key);
                    printStreamUsage();
                    String streamMessage = br.readLine();
                    if (isValidStreamMessage(streamMessage)) {
                        Request.Action action = getAction(streamMessage);
                        Country country = getCountry(streamMessage);
                        System.out.println("Sending request " + action + ", " + country + " to stream number " + key);
                        cvStub.createAndSendRequest(action, country);
                    } else {
                        System.out.println("Wrong request provided");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }

    }

    private void filterStreams() {
        for (Map.Entry<Integer, CVStub> entry : stubs.entrySet()) {
            if (entry.getValue().streamIsNotRunning()) {
                stubs.remove(entry.getKey());
            }
        }
    }

    private void printUsage() {
        System.out.println("n - create new stream");
        for (Map.Entry<Integer, CVStub> entry : stubs.entrySet()) {
            System.out.println(entry.getKey() + " - existing stream");
        }
    }

    private boolean isValidMessage(String message) {
        List<String> keys = stubs.keySet().stream().map(Object::toString).collect(Collectors.toList());
        return keys.contains(message);
    }

    private Integer getNextKey() {  // lowest number not in array
        Integer[] keys = stubs.keySet().stream().sorted().toArray(Integer[]::new);
        for (int i = 0; i < keys.length; ++i) {
            if (keys[i] != i) return i;
        }
        return keys.length;
    }

    private void printStreamUsage() {
        System.out.println("Action,Country");
        System.out.println("Action: 0 - Start, 1 Stop");
        System.out.println("Country: 0 - US, 1 - Poland, 2 - Germany, 3 - France, 4 - Japan");
        System.out.println("For example: 0,1");
    }

    private boolean isValidStreamMessage(String message) {
        String[] array = message.split(",");
        if (array.length != 2) return false;
        String action = array[0].trim();
        if (action.equals("1")) return true;
        List<String> countries = Arrays.asList("0", "1", "2", "3", "4");
        String country = array[1].trim();
        return action.equals("0") && countries.contains(country);

    }

    private Request.Action getAction(String message) {
        String[] array = message.split(",");
        return array[0].trim().equals("0") ? Request.Action.START : Request.Action.STOP;
    }


    private Country getCountry(String message) {
        String[] array = message.split(",");
        String country = array[1].trim();
        switch (country) {
            case "0":
                return Country.US;
            case "1":
                return Country.Poland;
            case "2":
                return Country.Germany;
            case "3":
                return Country.France;
            case "4":
                return Country.Japan;
        }
        return Country.US;
    }
}
