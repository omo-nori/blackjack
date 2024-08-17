package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class DecisionTest {
    @Test
    public void WIN_NOMAL_calculateDividendsTest() {
        BigDecimal actual = Decision.WIN_NORMAL.calculateDividends(BigDecimal.valueOf(5));
        BigDecimal expected = BigDecimal.valueOf(5);
        assertEquals(expected, actual);
    }

    @Test
    public void WIN_BLACKJACK_calculateDividendsTest() {
        BigDecimal actual = Decision.WIN_BLACKJACK.calculateDividends(BigDecimal.valueOf(5));
        BigDecimal expected = BigDecimal.valueOf(7);
        assertEquals(expected, actual);
    }

    @Test
    public void LOSE_calculateDividendsTest() {
        BigDecimal actual = Decision.LOSE.calculateDividends(BigDecimal.valueOf(5));
        BigDecimal expected = BigDecimal.valueOf(-5);
        assertEquals(expected, actual);
    }

    @Test
    public void TIE_calculateDividendsTest() {
        BigDecimal actual = Decision.TIE.calculateDividends(BigDecimal.valueOf(5));
        BigDecimal expected = BigDecimal.ZERO;
        assertEquals(expected, actual);
    }
}
