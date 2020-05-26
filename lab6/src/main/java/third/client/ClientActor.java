package third.client;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import static utils.Utils.SERVER_ADDRESS;


public class ClientActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    if (isResponse()) {
                        System.out.println(s);
                    } else {
                        sendRequest(s);
                    }
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }

    public boolean isResponse() {
        return getSender().path().toString().startsWith(SERVER_ADDRESS);
    }

    public void sendRequest(String request) {
        getContext().actorSelection("/user/server").tell(request, getSelf());
    }

}
