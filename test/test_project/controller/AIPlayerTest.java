package test_project.controller;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import project.controller.*;
import project.model.*;

import java.util.*;

public class AIPlayerTest {
    //set parameters for testing AIPlayer
    Suit leadingSuit = Suit.CLUBS;
    Suit trumpSuit = Suit.DIAMONDS;
    Player testPlayer = new Player("test", new Hand());
    Player aiTestOne = new AIPlayer("aiTestOne", new Hand());
    Player aiTestTwo = new AIPlayer("aiTestOne", new Hand());
    Player aiTestThree = new AIPlayer("aiTestOne", new Hand());

    /**
     * Tests AIPlayer constructor
     */
    @Test
    public void TestCreateAIPlayer(){
        Player aiPlayer = new AIPlayer("testAI", new Hand());
        assertNotNull(aiPlayer);
    }

    /**
     * Test AIOne playCard function.
     * This is the second round, each player dealt 2 cards.
     * AITestOne goes first.
     * For this test, the AIPlayer goes first, and has a leading suit card, the other non-trump, non-leading suit card.
     */
    @Test
    public void TestPlayCard(){
        List<Player> playerList = new ArrayList<>();
        playerList.add(aiTestOne);
        playerList.add(aiTestTwo);
        playerList.add(aiTestThree);
        playerList.add(testPlayer);

        List<Card> cardList = new ArrayList<Card>();
        cardList.add(new Card(Suit.CLUBS, Rank.QUEEN, null));
        cardList.add(new Card(Suit.HEARTS, Rank.KING, null));

        //test trick
        Trick t = new Trick(playerList, trumpSuit);
        t.setLeadingSuit(leadingSuit);
        t.setCardList(cardList);

        //add cards to players' test
        aiTestThree.getHand().addCard(new Card(Suit.HEARTS, Rank.ACE, null));
        aiTestThree.getHand().addCard(new Card(Suit.CLUBS, Rank.KING, null));

        AIPlayer aiTestAIPlayer = (AIPlayer) aiTestThree;
        aiTestAIPlayer.setBidPlaced(2);

        Card returnCard = aiTestThree.playCard(playerList, t);
        String returnCardname = returnCard.toString();

        String correctCardName = "King of Clubs";

        assertEquals(correctCardName, returnCardname);
    }

}
