package blackjack.domain;

/**
 * カードのマークの種類を表すクラス.
 */
public enum Suit {
    SPADE("♠"),
    HEART("♥"),
    DIAMOND("♦"),
    CLUB("♣");

    private String symbol;

    private Suit(String symbol) {
        this.symbol = symbol;
    }

    /**
     * カードのマークを取得する.
     * @return マーク（♠, ♥, ♦, ♣のいずれか）
     */
    public String getSymbol() {
        return this.symbol;
    }
}
