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
      addCard(new Card(Suit.CLUBS, Rank.ACE, null));
      addCard(new Card(Suit.CLUBS, Rank.TWO, null));
      addCard(new Card(Suit.CLUBS, Rank.THREE, null));
      addCard(new Card(Suit.CLUBS, Rank.FOUR, null));
      addCard(new Card(Suit.CLUBS, Rank.FIVE, null));
      addCard(new Card(Suit.CLUBS, Rank.SIX, null));
      addCard(new Card(Suit.CLUBS, Rank.SEVEN, null));
      addCard(new Card(Suit.CLUBS, Rank.EIGHT, null));
      addCard(new Card(Suit.CLUBS, Rank.NINE, null));
      addCard(new Card(Suit.CLUBS, Rank.TEN, null));
      addCard(new Card(Suit.CLUBS, Rank.JACK, null));
      addCard(new Card(Suit.CLUBS, Rank.QUEEN, null));
      addCard(new Card(Suit.CLUBS, Rank.KING, null));

      //DIAMONDS
      addCard(new Card(Suit.DIAMONDS, Rank.ACE, null));
      addCard(new Card(Suit.DIAMONDS, Rank.TWO, null));
      addCard(new Card(Suit.DIAMONDS, Rank.THREE, null));
      addCard(new Card(Suit.DIAMONDS, Rank.FOUR, null));
      addCard(new Card(Suit.DIAMONDS, Rank.FIVE, null));
      addCard(new Card(Suit.DIAMONDS, Rank.SIX, null));
      addCard(new Card(Suit.DIAMONDS, Rank.SEVEN, null));
      addCard(new Card(Suit.DIAMONDS, Rank.EIGHT, null));
      addCard(new Card(Suit.DIAMONDS, Rank.NINE, null));
      addCard(new Card(Suit.DIAMONDS, Rank.TEN, null));
      addCard(new Card(Suit.DIAMONDS, Rank.JACK, null));
      addCard(new Card(Suit.DIAMONDS, Rank.QUEEN, null));
      addCard(new Card(Suit.DIAMONDS, Rank.KING, null));

      //HEARTS
      addCard(new Card(Suit.HEARTS, Rank.ACE, null));
      addCard(new Card(Suit.HEARTS, Rank.TWO, null));
      addCard(new Card(Suit.HEARTS, Rank.THREE, null));
      addCard(new Card(Suit.HEARTS, Rank.FOUR, null));
      addCard(new Card(Suit.HEARTS, Rank.FIVE, null));
      addCard(new Card(Suit.HEARTS, Rank.SIX, null));
      addCard(new Card(Suit.HEARTS, Rank.SEVEN, null));
      addCard(new Card(Suit.HEARTS, Rank.EIGHT, null));
      addCard(new Card(Suit.HEARTS, Rank.NINE, null));
      addCard(new Card(Suit.HEARTS, Rank.TEN, null));
      addCard(new Card(Suit.HEARTS, Rank.JACK, null));
      addCard(new Card(Suit.HEARTS, Rank.QUEEN, null));
      addCard(new Card(Suit.HEARTS, Rank.KING, null));

      //SPADES
      addCard(new Card(Suit.SPADES, Rank.ACE, null));
      addCard(new Card(Suit.SPADES, Rank.TWO, null));
      addCard(new Card(Suit.SPADES, Rank.THREE, null));
      addCard(new Card(Suit.SPADES, Rank.FOUR, null));
      addCard(new Card(Suit.SPADES, Rank.FIVE, null));
      addCard(new Card(Suit.SPADES, Rank.SIX, null));
      addCard(new Card(Suit.SPADES, Rank.SEVEN, null));
      addCard(new Card(Suit.SPADES, Rank.EIGHT, null));
      addCard(new Card(Suit.SPADES, Rank.NINE, null));
      addCard(new Card(Suit.SPADES, Rank.TEN, null));
      addCard(new Card(Suit.SPADES, Rank.JACK, null));
      addCard(new Card(Suit.SPADES, Rank.QUEEN, null));
      addCard(new Card(Suit.SPADES, Rank.KING, null));
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

    /**
     * Removes card from deck.
     * @param card : the card that is to be removed from the deck.
     */
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
