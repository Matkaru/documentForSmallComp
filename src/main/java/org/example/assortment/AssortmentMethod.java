package org.example.assortment;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AssortmentMethod {
    private static final List<Long> codeList = new ArrayList<>();
    private static final List<String> nameList = new ArrayList<>();

    public static String fileName = "src/main/resources/assortment_data.json";

        static List<String> jsonStr;

    static {
        try {
            jsonStr = new ArrayList<>(Files.readAllLines(Paths.get("src/main/resources/assortment_data.json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadAssortmentFromFile() {


        try {
            File file = new File(fileName);
            if (!file.exists()) {
                // If the file does not exist, stop loading
                return;
            }
            if (jsonStr.isEmpty()) {
                DefaultTableModel model = (DefaultTableModel) Assortment.assortmentTable.getModel();
                model.setRowCount(0);
            }

            // Loading data from a JSON file
            if (!jsonStr.isEmpty()) {
                JSONParser parser = new JSONParser();
                JSONArray dane = (JSONArray) parser.parse(new FileReader(fileName));

                //Clearing the table before loading new data

                DefaultTableModel model = (DefaultTableModel) Assortment.assortmentTable.getModel();
                model.setRowCount(0);

                // Adding the loaded data to the table
                for (Object object : dane) {

                    JSONObject product = (JSONObject) object;
                    long id = (Long) product.get("Kod");
                    String name = (String) product.get("Nazwa");
                    double price = (double) product.get("Cena");
                    String unit = (String) product.get("Jednostka");
                    long vat = (Long) product.get("VAT");
                    model.addRow(new Object[]{id, name, price, unit, vat});
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static void setCodeAndNameList() {
        JSONParser parser = new JSONParser();
        JSONArray dane;

        if (jsonStr.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) Assortment.assortmentTable.getModel();
            model.setRowCount(0);
        } else {

            try {
                dane = (JSONArray) parser.parse(new FileReader(fileName));
            } catch (IOException | ParseException ex) {
                throw new RuntimeException(ex);
            }
//        loading codes into the list
            for (Object object : dane) {
                JSONObject product = (JSONObject) object;
                long id = (Long) product.get("Kod");
                String name = (String) product.get("Nazwa");
                codeList.add(id);
                nameList.add(name);
            }
        }
    }
    public static List<Long> getCodeList() {
        return codeList;
    }

    public static List<String> getNameList() {
        return nameList;
    }

}


