package utils;

import java.util.Date;

public class Utils {

    public static final String SERVER_ADDRESS = "akka://local_system/user/server";

    public static final int HTTP_REVIEW_TIMEOUT = 5000;
    public static final int HTTP_PRICE_TIMEOUT = 600;
    public static final int TIMEOUT = 300;

    public static String getTimestamp() {
        return Long.toString(new Date().getTime());
    }
}
