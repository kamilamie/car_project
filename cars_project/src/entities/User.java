package entities;

public class User {
    private int id;
    private String username;
    private String login;
    private String password;
    private int age;
    private int experience;
    private String photo_path;

    public User(int id, String username, String login, String password, int age, int experience, String photo_path) {
        this.id = id;
        this.username = username;
        this.login = login;
        this.password = password;
        this.age = age;
        this.experience = experience;
        this.photo_path = photo_path;
    }

    public String getPhoto_path() {
        return photo_path;
    }

    public int getExperience() {
        return experience;
    }

    public int getAge() {
        return age;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
