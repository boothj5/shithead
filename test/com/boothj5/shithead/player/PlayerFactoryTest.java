package com.boothj5.shithead.player;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import com.boothj5.shithead.game.ShitheadException;
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
}
