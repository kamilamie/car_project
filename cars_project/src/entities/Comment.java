package entities;


import java.sql.Timestamp;
import java.util.Date;

public class Comment {
    private  int id;
    private String text;
    private Timestamp date;
    private User user;
    private Car car;

    public Comment(int id, String text, Timestamp date, User user, Car car) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.user = user;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Timestamp getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }
}
