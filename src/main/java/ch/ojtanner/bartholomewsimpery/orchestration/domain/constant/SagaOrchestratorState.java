package ch.ojtanner.bartholomewsimpery.orchestration.domain.constant;

public enum SagaOrchestratorState {
    ORDER_CREATED,
    PAYMENT_PROCESSED,
    SUMMONING_FINISHED,
    IMP_DELIVERED,
    ORDER_COMPLETED
}
