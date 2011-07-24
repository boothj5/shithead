package com.boothj5.shithead.game.player;

import java.util.* ;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.card.Card;

/**
 * Player interface, all players must implement this interface
 * 
 * @author james
 *
 */
public interface Player {
	
	/** 
	 * Find out of this player is human, or a computer player
	 * 
	 * @return true if the player is a computer player
	 */
	public boolean isComputer() ;
	
	/**
	 * Ask if the player wishes to swap cards.
	 * 
	 * At the beginning of the game the player may swap cards between their hand
	 * and their face up hand.
	 * 
	 * @return true if the player wishes to swap cards, false if they don't
	 */
	public boolean askSwapMore() ;
		
	/**
	 * Ask which cards the player wishes to swap.
	 * 
	 * Will be called when the player returns true from askSwapMore
	 * 
	 * @return SwapResponse detailing the choice of cards to swap
	 */
	public SwapResponse askSwapChoice() throws ShitheadException;
	
	/**
	 * Ask the player to swap cards between hand and face up hand,
	 * based on the SwapResponse
	 * 
	 * @param swapResponse Representing the cards to swap
	 */
	public void swapCards(SwapResponse swapResponse) ;
	
	/**
	 * Asks the player which card(s) they wish to lay from their hand
	 * 
	 * The game will only ask this question when there exists cards in the
	 * players hands and there is at least one valid move that can be made.
	 * 
	 * @param helper The state of the current game
	 * @return A list of integers representing the cards in the hand, the first card is 0
	 */
	public List<Integer> askCardChoiceFromHand(PlayerHelper helper) ;
	
	/**
	 * Asks the player which card(s) they wish to lay from their face up hand
	 * 
	 * The game will only ask this question when there are no cards in the players
	 * hand, and there exists cards in the players face up hand and there is at 
	 * least one valid move that can be made.
	 * 
	 * @param helper The state of the current game
	 * @return A list of integers representing the cards in the face up hand, 
	 * the first card is 0
	 */
	public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) ;
	
	/**
	 * Get the players name
	 * 
	 * @return The players name
	 */
	public String getName() ;

	/**
	 * Get the players face down hand.  Cards are indexed from 0
	 * 
	 * @return A list of Card representing the players face down hand
	 */
	public List<Card> getFaceDown() ;

	/**
	 * Get the players face up hand.  Cards are indexed from 0
	 * 
	 * @return A list of Card representing the players face up hand
	 */
	public List<Card> getFaceUp() ;

	/**
	 * Get the players hand.  Cards are indexed from 0
	 * 
	 * @return A list of Card representing the players hand
	 */
	public List<Card> getHand() ;
	
	/**
	 * Whether or not the player has any cards in any of their hands
	 * @return true if the player has card, false otherwise
	 */
	public boolean hasCards() ;
	
	/**
	 * Receive cards from the game, e.g. when they must pick up the pile.
	 * These cards must be placed in the players hand
	 * 
	 * @param cards List of Card representing the cards the player must receive
	 */
	public void recieve(List<Card> cards) ;

	/**
	 * The player must place the Card in their hand.  
	 * Called during the deal at the beginning of the game
	 * 
	 * @param card Card dealt
	 */
	public void dealToHand(Card card) ;

	/**
	 * The player must place the Card in their face up hand.  
	 * Called during the deal at the beginning of the game
	 * 
	 * @param card Card dealt
	 */
	public void dealToFaceUp(Card card) ;

	/**
	 * The player must place the Card in their face down hand.  
	 * Called during the deal at the beginning of the game
	 * 
	 * @param card Card dealt
	 */
	public void dealToFaceDown(Card card) ;
	
}
