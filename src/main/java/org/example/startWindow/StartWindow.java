package org.example.startWindow;
import org.example.appConfig.WindowIconSetter;
import javax.swing.*;
import java.awt.*;
import static java.awt.Color.*;

public class StartWindow extends JFrame {

    //start window
    public StartWindow(StartWindowButtonListener buttonListener){
        super("DFSC SYSTEM");
        WindowIconSetter.setWindowIcon(this,"src/main/resources/dfsc syst.png");

        int widthScreen = Toolkit.getDefaultToolkit().getScreenSize().width;
        int heightScreen = Toolkit.getDefaultToolkit().getScreenSize().height;
        int frameWidth = (widthScreen/2);
        int frameHeight = (heightScreen/2);
        this.setPreferredSize(new Dimension(frameWidth,frameHeight));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(GRAY);

        //these are the buttons in our window - further on they are set to listen
        JButton asortyment = new JButton("Asortyment");
        JButton kontrahenci = new JButton("Kontrahenci");
        JButton dokumenty = new JButton("Dokumenty");
        JButton wyjscie = new JButton("Wyjście");

        asortyment.setIcon(new ImageIcon("src/main/java/org/example/startWindow/image/assortment.png"));
        kontrahenci.setIcon(new ImageIcon("src/main/java/org/example/startWindow/image/contractor.png"));
        dokumenty.setIcon(new ImageIcon("src/main/java/org/example/startWindow/image/document.png"));
        wyjscie.setIcon(new ImageIcon("src/main/java/org/example/startWindow/image/exit.png"));

        int buttonWidth = frameWidth / 3;
        int buttonHeight = frameHeight / 3;
        Dimension buttonSize = new Dimension(buttonWidth,buttonHeight);

        asortyment.setPreferredSize(buttonSize);
        kontrahenci.setPreferredSize(buttonSize);
        dokumenty.setPreferredSize(buttonSize);
        wyjscie.setPreferredSize(buttonSize);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;  // Left column
        constraints.gridy = 0;  // Top row
        constraints.anchor = GridBagConstraints.NORTHWEST;
        add(asortyment, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTHEAST;
        add(kontrahenci, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.SOUTHWEST;
        add(dokumenty, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.SOUTHEAST;
        add(wyjscie, constraints);

        //listening on buttons

        asortyment.addActionListener(buttonListener::onAsortymentButtonClicked);
        kontrahenci.addActionListener(buttonListener::onKontrahenciButtonClicked);
        dokumenty.addActionListener(e -> buttonListener.onDokumentyButtonClicked());
        wyjscie.addActionListener(e -> buttonListener.onWyjscieButtonClicked());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
