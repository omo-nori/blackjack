package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class RandomCardPickerTest {
    @Test
    public void pickTest() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.ACE, Suit.SPADE));
        cards.add(new Card(Rank.ACE, Suit.HEART));
        cards.add(new Card(Rank.ACE, Suit.DIAMOND));
        cards.add(new Card(Rank.ACE, Suit.CLUB));

        CardPicker picker = new RandomCardPicker();
        Card actual = picker.pick(cards);
        assertTrue(cards.contains(actual));
        System.out.println(actual);
    }

}
