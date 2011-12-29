package com.boothj5.shithead.engine;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.ShitheadGame;

public abstract class ShitheadEngine {
    protected ShitheadGame game ;
    protected int numGames ;

    public final int getNumGames() {
        return numGames ;
    }

    public abstract void init() throws ShitheadException;
    public abstract void deal() ;
    public abstract void swap() throws ShitheadException ;
    public abstract void firstMove() ;
    public abstract void play() throws ShitheadException;
    public abstract void end() throws ShitheadException;
    public abstract void globalEnd() ;
    public abstract void error(final ShitheadException e) ;
}	
