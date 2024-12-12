package ch.ojtanner.bartholomewsimpery.orchestration.domain.valueobject;

import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;

public final class PaymentProcessedResponse extends SagaResponse {

    public PaymentProcessedResponse(Order order) {
        super(order);
    }
}
