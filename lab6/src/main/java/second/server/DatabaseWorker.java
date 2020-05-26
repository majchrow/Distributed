package second.server;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.sql.ResultSet;
import java.sql.SQLException;

import static second.SecondApp.statement;

public class DatabaseWorker extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    int count = getQueryCount(s);
                    sendBack(count);
                    updateDb(s);
                    context().stop(self());
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }

    public void sendBack(int count) {
        getSender().tell(count, getSelf());
    }

    public void updateDb(String product) {
        try {
            String query = String.format("INSERT INTO queries (name, count) VALUES ('%s', 1) " +
                    "ON CONFLICT(name) DO UPDATE SET count = count + 1", product);
            statement.executeUpdate(query);
        } catch (SQLException ignored) {
        }
    }

    public int getQueryCount(String product) {
        int result = -1;
        try {
            String query = String.format("SELECT count FROM queries WHERE name = '%s'", product);
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                result = rs.getInt("count");
            } else {
                result = 0;
            }
        } catch (SQLException ignored) {
        }
        return result;
    }
}
