package blackjack.domain;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * 賭けの軍資金を管理するクラス.
 */
public class CreditManager {
    private BigDecimal credit;
    private BigDecimal wager;
    private ResourceBundle massages = ResourceBundle.getBundle("massages");;

    private static final BigDecimal INITIAL_MIN_CREDIT = BigDecimal.ONE;
    private static final BigDecimal INITIAL_MAX_CREDIT = BigDecimal.valueOf(100);
    private static final BigDecimal MIN_WAGER = BigDecimal.ONE;
    private static final BigDecimal MAX_WAGER = BigDecimal.valueOf(100);

    /**
     * 引数なしコンストラクタ.
     * 所持チップ枚数が初期設定の最大値で生成される
     */
    public CreditManager() {
        this(INITIAL_MAX_CREDIT);
    }

    /**
     * 引数ありコンストラクタ.
     * @param credit 所持チップ枚数を設定
     * @throws OutOfAmountRangeException 所持チップ枚数が1枚〜100枚の範囲外の場合、エラー
     */
    public CreditManager(BigDecimal credit) {
        if (credit.compareTo(INITIAL_MIN_CREDIT) < 0) {
            throw new OutOfAmountRangeException(
                MessageFormat.format(massages.getString("error_wager_under_limit"), INITIAL_MIN_CREDIT.toString()));
        }

        if (credit.compareTo(INITIAL_MAX_CREDIT) > 0) {
            throw new OutOfAmountRangeException(
                MessageFormat.format(massages.getString("error_wager_over_limit"), INITIAL_MAX_CREDIT.toString()));
        }

        this.credit = credit;
        initializeWager();
    }

    /**
     * 所持チップ枚数を取得する.
     * @return 所持チップ枚数
     */
    public BigDecimal getCredit() {
        return credit;
    }

    /**
     * 賭け金を取得する.
     * @return 賭け金
     */
    public BigDecimal getWager() {
        return wager;
    }

    /**
     * チップを賭ける.
     * @param wager 賭けチップ枚数
     * @throws OutOfAmountRangeException 賭けチップ枚数が1枚〜100枚の範囲外の場合、または、所持チップ枚数を超える場合、エラー。
     */
    public void bet(BigDecimal wager) {
        if (wager.compareTo(MIN_WAGER) < 0) {
            throw new OutOfAmountRangeException(
                MessageFormat.format(massages.getString("error_wager_under_limit"), MIN_WAGER.toString()));
        }
        
        if (wager.compareTo(MAX_WAGER) > 0) {
            throw new OutOfAmountRangeException(
                MessageFormat.format(massages.getString("error_wager_over_limit"), MAX_WAGER.toString()));
        }

        if (wager.compareTo(credit) > 0) {
            throw new OutOfAmountRangeException(massages.getString("error_wager_over_current_credit"));
        }

        this.wager = wager;
    }

    /**
     * ゲームの勝敗で賭けを精算する.
     * @param decision ゲーム勝敗
     */
    public void settle(Decision decision) {
        credit = credit.add(decision.calculateDividends(wager));
        initializeWager();
    }

    /**
     * 賭け金をクリアする.
     */
    public void initializeWager() {
        wager = BigDecimal.ZERO;
    }
}
