package blackjack.application;

import java.math.BigDecimal;

import blackjack.domain.CardPicker;
import blackjack.domain.CreditManager;
import blackjack.domain.Decision;
import blackjack.domain.Game;
import blackjack.domain.GameStatus;

public class BlackJackService {
    private CreditManager creditManager;
    private CardPicker picker;
    private Game game;

    public BlackJackService(CreditManager creditManager, CardPicker picker) {
        this.creditManager = creditManager;
        this.picker = picker;
    }

    public void bet(BigDecimal wager) {
        game = new Game(creditManager, picker);
        game.bet(wager);
    }

    public void deal() {
        game.deal();
    }

    public GameDto hit() {
        game.hit();
        return new GameDto(game);
    }

    public GameDto stand() {
        game.stand();
        return new GameDto(game);
    }

    public GameDto dealerTurn() {
        GameStatus status = GameStatus.DEALER_TURN;
        while (status == GameStatus.DEALER_TURN) {
            game.dealerPlay();
            status = game.getStatus();
        }
        return new GameDto(game);
    }

    public String decide() {
        Decision decision = game.decide();
        game.settle(decision);
        return decision.toString();

    }

    public BigDecimal getCredit() {
        return creditManager.getCredit();
    }

    public GameDto getGameDto() {
        return new GameDto(game);
    }
}
