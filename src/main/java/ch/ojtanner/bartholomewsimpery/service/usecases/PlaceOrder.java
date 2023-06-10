package ch.ojtanner.bartholomewsimpery.service.usecases;

import ch.ojtanner.bartholomewsimpery.domain.constants.Currency;
import ch.ojtanner.bartholomewsimpery.domain.entities.Order;
import ch.ojtanner.bartholomewsimpery.domain.valueobjects.SummoningFee;
import ch.ojtanner.bartholomewsimpery.service.ports.outgoing.IdGenerator;
import ch.ojtanner.bartholomewsimpery.service.ports.outgoing.OrderPublisher;
import ch.ojtanner.bartholomewsimpery.service.ports.outgoing.OrderRepository;
import ch.ojtanner.bartholomewsimpery.service.ports.outgoing.AccountingPublisher;
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
