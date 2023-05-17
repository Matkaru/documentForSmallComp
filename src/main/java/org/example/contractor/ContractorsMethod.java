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

    public static void loadContractorsFromFile() throws IOException {

        List<String> jsonStr = new ArrayList<>(Files.readAllLines(Paths.get("src/main/resources/contractors_data.json")));
        try {
            File file = new File(fileNameC);
            if (!file.exists()) {
                // Jeśli plik nie istnieje, zakończ wczytywanie
                return;
            }
            if (jsonStr.isEmpty()) {
                DefaultTableModel modelC = (DefaultTableModel) Contractor.contractorTable.getModel();
                modelC.setRowCount(0);
            }
            // Wczytanie danych z pliku JSON
            if (!jsonStr.isEmpty()) {
                JSONParser parser = new JSONParser();
                JSONArray daneC = (JSONArray) parser.parse(new FileReader(fileNameC));
                // Wyczyszczenie tabeli przed wczytaniem nowych danych
                DefaultTableModel modelC = (DefaultTableModel) Contractor.contractorTable.getModel();
                modelC.setRowCount(0);
                // Dodanie wczytanych danych do tabeli
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
        if (!fileNameC.isEmpty()) {
            JSONParser parser = new JSONParser();
            JSONArray daneC = null;
            try {
                daneC = (JSONArray) parser.parse(new FileReader(fileNameC));
            } catch (IOException | ParseException ex) {
                throw new RuntimeException(ex);
            }
            //wczytywanie kodów do listy
            for (Object object : daneC) {
                JSONObject product = (JSONObject) object;
                long id = (Long) product.get("Id");
                String companyName = (String) product.get("Nazwa firmy");
                idList.add(id);
                companyNameList.add(companyName);
            }
            DefaultTableModel tableModel;
        }
    }

    public static List<Long> getIdList() {
        return idList;
    }


    public static List<String> getCompanyNameList() {
        return companyNameList;
    }
}

