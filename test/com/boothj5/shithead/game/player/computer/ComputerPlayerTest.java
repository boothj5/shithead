package com.boothj5.shithead.game.player.computer;

import static org.junit.Assert.*;

import org.junit.Test;

import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.computer.Aggressive;
import com.boothj5.shithead.game.player.computer.DeviousPyro;
import com.boothj5.shithead.game.player.computer.FaceDownChecker;
import com.boothj5.shithead.game.player.computer.LikesRankOrder;
import com.boothj5.shithead.game.player.computer.Pyromaniac;
import com.boothj5.shithead.game.player.computer.RandomPlayer;
import com.boothj5.shithead.game.player.computer.SimplePlayer;

public class ComputerPlayerTest {

    @Test
    public void AggressiveIsComputerPlayer() {
        Player aggressive = new Aggressive("aggressive", 3) ;
        assertTrue(aggressive.isComputer()) ;
    }

    @Test
    public void DeviousPyroIsComputerPlayer() {
        Player dp = new DeviousPyro("dp", 3) ;
        assertTrue(dp.isComputer()) ;
    }

    @Test
    public void FaceDownCheckerIsComputerPlayer() {
        Player fd = new FaceDownChecker("fd", 3) ;
        assertTrue(fd.isComputer()) ;
    }

    @Test
    public void LikesRankOrderIsComputerPlayer() {
        Player lr = new LikesRankOrder("lr", 3) ;
        assertTrue(lr.isComputer()) ;
    }

    @Test
    public void PyromaniacIsComputerPlayer() {
        Player pyro = new Pyromaniac("pyro", 3) ;
        assertTrue(pyro.isComputer()) ;
    }

    @Test
    public void RandomIsComputerPlayer() {
        Player random = new RandomPlayer("random", 3) ;
        assertTrue(random.isComputer()) ;
    }

    @Test
    public void SimpleIsComputerPlayer() {
        Player simple = new SimplePlayer("simple", 3) ;
        assertTrue(simple.isComputer()) ;
    }
}


