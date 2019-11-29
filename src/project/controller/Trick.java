package project.controller;
import project.model.*;
import java.util.*;
/**
 * Class to create and run an instance of a trick.
 *
 * @author G1T13
*/
public class Trick{
    private Suit leadingSuit = Suit.CLUBS;
    private Suit trumpSuit = Suit.CLUBS;

    private List<Player> players;
    private List<Card> cardList = new ArrayList<Card>();
    private Map<Card, Player> cardsMap = new HashMap<Card, Player>();

    /**
     * constructor
     * @param players list of players
     * @param trumpSuit trump suit
     */

    public Trick(List<Player> players, Suit trumpSuit){
        this.players = players;
        this.trumpSuit = trumpSuit;
    }

    /**
     * Allows the players to play a card for the current trick in the order depending on the dealer.
     * @param playOrder list that stores players in the order in which they can play their card
     * @return winner player that wins the trick
     */
    public Player callPlayersPlayCard(List<Player> playOrder){
        for (int i = 0; i < playOrder.size(); i++){
            System.out.println(playOrder.get(i).getName() + "'s turn");
            Card playCard = playOrder.get(i).playCard(playOrder, this);
            cardList.add(playCard);
            if (i == 0){
                setLeadingSuit(playCard.getSuit()); //store the suit of the first card played as the leading suit
            }
            cardsMap.put(playCard, playOrder.get(i));
            System.out.println(playOrder.get(i).getName() + " played " + playCard.toString());
            if (i == 0) {
                System.out.println("-----------------------------");
                System.out.println("Leading suit: " + playCard.suitToString());
                System.out.println("-----------------------------");
            }
            System.out.println();
        }
        Player winner = this.getWinner();
        System.out.println("---------------" + winner.getName() + " won------------------");
        return winner;
    }

    /**
     * Decides the highest ranking and suit card of all cards played during trick, taking in consideration
     * trump and leading suit
     * @return wincard the winning card based off of all the cards played
     */
    public Card getWinCard(){
        List<Card> leadingSuitList = new ArrayList<Card>();
        List<Card> trumpSuitList = new ArrayList<Card>();
        
        String trumpSuitName = trumpSuit.getName();
        String leadingSuitName = leadingSuit.getName();

        Card winCard = null;

        //categorize the cards in diff suit to before compare
        for (Card card : cardsMap.keySet()){
            //if the cards is a leading suit
            if (card.suitToString().equals(leadingSuitName)){ // if card is a leading suit card
                leadingSuitList.add(card);
            } else if (card.suitToString().equals(trumpSuitName)){ //if card is a trump suit card
                trumpSuitList.add(card);
            }
        }

        //find out winning card
        //if got trump suit
        if (!trumpSuitList.isEmpty()){
            //compare trump suit cards rank
            winCard = trumpSuitList.get(0);
            //if trump card not only one
            if(trumpSuitList.size() > 1){
                for (Card card : trumpSuitList){
                    if (winCard.compareTo(card) > 0){
                        winCard = card;
                    }
                }
            }
        } 
        else if (!leadingSuitList.isEmpty()){
            //compare leading suit cards
            //if only one leading card
            winCard = leadingSuitList.get(0);
            //if there is more than one card played that is the leading suit
            if (leadingSuitList.size() > 1){
                for (Card card : leadingSuitList){
                    //compare rank of cards of the same leading suit
                    if (winCard.compareTo(card) > 0){
                        winCard = card;
                    }
                }
            }
        } else {
            for (Card card: cardsMap.keySet()) {
                if (winCard == null) {
                    winCard = card;
                }
                if (winCard.compareTo(card) > 0){
                    winCard = card;
                }
            }
        }

        return winCard;
    }

    /**
     * Determine the player who played the winning card
     * @param winCard winning card to find winner from
     * @return winner the player that won the trick
     */
    //find the winner from the wincard
    public Player findWinner(Card winCard){
        //get winner
        Player winner = null;
        for ( Card card : cardsMap.keySet()){
            if (card.isSameAs(winCard)){
                winner = cardsMap.get(winCard);
            }
        }

        return winner;
    }

    /**
     * Function in charge of determining winning card and the winner of the trick by calling the appropriate functions.
     * @return winner the player who won the trick
     */
    //returns the winner of this trick
    public Player getWinner(){
        Card winCard = this.getWinCard();
        Player winner = this.findWinner(winCard);
        return winner;
    }

    /**
     * Returns the last index of the cardsMap list, which stores all the cards played by Players
     * in order that they were played.
     * @return Card
     */
    public Card getPreviousCard(){
        if (cardList.size() == 0) {
            return null;
        }
        
        return cardList.get(Math.max(cardList.size()-1,0));
    }

    /**
     * get leading suit for this trick
     * @return leadingsuit
     */
    public Suit getLeadingSuit(){
        return leadingSuit;
    }

    /**
    * sets leading suit for this trick
     * @param suit leading suit to be set
     */
    public void setLeadingSuit(Suit suit){
        leadingSuit = suit;
    }

    /**
     * @return trumpSuit the trumpsuit of the round
     */
    public Suit getTrumpSuit(){
        return trumpSuit;
    }

    /**
     * @return cardList returns list of cards that have been played by players in this trick
     */
    public List<Card> getCardList() {
        return cardList;
    }

    /**
     * For testing purposes
     * @param cardList list of cards that have been played
     */
    public void setCardList(List<Card> cardList){ this.cardList = cardList; }

    /**
     *  For testing purposes
     * @param cardsMap hashmap of cards and the player who played that card
     */
    public void setCardsMap(Map<Card, Player> cardsMap){ this.cardsMap = cardsMap; }
}