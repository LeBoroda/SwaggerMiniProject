import com.github.tomakehurst.wiremock.WireMockServer;
import data.OrderStatusData;
import mock.Mocker;
import okhttp3.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import petstore.Order;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;


public class PetStoreTest {
    private Order order = new Order(1, 13, 1, "2023-04-22T20:16:10.093Z",
            OrderStatusData.PLACED.getName());
    private final WireMockServer wireMockServer = new WireMockServer(options().port(18389));

    @Test
    public void orderPlacementTest() {
        Mocker.setIsMocked(false);
        try (Response response = order.postOrder()) {
            Assertions.assertEquals("application/json", response.headers().values("content-type").get(0));
            Assertions.assertEquals(200, response.code());
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
