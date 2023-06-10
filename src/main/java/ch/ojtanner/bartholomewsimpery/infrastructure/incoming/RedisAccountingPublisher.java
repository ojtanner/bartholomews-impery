package ch.ojtanner.bartholomewsimpery.infrastructure.incoming;

import ch.ojtanner.bartholomewsimpery.domain.valueobjects.SummoningFee;
import ch.ojtanner.bartholomewsimpery.service.ports.outgoing.AccountingPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class RedisAccountingPublisher implements AccountingPublisher {
    @Override
    public void publish(SummoningFee summoningFee) {

    }
/*
    private final ObjectMapper objectMapper;
    private final Jedis jedis;

    public RedisAccountingPublisher(
            ObjectMapper objectMapper,
            Jedis jedis
    ) {
        this.objectMapper = objectMapper;
        this.jedis = jedis;
    }

    @Override
    public void publish(SummoningFee summoningFee) {
        try {
            String summoningFeeJson = objectMapper.writeValueAsString(summoningFee);
            jedis.publish("accounting", summoningFeeJson);

        } catch (JsonProcessingException exception) {
            System.err.println("Could not serialze summoningFee");
            System.err.println(summoningFee);
            throw new RuntimeException("Could not serialize summoningFee");
        }
    }
 */
}
