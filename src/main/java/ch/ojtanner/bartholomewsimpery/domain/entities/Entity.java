package ch.ojtanner.bartholomewsimpery.domain.entities;

public abstract class Entity {

    private int id;

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean equals(Entity e2) {
        return e2 != null && this.id == e2.id;
    }
}
