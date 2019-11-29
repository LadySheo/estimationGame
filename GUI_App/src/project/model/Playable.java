/**
* Runs the game.
*/
package project.model;
import java.util.*;
import project.controller.*;

public interface Playable{
    public void playCard(List<Player> playerList, Trick t);
    public int placeBid(List<Integer> allowedBids, Trick t);
}