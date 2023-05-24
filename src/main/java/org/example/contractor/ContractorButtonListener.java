package org.example.contractor;
import org.example.jsonSave.SaveContractor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.example.contractor.Contractor.contractorTable;
import static org.example.contractor.Contractor.*;


public class ContractorButtonListener extends Component implements ActionListener {
    @Override

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton sourceButton) {
            if (sourceButton.getText().equals("Dodaj")) {
                try {
                    onAddButtonClicked();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (sourceButton.getText().equals("Edytuj")) {
                try {
                    onEditButtonClicked(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (sourceButton.getText().equals("Usuń")) {
                onDeleteButtonClicked();

            }
        }
    }

    private void onAddButtonClicked() throws IOException {
        ContractorsMethod.setIdAndCompanyNameList();
        List<Long> idList = ContractorsMethod.getIdList();
        List<String> companyNameList = ContractorsMethod.getCompanyNameList();


        String id = newItemCompanyIdField.getText();
        String companyName = newItemCompanyNameField.getText();
        String nip = newItemNipField.getText();
        String regon = newItemRegonField.getText();
        String address = newItemAddressField.getText();
        String email = newItemEmailField.getText();
        String phoneNumber = newItemPhoneNumberField.getText();

        if (id.isEmpty() || companyName.isEmpty() || nip.isEmpty() || regon.isEmpty() || address.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(ContractorButtonListener.this, "Wypełnij wszystkie pola!", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (idList.contains(Long.parseLong(id))) {
            JOptionPane.showMessageDialog(ContractorButtonListener.this, "Podany kod już istnieje w bazie!", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (companyNameList.contains(companyName)) {
            JOptionPane.showMessageDialog(ContractorButtonListener.this, "Podana nazwa już istnieje w bazie!", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Checking if all fields are filled in
        boolean idValid = id.matches("[0-9]{4,}");
        boolean nipValid = nip.matches("[0-9]{10,}");
        boolean regonValid = regon.matches("[0-9]{9,}");
        boolean emailValid = email.matches("[\\w]+@[\\w]+\\.[a-zA-Z]{2,3}");


        if (!idValid) {
            JOptionPane.showMessageDialog(ContractorButtonListener.this, " Id musi zawierać minimum 4 cyfry od 0 do 9 !", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!nipValid) {
            JOptionPane.showMessageDialog(ContractorButtonListener.this, "Nip musi zawierać 10 cyfr!", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!regonValid) {
            JOptionPane.showMessageDialog(ContractorButtonListener.this, "Regon musi zawierać 9 cyfr ", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!emailValid) {
            JOptionPane.showMessageDialog(ContractorButtonListener.this, "Email musi zawierać minimum 8 znaków, male i duze litery, liczbe oraz znak specjalny ", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }

        long id2 = Long.parseLong(id);
        long nip2 = Long.parseLong(nip);
        long regon2 = Long.parseLong(regon);
        long phoneNumber2 = Long.parseLong(phoneNumber);

        //Adding a new product to the table
        DefaultTableModel model1 = (DefaultTableModel) contractorTable.getModel();
        model1.addRow(new Object[]{id2, companyName, nip2, regon2, address, email, phoneNumber2});

        // Clearing form fields after adding an entry
        newItemCompanyIdField.setText("");
        newItemCompanyNameField.setText("");
        newItemNipField.setText("");
        newItemRegonField.setText("");
        newItemAddressField.setText("");
        newItemEmailField.setText("");
        newItemPhoneNumberField.setText("");

        JOptionPane.showMessageDialog(ContractorButtonListener.this, "Dodano nowy wpis do tabeli.", "Informacja", JOptionPane.INFORMATION_MESSAGE);
        Contractor.sortContractorTableByCode();
        SaveContractor.saveContractor();

    }

    private void onDeleteButtonClicked() {
        int selectedRow;

        selectedRow = contractorTable.getSelectedRow(); // Fetch the selected row
        if (selectedRow == -1) {
            //Checks if a row has been selected
            JOptionPane.showMessageDialog(ContractorButtonListener.this, "Zaznacz wiersz do usunięcia.", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Displays a dialog asking you to confirm the deletion
        int confirm = JOptionPane.showConfirmDialog(ContractorButtonListener.this, "Czy na pewno chcesz usunąć zaznaczony wpis?", "Potwierdzenie usunięcia", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            //Delete the selected row from the table
            DefaultTableModel model = (DefaultTableModel) contractorTable.getModel();
            model.removeRow(selectedRow);
            JOptionPane.showMessageDialog(ContractorButtonListener.this, "Wpis został usunięty.", "Informacja", JOptionPane.INFORMATION_MESSAGE);
            Contractor.sortContractorTableByCode();
            SaveContractor.saveContractor();
        }
    }

    void onEditButtonClicked(ActionEvent e) throws IOException {

        int selectedRow = contractorTable.getSelectedRow(); // Fetch the selected row
        if (selectedRow == -1) {
            // Checks if a row has been selected
            JOptionPane.showMessageDialog(ContractorButtonListener.this, "Zaznacz wiersz do edycji.", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieve data from the selected row
        long id = (long) contractorTable.getValueAt(selectedRow, 0);
        String companyName = (String) contractorTable.getValueAt(selectedRow, 1);
        long nip = (long) contractorTable.getValueAt(selectedRow, 2);
        long regon = (long) contractorTable.getValueAt(selectedRow, 3);
        String address = (String) contractorTable.getValueAt(selectedRow, 4);
        String email = (String) contractorTable.getValueAt(selectedRow, 5);
        long phoneNumber = (long) contractorTable.getValueAt(selectedRow, 6);

        EditDialogFromContractor editDialogFromContractor = new EditDialogFromContractor(ContractorButtonListener.this, id, companyName, nip, regon, address, email, phoneNumber);
        editDialogFromContractor.setVisible(true);

        Contractor contractor = (Contractor) SwingUtilities.getWindowAncestor((Component) e.getSource());
        contractor.setEnabled(false);

        editDialogFromContractor.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {

                if (editDialogFromContractor.isConfirmed()) {

                    List<String> nameList = new ArrayList<>(ContractorsMethod.getCompanyNameList());
                    HashSet<String> set = new HashSet<>(nameList);
                    nameList.clear();
                    nameList.addAll(set);
                    nameList.remove(companyName);
                    //If the user confirms the changes, we fetch the modified data from the dialog
                    long editId = editDialogFromContractor.getId();
                    String editedCompanyName = editDialogFromContractor.getCompanyName();
                    long editNip = editDialogFromContractor.getNip();
                    long editRegon = editDialogFromContractor.getRegon();
                    String editAddress = editDialogFromContractor.getAddress();
                    String editEmail = editDialogFromContractor.getEmail();
                    long editPhoneNumber = editDialogFromContractor.getPhoneNumber();
                    if (nameList.contains(editedCompanyName)) {
                        JOptionPane.showMessageDialog(ContractorButtonListener.this, "Podana nazwa już istnieje w bazie!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    } else {
                        //Updating data in the table
                        DefaultTableModel modelC2 = (DefaultTableModel) contractorTable.getModel();
                        modelC2.setValueAt(editId, selectedRow, 0);
                        modelC2.setValueAt(editedCompanyName, selectedRow, 1);
                        modelC2.setValueAt(editNip, selectedRow, 2);
                        modelC2.setValueAt(editRegon, selectedRow, 3);
                        modelC2.setValueAt(editAddress, selectedRow, 4);
                        modelC2.setValueAt(editEmail, selectedRow, 5);
                        modelC2.setValueAt(editPhoneNumber, selectedRow, 6);


                        JOptionPane.showMessageDialog(ContractorButtonListener.this, "Dane zostały zaktualizowane.", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                        nameList.clear();
                        Contractor.sortContractorTableByCode();
                        SaveContractor.saveContractor();
                    }
                }
            }            @Override
            public void windowClosing(WindowEvent e) {
                contractor.setEnabled(true);
            }
        });

    }
}