package ch.ojtanner.bartholomewsimpery.infrastructure.adapter;

import ch.ojtanner.bartholomewsimpery.domain.valueobjects.SummoningFee;
import ch.ojtanner.bartholomewsimpery.infrastructure.port.AccountingPublisher;
import org.springframework.stereotype.Component;

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
