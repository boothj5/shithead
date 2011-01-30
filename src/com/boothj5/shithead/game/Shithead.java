package com.boothj5.shithead.game;

import com.boothj5.shithead.engine.*;

public class Shithead {
	
	public static void main(String[] args) throws Exception {
		String gameType = args[0] ;
		ShitheadEngine engine = EngineFactory.createEngine(gameType) ;
		engine.playShithead(args) ;
	}
}
