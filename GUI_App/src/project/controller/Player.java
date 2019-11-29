/*
* Player class is for the player to input
*/
package project.controller;
import project.model.*;
import java.io.*;
import java.util.*;

//implements Playable interface
public class Player{
    private Hand hand;

    public String getPlayerMsg() {
        return playerMsg;
    }

    public void setPlayerMsg(String playerMsg) {
        this.playerMsg = playerMsg;
    }

    private String name;
    private String playerMsg = "";

    public Player(String name, Hand hand){
        this.name = name;
        this.hand = new Hand();
    }

    public Card playCard(List<Player> playersList, Trick t){
        Card cardToPlay = inputCardPlay();
        hand.removeCard(cardToPlay);
        return cardToPlay;
    }

    /** 
     * Called from Round 
     * return map to Round to bid
     * map will be added into a list in Round
    */
    //return pair to Round to bid
    public Map<String,Integer> placeBid(List<Integer> bidsAllowed, Trick t){
        int playerBid = 0;
        playerBid = inputBidAmount(bidsAllowed);
        while (!bidsAllowed.contains(playerBid)) {
//            System.out.println("Bid not allowed. Please choose a bid from " + bidsAllowed.toString());
            playerBid = inputBidAmount(bidsAllowed);
        }
        //put bid into BidTracker
        //SEND NAME WITH BID
        Map<String,Integer> output = new HashMap<String, Integer>();
        output.put(name, playerBid);
        return output;
    }


    /** 
     * Internal call from placeBid
     * Allows player to input the bid amount they want to bid
     * Validates if input is a valid input (positive integer >= 0)
    */
    public int inputBidAmount(List<Integer> bidsAllowed){
//        Scanner sc = new Scanner(System.in);
        boolean bidPlaced = false;
        int bid = 0;
        playerMsg += "Your hand: " + hand.toString() +"<br/>";
        playerMsg += "Please select bid amount:<br/>";

//        while (!bidPlaced) {
//            System.out.println("Your hand: " + hand.toString());
//            System.out.println("Allowed bids: " + bidsAllowed.toString());
//            System.out.print("Please input bid amount: ");
//            String bidInput = sc.nextLine();
//            try {
//                bid = Integer.parseInt(bidInput);
//                if (bid < 0) {
//                    throw new NumberFormatException();
//                }
//                bidPlaced = true;
//            } catch (NumberFormatException e) {
////                System.out.println("That is not a valid input! Input must be a positive integer.");
//            }
//        }
        
        return bid;
    }
  
    /** 
     * Internal call
     * Called from Trick
     * Player will be 
    */
    public Card inputCardPlay(){
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
                System.out.println("Invalid suit entered. Please enter a suit rank:");
                userInputSuit = sc.nextLine();
            }
            String cardName = userInputRank.substring(0,1).toUpperCase() + userInputRank.substring(1)  + " of " + userInputSuit.substring(0,1).toUpperCase() + userInputSuit.substring(1);
            for (int i=0; i<hand.getNumberOfCards(); i++) {
                Card currentCard = hand.getCard(i);
                if (currentCard.toString().equals(cardName)) {
                    cardToPlay = currentCard;
                }
            }
        }
        return cardToPlay;
    }

    public String getName(){
        return this.name;
    }

    public Hand getHand() {
        return hand;
    }
}