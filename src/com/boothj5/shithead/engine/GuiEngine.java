package com.boothj5.shithead.engine;

import com.boothj5.shithead.gui.MainWindow;

public class GuiEngine implements ShitheadEngine {

    @Override
    public void runEngine(String[] args) {
        MainWindow.main(args);
        
    }

}
