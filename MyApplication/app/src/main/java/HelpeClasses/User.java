package HelpeClasses;

/**
 * This class defines the user object
 */
public class User {
    private String username;
    private String password;
    private String email;
    private int weigth;
    private int heigth;
    private int age;
    public User (String username, String password, String email, int weigth, int heigth, int age){

        this.username=username;
        this.password=password;
        this.email=email;
        this.weigth=weigth;
        this.heigth=heigth;
        this.age=age;
    }
    @Override
    public String toString() {
        String rez = "Useris: username=" + getUsername()
                + "; password=" + getHashedPassword()
                + "; email=" + getEmail()
                + "; weigth=" + getWeigth()
                + "; heigth=" + getHeigth()
                + "; age=" + getAge();
        return rez;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getHashedPassword() {
        return password;
    }
    public void setHashedPassword(String password) {
        this.password = password;
    }
    public int getWeigth() {
        return weigth;
    }
    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }
    public int getHeigth() {
        return heigth;
    }
    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
