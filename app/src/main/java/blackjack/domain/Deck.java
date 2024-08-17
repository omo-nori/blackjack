package blackjack.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 山札（トランプのカード一式）を管理するクラス.
 */
public class Deck {
    private List<Card> deck;
    private CardPicker picker;

    /**
     * コンストラクタ.
     * カード一式を生成する
     * @param picker カードを引くときの方法
     */
    public Deck(CardPicker picker) {
        this.picker = Objects.requireNonNull(picker);
        deck = Arrays.stream(Rank.values()).flatMap(
            rank -> Arrays.stream(Suit.values()).map(
                suit -> new Card(rank, suit))).collect(Collectors.toList());
    }

    /**
     * カードを1枚引く.
     * @return 引いたカード。山札にカードがない場合、{@code Optional.empty}を返す。
     */
    public Optional<Card> draw() {
        if (deck.isEmpty()) {
            return Optional.empty();
        }
        Card card = picker.pick(Collections.unmodifiableList(deck));
        deck.remove(card);
        return Optional.of(card);
    }
}
