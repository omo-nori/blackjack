package blackjack.domain;

public enum Suit {
    SPADE("♠"),
    HEART("♥"),
    DIAMOND("♦"),
    CLUB("♣");

    private String symbol;

    private Suit(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }
}
