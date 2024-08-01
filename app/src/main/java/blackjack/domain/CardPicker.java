package blackjack.domain;

import java.util.List;

public interface CardPicker {
    public Card pick(List<Card> cards);
}
