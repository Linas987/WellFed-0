package HelpeClasses;

/**
 * This class defines the product object
 */
public class Products {
    private int id;
    private String name;
    private double price;
    private int cal;
    //private String _id;

    public Products(/*String _id,*/ int id, String name, double price, int cal)
    {
        //this._id = _id;
        this.id=id;
        this.name=name;
        this.price= price;
        this.cal=cal;
    }
    @Override
    public String toString() {
        String rez = "Produktas: kodas=" + getid()
                + "; pav=" + getName()
                + "; kaina=" + getPrice()
                + "; kalorijos=" + getCal();
        return rez;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getid() {
        return id;
    }
    public void setid(int id) {
        this.id = id;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getCal() {
        return cal;
    }
    public void setCal(int cal) {
        this.cal = cal;
    }
}
