package ch.ojtanner.bartholomewsimpery.orchestration.domain.entity;

import ch.ojtanner.bartholomewsimpery.orchestration.domain.constant.SagaOrchestratorState;

public class SagaState {

    private final String id;
    private SagaOrchestratorState state;

    public SagaState(String id) {
        this.id = id;
        this.state = SagaOrchestratorState.ORDER_CREATED;
    }

    public String getId() {
        return this.id;
    }

    public SagaOrchestratorState getState() {
        return this.state;
    }

    // Happy case
    public void advanceState() {
        switch (this.state) {
            case ORDER_CREATED -> this.state = SagaOrchestratorState.PAYMENT_PROCESSED;
            case PAYMENT_PROCESSED -> this.state = SagaOrchestratorState.SUMMONING_FINISHED;
            case SUMMONING_FINISHED -> this.state = SagaOrchestratorState.IMP_DELIVERED;
            case IMP_DELIVERED -> this.state = SagaOrchestratorState.ORDER_COMPLETED;
            case ORDER_COMPLETED -> {}
        }
    }

    // TODO
    public void compensateState() {}
}
