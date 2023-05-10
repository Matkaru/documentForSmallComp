package org.example.assortment;
import org.example.enums.Unit;
import org.example.enums.Vat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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

        AssortmentMethod.setCodeAndNameList();

        Long value0 = Vat.ZERO.getValue();
        Long value5 = Vat.FIVE.getValue();
        Long value8 = Vat.EIGHT.getValue();
        Long value23 = Vat.TWO_THREE.getValue();

        Long[] values = {value0, value5, value8, value23};

        // Dodanie pól tekstowych do okna dialogowego
        idFiled = new JTextField(Long.toString(id));
        idFiled2 = Long.parseLong(idFiled.getText());
        nameField = new JTextField(name);
        unitField = new JComboBox<>(Unit.values());
        priceField = new JTextField(Double.toString(price));
        vatFiled = new JComboBox(values);

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
        java.util.List<Long> editCodeList = AssortmentMethod.getCodeList();
        List<String> editNameList = AssortmentMethod.getNameList();

                System.out.println("przed " + editCodeList);
                System.out.println("przed " + editNameList);

                editCodeList.remove(getId());
                editNameList.remove(getName());

                System.out.println("po " + editCodeList);
                System.out.println("po " + editNameList);


                if (editCodeList.contains(getId())) {
                    JOptionPane.showMessageDialog(EditDialog.this, "Podany kod już istnieje w bazie!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (editNameList.contains(getName())) {
                    JOptionPane.showMessageDialog(EditDialog.this, "Podana nazwa już istnieje w bazie!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                confirmed = true;
//                editCodeList.add(idFiled2);
//                editNameList.add(nameField.getText());
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


