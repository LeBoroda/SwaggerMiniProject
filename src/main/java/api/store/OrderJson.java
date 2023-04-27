package api.store;

import api.IJsonable;

public final class OrderJson implements IJsonable {
    private final String orderPostJson = "{\n"
            + "  \"id\": %d,\n"
            + "  \"petId\": %d,\n"
            + "  \"quantity\": %d,\n"
            + "  \"shipDate\": \"%s\",\n"
            + "  \"status\": \"%s\",\n"
            + "  \"complete\": %b\n"
            + "}";

    public String getPostJson() {
        return orderPostJson;
    }
}
