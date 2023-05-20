package org.example.windowIcon;

import org.example.assortment.EditDialog;

import javax.swing.*;

public class WindowIconSetter {
    public static void setWindowIcon(JFrame window, String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        window.setIconImage(icon.getImage());

    }

    public static void setWindowIcon(EditDialog editDialog, String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        editDialog.setIconImage(icon.getImage());
    }
}
