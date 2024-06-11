package ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.port;

import ch.ojtanner.bartholomewsimpery.orchestration.domain.entity.SagaState;

import java.util.Optional;

public interface SagaStateRepository {

    void save(SagaState sagaOrchestrator);
    Optional<SagaState> findById(String id);
}
