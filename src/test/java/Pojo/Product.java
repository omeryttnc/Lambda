package Pojo;

public class Product {
    public String name;
    public String price;
    public String quantity;
    public String total;

    public Product(String name, String price, String quantity, String total) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public Product(String price, String quantity, String total) {
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }
}
