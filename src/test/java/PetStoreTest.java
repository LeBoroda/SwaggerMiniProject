import com.github.tomakehurst.wiremock.WireMockServer;
import data.PetCategoryData;
import data.PetTagsData;
import mock.Mocker;

import okhttp3.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;;
import org.junit.jupiter.params.provider.CsvSource;
import petstore.Order;
import petstore.Pet;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;


public class PetStoreTest {
    private final Order order = new Order(new Pet(PetCategoryData.DOGS, "Sharik", PetTagsData.TESTDOG), 1);
    private static final WireMockServer wireMockServer = new WireMockServer(options().port(18389));

    @BeforeAll
    public static void setup() {
        wireMockServer.start();
        Mocker.setIsMocked((System.getProperty("isMocked")));
    }

    @ParameterizedTest
    @CsvSource("200, application/json")
    public void orderPlacementTest(int arg1, String arg2) {

        try (Response response = order.placeOrderInPetStore()) {
            Assertions.assertEquals(arg2, response.headers().values("content-type").get(0));
            Assertions.assertEquals(arg1, response.code());
        }
        Assertions.assertEquals(arg1, order.getOrderPet().deletePet().code());
        Assertions.assertEquals(arg1, order.deleteOrderFromSystem().code());
    }


    @AfterAll
    public static void shutDown() {
        wireMockServer.stop();
    }

}