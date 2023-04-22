package org.example.assortment;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private Long id;
    private String name;
    private double price;
    private String quantity;

    public Product() {
    }

    public Product(long id, String name, double price, String quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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


    public JsonObject getProductAsJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("Kod", this.id)
                .add("Nazwa", this.name)
                .add("Cena", this.price)
                .add("Jednostka", this.quantity);
        return builder.build();
    }

    @Override
    public String toString() {
        return
                id +
                "," + name +
                "," + price +
                "," + quantity;
    }
}

