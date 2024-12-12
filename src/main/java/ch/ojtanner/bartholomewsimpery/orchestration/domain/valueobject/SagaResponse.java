package ch.ojtanner.bartholomewsimpery.orchestration.domain.valueobject;

import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;

public abstract sealed class SagaResponse permits ImpDeliveredResponse, ImpSummonedResponse, OrderCompletedResponse, PaymentProcessedResponse {

    private final Order order;

    protected SagaResponse(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
