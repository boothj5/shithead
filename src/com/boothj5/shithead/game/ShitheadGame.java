package com.boothj5.shithead.game;

import java.util.* ;

import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.Deck;
import com.boothj5.shithead.game.card.ShitheadCardComparator;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerFactory;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.PlayerSummary;
import static com.boothj5.shithead.game.ShitheadRules.canLay;
import static com.boothj5.util.IterationUtil.*;

public final class ShitheadGame {
    private final List<Player> players = new ArrayList<Player>() ;
    private final Stack<Card> pile = new Stack<Card>() ;
    private final List<Card> burnt = new ArrayList<Card>() ;
    private final Deck deck ;
    private final int numPlayers ;
    private final int numCardsPerHand ;
    private int currentPlayer ;
    private LastMove lastMove ;

    public ShitheadGame(List<String> playerNames, List<String> playerTypes, int cardsPerHand) 
            throws ShitheadException {
        numPlayers = playerNames.size() ;
        numCardsPerHand = cardsPerHand ;
        deck = new Deck(numPlayers * numCardsPerHand * 3) ;
        currentPlayer = 0 ;
        lastMove = null ;

        for (int i : doTimes(numPlayers)) {
            String playerType = playerTypes.get(i) ;
            String playerName = playerNames.get(i) ;
            Player player = PlayerFactory.createPlayer(playerType, playerName, numCardsPerHand) ;
            players.add(player) ;
        }
    }

    public Stack<Card> getPile() {
        return pile ;
    }
    
    public Deck getDeck() {
        return deck ;
    }
    
    public List<Card> getBurnt() {
        return burnt ;
    }
    
    public List<Player> getPlayers() {
        return players ;
    }
    
    public LastMove getLastMove() {
        return lastMove ;
    }
    
    public int getNumCardsPerHand() {
        return numCardsPerHand ;
    }
    
    public void deal() {
        for (Player player : players) {
            for (int i : doTimes(numCardsPerHand)) {
                player.dealToHand(deck.takeCard()) ;
                player.dealToFaceUp(deck.takeCard()) ;
                player.dealToFaceDown(deck.takeCard()) ;
            }
            player.sortHand() ;
        }
    }

    public void firstMove() {
        currentPlayer = playerWithLowest() ;
        Player player = getCurrentPlayer() ;
        List<Card> cardsToLay = player.getAllOfSameRankFromHand(player.getHand().get(0));
        playFromHand(cardsToLay) ;
        moveToNextPlayer() ;
    }

    public boolean currentPlayerCanMove() {
        if (pile.isEmpty())
            return true ;

        Player player = getCurrentPlayer() ;
        if (player.hasCardsInHand())
            return canMoveWithOneOf(player.getHand());
        else if (player.hasCardsInFaceUp()) 
            return canMoveWithOneOf(player.getFaceUp());
        else if (player.hasCardsInFaceDown()) 
            return true ;
        else 
            return false ;
    }

    public boolean validMove(List<Integer> choice) {
        List<Card> cardsToLay = getCardsFromIndexes(choice) ;
        if (!Card.allRanksEqual(cardsToLay)) 
            return false ;
        else
            return canLay(cardsToLay.get(0), pile) ;
    }	

    public void playerPickUpPile() {
        Player currentPlayer = getCurrentPlayer() ;
        currentPlayer.recieve(pile) ;
        pile.removeAllElements() ;
    }

    public void playerPickUpPileAndFaceDownCard(int cardFromFaceDown) {
        playerPickUpPile() ;
        Player currentPlayer = getCurrentPlayer() ;
        currentPlayer.getHand().add(currentPlayer.getFaceDown().get(cardFromFaceDown)) ;
        currentPlayer.getFaceDown().remove(cardFromFaceDown) ;
    }

    public void makeMove(List<Integer> choice) {
        List<Card> cardsToPlay = getCardsFromIndexes(choice) ;
        Player player = getCurrentPlayer() ;

        if (player.playingFromHand()) 
            playFromHand(cardsToPlay) ;
        else if (player.playingFromFaceUp())
            playFromFaceUp(cardsToPlay) ;
        else
            playFromFaceDown(cardsToPlay) ;
    }	

    public void moveToNextPlayer() {
        currentPlayer ++ ;
        if (currentPlayer >= players.size())
            currentPlayer = 0 ;
        while (!getCurrentPlayer().hasCards()) {
            currentPlayer++ ;
            if (currentPlayer >= players.size())
                currentPlayer = 0 ;
        }
    }

    public boolean canContinue() {
        int numPlayersWithCards = 0 ;
        for (Player player : players) 
            if (player.hasCards()) 
                numPlayersWithCards++ ;

        return (numPlayersWithCards > 1) ;
    }

    public String getShithead() throws ShitheadException {
        for (Player player : players) 
            if (player.hasCards()) 
                return player.getName() ;
        throw new ShitheadException("Game finished but no Shithead found!") ;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer) ;
    }

    public PlayerHelper getPlayerHelper() {
        List<PlayerSummary> playerSummaries = new ArrayList<PlayerSummary>() ;

        for (Player player : players) {
            PlayerSummary playerSummary = new PlayerSummary(player.getName(), player.getHandSize(), 
                    Collections.unmodifiableList(player.getFaceUp()), player.getFaceDownSize(), player.hasCards()) ;
            playerSummaries.add(playerSummary) ;
        }

        return new PlayerHelper(deck.size(), numPlayers, numCardsPerHand, currentPlayer, 
                pile, Collections.unmodifiableList(burnt), playerSummaries) ;
    }
    
    private boolean burnIfPossible() {
        boolean didBurn = false ;

        boolean burnCardOnPile = (!pile.empty()) && (pile.peek().getRank().equals(ShitheadRules.BURN)) ;
        boolean fourOfAKindOnPile = (pile.size() >= 4) && 
                ((pile.get(pile.size()-1).getRank().equals(pile.get(pile.size()-2).getRank())) && 
                        (pile.get(pile.size()-2).getRank().equals(pile.get(pile.size()-3).getRank())) &&
                        (pile.get(pile.size()-3).getRank().equals(pile.get(pile.size()-4).getRank()))) ;

        if (burnCardOnPile || fourOfAKindOnPile) {
            currentPlayer-- ;
            if (currentPlayer < 0)
                currentPlayer = numPlayers - 1 ;
            burnt.addAll(pile) ;
            pile.removeAllElements() ;
            didBurn = true ;
        }

        return didBurn ;
    }

    private boolean missAGoIfRequied() {
        boolean missAGo = false ;

        boolean missAGoCardOnPile = (!pile.empty()) && (pile.peek().getRank().equals(ShitheadRules.MISS_A_TURN)) ;
        if (missAGoCardOnPile) {
            moveToNextPlayer();
            missAGo = true ;
        }
        return missAGo ;
    }

    private void playFromHand(List<Card> toPlay) {
        Player player = getCurrentPlayer() ;
        pile.addAll(toPlay) ;
        player.getHand().removeAll(toPlay) ;
        pickupFromDeck(toPlay);
        player.sortHand() ;

        boolean didBurn = burnIfPossible() ;
        boolean missedAGo = missAGoIfRequied() ;
        lastMove = new LastMove(getCurrentPlayer(), toPlay, didBurn, missedAGo) ;
    }	

    private void playFromFaceUp(List<Card> toPlay) {
        Player player = getCurrentPlayer() ;

        pile.addAll(toPlay) ;
        player.getFaceUp().removeAll(toPlay) ;
        pickupFromDeck(toPlay);

        boolean didBurn = burnIfPossible() ;
        boolean missedAGo = missAGoIfRequied() ;
        lastMove = new LastMove(player, toPlay, didBurn, missedAGo) ;
    }

    private void playFromFaceDown(List<Card> toPlay) {
        Player player = getCurrentPlayer() ;

        pile.addAll(toPlay) ;
        player.getFaceDown().removeAll(toPlay) ;
        pickupFromDeck(toPlay);

        boolean didBurn = burnIfPossible() ;
        boolean missedAGo = missAGoIfRequied() ;
        lastMove = new LastMove(player, toPlay, didBurn, missedAGo) ;
    }	

    private void pickupFromDeck(List<Card> toPlay) {
        Player player = getCurrentPlayer() ;
        for (int i : doTimes(toPlay.size())) {
            boolean deckIsEmpty = deck.isEmpty() ;
            boolean playersHandLessThanGameHandSize = 
                    player.getHandSize() < numCardsPerHand ;
            if (!deckIsEmpty && playersHandLessThanGameHandSize) {
                Card pickup = deck.takeCard() ;
                List<Card> pickupList = new ArrayList<Card>();
                pickupList.add(pickup) ;
                player.recieve(pickupList) ;
                player.sortHand() ;
            }
        }
    }	

    private boolean canMoveWithOneOf(List<Card> hand) {
        for (Card card : hand)
            if (canLay(card, pile))
                return true ;
        return false ;
    }

    private int playerWithLowest() {
        // assumes players hands are not empty and are sorted
        int lowestPlayer = 0 ;
        ShitheadCardComparator comp = new ShitheadCardComparator() ;

        for (int i : range(1, players.size())) {
            Card playersLowest = players.get(i).getHand().get(0) ;
            Card currentLowest = players.get(lowestPlayer).getHand().get(0);
            if (comp.compare(playersLowest, currentLowest) < 0)
                lowestPlayer = i ;
        }

        return lowestPlayer ;
    }

    private List<Card> getCardsFromIndexes(List<Integer> cardChoice) {
        Player player = getCurrentPlayer() ;
        List<Card> handToPlayFrom = player.getHandPlayingFrom() ;
        List<Card> returnCards = new ArrayList<Card>() ;

        for (int cardIndex : cardChoice) {
            returnCards.add(handToPlayFrom.get(cardIndex)) ;
        }
        return returnCards ;
    }   

}
