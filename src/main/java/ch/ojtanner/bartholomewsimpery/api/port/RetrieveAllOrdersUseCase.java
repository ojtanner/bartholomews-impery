package ch.ojtanner.bartholomewsimpery.api.port;

import ch.ojtanner.bartholomewsimpery.domain.entities.Order;

import java.util.List;

public interface RetrieveAllOrdersUseCase {

    List<Order> handle();
}
