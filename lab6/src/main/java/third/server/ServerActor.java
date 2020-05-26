package third.server;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.pattern.Patterns;
import scala.concurrent.Future;
import third.requests.HttpPriceRequest;
import third.requests.HttpReviewRequest;

import static utils.Utils.HTTP_PRICE_TIMEOUT;
import static utils.Utils.HTTP_REVIEW_TIMEOUT;

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
                    Future<Object> fut = Patterns.ask(target, s.getProduct(), HTTP_PRICE_TIMEOUT);
                    Patterns.pipe(fut, getContext().dispatcher()).to(getSender());
                })
                .match(HttpReviewRequest.class, s -> {
                    ActorRef target = context().actorOf(Props.create(ServerWorker.class).withDispatcher("review-dispatcher"));
                    Future<Object> fut = Patterns.ask(target, s.getProduct(), HTTP_REVIEW_TIMEOUT);
                    Patterns.pipe(fut, getContext().dispatcher()).to(getSender());
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}
