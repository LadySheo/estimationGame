package project.controller;
import project.model.*;

import java.io.*;
import java.util.*;

/**
 * Representation of the player.
 *
 * @author G1T13
 */

public class Player{
    private Hand hand;
    private String name;

    /**
     * constructor
     * @param name player name
     * @param hand player hand
     */
    public Player(String name, Hand hand){
        this.name = name;
        this.hand = new Hand();
    }

    /**
     * Prompt player to play a card.
     * Calls function that allows player to input card that they want to play.
     * Remove card from player's hand if inputted card exists.
     * @param playersList list of players in order that cards are played
     * @param t instance of the current trick
     * @return cardToPlay the card the player has decided to play
     */
    public Card playCard(List<Player> playersList, Trick t){
        Suit trumpSuit = t.getTrumpSuit();
        Suit leadingSuit = t.getLeadingSuit();
        Card cardToPlay = inputCardPlay(trumpSuit, leadingSuit, playersList);
        hand.removeCard(cardToPlay);
        return cardToPlay;
    }

    /**
     * Allows player to input desired bid amount based on list of allowed bids.
     * @param bidsAllowed List of integers of bids that player can place
     * @param t instance of the current trick
     * @return output hashmap of player's name and bid amount
     */
    public Map<String,Integer> placeBid(List<Integer> bidsAllowed, Trick t){
        int playerBid = 0;
        playerBid = inputBidAmount(bidsAllowed);
        while (!bidsAllowed.contains(playerBid)) {
            System.out.println("Bid not allowed. Please choose a bid from " + bidsAllowed.toString());
            playerBid = inputBidAmount(bidsAllowed);
        }
        Map<String,Integer> output = new HashMap<String, Integer>();
        output.put(name, playerBid);
        return output;
    }
  
    /**
     * Allows player to input the bid amount they want to bid.
     * Validates if input is a valid input (positive integer bigger or equals to 0).
     * @param bidsAllowed List of integer of bids allowed
     * @return int of bid amount
     */
    public int inputBidAmount(List<Integer> bidsAllowed){
        Scanner sc = new Scanner(System.in);
        boolean bidPlaced = false;
        int bid = 0;
        while (!bidPlaced) {
            System.out.println("Your hand: " + hand.toString());
            System.out.println("Allowed bids: " + bidsAllowed.toString());
            System.out.print("Please input bid amount: ");
            String bidInput = sc.nextLine();
            try {
                bid = Integer.parseInt(bidInput);
                if (bid < 0) {
                    throw new NumberFormatException();
                }
                bidPlaced = true;
            } catch (NumberFormatException e) {
                System.out.println("That is not a valid input! Input must be a positive integer.");
            }
        }
        
        return bid;
    }
  
    /**
     * Allows player to input rank and suit of the card they want to play from their hand.
     * Validates if card exists in player's hand.
     * @param trumpSuit trump suit
     * @param leadingSuit leading suit
     * @param playersList list of player objects
     * @return card to be played
     */
    public Card inputCardPlay(Suit trumpSuit, Suit leadingSuit, List<Player> playersList){
        Card cardToPlay = null;
        while (cardToPlay == null) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Your hand: " + hand.toString());
            System.out.println("Please enter the rank of the card you want to play: E.g. ace");
            String userInputRank = sc.nextLine();
            String[] validRanks = {"ace", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king"};
            while (!Arrays.asList(validRanks).contains(userInputRank.toLowerCase())) {
                System.out.println("Invalid rank entered. Please enter a valid rank:");
                userInputRank = sc.nextLine();
            }
            System.out.println("Please enter the suit of the card you want to play: E.g. clubs ");
            String userInputSuit = sc.nextLine();
            String[] validSuits = {"clubs", "diamonds", "hearts", "spades"};
            while (!Arrays.asList(validSuits).contains(userInputSuit.toLowerCase())) {
                System.out.println("Invalid suit entered. Please enter a valid suit:");
                userInputSuit = sc.nextLine();
            }
            String cardName = userInputRank.substring(0,1).toUpperCase() + userInputRank.substring(1).toLowerCase()  + " of " + userInputSuit.substring(0,1).toUpperCase() + userInputSuit.substring(1).toLowerCase();
            for (int i=0; i<hand.getNumberOfCards(); i++) {
                Card currentCard = hand.getCard(i);
                if (currentCard.toString().equals(cardName)) {
                    if (currentCard.getSuit().equals(trumpSuit) || currentCard.getSuit().equals(leadingSuit) || playersList.indexOf(this) == 0) {
                        cardToPlay = currentCard;
                    } else {
                        for (int j=0; j<hand.getNumberOfCards(); j++) {
                            Card currentCard2 = hand.getCard(j);
                            if (!currentCard2.toString().equals(cardName) && (currentCard2.getSuit().equals(trumpSuit) || currentCard2.getSuit().equals(leadingSuit))) {
                                System.out.println();
                                System.out.println("You must play a trump or leading suit first!");
                                System.out.println();
                                break;
                            }
                            else if (j == hand.getNumberOfCards()-1 && !currentCard2.getSuit().equals(trumpSuit) && !currentCard2.getSuit().equals(leadingSuit)) {
                                cardToPlay = currentCard;
                            }
                        }
                    }
                } 
                else if (i == hand.getNumberOfCards()-1 && !currentCard.toString().equals(cardName) && currentCard == null) {
                    System.out.println();
                    System.out.println("You don't have this card in your hand!");
                    System.out.println();
                }
            }
        }
        return cardToPlay;
    }

    /**
     * @return name player's name
     */
    public String getName(){
        return this.name;
    }

    /**
     * @return hand player's hand
     */
    public Hand getHand() {
        return hand;
    }
}