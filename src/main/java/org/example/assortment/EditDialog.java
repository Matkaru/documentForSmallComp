package org.example.assortment;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class EditDialog extends JDialog {

    private JTextField idFiled;
    private long idFiled2;
    private JTextField nameField;
    private JComboBox unitField;
    private JTextField priceField;
    private JComboBox vatFiled;
    private boolean confirmed;

    public EditDialog(JFrame parent,long id, String name, String unit, double price, long vat) {
        super(parent, "Edycja danych", true); // Ustawienie tytułu okna dialogowego i trybu modalnego
        setLayout(new GridLayout(6, 4));

        // Dodanie pól tekstowych do okna dialogowego
        idFiled = new JTextField(Long.toString(id));
        idFiled2 = Long.parseLong(idFiled.getText());
        nameField = new JTextField(name);
        unitField = new JComboBox<>(new String[]{"tysiąc szt", "szt", "kg", "l", "m"});
        priceField = new JTextField(Double.toString(price));
        vatFiled = new JComboBox(new Integer[]{0, 5, 8, 23});

        add(new JLabel("Kod"));
        add(idFiled);
        add(new JLabel("Nazwa:"));
        add(nameField);
        add(new JLabel("Jednostka:"));
        add(unitField);
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

    public String getUnit() {
        return Objects.requireNonNull(unitField.getSelectedItem()).toString();
    }

    public double getPrice() {
        try {
            return Double.parseDouble(priceField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public String getVat(){
        return Objects.requireNonNull(vatFiled.getSelectedItem()).toString();
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public long getId() {return Long.parseLong(idFiled.getText());}
}


