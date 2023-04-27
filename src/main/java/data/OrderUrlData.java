package data;

public enum OrderUrlData {
    POST("/store/order");
    private final String name;
    OrderUrlData(final String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

}
