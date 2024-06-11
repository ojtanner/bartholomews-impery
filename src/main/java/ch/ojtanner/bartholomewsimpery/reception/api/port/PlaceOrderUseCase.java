package ch.ojtanner.bartholomewsimpery.reception.api.port;

import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;

public interface PlaceOrderUseCase {

    Order handle(int summoningFee);
}
