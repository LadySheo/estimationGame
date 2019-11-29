package project.controller;
import project.model.*;
import java.util.*;
/**
* Runs the game.
*/
public class Trick{
    private Suit leadingSuit = Suit.CLUBS;
    private Suit trumpSuit = Suit.CLUBS;

    private List<Player> players;
    private List<Card> cardList = new ArrayList<Card>();
    private Map<Card, Player> cardsMap = new HashMap<Card, Player>();
    private String playCardMsg = "";

    //constructor
    public Trick(List<Player> players, Suit trumpSuit){
        this.players = players;
        this.trumpSuit = trumpSuit;
    }

    public Player callPlayersPlayCard(List<Player> playOrder){
        for (int i = 0; i < playOrder.size(); i++){
//            System.out.println(playOrder.get(i).getName() + "'s turn");
            playCardMsg += playOrder.get(i).getName() + "'s turn";
            Card playCard = playOrder.get(i).playCard(playOrder, this);
            cardList.add(playCard);
            if (i == 0){
                setLeadingSuit(playCard.getSuit());
            }
            cardsMap.put(playCard, playOrder.get(i));
//            System.out.println(players.get(i).getName() + " played " + playCard.toString());
            playCardMsg = players.get(i).getName() + " played " + playCard.toString() +"<br/>";
            if (i == 0) {
//                System.out.println("-----------------------------");
                playCardMsg += "----------------------------- <br/>";
//                System.out.println("Leading suit: " + playCard.suitToString());
                playCardMsg += "Leading suit: " + playCard.suitToString()+"<br/>";
//                System.out.println("-----------------------------");
                playCardMsg += "----------------------------- <br/>";
            }
//            System.out.println();
        }
        Player winner = this.getWinner();
//        System.out.println("---------------" + winner.getName() + " won--------------");
        playCardMsg += "---------------" + winner.getName() + " won--------------";
        return winner;
    }

    
    public Card getWinCard(){
        List<Card> leadingSuitList = new ArrayList<Card>();
        List<Card> trumpSuitList = new ArrayList<Card>();
        
        String trumpSuitName = trumpSuit.getName();
        String leadingSuitName = leadingSuit.getName();

        Card winCard = null;

        //categorize the cards in diff suit to before compare
        for (Card card : cardsMap.keySet()){
            //if the cards is a leading suit
            if (card.suitToString().equals(leadingSuitName)){
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

    public Suit getLeadingSuit(){
        return leadingSuit;
    }

    /**
    * sets leading suit for this trick
    */
    public void setLeadingSuit(Suit suit){
        leadingSuit = suit;
    }

    public Suit getTrumpSuit(){
        return trumpSuit;
    }

    public List<Card> getCardList() {
        return cardList;
    }
}