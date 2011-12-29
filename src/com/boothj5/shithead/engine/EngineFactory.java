package com.boothj5.shithead.engine;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public class EngineFactory {
    public static ShitheadEngine createEngine(ShitheadCli cli, String[] args) throws ShitheadException {
        if (args.length < 1)
            throw new ShitheadException("Cannot find engine type to create") ;
        else if ("i".equals(args[0]))
            return new CliEngine(cli) ;
        else if ("b".equals(args[0])) 
            return new BattleEngine(args, cli) ;
        else if ("g".equals(args[0])) 
            return new GuiEngine(args) ;
        else 
            throw new ShitheadException("Cannot find engine type to create") ;
    }
}
