package project.controller;
import project.model.*;

import java.util.*;

public class ScoreKeeper{

    public Map<String, Integer> getTrackScore() {
        return trackScore;
    }

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
    public int getPlayerScore(String player) {
        return trackScore.get(player);
    }

    public String getPlayerByScore(int score) {
        String output = "";
        for (String player: trackScore.keySet()) {
            if (trackScore.get(player) == score) {
                return player;
            }
        }
        return output;
    }

    

    public void calculateOverallScore(Map<String,Integer> playersScore){
        for (String player: trackScore.keySet()){
            int currentScore = trackScore.get(player);
            currentScore += playersScore.get(player);
            trackScore.put(player, currentScore);
        }
    }

    /**
    To be called from GameManager to check if game has ended.
    Game ends when highest score is >= 100.
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

    public void displayScore(){
        for (String player: trackScore.keySet()){
            System.out.println(player + " : " + trackScore.get(player));
        }
    }

}