package com.boothj5.shithead.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class ShitheadGUI {

	private static final long serialVersionUID = 1L;

    private static JPanel createMainPanel() {
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));
        mainpanel.setOpaque(true);
        mainpanel.setBackground(new Color(0, 0, 0));
        mainpanel.setPreferredSize(new Dimension(800, 600)); 

        JLabel title = new JLabel("Javahead") ;
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBackground(new Color(0,0,0));
        title.setForeground(new Color(255, 255, 255));
        title.setFont(new Font("Courier new", Font.PLAIN, 20));
        mainpanel.add(title);

        mainpanel.add(Box.createRigidArea(new Dimension(0,50)));        

        JLabel numPlayersLabel = new JLabel("Enter number of players:");
//        numPlayersLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        numPlayersLabel.setBackground(new Color(0,0,0));
        numPlayersLabel.setForeground(new Color(255, 255, 255));
        numPlayersLabel.setFont(new Font("Courier new", Font.PLAIN, 20));
        mainpanel.add(numPlayersLabel);
        
        return mainpanel;
    }


    private static JMenuBar createMenubar() {
        JMenuBar menubar = new JMenuBar();
        menubar.setPreferredSize(new Dimension(200, 20));

        JMenu filemenu = createFileMenu();
        JMenu aboutmenu = createAboutMenu();
        menubar.add(filemenu);
        menubar.add(aboutmenu);
        
        return menubar;
    }

    private static JMenu createFileMenu() {
        JMenu filemenu = new JMenu("File");
        filemenu.setMnemonic(KeyEvent.VK_F);

        JMenu gameSubmenu = createGameSubmenu();

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
        
        filemenu.add(gameSubmenu);
        filemenu.addSeparator();
        filemenu.add(exitShithead);
        return filemenu;
    }


    private static JMenu createGameSubmenu() {
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
        
        return game;
    }

    private static JMenu createAboutMenu() {
        JMenu aboutmenu = new JMenu("About");
        aboutmenu.setMnemonic(KeyEvent.VK_A);

        JMenuItem aboutGame = new JMenuItem("About");
        aboutGame.setMnemonic(KeyEvent.VK_A);

        JMenuItem helpGame = new JMenuItem("Help");
        helpGame.setMnemonic(KeyEvent.VK_H);
        
        aboutmenu.add(aboutGame);
        aboutmenu.addSeparator();
        aboutmenu.add(helpGame);

        return aboutmenu;
    }

    private static void initGUI() {
        JFrame mainframe = new JFrame("Javahead");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar menu = createMenubar();
        mainframe.setJMenuBar(menu);

        JPanel mainpanel = createMainPanel();
        
        mainframe.getContentPane().add(mainpanel);        
        mainframe.pack();
        mainframe.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initGUI();
            }
        });
    }
}