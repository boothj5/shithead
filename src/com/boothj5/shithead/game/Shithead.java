package com.boothj5.shithead.game;

import com.boothj5.shithead.engine.ShitheadEngine;
import com.boothj5.shithead.engine.EngineFactory;

public class Shithead {
	
	public static void main(String[] args) throws Exception {
		String gameType = args[0] ;
		ShitheadEngine engine = EngineFactory.createEngine(gameType) ;
		engine.runEngine(args) ;
	}
}
