package test_project.model;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import project.model.*;

public class DeckTest {
    /**
     * Test to verify if createDeck function does indeed create a full 52 card deck
     */
    @Test
    public void TestCreateDeck(){
        Deck deck = new Deck();
        int beforeCreateDeck = deck.getSizeOfDeck();

        deck.createDeck();
        int afterCreateDeck = deck.getSizeOfDeck();
        assertEquals(beforeCreateDeck+ 52, afterCreateDeck);
    }
    /**
     * Test to verify if addCard function in Deck adds a card to hand
     */
    @Test
    public void TestDealCard() {
        Deck deck = new Deck();
        deck.createDeck();

    }
}
