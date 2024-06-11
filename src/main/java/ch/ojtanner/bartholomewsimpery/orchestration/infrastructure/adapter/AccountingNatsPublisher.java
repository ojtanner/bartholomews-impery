package ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.adapter;

import ch.ojtanner.bartholomewsimpery.orchestration.infrastructure.port.AccountingCommandsPublisher;
import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class AccountingNatsPublisher implements AccountingCommandsPublisher {
    @Override
    public void publishProcessPaymentCommand(Order order) {
        System.out.println("Publishing bill customer command for " + order.getId());
    }
}
