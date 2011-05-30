package com.boothj5.shithead.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.boothj5.shithead.card.Card;
import com.boothj5.shithead.game.ShitheadGame;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GamePane extends JPanel {

    /**
     * Create the panel.
     */
    public GamePane(ShitheadGame game) {
        super() ;
        
        JLabel lblWoop = new JLabel(game.getGameDetails().getPlayers().get(0).getName() + " VS Computer");
        lblWoop.setFont(new Font("Dialog", Font.BOLD, 22));
        
        JPanel player1 = new JPanel();
        player1.setBackground(new Color(143, 188, 143));
        
        JPanel computer = new JPanel();
        computer.setBackground(new Color(143, 188, 143));
        
        JLabel player1Name = new JLabel(game.getGameDetails().getPlayers().get(0).getName());
        
        JLabel computerName = new JLabel(game.getGameDetails().getPlayers().get(1).getName());
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGap(314)
                            .addComponent(lblWoop))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(player1, GroupLayout.PREFERRED_SIZE, 444, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(computer, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(34, Short.MAX_VALUE))
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(196)
                    .addComponent(player1Name)
                    .addPreferredGap(ComponentPlacement.RELATED, 665, Short.MAX_VALUE)
                    .addComponent(computerName)
                    .addGap(191))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(37)
                    .addComponent(lblWoop)
                    .addGap(330)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(computer, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                        .addComponent(player1, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(player1Name)
                        .addComponent(computerName))
                    .addGap(66))
        );
        GridBagLayout gbl_computer = new GridBagLayout();
        gbl_computer.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_computer.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_computer.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_computer.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        computer.setLayout(gbl_computer);
        
        Card p2h1 = game.getGameDetails().getPlayers().get(1).getHand().get(0) ;
        Card p2h2 = game.getGameDetails().getPlayers().get(1).getHand().get(1) ;
        Card p2h3 = game.getGameDetails().getPlayers().get(1).getHand().get(2) ;
        Card p2h4 = game.getGameDetails().getPlayers().get(1).getHand().get(3) ;
        Card p2f1 = game.getGameDetails().getPlayers().get(1).getFaceUp().get(0) ;
        Card p2f2 = game.getGameDetails().getPlayers().get(1).getFaceUp().get(1) ;
        Card p2f3 = game.getGameDetails().getPlayers().get(1).getFaceUp().get(2) ;
        Card p2f4 = game.getGameDetails().getPlayers().get(1).getFaceUp().get(3) ;
        
        JLabel player2Hand1 = new JLabel("");
        player2Hand1.setIcon(new ImageIcon(GamePane.class.getResource(p2h1.getImage())));
        GridBagConstraints gbc_player2Hand1 = new GridBagConstraints();
        gbc_player2Hand1.insets = new Insets(0, 0, 5, 5);
        gbc_player2Hand1.gridx = 2;
        gbc_player2Hand1.gridy = 1;
        computer.add(player2Hand1, gbc_player2Hand1);
        
        JLabel player2Hand2 = new JLabel("");
        player2Hand2.setIcon(new ImageIcon(GamePane.class.getResource(p2h2.getImage())));
        GridBagConstraints gbc_player2Hand2 = new GridBagConstraints();
        gbc_player2Hand2.insets = new Insets(0, 0, 5, 5);
        gbc_player2Hand2.gridx = 3;
        gbc_player2Hand2.gridy = 1;
        computer.add(player2Hand2, gbc_player2Hand2);
        
        JLabel player2Hand3 = new JLabel("");
        player2Hand3.setIcon(new ImageIcon(GamePane.class.getResource(p2h3.getImage())));
        GridBagConstraints gbc_player2Hand3 = new GridBagConstraints();
        gbc_player2Hand3.insets = new Insets(0, 0, 5, 5);
        gbc_player2Hand3.gridx = 4;
        gbc_player2Hand3.gridy = 1;
        computer.add(player2Hand3, gbc_player2Hand3);
        
        JLabel player2Hand4 = new JLabel("");
        player2Hand4.setIcon(new ImageIcon(GamePane.class.getResource(p2h4.getImage())));
        GridBagConstraints gbc_player2Hand4 = new GridBagConstraints();
        gbc_player2Hand4.insets = new Insets(0, 0, 5, 5);
        gbc_player2Hand4.gridx = 5;
        gbc_player2Hand4.gridy = 1;
        computer.add(player2Hand4, gbc_player2Hand4);
        
        JLabel player2Faceup1 = new JLabel("");
        player2Faceup1.setIcon(new ImageIcon(GamePane.class.getResource(p2f1.getImage())));
        GridBagConstraints gbc_player2Faceup1 = new GridBagConstraints();
        gbc_player2Faceup1.insets = new Insets(0, 0, 0, 5);
        gbc_player2Faceup1.gridx = 2;
        gbc_player2Faceup1.gridy = 3;
        computer.add(player2Faceup1, gbc_player2Faceup1);
        
        JLabel player2Faceup2 = new JLabel("");
        player2Faceup2.setIcon(new ImageIcon(GamePane.class.getResource(p2f2.getImage())));
        GridBagConstraints gbc_player2Faceup2 = new GridBagConstraints();
        gbc_player2Faceup2.insets = new Insets(0, 0, 0, 5);
        gbc_player2Faceup2.gridx = 3;
        gbc_player2Faceup2.gridy = 3;
        computer.add(player2Faceup2, gbc_player2Faceup2);
        
        JLabel player2Faceup3 = new JLabel("");
        player2Faceup3.setIcon(new ImageIcon(GamePane.class.getResource(p2f3.getImage())));
        GridBagConstraints gbc_player2Faceup3 = new GridBagConstraints();
        gbc_player2Faceup3.insets = new Insets(0, 0, 0, 5);
        gbc_player2Faceup3.gridx = 4;
        gbc_player2Faceup3.gridy = 3;
        computer.add(player2Faceup3, gbc_player2Faceup3);
        
        JLabel player2Faceup4 = new JLabel("");
        player2Faceup4.setIcon(new ImageIcon(GamePane.class.getResource(p2f4.getImage())));
        GridBagConstraints gbc_player2Faceup4 = new GridBagConstraints();
        gbc_player2Faceup4.insets = new Insets(0, 0, 0, 5);
        gbc_player2Faceup4.gridx = 5;
        gbc_player2Faceup4.gridy = 3;
        computer.add(player2Faceup4, gbc_player2Faceup4);
        GridBagLayout gbl_player1 = new GridBagLayout();
        gbl_player1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_player1.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_player1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_player1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        player1.setLayout(gbl_player1);
        
        Card p1h1 = game.getGameDetails().getPlayers().get(0).getHand().get(0) ;
        Card p1h2 = game.getGameDetails().getPlayers().get(0).getHand().get(1) ;
        Card p1h3 = game.getGameDetails().getPlayers().get(0).getHand().get(2) ;
        Card p1h4 = game.getGameDetails().getPlayers().get(0).getHand().get(3) ;
        Card p1f1 = game.getGameDetails().getPlayers().get(0).getFaceUp().get(0) ;
        Card p1f2 = game.getGameDetails().getPlayers().get(0).getFaceUp().get(1) ;
        Card p1f3 = game.getGameDetails().getPlayers().get(0).getFaceUp().get(2) ;
        Card p1f4 = game.getGameDetails().getPlayers().get(0).getFaceUp().get(3) ;
        
        JLabel player1Hand1 = new JLabel("");
        player1Hand1.setIcon(new ImageIcon(GamePane.class.getResource(p1h1.getImage())));
        GridBagConstraints gbc_player1Hand1 = new GridBagConstraints();
        gbc_player1Hand1.insets = new Insets(0, 0, 5, 5);
        gbc_player1Hand1.gridx = 2;
        gbc_player1Hand1.gridy = 1;
        player1.add(player1Hand1, gbc_player1Hand1);
        
        JLabel player1Hand2 = new JLabel("");
        player1Hand2.setIcon(new ImageIcon(GamePane.class.getResource(p1h2.getImage())));
        GridBagConstraints gbc_player1Hand2 = new GridBagConstraints();
        gbc_player1Hand2.insets = new Insets(0, 0, 5, 5);
        gbc_player1Hand2.gridx = 3;
        gbc_player1Hand2.gridy = 1;
        player1.add(player1Hand2, gbc_player1Hand2);
        
        JLabel player1Hand3 = new JLabel("");
        player1Hand3.setIcon(new ImageIcon(GamePane.class.getResource(p1h3.getImage())));
        GridBagConstraints gbc_player1Hand3 = new GridBagConstraints();
        gbc_player1Hand3.insets = new Insets(0, 0, 5, 5);
        gbc_player1Hand3.gridx = 4;
        gbc_player1Hand3.gridy = 1;
        player1.add(player1Hand3, gbc_player1Hand3);
        
        JLabel player1Hand4 = new JLabel("");
        player1Hand4.setIcon(new ImageIcon(GamePane.class.getResource(p1h4.getImage())));
        GridBagConstraints gbc_player1Hand4 = new GridBagConstraints();
        gbc_player1Hand4.insets = new Insets(0, 0, 5, 5);
        gbc_player1Hand4.gridx = 5;
        gbc_player1Hand4.gridy = 1;
        player1.add(player1Hand4, gbc_player1Hand4);
        
        JLabel player1Faceup1 = new JLabel("");
        player1Faceup1.setIcon(new ImageIcon(GamePane.class.getResource(p1f1.getImage())));
        GridBagConstraints gbc_player1Faceup1 = new GridBagConstraints();
        gbc_player1Faceup1.insets = new Insets(0, 0, 0, 5);
        gbc_player1Faceup1.gridx = 2;
        gbc_player1Faceup1.gridy = 3;
        player1.add(player1Faceup1, gbc_player1Faceup1);
        
        JLabel player1Faceup2 = new JLabel("");
        player1Faceup2.setIcon(new ImageIcon(GamePane.class.getResource(p1f2.getImage())));
        GridBagConstraints gbc_player1Faceup2 = new GridBagConstraints();
        gbc_player1Faceup2.insets = new Insets(0, 0, 0, 5);
        gbc_player1Faceup2.gridx = 3;
        gbc_player1Faceup2.gridy = 3;
        player1.add(player1Faceup2, gbc_player1Faceup2);
        
        JLabel player1Faceup3 = new JLabel("");
        player1Faceup3.setIcon(new ImageIcon(GamePane.class.getResource(p1f3.getImage())));
        GridBagConstraints gbc_player1Faceup3 = new GridBagConstraints();
        gbc_player1Faceup3.insets = new Insets(0, 0, 0, 5);
        gbc_player1Faceup3.gridx = 4;
        gbc_player1Faceup3.gridy = 3;
        player1.add(player1Faceup3, gbc_player1Faceup3);
        
        JLabel player1Faceup4 = new JLabel("");
        player1Faceup4.setIcon(new ImageIcon(GamePane.class.getResource(p1f4.getImage())));
        GridBagConstraints gbc_player1Faceup4 = new GridBagConstraints();
        gbc_player1Faceup4.insets = new Insets(0, 0, 0, 5);
        gbc_player1Faceup4.gridx = 5;
        gbc_player1Faceup4.gridy = 3;
        player1.add(player1Faceup4, gbc_player1Faceup4);
        setLayout(groupLayout);

    }
}
