package com.boothj5.shithead.game;

import com.boothj5.shithead.engine.ShitheadEngine;
import com.boothj5.shithead.engine.EngineFactory;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public class Shithead {
	
	public static void main(String[] args) throws Exception {
		final ShitheadCli cli = new ShitheadCli() ;
        final ShitheadEngine engine = EngineFactory.createEngine(cli, args) ;
		
        try {
            for (int i = 0 ; i < engine.getNumGames() ; i++) {
                engine.init() ;
                engine.deal() ;
                engine.swap() ;
                engine.firstMove() ;
                engine.play() ;
                engine.end() ;
            }
            engine.globalEnd() ;
        } catch (ShitheadException e) {
            engine.error(e) ;
        }		
	}
}
