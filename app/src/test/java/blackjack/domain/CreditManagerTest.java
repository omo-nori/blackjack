package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class CreditManagerTest {
    @Test
    public void constructorTest_setCredit_min() {
        BigDecimal credit = BigDecimal.ONE;
        CreditManager actual = new CreditManager(credit);
        assertEquals(credit, actual.getCredit());
        assertEquals(BigDecimal.ZERO, actual.getWager());
    }

    @Test
    public void constructorTest_setCredit_max() {
        BigDecimal credit = BigDecimal.valueOf(100);
        CreditManager actual = new CreditManager(credit);
        assertEquals(credit, actual.getCredit());
        assertEquals(BigDecimal.ZERO, actual.getWager());
    }

    @Test
    public void constructorTest_setCredit_under() {
        BigDecimal credit = BigDecimal.ZERO;
        assertThrows(OutOfAmountRangeException.class, () -> new CreditManager(credit));
    }

    @Test
    public void constructorTest_setCredit_over() {
        BigDecimal credit = BigDecimal.valueOf(101);
        assertThrows(OutOfAmountRangeException.class, () -> new CreditManager(credit));
    }

    @Test
    public void constructorTest_noArgs() {
        CreditManager actual = new CreditManager();
        BigDecimal expectedCredit = BigDecimal.valueOf(100);
        assertEquals(expectedCredit, actual.getCredit());
        assertEquals(BigDecimal.ZERO, actual.getWager());
    }

    @Test
    public void betTest_min() {
        CreditManager actual = new CreditManager();
        BigDecimal wager = BigDecimal.ONE;
        actual.bet(wager);
        assertEquals(wager, actual.getWager());
        assertEquals(BigDecimal.valueOf(100), actual.getCredit());
    }

    @Test
    public void betTest_max() {
        CreditManager actual = new CreditManager();
        BigDecimal wager = BigDecimal.valueOf(100);
        actual.bet(wager);
        assertEquals(wager, actual.getWager());
        assertEquals(BigDecimal.valueOf(100), actual.getCredit());
    }

    @Test
    public void betTest_under() {
        CreditManager actual = new CreditManager();
        BigDecimal wager = BigDecimal.ZERO;
        assertThrows(OutOfAmountRangeException.class, () -> actual.bet(wager));
    }

    @Test
    public void betTest_over() {
        CreditManager actual = new CreditManager();
        BigDecimal wager = BigDecimal.valueOf(101);
        assertThrows(OutOfAmountRangeException.class, () -> actual.bet(wager));
    }

    @Test
    public void betTest_overCredit() {
        CreditManager actual = new CreditManager(new BigDecimal(50));
        BigDecimal wager = BigDecimal.valueOf(51);
        assertThrows(OutOfAmountRangeException.class, () -> actual.bet(wager));
    }

    @Test
    public void settleTest_Win_Nomal() {
        CreditManager actual = new CreditManager();
        BigDecimal wager = BigDecimal.ONE;
        actual.bet(wager);
        actual.settle(Decision.WIN_NORMAL);
        BigDecimal expected = BigDecimal.valueOf(101);
        assertEquals(expected, actual.getCredit());
        assertEquals(BigDecimal.ZERO, actual.getWager());
    }

    @Test
    public void settleTest_Win_BlackJack() {
        CreditManager actual = new CreditManager();
        BigDecimal wager = BigDecimal.valueOf(2);
        actual.bet(wager);
        actual.settle(Decision.WIN_BLACKJACK);
        BigDecimal expected = BigDecimal.valueOf(103);
        assertEquals(expected, actual.getCredit());
        assertEquals(BigDecimal.ZERO, actual.getWager());
    }

    @Test
    public void settleTest_Win_BlackJack_roundDown() {
        CreditManager actual = new CreditManager();
        BigDecimal wager = BigDecimal.ONE;
        actual.bet(wager);
        actual.settle(Decision.WIN_BLACKJACK);
        BigDecimal expected = BigDecimal.valueOf(101);
        assertEquals(expected, actual.getCredit());
        assertEquals(BigDecimal.ZERO, actual.getWager());
    }

    @Test
    public void settleTest_Lose() {
        CreditManager actual = new CreditManager();
        BigDecimal wager = BigDecimal.ONE;
        actual.bet(wager);
        actual.settle(Decision.LOSE);
        BigDecimal expected = BigDecimal.valueOf(99);
        assertEquals(expected, actual.getCredit());
        assertEquals(BigDecimal.ZERO, actual.getWager());
    }

    @Test
    public void initializeWagerTest() {
        CreditManager actual = new CreditManager();
        BigDecimal wager = BigDecimal.ONE;
        actual.bet(wager);
        actual.initializeWager();
        BigDecimal expected = BigDecimal.valueOf(100);
        assertEquals(expected, actual.getCredit());
        assertEquals(BigDecimal.ZERO, actual.getWager());
    }

}
