package petstore;

import api.IJsonable;
import api.store.OrderJson;
import data.OrderStatusData;
import data.OrderUrlData;
import mock.Mocker;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static okhttp.Client.JSON;

public final class Order extends SwaggerObject {
    private final int id;
    private final int petId;
    private final int quantity;
    private final String shipDate;
    private final String shipStatus;
    private final boolean isComplete;
    private final IJsonable orderJson = new OrderJson();

    public Order(final int id, final int petId, final int quantity, final String shipDate, final String shipStatus) {
        super();
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.shipStatus = shipStatus;
        this.isComplete = shipStatus.equals(OrderStatusData.DELIVERED.getName());
    }

    public String getPostJson() {
        return String.format(orderJson.getPostJson(), this.id, this.petId, this.quantity, this.shipDate,
                this.shipStatus, this.isComplete);
    }

    public String getPostUrl() {
        return OrderUrlData.POST.getName();
    }

    public Response postOrder() {
        RequestBody requestBody = RequestBody.create(getPostJson(), JSON);
        request = new Request.Builder()
                .url(Mocker.getUrl() + getPostUrl())
                .post(requestBody)
                .build();
        return client.call(request);
    }

}
