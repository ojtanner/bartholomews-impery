package ch.ojtanner.bartholomewsimpery.accounting.api.port;

import ch.ojtanner.bartholomewsimpery.accounting.domain.entity.Order;

import java.util.List;

public interface RetrieveAllPaymentsUseCase {

    List<Order> handle();
}
