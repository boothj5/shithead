package com.boothj5.shithead.ui.gui;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JButton;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.player.PlayerFactory;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DetailsPane extends JPanel implements ActionListener {
    private JTextField playerName;
    private JComboBox compType;
    private JTextArea compDescription ;
    private String[] compTypesChoice = {};

    /**
     * Create the panel.
     */
    public DetailsPane() throws ShitheadException {
        
        // Heading
        JLabel welcomeLabel = new JLabel("Welcome to Javahead!");
        welcomeLabel.setFont(new Font("Dialog", Font.BOLD, 22));
        
        JLabel playerNameLabel = new JLabel("Player name: ");
        playerName = new JTextField();
        playerName.setColumns(10);
        
        JLabel computerLabel = new JLabel("Computer type: ");
        
        Set<String> set = PlayerFactory.computerPlayerList().keySet() ;
        compTypesChoice = set.toArray(compTypesChoice) ;
                
        compType = new JComboBox(compTypesChoice);
        compType.setSelectedItem("SimplePlayer") ;
        compType.setActionCommand("selectComp") ;
        compType.addActionListener(this) ;

        JLabel compTacticsLabel = new JLabel("AI tactics: ") ;
        compDescription = new JTextArea(PlayerFactory.computerPlayerDescriptions().get(compType.getSelectedItem())) ;
        compDescription.setColumns(20) ;
        compDescription.setRows(5) ;
        compDescription.setLineWrap(true) ;
        compDescription.setWrapStyleWord(true) ;
        compDescription.setEditable(false) ;
        
        JPanel entryPanel = new JPanel(new GridBagLayout()) ;
        entryPanel.setBorder(BorderFactory.createTitledBorder("Enter game details")) ;
        
        GridBagConstraints labelConstraints = new GridBagConstraints() ;
        labelConstraints.gridx = 0 ;
        labelConstraints.anchor = GridBagConstraints.LINE_END ;
        labelConstraints.insets = new Insets(10,10,10,10);

        labelConstraints.gridy = 0 ;
        entryPanel.add(playerNameLabel, labelConstraints) ;
        labelConstraints.gridy = 1 ;
        entryPanel.add(computerLabel, labelConstraints) ;
        labelConstraints.gridy = 2 ;
        entryPanel.add(compTacticsLabel, labelConstraints) ;
        
        GridBagConstraints fieldConstraints = new GridBagConstraints() ;
        fieldConstraints.gridx = 1 ;
        fieldConstraints.anchor = GridBagConstraints.LINE_START ;
        fieldConstraints.insets = new Insets(10,10,10,10);

        fieldConstraints.gridy = 0 ;
        entryPanel.add(playerName, fieldConstraints) ;
        fieldConstraints.gridy = 1 ;
        entryPanel.add(compType, fieldConstraints) ;
        fieldConstraints.gridy = 2 ;
        entryPanel.add(compDescription, fieldConstraints) ;
        
        // deal (start) button
        JButton dealButton = new JButton("Deal");
        dealButton.setActionCommand("deal") ;
        dealButton.addActionListener(this) ;
        
        // complete layout
        setLayout(new GridBagLayout()) ;
        
        GridBagConstraints wc = new GridBagConstraints() ;
        wc.ipady = 20 ;
        wc.weightx = 1.0 ; 
        wc.weighty = 1.0 ; 
        wc.gridx = 0 ;
        wc.gridy = 0 ;
        wc.anchor = GridBagConstraints.PAGE_START ;
        add(welcomeLabel, wc) ;
        
        GridBagConstraints pc = new GridBagConstraints() ;
        pc.weightx = 1.0 ; 
        pc.weighty = 1.0 ; 
        pc.fill = GridBagConstraints.BOTH ;
        pc.gridx = 0 ;
        pc.gridy = 1 ;
        pc.anchor = GridBagConstraints.PAGE_START ;
        add(entryPanel, pc) ;
        
        
        GridBagConstraints dc = new GridBagConstraints() ;
        dc.weightx = 1.0 ; 
        dc.weighty = 1.0 ; 
        dc.gridx = 0 ;
        dc.gridy = 2 ;
        dc.anchor = GridBagConstraints.PAGE_START ;
        add(dealButton, dc) ;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("deal".equals(e.getActionCommand())) {
            try {
                ShitheadGame game ;
                List<String> names = new ArrayList<String>(); 
                names.add(playerName.getText()) ;
                names.add("Computer") ;
                List<String> types = new ArrayList<String>(); 
                types.add("h") ;
                types.add(PlayerFactory.computerPlayerList().get(compType.getSelectedItem())) ;
                int numCardsPerHand = 4 ;
                game = new ShitheadGame(2, names, types, numCardsPerHand) ;
                game.deal();
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setBounds(100, 100, 920, 800) ;
                GamePane gamePane = new GamePane(game) ;
                JPanel contentPane = (JPanel) frame.getContentPane() ;
                contentPane.removeAll() ;
                contentPane.add(gamePane, BorderLayout.CENTER);
                contentPane.revalidate(); 
                contentPane.repaint();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            
        }
        if (("selectComp").equals(e.getActionCommand())) {
            try {
                compDescription.setText(PlayerFactory.computerPlayerDescriptions().get(compType.getSelectedItem())) ;
            } catch (ShitheadException she) {
                compDescription.setText("ERROR!!!" + she.getMessage()) ;
            }
        }
    }
}
