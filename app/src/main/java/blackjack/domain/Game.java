package blackjack.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Game {
    private Hand player;
    private Hand dealer;
    private Deck deck;
    private CreditManager creditManager;
    private GameStatus status;

    private static final int DEALER_MIN_SCORE = 17;

    public Game(CreditManager creditManager, CardPicker picker) {
        deck = new Deck(picker);
        player = new Hand();
        dealer = new Hand();
        this.creditManager = creditManager;
        status = GameStatus.PLAYER_TURN;;
    }

    public List<Card> getPlayerCards() {
        return player.getCards();
    }

    public List<Card> getDealerCards() {
        return dealer.getCards();
    }

    public int getPlayerScore() {
        return player.score();
    }

    public int getDealerScore() {
        return dealer.score();
    }

    public GameStatus getStatus() {
        return status;
    }

    public void bet(BigDecimal wager) {
        creditManager.bet(wager);
    }

    public void deal() {
        player.addCard(deck.draw().get());
        player.addCard(deck.draw().get());
        dealer.addCard(deck.draw().get());
        dealer.addCard(deck.draw().get());
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

    public Decision decide() {
        if (player.isBust()) {
            return Decision.LOSE;
        }

        if (player.isBlackJack()) {
            if (dealer.isBlackJack()) {
                return Decision.TIE;
            } else {
                return Decision.WIN_BLACKJACK;
            }
        }

        if (dealer.isBust()) {
            return Decision.WIN_NORMAL;
        }

        if (player.score() > dealer.score()) {
            return Decision.WIN_NORMAL;
        }

        if (player.score() < dealer.score()) {
            return Decision.LOSE;
        }

        return Decision.TIE;
    }

    public void settle(Decision decision) {
        creditManager.settle(Objects.requireNonNull(decision));
    }
}
