package data;

public enum PetUrlData {

    POST("/pet"),
    GET("/pet/%d"),
    DELETE("/pet/%d");
    private final String name;
    PetUrlData(final String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

}
