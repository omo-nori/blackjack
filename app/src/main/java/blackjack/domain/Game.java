package blackjack.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * ゲームの操作や状態を管理するクラス.
 */
public class Game {
    private Hand player;
    private Hand dealer;
    private Deck deck;
    private CreditManager creditManager;
    private GameStatus status;

    private static final int DEALER_MIN_SCORE = 17;

    /**
     * コンストラクタ.
     * ゲームに必要な情報を生成する
     * @param creditManager 軍資金管理
     * @param picker 山札からカードを引く方法
     */
    public Game(CreditManager creditManager, CardPicker picker) {
        deck = new Deck(picker);
        player = new Hand();
        dealer = new Hand();
        this.creditManager = creditManager;
        status = GameStatus.PLAYER_TURN;;
    }

    /**
     * プレイヤーの持ち札を取得する.
     * @return プレイヤーの持ち札。0枚がありえる。
     */
    public List<Card> getPlayerCards() {
        return player.getCards();
    }

    /**
     * ディーラーの持ち札を取得する.
     * @return ディーラーの持ち札。0枚がありえる。
     */
    public List<Card> getDealerCards() {
        return dealer.getCards();
    }

    /**
     * プレイヤーの得点を取得する.
     * @return 得点
     */
    public int getPlayerScore() {
        return player.score();
    }

    /**
     * ディーラーの得点を取得する.
     * @return 得点
     */
    public int getDealerScore() {
        return dealer.score();
    }

    /**
     * ゲームの進行状態を取得する.
     * @return 進行状態
     */
    public GameStatus getStatus() {
        return status;
    }

    /**
     * 賭け金を設定する.
     * @param wager 賭け金
     */
    public void bet(BigDecimal wager) {
        creditManager.bet(wager);
    }

    /**
     * プレイヤーとディーラーに2枚ずつカードを配る.
     * ブラックジャックのゲーム開始時に実行すること
     */
    public void deal() {
        player.addCard(deck.draw().get());
        player.addCard(deck.draw().get());
        dealer.addCard(deck.draw().get());
        dealer.addCard(deck.draw().get());
    }

    /**
     * プレイヤーがカードを1枚追加する.
     */
    public void hit() {
        player.addCard(deck.draw().get());
        if (player.isBust()) {
            status = GameStatus.MATCH;
            return;
        }
    }

    /**
     * プレイヤーのターンを終了する.
     */
    public void stand() {
        if (player.isBlackJack()) {
            status = GameStatus.MATCH;
            return;
        }
        status = GameStatus.DEALER_TURN;
    }

    /**
     * ディーラーのターンを行う.
     * <ul>
     * 以下の場合、ディーラーのターンを終了する
     * <li>得点が17点を超えている
     * <li>得点が17点でエースを含んでいない
     * </ul>
     */
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

    /**
     * 勝敗を決める.
     * <ul>
     * 勝敗の決め方は以下のとおり
     * <li>プレイヤーがバストしている場合、プレイヤーの負け
     * <li>プレイヤーとディーラーがどちらもブラックジャックの場合、引き分け
     * <li>プレイヤーのみブラックジャックの場合、プレイヤーの勝ち（ブラックジャック配当）
     * <li>ディーラーがバスとしている場合、プレイヤーの勝ち
     * <li>プレイヤーのほうが得点が高い場合、プレイヤーの勝ち
     * <li>プレイヤーのほうが得点が低い場合、プレイヤーの負け
     * <li>上記以外（プレイヤーとディーラーの得点が同じ）の場合、引き分け
     * </ul>
     * @return 勝敗結果
     */
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

    /**
     * ゲームの勝敗で賭けを精算する.
     * @param decision ゲーム勝敗
     */
    public void settle(Decision decision) {
        creditManager.settle(Objects.requireNonNull(decision));
    }
}
