package ch.ojtanner.bartholomewsimpery.orchestration.api.adapter;

import io.nats.client.Connection;
import io.nats.client.Nats;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NatsConnection {

    private final Connection connection;

    public NatsConnection() throws IOException, InterruptedException {
        this.connection = Nats.connect();
    }

    public Connection getConnection() {
        return connection;
    }
}
