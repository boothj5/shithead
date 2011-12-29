package com.boothj5.shithead.game.player;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test ;

import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.PlayerSummary;

import static org.junit.Assert.* ;
import java.util.* ;

public class PlayerHelperTest {

    @Test
    public void newPlayerHelperContainsCorrectNumCardsPerHand() throws Exception {
        ShitheadGame game = createGameWith3HumanPlayersTenCards() ;
        PlayerHelper helper = game.getPlayerHelper() ;

        assertThat(helper.getNumCardsPerHand(), is(10)) ;
    }

    @Test
    public void newPlayerHelperStatesCorrectNumPlayersInGame() throws Exception {
        ShitheadGame game = createGameWith3HumanPlayersTenCards() ;
        PlayerHelper helper = game.getPlayerHelper() ;

        assertThat(helper.getNumPlayersInGame(), is(3)) ;
    }

    @Test
    public void newPlayerHelperContainsEmptyNotNullBurnt() throws Exception {
        ShitheadGame game = createGameWith3HumanPlayersTenCards() ;
        PlayerHelper helper = game.getPlayerHelper() ;

        assertThat(helper.getBurnt().size(), is(0)) ;
    }

    @Test
    public void newPlayerHelperConatinsCorrectNumberLeftOnDeck() throws Exception {
        ShitheadGame game = createGameWith3HumanPlayersTenCards() ;
        PlayerHelper helper = game.getPlayerHelper() ;

        assertThat(helper.getNumberOnDeck(), is(14)) ;
    }

    @Test
    public void newPlayerHelperCurrentPlayerIsZero() throws Exception {
        ShitheadGame game = createGameWith3HumanPlayersTenCards() ;
        PlayerHelper helper = game.getPlayerHelper() ;

        assertThat(helper.getCurrentPlayer(), is(0)) ;
    }

    @Test
    public void newPlayerHelperContainsCorrectNumPlayersStillInGame() throws Exception {
        ShitheadGame game = createGameWith3HumanPlayersTenCards() ;
        PlayerHelper helper = game.getPlayerHelper() ;

        assertThat(helper.getNumPlayersStillPlaying(), is(3)) ;
    }

    @Test
    public void newPlayerHelperContainsEmptyStack() throws Exception {
        ShitheadGame game = createGameWith3HumanPlayersTenCards() ;
        PlayerHelper helper = game.getPlayerHelper() ;

        assertThat(helper.getPile().size(), is(0)) ;
    }

    @Test
    public void newPlayerHelperContainsCorrectNumberOfPlayersInList() throws Exception {
        ShitheadGame game = createGameWith3HumanPlayersTenCards() ;
        PlayerHelper helper = game.getPlayerHelper() ;

        assertThat(helper.getPlayers().size(), is(3)) ;
    }


    @Test
    public void newPlayerHelperContainsCorrectPlayersNames() throws Exception {
        ShitheadGame game = createGameWith3HumanPlayersTenCards() ;
        PlayerHelper helper = game.getPlayerHelper() ;

        List<PlayerSummary> summaries = helper.getPlayers() ;

        int totalFound = 0 ;

        for (PlayerSummary playerSum : summaries) {
            if (playerSum.getName().equals("James")) 
                totalFound++ ;
            if (playerSum.getName().equals("Bob")) 
                totalFound++ ;
            if (playerSum.getName().equals("Monkey")) 
                totalFound++ ;
        }
        assertThat(totalFound, is(3)) ;
    }


    @Test
    public void newPlayerHelperContainsCorrectHandSizes() throws Exception {
        ShitheadGame game = createGameWith3HumanPlayersTenCards() ;
        PlayerHelper helper = game.getPlayerHelper() ;

        List<PlayerSummary> summaries = helper.getPlayers() ;

        int totalFound = 0 ;

        for (PlayerSummary playerSum : summaries) {
            if (playerSum.getHandSize() == 10) 
                totalFound++ ;
        }
        assertThat(totalFound, is(3)) ;
    }

    @Test
    public void newPlayerHelperContainsCorrectFaceDownSizes() throws Exception {
        ShitheadGame game = createGameWith3HumanPlayersTenCards() ;
        PlayerHelper helper = game.getPlayerHelper() ;

        List<PlayerSummary> summaries = helper.getPlayers() ;

        int totalFound = 0 ;

        for (PlayerSummary playerSum : summaries) {
            if (playerSum.getHandDownSize() == 10) 
                totalFound++ ;
        }
        assertThat(totalFound, is(3)) ;
    }

    @Test
    public void newPlayerHelperContainsCorrectFaceUpSizes() throws Exception {
        ShitheadGame game = createGameWith3HumanPlayersTenCards() ;
        PlayerHelper helper = game.getPlayerHelper() ;

        List<PlayerSummary> summaries = helper.getPlayers() ;

        int totalFound = 0 ;

        for (PlayerSummary playerSum : summaries) {
            if (playerSum.getFaceUp().size() == 10) 
                totalFound++ ;
        }
        assertThat(totalFound, is(3)) ;
    }

    @Test
    public void stackPeekSameAsLastELemebtOfUnmodifiableList() {
        Stack<String> stringStack= new Stack<String>() ;

        stringStack.push("Bottom") ;
        stringStack.push("SecondPushed") ;
        stringStack.push("ThirdPushed") ;
        stringStack.push("Top") ;

        List<String> stringList = Collections.unmodifiableList(stringStack) ;

        assertThat(stringStack.peek().equals(stringList.get(stringList.size()-1)), is(true)) ;
    }


    private ShitheadGame createGameWith3HumanPlayersTenCards() throws Exception {
        int numCardsPerHand = 10 ;

        List<String> playerNames = new ArrayList<String>() ;
        playerNames.add("James") ;
        playerNames.add("Bob") ;
        playerNames.add("Monkey") ;

        List<String> playerTypes = new ArrayList<String>() ;
        playerTypes.add("h") ;
        playerTypes.add("h") ;
        playerTypes.add("h") ;

        ShitheadGame game = new ShitheadGame(playerNames, playerTypes, numCardsPerHand) ;
        game.deal();
        return game ;
    }		
}
