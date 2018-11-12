package entities;

public class CarClass {
    private int id;
    private String letter;
    private String name;

    public CarClass(int id, String letter, String name) {
        this.id = id;
        this.letter = letter;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getLetter() {
        return letter;
    }

    public String getName() {
        return name;
    }
}
