package project.controller;
import project.controller.Trick;
import project.model.*;

import java.util.*;

/**
 * Class to create and run an instance of a round.
 *
 * @author G1T13
 */

public class Round{
    private BidTracker bidTracker;
    
    private int dealerIndex; //player is set to dealer by default
    /**
    * when index is 0, player is set to dealer
    * when index is 1, aiOne is set to dealer
    * when index is 2, aiTwo is set to dealer
    * when index is 3, aiThree is set to dealer
     */

    private Player player;
    private Player aiOne;
    private Player aiTwo;
    private Player aiThree;

    private Suit trumpSuit;

    private int roundNumber; // this will determine the number of tricks that should be played
    private int counter = 0;

    /**
    All possible lists to place bids and play cards depending on who the dealer for the round is
    */
    public List<List> iteratingListForDealers = new ArrayList<List>();

    /**
     * constructor
     * @param bidTracker bid tracker
     * @param roundNumber round number
     * @param player human player
     * @param aiOne AI player 1
     * @param aiTwo AI player 2
     * @param aiThree AI player 3
     * @param trumpSuit trump suit
     * @param dealerIndex dealer index
     */
    public Round(BidTracker bidTracker, int roundNumber, Player player, Player aiOne, Player aiTwo, Player aiThree, Suit trumpSuit, int dealerIndex){
        this.bidTracker = bidTracker;
        this.roundNumber = roundNumber;
        this.player = player;
        this.aiOne = aiOne;
        this.aiTwo = aiTwo;
        this.aiThree = aiThree;
        this.trumpSuit = trumpSuit;
        this.dealerIndex = dealerIndex;
        List DEALER_PLAYER = new ArrayList<>(Arrays.asList(aiOne, aiTwo, aiThree, player));
        List DEALER_AIONE = new ArrayList<>(Arrays.asList(aiTwo, aiThree, player, aiOne));
        List DEALER_AITWO = new ArrayList<>(Arrays.asList(aiThree, player, aiOne, aiTwo));
        List DEALER_AITHREE = new ArrayList<>(Arrays.asList(player, aiOne, aiTwo, aiThree));
        iteratingListForDealers.add(DEALER_PLAYER);
        iteratingListForDealers.add(DEALER_AIONE);
        iteratingListForDealers.add(DEALER_AITWO);
        iteratingListForDealers.add(DEALER_AITHREE);
        System.out.println("Dealer is " + ((Player)iteratingListForDealers.get(dealerIndex).get(iteratingListForDealers.size()-1)).getName());
    }

    /**
     * Calculate the number of tricks being played in the current round to decide number of cards to deal
     * @return numOfTricks
     */
    public int numOfTricks() {
        int numOfTricks = 0;
        if (roundNumber < 6) {
            numOfTricks = roundNumber;
        } else {
            numOfTricks = 12 - roundNumber;
        }
        return numOfTricks;
    }

    /**
     * Creates and runs an instance of a trick.
     * Allows players to bid before the start of the trick if the trick created is the first trick of the round.
     * Runs the trick, and at the end of each trick, track the winner in a hashmap
     * Bids and tricks won are then stored in a hashmap
     * @return output hashmap of players' bids and number of tricks won respectively
     */
    public Map<Map<Player,Integer>,List<Map<String, Integer>>> callPlayersPlayTrick(){
        //iterate through correct final list depending on who dealer and call the various players to place their bids
        List<Player> bidOrder = iteratingListForDealers.get(dealerIndex);
        List<Player> playOrder = iteratingListForDealers.get(dealerIndex);
        List<Player> fixedList = iteratingListForDealers.get(iteratingListForDealers.size()-1);
        Map<Player,Integer> tricksWonMap = new HashMap<>();
        tricksWonMap.put(player,0);
        tricksWonMap.put(aiOne, 0);
        tricksWonMap.put(aiTwo,0);
        tricksWonMap.put(aiThree,0);
        List<Map<String, Integer>> bidsPlaced = new ArrayList<>();
        Map<Map<Player,Integer>,List<Map<String, Integer>>> output = new HashMap<>();
        for (int j = 0; j < numOfTricks(); j++){
            Trick trick = new Trick(bidOrder, trumpSuit);

            //before the first trick, allow bids to be placed.
            if (j == 0){
                bidsPlaced = placeBids(playOrder, trick);
            }

            //play trick
            Player winner = trick.callPlayersPlayCard(playOrder);
            int indexOfWinner = fixedList.indexOf(winner);
            if (indexOfWinner > 0){ 
                playOrder = iteratingListForDealers.get(indexOfWinner-1);
            } else {
                playOrder = iteratingListForDealers.get(playOrder.size()-1);
            }
            tricksWonMap.put(winner,tricksWonMap.get(winner)+1);
        }
        output.put(tricksWonMap,bidsPlaced);
        return output;
    }


    /**
     * Creates a list of integers that contains the number of bids that the players are allowed to place.
     * @param unallowedBidAmount bid amount that players are not allowed to bid as this would cause total number of
     *                           bids to be above total number of tricks or equals to the number of tricks
     * @return bidsAllowedList
     */
    public List<Integer> createBidsAllowedList(int unallowedBidAmount){
        List<Integer> bidsAllowedList = new ArrayList<Integer>();
        for (int i = 0; i < unallowedBidAmount; i++){
            bidsAllowedList.add(i);
        }
        if (unallowedBidAmount < numOfTricks()) {
            for (int i = unallowedBidAmount; i < numOfTricks(); i++) {
                bidsAllowedList.add(i);
            }
        }
        return bidsAllowedList;
    }


    /**
     * Calls players' functions to place bid at the start of the round.
     * @param playOrder list of players in the order that they are bidding, starting from left of the dealer
     * @param trick the instance of the trick being played
     * @return bidsPlaced a list constaining a hasmap of the player and their respective bid for the round
     */
    public List<Map<String, Integer>> placeBids(List<Player> playOrder, Trick trick){
        bidTracker.resetBidTracker();
        List<Map<String, Integer>> bidsPlaced = new ArrayList<Map<String, Integer>>();
        int totalBidAmount = 0;
        System.out.println("Trump suit: " + trumpSuit);
        System.out.println();
        for (int i = 0; i < playOrder.size(); i++){
            if (i == playOrder.size()-1) {
                int unallowedBidAmount = numOfTricks();
                if (totalBidAmount <= numOfTricks()) {
                    unallowedBidAmount -= totalBidAmount;
                }
                List<Integer> bidsAllowed = createBidsAllowedList(unallowedBidAmount);
                Map<String, Integer> playerAndBid = playOrder.get(i).placeBid(bidsAllowed, trick);
                bidsPlaced.add(playerAndBid);
                System.out.println(playOrder.get(i).getName() + " bidded " + playerAndBid.get(playOrder.get(i).getName()));
                totalBidAmount += playerAndBid.get(playOrder.get(i).getName());
                System.out.println();
            } else {
                int unallowedBidAmount = numOfTricks();
                List<Integer> bidsAllowed = createBidsAllowedList(unallowedBidAmount);
                bidsAllowed.add(unallowedBidAmount);
                Map<String, Integer> playerAndBid = playOrder.get(i).placeBid(bidsAllowed, trick);
                bidsPlaced.add(playerAndBid);
                System.out.println(playOrder.get(i).getName() + " bidded " + playerAndBid.get(playOrder.get(i).getName()));
                totalBidAmount += playerAndBid.get(playOrder.get(i).getName());
                System.out.println();
            }
        }

        bidTracker.placeBids(bidsPlaced);
        System.out.println("Trump suit: " + trumpSuit.toString());
        System.out.println("--------------------------------");
        return bidsPlaced;
    }


    /**
     * @return trumpsuit trump suit of the round
     */
    public Suit getTrumpSuit() {
        return trumpSuit;
    }

}