package project.controller;

import project.model.*;

import java.util.*;

public class TrickTest{
    public static void main(String[] args) {
        Player p1 = new Player("Player1", new Hand());
        p1.inputCardPlay();
        Player p2 = new Player("Player2", new Hand());
        Player p3 = new Player("Player3", new Hand());
        Player p4 = new Player("Player4", new Hand());
        Suit trumpSuit = Suit.CLUBS;
        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        Trick trick1 = new Trick(players,trumpSuit);
    }
}