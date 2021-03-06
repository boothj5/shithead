package com.boothj5.shithead.engine;

import static org.junit.Assert.* ;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerFactory;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public class BattleEngineTest {

    private ShitheadCli cli = new ShitheadCli() ;
    private BattleEngine engine ;
    private String[] args = {"b", "100"} ;

    @Before 
    public void setup() throws Exception {
        engine = (BattleEngine) EngineFactory.createEngine(cli, args) ;
    }

    @Test
    public void hasThreeCards() {
        assertEquals(3, engine.numCards) ;
    }

    @Test
    public void hasCorrectNumGames() {
        int arg1 = Integer.parseInt(args[1]) ;
        assertEquals(arg1, engine.numGames) ; 
    }

    @Test
    public void hasCorrectNumPlayers() {
        int numComputerPlayers = PlayerFactory.computerPlayerList().size() ;
        assertEquals(numComputerPlayers, engine.numPlayers) ;
    }

    @Test
    public void hasCorrectNumPlayerNames() {
        int numPlayerNames = engine.playerNames.size() ;
        assertEquals(engine.numPlayers, numPlayerNames) ;
    }

    @Test
    public void hasCorrectPlayerNames() {
        for (String playerShortName : PlayerFactory.computerPlayerList().keySet()) {
            assertTrue(engine.playerNames.contains(playerShortName)) ;
        }
    }

    @Test 
    public void initiatesShitheadMapToCorrectSize() {
        assertEquals(engine.numPlayers, engine.shitheadMap.size()) ;
    }

    @Test 
    public void initiatesShitheadMapWithZeros() {
        for (String player : engine.shitheadMap.keySet()) {
            int shitheadCount = (Integer)engine.shitheadMap.get(player).intValue() ;
            assertEquals(0, shitheadCount) ;
        }
    }

    @Test
    public void hasCorrectPlayerNamesAfterInit() throws ShitheadException {
        engine.init() ;
        for (String playerShortName : PlayerFactory.computerPlayerList().keySet()) {
            assertTrue(engine.playerNames.contains(playerShortName)) ;
        }
    }

    @Test 
    public void initSetsTurnsToZero() throws ShitheadException {
        engine.init() ;
        assertEquals(0, engine.turns) ;
    }

    @Test 
    public void initSetsStalemeteToFalse() throws ShitheadException {
        engine.init() ;
        assertFalse(engine.stalemate) ;
    }

    @Test 
    public void initCreatesGameWithCorrectNumPlayers() throws ShitheadException {
        engine.init();
        int numPlayersInGame = engine.game.getPlayers().size() ;
        assertEquals(engine.numPlayers, numPlayersInGame) ;
    }

    @Test
    public void initCreatesGameWithCorrectPlayerNames() throws ShitheadException {
        engine.init() ;
        List<String> playerNamesInGame = new ArrayList<String>() ;
        for (Player player : engine.game.getPlayers()) {
            playerNamesInGame.add(player.getName()) ;
        }

        for (String name : engine.playerNames) {
            assertTrue(playerNamesInGame.contains(name)) ;
        }
    }

    @Test 
    public void initCreatesGameWithCorrectPlayerTypes() throws ShitheadException {
        engine.init() ;
        List<String> playerClassesInGame = new ArrayList<String>() ;
        for (Player player : engine.game.getPlayers()) {
            playerClassesInGame.add(player.getClass().getSimpleName()) ;
        }

        for (String className : PlayerFactory.computerPlayerList().keySet()) {
            assertTrue(playerClassesInGame.contains(className)) ;
        }
    }

    @Test
    public void initCreatesGameWithCorrectNumCardsPerHand() throws ShitheadException {
        engine.init();
        assertEquals(engine.numCards, engine.game.getNumCardsPerHand()) ;
    }

    @Test
    public void running100GamesDoesntBail() throws ShitheadException {
        for (int i = 0 ; i < engine.getNumGames() ; i++) {
            engine.init() ;
            engine.deal() ;
            engine.swap() ;
            engine.firstMove() ;
            engine.play() ;
            engine.end() ;
        }
        engine.globalEnd() ;    
    }
}
