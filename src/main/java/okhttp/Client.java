package okhttp;

import logging.ILoggable;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Headers;


import java.io.IOException;

public final class Client implements ILoggable {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private final TestHeaderInterceptor interceptor = new TestHeaderInterceptor();
    private final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build();

    public Response call(final Request request) {
        try (Response response = okHttpClient.newCall(request).execute()) {
            int code = response.code();
            log().info(String.valueOf(code));

            Headers headers = response.headers();
            headers.names().forEach(log()::info);

            assert response.body() != null;
            String body = response.body().string();
            log().info(body);

            MediaType mediaType = response.body().contentType();
            return response.newBuilder().body(ResponseBody.create(body, mediaType)).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
