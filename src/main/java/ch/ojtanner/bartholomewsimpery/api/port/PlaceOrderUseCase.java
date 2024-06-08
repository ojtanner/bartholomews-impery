package ch.ojtanner.bartholomewsimpery.api.port;

import ch.ojtanner.bartholomewsimpery.domain.entities.Order;

public interface PlaceOrderUseCase {

    Order handle(int summoningFee);
}
