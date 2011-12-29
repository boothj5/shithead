package com.boothj5.shithead.game.player.computer;

import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.junit.Test ;

import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.PlayerSummary;
import com.boothj5.shithead.game.player.computer.SimplePlayer;

import static org.junit.Assert.* ;


public class SimplePlayerTest {

    @Test
    public void picksOnlyCard() {
        int numCardsPerHand = 3 ;
        Player james = new SimplePlayer("James", numCardsPerHand) ;
        List<Player> players = new ArrayList<Player>();
        players.add(james) ;

        Card hand1 = new Card(Card.Rank.SEVEN, Card.Suit.SPADES) ;
        james.dealToHand(hand1) ;

        PlayerSummary playerSummaryJames = new PlayerSummary(james.getName(), 
                james.getHand().size(), 
                Collections.unmodifiableList(james.getFaceUp()), 
                james.getFaceDown().size(), james.hasCards()) ;

        List<PlayerSummary> playerSummaries = new ArrayList<PlayerSummary>() ;
        playerSummaries.add(playerSummaryJames) ;

        PlayerHelper helper = new PlayerHelper(0, 1, 1, numCardsPerHand, 0, new Stack<Card>(), new ArrayList<Card>(), playerSummaries) ;

        List<Integer> choices = james.askCardChoiceFromHand(helper) ;
        Card chosenCard = james.getHand().get(choices.get(0)) ;

        assertThat(choices.size(), is(1)) ;
        assertThat(chosenCard.equals(new Card(Card.Rank.SEVEN, Card.Suit.SPADES)), is(true)) ;
    }

    @Test
    public void picksLowestOfNormalWhenTwoCards() {
        int numCardsPerHand = 3 ;
        Player james = new SimplePlayer("James", numCardsPerHand) ;
        List<Player> players = new ArrayList<Player>();
        players.add(james) ;

        Card hand1 = new Card(Card.Rank.FOUR, Card.Suit.SPADES) ;
        Card hand2 = new Card(Card.Rank.NINE, Card.Suit.DIAMONDS) ;
        james.dealToHand(hand1) ;
        james.dealToHand(hand2) ;

        PlayerSummary playerSummaryJames = new PlayerSummary(james.getName(), 
                james.getHand().size(), 
                Collections.unmodifiableList(james.getFaceUp()), 
                james.getFaceDown().size(), james.hasCards()) ;

        List<PlayerSummary> playerSummaries = new ArrayList<PlayerSummary>() ;
        playerSummaries.add(playerSummaryJames) ;

        PlayerHelper helper = new PlayerHelper(0, 1, 1, numCardsPerHand, 0, new Stack<Card>(), new ArrayList<Card>(), playerSummaries) ;

        List<Integer> choices = james.askCardChoiceFromHand(helper) ;
        Card chosenCard = james.getHand().get(choices.get(0)) ;

        assertThat(choices.size(), is(1)) ;
        assertThat(chosenCard.equals(new Card(Card.Rank.FOUR, Card.Suit.SPADES)), is(true)) ;
    }


    @Test
    public void picksLowestOverSpecialWhenTwoCards() {
        int numCardsPerHand = 3 ;
        Player james = new SimplePlayer("James", numCardsPerHand) ;
        List<Player> players = new ArrayList<Player>();
        players.add(james) ;

        Card hand1 = new Card(Card.Rank.SEVEN, Card.Suit.SPADES) ;
        Card hand2 = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS) ;
        james.dealToHand(hand1) ;
        james.dealToHand(hand2) ;
        james.sortHand() ;

        PlayerSummary playerSummaryJames = new PlayerSummary(james.getName(), 
                james.getHand().size(), 
                Collections.unmodifiableList(james.getFaceUp()), 
                james.getFaceDown().size(), james.hasCards()) ;

        List<PlayerSummary> playerSummaries = new ArrayList<PlayerSummary>() ;
        playerSummaries.add(playerSummaryJames) ;

        PlayerHelper helper = new PlayerHelper(0, 1, 1, numCardsPerHand, 0, new Stack<Card>(), new ArrayList<Card>(), playerSummaries) ;

        List<Integer> choices = james.askCardChoiceFromHand(helper) ;
        Card chosenCard = james.getHand().get(choices.get(0)) ;

        assertThat(choices.size(), is(1)) ;
        assertThat(chosenCard.equals(new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS)), is(true)) ;
    }


    @Test
    public void picksLowestCardsOverOtherNormalRanks() {
        int numCardsPerHand = 4 ;
        Player james = new SimplePlayer("James", numCardsPerHand) ;
        List<Player> players = new ArrayList<Player>();
        players.add(james) ;

        Card hand1 = new Card(Card.Rank.EIGHT, Card.Suit.SPADES) ;
        Card hand2 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
        Card hand3 = new Card(Card.Rank.FIVE, Card.Suit.HEARTS) ;
        Card hand4 = new Card(Card.Rank.SIX, Card.Suit.HEARTS) ;
        james.dealToHand(hand1) ;
        james.dealToHand(hand2) ;
        james.dealToHand(hand3) ;
        james.dealToHand(hand4) ;
        james.sortHand() ;

        PlayerSummary playerSummaryJames = new PlayerSummary(james.getName(), 
                james.getHand().size(), 
                Collections.unmodifiableList(james.getFaceUp()), 
                james.getFaceDown().size(), james.hasCards()) ;

        List<PlayerSummary> playerSummaries = new ArrayList<PlayerSummary>() ;
        playerSummaries.add(playerSummaryJames) ;

        PlayerHelper helper = new PlayerHelper(0, 1, 1, numCardsPerHand, 0, new Stack<Card>(), new ArrayList<Card>(), playerSummaries) ;

        List<Integer> choices = james.askCardChoiceFromHand(helper) ;
        Card chosenCard = james.getHand().get(choices.get(0)) ;

        assertThat(choices.size(), is(1)) ;
        assertThat(chosenCard.equals(new Card(Card.Rank.FIVE, Card.Suit.HEARTS)), is(true)) ;
    }

    @Test
    public void picksLowestTwoCardsWhenTwoSame() {
        int numCardsPerHand = 3 ;
        Player james = new SimplePlayer("James", numCardsPerHand) ;
        List<Player> players = new ArrayList<Player>();
        players.add(james) ;

        Card hand1 = new Card(Card.Rank.SIX, Card.Suit.HEARTS) ;
        Card hand2 = new Card(Card.Rank.TEN, Card.Suit.SPADES) ;
        Card hand3 = new Card(Card.Rank.SIX, Card.Suit.DIAMONDS) ;
        james.dealToHand(hand1) ;
        james.dealToHand(hand2) ;
        james.dealToHand(hand3) ;

        PlayerSummary playerSummaryJames = new PlayerSummary(james.getName(), 
                james.getHand().size(), 
                Collections.unmodifiableList(james.getFaceUp()), 
                james.getFaceDown().size(), james.hasCards()) ;

        List<PlayerSummary> playerSummaries = new ArrayList<PlayerSummary>() ;
        playerSummaries.add(playerSummaryJames) ;

        PlayerHelper helper = new PlayerHelper(0, 1, 1, numCardsPerHand, 0, new Stack<Card>(), new ArrayList<Card>(), playerSummaries) ;

        List<Integer> choices = james.askCardChoiceFromHand(helper) ;
        List<Card> chosenCards = new ArrayList<Card>() ;

        chosenCards.add(james.getHand().get(choices.get(0))) ;
        chosenCards.add(james.getHand().get(choices.get(1))) ;


        assertThat(choices.size(), is(2)) ;
        assertThat(chosenCards.contains(new Card(Card.Rank.SIX, Card.Suit.HEARTS)), is(true)) ;
        assertThat(chosenCards.contains(new Card(Card.Rank.SIX, Card.Suit.DIAMONDS)), is(true)) ;
    }

    @Test
    public void picksLowestOverSpecial() {
        int numCardsPerHand = 4 ;
        Player james = new SimplePlayer("James", numCardsPerHand) ;
        List<Player> players = new ArrayList<Player>();
        players.add(james) ;

        Card hand1 = new Card(Card.Rank.TWO, Card.Suit.SPADES) ;
        Card hand2 = new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS) ;
        Card hand3 = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
        Card hand4 = new Card(Card.Rank.THREE, Card.Suit.HEARTS) ;
        james.dealToHand(hand1) ;
        james.dealToHand(hand2) ;
        james.dealToHand(hand3) ;
        james.dealToHand(hand4) ;
        james.sortHand() ;

        PlayerSummary playerSummaryJames = new PlayerSummary(james.getName(), 
                james.getHand().size(), 
                Collections.unmodifiableList(james.getFaceUp()), 
                james.getFaceDown().size(), james.hasCards()) ;

        List<PlayerSummary> playerSummaries = new ArrayList<PlayerSummary>() ;
        playerSummaries.add(playerSummaryJames) ;

        PlayerHelper helper = new PlayerHelper(0, 1, 1, numCardsPerHand, 0, new Stack<Card>(), new ArrayList<Card>(), playerSummaries) ;

        List<Integer> choices = james.askCardChoiceFromHand(helper) ;

        Card chosenCard = james.getHand().get(choices.get(0)) ;


        assertThat(choices.size(), is(1)) ;
        assertThat(chosenCard.equals(new Card(Card.Rank.THREE, Card.Suit.HEARTS)), is(true)) ;
    }

    @Test
    public void picksOneCardWhenAllSpecial() {
        int numCardsPerHand = 4 ;
        Player james = new SimplePlayer("James", numCardsPerHand) ;
        List<Player> players = new ArrayList<Player>();
        players.add(james) ;

        Card hand1 = new Card(Card.Rank.TWO, Card.Suit.SPADES) ;
        Card hand2 = new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS) ;
        Card hand3 = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
        james.dealToHand(hand1) ;
        james.dealToHand(hand2) ;
        james.dealToHand(hand3) ;

        PlayerSummary playerSummaryJames = new PlayerSummary(james.getName(), 
                james.getHand().size(), 
                Collections.unmodifiableList(james.getFaceUp()), 
                james.getFaceDown().size(), james.hasCards()) ;

        List<PlayerSummary> playerSummaries = new ArrayList<PlayerSummary>() ;
        playerSummaries.add(playerSummaryJames) ;

        PlayerHelper helper = new PlayerHelper(0, 1, 1, numCardsPerHand, 0, new Stack<Card>(), new ArrayList<Card>(), playerSummaries) ;

        List<Integer> choices = james.askCardChoiceFromHand(helper) ;
        assertThat(choices.size(), is(1)) ;
    }

    @Test
    public void picksOneCardWhenAllSpecialAndTwoSameRank() {
        int numCardsPerHand = 4 ;
        Player james = new SimplePlayer("James", numCardsPerHand) ;
        List<Player> players = new ArrayList<Player>();
        players.add(james) ;

        Card hand1 = new Card(Card.Rank.TWO, Card.Suit.SPADES) ;
        Card hand2 = new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS) ;
        Card hand3 = new Card(Card.Rank.TWO, Card.Suit.DIAMONDS) ;
        james.dealToHand(hand1) ;
        james.dealToHand(hand2) ;
        james.dealToHand(hand3) ;

        PlayerSummary playerSummaryJames = new PlayerSummary(james.getName(), 
                james.getHand().size(), 
                Collections.unmodifiableList(james.getFaceUp()), 
                james.getFaceDown().size(), james.hasCards()) ;

        List<PlayerSummary> playerSummaries = new ArrayList<PlayerSummary>() ;
        playerSummaries.add(playerSummaryJames) ;

        PlayerHelper helper = new PlayerHelper(0, 1, 1, numCardsPerHand, 0, new Stack<Card>(), new ArrayList<Card>(), playerSummaries) ;

        List<Integer> choices = james.askCardChoiceFromHand(helper) ;
        assertThat(choices.size(), is(1)) ;
    }

}
