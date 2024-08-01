package blackjack.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Deck {
    private List<Card> deck;
    private CardPicker picker;

    public Deck(CardPicker picker) {
        this.picker = Objects.requireNonNull(picker);
        deck = Arrays.stream(Rank.values()).flatMap(
            rank -> Arrays.stream(Suit.values()).map(
                suit -> new Card(rank, suit))).collect(Collectors.toList());
    }

    public Optional<Card> draw() {
        if (deck.isEmpty()) {
            return Optional.empty();
        }
        Card card = picker.pick(Collections.unmodifiableList(deck));
        deck.remove(card);
        return Optional.of(card);
    }
}
