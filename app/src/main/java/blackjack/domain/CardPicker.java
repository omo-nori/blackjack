package blackjack.domain;

import java.util.List;

/**
 * カードを引くクラス.
 */
public interface CardPicker {
    /**
     * カードの束から1枚のカードを引く.
     * @param cards カードの束
     * @return 引いたカード
     */
    public Card pick(List<Card> cards);
}
