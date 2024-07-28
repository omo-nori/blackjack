package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    public void constructorTest_firstArgsNull() {
        assertThrows(NullPointerException.class, () -> new Card(null, Suit.SPADE));
    }

    @Test
    public void constructorTest_secondArgsNull() {
        assertThrows(NullPointerException.class, () -> new Card(Rank.ACE, null));
    }

    @Test
    public void equalsTest_null() {
        Card card = new Card(Rank.ACE, Suit.SPADE);
        assertFalse(card.equals(null));
    }

    @Test
    @SuppressWarnings("unlikely-arg-type")
    public void equalsTest_otherObject() {
        Card card = new Card(Rank.ACE, Suit.SPADE);
        assertFalse(card.equals("aaa"));
    }

    @Test
    public void equalsTest_differentRank() {
        Card card = new Card(Rank.ACE, Suit.SPADE);
        Card other = new Card(Rank.TWO, Suit.SPADE);
        assertFalse(card.equals(other));
    }

    @Test
    public void equalsTest_differentSuit() {
        Card card = new Card(Rank.ACE, Suit.SPADE);
        Card other = new Card(Rank.ACE, Suit.HEART);
        assertFalse(card.equals(other));
    }

    @Test
    public void equalsTest_equivalentCard() {
        Card card = new Card(Rank.ACE, Suit.SPADE);
        Card other = new Card(Rank.ACE, Suit.SPADE);
        assertTrue(card.equals(other));
    }

    @Test
    public void getPointsTest_Pip() {
        Card card = new Card(Rank.ACE, Suit.SPADE);
        int expected = Rank.ACE.getNumber();
        int actual = card.getPoints();

        assertEquals(expected, actual);
    }

    @Test
    public void getPointsTest_Court() {
        Card card = new Card(Rank.JACK, Suit.SPADE);
        int expected = 10;
        int actual = card.getPoints();

        assertEquals(expected, actual);
    }

}
