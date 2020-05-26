package second.server;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public class PriceCheckerWorker extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    sendPrice();
                    context().stop(self());
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }

    private void sendPrice() {
        getContext().getSystem().scheduler().scheduleOnce(
                Duration.ofMillis(ThreadLocalRandom.current().nextInt(100, 501)),
                getSender(),
                ThreadLocalRandom.current().nextInt(1, 11),
                getContext().getSystem().dispatcher(),
                getSelf()
        );
    }
}
