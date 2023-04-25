package org.example.assortment;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditDialog extends JDialog {

    private JTextField idFiled;
    private long idFiled2;
    private JTextField nameField;
    private JTextField categoryField;
    private JTextField priceField;
    private JTextField vatFiled;
    private boolean confirmed;

    public EditDialog(JFrame parent,long id, String name, String unit, double price, long vat) {
        super(parent, "Edycja danych", true); // Ustawienie tytułu okna dialogowego i trybu modalnego
        setLayout(new GridLayout(6, 4));

        // Dodanie pól tekstowych do okna dialogowego
        idFiled = new JTextField(Long.toString(id));
        idFiled2 = Long.parseLong(idFiled.getText());
        nameField = new JTextField(name);
        categoryField = new JTextField(unit);
        priceField = new JTextField(Double.toString(price));
        vatFiled = new JTextField(Long.toString(vat));

        add(new JLabel("Kod"));
        add(idFiled);
        add(new JLabel("Nazwa:"));
        add(nameField);
        add(new JLabel("Jednostka:"));
        add(categoryField);
        add(new JLabel("Cena:"));
        add(priceField);
        add(new JLabel("VAT"));
        add(vatFiled);

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

    private void add(long parseLong) {
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
    public String getVat(){return vatFiled.getText();}

    public boolean isConfirmed() {
        return confirmed;
    }

    public long getId() {return Long.parseLong(idFiled.getText());}
}


