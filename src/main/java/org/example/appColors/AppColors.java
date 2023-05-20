package org.example.appColors;

import javax.swing.*;
import java.awt.*;

public class AppColors {
    public static void setColors() {
        // Kolorystyka przycisków
        UIManager.put("Button.background", Color.GRAY);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.focus", Color.LIGHT_GRAY);

        // Kolorystyka tła okna
        UIManager.put("Panel.background", Color.DARK_GRAY);
        UIManager.put("Table.background", Color.DARK_GRAY);
        UIManager.put("Label.background", Color.DARK_GRAY);

        // Kolorystyka tekstu
        UIManager.put("Label.foreground", Color.WHITE);




    }
}
