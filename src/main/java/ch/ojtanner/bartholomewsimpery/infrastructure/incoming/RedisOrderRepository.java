package ch.ojtanner.bartholomewsimpery.infrastructure.incoming;

import ch.ojtanner.bartholomewsimpery.domain.entities.Order;
import ch.ojtanner.bartholomewsimpery.service.ports.outgoing.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisPooled;

import java.util.Optional;

@Repository
public class RedisOrderRepository implements OrderRepository {

    private final ObjectMapper objectMapper;
    private final JedisPooled jedisPooled;


    public RedisOrderRepository(
            ObjectMapper objectMapper,
            JedisPooled jedisPooled
    ) {
        this.objectMapper = objectMapper;
        this.jedisPooled = jedisPooled;
    }


    @Override
    public void save(Order order) {
        try {
            String key = order.getId();
            String value = objectMapper.writeValueAsString(order);
            jedisPooled.set(key, value);
        } catch (JsonProcessingException exception) {
            System.err.println("Could not serialze order");
            System.err.println(order
            );
            throw new RuntimeException("Could not serialize order");
        }
    }

    @Override
    public Optional<Order> findById(int id) {
        Optional<String> maybeOrderJson = Optional.ofNullable(jedisPooled.get(Integer.toString(id)));
        return maybeOrderJson.map(orderJson -> {
            try {
                return objectMapper.readValue(orderJson, Order.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
