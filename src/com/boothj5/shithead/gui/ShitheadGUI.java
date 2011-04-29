package com.boothj5.shithead.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

public class ShitheadGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private final ImageIcon quitImage = new ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/Stop16.gif"));
    private final ImageIcon quitImageLarge = new ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/Stop24.gif"));
    private final ImageIcon helpImage = new ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/Help16.gif"));
    private final ImageIcon helpImageLarge = new ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/Help24.gif"));
    private final ImageIcon aboutImage = new ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/About16.gif"));
    private final ImageIcon aboutImageLarge = new ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/About24.gif"));
	
	private JLabel statusbar ;
	private JPopupMenu popupmenu ;

	public ShitheadGUI() {
       initGUI();
    }

	private void initGUI() {
        JPanel panel = createPanel();
        getContentPane().add(panel);

        JMenuBar menu = createMainMenu();
        setJMenuBar(menu);
        createStatusBar();

        JButton quitButton = createQuitButton();	    
        panel.add(quitButton);

        JButton newButton = createNewButton();        
        
        panel.add(new JLabel("Label1"));
        panel.add(newButton);
        
        createPopupmenu();
        
        JToolBar toolbar = createToolbar();

        add(toolbar, BorderLayout.NORTH);        
        
        setTitle("Shithead GUI");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

    private JToolBar createToolbar() {
        JToolBar toolbar = new JToolBar();

        JButton exitButton = new JButton(quitImageLarge);
        JButton helpButton = new JButton(helpImageLarge);
        JButton aboutButton = new JButton(aboutImageLarge);
        exitButton.setToolTipText("Quit");
        helpButton.setToolTipText("Help");
        aboutButton.setToolTipText("About");
        toolbar.add(exitButton);
        toolbar.add(helpButton);
        toolbar.add(aboutButton);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }

        });
        return toolbar;
    }

    private void createPopupmenu() {
        popupmenu = new JPopupMenu();
        JMenuItem menuItemQuit = new JMenuItem("Quit", quitImage);         
        JMenuItem menuItemHelp = new JMenuItem("Help", helpImage);
        menuItemQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        popupmenu.add(menuItemHelp);
        popupmenu.add(menuItemQuit);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popupmenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 5, 5));
        return panel;
    }

    private JButton createQuitButton() {
        JButton quitButton = new JButton("Quit");
//        quitButton.setBounds(50, 60, 80, 30);
        quitButton.setToolTipText("Press to quit") ;
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        return quitButton;
    }

    private JButton createNewButton() {
        JButton newButton = new JButton("New");
//        newButton.setBounds(50, 60, 80, 30);
        newButton.setToolTipText("Start a new game") ;
        return newButton;
    }

    
    
    private void createStatusBar() {
        statusbar = new JLabel(" Statusbar");
        statusbar.setBorder(BorderFactory.createEtchedBorder(
                EtchedBorder.RAISED));
        add(statusbar, BorderLayout.SOUTH);
    }

    private JMenuBar createMainMenu() {
        JMenuBar menu = new JMenuBar();    
        
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenu view = new JMenu("View");
        view.setMnemonic(KeyEvent.VK_V);

        JMenu about = new JMenu("About");
        view.setMnemonic(KeyEvent.VK_A);
        
        JCheckBoxMenuItem showbar = new JCheckBoxMenuItem("StatusBar");
        showbar.setState(true);
        showbar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
              if (statusbar.isVisible()) {
                  statusbar.setVisible(false);
              } else {
                  statusbar.setVisible(true);
              }
            }

        });        
        
        JMenuItem exitShithead = new JMenuItem("Quit", quitImage) ;

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

        JMenuItem aboutGame = new JMenuItem("About", aboutImage);
        gameTime.setMnemonic(KeyEvent.VK_A);

        JMenuItem helpGame = new JMenuItem("Help", helpImage);
        gameTime.setMnemonic(KeyEvent.VK_H);
        
        game.add(newGame);
        game.add(gameTime);
        
        file.add(game);
        file.addSeparator();
        file.add(exitShithead);
        
        view.add(showbar);

        about.add(aboutGame);
        about.addSeparator();
        about.add(helpGame);
        
        menu.add(file);
        menu.add(view);
        menu.add(about);
        return menu;
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