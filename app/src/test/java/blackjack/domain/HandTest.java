package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class HandTest {
    @Test
    public void addCardTest_ArgsNotNull() {
        Card diamondAce = new Card(Rank.ACE, Suit.DIAMOND);

        List<Card> expected = new ArrayList<>(Arrays.asList(diamondAce));

        Hand hand = new Hand();
        hand.addCard(diamondAce);
        List<Card> actual = hand.getCards();
        
        assertEquals(expected, actual);
    }

    @Test
    public void addCardTest_ArgsNull() {
        Hand hand = new Hand();
        assertThrows(NullPointerException.class, () -> hand.addCard(null));
    }

    @Test
    public void scoreTest_noAce_PipCards() {
        Card card1 = new Card(Rank.TWO, Suit.CLUB);
        Card card2 = new Card(Rank.TWO, Suit.DIAMOND);

        int expected = 4;

        Hand hand = new Hand();
        hand.addCard(card1);
        hand.addCard(card2);
        int actual = hand.score();
        
        assertEquals(expected, actual);
    }

    @Test
    public void scoreTest_noAce_CourtCards() {
        Card card1 = new Card(Rank.JACK, Suit.SPADE);
        Card card2 = new Card(Rank.QUEEN, Suit.HEART);
        Card card3 = new Card(Rank.KING, Suit.DIAMOND);

        int expected = 30;

        Hand hand = new Hand();
        hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card3);
        int actual = hand.score();
        
        assertEquals(expected, actual);
    }

    @Test
    public void scoreTest_1Ace_11points() {
        Card card1 = new Card(Rank.ACE, Suit.SPADE);
        Card card2 = new Card(Rank.TEN, Suit.CLUB);

        int expected = 21;

        Hand hand = new Hand();
        hand.addCard(card1);
        hand.addCard(card2);
        int actual = hand.score();
        
        assertEquals(expected, actual);
    }

    @Test
    public void scoreTest_1Ace_1points() {
        Card card1 = new Card(Rank.TWO, Suit.SPADE);
        Card card2 = new Card(Rank.NINE, Suit.CLUB);
        Card card3 = new Card(Rank.ACE, Suit.CLUB);

        int expected = 12;

        Hand hand = new Hand();
        hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card3);
        int actual = hand.score();
        
        assertEquals(expected, actual);
    }

    @Test
    public void scoreTest_2Ace_firstCard_11points() {
        Card card1 = new Card(Rank.ACE, Suit.SPADE);
        Card card2 = new Card(Rank.ACE, Suit.CLUB);

        int expected = 12;

        Hand hand = new Hand();
        hand.addCard(card1);
        hand.addCard(card2);
        int actual = hand.score();
        
        assertEquals(expected, actual);
    }

    @Test
    public void scoreTest_2Ace_1points() {
        Card card1 = new Card(Rank.ACE, Suit.SPADE);
        Card card2 = new Card(Rank.ACE, Suit.CLUB);
        Card card3 = new Card(Rank.KING, Suit.DIAMOND);

        int expected = 12;

        Hand hand = new Hand();
        hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card3);
        int actual = hand.score();
        
        assertEquals(expected, actual);
    }

    @Test
    public void scoreTest_0points() {
        int expected = 0;

        Hand hand = new Hand();
        int actual = hand.score();
        
        assertEquals(expected, actual);
    }
}
