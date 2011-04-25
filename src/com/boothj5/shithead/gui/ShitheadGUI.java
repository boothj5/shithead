package com.boothj5.shithead.gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ShitheadGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	public ShitheadGUI() {
       setTitle("Simple example");
       setSize(800, 800);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ShitheadGUI ex = new ShitheadGUI();
                ex.setVisible(true);
            }
        });
    }
}