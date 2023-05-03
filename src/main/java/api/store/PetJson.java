package api.store;

import api.IJsonable;

public final class PetJson implements IJsonable {

    private final String petPostJson = "{\n"
            + "  \"id\": %d,\n"
            + "  \"category\": {\n"
            + "    \"id\": %d,\n"
            + "    \"name\": \"%s\"\n"
            + "  },\n"
            + "  \"name\": \"%s\",\n"
            + "  \"photoUrls\": [\n"
            + "    \"%s\"\n"
            + "  ],\n"
            + "  \"tags\": [\n"
            + "    {\n"
            + "      \"id\": %d,\n"
            + "      \"name\": \"%s\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"status\": \"%s\"\n"
            + "}";



    public String getPostJson() {
        return petPostJson;
    }
}
