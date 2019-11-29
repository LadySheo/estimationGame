/**
* Runs the game.
*/
package project.controller;
import project.model.*;

import java.util.*;

public class AIPlayer extends Player{
    private int bidPlaced = 0;
    
    public AIPlayer(String name, Hand hand){
        super(name,hand);
    }
    
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
        else if (playerList.indexOf((Player) this) == 0) {
            Card cardPlayed = playCardIfFirstPlayer(t, bidPlaced);
            hand.removeCard(cardPlayed);
            return cardPlayed;
        } else {
            Card cardPlayed = playCardIfNotFirstPlayer(t, bidPlaced);
            hand.removeCard(cardPlayed);
            return cardPlayed;
        }
    }
    
    public Card playCardIfFirstPlayer(Trick t, int bidPlaced){
        Suit trumpSuit = t.getTrumpSuit();
        Hand hand = super.getHand();
        if (bidPlaced > 0) {
            Card maxCard = null;
            for (int i=0; i<hand.getNumberOfCards(); i++) {
                Card currentCard = hand.getCard(i);
                if (currentCard.getSuit().compareTo(trumpSuit) != 0) {
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
        } else {
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

    public Card playCardIfNotFirstPlayer(Trick t, int bidPlaced) {
        Suit trumpSuit = t.getTrumpSuit();
        Suit leadingSuit = t.getLeadingSuit();
        Hand hand = super.getHand();
        Card previousCard = t.getPreviousCard();
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
        } else {
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

    public String handToString() {
        return super.getHand().toString();
    }

    //calculate bid that should be placed
    public Map<String,Integer> placeBid(List<Integer> bidsAllowed, Trick t){  
        Suit trumpSuit = t.getTrumpSuit();
        Map<String,Integer> output = new HashMap<>();
        Hand hand = super.getHand();
        String name = super.getName();
        bidsAllowed.sort(null);
        int bid = 0;
        int bidIndex = 0;
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
        if (countOfTrumpAndHigherRank <= 0.25*bidsAllowed.size()) {
            bidIndex = Math.max(bidIndex-2,0);
        }
        else if (countOfTrumpAndHigherRank > 0.25*bidsAllowed.size() && countOfTrumpAndHigherRank <= 0.5*bidsAllowed.size()) {
            bidIndex = Math.max(bidIndex-1,0);
        }
        else if (countOfTrumpAndHigherRank > 0.5*bidsAllowed.size() && countOfTrumpAndHigherRank <= 0.75*bidsAllowed.size()) {
            if (bidsAllowed.size() > 0) {
                bidIndex = Math.min(bidIndex+1,bidsAllowed.size()-1);
            } else {
                bidIndex = 0;
            }
        } else {
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

    public Hand getHand() {
        return super.getHand();
    }
}
