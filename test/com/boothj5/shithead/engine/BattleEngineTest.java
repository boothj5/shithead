package com.boothj5.shithead.engine;

import static org.junit.Assert.* ;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerFactory;

public class BattleEngineTest {
    
    private BattleEngine engine ;
    private String[] args = {"b", "100"} ; 

    @Before 
    public void setup() throws Exception {
        engine = (BattleEngine) EngineFactory.createEngine(args[0]) ;
    }
    
    @Test
    public void hasThreeCards() throws ShitheadException {
        engine.globalInit(args) ;
        assertEquals(3, engine.numCards) ;
    }
    
    @Test
    public void hasCorrectNumGames() throws ShitheadException {
        engine.globalInit(args) ;
        int arg1 = Integer.parseInt(args[1]) ;
        assertEquals(arg1, engine.numGames) ; 
    }
    
    @Test
    public void hasCorrectNumPlayers() throws ShitheadException {
        engine.globalInit(args) ;
        int numComputerPlayers = PlayerFactory.computerPlayerList().size() ;
        assertEquals(numComputerPlayers, engine.numPlayers) ;
    }
    
    @Test
    public void hasCorrectNumPlayerNames() throws ShitheadException {
        engine.globalInit(args) ;
        int numPlayerNames = engine.playerNames.size() ;
        assertEquals(engine.numPlayers, numPlayerNames) ;
    }
    
    @Test
    public void hasCorrectPlayerNames() throws ShitheadException {
        engine.globalInit(args) ;
        for (String playerShortName : PlayerFactory.computerPlayerList().keySet()) {
            assertTrue(engine.playerNames.contains(playerShortName)) ;
        }
    }
    
    @Test 
    public void initiatesShitheadMapToCorrectSize() throws ShitheadException {
        engine.globalInit(args) ;
        assertEquals(engine.numPlayers, engine.shitheadMap.size()) ;
    }
    
    @Test 
    public void initiatesShitheadMapWithZeros() throws ShitheadException {
        engine.globalInit(args) ;
        for (String player : engine.shitheadMap.keySet()) {
            int shitheadCount = (Integer)engine.shitheadMap.get(player).intValue() ;
            assertEquals(0, shitheadCount) ;
        }
    }
    
    @Test
    public void hasCorrectPlayerNamesAfterInit() throws ShitheadException {
        engine.globalInit(args) ;
        engine.init() ;
        for (String playerShortName : PlayerFactory.computerPlayerList().keySet()) {
            assertTrue(engine.playerNames.contains(playerShortName)) ;
        }
    }
    
    @Test 
    public void initSetsTurnsToZero() throws ShitheadException {
        engine.globalInit(args) ;
        engine.init() ;
        assertEquals(0, engine.turns) ;
    }
    
    @Test 
    public void initSetsStalemeteToFalse() throws ShitheadException {
        engine.globalInit(args) ;
        engine.init() ;
        assertFalse(engine.stalemate) ;
    }
    
    @Test 
    public void initCreatesGameWithCorrectNumPlayers() throws ShitheadException {
        engine.globalInit(args) ;
        engine.init();
        int numPlayersInGame = engine.game.getGameDetails().getNumPlayers() ;
        assertEquals(engine.numPlayers, numPlayersInGame) ;
    }
    
    @Test
    public void initCreatesGameWithCorrectPlayerNames() throws ShitheadException {
        engine.globalInit(args) ;
        engine.init() ;
        List<String> playerNamesInGame = new ArrayList<String>() ;
        for (Player player : engine.game.getGameDetails().getPlayers()) {
            playerNamesInGame.add(player.getName()) ;
        }
        
        for (String name : engine.playerNames) {
            assertTrue(playerNamesInGame.contains(name)) ;
        }
    }
    
    @Test 
    public void initCreatesGameWithCorrectPlayerTypes() throws ShitheadException {
        engine.globalInit(args) ;
        engine.init() ;
        List<String> playerClassesInGame = new ArrayList<String>() ;
        for (Player player : engine.game.getGameDetails().getPlayers()) {
            playerClassesInGame.add(player.getClass().getSimpleName()) ;
        }
        
        for (String className : PlayerFactory.computerPlayerList().keySet()) {
            assertTrue(playerClassesInGame.contains(className)) ;
        }
    }
    
    @Test
    public void initCteatesGameWithCorrectNumCardsPerHand() throws ShitheadException {
        engine.globalInit(args) ;
        engine.init();
        assertEquals(engine.numCards, engine.game.getGameDetails().getNumCardsPerHand()) ;
    }
    
    
    
    
    
    
    
}
