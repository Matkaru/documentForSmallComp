package org.example.assortment;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditDialog extends JDialog {
    private JTextField nameField;
    private JTextField categoryField;
    private JTextField priceField;
    private boolean confirmed;

    public EditDialog(JFrame parent, String name, String category, double price) {
        super(parent, "Edycja danych", true); // Ustawienie tytułu okna dialogowego i trybu modalnego
        setLayout(new GridLayout(4, 2));

        // Dodanie pól tekstowych do okna dialogowego
        nameField = new JTextField(name);
        categoryField = new JTextField(category);
        priceField = new JTextField(Double.toString(price));
        add(new JLabel("Nazwa:"));
        add(nameField);
        add(new JLabel("Kategoria:"));
        add(categoryField);
        add(new JLabel("Cena:"));
        add(priceField);

        // Dodanie przycisków do okna dialogowego
        JButton confirmButton = new JButton("Potwierdź");
        JButton cancelButton = new JButton("Anuluj");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                dispose(); // Zamknięcie okna dialogowego
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = false;
                dispose(); // Zamknięcie okna dialogowego
            }
        });
        add(confirmButton);
        add(cancelButton);

        setSize(300, 150);
        setLocationRelativeTo(parent);
    }

    public String getName() {
        return nameField.getText();
    }

    public String getCategory() {
        return categoryField.getText();
    }

    public double getPrice() {
        try {
            return Double.parseDouble(priceField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}


