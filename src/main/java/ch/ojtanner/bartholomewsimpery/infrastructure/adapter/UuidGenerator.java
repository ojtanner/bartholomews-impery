package ch.ojtanner.bartholomewsimpery.infrastructure.adapter;

import ch.ojtanner.bartholomewsimpery.infrastructure.port.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidGenerator implements IdGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
