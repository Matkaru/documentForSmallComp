package org.example;
import org.example.startWindow.StartWindow;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import static org.example.assortment.Assortment.assortmentTable;
import static org.junit.jupiter.api.Assertions.*;
import org.example.startWindow.StartWindowButtonListener;

class StartWindowTest {
    @Test
    public void testStartWindowInitialization() {
        StartWindowButtonListener buttonListener = new StartWindowButtonListener();
        StartWindow startWindow = new StartWindow(buttonListener);

        assertEquals("Panel Wyboru", startWindow.getTitle());
        assertEquals(WindowConstants.EXIT_ON_CLOSE, startWindow.getDefaultCloseOperation());
        assertEquals(new Dimension(400, 300), startWindow.getPreferredSize());

        assertNotNull(startWindow.getContentPane());
        assertEquals(2, startWindow.getContentPane().getComponentCount());
        assertTrue(startWindow.getContentPane().getComponent(0) instanceof JLabel);
        assertTrue(startWindow.getContentPane().getComponent(1) instanceof JPanel);

        JPanel panel = (JPanel) startWindow.getContentPane().getComponent(1);
        assertEquals(4, panel.getComponentCount());
        assertTrue(panel.getComponent(0) instanceof JButton);
        assertTrue(panel.getComponent(1) instanceof JButton);
        assertTrue(panel.getComponent(2) instanceof JButton);
        assertTrue(panel.getComponent(3) instanceof JButton);
    }

    @Test
    public void testAsortymentButtonAction() {
        StartWindowButtonListener buttonListener = new StartWindowButtonListener();
        StartWindow startWindow = new StartWindow(buttonListener);
        JButton asortymentButton = (JButton) ((JPanel) startWindow.getContentPane().getComponent(1)).getComponent(0);
        asortymentButton.doClick();
        assertFalse(startWindow.isVisible());
        assertNotNull(assortmentTable);
        assertTrue(assortmentTable.isVisible());
    }
}