package project;
import project.controller.*;
import project.model.*;

public class testClass{
    public static void main(String[] args){
        //testing deck class
        // Deck deck = new Deck();
        // deck.createDeck();
        // deck.shuffle();
        // System.out.println("Deck contains: " + deck.returnCardsInDeck());
        // deck.restoreDeck();
        // System.out.println("Deck contains: " + deck.returnCardsInDeck());

         GameManager gameManager = new GameManager();
         gameManager.initGame();
    }
}