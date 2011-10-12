package com.boothj5.shithead.engine;

import static org.junit.Assert.*;

import org.junit.Test;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public class EngineFactoryTest {

    @Test
    public void returnsCliEngine() throws ShitheadException {
        ShitheadCli cli = new ShitheadCli() ;
        ShitheadEngine engine = EngineFactory.createEngine("i", cli) ;
        
        assertTrue(engine instanceof CliEngine) ;
    }

    @Test
    public void returnsBattleEngine() throws ShitheadException {
        ShitheadCli cli = new ShitheadCli() ;
        ShitheadEngine engine = EngineFactory.createEngine("b", cli) ;
        
        assertTrue(engine instanceof BattleEngine) ;
    }

    @Test
    public void returnsGuiEngine() throws ShitheadException {
        ShitheadCli cli = new ShitheadCli() ;
        ShitheadEngine engine = EngineFactory.createEngine("g", cli) ;
        
        assertTrue(engine instanceof GuiEngine) ;
    }

    @Test (expected=ShitheadException.class)
    public void ThrowsExceptionOnEmptyString() throws ShitheadException {
        ShitheadCli cli = new ShitheadCli() ;
        EngineFactory.createEngine("", cli) ;
    }

    @Test (expected=ShitheadException.class)
    public void ThrowsExceptionOnNullString() throws ShitheadException {
        ShitheadCli cli = new ShitheadCli() ;
        EngineFactory.createEngine(null, cli) ;
    }
    
    @Test (expected=ShitheadException.class)
    public void ThrowsExceptionOnInvalidString() throws ShitheadException {
        ShitheadCli cli = new ShitheadCli() ;
        EngineFactory.createEngine("p", cli) ;
    }
    
    
}
