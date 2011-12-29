package com.boothj5.shithead.game.player.computer;

import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.junit.Test ;

import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.player.HumanPlayer;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.PlayerSummary;
import com.boothj5.shithead.game.player.computer.FaceDownChecker;

import static org.junit.Assert.* ;


public class FaceDownCheckerTest {

	@Test
	public void picksLowWhenOtherPlayerNotPlayingFromFaceDown() {
		int numCardsPerHand = 3 ;
		List<Player> players = new ArrayList<Player>();
		Player faceDown = new FaceDownChecker("FaceDown", numCardsPerHand) ;
		Player other = new HumanPlayer("James", numCardsPerHand) ;
		players.add(faceDown) ;
		players.add(other) ;
		
		Card otherHand1 = new Card(Card.Rank.THREE, Card.Suit.SPADES) ;
		other.dealToHand(otherHand1) ;
		
		Card hand1 = new Card(Card.Rank.FOUR, Card.Suit.SPADES) ;
		Card hand2 = new Card(Card.Rank.NINE, Card.Suit.SPADES) ;
		faceDown.dealToHand(hand1) ;
		faceDown.dealToHand(hand2) ;

		PlayerSummary playerSummaryFaceDown = new PlayerSummary(faceDown.getName(), 
				faceDown.getHand().size(), 
				Collections.unmodifiableList(faceDown.getFaceUp()), 
				faceDown.getFaceDown().size(), faceDown.hasCards()) ;
		
		PlayerSummary playerSummaryOther = new PlayerSummary(other.getName(), 
				other.getHand().size(), 
				Collections.unmodifiableList(other.getFaceUp()), 
				other.getFaceDown().size(), other.hasCards()) ;

		List<PlayerSummary> playerSummaries = new ArrayList<PlayerSummary>() ;
		playerSummaries.add(playerSummaryFaceDown) ;
		playerSummaries.add(playerSummaryOther) ;
		
		PlayerHelper helper = new PlayerHelper(0, 2, 2, numCardsPerHand, 0, new Stack<Card>(), new ArrayList<Card>(), playerSummaries) ;

		List<Integer> choices = faceDown.askCardChoiceFromHand(helper) ;
		Card chosenCard = faceDown.getHand().get(choices.get(0)) ;
		
		assertThat(choices.size(), is(1)) ;
		assertThat(chosenCard.equals(new Card(Card.Rank.FOUR, Card.Suit.SPADES)), is(true)) ;
	}
	
	@Test
	public void picksHighWhenOtherPlayerPlayingFromFaceDown() {
		int numCardsPerHand = 3 ;
		List<Player> players = new ArrayList<Player>();
		Player checker = new FaceDownChecker("James", numCardsPerHand) ;
		Player other = new HumanPlayer("James", numCardsPerHand) ;
		players.add(checker) ;
		players.add(other) ;
		
		Card otherHand1 = new Card(Card.Rank.THREE, Card.Suit.SPADES) ;
		other.dealToFaceDown(otherHand1) ;
		
		Card hand1 = new Card(Card.Rank.FOUR, Card.Suit.SPADES) ;
		Card hand2 = new Card(Card.Rank.NINE, Card.Suit.SPADES) ;
		checker.dealToHand(hand1) ;
		checker.dealToHand(hand2) ;

		PlayerSummary playerSummaryFaceDown = new PlayerSummary(checker.getName(), 
				checker.getHand().size(), 
				Collections.unmodifiableList(checker.getFaceUp()), 
				checker.getFaceDown().size(), checker.hasCards()) ;
		
		PlayerSummary playerSummaryOther = new PlayerSummary(other.getName(), 
				other.getHand().size(), 
				Collections.unmodifiableList(other.getFaceUp()), 
				other.getFaceDown().size(), other.hasCards()) ;

		List<PlayerSummary> playerSummaries = new ArrayList<PlayerSummary>() ;
		playerSummaries.add(playerSummaryFaceDown) ;
		playerSummaries.add(playerSummaryOther) ;
		
		PlayerHelper helper = new PlayerHelper(0, 2, 2, numCardsPerHand, 0, new Stack<Card>(), new ArrayList<Card>(), playerSummaries) ;

		List<Integer> choices = checker.askCardChoiceFromHand(helper) ;
		Card chosenCard = checker.getHand().get(choices.get(0)) ;
		
		assertThat(choices.size(), is(1)) ;
		assertThat(chosenCard.equals(new Card(Card.Rank.NINE, Card.Suit.SPADES)), is(true)) ;
	}

}
