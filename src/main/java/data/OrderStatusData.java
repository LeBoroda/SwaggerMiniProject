package data;

public enum OrderStatusData {
    PLACED("placed"),
    APPROVED("approved"),
    DELIVERED("delivered");

    private final String name;

    OrderStatusData(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
