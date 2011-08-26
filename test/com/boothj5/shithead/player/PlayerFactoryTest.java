package com.boothj5.shithead.player;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.player.HumanPlayer;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerFactory;
import com.boothj5.shithead.game.player.computer.Aggressive;
import com.boothj5.shithead.game.player.computer.DeviousPyro;
import com.boothj5.shithead.game.player.computer.FaceDownChecker;
import com.boothj5.shithead.game.player.computer.LikesRankOrder;
import com.boothj5.shithead.game.player.computer.Pyromaniac;
import com.boothj5.shithead.game.player.computer.RandomPlayer;
import com.boothj5.shithead.game.player.computer.RankOrderSwapper;
import com.boothj5.shithead.game.player.computer.SimplePlayer;

public class PlayerFactoryTest {

    @Test
    public void computerPlayerListContainsAllPlayers() {
        Map<String, String> players = PlayerFactory.computerPlayerList() ;
        
        assertEquals("s", players.get("SimplePlayer")) ;
        assertEquals("a", players.get("Aggressive")) ;
        assertEquals("r", players.get("RandomPlayer")) ;
        assertEquals("p", players.get("Pyromaniac")) ;
        assertEquals("d", players.get("DeviousPyro")) ;
        assertEquals("f", players.get("FaceDownChecker")) ;
        assertEquals("l", players.get("LikesRankOrder")) ;
        assertEquals("o", players.get("RankOrderSwapper")) ;
    }
    
    @Test
    public void computerPlayerDescriptionsContainsAllPlayers() throws ShitheadException {
        Map<String, String> descriptions = PlayerFactory.computerPlayerDescriptions() ;

        assertEquals(SimplePlayer.description, descriptions.get("SimplePlayer")) ;
        assertEquals(Aggressive.description, descriptions.get("Aggressive")) ;
        assertEquals(RandomPlayer.description, descriptions.get("RandomPlayer")) ;
        assertEquals(Pyromaniac.description, descriptions.get("Pyromaniac")) ;
        assertEquals(DeviousPyro.description, descriptions.get("DeviousPyro")) ;
        assertEquals(FaceDownChecker.description, descriptions.get("FaceDownChecker")) ;
        assertEquals(LikesRankOrder.description, descriptions.get("LikesRankOrder")) ;
        assertEquals(RankOrderSwapper.description, descriptions.get("RankOrderSwapper")) ;
    }
    
    private void assertDetails(Player player) {
        assertEquals(player.getName(), "James") ;
    }
    
    @Test
    public void createHumanPlayer() throws ShitheadException {
        Player player = PlayerFactory.createPlayer("h", "James", 4) ;
        
        assertTrue(player instanceof HumanPlayer) ;
        assertDetails(player) ;
    }

    @Test
    public void createSimplePlayer() throws ShitheadException {
        Player player = PlayerFactory.createPlayer("s", "James", 4) ;
        
        assertTrue(player instanceof SimplePlayer) ;
        assertDetails(player) ;
    }

    @Test
    public void createAggressive() throws ShitheadException {
        Player player = PlayerFactory.createPlayer("a", "James", 4) ;
        
        assertTrue(player instanceof Aggressive) ;
        assertDetails(player) ;
    }

    @Test
    public void createRandomPlayer() throws ShitheadException {
        Player player = PlayerFactory.createPlayer("r", "James", 4) ;
        
        assertTrue(player instanceof RandomPlayer) ;
        assertDetails(player) ;
    }

    @Test
    public void createPyromaniac() throws ShitheadException {
        Player player = PlayerFactory.createPlayer("p", "James", 4) ;
        
        assertTrue(player instanceof Pyromaniac) ;
        assertDetails(player) ;
    }

    @Test
    public void createDeviousPyro() throws ShitheadException {
        Player player = PlayerFactory.createPlayer("d", "James", 4) ;
        
        assertTrue(player instanceof DeviousPyro) ;
        assertDetails(player) ;
    }

    @Test
    public void createFaceDownChecker() throws ShitheadException {
        Player player = PlayerFactory.createPlayer("f", "James", 4) ;
        
        assertTrue(player instanceof FaceDownChecker) ;
        assertDetails(player) ;
    }

    @Test
    public void createLikesRankOrder() throws ShitheadException {
        Player player = PlayerFactory.createPlayer("l", "James", 4) ;
        
        assertTrue(player instanceof LikesRankOrder) ;
        assertDetails(player) ;
    }

    @Test
    public void createRankOrderSwapper() throws ShitheadException {
        Player player = PlayerFactory.createPlayer("o", "James", 4) ;
        
        assertTrue(player instanceof RankOrderSwapper) ;
        assertDetails(player) ;
    }
    
    @Test (expected=ShitheadException.class)
    public void createInvalidPlayerThrowsException() throws ShitheadException {
        PlayerFactory.createPlayer("ZZZ", "James", 4) ;
    }
}
