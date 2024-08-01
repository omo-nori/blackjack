package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SpecificCardPickerTest {
    @Test
    public void pickTest_containts() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.ACE, Suit.SPADE));
        cards.add(new Card(Rank.ACE, Suit.HEART));
        cards.add(new Card(Rank.ACE, Suit.DIAMOND));
        cards.add(new Card(Rank.ACE, Suit.CLUB));

        Card specificCard = new Card(Rank.ACE, Suit.DIAMOND);
        CardPicker picker = new SpecificCardPicker(specificCard);
        Card actual = picker.pick(cards);
        Card expected = new Card(Rank.ACE, Suit.DIAMOND);
        assertEquals(expected, actual);
    }

    @Test
    public void pickTest_noContaints() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.ACE, Suit.SPADE));
        cards.add(new Card(Rank.ACE, Suit.HEART));
        cards.add(new Card(Rank.ACE, Suit.DIAMOND));
        cards.add(new Card(Rank.ACE, Suit.CLUB));

        Card specificCard = new Card(Rank.TWO, Suit.DIAMOND);
        CardPicker picker = new SpecificCardPicker(specificCard);
        Card actual = picker.pick(cards);
        Card expected = cards.get(0);
        assertEquals(expected, actual);
    }
}
