/**
 * Class to keep track of bids placed by players at the start of every round.
 *
 * @author G1T13
*/
package project.controller;
import project.model.*;
import java.util.*;

public class BidTracker{
    //bids placed and won tracked by name
    private Map<String, Integer> bidsPlaced = new HashMap<String, Integer>();
    private Map<String, Integer> bidsWon = new HashMap<String, Integer>();

    //constructor
    public BidTracker(){
        bidsPlaced.put("Player", 0);
        bidsPlaced.put("AIOne", 0);
        bidsPlaced.put("AITwo", 0);
        bidsPlaced.put("AIThree", 0);

        bidsWon.put("Player", 0);
        bidsWon.put("AIOne", 0);
        bidsWon.put("AITwo", 0);
        bidsWon.put("AIThree", 0);
    }

    /** 
     * Stores the bids of players for the current round in a hashmap
     * @param placedBidsList a list of hashmap that stores the player's name and their repsective bids for the current round
     */
    public void placeBids(List<Map<String, Integer>> placedBidsList){
        for (int i = 0; i < placedBidsList.size(); i++){
            for (Map.Entry<String,Integer> placedBidsEntry: placedBidsList.get(i).entrySet()){
                for (Map.Entry<String, Integer> bidsPlacedEntry: bidsPlaced.entrySet()){
                    //if the string name in the pair is the same as the hashmap's
                    if (placedBidsEntry.getKey() == bidsPlacedEntry.getKey()){
                        //add the bid amount to the correct place in the hashmap
                        bidsPlaced.put(placedBidsEntry.getKey(), placedBidsEntry.getValue());
                        break;
                    }
                }
            }    
        }
    }

    /**
     * To return the total number of bids already placedby previous players.
     * @return totalBids
     */
    public int checkTotalBidsPlaced(){
        int totalBids = 0;
        for (int placedBids: bidsPlaced.values()){
            totalBids += placedBids;
        }

        return totalBids;
    }

    /**
    * To reset all the bids placed and won back to zero for the next round
    */
    public void resetBidTracker(){
        bidsPlaced.put("Player", 0);
        bidsPlaced.put("AIOne", 0);
        bidsPlaced.put("AITwo", 0);
        bidsPlaced.put("AIThree", 0);

        bidsWon.put("Player", 0);
        bidsWon.put("Player", 0);
        bidsWon.put("Player", 0);
        bidsWon.put("Player", 0);
    }

}