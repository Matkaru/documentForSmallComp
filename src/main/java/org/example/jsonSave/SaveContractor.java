package org.example.jsonSave;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.contractor.Contractor;
import org.example.contractor.ContractorsData;

import javax.swing.table.TableModel;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveContractor {
    public static void saveContractor() {
        TableModel modelC = Contractor.contractorTable.getModel();

        List<ContractorsData> contractorsDataList = new ArrayList<>();
        for (int i = 0; i < modelC.getRowCount(); i++) {

            ContractorsData contractorsData = new ContractorsData();

            long itemIdString = (long) modelC.getValueAt(i, 0);
            contractorsData.setId(Long.parseLong(String.valueOf(itemIdString)));
            String companyName = (String) modelC.getValueAt(i,1);
            contractorsData.setCompanyName((String) modelC.getValueAt(i, 1));
            long itemNipString = (long) modelC.getValueAt(i, 2);
            contractorsData.setNip(Long.parseLong(String.valueOf(itemNipString)));
            // contractorsData.setRegon((Long) model.getValueAt(i, 3));
            long itemRegonString = (long) modelC.getValueAt(i, 3);
            contractorsData.setRegon(Long.parseLong(String.valueOf(itemRegonString)));
            contractorsData.setAddress((String) modelC.getValueAt(i, 4));
            contractorsData.setEmail((String) modelC.getValueAt(i, 5));
            long itemPhoneNumberString = (long) modelC.getValueAt(i, 6);
            contractorsData.setPhoneNumber(Long.parseLong(String.valueOf(itemPhoneNumberString)));


            contractorsDataList.add(contractorsData);
        }
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Utworzenie obiektu JsonGenerator
            JsonFactory jsonFactory = objectMapper.getFactory();
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(new FileWriter("src/main/resources/contractors_data.json"));

            // Rozpoczęcie zapisu do pliku JSON
            jsonGenerator.writeStartArray();
            for (ContractorsData contractorsData : contractorsDataList) {
                // Zapisanie pojedynczego obiektu jako JSON
                jsonGenerator.writeStartObject();

          jsonGenerator.writeNumberField("Id", contractorsData.getId());
          jsonGenerator.writeStringField("Nazwa firmy", contractorsData.getCompanyName());
          jsonGenerator.writeNumberField("NIP", contractorsData.getNip());
          jsonGenerator.writeNumberField("REGON", contractorsData.getRegon());
          jsonGenerator.writeStringField("Adres", contractorsData.getAddress());
          jsonGenerator.writeStringField("Email", contractorsData.getEmail());
          jsonGenerator.writeNumberField("Nr telefonu", contractorsData.getPhoneNumber());

                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();

            // Zakończenie zapisu
            jsonGenerator.flush();
            jsonGenerator.close();
        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
    }
}
