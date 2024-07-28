package blackjack.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CreditManager {
    private BigDecimal credit;
    private BigDecimal wager;

    private static final BigDecimal MIN_WAGER = new BigDecimal(1);
    private static final BigDecimal MAX_WAGER = new BigDecimal(100);

    public CreditManager() {
        this(new BigDecimal(100));
    }

    public CreditManager(BigDecimal credit) {
        this.credit = credit;
        clearWager();
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
        credit = credit.subtract(wager);
    }

    public void addPayout(double raito) {
        credit = credit.add(wager);
        credit = credit.add(wager.multiply(new BigDecimal(String.valueOf(raito))).setScale(0, RoundingMode.DOWN));
        clearWager();
    }

    public void returnWager() {
        credit = credit.add(wager);
        clearWager();
    }

    public void clearWager() {
        wager = BigDecimal.ZERO;
    }
}
