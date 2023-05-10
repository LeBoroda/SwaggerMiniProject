package servise;

import okhttp.Client;
import okhttp3.Request;

public abstract class AbsService {
    protected Request request;
    protected Client client = new Client();
}
