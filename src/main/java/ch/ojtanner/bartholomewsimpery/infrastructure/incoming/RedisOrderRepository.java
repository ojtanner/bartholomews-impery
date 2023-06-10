package ch.ojtanner.bartholomewsimpery.infrastructure.incoming;

import ch.ojtanner.bartholomewsimpery.domain.entities.Order;
import ch.ojtanner.bartholomewsimpery.service.ports.outgoing.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.util.Optional;

@Repository
public class RedisOrderRepository implements OrderRepository {

    private final ObjectMapper objectMapper;
    private final Jedis jedis;


    public RedisOrderRepository(
            ObjectMapper objectMapper
    ) {
        this.objectMapper = objectMapper;
        this.jedis = new Jedis();
    }


    @Override
    public void save(Order order) {
        try {
            String key = Integer.toString(order.getId());
            String value = objectMapper.writeValueAsString(order);
            jedis.set(key, value);
        } catch (JsonProcessingException exception) {
            System.err.println("Could not serialze order");
            System.err.println(order
            );
            throw new RuntimeException("Could not serialize order");
        }
    }

    @Override
    public Optional<Order> findById(int id) {
        Optional<String> maybeOrderJson = Optional.ofNullable(jedis.get(Integer.toString(id)));
        return maybeOrderJson.map(orderJson -> {
            try {
                return objectMapper.readValue(orderJson, Order.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
