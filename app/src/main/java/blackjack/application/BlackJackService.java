package blackjack.application;

import java.math.BigDecimal;

import blackjack.domain.CardPicker;
import blackjack.domain.CreditManager;
import blackjack.domain.Decision;
import blackjack.domain.Game;
import blackjack.domain.GameStatus;

/**
 * ブラックジャックの各種操作を提供するクラス.
 */
public class BlackJackService {
    private CreditManager creditManager;
    private CardPicker picker;
    private Game game;

    public BlackJackService(CreditManager creditManager, CardPicker picker) {
        this.creditManager = creditManager;
        this.picker = picker;
    }

    /**
     * 賭け金を設定する.
     * @param wager 賭け金
     */
    public void bet(BigDecimal wager) {
        game = new Game(creditManager, picker);
        game.bet(wager);
    }

    /**
     * プレイヤーとディーラーに2枚ずつカードを配る.
     * @return カードを配った後のゲーム詳細
     */
    public GameDto deal() {
        game.deal();
        return GameDto.from(game);
    }

    /**
     * プレイヤーがカードを1枚追加する.
     * @return カード1枚追加後のゲーム詳細
     */
    public GameDto hit() {
        game.hit();
        return GameDto.from(game);
    }

    /**
     * プレイヤーのターンを終了する.
     * @return プレイヤーターン終了後のゲーム詳細
     */
    public GameDto stand() {
        game.stand();
        return GameDto.from(game);
    }

    /**
     * ディーラーのターンを行う.
     * @return ディーラーターン終了後のゲーム詳細
     */
    public GameDto dealerTurn() {
        GameStatus status = GameStatus.DEALER_TURN;
        while (status == GameStatus.DEALER_TURN) {
            game.dealerPlay();
            status = game.getStatus();
        }
        return GameDto.from(game);
    }

    /**
     * 勝敗を決めて精算する.
     * @return 勝敗結果
     */
    public String decide() {
        Decision decision = game.decide();
        game.settle(decision);
        return decision.toString();
    }

    /**
     * 賭け金を取得する.
     * @return 賭け金
     */
    public BigDecimal getCredit() {
        return creditManager.getCredit();
    }

    /**
     * ゲーム詳細を取得する.
     * @return ゲーム詳細
     */
    public GameDto getGameDto() {
        return GameDto.from(game);
    }
}
