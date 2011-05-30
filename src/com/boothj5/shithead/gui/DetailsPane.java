package com.boothj5.shithead.gui;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import com.boothj5.shithead.game.ShitheadGame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DetailsPane extends JPanel {
    private JTextField numCards;
    private JTextField name1;

    /**
     * Create the panel.
     */
    public DetailsPane() {
        
        JLabel lblWelcomeToJavahead = new JLabel("Welcome to Javahead!");
        lblWelcomeToJavahead.setFont(new Font("Dialog", Font.BOLD, 22));
        
        JLabel lblEnterNumberOf = new JLabel("Enter number of cards each:");
        
        numCards = new JTextField();
        numCards.setColumns(10);
        
        JLabel lblEnterPlayer = new JLabel("Enter player 1 name:");
        
        name1 = new JTextField();
        name1.setColumns(10);
        
        JButton btnDeal = new JButton("Deal");

        btnDeal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                                        
                    ShitheadGame game ;
                    List<String> names = new ArrayList<String>(); 
                    names.add(name1.getText()) ;
                    names.add("Computer") ;
                    List<String> types = new ArrayList<String>(); 
                    types.add("h") ;
                    types.add("s") ;
                    int numCardsPerHand = Integer.parseInt(numCards.getText()) ;
                        game = new ShitheadGame(2, names, types, numCardsPerHand) ;
                    game.deal();
                    Component component = (Component) e.getSource();
                    JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                    GamePane gamePane = new GamePane(game) ;
                    JPanel contentPane = (JPanel) frame.getContentPane() ;
                    contentPane.removeAll() ;
    
                    contentPane.add(gamePane, BorderLayout.CENTER);
                    contentPane.revalidate(); 
                    contentPane.repaint();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(132)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(btnDeal)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblEnterNumberOf)
                                .addComponent(lblEnterPlayer))
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(name1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(numCards, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(325, Short.MAX_VALUE))
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap(271, Short.MAX_VALUE)
                    .addComponent(lblWelcomeToJavahead)
                    .addGap(244))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(66)
                    .addComponent(lblWelcomeToJavahead)
                    .addGap(97)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(numCards, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEnterNumberOf))
                    .addGap(60)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblEnterPlayer)
                        .addComponent(name1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(84)
                    .addComponent(btnDeal)
                    .addContainerGap(118, Short.MAX_VALUE))
        );
        setLayout(groupLayout);

    }
}
