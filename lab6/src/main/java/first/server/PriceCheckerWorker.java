package first.server;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.concurrent.ThreadLocalRandom;

public class PriceCheckerWorker extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    getSender().tell(getPrice(), getSelf());
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }

    private int getPrice() throws InterruptedException {
        int sleepTime = ThreadLocalRandom.current().nextInt(100, 501);
        Thread.sleep(sleepTime);
        return ThreadLocalRandom.current().nextInt(1, 11);
    }
}
