package ch.ojtanner.bartholomewsimpery.orchestration.domain.valueobject;

import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;

public final class ImpDeliveredResponse extends SagaResponse {

    public ImpDeliveredResponse(String orderId) {
        super(orderId);
    }
}
