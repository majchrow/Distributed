public enum Service {
    PASSENGER(1), ITEM(2), SATELLITE(3);

    public final int id;

    Service(int id) {
        this.id = id;
    }


    public static Service getById(int id) {
        return Service.values()[id];
    }
}
