package blackjack.domain;

import java.util.List;
import java.util.Random;

public class RandomCardPicker implements CardPicker {
    private Random random;
    
    public RandomCardPicker() {
        random = new Random();
    }
    public Card pick(List<Card> cards) {
        return cards.get(random.nextInt(cards.size()));
    }
}
