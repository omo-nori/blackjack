package blackjack.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * ゲームの勝敗の種類
 */
public enum Decision {
    WIN_NORMAL {
        @Override
        public BigDecimal calculateDividends(BigDecimal wager) {
            return wager;
        }
    },
    WIN_BLACKJACK {
        private static final BigDecimal RAITO = BigDecimal.valueOf(1.5);
        @Override
        public BigDecimal calculateDividends(BigDecimal wager) {
            return wager.multiply(RAITO).setScale(0, RoundingMode.DOWN);
        }
    },
    LOSE {
        @Override
        public BigDecimal calculateDividends(BigDecimal wager) {
            return wager.negate();
        }
    },
    TIE {
        @Override
        public BigDecimal calculateDividends(BigDecimal wager) {
            return BigDecimal.ZERO;
        }
    };

    /**
     * 配当金を計算する.
     * @param wager 賭け金
     * @return 配当金。必ず整数となる。ゼロ、マイナス値がありえる。
     */
    public abstract BigDecimal calculateDividends(BigDecimal wager);
}
