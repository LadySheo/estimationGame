package project.controller;
import project.model.*;

import java.util.*;

/**
 * Representation of an entity that keeps the score for the entire game. Instantiates a score tracker
 * for each player
 * 
 * @author G1T13
 */
public class ScoreKeeper{

    private Map<String,Integer> trackScore = new HashMap<>();
    /**
    * at index 0 is player's score
    * at index 1 is aiOne's score
    * at index 2 is aiTwo's score
    * at index 3 is aiThree's score
    */
    private Map<Integer, String> mapIndexToPlayers = new HashMap<Integer, String>();

    public ScoreKeeper(){
        mapIndexToPlayers.put(0, "Player");
        mapIndexToPlayers.put(1, "AIOne");
        mapIndexToPlayers.put(2, "AITwo");
        mapIndexToPlayers.put(3, "AIThree");

        trackScore.put("Player",0);
        trackScore.put("AIOne",0);
        trackScore.put("AITwo",0);
        trackScore.put("AIThree",0);
        
    }

    /**
    Calculates the player's score for the round when called and adds it to the player's overall score in the game
    @param player player to be queried
    @return int of player's score
     */
    public int getPlayerScore(String player) {
        return trackScore.get(player);
    }

    /**
    Calculates the player's score for the round when called and adds it to the player's overall score in the game
    @param score score to be queried
    @return string containing player's name
     */
    public String getPlayerByScore(int score) {
        String player = "";
        for (String s: trackScore.keySet()) {
            if (trackScore.get(s) == score) {
                return player + s;
            }
        }
        return player;
    }

    
    /**
    Calculates the player's score for the round when called and adds it to the player's overall score in the game
    @param playersScore map of player's name to their score (determined by whether bids match number of tricks won)
     */
    public void calculateOverallScore(Map<String,Integer> playersScore){
        for (String player: trackScore.keySet()){
            int currentScore = trackScore.get(player);
            currentScore += playersScore.get(player);
            trackScore.put(player, currentScore);
        }
    }

    /**
    To be called from GameManager to check if game has ended.
    Game ends when highest score is bigger or equals to 100.
    @return int of highest score in the game
     */
    public int getHighestScore(){
        int highestScore = 0;
        for (String player: trackScore.keySet()){
            int currentScore = trackScore.get(player);
            if (currentScore > highestScore) {
                highestScore = currentScore;
            }
        }
        return highestScore;
    }

    /**
     * Displays every player's score in tabular form
     */
    
    public void displayScore(){
        for (String player: trackScore.keySet()){
            System.out.println(player + " : " + trackScore.get(player));
        }
    }
}