package com.boothj5.shithead.engine;

import static org.junit.Assert.*;

import static org.mockito.Mockito.mock ;
import static org.mockito.Mockito.when ;
import static org.mockito.Mockito.verify ;

import org.junit.Before;
import org.junit.Test;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public class CliEngineTest {

    String[] args = {"i"} ;
    ShitheadEngine engine ;
    ShitheadCli cli = mock(ShitheadCli.class); 
    
    @Before 
    public void setup() throws Exception {
        engine = (CliEngine) EngineFactory.createEngine(args[0], cli) ;
    }
    
    @Test
    public void hasCorrectNumGames() throws ShitheadException {
        engine.globalInit(args) ;
        assertEquals(1, engine.numGames) ; 
    }
    
    @Test
    public void screenIsClearedOnInit() throws ShitheadException {
        engine.globalInit(args) ;
        engine.init() ;
        verify(cli).clearScreen() ;
    }

    @Test
    public void weclomeMessageOnInit() throws ShitheadException {
        engine.globalInit(args) ;
        engine.init() ;
        verify(cli).welcome() ;
    }
    
    @Test
    public void numPlayersRequestedOnInit() throws ShitheadException {
        engine.globalInit(args) ;
        engine.init() ;
        verify(cli).requestNumPlayers() ;
    }
    
    @Test
    public void numCardsEachRequestedOnInit() throws ShitheadException {
        engine.globalInit(args) ;
        engine.init() ;
        verify(cli).requestNumCardsPerHand() ;
    }
    
    @Test
    public void requestCorrectNumPlayerNames() throws ShitheadException {
        engine.globalInit(args) ;
        respondWithGameDetails();
        engine.init() ;
        
        verify(cli).requestPlayerName(1) ;
        verify(cli).requestPlayerName(2) ;
        verify(cli).requestPlayerName(3) ;
    }

    @Test
    public void requestCorrectNumPlayerTypes() throws ShitheadException {
        engine.globalInit(args) ;
        respondWithGameDetails();
        engine.init() ;
        
        verify(cli).requestPlayerType("James");
        verify(cli).requestPlayerType("Mike");
        verify(cli).requestPlayerType("Comp");
    }
    
    @Test
    public void gameHasCorrectNumberOfCards() throws ShitheadException {
        engine.globalInit(args) ;
        respondWithGameDetails();
        engine.init() ;
        assertEquals(4, engine.game.getGameDetails().getNumCardsPerHand()) ;
    }

    private void respondWithGameDetails() throws ShitheadException {
        when(cli.requestNumPlayers()).thenReturn(3) ;
        when(cli.requestNumCardsPerHand()).thenReturn(4) ;
        when(cli.requestPlayerName(1)).thenReturn("James") ;
        when(cli.requestPlayerName(2)).thenReturn("Mike") ;
        when(cli.requestPlayerName(3)).thenReturn("Comp") ;
        when(cli.requestPlayerType("James")).thenReturn("h");
        when(cli.requestPlayerType("Mike")).thenReturn("h");
        when(cli.requestPlayerType("Comp")).thenReturn("r");
    }



}
