package ch.ojtanner.bartholomewsimpery.orchestration.domain.valueobject;

import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;

public abstract sealed class SagaResponse permits ImpDeliveredResponse, ImpSummonedResponse, OrderCompletedResponse, PaymentProcessedResponse {

    private final String orderId;

    protected SagaResponse(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return this.orderId;
    }
}
