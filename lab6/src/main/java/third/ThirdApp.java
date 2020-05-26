package third;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.Future;
import third.client.ClientActor;
import third.requests.HttpPriceRequest;
import third.requests.HttpReviewRequest;
import third.server.ServerActor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.concurrent.CompletionStage;

import static utils.Utils.HTTP_PRICE_TIMEOUT;
import static utils.Utils.HTTP_REVIEW_TIMEOUT;

public class ThirdApp extends AllDirectives {

    private ActorRef server;

    public ThirdApp(ActorRef server) {
        this.server = server;
    }

    static void startHttpServer(Route route, ActorSystem system) {
        // Akka HTTP still needs a classic ActorSystem to start
        final Http http = Http.get(system);
        final Materializer materializer = Materializer.matFromSystem(system);

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = route.flow(system, materializer);
        CompletionStage<ServerBinding> futureBinding =
                http.bindAndHandle(routeFlow, ConnectHttp.toHost("localhost", 8080), materializer);


    }

    public static void main(String[] args) throws IOException {

        File configFile = new File("third.conf");
        Config config = ConfigFactory.parseFile(configFile);
        final ActorSystem system = ActorSystem.create("local_system", config);
        ActorRef client = system.actorOf(Props.create(ClientActor.class), "client");
        ActorRef server = system.actorOf(Props.create(ServerActor.class), "server");
        ThirdApp app = new ThirdApp(server);

        final Http http = Http.get(system);
        final Materializer materializer = Materializer.matFromSystem(system);

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = app.createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> futureBinding = http.bindAndHandle(routeFlow,
                ConnectHttp.toHost("localhost", 8080), materializer);

        futureBinding.whenComplete((binding, exception) -> {
            if (binding != null) {
                InetSocketAddress address = binding.localAddress();
                System.out.println(String.format("Server online at http://%s:%s/", address.getHostString(), address.getPort()));
            } else {
                System.out.println("Failed");
                system.terminate();
            }
        });

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; ++i) {
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


//        Http.get(system).singleRequest(HttpRequest.create(
//                String.format("https://www.opineo.pl/?szukaj=%s&s=2", URLEncoder.encode("olimp creatine xplode", "utf-8"))
//        )).thenCompose(response ->
//                response.entity().toStrict(5000, materializer)).thenApply(entity ->
//                entity.getData().utf8String()).thenAccept(System.out::println);

        system.terminate();
    }

    private Route createRoute() {
        return route(
                get(() ->
                        pathPrefix("price", () ->
                                path((String product) ->
                                        get(() -> {
                                            Future<Object> test = Patterns.ask(server, new HttpPriceRequest(product), HTTP_PRICE_TIMEOUT);
                                            return completeOKWithFuture(test, Jackson.marshaller());
                                        })))),
                get(() ->
                        pathPrefix("review", () ->
                                path((String product) ->
                                        get(() -> {
                                            Future<Object> test = Patterns.ask(server, new HttpReviewRequest(product), HTTP_REVIEW_TIMEOUT);
                                            return completeOKWithFuture(test, Jackson.marshaller());
                                        }))))
        );
    }
}