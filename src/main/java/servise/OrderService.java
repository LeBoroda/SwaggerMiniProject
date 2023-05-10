package servise;

import api.IJsonable;
import api.store.OrderJson;
import data.OrderUrlData;
import logging.ILoggable;
import mock.Mocker;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import petstore.Order;

import static okhttp.Client.JSON;

public class OrderService extends AbsService implements ILoggable {

    private final Order order;

    private final IJsonable orderJson = new OrderJson();

    public OrderService(Order order) {
        this.order = order;
    }

    public String getPostJson() {
        return String.format(orderJson.getPostJson(), order.getId(), order.getPetId(), order.getQuantity(), order.getShipDate(),
                order.getShipStatus(), order.isComplete());
    }

    public Response postOrder() {
        log().info("Posting order to system");
        RequestBody requestBody = RequestBody.create(getPostJson(), JSON);
        request = new Request.Builder()
                .url(Mocker.getUrl() + OrderUrlData.POST.getName())
                .post(requestBody)
                .build();
        return client.call(request);
    }

    public Response getOrder(Order order) {
        log().info("Retrieving order from system");
        request = new Request.Builder()
                .url(Mocker.getUrl() + String.format(OrderUrlData.GET.getName(), order.getId()))
                .get()
                .build();

        return client.call(request);
    }

    public Response deleteOrder(Order order) {
        log().info("Deleting order from system");
        request = new Request.Builder()
                .url(Mocker.getUrl() + String.format(OrderUrlData.DELETE.getName(), order.getId()))
                .delete()
                .build();

        return client.call(request);
    }

}
