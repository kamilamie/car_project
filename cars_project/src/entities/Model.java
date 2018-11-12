package entities;

public class Model {
    private int id;
    private String name;
    private Producer producer;

    public Model(int id, String name, Producer producer) {
        this.id = id;
        this.name = name;
        this.producer = producer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Producer getProducer() {
        return producer;
    }
}
