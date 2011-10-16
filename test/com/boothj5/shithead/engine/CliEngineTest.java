package com.boothj5.shithead.engine;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock ;
import static org.mockito.Mockito.when ;
import static org.mockito.Mockito.verify ;

import org.junit.Before;
import org.junit.Test;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerFactory;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public class CliEngineTest {

    String[] args = {"i"} ;
    ShitheadEngine engine ;
    ShitheadCli cli = mock(ShitheadCli.class); 
    
    @Before 
    public void setup() throws Exception {
        engine = (CliEngine) EngineFactory.createEngine(cli, args) ;
    }
    
    @Test
    public void hasCorrectNumGames() throws ShitheadException {
        assertEquals(1, engine.numGames) ; 
    }
    
    @Test
    public void screenIsClearedOnInit() throws ShitheadException {
        engine.init() ;
        verify(cli).clearScreen() ;
    }

    @Test
    public void weclomeMessageOnInit() throws ShitheadException {
        engine.init() ;
        verify(cli).welcome() ;
    }
    
    @Test
    public void numPlayersRequestedOnInit() throws ShitheadException {
        engine.init() ;
        verify(cli).requestNumPlayers() ;
    }
    
    @Test
    public void numCardsEachRequestedOnInit() throws ShitheadException {
        engine.init() ;
        verify(cli).requestNumCardsPerHand() ;
    }
    
    @Test
    public void requestCorrectNumPlayerNamesOnInit() throws ShitheadException {
        respondWithGameDetails();
        engine.init() ;
        
        verify(cli).requestPlayerName(1) ;
        verify(cli).requestPlayerName(2) ;
        verify(cli).requestPlayerName(3) ;
    }

    @Test
    public void requestCorrectNumPlayerTypesOnInit() throws ShitheadException {
        respondWithGameDetails();
        engine.init() ;
        
        verify(cli).requestPlayerType("James");
        verify(cli).requestPlayerType("Mike");
        verify(cli).requestPlayerType("Comp");
    }
    
    @Test
    public void initCreatesGameWithCorrectNumberOfCards() throws ShitheadException {
        respondWithGameDetails();
        engine.init() ;
        assertEquals(4, engine.game.getGameDetails().getNumCardsPerHand()) ;
    }

    @Test
    public void initCreatesGameWithCorrectNumberOfPlayers() throws ShitheadException {
        respondWithGameDetails();
        engine.init() ;
        assertEquals(3, engine.game.getGameDetails().getNumPlayers()) ;
    }
    
    @Test
    public void initCreatesGameWithCorrectPlayerNames() throws ShitheadException {
        respondWithGameDetails();
        engine.init() ;
        String[] names = { "James", "Mike", "Comp" };
        List<String> namesExpected = Arrays.asList(names) ;
        
        for (Player player : engine.game.getGameDetails().getPlayers()) {
            assertTrue(namesExpected.contains(player.getName())) ;
        }
    }

    @Test
    public void initCreatesGameWithCorrectPlayerTypes() throws ShitheadException {
        respondWithGameDetails() ;
        engine.init() ;
        String[] types = {"h", "s", "r" } ;
        List<String> typesExpected = Arrays.asList(types) ;
        List<String> playerTypesInGame = new ArrayList<String>() ;
        
        for (Player player : engine.game.getGameDetails().getPlayers()) {
            String type = PlayerFactory.computerPlayerList().get(player.getClass().getSimpleName()) ;
            playerTypesInGame.add(type) ;
        }
        
        for (String type : typesExpected) {
            assertTrue(playerTypesInGame.contains(PlayerFactory.computerPlayerList().get(type))) ;
        }
    }
    
    private void respondWithGameDetails() throws ShitheadException {
        when(cli.requestNumPlayers()).thenReturn(3) ;
        when(cli.requestNumCardsPerHand()).thenReturn(4) ;
        when(cli.requestPlayerName(1)).thenReturn("James") ;
        when(cli.requestPlayerName(2)).thenReturn("Mike") ;
        when(cli.requestPlayerName(3)).thenReturn("Comp") ;
        when(cli.requestPlayerType("James")).thenReturn("h");
        when(cli.requestPlayerType("Mike")).thenReturn("s");
        when(cli.requestPlayerType("Comp")).thenReturn("r");
    }
}
