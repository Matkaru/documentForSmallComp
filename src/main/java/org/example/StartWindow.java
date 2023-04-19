package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends JFrame {

    public StartWindow(){

        setTitle("Panel Wyboru Sakdrew");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));

        JLabel naglowek = new JLabel("Wybierz czynność");
        naglowek.setHorizontalAlignment(JLabel.CENTER);

        JButton nowyDokument = new JButton("Nowy dokument");
        JButton dodajAsortyment = new JButton("Dodaj asortyment");
        JButton dodajOdbiorce = new JButton("Dodaj odbiorcę");
        JButton dokumenty = new JButton("Dokumenty");
        JButton wyjscie = new JButton("Wyjście");

        setLayout(new BorderLayout());

        add(naglowek, BorderLayout.NORTH);
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(nowyDokument);
        panel.add(dodajAsortyment);
        panel.add(dodajOdbiorce);
        panel.add(dokumenty);
        panel.add(wyjscie);
        add(panel, BorderLayout.CENTER);

        nowyDokument.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logika dla przycisku "Nowy dokument"
                JOptionPane.showMessageDialog(null, "Wybrano: Nowy dokument");
            }
        });

        dodajAsortyment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logika dla przycisku "Dodaj asortyment"
                JOptionPane.showMessageDialog(null, "Wybrano: Dodaj asortyment");
            }
        });

        dodajOdbiorce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logika dla przycisku "Dodaj odbiorcę"
                JOptionPane.showMessageDialog(null, "Wybrano: Dodaj odbiorcę");
            }
        });

        dokumenty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logika dla przycisku "Dokumenty"
                JOptionPane.showMessageDialog(null, "Wybrano: Dokumenty");
            }
        });

        wyjscie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logika dla przycisku "Wyjście"
                System.exit(0);
            }
        });
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
