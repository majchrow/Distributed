package third.server;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.stream.Materializer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static utils.Utils.HTTP_REVIEW_TIMEOUT;

public class WebScraperWorker extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private ActorRef sender = null;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class,
                        s -> {
                            this.sender = getSender();
                            sendResponseAndStop(s);
                        })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }

    public void sendResponseAndStop(String request) throws UnsupportedEncodingException {
        final Materializer materializer = Materializer.matFromSystem(getContext().getSystem());
        Http.get(getContext().getSystem()).singleRequest(HttpRequest.create(
                String.format("https://www.opineo.pl/?szukaj=%s&s=2", URLEncoder.encode(request, "utf-8"))
        )).thenCompose(response ->
                response.entity().toStrict(HTTP_REVIEW_TIMEOUT, materializer)).thenApply(entity ->
                entity.getData().utf8String()).thenAccept(body -> {
            Document doc = Jsoup.parse(body);
            Elements elements = doc.select("div.pl_attr");
            for (Element e : elements) {
                if (e.text().startsWith("Zalety")) {
                    sender.tell(e.text(), getSelf());
                    context().stop(self());
                }
            }
            sender.tell("Not found", getSelf());
            context().stop(self());
        });
    }
}
