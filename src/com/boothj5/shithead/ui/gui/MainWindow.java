package com.boothj5.shithead.ui.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.boothj5.shithead.game.ShitheadException;

import java.awt.BorderLayout;

public class MainWindow {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow window = new MainWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public MainWindow() throws ShitheadException {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() throws ShitheadException{
        frame = new JFrame();
        frame.setBounds(100, 100, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        DetailsPane detailsPane = new DetailsPane();
        frame.getContentPane().add(detailsPane, BorderLayout.CENTER);
    }

}
