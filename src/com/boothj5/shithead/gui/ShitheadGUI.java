package com.boothj5.shithead.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class ShitheadGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	public ShitheadGUI() {
       initGUI();
    }

	private void initGUI() {
        JMenuBar menu = new JMenuBar();

        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem exitShithead = new JMenuItem("Quit") ;
        exitShithead.setMnemonic(KeyEvent.VK_X);
        exitShithead.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
                ActionEvent.CTRL_MASK));
        exitShithead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                
            }
        });
        
        JMenu game = new JMenu("Game");

        JMenuItem newGame = new JMenuItem("New") ;
        newGame.setMnemonic(KeyEvent.VK_N);
        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                ActionEvent.CTRL_MASK));
        
        JMenuItem gameTime = new JMenuItem("Show game time");
        gameTime.setMnemonic(KeyEvent.VK_T);
        gameTime.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
                ActionEvent.CTRL_MASK));

        game.add(newGame);
        game.add(gameTime);
        
        file.add(game);
        file.addSeparator();
        file.add(exitShithead);
        
        menu.add(file);
        
        setJMenuBar(menu);
        
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(50, 60, 80, 30);
        quitButton.setToolTipText("Press to quit") ;
        quitButton.addActionListener(new ActionListener() {
            @Override
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