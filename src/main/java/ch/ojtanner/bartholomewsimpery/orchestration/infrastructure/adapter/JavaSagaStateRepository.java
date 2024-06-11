package ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.adapter;

import ch.ojtanner.bartholomewsimpery.orchestration.domain.entity.SagaState;
import ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.port.SagaStateRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class JavaSagaStateRepository implements SagaStateRepository {

    private final Map<String, SagaState> sagaOrchestratorRepository = Collections.synchronizedMap(new HashMap<>());

    @Override
    public void save(SagaState sagaOrchestrator) {
        if (sagaOrchestratorRepository.containsKey(sagaOrchestrator.getId())) {
            System.out.println("SagaOrchestrator of ID " + sagaOrchestrator.getId() + " already exists");
            return;
        }

        sagaOrchestratorRepository.put(sagaOrchestrator.getId(), sagaOrchestrator);
    }

    @Override
    public Optional<SagaState> findById(String id) {
        return Optional.ofNullable(sagaOrchestratorRepository.get(id));
    }
}
