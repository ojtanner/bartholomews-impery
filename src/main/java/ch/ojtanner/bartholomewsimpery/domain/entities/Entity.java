package ch.ojtanner.bartholomewsimpery.domain.entities;

import java.util.Objects;

public abstract class Entity {

    private final String id;

    public Entity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean equals(Entity e2) {
        return e2 != null && Objects.equals(this.id, e2.id);
    }
}
