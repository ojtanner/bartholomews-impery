package ch.ojtanner.bartholomewsimpery.reception.domain.entity;

import ch.ojtanner.bartholomewsimpery.reception.domain.constant.OrderStatus;
import ch.ojtanner.bartholomewsimpery.reception.domain.valueobject.SummoningFee;

public class Order extends Entity {

    private OrderStatus status;
    private final SummoningFee summoningFee;

    public Order(String id, SummoningFee summoningFee) {
        super(id);
        this.summoningFee = summoningFee;
        this.status = OrderStatus.PLACED;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public SummoningFee getSummoningFee() {
        return this.summoningFee;
    }

    public void inProgress() {
        if (this.status != OrderStatus.PLACED) return;
        this.status = OrderStatus.IN_PROGRESS;
    }

    public void succeeded() {
        if (this.status != OrderStatus.IN_PROGRESS) return;
        this.status = OrderStatus.SUCCEEDED;
    }

    public void failed() {
        if (this.status != OrderStatus.IN_PROGRESS) return;
        this.status = OrderStatus.FAILED;
    }

}
