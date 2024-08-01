package blackjack.domain;

import java.math.BigDecimal;
import java.util.List;

public class Game {
    private Hand player;
    private Hand dealer;
    private Deck deck;
    private CreditManager creditManager;
    private GameStatus status;

    private static final int DEALER_MIN_SCORE = 17;

    public Game(CreditManager creditManager, CardPicker picker) {
        deck = new Deck(picker);
        player = new Hand(deck.draw().get(), deck.draw().get());
        dealer = new Hand(deck.draw().get(), deck.draw().get());
        this.creditManager = creditManager;
        status = GameStatus.PLAYER_TURN;
    }

    public List<Card> getPlayerCards() {
        return player.getCards();
    }

    public List<Card> getDealerCards() {
        return dealer.getCards();
    }

    public GameStatus getStatus() {
        return status;
    }

    public void bet(BigDecimal wager) {
        creditManager.bet(wager);
    }

    public void hit() {
        player.addCard(deck.draw().get());
        if (player.isBust()) {
            status = GameStatus.MATCH;
            return;
        }
    }

    public void stand() {
        if (player.isBlackJack()) {
            status = GameStatus.MATCH;
            return;
        }
        status = GameStatus.DEALER_TURN;
    }

    public void dealerPlay() {
        if (dealer.score() > DEALER_MIN_SCORE) {
            status = GameStatus.MATCH;
            return;
        }

        if (dealer.score() == DEALER_MIN_SCORE && !dealer.hasAce()) {
            status = GameStatus.MATCH;
            return;
        }

        dealer.addCard(deck.draw().get());
    }
}
