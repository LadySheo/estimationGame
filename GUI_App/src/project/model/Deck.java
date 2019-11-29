// Deck.java - John K. Estell - 8 May 2003
// Modified by: Jolene Seow
// Last modified: 19 November 2019
// Implementation of a deck of playing cards.  Uses the Card class.

package project.model;

import java.util.*;
import javax.swing.ImageIcon;

/**
 * Represents a deck of playing cards.

 * @author John K. Estell
 * @version 1.0
 */
public class Deck {
   private List<Card> deck;
   private int index;
 
  
  /**
   * Creates an empty deck of cards.
   */
   public Deck() {
      deck = new ArrayList<Card>();
      index = 0;
   }
  
  /**
   * Adds a card to the deck.
   * @param card card to be added to the deck.
   */
   public void addCard(Card card) {
      deck.add( card );
   }

   /**
   * Creates a full standard 52-card deck.
   */
   public void createDeck(){

      //CLUBS SUIT
      addCard(new Card(Suit.CLUBS, Rank.ACE, new ImageIcon("image.png")));
      addCard(new Card(Suit.CLUBS, Rank.TWO, new ImageIcon("image.png")));
      addCard(new Card(Suit.CLUBS, Rank.THREE, new ImageIcon("image.png")));
      addCard(new Card(Suit.CLUBS, Rank.FOUR, new ImageIcon("image.png")));
      addCard(new Card(Suit.CLUBS, Rank.FIVE, new ImageIcon("image.png")));
      addCard(new Card(Suit.CLUBS, Rank.SIX, new ImageIcon("image.png")));
      addCard(new Card(Suit.CLUBS, Rank.SEVEN, new ImageIcon("image.png")));
      addCard(new Card(Suit.CLUBS, Rank.EIGHT, new ImageIcon("image.png")));
      addCard(new Card(Suit.CLUBS, Rank.NINE, new ImageIcon("image.png")));
      addCard(new Card(Suit.CLUBS, Rank.TEN, new ImageIcon("image.png")));
      addCard(new Card(Suit.CLUBS, Rank.JACK, new ImageIcon("image.png")));
      addCard(new Card(Suit.CLUBS, Rank.QUEEN, new ImageIcon("image.png")));
      addCard(new Card(Suit.CLUBS, Rank.KING, new ImageIcon("image.png")));

      //DIAMONDS
      addCard(new Card(Suit.DIAMONDS, Rank.ACE, new ImageIcon("image.png")));
      addCard(new Card(Suit.DIAMONDS, Rank.TWO, new ImageIcon("image.png")));
      addCard(new Card(Suit.DIAMONDS, Rank.THREE, new ImageIcon("image.png")));
      addCard(new Card(Suit.DIAMONDS, Rank.FOUR, new ImageIcon("image.png")));
      addCard(new Card(Suit.DIAMONDS, Rank.FIVE, new ImageIcon("image.png")));
      addCard(new Card(Suit.DIAMONDS, Rank.SIX, new ImageIcon("image.png")));
      addCard(new Card(Suit.DIAMONDS, Rank.SEVEN, new ImageIcon("image.png")));
      addCard(new Card(Suit.DIAMONDS, Rank.EIGHT, new ImageIcon("image.png")));
      addCard(new Card(Suit.DIAMONDS, Rank.NINE, new ImageIcon("image.png")));
      addCard(new Card(Suit.DIAMONDS, Rank.TEN, new ImageIcon("image.png")));
      addCard(new Card(Suit.DIAMONDS, Rank.JACK, new ImageIcon("image.png")));
      addCard(new Card(Suit.DIAMONDS, Rank.QUEEN, new ImageIcon("image.png")));
      addCard(new Card(Suit.DIAMONDS, Rank.KING, new ImageIcon("image.png")));

      //HEARTS
      addCard(new Card(Suit.HEARTS, Rank.ACE, new ImageIcon("image.png")));
      addCard(new Card(Suit.HEARTS, Rank.TWO, new ImageIcon("image.png")));
      addCard(new Card(Suit.HEARTS, Rank.THREE, new ImageIcon("image.png")));
      addCard(new Card(Suit.HEARTS, Rank.FOUR, new ImageIcon("image.png")));
      addCard(new Card(Suit.HEARTS, Rank.FIVE, new ImageIcon("image.png")));
      addCard(new Card(Suit.HEARTS, Rank.SIX, new ImageIcon("image.png")));
      addCard(new Card(Suit.HEARTS, Rank.SEVEN, new ImageIcon("image.png")));
      addCard(new Card(Suit.HEARTS, Rank.EIGHT, new ImageIcon("image.png")));
      addCard(new Card(Suit.HEARTS, Rank.NINE, new ImageIcon("image.png")));
      addCard(new Card(Suit.HEARTS, Rank.TEN, new ImageIcon("image.png")));
      addCard(new Card(Suit.HEARTS, Rank.JACK, new ImageIcon("image.png")));
      addCard(new Card(Suit.HEARTS, Rank.QUEEN, new ImageIcon("image.png")));
      addCard(new Card(Suit.HEARTS, Rank.KING, new ImageIcon("image.png")));

      //SPADES
      addCard(new Card(Suit.SPADES, Rank.ACE, new ImageIcon("image.png")));
      addCard(new Card(Suit.SPADES, Rank.TWO, new ImageIcon("image.png")));
      addCard(new Card(Suit.SPADES, Rank.THREE, new ImageIcon("image.png")));
      addCard(new Card(Suit.SPADES, Rank.FOUR, new ImageIcon("image.png")));
      addCard(new Card(Suit.SPADES, Rank.FIVE, new ImageIcon("image.png")));
      addCard(new Card(Suit.SPADES, Rank.SIX, new ImageIcon("image.png")));
      addCard(new Card(Suit.SPADES, Rank.SEVEN, new ImageIcon("image.png")));
      addCard(new Card(Suit.SPADES, Rank.EIGHT, new ImageIcon("image.png")));
      addCard(new Card(Suit.SPADES, Rank.NINE, new ImageIcon("image.png")));
      addCard(new Card(Suit.SPADES, Rank.TEN, new ImageIcon("image.png")));
      addCard(new Card(Suit.SPADES, Rank.JACK, new ImageIcon("image.png")));
      addCard(new Card(Suit.SPADES, Rank.QUEEN, new ImageIcon("image.png")));
      addCard(new Card(Suit.SPADES, Rank.KING, new ImageIcon("image.png")));
   }

   /**
   * Prints out string containing all the cards left in the deck.
   * @return the list of all cards present in the deck.
   */
   public String returnCardsInDeck(){  
      String cardsList = "";
      for (int i = 0; i < deck.size(); i++){
         Card card = (Card) this.deck.get(i);
         cardsList += card.toString() + " , ";
      }

      return cardsList;
   }
   
  
  /**
   * The size of a deck of cards.
   * @return the number of cards present in the full deck.
   */
   public int getSizeOfDeck() {
      return deck.size();
   }
   
   
  /**
   * The number of cards left in the deck.
   * @return the number of cards left to be dealt from the deck.
   */
   public int getNumberOfCardsRemaining() {
      return deck.size() - index;
   }
   
   
  /**
   * Deal one card from the deck.
   * @return a card from the deck, or the null reference if there
   * are no cards left in the deck.
   */
   public Card dealCard() {
      if (deck.isEmpty()) {
         return null;
      } else {
         return (Card) deck.get(index);
      }
   }

   public void removeCard(Card card){
      deck.remove(card);
   }
   
   
  /**
   * Shuffles the cards present in the deck.
   */
   public void shuffle() {
      Collections.shuffle(deck);
   }
   
   
  /**
   * Looks for an empty deck.
   * @return <code>true</code> if there are no cards left to be dealt from the deck.
   */
   public boolean isEmpty() {
      if ( index >= deck.size() )
         return true;
      else
         return false;
   }
   
  /**
   * Restores the deck to "full deck" status.
   */
   public void restoreDeck() {
      deck.clear();
      createDeck();
      index = 0;
   }   

}
