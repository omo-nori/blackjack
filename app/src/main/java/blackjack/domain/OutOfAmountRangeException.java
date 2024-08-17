package blackjack.domain;

/**
 * ユーザーの入力するチップ枚数がゲームで取り扱う範囲を超える場合に発生する例外
 */
public class OutOfAmountRangeException extends RuntimeException {
    public OutOfAmountRangeException() {
        super();
    }
}
