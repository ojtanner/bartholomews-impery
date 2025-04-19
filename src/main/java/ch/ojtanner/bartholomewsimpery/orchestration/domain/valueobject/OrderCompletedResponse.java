package ch.ojtanner.bartholomewsimpery.orchestration.domain.valueobject;

import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;

public final class OrderCompletedResponse extends SagaResponse {

    public OrderCompletedResponse(String orderId) {
        super(orderId);
    }
}
