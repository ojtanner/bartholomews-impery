package ch.ojtanner.bartholomewsimpery.service.usecases;

import ch.ojtanner.bartholomewsimpery.domain.entities.Order;

public interface PlaceOrderUseCase {

    Order handle(int summoningFee);
}
