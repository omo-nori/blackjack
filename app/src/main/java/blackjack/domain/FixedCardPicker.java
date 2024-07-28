package blackjack.domain;

public class FixedCardPicker implements CardPicker {
    private int number;

    public FixedCardPicker(int number) {
        this.number = number;
    }
    public int pickNumber(int total) {
        return number;
    }
}
