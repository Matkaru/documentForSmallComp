package org.example.contractor;
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

public class ContractorsMethod {
    private static final List<Long> idList = new ArrayList<>();
    private static final List<String> companyNameList = new ArrayList<>();
    public static String fileNameC = "src/main/resources/contractors_data.json";

    static List<String> jsonStr;

    static {
        try {
            jsonStr = new ArrayList<>(Files.readAllLines(Paths.get("src/main/resources/contractors_data.json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadContractorsFromFile() {


        try {
            File file = new File(fileNameC);
            if (!file.exists()) {
            // If the file does not exist, stop loading
                return;
            }
            if (jsonStr.isEmpty()) {
                DefaultTableModel modelC = (DefaultTableModel) Contractor.contractorTable.getModel();
                modelC.setRowCount(0);
            }
            // Loading data from a JSON file
            if (!jsonStr.isEmpty()) {
                JSONParser parser = new JSONParser();
                JSONArray daneC = (JSONArray) parser.parse(new FileReader(fileNameC));

            //Clearing the table before loading new data
                DefaultTableModel modelC = (DefaultTableModel) Contractor.contractorTable.getModel();
                modelC.setRowCount(0);

            // Adding the loaded data to the table
                for (Object object : daneC) {
                    JSONObject contractorsData = (JSONObject) object;
                    long id = (Long) contractorsData.get("Id");
                    String companyName = (String) contractorsData.get("Nazwa firmy");
                    long nip = (Long) contractorsData.get("NIP");
                    long regon = (Long) contractorsData.get("REGON");
                    String address = (String) contractorsData.get("Adres");
                    String email = (String) contractorsData.get("Email");
                    long phoneNumber = (Long) contractorsData.get("Nr telefonu");
                    modelC.addRow(new Object[]{id, companyName, nip, regon, address, email, phoneNumber});
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setIdAndCompanyNameList() {

        JSONParser parser = new JSONParser();
        JSONArray daneC;

        if (fileNameC.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) Contractor.contractorTable.getModel();
            model.setRowCount(0);
        }else{

            try {
                daneC = (JSONArray) parser.parse(new FileReader(fileNameC));
            } catch (IOException | ParseException ex) {
                throw new RuntimeException(ex);
            }
            // Loading codes into the list
            for (Object object : daneC) {
                JSONObject product = (JSONObject) object;
                long id = (Long) product.get("Id");
                String companyName = (String) product.get("Nazwa firmy");
                idList.add(id);
                companyNameList.add(companyName);
            }

        }
    }

    public static List<Long> getIdList() {
        return idList;
    }


    public static List<String> getCompanyNameList() {
        return companyNameList;
    }
}

