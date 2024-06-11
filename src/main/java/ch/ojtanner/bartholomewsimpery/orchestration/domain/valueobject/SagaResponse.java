package ch.ojtanner.bartholomewsimpery.orchestration.domain.valueobject;

public abstract sealed class SagaResponse permits ImpDeliveredResponse, ImpSummonedResponse, OrderCompletedResponse, PaymentProcessedResponse {

    private final String id;

    protected SagaResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
