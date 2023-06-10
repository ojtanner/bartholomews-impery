package ch.ojtanner.bartholomewsimpery.infrastructure.incoming;

import ch.ojtanner.bartholomewsimpery.domain.constants.Currency;
import ch.ojtanner.bartholomewsimpery.domain.entities.Order;
import ch.ojtanner.bartholomewsimpery.domain.valueobjects.SummoningFee;
import ch.ojtanner.bartholomewsimpery.service.ports.outgoing.OrderRepository;
import ch.ojtanner.bartholomewsimpery.service.usecases.PlaceOrderUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    private final PlaceOrderUseCase placeOrderUseCase;
    private final OrderRepository orderRepository;

    public OrderController(PlaceOrderUseCase placeOrderUseCase, OrderRepository orderRepository) {
        this.placeOrderUseCase = placeOrderUseCase;
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public Order placeOrder(@RequestBody int summoningFee) {
        return placeOrderUseCase.handle(summoningFee);
    }

    @GetMapping
    public String helloWorld() {
        return "Hello, World";
    }

    @PostMapping(path = "/save")
    public void saveTest() {
        Order order = new Order(1234, new SummoningFee(Currency.GOLD, 500));

        orderRepository.save(order);
    }

    @GetMapping(path = "/get")
    public Order getTest() {
        Order fallback = new Order(666, new SummoningFee(Currency.GOLD, 100));

        return orderRepository.findById(1234).orElse(fallback);
    }
}
