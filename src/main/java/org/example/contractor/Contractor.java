package org.example.contractor;

import org.example.StartWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Contractor extends JFrame {

    private final JTextField newItemCompanyIdField;
    private final JTextField newItemCompanyNameField;
    private final JTextField newItemNipField;
    private final JTextField newItemRegonField;
    private final JTextField newItemAddressField;
    private final JTextField newItemEmailField;
    private final JTextField newItemPhoneNumberField;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private final String fileName = "src/main/resources/contractors_data.json";
    public static JTable contractorTable;

    public Contractor() throws IOException {

        setTitle("Kontrahenci");
        setSize(700, 800);
        String[] columnNames = {"Id", "Nazwa firmy", "NIP", "REGON", "Adres", "Email", "Nr telefonu"};
        TableModel model = new DefaultTableModel(columnNames, 0);

        contractorTable = new JTable(model);
        contractorTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contractorTable.getTableHeader().setReorderingAllowed(false);

        ContractorsMethod.loadContractorsFromFile();

        JScrollPane scrollPane = new JScrollPane(contractorTable);
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);


        newItemCompanyIdField = new JTextField();
        newItemCompanyNameField = new JTextField();
        newItemNipField = new JTextField();
        newItemRegonField = new JTextField();
        newItemAddressField = new JTextField();
        newItemEmailField = new JTextField();
        newItemPhoneNumberField = new JTextField();

        addButton = new JButton("Dodaj");
        editButton = new JButton("Edytuj");
        deleteButton = new JButton("Usuń");

        JPanel formPanel = new JPanel(new GridLayout(7, 2));
        formPanel.add(new JLabel("Id: "));
        formPanel.add(newItemCompanyIdField);
        formPanel.add(new JLabel("Nazwa firmy: "));
        formPanel.add(newItemCompanyNameField);
        formPanel.add(new JLabel("NIP: "));
        formPanel.add(newItemNipField);
        formPanel.add(new JLabel("REGON: "));
        formPanel.add(newItemRegonField);
        formPanel.add(new JLabel("Adres: "));
        formPanel.add(newItemAddressField);
        formPanel.add(new JLabel("Email: "));
        formPanel.add(newItemEmailField);
        formPanel.add(new JLabel("Nr telefonu: "));
        formPanel.add(newItemPhoneNumberField);

        // Dodanie przycisków do okienka
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
                              @Override
                              public void windowClosing(WindowEvent e) {
                                  TableModel model = contractorTable.getModel();

        StartWindow startWindow = new StartWindow();
        startWindow.setVisible(true);
        dispose();
                              }

                          }
        );
    }
}
