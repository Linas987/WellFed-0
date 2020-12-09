package HelpeClasses;

/**
 * This class defines the user object
 */
public class User {
    private int _id;
    private String username;
    private String password;
    private String email;
    private int weight;
    private int height;
    private int age;
    public User ( int _id, String username, String password, String email, int weight, int height, int age){
        this._id = _id;
        this.username=username;
        this.password=password;
        this.email=email;
        this.weight =weight;
        this.height =height;
        this.age=age;
    }
    /*public User ( String username, String password, String email, int weight, int height, int age){
        this.username=username;
        this.password=password;
        this.email=email;
        this.weight =weight;
        this.height =height;
        this.age=age;
    }*/
    @Override
    public String toString() {
        String rez = "Useris: username=" + getUsername()
                + "; id=" +get_id()
                + "; password=" + getHashedPassword()
                + "; email=" + getEmail()
                + "; weigth=" + getWeight()
                + "; heigth=" + getHeight()
                + "; age=" + getAge();
        return rez;
    }
    public int get_id() {
        return _id;
    }
    public void set_id(int _id) {
        this._id=_id;
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
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

}
