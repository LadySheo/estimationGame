/**
* Runs the game.
*/
package project.controller;
import project.model.*;
import java.util.*;

public class BidTracker{
    //bids placed and won tracked by name
    private Map<String, Integer> bidsPlaced = new HashMap<String, Integer>();
    private Map<String, Integer> bidsWon = new HashMap<String, Integer>();

    private ScoreKeeper scoreKeeper;

    //need to pass in a scoreKeeper when creating a bidtracker
    public BidTracker(ScoreKeeper scoreKeeper){
        bidsPlaced.put("Player", 0);
        bidsPlaced.put("AIOne", 0);
        bidsPlaced.put("AITwo", 0);
        bidsPlaced.put("AIThree", 0);

        bidsWon.put("Player", 0);
        bidsWon.put("AIOne", 0);
        bidsWon.put("AITwo", 0);
        bidsWon.put("AIThree", 0);

        this.scoreKeeper = scoreKeeper;
    }

    /** 
     * Called from Round 
     * return pair to Round to bid
     * pair will be added into a list in Round
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
     * Called from Round
     * During bidding process, bids should not exceed the number of cards being dealt &
     * number of bids cannot be the same as the number of tricks in that round.
     * This function will return the total number of bids already placed.
     */
    public int checkTotalBidsPlaced(){
        int totalBids = 0;
        for (int placedBids: bidsPlaced.values()){
            totalBids += placedBids;
        }

        return totalBids;
    }

    
    

    /**
    * To reset all the bids placed and won back to zero for the next round after recording the score
    * into ScoreKeeper
    */
    public void ResetBidTracker(){
        bidsPlaced.put("Player", 0);
        bidsPlaced.put("AIOne", 0);
        bidsPlaced.put("AITwo", 0);
        bidsPlaced.put("AIThree", 0);

        bidsWon.put("Player", 0);
        bidsWon.put("Player", 0);
        bidsWon.put("Player", 0);
        bidsWon.put("Player", 0);
    }

    public void EndOfRound(){

    }
}