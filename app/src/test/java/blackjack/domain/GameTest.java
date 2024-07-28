package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    public void constructorTest() {
        CreditManager creditManger = new CreditManager();
        Game actual = new Game(creditManger);
        int expectedCardNumber = 2;
        assertEquals(expectedCardNumber, actual.getPlayerCards().size());
        assertEquals(expectedCardNumber, actual.getDealerCards().size());

        System.out.println("Player手札");
        actual.getPlayerCards().stream().forEach(System.out::println);
        System.out.println("Dealer手札");
        actual.getDealerCards().stream().forEach(System.out::println);   
    }

    @Test
    public void betTest() {
        CreditManager actual = new CreditManager();
        Game game = new Game(actual);
        BigDecimal expectedWager = new BigDecimal(1);
        BigDecimal expectedCredit = new BigDecimal(100).subtract(expectedWager);
        game.bet(expectedWager);
        assertEquals(expectedWager, actual.getWager());
        assertEquals(expectedCredit, actual.getCredit());
    }

    @Test
    public void hitTest() {
        CreditManager creditManger = new CreditManager();
        Game actual = new Game(creditManger);
        actual.hit();
        int expectedCardNumber = 3;
        assertEquals(expectedCardNumber, actual.getPlayerCards().size());

        System.out.println("Player手札");
        actual.getPlayerCards().stream().forEach(System.out::println);
    }



}
