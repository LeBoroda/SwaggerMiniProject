import com.github.tomakehurst.wiremock.WireMockServer;
import data.OrderStatusData;
import mock.Mocker;
import okhttp3.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import petstore.Order;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;


public class PetStoreTest {
    private final Order order = new Order(1, 13, 1, "2023-04-22T20:16:10.093Z",
            OrderStatusData.PLACED.getName());
    private final WireMockServer wireMockServer = new WireMockServer(options().port(18389));

    @ParameterizedTest
    @CsvSource("200, application/json")
    public void orderPlacementTest(int arg1, String arg2) {
        Mocker.setIsMocked(false);
        try (Response response = order.postOrder()) {
            Assertions.assertEquals(arg2, response.headers().values("content-type").get(0));
            Assertions.assertEquals(arg1, response.code());
        }
    }

    @Test
    public void mockedOrderPlacementTest() {
        Mocker.setIsMocked(true);
        wireMockServer.start();
        try (Response response = order.postOrder()) {
            Assertions.assertEquals("application/json", response.headers().values("content-type").get(0));
            Assertions.assertEquals(200, response.code());
        }
        wireMockServer.stop();
    }
}
