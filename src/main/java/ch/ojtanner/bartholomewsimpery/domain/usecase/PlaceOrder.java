package ch.ojtanner.bartholomewsimpery.domain.usecase;

import ch.ojtanner.bartholomewsimpery.api.port.PlaceOrderUseCase;
import ch.ojtanner.bartholomewsimpery.domain.constants.Currency;
import ch.ojtanner.bartholomewsimpery.domain.entities.Order;
import ch.ojtanner.bartholomewsimpery.domain.valueobjects.SummoningFee;
import ch.ojtanner.bartholomewsimpery.infrastructure.port.IdGenerator;
import ch.ojtanner.bartholomewsimpery.infrastructure.port.OrderPublisher;
import ch.ojtanner.bartholomewsimpery.infrastructure.port.OrderRepository;
import ch.ojtanner.bartholomewsimpery.infrastructure.port.AccountingPublisher;
import org.springframework.stereotype.Component;

@Component
public class PlaceOrder implements PlaceOrderUseCase {

    private final OrderPublisher orderPublisher;
    private final AccountingPublisher accountingPublisher;
    private final OrderRepository orderRepository;
    private final IdGenerator idGenerator;

    public PlaceOrder(
            OrderPublisher orderPublisher,
            AccountingPublisher accountingPublisher,
            OrderRepository orderRepository,
            IdGenerator idGenerator
    ) {
        this.orderPublisher = orderPublisher;
        this.accountingPublisher = accountingPublisher;
        this.orderRepository = orderRepository;
        this.idGenerator = idGenerator;
    }

    @Override
    public Order handle(int payload) {
        SummoningFee summoningFee = new SummoningFee(Currency.GOLD, payload);
        String newId = idGenerator.generate();
        Order newOrder = new Order(newId, summoningFee);

        orderRepository.save(newOrder);
        accountingPublisher.publish(summoningFee);
        orderPublisher.publish(newOrder);
        newOrder.inProgress();

        return newOrder;
    }
}
