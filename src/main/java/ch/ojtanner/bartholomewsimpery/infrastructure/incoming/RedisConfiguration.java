package ch.ojtanner.bartholomewsimpery.infrastructure.incoming;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

//@Configuration
public class RedisConfiguration {

    @Bean
    public Jedis jedis() {
        Jedis jedis = new Jedis();
        JedisPubSub jedisPubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.printf("Received message %s from channel %s%n", message, channel);
            }
        };

        jedis.subscribe(jedisPubSub, "accounting");

        return jedis;
    }
}
