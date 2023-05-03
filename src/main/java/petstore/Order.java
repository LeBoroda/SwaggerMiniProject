package petstore;

import api.IJsonable;
import api.store.OrderJson;
import data.OrderStatusData;
import data.OrderUrlData;
import mock.Mocker;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.jupiter.api.Assertions;


import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static okhttp.Client.JSON;

public final class Order extends SwaggerObject {
    private final int id;
    private final int petId;
    private final int quantity;
    private final String shipDate;
    private final String shipStatus;
    private final boolean isComplete;
    private final Pet pet;
    private final IJsonable orderJson = new OrderJson();

    public Order(final Pet pet, final int quantity) {
        super();
        this.id = (int) (Math.random() * 99);
        this.pet = pet;
        this.petId = this.pet.getPetId();
        this.quantity = quantity;
        this.shipDate = generateShipDate();
        this.shipStatus = getShipStatus();
        this.isComplete = shipStatus.equals(OrderStatusData.DELIVERED.getName());
    }

    private String generateShipDate() {
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(1);
        OffsetDateTime offsetDateTime = localDateTime.atOffset(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return formatter.format(offsetDateTime);
    }

    private String getShipStatus() {
        return OrderStatusData.PLACED.getName();
    }

    public String getPostJson() {
        return String.format(orderJson.getPostJson(), this.id, this.petId, this.quantity, this.shipDate,
                this.shipStatus, this.isComplete);
    }

    public Pet getOrderPet() {
        return this.pet;
    }

    private Response postOrder() {

        RequestBody requestBody = RequestBody.create(getPostJson(), JSON);
        request = new Request.Builder()
                .url(Mocker.getUrl() + OrderUrlData.POST.getName())
                .post(requestBody)
                .build();
        return client.call(request);
    }

    private Response getOrder() {
        request = new Request.Builder()
                .url(Mocker.getUrl() + String.format(OrderUrlData.GET.getName(), this.id))
                .get()
                .build();

        return client.call(request);
    }

    public Response deleteOrderFromSystem() {
        log().info("Deleting order from system");
        request = new Request.Builder()
                .url(Mocker.getUrl() + String.format(OrderUrlData.DELETE.getName(), this.id))
                .delete()
                .build();

        return client.call(request);
    }

    public Response placeOrderInPetStore() {
        Assertions.assertEquals(200, pet.postPet().code());
        Assertions.assertEquals(200, pet.getPet().code());

        log().info("Placing order in petstore");
        postOrder();
        log().info("Getting order from system");
        return getOrder();
    }
}
