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
    public void drawTest_Specific() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.ACE, Suit.SPADE));
        cards.add(new Card(Rank.ACE, Suit.HEART));
        cards.add(new Card(Rank.ACE, Suit.DIAMOND));
        cards.add(new Card(Rank.ACE, Suit.CLUB));
        cards.add(new Card(Rank.TWO, Suit.SPADE));
        cards.add(new Card(Rank.TWO, Suit.HEART));
        cards.add(new Card(Rank.TWO, Suit.DIAMOND));
        cards.add(new Card(Rank.TWO, Suit.CLUB));
        cards.add(new Card(Rank.THREE, Suit.SPADE));
        cards.add(new Card(Rank.THREE, Suit.HEART));
        cards.add(new Card(Rank.THREE, Suit.DIAMOND));
        cards.add(new Card(Rank.THREE, Suit.CLUB));
        cards.add(new Card(Rank.FOUR, Suit.SPADE));
        cards.add(new Card(Rank.FOUR, Suit.HEART));
        cards.add(new Card(Rank.FOUR, Suit.DIAMOND));
        cards.add(new Card(Rank.FOUR, Suit.CLUB));
        cards.add(new Card(Rank.FIVE, Suit.SPADE));
        cards.add(new Card(Rank.FIVE, Suit.HEART));
        cards.add(new Card(Rank.FIVE, Suit.DIAMOND));
        cards.add(new Card(Rank.FIVE, Suit.CLUB));
        cards.add(new Card(Rank.SIX, Suit.SPADE));
        cards.add(new Card(Rank.SIX, Suit.HEART));
        cards.add(new Card(Rank.SIX, Suit.DIAMOND));
        cards.add(new Card(Rank.SIX, Suit.CLUB));
        cards.add(new Card(Rank.SEVEN, Suit.SPADE));
        cards.add(new Card(Rank.SEVEN, Suit.HEART));
        cards.add(new Card(Rank.SEVEN, Suit.DIAMOND));
        cards.add(new Card(Rank.SEVEN, Suit.CLUB));
        cards.add(new Card(Rank.EIGHT, Suit.SPADE));
        cards.add(new Card(Rank.EIGHT, Suit.HEART));
        cards.add(new Card(Rank.EIGHT, Suit.DIAMOND));
        cards.add(new Card(Rank.EIGHT, Suit.CLUB));
        cards.add(new Card(Rank.NINE, Suit.SPADE));
        cards.add(new Card(Rank.NINE, Suit.HEART));
        cards.add(new Card(Rank.NINE, Suit.DIAMOND));
        cards.add(new Card(Rank.NINE, Suit.CLUB));
        cards.add(new Card(Rank.TEN, Suit.SPADE));
        cards.add(new Card(Rank.TEN, Suit.HEART));
        cards.add(new Card(Rank.TEN, Suit.DIAMOND));
        cards.add(new Card(Rank.TEN, Suit.CLUB));
        cards.add(new Card(Rank.JACK, Suit.SPADE));
        cards.add(new Card(Rank.JACK, Suit.HEART));
        cards.add(new Card(Rank.JACK, Suit.DIAMOND));
        cards.add(new Card(Rank.JACK, Suit.CLUB));
        cards.add(new Card(Rank.QUEEN, Suit.SPADE));
        cards.add(new Card(Rank.QUEEN, Suit.HEART));
        cards.add(new Card(Rank.QUEEN, Suit.DIAMOND));
        cards.add(new Card(Rank.QUEEN, Suit.CLUB));
        cards.add(new Card(Rank.KING, Suit.SPADE));
        cards.add(new Card(Rank.KING, Suit.HEART));
        cards.add(new Card(Rank.KING, Suit.DIAMOND));
        cards.add(new Card(Rank.KING, Suit.CLUB));

        SpecificCardPicker picker = new SpecificCardPicker(new Card(Rank.ACE, Suit.SPADE));
        Deck deck = new Deck(picker);

        for (int i = 0; i < 52; i++) {
            picker.setCard(cards.get(i));
            Card actual = deck.draw().get();
            Card expected = cards.get(i);
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
