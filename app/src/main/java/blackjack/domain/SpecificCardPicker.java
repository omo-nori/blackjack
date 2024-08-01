package blackjack.domain;

import java.util.List;
import java.util.Objects;

public class SpecificCardPicker implements CardPicker {
    private Card specificCard;

    public SpecificCardPicker(Card specificCard) {
        setCard(specificCard);
    }

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
