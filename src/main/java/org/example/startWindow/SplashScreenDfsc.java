package org.example.startWindow;
import javax.swing.*;
import java.awt.*;

public class SplashScreenDfsc extends JWindow{
    public SplashScreenDfsc() {
        splashScreenWindow();
    }
    public static void splashScreenWindow() {
        JWindow splashScreen = new JWindow();
        setWindowTransparent(splashScreen);

        ImageIcon logoIcon = new ImageIcon("src/main/resources/dfsc syst.png");
        JLabel logoLabel = new JLabel(logoIcon);
        splashScreen.getContentPane().add(logoLabel, BorderLayout.CENTER);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 500;
        int height = 400;
        int x = (screenSize.width - width) / 2;
        int y = (screenSize.height - height) / 2;
        splashScreen.setBounds(x, y, width, height);

        splashScreen.setVisible(true);
        // Simulation of waiting for the application to load
        try {
            Thread.sleep(3000); //simulation of waiting for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        splashScreen.dispose();
        // Creating and displaying the main application window
        SwingUtilities.invokeLater(() -> {
            StartWindowButtonListener buttonListener = new StartWindowButtonListener();
            new StartWindow(buttonListener);
        });

    }
        private static void setWindowTransparent (JWindow window){
            window.setBackground(new Color(0, 0, 0, 0)); // Ustawienie przezroczystego tła
            window.isOpaque();
        }
}
