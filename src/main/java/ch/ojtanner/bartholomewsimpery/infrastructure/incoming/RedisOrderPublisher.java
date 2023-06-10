package ch.ojtanner.bartholomewsimpery.infrastructure.incoming;

import ch.ojtanner.bartholomewsimpery.domain.entities.Order;
import ch.ojtanner.bartholomewsimpery.service.ports.outgoing.OrderPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class RedisOrderPublisher implements OrderPublisher {
    @Override
    public void publish(Order order) {

    }
/*
    private final ObjectMapper objectMapper;
    private final Jedis jedis;

    public RedisOrderPublisher(
            ObjectMapper objectMapper,
            Jedis jedis
    ) {
        this.objectMapper = objectMapper;
        this.jedis = jedis;
    }

    @Override
    public void publish(Order order) {
        try {
            String orderJson = objectMapper.writeValueAsString(order);
            jedis.publish("order", orderJson);

        } catch (JsonProcessingException exception) {
            System.err.println("Could not serialze order");
            System.err.println(order
            );
            throw new RuntimeException("Could not serialize order");
        }
    }

 */
}
