package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    public void drawTest_Fixed() {
        List<Card> expectedCards = new ArrayList<>();
        expectedCards.add(new Card(Rank.ACE, Suit.SPADE));
        expectedCards.add(new Card(Rank.ACE, Suit.HEART));
        expectedCards.add(new Card(Rank.ACE, Suit.DIAMOND));
        expectedCards.add(new Card(Rank.ACE, Suit.CLUB));
        expectedCards.add(new Card(Rank.TWO, Suit.SPADE));
        expectedCards.add(new Card(Rank.TWO, Suit.HEART));
        expectedCards.add(new Card(Rank.TWO, Suit.DIAMOND));
        expectedCards.add(new Card(Rank.TWO, Suit.CLUB));
        expectedCards.add(new Card(Rank.THREE, Suit.SPADE));
        expectedCards.add(new Card(Rank.THREE, Suit.HEART));
        expectedCards.add(new Card(Rank.THREE, Suit.DIAMOND));
        expectedCards.add(new Card(Rank.THREE, Suit.CLUB));
        expectedCards.add(new Card(Rank.FOUR, Suit.SPADE));
        expectedCards.add(new Card(Rank.FOUR, Suit.HEART));
        expectedCards.add(new Card(Rank.FOUR, Suit.DIAMOND));
        expectedCards.add(new Card(Rank.FOUR, Suit.CLUB));
        expectedCards.add(new Card(Rank.FIVE, Suit.SPADE));
        expectedCards.add(new Card(Rank.FIVE, Suit.HEART));
        expectedCards.add(new Card(Rank.FIVE, Suit.DIAMOND));
        expectedCards.add(new Card(Rank.FIVE, Suit.CLUB));
        expectedCards.add(new Card(Rank.SIX, Suit.SPADE));
        expectedCards.add(new Card(Rank.SIX, Suit.HEART));
        expectedCards.add(new Card(Rank.SIX, Suit.DIAMOND));
        expectedCards.add(new Card(Rank.SIX, Suit.CLUB));
        expectedCards.add(new Card(Rank.SEVEN, Suit.SPADE));
        expectedCards.add(new Card(Rank.SEVEN, Suit.HEART));
        expectedCards.add(new Card(Rank.SEVEN, Suit.DIAMOND));
        expectedCards.add(new Card(Rank.SEVEN, Suit.CLUB));
        expectedCards.add(new Card(Rank.EIGHT, Suit.SPADE));
        expectedCards.add(new Card(Rank.EIGHT, Suit.HEART));
        expectedCards.add(new Card(Rank.EIGHT, Suit.DIAMOND));
        expectedCards.add(new Card(Rank.EIGHT, Suit.CLUB));
        expectedCards.add(new Card(Rank.NINE, Suit.SPADE));
        expectedCards.add(new Card(Rank.NINE, Suit.HEART));
        expectedCards.add(new Card(Rank.NINE, Suit.DIAMOND));
        expectedCards.add(new Card(Rank.NINE, Suit.CLUB));
        expectedCards.add(new Card(Rank.TEN, Suit.SPADE));
        expectedCards.add(new Card(Rank.TEN, Suit.HEART));
        expectedCards.add(new Card(Rank.TEN, Suit.DIAMOND));
        expectedCards.add(new Card(Rank.TEN, Suit.CLUB));
        expectedCards.add(new Card(Rank.JACK, Suit.SPADE));
        expectedCards.add(new Card(Rank.JACK, Suit.HEART));
        expectedCards.add(new Card(Rank.JACK, Suit.DIAMOND));
        expectedCards.add(new Card(Rank.JACK, Suit.CLUB));
        expectedCards.add(new Card(Rank.QUEEN, Suit.SPADE));
        expectedCards.add(new Card(Rank.QUEEN, Suit.HEART));
        expectedCards.add(new Card(Rank.QUEEN, Suit.DIAMOND));
        expectedCards.add(new Card(Rank.QUEEN, Suit.CLUB));
        expectedCards.add(new Card(Rank.KING, Suit.SPADE));
        expectedCards.add(new Card(Rank.KING, Suit.HEART));
        expectedCards.add(new Card(Rank.KING, Suit.DIAMOND));
        expectedCards.add(new Card(Rank.KING, Suit.CLUB));

        Deck deck = new Deck(new FixedCardPicker(0));

        for (int i = 0; i < 52; i++) {
            Card actual = deck.draw().get();
            Card expected = expectedCards.get(i);
            assertEquals(expected, actual);
        }
        
        assertFalse(deck.draw().isPresent());
    }

    @Test
    public void drawTest_Random() {
        Deck deck = new Deck(new RandomCardPicker());

        for (int i = 0; i < 52; i++) {
            Optional<Card> actual = deck.draw();
            assertTrue(actual.isPresent());
            System.out.println(actual.get());
        }
        
        assertFalse(deck.draw().isPresent());
    }
}
