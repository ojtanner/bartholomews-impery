package ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.port;

import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;

public interface AccountingCommandsPublisher {

    void publishProcessPaymentCommand(Order order);
}
