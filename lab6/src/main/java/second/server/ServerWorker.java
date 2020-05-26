package second.server;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
import static utils.Utils.TIMEOUT;

public class ServerWorker extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private final Timeout timeout = Timeout.create(Duration.ofMillis(TIMEOUT));

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    ActorRef databaseWorker = context().actorOf(Props.create(DatabaseWorker.class).withDispatcher("my-database-dispatcher"));
                    ActorRef firstPriceChecker = context().actorOf(Props.create(PriceCheckerWorker.class));
                    ActorRef secondPriceChecker = context().actorOf(Props.create(PriceCheckerWorker.class));
                    Future<Object> firstPriceFuture = Patterns.ask(firstPriceChecker, s, timeout);
                    Future<Object> secondPriceFuture = Patterns.ask(secondPriceChecker, s, timeout);
                    Future<Object> queryCountFuture = Patterns.ask(databaseWorker, s, timeout);
                    int firstPrice = -1;
                    int secondPrice = -1;
                    int queryCount = -1;
                    try {
                        firstPrice = (int) Await.result(firstPriceFuture, timeout.duration());
                    } catch (TimeoutException ignored) {
                    }
                    try {
                        secondPrice = (int) Await.result(secondPriceFuture, timeout.duration());
                    } catch (TimeoutException ignored) {
                    }
                    try {
                        queryCount = (int) Await.result(queryCountFuture, timeout.duration());
                    } catch (TimeoutException ignored) {
                    }

                    String price = firstPrice < 0 && secondPrice < 0 ? "not found" : Integer.toString(
                            firstPrice < 0 || secondPrice < 0 ? max(firstPrice, secondPrice) :
                                    min(firstPrice, secondPrice));

                    String count = queryCount < 0 ? "not found" : Integer.toString(queryCount);

                    String response = String.format("Product: %s | Price %s | Queries %s",
                            s, price, count);
                    getSender().tell(response, getSelf());
                    context().stop(self());
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}
