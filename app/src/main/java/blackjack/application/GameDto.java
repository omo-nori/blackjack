package blackjack.application;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.Card;
import blackjack.domain.Game;

public class GameDto {
    private List<String> playerHand;
    private List<String> dealerHand;
    private int playerScore;
    private int dealerScore;
    private String status;

    public GameDto(Game game) {
        this.playerHand = toStringCardList(game.getPlayerCards());
        this.dealerHand = toStringCardList(game.getDealerCards());
        this.playerScore = game.getPlayerScore();
        this.dealerScore = game.getDealerScore();
        this.status = game.getStatus().toString();
    }

    public List<String> playerHand() {
        return playerHand;
    }

    public List<String> dealerHand() {
        return dealerHand;
    }

    public int playerScore() {
        return playerScore;
    }

    public int dealerScore() {
        return dealerScore;
    }

    public String status() {
        return status;
    }

    private List<String> toStringCardList(List<Card> cards) {
        return cards.stream().map(card -> card.toString()).collect(Collectors.toList());
    }
}
