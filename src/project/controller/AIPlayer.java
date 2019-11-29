/**
* Computer player and its strategies
*/
package project.controller;
import project.model.*;

import java.util.*;

/**
 * Representation of an AI player. 
 * 
 * @author G1T13
 */

public class AIPlayer extends Player{
    private int bidPlaced = 0;

    /**
     * @param name name of this aiplayer for mapping
     * @param hand hand of the player
     */
    public AIPlayer(String name, Hand hand){
        super(name,hand);
    }

    /**
     * Checks the bid amount placed by this aiplayer, the cards it has in its hand, as well as which position this aiplayer can play its card,
     * and calls the appropriate function
     * @param playerList players listed in the order that they are playing their card
     * @param t Trick the instance of the trick that the game is currently on
     * @return card to be played
     */
    public Card playCard(List<Player> playerList, Trick t) {
        List<Suit> suitList = new ArrayList<>();
        Suit leadingSuit = t.getLeadingSuit();
        Suit trumpSuit = t.getTrumpSuit();
        Hand hand = super.getHand();
        for (int i=0; i<hand.getNumberOfCards(); i++) {
            Card currentCard = hand.getCard(i);
            if (currentCard.getSuit().equals(t.getLeadingSuit())) {
                suitList.add(currentCard.getSuit());
            }
        }
        /* If the player is void of the lead suit, then discard a card of the highest rank of suit other than TRUMP
        *   suit if has bid 0 ( because the player does not want to win any trick).
        */
        if (!suitList.contains(leadingSuit) && bidPlaced == 0) {
            Card maxCard = null;
            for (int i=0; i<hand.getNumberOfCards(); i++) {
                Card currentCard = hand.getCard(i);
                if (currentCard.getSuit() != trumpSuit) {
                    if (maxCard == null) {
                        maxCard = currentCard;
                    }
                    else if (currentCard.compareTo(maxCard) > 0) {
                        maxCard = currentCard;
                    }
                }
            }
            maxCard = hand.getCard(0);
            hand.removeCard(maxCard);
            return maxCard;
        }   
        // if player is first player
        else if (playerList.indexOf((Player) this) == 0) {
            Card cardPlayed = playCardIfFirstPlayer(t, bidPlaced);
            hand.removeCard(cardPlayed);
            return cardPlayed;
        } else { // if player is not first player
            Card cardPlayed = playCardIfNotFirstPlayer(t, bidPlaced);
            hand.removeCard(cardPlayed);
            return cardPlayed;
        }
    }

    /**
     * Decides which card the AIPlayer should play if it does play the first card of the trick
     * @param t Trick the instance of the trick that the game is currently on
     * @param bidPlaced the bid that is placed by this aiPlayer
     * @return card card that aiPlayer is playing
     */
    public Card playCardIfFirstPlayer(Trick t, int bidPlaced){
        Suit trumpSuit = t.getTrumpSuit();
        Hand hand = super.getHand();
        if (bidPlaced > 0) {
            // pick the card of the highest rank from any suit if the bid is positive
            Card maxCard = null;
            for (int i=0; i<hand.getNumberOfCards(); i++) {
                Card currentCard = hand.getCard(i);
                if (!currentCard.getSuit().equals(trumpSuit)) {
                    if (maxCard == null) {
                        maxCard = currentCard;
                    }
                    if (currentCard.compareTo(maxCard) > 0) {
                        maxCard = currentCard;
                    }
                }
            }
            if (maxCard == null) {
                for (int i=0; i<hand.getNumberOfCards(); i++) {
                    Card currentCard = hand.getCard(i);
                    if (i == 0) {
                        maxCard = currentCard;
                    }
                    if (currentCard.compareTo(maxCard) > 0) {
                        maxCard = currentCard;
                    }
                }
            }
            if (maxCard != null) {
                return maxCard;
            }
        } else { // Else, if the bid is 0, the player can choose to play the smallest rank card (so as to not win the trick)
            Card minCard = null;
            for (int i=0; i<hand.getNumberOfCards(); i++) {
                Card currentCard = hand.getCard(i);
                if (minCard == null) {
                    minCard = currentCard;
                }
                if (currentCard.compareTo(minCard) < 0) {
                    minCard = currentCard;
                }
            }
            if (minCard != null) {
                return minCard;
            }
        }
        return hand.getCard(0);
    }

    /**
     * Decides which card the AIPlayer should play if it does not play the first card of the trick
     * @param t Trick the instance of the trick that the game is currently on
     * @param bidPlaced the bid that is placed by this aiPlayer
     * @return card card that aiPlayer is playing
     */
    public Card playCardIfNotFirstPlayer(Trick t, int bidPlaced) {
        Suit trumpSuit = t.getTrumpSuit();
        Suit leadingSuit = t.getLeadingSuit();
        Hand hand = super.getHand();
        Card previousCard = t.getPreviousCard();
        // pick the card of the lead suit that is higher in rank than that is played or TRUMP card if bids predicted is positive
        if (bidPlaced > 0) {
            ArrayList<Card> cardsThatFulfilCondition = new ArrayList<>();
            for (int i=0; i<hand.getNumberOfCards(); i++) {
                Card currentCard = hand.getCard(i);
                if (currentCard.getSuit().compareTo(t.getPreviousCard().getSuit()) > 0 && currentCard.getSuit().equals(leadingSuit)) {
                    cardsThatFulfilCondition.add(currentCard);
                }
            }
            if (!cardsThatFulfilCondition.isEmpty()) {
                Card minCard1 = null;
                for (Card c: cardsThatFulfilCondition) {
                    if (minCard1 == null) {
                        minCard1 = c;
                    } else {
                        if (c.compareTo(minCard1) < 0) {
                            minCard1 = c;
                        }
                    }
                }
                if (minCard1 != null) {
                    return minCard1;
                }
            } else {
                for (int i=0; i<hand.getNumberOfCards(); i++) {
                    Card currentCard = hand.getCard(i);
                    if (currentCard.getSuit().equals(trumpSuit) && currentCard.getRank().compareTo(t.getPreviousCard().getRank()) > 0) {
                        return currentCard;
                    }
                }
                Card minCard2 = null;
                for (int i=0; i<hand.getNumberOfCards(); i++) {
                    Card currentCard = hand.getCard(i);
                    if (currentCard.getSuit().equals(leadingSuit)) {
                        if (minCard2 == null) {
                            minCard2 = currentCard;
                        }
                        else if (currentCard.compareTo(minCard2) < 0) {
                            minCard2 = currentCard;
                        }
                    }
                }
                if (minCard2 != null) {
                    return minCard2;
                }
                Card minCard3 = null;
                for (int i=0; i<hand.getNumberOfCards(); i++) {
                    Card currentCard = hand.getCard(i);
                    if (currentCard.getSuit().equals(trumpSuit)) {
                        if (minCard3 == null) {
                            minCard3 = currentCard;
                        }
                        else if (currentCard.compareTo(minCard3) < 0) {
                            minCard3 = currentCard;
                        }
                    }
                }
                if (minCard3 != null) {
                    return minCard3;
                }
                Card minCard4 = null;
                for (int i=0; i<hand.getNumberOfCards(); i++) {
                    Card currentCard = hand.getCard(i);
                    if (minCard4 == null) {
                        minCard4 = currentCard;
                    }
                    if (currentCard.compareTo(minCard4) < 0) {
                        minCard4 = currentCard;
                    }
                }
                if (minCard4 != null) {
                    return minCard4;
                }
            }
        } else { // Else choose a ranked card from the lead suit that is smaller than the highest rank that is played so far
            List<Card> cardList = t.getCardList();
            List<Rank> rankList = new ArrayList<>();
            for (Card c: cardList) {
                rankList.add(c.getRank());
            }
            Rank highestRankPlayedSoFar = null;
            for (Rank r: rankList) {
                if (highestRankPlayedSoFar == null) {
                    highestRankPlayedSoFar = r;
                }
                else if (r.compareTo(highestRankPlayedSoFar) > 0) {
                    highestRankPlayedSoFar = r;
                }
            }
            List<Rank> rankListOnHand = new ArrayList<>();
            for (int i=0; i<hand.getNumberOfCards(); i++) {
                rankListOnHand.add(hand.getCard(i).getRank());
            }
            Rank highestRankOnHand = null;
            for (Rank r: rankListOnHand) {
                if (highestRankOnHand == null) {
                    highestRankOnHand = r;
                }
                else if (r.compareTo(highestRankOnHand) > 0) {
                    highestRankOnHand = r;
                }
            }
            for (int i=0; i<hand.getNumberOfCards(); i++) {
                Card currentCard = hand.getCard(i);
                if (currentCard.getSuit().equals(leadingSuit) && currentCard.getRank().compareTo(highestRankPlayedSoFar) < 0 && currentCard.getRank().compareTo(highestRankOnHand) == 0) {
                    return currentCard;
                } 
            }
            for (int i=0; i<hand.getNumberOfCards(); i++) {
                Card currentCard = hand.getCard(i);
                if (currentCard.getSuit().equals(trumpSuit) && currentCard.getRank().compareTo(highestRankPlayedSoFar) < 0 && currentCard.getRank().compareTo(highestRankOnHand) == 0) {
                    return currentCard;
                }
            }
            for (int i=0; i<hand.getNumberOfCards(); i++) {
                Card currentCard = hand.getCard(i);
                if (currentCard.getSuit().equals(trumpSuit) && currentCard.getRank().compareTo(highestRankOnHand) == 0) {
                    return currentCard;
                }
                else if (currentCard.getSuit().equals(leadingSuit) && currentCard.getRank().compareTo(highestRankOnHand) == 0) {
                    return currentCard;
                }
            }
            Card maxCard5 = null;
            for (int i=0; i<hand.getNumberOfCards(); i++) {
                Card currentCard = hand.getCard(i);
                if (currentCard.getSuit().compareTo(trumpSuit) == 0) {
                    if (maxCard5 != null ) {
                        if (currentCard.compareTo(maxCard5) > 0) {
                            maxCard5 = currentCard;
                        }
                    } else {
                        maxCard5 = currentCard;
                    }
                }
            }
            if (maxCard5 != null) {
                return maxCard5;
            }
            for (int i=0; i<hand.getNumberOfCards(); i++) {
                Card currentCard = hand.getCard(i);
                if (currentCard.getSuit().compareTo(leadingSuit) == 0) {
                    if (maxCard5 != null ) {
                        if (currentCard.compareTo(maxCard5) > 0) {
                            maxCard5 = currentCard;
                        }
                    } else {
                        maxCard5 = currentCard;
                    }
                }
            }
            if (maxCard5 != null) {
                return maxCard5;
            }
        }
        return hand.getCard(0);
    }

    /**
     * @return String of hand
     */
    public String handToString() {
        return super.getHand().toString();
    }

    /**
   * Calculates bid that should be placed per game round
   * @param bidsAllowed List of integer of bids allowed
   * @param t instance of trick
   * @return Map of string of player name as key and bid as value 
   */
    public Map<String,Integer> placeBid(List<Integer> bidsAllowed, Trick t){  
        Suit trumpSuit = t.getTrumpSuit();
        Map<String,Integer> output = new HashMap<>();
        Hand hand = super.getHand();
        String name = super.getName();
        bidsAllowed.sort(null);
        int bid = 0;
        int bidIndex = 0;
        // Ensures that only allowed bids can be placed
        if (bidsAllowed.size() % 2 == 0) {
            bidIndex = (bidsAllowed.size()/2)-1;
        } else {
            bidIndex = Math.floorDiv(bidsAllowed.size(),2);
        }
        int countOfTrumpAndHigherRank = 0;
        for (int i=0; i<hand.getNumberOfCards(); i++) {
            Card currentCard = hand.getCard(i);
            if (currentCard.getSuit().equals(trumpSuit) || currentCard.getRank().compareTo(Rank.NINE) >= 0) {
                countOfTrumpAndHigherRank += 1;
            }
        }
        // if no. of trump and higher level cards do not exceed 25%, lower bid by 2 places from median
        if (countOfTrumpAndHigherRank <= 0.25*bidsAllowed.size()) {
            bidIndex = Math.max(bidIndex-2,0);
        }
        // else if no. of trump and higher level cards do not exceed 50%, lower bid by 1 place from median
        else if (countOfTrumpAndHigherRank > 0.25*bidsAllowed.size() && countOfTrumpAndHigherRank <= 0.5*bidsAllowed.size()) {
            bidIndex = Math.max(bidIndex-1,0);
        }
        // if no. of trump and higher level cards do not exceed 75%, riase bid by 1 place from median
        else if (countOfTrumpAndHigherRank > 0.5*bidsAllowed.size() && countOfTrumpAndHigherRank <= 0.75*bidsAllowed.size()) {
            if (bidsAllowed.size() > 0) {
                bidIndex = Math.min(bidIndex+1,bidsAllowed.size()-1);
            } else {
                bidIndex = 0;
            }
        } else { // else, raise bid by 2 places from median
            if (bidsAllowed.size() > 0) {
                bidIndex = Math.min(bidIndex+2,bidsAllowed.size()-1);
            } else {
                bidIndex = 0;
            }
        }
        output.put(name,bidsAllowed.get(bidIndex));
        bidPlaced += output.get(name);
        return output;
    }

    /**
     * @return aiplayer's hand
     */
    public Hand getHand() {
        return super.getHand();
    }

    public void setBidPlaced (int bidPlaced){ this.bidPlaced = bidPlaced; }
}
