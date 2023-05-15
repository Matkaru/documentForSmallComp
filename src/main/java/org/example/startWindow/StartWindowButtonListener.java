package org.example.startWindow;
import org.example.assortment.Assortment;
import org.example.contractor.Contractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;



public class StartWindowButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton sourceButton) {
            if (sourceButton.getText().equals("Asortyment")) {
                onAsortymentButtonClicked(e);
            } else if (sourceButton.getText().equals("Kontrahenci")) {
                onKontrahenciButtonClicked(e);
            } else if (sourceButton.getText().equals("Dokumenty")) {
                onDokumentyButtonClicked();
            } else if (sourceButton.getText().equals("Wyjście")) {
                onWyjscieButtonClicked();
            }
        }
    }

    public void onAsortymentButtonClicked(ActionEvent e) {
        StartWindow startWindow = (StartWindow) SwingUtilities.getWindowAncestor((Component)e.getSource());
        startWindow.dispose();
        Assortment assortment;
        try {
            assortment = new Assortment();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        assortment.setVisible(true);
    }
    public void onKontrahenciButtonClicked(ActionEvent e) {
        StartWindow startWindow = (StartWindow) SwingUtilities.getWindowAncestor((Component) e.getSource());
        startWindow.dispose();
        Contractor contractor;
        try {
            contractor = new Contractor();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        contractor.setVisible(true);
    }

    public void onDokumentyButtonClicked() {
        JOptionPane.showMessageDialog(null, "Documents section selected");
    }
    public void onWyjscieButtonClicked() {
        System.exit(0);

}



}

