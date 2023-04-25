package org.example;

import org.example.assortment.Assortment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends JFrame {

//okno starotwe
    public StartWindow(){
//tutaj ustawiamy tytuł i domyślną operację, która wykona się po zamknięciu okienka oraz wielkość ramki
        setTitle("Panel Wyboru");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));

//ustawiamy tytuł nagłówka oraz jego położenie
        JLabel naglowek = new JLabel("Wybierz czynność");
        naglowek.setHorizontalAlignment(JLabel.CENTER);

//to są przyciski w naszym okienku - w dalszej części jest ustawione do nich nasłuchiwanie
        JButton asortyment = new JButton("Asortyment");
        JButton kontrahenci = new JButton("Kontrahenci");
        JButton dokumenty = new JButton("Dokumenty");
        JButton wyjscie = new JButton("Wyjście");

        setLayout(new BorderLayout());// to jest manager układu - w dalszej części będziemy z niego korzystać

        add(naglowek, BorderLayout.NORTH); // tu dodajemy położenie nagłówka
        JPanel panel = new JPanel(new FlowLayout());

        panel.add(asortyment);
        panel.add(kontrahenci);
        panel.add(dokumenty);
        panel.add(wyjscie);
        add(panel, BorderLayout.CENTER);

// poniżej są przedstawione akcje do nasłuchiwania reakcji na danym przycisku
        asortyment.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StartWindow.this.dispose();
                Assortment assortment = new Assortment();
                assortment.setVisible(true);
            }
        });

        kontrahenci.addActionListener(new ActionListener() {
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
        setVisible(true);// bez tego nie widać ramki
    }
}
