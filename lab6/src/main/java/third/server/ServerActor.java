package third.server;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import third.requests.HttpPriceRequest;
import third.requests.HttpReviewRequest;

public class ServerActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    ActorRef target = context().actorOf(Props.create(ServerWorker.class));
                    target.forward(s, getContext());
                })
                .match(HttpPriceRequest.class, s -> {
                    ActorRef target = context().actorOf(Props.create(ServerWorker.class));
                    target.forward(s.getProduct(), getContext());
                })
                .match(HttpReviewRequest.class, s -> {
                    ActorRef target = context().actorOf(Props.create(WebScraperWorker.class).withDispatcher("review-dispatcher"));
                    target.forward(s.getProduct(), getContext());
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}
