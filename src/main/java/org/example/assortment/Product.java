package org.example.assortment;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Product {
    private long id;
    private String name;
    private double price;
    private String quantity;
    private Long vat;

    public Product() {
    }

    public Product(long id, String name, double price, String quantity, long vat) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.vat = vat;
    }

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

    public void setVat(long vat) {
        this.vat = vat;
    }

//    public JsonObject getProductAsJson() {
//        JsonObjectBuilder builder = Json.createObjectBuilder();
//        builder.add("Kod", this.id)
//                .add("Nazwa", this.name)
//                .add("Cena", this.price)
//                .add("Jednostka", this.quantity)
//                .add("VAT",this.vat);
//        return builder.build();
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

