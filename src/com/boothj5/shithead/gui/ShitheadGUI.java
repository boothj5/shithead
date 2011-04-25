package com.boothj5.shithead.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ShitheadGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	public ShitheadGUI() {
       initGUI();
    }

	private void initGUI() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(50, 60, 80, 30);
        quitButton.setToolTipText("Press to quit") ;
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });	    

        panel.add(quitButton);
        
        setTitle("Shithead GUI");
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