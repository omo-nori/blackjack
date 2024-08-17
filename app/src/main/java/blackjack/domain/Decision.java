package blackjack.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    public abstract BigDecimal calculateDividends(BigDecimal wager);
}
