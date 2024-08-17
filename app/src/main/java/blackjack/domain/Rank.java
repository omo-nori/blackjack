package blackjack.domain;

import java.util.EnumSet;

/**
 * カードランクの種類を表すクラス.
 */
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

    /**
     * カードランクのシンボルを取得する.
     * @return エース以外の数札は数字（2〜10いずれか）を返す。エースと絵札は頭文字（A, J, Q, Kいずれか）を返す。
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * カードの数字を取得する.
     * @return 1〜13いずれかを返す
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * 絵札かどうかを判定する.
     * @return {@code true} 絵札 {@code false} 数札（エースは数札と判定する）
     */
    public boolean isCourtCard() {
        return courtCard.contains(this);
    }
}