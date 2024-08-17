package blackjack.domain;

import java.util.List;
import java.util.Objects;

/**
 * 山札から特定のカードを引くクラス.
 * 引きたいカードを指定してからカードを引く
 * 指定されたカードが山札に存在しない場合、山札の1番上のカードを引く
 */
public class SpecificCardPicker implements CardPicker {
    private Card specificCard;

    /**
     * コンストラクタ.
     * 引きたいカードを指定する
     * @param specificCard 指定カード。nullは許可しない。
     */
    public SpecificCardPicker(Card specificCard) {
        setCard(specificCard);
    }

    /**
     * 引きたいカードを指定する.
     * @param specificCard 指定カード。nullは許可しない。
     */
    public void setCard(Card specificCard) {
        this.specificCard = Objects.requireNonNull(specificCard);
    }

    public Card pick(List<Card> cards) {
        if (cards.contains(specificCard)) {
            return specificCard;
        }
        return cards.get(0);
    }

}
