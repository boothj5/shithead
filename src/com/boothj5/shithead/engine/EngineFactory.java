package com.boothj5.shithead.engine;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public class EngineFactory {
	public static ShitheadEngine createEngine(String gameType, ShitheadCli cli) throws ShitheadException {
			if ("i".equals(gameType))
				return new CliEngine(cli) ;
			else if ("b".equals(gameType)) 
				return new BattleEngine(cli) ;
            else if ("g".equals(gameType)) 
                return new GuiEngine() ;
			else 
				throw new ShitheadException("Cannot find engine type to create") ;
	}
}
