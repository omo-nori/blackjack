package blackjack.domain;

import java.util.Objects;

public class Card {
    private Rank rank;
    private Suit suit;

    private static final int MAX_POINTS = 10;

    public Card(Rank rank, Suit suit) {
        this.rank = Objects.requireNonNull(rank);
        this.suit = Objects.requireNonNull(suit);
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return suit.getSymbol() + rank.getNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Card card)) {
            return false;
        }
        if (card.rank != this.rank) {
            return false;
        }
        if (card.suit != this.suit) {
            return false;
        }
        return true;
    }

    public int getPoints() {
        if (rank.isCourtCard()) {
            return MAX_POINTS;
        } else {
            return rank.getNumber();
        }
    }
}
