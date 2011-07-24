package com.boothj5.shithead.engine;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.ui.gui.MainWindow;

public class GuiEngine implements ShitheadEngine {

    private String[] args ;
    private int numGames = 1 ;
    
    public void globalInit(String[] args) throws ShitheadException {
        this.args = args ;
    }
    
    public int getNumGames() {
        return numGames ;
    }
    
    public void init() throws ShitheadException {
        MainWindow.main(args);
    }
    
    public void deal() {
        
    }
    
    public void swap() {
        
    }
    
    public void firstMove() {
        
    }
    
    public void play() throws ShitheadException {
        
    }
    
    public void end() throws ShitheadException {
        
    }
    
    public void globalEnd() {
        
    }
    
    public void error(ShitheadException e) {
        
    }
    
    
    
}
