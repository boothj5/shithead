package com.boothj5.shithead.game;

import com.boothj5.shithead.engine.ShitheadEngine;
import com.boothj5.shithead.engine.EngineFactory;

public class Shithead {
	
	public static void main(String[] args) throws Exception {
		String gameType = args[0] ;
		ShitheadEngine engine = EngineFactory.createEngine(gameType) ;
		
        try {
            engine.globalInit(args) ;
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
