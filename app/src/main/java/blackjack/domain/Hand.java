package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 手札を管理するクラス.
 */
public class Hand {
    private List<Card> cards;
    private static final int INITIAL_NUMBER_OF_CARDS = 2;
    private static final int ACE_BONUS = 10;
    private static final int MAX_SCORE = 21;

    /**
     * コンストラクタ.
     * 手札は0枚
     */
    public Hand() {
        cards = new ArrayList<>();
    }

    /**
     * 手札のカードをすべて取得する.
     * @return 手札のカードすべて
     */
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    /**
     * 手札にカードを1枚追加する.
     * @param card 追加するカード
     */
    public void addCard(Card card) {
        cards.add(Objects.requireNonNull(card));
    }

    /**
     * 手札の得点を計算する.
     * @return 得点
     */
    public int score() {
        int score = cards.stream().map(card -> card.getPoints()).reduce(0, Integer::sum);
        if (hasAce() && score + ACE_BONUS <= MAX_SCORE) {
            score = score + ACE_BONUS;
        }
        return score;
    }

    /**
     * 手札がバストしてるかどうかを判定する.
     * （バストとは、21点を超えること）
     * @return {@code true} バストしている {@code false} バストしていない
     */
    public boolean isBust() {
        if (score() > MAX_SCORE) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 手札がブラックジャックかどうかを判定する.
     * <ul>
     * 以下の条件をすべて満たすとブラックジャックと判定する
     * <li>手札の枚数が2枚（最初に配られてからカードを追加していない）
     * <li>手札の得点が21点（エースと10点札で構成されている）
     * </ul>
     * @return {@code true} ブラックジャック {@code false} ブラックジャックでない
     */
    public boolean isBlackJack() {
        if (cards.size() > INITIAL_NUMBER_OF_CARDS) {
            return false;
        }

        if (score() != MAX_SCORE) {
            return false;
        }

        return true;
    }

    /**
     * 手札にエースがあるかどうかを判定する.
     * @return {@code true} エースあり {@code false} エースなし
     */
    public boolean hasAce() {
        List<Rank> ranks = cards.stream().map(card -> card.getRank()).collect(Collectors.toList());
        if (ranks.contains(Rank.ACE)) {
            return true;
        } else {
            return false;
        }
    }
}
