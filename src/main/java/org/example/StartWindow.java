package org.example;
import org.example.assortment.Assortment;
import org.example.contractor.Contractor;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StartWindow extends JFrame {

    //start window
    public StartWindow(){
        //here we set the title and the default operation that will be performed after closing the window and the size of the frame
        setTitle("Panel Wyboru");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));

        //we set the header title and its position
        JLabel naglowek = new JLabel("Wybierz czynność");
        naglowek.setHorizontalAlignment(JLabel.CENTER);

        //these are the buttons in our window - further on they are set to listen
        JButton asortyment = new JButton("Asortyment");
        JButton kontrahenci = new JButton("Kontrahenci");
        JButton dokumenty = new JButton("Dokumenty");
        JButton wyjscie = new JButton("Wyjście");

        setLayout(new BorderLayout());//this is the layout manager - we'll use it later

        add(naglowek, BorderLayout.NORTH); // here we add the location of the header
        JPanel panel = new JPanel(new FlowLayout());

        panel.add(asortyment);
        panel.add(kontrahenci);
        panel.add(dokumenty);
        panel.add(wyjscie);
        add(panel, BorderLayout.CENTER);


        //listening on buttons
        asortyment.addActionListener(e -> {
            StartWindow.this.dispose();
            Assortment assortment;
            try {
                assortment = new Assortment();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            assortment.setVisible(true);
        });

        kontrahenci.addActionListener(e -> {
            StartWindow.this.dispose();

            Contractor contractor;
            try {
                contractor = new Contractor();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            contractor.setVisible(true);
        });

        dokumenty.addActionListener(e -> {
            //Logic for the "Documents" button
            JOptionPane.showMessageDialog(null, "Documents section selected");
        });

        wyjscie.addActionListener(e -> {
            // Logic for the "Exit" button
            System.exit(0);
        });
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
