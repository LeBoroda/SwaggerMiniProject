package data;

public enum OrderUrlData {
    POST("/store/order"),
    GET("/store/order/%d"),
    DELETE("/store/order/%d");
    private final String name;
    OrderUrlData(final String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

}
