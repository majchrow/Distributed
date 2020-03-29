import java.util.Date;

public class Utils {

    public static final String ADMIN_EXCHANGE_NAME = "Admin";
    public static final String TRANSPORT_EXCHANGE_NAME = "Transport";
    public static final String AGENCY_EXCHANGE_NAME = "Agency";

    public static final String ADMIN_QUEUE = "Admin";
    public static final String ADMIN_TRANSPORT_QUEUE = "AdminTransport";
    public static final String ADMIN_AGENCY_QUEUE = "AdminAgency";


    public static final String PASSENGER_SHARED_QUEUE = "Passenger";
    public static final String ITEM_SHARED_QUEUE = "Item";
    public static final String SATELLITE_SHARED_QUEUE = "Satellite";

    public static final String ADMIN_ROUTING_KEY = "#";
    public static final String ADMIN_ALL_ROUTING_KEY = "admin.all";
    public static final String ADMIN_TRANSPORT_ROUTING_KEY = "admin.transport";
    public static final String ADMIN_AGENCY_ROUTING_KEY = "admin.agency";
    public static final String PASSENGER_ROUTING_KEY = "passenger";
    public static final String ITEM_ROUTING_KEY = "item";
    public static final String SATELLITE_ROUTING_KEY = "satellite";

    public static String getTimestamp() {
        return Long.toString(new Date().getTime());
    }
}
