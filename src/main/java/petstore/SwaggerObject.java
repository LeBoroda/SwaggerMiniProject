package petstore;

import logging.ILoggable;
import okhttp.Client;
import okhttp3.Request;

public abstract class SwaggerObject implements ILoggable {
    protected Client client;
    protected Request request;

    public SwaggerObject() {
        this.client = new Client();
    }
}
