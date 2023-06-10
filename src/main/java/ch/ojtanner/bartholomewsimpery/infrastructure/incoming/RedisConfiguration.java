package ch.ojtanner.bartholomewsimpery.infrastructure.incoming;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.JedisPubSub;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

@Configuration
public class RedisConfiguration {

    @Bean
    public JedisPooled jedisPooled() {
        JedisPooled jedisPooled = new JedisPooled();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        BiConsumer<String, String> sout = (channelName, message) -> System.out.printf("\n\nReceived message %s on channel %s\n\n", message, channelName);
        executorService.execute(() -> jedisPooled.subscribe(new PubSubHandler("order", sout), "order"));
        return jedisPooled;
    }

    static class PubSubHandler extends JedisPubSub {
        
        private final String channelName;
        private final BiConsumer<String, String> messageHandler;

        private PubSubHandler(String channelName, BiConsumer<String, String> messageHandler) {
            this.channelName = channelName;
            this.messageHandler = messageHandler;
        }

        public void onMessage(String channel, String message) {
            messageHandler.accept(channel, message);
        }

        public void onSubscribe(String channel, int subscribedChannels) {
            System.out.printf(
                    "channelName: %s method: %s channel: %s subscribedChannels: %d\n",
                    channelName, "onSubscribe", channel, subscribedChannels);
        }

        public void onUnsubscribe(String channel, int subscribedChannels) {
            System.out.printf(
                    "channelName: %s method: %s channel: %s subscribedChannels: %d\n",
                    channelName, "onUnsubscribe", channel, subscribedChannels);
        }

        public void onPUnsubscribe(String pattern, int subscribedChannels) {
            System.out.printf(
                    "channelName: %s method: %s patten: %s subscribedChannels: %d\n",
                    channelName, "onPUnsubscribe", pattern, subscribedChannels);
        }

        public void onPSubscribe(String pattern, int subscribedChannels) {
            System.out.printf(
                    "channelName: %s method: %s patten: %s subscribedChannels: %d\n",
                    channelName, "onPSubscribe", pattern, subscribedChannels);
        }

        public void onPong(String message) {
            System.out.printf("channelName: %s method: %s message: %s\n", channelName, "onPong", message);
        }
    }
}
