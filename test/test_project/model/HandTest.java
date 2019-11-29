package test_project.model;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import project.model.*;

public class HandTest {
    /**
     * Test to verify if addCard function in Hand adds a card to the hand
     */
    @Test
    public void TestAddCard(){
        Hand beforeAdd = new Hand();
        int beforeAddSize = beforeAdd.getNumberOfCards();

        Hand afterAdd = new Hand();
        afterAdd.addCard(new Card(Suit.CLUBS, Rank.TWO, null));
        int afterAddSize = afterAdd.getNumberOfCards();
        assertEquals(beforeAddSize + 1, afterAddSize);
    }

    /**
     * Test to verify if removeCard function removes a card from the Hand
     */
    @Test
    public void TestRemoveCard(){
        Hand hand = new Hand();
        Card card = new Card(Suit.CLUBS, Rank.TWO, null);
        hand.addCard(card);
        int beforeRemoveSize = hand.getNumberOfCards();

        hand.removeCard(card);
        int afterRemoveSize = hand.getNumberOfCards();
        assertEquals(beforeRemoveSize-1, afterRemoveSize);
    }
}
