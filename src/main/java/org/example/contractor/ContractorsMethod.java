package org.example.contractor;
import org.example.assortment.Assortment;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class ContractorsMethod {
    public static String fileName = "src/main/resources/contractors_data.json";
    public static void loadContractorsFromFile() throws IOException {
        // Files.readAllBytes(Paths.get(fileName)
        List<String> jsonStr = new ArrayList<>(Files.readAllLines(Paths.get("src/main/resources/contractors_data.json")));
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                // Jeśli plik nie istnieje, zakończ wczytywanie
                return;
            }
            if (jsonStr.isEmpty()) {
                DefaultTableModel model = (DefaultTableModel) Contractor.contractorTable.getModel();
                model.setRowCount(0);
            }
            // Wczytanie danych z pliku JSON
            if (!jsonStr.isEmpty()) {
                JSONParser parser = new JSONParser();
                JSONArray dane = (JSONArray) parser.parse(new FileReader(fileName));
                // Wyczyszczenie tabeli przed wczytaniem nowych danych
                DefaultTableModel model = (DefaultTableModel) Contractor.contractorTable.getModel();
                model.setRowCount(0);
                // Dodanie wczytanych danych do tabeli
                for (Object object : dane) {
                    JSONObject contractorsData = (JSONObject) object;
                    int id = (int) contractorsData.get("Id");
                    String companyName = (String) contractorsData.get("Nazwa firmy");
                    long nip = (long) contractorsData.get("NIP");
                    long regon = (long) contractorsData.get("REGON");
                    String  address = (String) contractorsData.get("Adres firmy");
                    String  email = (String) contractorsData.get("Adres email");
                    String  phoneNumber = (String) contractorsData.get("Nr telefonu");
                    model.addRow(new Object[]{id, companyName,nip,regon,address,email,phoneNumber});                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
