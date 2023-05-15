package org.example.contractor;
import org.example.startWindow.StartWindow;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import org.example.startWindow.StartWindowButtonListener;

public class Contractor extends JFrame {


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


        JTextField newItemCompanyIdField = new JTextField();
        JTextField newItemCompanyNameField = new JTextField();
        JTextField newItemNipField = new JTextField();
        JTextField newItemRegonField = new JTextField();
        JTextField newItemAddressField = new JTextField();
        JTextField newItemEmailField = new JTextField();
        JTextField newItemPhoneNumberField = new JTextField();

        JButton addButton = new JButton("Dodaj");
        JButton editButton = new JButton("Edytuj");
        JButton deleteButton = new JButton("Usuń");

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
//                                  TableModel model = contractorTable.getModel();

        StartWindowButtonListener buttonListener = new StartWindowButtonListener();
        StartWindow startWindow = new StartWindow(buttonListener);
        startWindow.setVisible(true);
        dispose();
                              }

                          }
        );
    }
}
