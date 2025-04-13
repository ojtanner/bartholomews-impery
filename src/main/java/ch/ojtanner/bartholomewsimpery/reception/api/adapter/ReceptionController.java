package ch.ojtanner.bartholomewsimpery.reception.api.adapter;

import ch.ojtanner.bartholomewsimpery.reception.api.port.PlaceOrderUseCase;
import ch.ojtanner.bartholomewsimpery.reception.api.port.RetrieveAllOrdersUseCase;
import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/reception")
public class ReceptionController {

    private final PlaceOrderUseCase placeOrderUseCase;
    private final RetrieveAllOrdersUseCase retrieveAllOrdersUseCase;

    public ReceptionController(
            PlaceOrderUseCase placeOrderUseCase,
            RetrieveAllOrdersUseCase retrieveAllOrdersUseCase
    ) {
        this.placeOrderUseCase = placeOrderUseCase;
        this.retrieveAllOrdersUseCase = retrieveAllOrdersUseCase;
    }

    @PostMapping(path = "/order/{summoningFee}")
    public Order placeOrder(@PathVariable Integer summoningFee) {
        return placeOrderUseCase.handle(summoningFee);
    }

    @GetMapping(path = "/order")
    public List<Order> retrieveAllOrders() {
        return retrieveAllOrdersUseCase.handle();
    }
}
