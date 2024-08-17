package blackjack.domain;

import java.util.EnumSet;

public enum Rank {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("K", 13);

    private String symbol;
    private int number;
    private static final EnumSet<Rank> courtCard = EnumSet.of(JACK, QUEEN,KING);

    private Rank(String symbol, int number) {
        this.symbol = symbol;
        this.number = number;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public int getNumber() {
        return this.number;
    }

    public boolean isCourtCard() {
        return courtCard.contains(this);
    }
}