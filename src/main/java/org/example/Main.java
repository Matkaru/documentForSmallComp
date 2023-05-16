package org.example;

import org.example.startWindow.StartWindow;
import org.example.startWindow.StartWindowButtonListener;

public class Main {
    public static void main(String[] args) {

        StartWindowButtonListener buttonListener = new StartWindowButtonListener();
        new StartWindow(buttonListener);
    }
}