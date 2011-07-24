package com.boothj5.shithead.engine;

import com.boothj5.shithead.game.ShitheadException;

public interface ShitheadEngine {
	public int getNumGames() ;
    public void globalInit(String[] args) throws ShitheadException;
    public void init() throws ShitheadException;
    public void deal() ;
    public void swap() ;
    public void firstMove() ;
    public void play() throws ShitheadException;
    public void end() throws ShitheadException;
    public void globalEnd() ;
    public void error(ShitheadException e) ;
}	
