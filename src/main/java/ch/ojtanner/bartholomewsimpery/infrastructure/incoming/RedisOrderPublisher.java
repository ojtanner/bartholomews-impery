package ch.ojtanner.bartholomewsimpery.infrastructure.incoming;

import ch.ojtanner.bartholomewsimpery.domain.entities.Order;
import ch.ojtanner.bartholomewsimpery.service.ports.outgoing.OrderPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPooled;

@Component
public class RedisOrderPublisher implements OrderPublisher {

    private final ObjectMapper objectMapper;
    private final JedisPooled jedisPooled;

    public RedisOrderPublisher(
            ObjectMapper objectMapper,
            JedisPooled jedisPooled
    ) {
        this.objectMapper = objectMapper;
        this.jedisPooled = jedisPooled;
    }

    @Override
    public void publish(Order order) {
        try {
            String orderJson = objectMapper.writeValueAsString(order);
            jedisPooled.publish("order", orderJson);

        } catch (JsonProcessingException exception) {
            System.err.println("Could not serialze order");
            System.err.println(order
            );
            throw new RuntimeException("Could not serialize order");
        }
    }
}
