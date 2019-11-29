// Hand.java - John K. Estell - 8 May 2003
// last modified: 19 November 2019
// Modified by: Jolene Seow
// Implementation of a abstract hand of playing cards.  
// Uses the Card class.  Requires subclass for specifying
// the specifics of what constitutes the evaluation of a hand 
// for the game being implemented.

package project.model;

import java.util.*;

/**
 * Represents the basic functionality of a hand of cards.
 * Extensions of this class will provide the
 * definition of what constitutes a hand for that game and how hands are compared
 * to one another by overriding the <code>compareTo</code> method.
 * @author John K. Estell
 * @version 1.0
 */
public class Hand{
    
   private java.util.List hand = new ArrayList<Card>(); 

  /**
   * Adds a card to this hand.
   * @param card card to be added to the current hand.
   */
   public void addCard(Card card) {
      hand.add(card);
   }

  /**
   * Obtains the card stored at the specified location in the hand.  Does not
   * remove the card from the hand.
   * @param index position of card to be accessed.
   * @return the card of interest, or the null reference if the index is out of
   * bounds.
   */
   public Card getCard(int index) {
      return (Card) hand.get(index);
   }
  
  /**
   * Removes the specified card from the current hand.
   * @param card the card to be removed.
   * @return the card removed from the hand, or null if the card
   * was not present in the hand.
   */
   public Card removeCard(Card card) {
      int index = hand.indexOf(card);
      if ( index < 0 )
         return null;
      else
         return (Card) hand.remove(index);     
   }

  /**
   * Removes the card at the specified index from the hand.
   * @param index poisition of the card to be removed.
   * @return the card removed from the hand, or the null reference if
   * the index is out of bounds.
   */
   public Card removeCard(int index) {
      return (Card) hand.remove(index);
   } 

  /**
   * The number of cards held in the hand.
   * @return number of cards currently held in the hand.
   */
   public int getNumberOfCards() {
      return hand.size();
   }

  /**
   * Sorts the card in the hand.
   * Sort is performed according to the order specified in the {@link Card} class.
   */
   public void sort() {
      Collections.sort(hand);
   }

  /**
   * Checks to see if the hand is empty.
   * @return <code>true</code> is the hand is empty.
   */
   public boolean isEmpty() {
      return hand.isEmpty();
   }
   
   /**
    * Returns a description of the hand.
    * @return a list of cards held in the hand.
    */
    public String toString() {
        return hand.toString();
    }
    
    /**
    * Retuns index of card in hand
    * @param card card to be queried
    * @return int of index of queried card
    */
    public int findCard(Card card){
       return hand.indexOf(card);
    }
    
   /**
    * Replaces the specified card with another card.  Only the first
    * instance of the targeted card is replaced.  No action occurs if
    * the targeted card is not present in the hand.
    * @param oldCard old card to be replaced
    * @param replacementCard replacement card
    * @return <code>true</code> if the replacement occurs.
    */
    public boolean replaceCard( Card oldCard, Card replacementCard ) {
        int location = findCard( oldCard );
        if ( location < 0 )
           return false;
        hand.set( location, replacementCard );
        return true;
    }

}