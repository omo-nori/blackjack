package blackjack.domain;

import java.util.Random;

public class RandomCardPicker implements CardPicker {
    private Random random;
    
    public RandomCardPicker() {
        random = new Random();
    }
    public int pickNumber(int total) {
        return random.nextInt(total);
    }
}
