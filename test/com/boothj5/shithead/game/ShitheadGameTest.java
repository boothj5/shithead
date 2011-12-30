package com.boothj5.shithead.game;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test ;

import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.card.*;
import com.boothj5.shithead.game.player.Player;

import static org.junit.Assert.* ;
import java.util.* ;

public class ShitheadGameTest {

    @Test
    public void newGameContainsSpecifiedPlayers() throws Exception {
        int numPlayers = 3 ;
        int numCardsPerHand = 4 ;
        ShitheadGame game = createGameWith3HumanPlayersFourCards() ;

        assertThat("Correct number of players", game.getPlayers().size(), is(numPlayers)) ;
        assertThat("Correct cards per hand", game.getNumCardsPerHand(), is(numCardsPerHand)) ;
    }

    @Test
    public void dealResultsIn52CardsInGame() throws Exception {
        ShitheadGame game = createGameWith3HumanPlayersFourCards() ;
        game.deal();
        int totalCardsInGame = game.getDeck().size() + 
                game.getPile().size() + 
                game.getBurnt().size() ;

        int playerCards = 0 ;
        for (Player player : game.getPlayers()) {
            playerCards += player.getHand().size() ;
            playerCards += player.getFaceUp().size() ;
            playerCards += player.getFaceDown().size() ;
        }


        totalCardsInGame += playerCards ;

        assertThat(totalCardsInGame, is(52)) ;
    }

    @Test
    public void dealResultsIn104CardsInGame() throws Exception {
        ShitheadGame game = createGameWith3HumanPlayersTenCards() ;
        game.deal();

        int totalCardsExpected = 104 ;
        int totalCardsInGame = game.getDeck().size() + 
                game.getPile().size() + 
                game.getBurnt().size() ;

        int playerCards = 0 ;
        for (Player player : game.getPlayers()) {
            playerCards += player.getHand().size() ;
            playerCards += player.getFaceUp().size() ;
            playerCards += player.getFaceDown().size() ;
        }


        totalCardsInGame += playerCards ;

        assertThat(totalCardsInGame, is(totalCardsExpected)) ;
    }

    @Test
    public void dealResultsIn156CardsInGame() throws Exception {
        ShitheadGame game = createGameWith5HumanPlayersTenCards() ;
        game.deal();

        int totalCardsExpected = 156;
        int totalCardsInGame = game.getDeck().size() + 
                game.getPile().size() + 
                game.getBurnt().size() ;

        int playerCards = 0 ;
        for (Player player : game.getPlayers()) {
            playerCards += player.getHand().size() ;
            playerCards += player.getFaceUp().size() ;
            playerCards += player.getFaceDown().size() ;
        }


        totalCardsInGame += playerCards ;

        assertThat(totalCardsInGame, is(totalCardsExpected)) ;
    }	


    @Test
    public void dealLeavesCorrectAmountInDeck() throws Exception{
        int numPlayers = 3 ;
        int numCardsPerHand = 4 ;

        ShitheadGame game = createGameWith3HumanPlayersFourCards() ;
        game.deal();
        int numberOfCardsThatShouldBeLeft = 52 - ((numPlayers * numCardsPerHand) * 3) ;

        assertThat(game.getDeck().size(), is(numberOfCardsThatShouldBeLeft)) ;
    }

    @Test
    public void firstMovePicksLowestCard() throws Exception {
        ShitheadGame game = createGameWith3HumanPlayersFourCards() ;
        game.deal();
        game.firstMove() ;

        List<Card> laid = game.getLastMove().getCards() ;
        Card testCard = laid.get(0) ;

        boolean foundLower = false ;

        for (Player player : game.getPlayers()) {
            List<Card> hand = player.getHand() ;

            for (Card card : hand) {
                ShitheadCardComparator comp = new ShitheadCardComparator();
                foundLower = (comp.compare(testCard, card) > 0) ;
                if (foundLower) break ;
            }
        }

        assertThat(foundLower, is(false)) ;
    }		

    @Test
    public void firstMovePicksSameRank() throws Exception {
        ShitheadGame game = createGameWith3HumanPlayersFourCards() ;
        game.deal();
        game.firstMove() ;

        List<Card> laid = game.getLastMove().getCards() ;
        Card firstCard = laid.get(0) ;

        boolean foundDifferent = false ;

        for (Card testCard : laid) {
            foundDifferent = (firstCard.compareTo(testCard) != 0) ;
            if (foundDifferent) break ;
        }

        assertThat(foundDifferent, is(false)) ;
    }

    private ShitheadGame createGameWith3HumanPlayersFourCards() throws Exception {
        int numCardsPerHand = 4 ;

        List<String> playerNames = new ArrayList<String>() ;
        playerNames.add("James") ;
        playerNames.add("Bob") ;
        playerNames.add("Monkey") ;

        List<String> playerTypes = new ArrayList<String>() ;
        playerTypes.add("h") ;
        playerTypes.add("h") ;
        playerTypes.add("h") ;

        return new ShitheadGame(playerNames, playerTypes, numCardsPerHand) ;
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

        return new ShitheadGame(playerNames, playerTypes, numCardsPerHand) ;
    }	

    private ShitheadGame createGameWith5HumanPlayersTenCards() throws Exception {
        int numCardsPerHand = 10 ;

        List<String> playerNames = new ArrayList<String>() ;
        playerNames.add("James") ;
        playerNames.add("Bob") ;
        playerNames.add("Monkey") ;
        playerNames.add("Dave") ;
        playerNames.add("Mike") ;

        List<String> playerTypes = new ArrayList<String>() ;
        playerTypes.add("h") ;
        playerTypes.add("h") ;
        playerTypes.add("h") ;
        playerTypes.add("h") ;
        playerTypes.add("h") ;

        return new ShitheadGame(playerNames, playerTypes, numCardsPerHand) ;
    }	

}