/**
* Runs the game.
*/
package project.controller;
import project.model.*;
import java.util.*;

public class GameManager{
    private Deck deck = new Deck();
    private Player player = new Player("Player", new Hand());
    private Player aiOne = new AIPlayer("AIOne", new Hand());
    private Player aiTwo = new AIPlayer("AITwo", new Hand());
    private Player aiThree = new AIPlayer("AIThree", new Hand());

    private List<Player> listPlayers = new ArrayList<>();

    private ScoreKeeper scoreKeeper = new ScoreKeeper();
    private BidTracker bidTracker = new BidTracker(scoreKeeper);
    private int roundNumber = 0;
    private int dealerIndex = 0;

    public int getRoundNumber() {
        return roundNumber;
    }

    public int getDealerIndex() {
        return dealerIndex;
    }

    public void initGame(){
        deck.createDeck();
        deck.shuffle();
        scoreKeeper.displayScore();
        listPlayers.add(player);
        listPlayers.add(aiOne);
        listPlayers.add(aiTwo);
        listPlayers.add(aiThree);
        System.out.println("Game Started!");

        System.out.println("Initializing new round");
        RunRounds();
    }

    public void RunRounds(){
        while (roundNumber < 11){
            System.out.println("----------Round " + (roundNumber+1) + "--------------");
            StartNewRound();
            if (scoreKeeper.getHighestScore() >= 100) {
                break;
            }
        }

        EndGame();
    }

    public int determineFirstDealer(){
        //draw 4 cards
        List<Card> cardList = new ArrayList<Card>();
        int indexDealer = 0; //returns the index of the first dealer - to be passed into Round
        //deal one card to every player, but DO NOT remove card from deck
        for (int i = 0; i < listPlayers.size(); i++){
            cardList.add(deck.dealCard());
            deck.removeCard(deck.dealCard());
        }

        //compare cards
        Card maxCard = cardList.get(0); // by default, set the largest card to the first card
        for (int i = 0; i < cardList.size(); i++){
            Card currentCard = cardList.get(i);
            if (currentCard.compareTo(maxCard) > 0){
                maxCard = currentCard;
                indexDealer = i;
            }
        }

        deck.shuffle(); //shuffle deck again after looking at top 4 cards
        return indexDealer;
    }

    public void dealCards(int numCards){
        for (int i = 0; i < numCards; i++){
            for (int j = 0; j < listPlayers.size(); j++){
                // Card dealtCard = deck.dealCard();
                Player currentPlayer = listPlayers.get(j);
                Hand currentPlayerHand = currentPlayer.getHand();
                Card cardDealt = deck.dealCard();
                currentPlayerHand.addCard(cardDealt);
                deck.removeCard(cardDealt);
            }
        }
    }

    public Suit determineTrumpSuit(){
        //draw first card after dealing card
        Card topCard = deck.dealCard();
        Suit trumpSuit = topCard.getSuit();
        deck.shuffle();
        return trumpSuit;
    }

    public void StartNewRound(){
        if (roundNumber == 0){
            dealerIndex = determineFirstDealer();
        } else {
            dealerIndex += 1;
            if (dealerIndex > 3){
                dealerIndex = 0;
            }
        }
        roundNumber += 1;
        deck.restoreDeck();
        deck.shuffle();
        int numCards = numCardsDealt();
        dealCards(numCards);
        Suit trumpSuit = determineTrumpSuit();
        Round round = new Round(bidTracker, roundNumber, player, aiOne, aiTwo, aiThree, trumpSuit, dealerIndex);
        Map<Map<Player,Integer>,List<Map<String, Integer>>> tricksWonMapAndBidsPlaced = round.callPlayersPlayTrick();
        Map<Player,Integer> tricksWonMap = new HashMap<>();
        List<Map<String, Integer>> bidsPlaced = new ArrayList<>();
        for (Map<Player,Integer> m: tricksWonMapAndBidsPlaced.keySet()) {
            tricksWonMap = m;
            bidsPlaced = tricksWonMapAndBidsPlaced.get(m);
        }
        Map<String,Integer> playerScore = new HashMap<>();
        for (Player p: tricksWonMap.keySet()) {
            for (Map<String,Integer> m: bidsPlaced) {
                for (String playerName: m.keySet()) {
                    if (p.getName().equals(playerName)) {
                        int bid = m.get(playerName);
                        int tricksWon = tricksWonMap.get(p);
                        int score = calculateScore(bid,tricksWon);
                        playerScore.put(p.getName(),score);
                    }
                }
            }
        }
        scoreKeeper.calculateOverallScore(playerScore);
        scoreKeeper.displayScore();

    }

    /** 
     * Internal call
     * Used to calculate score for every player based on bids placed and bids won
    */
    private int calculateScore(int bidsPlaced, int tricksWon){
        int finalScore = 10 + bidsPlaced; //every player given 10 points at end of round
        
        if (bidsPlaced != tricksWon){
            finalScore *= -1;
        } 

        return finalScore;
    } 

    public int numCardsDealt(){
        int cardsDealt = 0;
        if (roundNumber > 6){
            cardsDealt = 12 - roundNumber;
        } else {
            cardsDealt = roundNumber;
        }

        return cardsDealt;
    }

    //if any player has reached 100 points or all 11 rounds have been completed
    public void EndGame(){
        int highestScore = scoreKeeper.getHighestScore();
        System.out.println("-------------Game Ended!-------------");
        String playerWithHighestScore = scoreKeeper.getPlayerByScore(highestScore);
        System.out.println("-------------" + playerWithHighestScore.toString() + " won!-------------");
        scoreKeeper.displayScore();
    }
}