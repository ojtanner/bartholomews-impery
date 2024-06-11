package ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.port;

import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;

public interface ReceptionCommandsPublisher {

    public void publishDeliverImpCommand(Order order);
    public void publishCompleteOrderCommand(Order order);
}
