package blackjack.presentation;

import java.math.BigDecimal;

import blackjack.application.BlackJackService;
import blackjack.application.GameDto;

public class BlackJackController {
    private BlackJackService service;

    public BlackJackController(BlackJackService service) {
        this.service = service;
    }

    public int getCredit() {
        return service.getCredit().intValue();
    }

    public void bet(int wager) {
        service.bet(new BigDecimal(wager));
    }

    public void deal() {
        service.deal();
    }

    public GameDto getGameDto() {
        return service.getGameDto();
    }

    public GameDto hit() {
        return service.hit();
    }

    public GameDto stand() {
        return service.stand();
    }

    public GameDto dealerTurn() {
        return service.dealerTurn();
    }

    public String decide() {
        return service.decide();
    }
}
