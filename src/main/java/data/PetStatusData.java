package data;

public enum PetStatusData {

    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    private final String name;

    PetStatusData(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
