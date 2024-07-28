package blackjack.domain;

import java.math.BigDecimal;
import java.util.List;

public class Game {
    private Hand player;
    private Hand dealer;
    private Deck deck;
    private CreditManager creditManager;

    private static final int DEALER_MIN_SCORE = 17;

    public Game(CreditManager creditManager) {
        deck = new Deck(new RandomCardPicker());
        player = new Hand(deck.draw().get(), deck.draw().get());
        dealer = new Hand(deck.draw().get(), deck.draw().get());
        this.creditManager = creditManager;
    }

    public List<Card> getPlayerCards() {
        return player.getCards();
    }

    public List<Card> getDealerCards() {
        return dealer.getCards();
    }

    public void bet(BigDecimal wager) {
        creditManager.bet(wager);
    }

    public void hit() {
        player.addCard(deck.draw().get());
    }

    public void dealerTurn() {
        if (dealer.isBlackJack()) {
            return;
        }
        while (dealer.score() <= DEALER_MIN_SCORE) {
            if (dealer.score() == DEALER_MIN_SCORE && !dealer.hasAce()) {
                return;
            }
            dealer.addCard(deck.draw().get());
        }
    }

}
