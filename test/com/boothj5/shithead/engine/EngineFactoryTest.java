package com.boothj5.shithead.engine;

import static org.junit.Assert.*;

import org.junit.Test;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public class EngineFactoryTest {

    @Test
    public void returnsCliEngine() throws ShitheadException {
        final ShitheadCli cli = new ShitheadCli() ;
        final String[] args = {"i"} ;
        final ShitheadEngine engine = EngineFactory.createEngine(cli, args) ;

        assertTrue(engine instanceof CliEngine) ;
    }

    @Test
    public void returnsBattleEngine() throws ShitheadException {
        final ShitheadCli cli = new ShitheadCli() ;
        final String[] args = {"b", "100"} ;
        final ShitheadEngine engine = EngineFactory.createEngine(cli, args) ;

        assertTrue(engine instanceof BattleEngine) ;
    }

    @Test
    public void returnsGuiEngine() throws ShitheadException {
        final ShitheadCli cli = new ShitheadCli() ;
        final String[] args = {"g"} ;
        final ShitheadEngine engine = EngineFactory.createEngine(cli, args) ;

        assertTrue(engine instanceof GuiEngine) ;
    }

    @Test (expected=ShitheadException.class)
    public void ThrowsExceptionOnEmptyString() throws ShitheadException {
        final ShitheadCli cli = new ShitheadCli() ;
        final String[] args = {""} ;

        EngineFactory.createEngine(cli, args) ;
    }

    @Test (expected=ShitheadException.class)
    public void ThrowsExceptionOnNullString() throws ShitheadException {
        final ShitheadCli cli = new ShitheadCli() ;
        final String[] args = {} ;

        EngineFactory.createEngine(cli, args) ;
    }

    @Test (expected=ShitheadException.class)
    public void ThrowsExceptionOnInvalidString() throws ShitheadException {
        final ShitheadCli cli = new ShitheadCli() ;
        final String[] args = {"p"} ;

        EngineFactory.createEngine(cli, args) ;
    }


}
