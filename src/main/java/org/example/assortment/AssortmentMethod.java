package org.example.assortment;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import static org.example.assortment.Assortment.assortmentListModel;

public class AssortmentMethod {
    public static String fileName = "src/main/resources/assortment_data.json";

    public static void saveAssortmentToFile(List<Product> productList) {
        try {
//           create JsonObject
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (Product product : productList) {
                JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder()
                        .add("Kod", product.getId())
                        .add("Nazwa", product.getName())
                        .add("Cena", product.getPrice())
                        .add("Jednostka", product.getQuantity());
                jsonArrayBuilder.add(jsonObjectBuilder);
            }
            JsonArray jsonArray = jsonArrayBuilder.build();

            // Tworzenie obiektu JsonObject i dodawanie do niego tablicy
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("assortment", jsonArray)
                    .build();

            // Zapisanie danych do pliku
            FileWriter fileWriter = new FileWriter(fileName);
            JsonWriter jsonWriter = Json.createWriter(fileWriter);
            jsonWriter.writeObject(jsonObject);
            jsonWriter.close();
            fileWriter.close();

            System.out.println("Dane zapisane do pliku: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void loadAssortmentFromFile() {
        JSONParser parser = new JSONParser();
        try {
            // Otwieranie pliku JSON
            FileReader reader = new FileReader(fileName);
            // Parsowanie pliku JSON
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            // Pobieranie wartości z pliku JSON
            JSONArray items = (JSONArray) jsonObject.get("assortment");
            // Konwertowanie JSONArray na String[]
            for (Object item : items) {
                JSONObject jsonItem = (JSONObject) item;
                long id = (long) jsonItem.get("Kod");
                String name = (String) jsonItem.get("Nazwa");
                double price = (double) jsonItem.get("Cena");
                String quantity = (String) jsonItem.get("Jednostka");
                Product product = new Product(id, name, price, quantity);
                assortmentListModel.addElement(product);
            }
        } catch (IOException e) {
            System.err.println("Błąd odczytu pliku: " + e.getMessage());
        } catch (ParseException e) {
            System.err.println("Błąd parsowania pliku JSON: " + e.getMessage());
        }
    }


}


