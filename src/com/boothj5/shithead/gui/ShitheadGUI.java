package com.boothj5.shithead.gui;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ShitheadGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel jLabel ;
    private JTextField jTextField ;
    private JButton jButton ;
    
    public ShitheadGUI()
    {
       super();
       this.setSize(300, 200);
       this.getContentPane().setLayout(null);
       this.add(getJLabel(), null);
       this.add(getJTextField(), null);
       this.add(getJButton(), null);
       this.setTitle("JavaHead");
    }

    private JLabel getJLabel() {
       if(jLabel == null) {
          jLabel = new JLabel();
          jLabel.setBounds(34, 49, 53, 18);
          jLabel.setText("Players");
       }
       return jLabel;
    }

    private JTextField getJTextField() {
       if(jTextField == null) {
          jTextField = new JTextField();
          jTextField.setBounds(96, 49, 160, 20);
       }
       return jTextField;
    }

    private javax.swing.JButton getJButton() {
       if(jButton == null) {
          jButton = new JButton();
          jButton.setBounds(103, 110, 71, 27);
          jButton.setText("OK");
       }
       return jButton;
    }

}