package blackjack.application;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.Card;
import blackjack.domain.Game;

/**
 * ゲーム詳細を受け渡すクラス.
 * コンストラクタは公開せず、fromメソッドでインスタンスを返す
 * <ul>
 * 受け渡し情報の詳細
 * <li>プレイヤーの手札
 * <li>ディーラーの手札
 * <li>プレイヤーの得点
 * <li>ディーラーの得点
 * <li>ゲームの進行状態
 * </ul>
 */
public class GameDto {
    private List<String> playerHand;
    private List<String> dealerHand;
    private int playerScore;
    private int dealerScore;
    private String status;

    private GameDto() {}

    /**
     * ゲームのドメインクラスから外部に渡す情報のみを抽出し、DTOを生成する.
     * @param game ゲーム
     * @return 外部受け渡し情報
     */
    public static GameDto from(Game game) {
        GameDto dto = new GameDto();
        dto.playerHand = toStringCardList(game.getPlayerCards());
        dto.dealerHand = toStringCardList(game.getDealerCards());
        dto.playerScore = game.getPlayerScore();
        dto.dealerScore = game.getDealerScore();
        dto.status = game.getStatus().toString();
        return dto;
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

    private static List<String> toStringCardList(List<Card> cards) {
        return cards.stream().map(card -> card.toString()).collect(Collectors.toList());
    }
}
