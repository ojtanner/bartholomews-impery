package ch.ojtanner.bartholomewsimpery.reception.api.port;

import ch.ojtanner.bartholomewsimpery.reception.domain.entity.Order;

import java.util.List;

public interface RetrieveAllOrdersUseCase {

    List<Order> handle();
}
