package org.example.assortment;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.StartWindow;
import org.example.enums.Unit;
import org.example.enums.Vat;
import org.example.jsonSave.SaveAssortment;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Assortment extends JFrame {


    private final JTextField newItemCodeField;
    private final JTextField newItemNameField;
    private final JTextField newItemPriceField;
    private final JComboBox<Unit> newItemUnitComboBox;
    private final JComboBox<Long> newItemVatComboBox;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private final String fileName = "src/main/resources/assortment_data.json";
    public static JTable assortmentTable;
    private boolean codeValid = true;
    private boolean priceValid = true;


    public Assortment() throws IOException {

        setTitle("Asortyment");
        setSize(700, 800);


        String[] columnNames = {"Kod", "Nazwa", "Cena", "Jednostka", "VAT"};
        TableModel model = new DefaultTableModel(columnNames, 0);
        assortmentTable = new JTable(model);
        assortmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        assortmentTable.getTableHeader().setReorderingAllowed(false);

        AssortmentMethod.loadAssortmentFromFile();

        JScrollPane scrollPane = new JScrollPane(assortmentTable);
        // Utworzenie listy asortymentu

        Long value0 = Vat.ZERO.getValue();
        Long value5 = Vat.FIVE.getValue();
        Long value8 = Vat.EIGHT.getValue();
        Long value23 = Vat.TWO_THREE.getValue();

        Long[] values = {value0, value5, value8, value23};


        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);


        newItemCodeField = new JTextField();
        newItemNameField = new JTextField();
        newItemPriceField = new JTextField();
        newItemUnitComboBox = new JComboBox<>(Unit.values());
        newItemVatComboBox = new JComboBox<>(values);


        // Utworzenie przycisków
        addButton = new JButton("Dodaj");
        editButton = new JButton("Edytuj");
        deleteButton = new JButton("Usuń");

        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.add(new JLabel("Kod: "));
        formPanel.add(newItemCodeField);
        formPanel.add(new JLabel("Nazwa: "));
        formPanel.add(newItemNameField);
        formPanel.add(new JLabel("Cena: "));
        formPanel.add(newItemPriceField);
        formPanel.add(new JLabel("Jednostka miary: "));
        formPanel.add(newItemUnitComboBox);
        formPanel.add(new JLabel("VAT"));
        formPanel.add(newItemVatComboBox);


        // Dodanie przycisków do okienka
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);


        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.SOUTH);


// poniżej dodajemy akcje na przyciskach
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                StartWindow startWindow = new StartWindow();
                startWindow.setVisible(true);
                dispose();
            }
        });

        addButton.addActionListener(e -> {

//            List<Long> codeList = new ArrayList();
//
//            JSONParser parser = new JSONParser();
//            JSONArray dane = null;
//            try {
//                dane = (JSONArray) parser.parse(new FileReader(fileName));
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            } catch (ParseException ex) {
//                throw new RuntimeException(ex);
//            }
//
//            // Dodanie wczytanych danych do tabeli
//            for (Object object : dane) {
//
//                JSONObject product = (JSONObject) object;
//                long id = (Long) product.get("Kod");
//                codeList.add(id);
//
//            }


            String code = newItemCodeField.getText();
            String name = newItemNameField.getText();
            String price = newItemPriceField.getText().replace(",", ".");
            String unit = Objects.requireNonNull(newItemUnitComboBox.getSelectedItem()).toString();
            String vat = Objects.requireNonNull(newItemVatComboBox.getSelectedItem()).toString();

            AssortmentMethod.setCodeList();
            List<Long> codeList = AssortmentMethod.getCodeList();


            if(codeList.contains(Long.parseLong(code))){
                JOptionPane.showMessageDialog(Assortment.this, "Podany kod już istnieje w bazie!", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Sprawdzenie, czy wszystkie pola są wypełnione
            if (code.isEmpty() || name.isEmpty() || price.isEmpty() || vat.isEmpty()) {
                JOptionPane.showMessageDialog(Assortment.this, "Wypełnij wszystkie pola!", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }
            codeValid = code.matches("[0-9]{4,}");
            priceValid = price.matches("[0-9]+(\\.[0-9]{1,2})?");

            if (!codeValid || !priceValid) {
                JOptionPane.showMessageDialog(Assortment.this, " Kod musi zawierać minimum 4 cyfry od 0 do 9 ! \n Cena musi zawierać cyfry i dwa miejsca po przecinku", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }

            long code2 = Long.parseLong(code);
            double price2 = Double.parseDouble(price);
            long vat2 = Long.parseLong(vat);

            // Dodanie nowego produktu do tabeli
            DefaultTableModel model1 = (DefaultTableModel) assortmentTable.getModel();
            model1.addRow(new Object[]{code2, name, price2, unit, vat2});

            // Wyczyszczenie pól formularza po dodaniu wpisu
            newItemCodeField.setText("");
            newItemNameField.setText("");
            newItemPriceField.setText("");
            newItemUnitComboBox.setSelectedIndex(0);
            newItemVatComboBox.setSelectedIndex(0);

            JOptionPane.showMessageDialog(Assortment.this, "Dodano nowy wpis do tabeli.", "Informacja", JOptionPane.INFORMATION_MESSAGE);
            SaveAssortment.saveAssortment();

        });


        editButton.addActionListener(e -> {
            int selectedRow = assortmentTable.getSelectedRow(); // Pobranie zaznaczonego wiersza
            if (selectedRow == -1) {
                // Sprawdzenie, czy wiersz został zaznaczony
                JOptionPane.showMessageDialog(Assortment.this, "Zaznacz wiersz do edycji.", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Pobranie danych z zaznaczonego wiersza
            long id = (long) assortmentTable.getValueAt(selectedRow, 0);
            String name = (String) assortmentTable.getValueAt(selectedRow, 1);
            double price = (double) assortmentTable.getValueAt(selectedRow, 2);
            String category = (String) assortmentTable.getValueAt(selectedRow, 3);
            long vat = (long) (assortmentTable.getValueAt(selectedRow, 4));
            // Wyświetlenie okna dialogowego z formularzem edycji danych
            EditDialog editDialog = new EditDialog(Assortment.this, id, name, category, price, vat);
            // Zakładamy, że mamy zdefiniowany własny dialog o nazwie EditDialog
            editDialog.setVisible(true);

            if (editDialog.isConfirmed()) {
                // Jeśli użytkownik potwierdzi zmiany, pobieramy zmodyfikowane dane z dialogu
                long editId = editDialog.getId();
                String editedName = editDialog.getName();
                String editedCategory = editDialog.getUnit();
                double editedPrice = editDialog.getPrice();
                long editedVat = Long.parseLong(editDialog.getVat());

                // Aktualizacja danych w tabeli
                DefaultTableModel model12 = (DefaultTableModel) assortmentTable.getModel();
                model12.setValueAt(editId, selectedRow, 0);
                model12.setValueAt(editedName, selectedRow, 1);
                model12.setValueAt(editedPrice, selectedRow, 2);
                model12.setValueAt(editedCategory, selectedRow, 3);
                model12.setValueAt(editedVat, selectedRow, 4);

                JOptionPane.showMessageDialog(Assortment.this, "Dane zostały zaktualizowane.", "Informacja", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = assortmentTable.getSelectedRow(); // Pobranie zaznaczonego wiersza
                if (selectedRow == -1) {
                    // Sprawdzenie, czy wiersz został zaznaczony
                    JOptionPane.showMessageDialog(Assortment.this, "Zaznacz wiersz do usunięcia.", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Wyświetlenie okna dialogowego z pytaniem o potwierdzenie usunięcia
                int confirm = JOptionPane.showConfirmDialog(Assortment.this, "Czy na pewno chcesz usunąć zaznaczony wpis?", "Potwierdzenie usunięcia", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Usunięcie zaznaczonego wiersza z tabeli
                    DefaultTableModel model = (DefaultTableModel) assortmentTable.getModel();
                    model.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(Assortment.this, "Wpis został usunięty.", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

    }
}
