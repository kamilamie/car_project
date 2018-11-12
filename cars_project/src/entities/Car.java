package entities;

public class Car {
    private int id;
    private String color;
    private int horsepower;
    private int seats;
    private int price;
    private String transmission;
    private Model model;
    private CarClass carClass;
    private String description;
    private String photo_path;


    public Car(int id, String color, int horsepower, int seats, int price, String transmission, Model model, CarClass carClass, String description, String photo_path) {
        this.id = id;
        this.color = color;
        this.horsepower = horsepower;
        this.seats = seats;
        this.price = price;
        this.transmission = transmission;
        this.model = model;
        this.carClass = carClass;
        this.description = description;
        this.photo_path = photo_path;
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public int getSeats() {
        return seats;
    }

    public int getPrice() {
        return price;
    }

    public String getTransmission() {
        return transmission;
    }

    public Model getModel() {
        return model;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoto_path() {
        return photo_path;
    }
}
