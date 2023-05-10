package petstore;

import data.OrderStatusData;
import okhttp3.Response;
import org.junit.jupiter.api.Assertions;
import servise.OrderService;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


public final class Order extends SwaggerObject {
    private final int id;
    private final int petId;
    private final int quantity;
    private final String shipDate;
    private final String shipStatus;
    private final boolean isComplete;
    private final Pet pet;
    OrderService orderService = new OrderService(this);

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

    public String getShipStatus() {
        return OrderStatusData.PLACED.getName();
    }

    public int getId() {
        return id;
    }

    public int getPetId() {
        return petId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getShipDate() {
        return shipDate;
    }

    public boolean isComplete() {
        return isComplete;
    }

    private Response placeOrderInPetStore() {
        Assertions.assertEquals(200, pet.postPetInPetStore().code());
        Assertions.assertEquals(200, pet.getPetFromPetStore().code());

        orderService.postOrder();
        return orderService.getOrder(this);
    }

    public void runOrderPlacementTest(int code, String header) {
        try (Response response = placeOrderInPetStore()) {
            Assertions.assertEquals(header, response.headers().values("content-type").get(0));
            Assertions.assertEquals(code, response.code());
        }
        Assertions.assertEquals(code, pet.deletePetFromSystem().code());
        Assertions.assertEquals(code, orderService.deleteOrder(this).code());
    }
}