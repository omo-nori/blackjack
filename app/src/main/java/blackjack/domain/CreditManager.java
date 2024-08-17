package blackjack.domain;

import java.math.BigDecimal;

public class CreditManager {
    private BigDecimal credit;
    private BigDecimal wager;

    private static final BigDecimal INITIAL_MIN_CREDIT = BigDecimal.ONE;
    private static final BigDecimal INITIAL_MAX_CREDIT = BigDecimal.valueOf(100);
    private static final BigDecimal MIN_WAGER = BigDecimal.ONE;
    private static final BigDecimal MAX_WAGER = BigDecimal.valueOf(100);

    public CreditManager() {
        this(INITIAL_MAX_CREDIT);
    }

    public CreditManager(BigDecimal credit) {
        if (credit.compareTo(INITIAL_MIN_CREDIT) < 0) {
            throw new OutOfAmountRangeException();
        }

        if (credit.compareTo(INITIAL_MAX_CREDIT) > 0) {
            throw new OutOfAmountRangeException();
        }

        this.credit = credit;
        initializeWager();
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public BigDecimal getWager() {
        return wager;
    }

    public void bet(BigDecimal wager) {
        if (wager.compareTo(MIN_WAGER) < 0) {
            throw new OutOfAmountRangeException();
        }
        
        if (wager.compareTo(MAX_WAGER) > 0) {
            throw new OutOfAmountRangeException();
        }

        if (wager.compareTo(credit) > 0) {
            throw new OutOfAmountRangeException();
        }

        this.wager = wager;
    }

    public void settle(Decision decision) {
        credit = credit.add(decision.calculateDividends(wager));
        initializeWager();
    }

    public void initializeWager() {
        wager = BigDecimal.ZERO;
    }
}
