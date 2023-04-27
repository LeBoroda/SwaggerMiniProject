package okhttp;

import mock.Mocker;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class TestHeaderInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull final Chain chain) throws IOException {
        Request request = chain.request();
        if (Mocker.isMocked()) {
            Request formattedRequest = request.newBuilder()
                    .header("Mocked-Test-Header", "This is mocked test header")
                    .build();
            return chain.proceed(formattedRequest);
        }
        return chain.proceed(request);
    }

}
