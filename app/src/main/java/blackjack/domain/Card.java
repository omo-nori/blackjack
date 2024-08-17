package blackjack.domain;

import java.util.Objects;

/**
 * トランプのカードを表すクラス.
 */
public class Card {
    private Rank rank;
    private Suit suit;

    private static final int MAX_POINTS = 10;

    public Card(Rank rank, Suit suit) {
        this.rank = Objects.requireNonNull(rank);
        this.suit = Objects.requireNonNull(suit);
    }

    /**
     * カードのランクを取得する.
     * @return ランク
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * カードのマークを取得する.
     * @return マーク
     */
    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return suit.getSymbol() + " " + rank.getSymbol();
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

    /**
     * ブラックジャックにおけるカードの点数を取得する.
     * 
     * @return 数札はランクと同じ点数（エースは1点）。絵札は10点。
     */
    public int getPoints() {
        if (rank.isCourtCard()) {
            return MAX_POINTS;
        } else {
            return rank.getNumber();
        }
    }
}
