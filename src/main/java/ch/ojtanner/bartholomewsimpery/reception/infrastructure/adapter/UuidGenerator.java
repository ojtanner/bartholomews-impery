package ch.ojtanner.bartholomewsimpery.reception.infrastructure.adapter;

import ch.ojtanner.bartholomewsimpery.reception.infrastructure.port.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidGenerator implements IdGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
