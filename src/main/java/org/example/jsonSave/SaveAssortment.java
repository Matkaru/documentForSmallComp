package org.example.jsonSave;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.assortment.Assortment;
import org.example.assortment.Product;

import javax.swing.table.TableModel;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public  class SaveAssortment {

    public static void saveAssortment() {
        TableModel model = Assortment.assortmentTable.getModel();

      List<Product> productList = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {

            Product product = new Product();

            long itemCodeString = (long) model.getValueAt(i, 0);
            product.setItemCode(Long.parseLong(String.valueOf(itemCodeString)));
            product.setItemName((String) model.getValueAt(i, 1));
            double itemPriceString = (double) model.getValueAt(i, 2);
            product.setItemPrice(Double.parseDouble(String.valueOf(itemPriceString)));
            product.setItemUnit((String) model.getValueAt(i, 3));
            long itemVatString = (long) model.getValueAt(i, 4);
            product.setItemVat(Long.parseLong(String.valueOf(itemVatString)));

            productList.add(product);
        }
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Created object JsonGenerator
            JsonFactory jsonFactory = objectMapper.getFactory();
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(new FileWriter("src/main/resources/assortment_data.json"));

            // Started save Json File
            jsonGenerator.writeStartArray();
            for (Product product : productList) {
                // Saving a single object as JSON
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("Kod", product.getId());
                jsonGenerator.writeStringField("Nazwa", product.getName());
                jsonGenerator.writeNumberField("Cena", product.getPrice());
                jsonGenerator.writeStringField("Jednostka", product.getQuantity());
                jsonGenerator.writeNumberField("VAT", product.getVat());

                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();

            //End of recording
            jsonGenerator.flush();
            jsonGenerator.close();
        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
    }
}