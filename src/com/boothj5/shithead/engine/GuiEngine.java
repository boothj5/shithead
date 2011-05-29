package com.boothj5.shithead.engine;

import com.boothj5.shithead.gui.ShitheadGUI;

public class GuiEngine implements ShitheadEngine {

    @Override
    public void runEngine(String[] args) {
        ShitheadGUI gui = new ShitheadGUI();
        gui.setVisible(true);
        
    }

}
