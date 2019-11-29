package test_project.controller;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import project.controller.*;
import project.model.*;

import java.util.*;

public class TrickTest {
    //set parameters for testing Trick
    Suit trumpSuit = Suit.DIAMONDS;
    Suit leadSuit = Suit.HEARTS;

    Player testPlayer = new Player("test", new Hand());
    Player aiTestOne = new AIPlayer("aiTestOne", new Hand());
    Player aiTestTwo = new AIPlayer("aiTestOne", new Hand());
    Player aiTestThree = new AIPlayer("aiTestOne", new Hand());

    /**
     * Test to verify if trick constructor creates a trick object.
     */
    @Test
    public void TestCreateTrick(){
        List<Player> playerList = new ArrayList<>();
        playerList.add(aiTestOne);
        playerList.add(aiTestTwo);
        playerList.add(aiTestThree);
        playerList.add(testPlayer);

        Trick t = new Trick(playerList, trumpSuit);
        assertNotNull(t);
    }

    /**
     *  Test to verify if getPreviousCard function returns the previous card
     */
    @Test
    public void TestGetPreviousCard(){
        //parameters for test
        List<Player> playerList = new ArrayList<>();
        playerList.add(aiTestOne);
        playerList.add(aiTestTwo);
        playerList.add(aiTestThree);
        playerList.add(testPlayer);

        List<Card> cardList = new ArrayList<Card>();
        Card testCard = new Card(Suit.HEARTS, Rank.TWO, null);
        String testCardString = testCard.toString();
        cardList.add(testCard);

        //test
        Trick t = new Trick(playerList, trumpSuit);
        t.setCardList(cardList);
        Card getCard = t.getPreviousCard();
        String getCardString = getCard.toString();
        assertEquals(testCardString, getCardString);
    }

    /**
     *  Test to verify if trick returns the correct winner.
     *  Correct winner is aiTestOne who has a trump suit, while testPlayer has a leading suit.
     */
    @Test
    public void TestGetWinner(){
        //setting parameters for testing
        List<Player> playerList = new ArrayList<>();
        playerList.add(aiTestOne);
        playerList.add(aiTestTwo);
        playerList.add(aiTestThree);
        playerList.add(testPlayer);

        List<Card> cardList = new ArrayList<Card>();
        cardList.add(new Card(Suit.DIAMONDS, Rank.ACE, null));
        cardList.add(new Card(Suit.HEARTS, Rank.KING, null));
        cardList.add(new Card(Suit.SPADES, Rank.THREE, null));
        cardList.add(new Card(Suit.HEARTS, Rank.FOUR, null));

        Map<Card, Player> cardsMap = new HashMap<Card, Player>();
        cardsMap.put(new Card(Suit.DIAMONDS, Rank.ACE, null), aiTestOne);
        cardsMap.put(new Card(Suit.HEARTS, Rank.KING, null), aiTestOne);
        cardsMap.put(new Card(Suit.SPADES, Rank.THREE, null), aiTestOne);
        cardsMap.put(new Card(Suit.HEARTS, Rank.FOUR, null), aiTestOne);

        //test
        Trick t = new Trick(playerList, trumpSuit);
        t.setCardList(cardList);
        t.setCardsMap(cardsMap);

        String correctNameWinner = "aiTestOne";

        Player testWinner = t.getWinner();
        String nameTestWinner = testWinner.getName();

        assertEquals(correctNameWinner, nameTestWinner);
    }
}
