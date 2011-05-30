package com.boothj5.shithead.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
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
    public MainWindow() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 964, 731);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        DetailsPane detailsPane = new DetailsPane();
        frame.getContentPane().add(detailsPane, BorderLayout.CENTER);
    }

}
