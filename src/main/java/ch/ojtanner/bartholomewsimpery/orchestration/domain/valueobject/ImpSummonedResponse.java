package ch.ojtanner.bartholomewsimpery.orchestration.domain.valueobject;

import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;

public final class ImpSummonedResponse extends SagaResponse {

    public ImpSummonedResponse(String orderId) {
        super(orderId);
    }
}
