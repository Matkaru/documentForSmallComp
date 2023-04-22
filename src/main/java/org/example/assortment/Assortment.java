package org.example.assortment;

import org.example.StartWindow;

import javax.json.JsonObject;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Assortment extends JFrame {

    public static DefaultListModel<Product> assortmentListModel = new DefaultListModel<>();
    private final JTextField newItemCodeField;
    private final JTextField newItemNameField;
    private final JTextField newItemPriceField;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private final JComboBox<String> newItemUnitComboBox;
    private final String fileName = "src/main/resources/assortment_data.json";

    public Assortment() {

        setTitle("Asortyment");
        setSize(700, 800);

        String[] columnNames = {"Kod", "Nazwa", "Cena", "Jednostka"};
        TableModel model = new DefaultTableModel(columnNames,0);
        JTable assortmentTable = new JTable(model);
        assortmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        assortmentTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(assortmentTable);
        // Utworzenie listy asortymentu

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);

//        assortmentListModel = new DefaultListModel<>();
//        JList<Product> assortmentList = new JList<>(assortmentListModel);
//        add(new JScrollPane(assortmentList), BorderLayout.CENTER);

        AssortmentMethod.loadAssortmentFromFile();

        newItemCodeField = new JTextField();
        newItemNameField = new JTextField();
        newItemPriceField = new JTextField();
        newItemUnitComboBox = new JComboBox<>(new String[]{"tysiąc szt", "kg"});


        // Utworzenie przycisków
        addButton = new JButton("Dodaj");
        editButton = new JButton("Edytuj");
        deleteButton = new JButton("Usuń");

        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        formPanel.add(new JLabel("Kod: "));
        formPanel.add(newItemCodeField);
        formPanel.add(new JLabel("Nazwa: "));
        formPanel.add(newItemNameField);
        formPanel.add(new JLabel("Cena: "));
        formPanel.add(newItemPriceField);
        formPanel.add(new JLabel("Jednostka miary: "));
        formPanel.add(newItemUnitComboBox);


        // Dodanie przycisków do okienka
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);


        // Dodanie akcji do przycisków

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DefaultListModel<Product> model = (DefaultListModel<Product>) assortmentListModel;
                List<Product> productList = new ArrayList<>();
                for (int i = 0; i < model.getSize(); i++) {
                    Product product = model.getElementAt(i);
                    productList.add(product);
                }

                AssortmentMethod.saveAssortmentToFile(productList);
                StartWindow startWindow = new StartWindow();
                startWindow.setVisible(true);
                dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = newItemCodeField.getText();
                String name = newItemNameField.getText();
                String price = newItemPriceField.getText();
                String unit = newItemUnitComboBox.getSelectedItem().toString();

                // Sprawdzenie, czy wszystkie pola są wypełnione
                if (code.isEmpty() || name.isEmpty() || price.isEmpty()) {
                    JOptionPane.showMessageDialog(Assortment.this, "Wypełnij wszystkie pola!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Dodanie nowego produktu do tabeli
                DefaultTableModel model = (DefaultTableModel) assortmentTable.getModel();
                model.addRow(new Object[]{code, name, price, unit});

                // Wyczyszczenie pól formularza po dodaniu wpisu
                newItemCodeField.setText("");
                newItemNameField.setText("");
                newItemPriceField.setText("");
                newItemUnitComboBox.setSelectedIndex(0);

                JOptionPane.showMessageDialog(Assortment.this, "Dodano nowy wpis do tabeli.", "Informacja", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = assortmentTable.getSelectedRow(); // Pobranie zaznaczonego wiersza
                if (selectedRow == -1) {
                    // Sprawdzenie, czy wiersz został zaznaczony
                    JOptionPane.showMessageDialog(Assortment.this, "Zaznacz wiersz do edycji.", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Pobranie danych z zaznaczonego wiersza
                long id = (long) assortmentTable.getValueAt(selectedRow,0);
                String name = (String) assortmentTable.getValueAt(selectedRow, 1); // Zakładamy, że nazwa znajduje się w pierwszej kolumnie
                double price = (Double) assortmentTable.getValueAt(selectedRow, 2); // Zakładamy, że cena znajduje się w trzeciej kolumnie
                String category = (String) assortmentTable.getValueAt(selectedRow, 3); // Zakładamy, że kategoria znajduje się w drugiej kolumnie

                // Wyświetlenie okna dialogowego z formularzem edycji danych
                EditDialog editDialog = new EditDialog(Assortment.this, name, category, price); // Zakładamy, że mamy zdefiniowany własny dialog o nazwie EditDialog
                editDialog.setVisible(true);

                if (editDialog.isConfirmed()) {
                    // Jeśli użytkownik potwierdzi zmiany, pobieramy zmodyfikowane dane z dialogu
                    String editedName = editDialog.getName();
                    String editedCategory = editDialog.getCategory();
                    double editedPrice = editDialog.getPrice();

                    // Aktualizacja danych w tabeli
                    DefaultTableModel model = (DefaultTableModel) assortmentTable.getModel();
                    model.setValueAt(editedName, selectedRow, 0);
                    model.setValueAt(editedCategory, selectedRow, 1);
                    model.setValueAt(editedPrice, selectedRow, 2);

                    JOptionPane.showMessageDialog(Assortment.this, "Dane zostały zaktualizowane.", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                }
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
