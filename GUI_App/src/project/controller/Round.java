/**
* Runs the game.
*/
package project.controller;
import project.controller.Trick;
import project.model.*;

import java.util.*;

public class Round{
    private BidTracker bidTracker;

    public String getBidMsg() {
        return bidMsg;
    }
//private Suit trumpSuit; //set by game manager
    
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
    public List<Integer> bidsAllowedForPlayer;

    private int roundNumber; // this will determine the number of tricks that should be played
    private int counter = 0;
    private String bidMsg = "";
    public String playerMsg ="";
    /**
    All possible lists to place bids and play cards depending on who the dealer for the round is
    */

    

    public List<List> iteratingListForDealers = new ArrayList<List>();

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


//        System.out.println("Dealer is " + ((Player)iteratingListForDealers.get(dealerIndex).get(iteratingListForDealers.size()-1)).getName());
    }
    
    public int numOfTricks() {
        int numOfTricks = 0;
        if (roundNumber < 6) {
            numOfTricks = roundNumber;
        } else {
            numOfTricks = 12 - roundNumber;
        }
        return numOfTricks;
    }
    
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
                //printing bids from everyone
                bidsPlaced = placeBids(playOrder, trick);
            }

            //play trick
//            Player winner = trick.callPlayersPlayCard(playOrder);
//            int indexOfWinner = fixedList.indexOf(winner);
//            if (indexOfWinner > 0){
//                playOrder = iteratingListForDealers.get(indexOfWinner-1);
//            } else {
//                playOrder = iteratingListForDealers.get(playOrder.size()-1);
//            }
//            tricksWonMap.put(winner,tricksWonMap.get(winner)+1);
        }
        output.put(tricksWonMap,bidsPlaced);
        return output;
    }


    /**
     * create a list of integers that contains the number of bids that the players are a allowed to place 
     * @param unallowedBidAmount
     * @return
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

    public List<Map<String, Integer>> placeBids(List<Player> playOrder, Trick trick){
        bidTracker.ResetBidTracker();
        List<Map<String, Integer>> bidsPlaced = new ArrayList<Map<String, Integer>>();
        int totalBidAmount = 0;
//        System.out.println("Trump suit: " + trumpSuit);
//        System.out.println();
        for (int i = 0; i < playOrder.size(); i++){
            if (i == playOrder.size()-1) {
                int unallowedBidAmount = numOfTricks();
                if (totalBidAmount <= numOfTricks()) {
                    unallowedBidAmount -= totalBidAmount;
                }
                List<Integer> bidsAllowed = createBidsAllowedList(unallowedBidAmount);
                Map<String, Integer> playerAndBid = playOrder.get(i).placeBid(bidsAllowed, trick);
                bidsAllowedForPlayer = bidsAllowed;
                playerMsg = playOrder.get(i).getPlayerMsg();
                bidMsg += playOrder.get(i).getPlayerMsg();
                bidsPlaced.add(playerAndBid);

//                System.out.println(playOrder.get(i).getName() + " bidded " + playerAndBid.get(playOrder.get(i).getName()));
                bidMsg += playOrder.get(i).getName() + " bidded " + playerAndBid.get(playOrder.get(i).getName()) + "<br/>";
                totalBidAmount += playerAndBid.get(playOrder.get(i).getName());
//                System.out.println();
            } else {
                int unallowedBidAmount = numOfTricks();
                List<Integer> bidsAllowed = createBidsAllowedList(unallowedBidAmount);
                bidsAllowed.add(unallowedBidAmount);
                Map<String, Integer> playerAndBid = playOrder.get(i).placeBid(bidsAllowed, trick);
                bidsAllowedForPlayer = bidsAllowed;
                playerMsg = playOrder.get(i).getPlayerMsg();
                bidMsg += playOrder.get(i).getPlayerMsg();
                bidsPlaced.add(playerAndBid);
//                System.out.println(playOrder.get(i).getName() + " bidded " + playerAndBid.get(playOrder.get(i).getName()));
                bidMsg += playOrder.get(i).getName() + " bidded " + playerAndBid.get(playOrder.get(i).getName())+ "<br/>";
                totalBidAmount += playerAndBid.get(playOrder.get(i).getName());
//                System.out.println();
            }
        }

        bidTracker.placeBids(bidsPlaced);

//        System.out.println("Trump suit: " + trumpSuit.toString());
//        System.out.println("--------------------------------");
        return bidsPlaced;
    }


    public Suit getTrumpSuit() {
        return trumpSuit;
    }

}