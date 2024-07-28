package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class HandTest {
    @Test
    public void constructorTest_firstArgsNull() {
        Card spadeAce = new Card(Rank.ACE, Suit.SPADE);

        assertThrows(NullPointerException.class, () -> new Hand(null, spadeAce));
    }

    @Test
    public void constructorTest_secondArgsNull() {
        Card spadeAce = new Card(Rank.ACE, Suit.SPADE);

        assertThrows(NullPointerException.class, () -> new Hand(spadeAce, null));
    }

    @Test
    public void getCardsTest() {
        Card spadeAce = new Card(Rank.ACE, Suit.SPADE);
        Card heartAce = new Card(Rank.ACE, Suit.HEART);

        List<Card> expected = new ArrayList<>(Arrays.asList(spadeAce, heartAce));

        Hand hand = new Hand(spadeAce, heartAce);
        List<Card> actual = hand.getCards();
        
        assertEquals(expected, actual);
    }

    @Test
    public void addCardTest() {
        Card spadeAce = new Card(Rank.ACE, Suit.SPADE);
        Card heartAce = new Card(Rank.ACE, Suit.HEART);
        Card diamondAce = new Card(Rank.ACE, Suit.DIAMOND);

        List<Card> expected = new ArrayList<>(Arrays.asList(spadeAce, heartAce, diamondAce));

        Hand hand = new Hand(spadeAce, heartAce);
        hand.addCard(diamondAce);
        List<Card> actual = hand.getCards();
        
        assertEquals(expected, actual);
    }

    @Test
    public void scoreTest_noAce_PipCards() {
        Card card1 = new Card(Rank.TWO, Suit.SPADE);
        Card card2 = new Card(Rank.TWO, Suit.HEART);

        int expected = 4;

        Hand hand = new Hand(card1, card2);
        int actual = hand.score();
        
        assertEquals(expected, actual);
    }

    @Test
    public void scoreTest_noAce_CourtCards() {
        Card card1 = new Card(Rank.JACK, Suit.SPADE);
        Card card2 = new Card(Rank.QUEEN, Suit.HEART);
        Card card3 = new Card(Rank.KING, Suit.DIAMOND);

        int expected = 30;

        Hand hand = new Hand(card1, card2);
        hand.addCard(card3);
        int actual = hand.score();
        
        assertEquals(expected, actual);
    }

    @Test
    public void scoreTest_1Ace_11points() {
        Card card1 = new Card(Rank.ACE, Suit.SPADE);
        Card card2 = new Card(Rank.TEN, Suit.CLUB);

        int expected = 21;

        Hand hand = new Hand(card1, card2);
        int actual = hand.score();
        
        assertEquals(expected, actual);
    }

    @Test
    public void scoreTest_1Ace_1points() {
        Card card1 = new Card(Rank.TWO, Suit.SPADE);
        Card card2 = new Card(Rank.NINE, Suit.CLUB);
        Card card3 = new Card(Rank.ACE, Suit.CLUB);

        int expected = 12;

        Hand hand = new Hand(card1, card2);
        hand.addCard(card3);
        int actual = hand.score();
        
        assertEquals(expected, actual);
    }

    @Test
    public void scoreTest_2Ace_firstCard_11points() {
        Card card1 = new Card(Rank.ACE, Suit.SPADE);
        Card card2 = new Card(Rank.ACE, Suit.CLUB);

        int expected = 12;

        Hand hand = new Hand(card1, card2);
        int actual = hand.score();
        
        assertEquals(expected, actual);
    }

    @Test
    public void scoreTest_2Ace_1points() {
        Card card1 = new Card(Rank.ACE, Suit.SPADE);
        Card card2 = new Card(Rank.ACE, Suit.CLUB);
        Card card3 = new Card(Rank.KING, Suit.DIAMOND);

        int expected = 12;

        Hand hand = new Hand(card1, card2);
        hand.addCard(card3);
        int actual = hand.score();
        
        assertEquals(expected, actual);
    }
}
