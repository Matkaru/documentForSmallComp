package org.example.assortment;

public class Product {
    private long id;
    private String name;
    private double price;
    private String quantity;
    private Long vat;

    public Product() {
    }

//    public Product(long id, String name, double price, String quantity, long vat) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.quantity = quantity;
//        this.vat = vat;
//    }

    public long getId() {
        return id;
    }

    public void setItemCode(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setItemName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setItemPrice(double price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setItemUnit(String quantity) {
        this.quantity = quantity;
    }

    public long getVat() {
        return vat;
    }

//    public void setVat(long vat) {
//        this.vat = vat;
//    }


    @Override
    public String toString() {
        return
                id +
                        "," + name +
                        "," + price +
                        "," + quantity +
                        "," + vat;
    }

    public void setItemVat(long vat) {
        this.vat = vat;
    }
}

