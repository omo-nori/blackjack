package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class CreditManagerTest {
    @Test
    public void constructorTest_setCredit() {
        BigDecimal credit = new BigDecimal(200);
        CreditManager actual = new CreditManager(credit);
        assertEquals(credit, actual.getCredit());
        assertEquals(BigDecimal.ZERO, actual.getWager());
    }

    @Test
    public void constructorTest_noArgs() {
        BigDecimal credit = new BigDecimal(100);
        CreditManager actual = new CreditManager();
        assertEquals(credit, actual.getCredit());
        assertEquals(BigDecimal.ZERO, actual.getWager());
    }

    @Test
    public void betTest_min() {
        CreditManager actual = new CreditManager();
        BigDecimal wager = new BigDecimal(1);
        actual.bet(wager);
        assertEquals(wager, actual.getWager());
        assertEquals(new BigDecimal(99), actual.getCredit());
    }

    @Test
    public void betTest_max() {
        CreditManager actual = new CreditManager();
        BigDecimal wager = new BigDecimal(100);
        actual.bet(wager);
        assertEquals(wager, actual.getWager());
        assertEquals(new BigDecimal(0), actual.getCredit());
    }

    @Test
    public void betTest_under() {
        CreditManager actual = new CreditManager();
        BigDecimal wager = new BigDecimal(0);
        assertThrows(OutOfAmountRangeException.class, () -> actual.bet(wager));
    }

    @Test
    public void betTest_over() {
        CreditManager actual = new CreditManager();
        BigDecimal wager = new BigDecimal(101);
        assertThrows(OutOfAmountRangeException.class, () -> actual.bet(wager));
    }

    @Test
    public void betTest_overCredit() {
        CreditManager actual = new CreditManager(new BigDecimal(50));
        BigDecimal wager = new BigDecimal(51);
        assertThrows(OutOfAmountRangeException.class, () -> actual.bet(wager));
    }

    @Test
    public void addPayoutTest_oneTimes() {
        CreditManager actual = new CreditManager();
        BigDecimal wager = new BigDecimal(1);
        actual.bet(wager);
        actual.addPayout(1.0);
        BigDecimal expected = new BigDecimal(101);
        assertEquals(expected, actual.getCredit());
        assertEquals(BigDecimal.ZERO, actual.getWager());
    }

    @Test
    public void addPayoutTest_oneAndHalfTimes() {
        CreditManager actual = new CreditManager();
        BigDecimal wager = new BigDecimal(2);
        actual.bet(wager);
        actual.addPayout(1.5);
        BigDecimal expected = new BigDecimal(103);
        assertEquals(expected, actual.getCredit());
        assertEquals(BigDecimal.ZERO, actual.getWager());
    }

    @Test
    public void addPayoutTest_roundDown() {
        CreditManager actual = new CreditManager();
        BigDecimal wager = new BigDecimal(1);
        actual.bet(wager);
        actual.addPayout(1.5);
        BigDecimal expected = new BigDecimal(101);
        assertEquals(expected, actual.getCredit());
        assertEquals(BigDecimal.ZERO, actual.getWager());
    }

    @Test
    public void returnWagerTest() {
        CreditManager actual = new CreditManager();
        BigDecimal wager = new BigDecimal(1);
        actual.bet(wager);
        actual.returnWager();
        BigDecimal expected = new BigDecimal(100);
        assertEquals(expected, actual.getCredit());
        assertEquals(BigDecimal.ZERO, actual.getWager());
    }

    @Test
    public void clearWagerTest() {
        CreditManager actual = new CreditManager();
        BigDecimal wager = new BigDecimal(1);
        actual.bet(wager);
        actual.clearWager();
        BigDecimal expected = new BigDecimal(99);
        assertEquals(expected, actual.getCredit());
        assertEquals(BigDecimal.ZERO, actual.getWager());
    }

}
