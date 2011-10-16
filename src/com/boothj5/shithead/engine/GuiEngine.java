package com.boothj5.shithead.engine;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.ui.gui.MainWindow;

public class GuiEngine extends ShitheadEngine {

    private String[] args ;
    
    public GuiEngine(String[] args) {
        this.args = args ;
        this.numGames = 1;
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
