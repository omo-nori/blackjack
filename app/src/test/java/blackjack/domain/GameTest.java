package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    public void constructorTest() {
        CreditManager creditManger = new CreditManager();
        CardPicker picker = new RandomCardPicker();
        Game actual = new Game(creditManger, picker);
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
        CardPicker picker = new RandomCardPicker();
        Game game = new Game(actual, picker);
        BigDecimal expectedWager = new BigDecimal(1);
        BigDecimal expectedCredit = new BigDecimal(100).subtract(expectedWager);
        game.bet(expectedWager);
        assertEquals(expectedWager, actual.getWager());
        assertEquals(expectedCredit, actual.getCredit());
    }

    @Test
    public void hitTest_bust() {
        CreditManager creditManger = new CreditManager();
        SpecificCardPicker picker = new SpecificCardPicker(new Card(Rank.ACE, Suit.SPADE));
        Game actual = new Game(creditManger, picker);
        picker.setCard(new Card(Rank.KING, Suit.CLUB));
        actual.hit();
        picker.setCard(new Card(Rank.KING, Suit.DIAMOND));
        actual.hit();
        int expectedCardNumber = 4;
        GameStatus expectedStatus = GameStatus.MATCH;
        assertEquals(expectedCardNumber, actual.getPlayerCards().size());
        assertEquals(expectedStatus, actual.getStatus());

        System.out.println("Player手札");
        actual.getPlayerCards().stream().forEach(System.out::println);
    }

    @Test
    public void hitTest_notBust() {
        CreditManager creditManger = new CreditManager();
        CardPicker picker = new SpecificCardPicker(new Card(Rank.ACE, Suit.SPADE));
        Game actual = new Game(creditManger, picker);
        actual.hit();
        int expectedCardNumber = 3;
        GameStatus expectedStatus = GameStatus.PLAYER_TURN;
        assertEquals(expectedCardNumber, actual.getPlayerCards().size());
        assertEquals(expectedStatus, actual.getStatus());

        System.out.println("Player手札");
        actual.getPlayerCards().stream().forEach(System.out::println);
    }

    @Test
    public void standTest_brackJack() {
        CreditManager creditManger = new CreditManager();
        CardPicker picker = new SpecificCardPicker(new Card(Rank.TEN, Suit.HEART));
        Game actual = new Game(creditManger, picker);
        actual.stand();
        int expectedCardNumber = 2;
        GameStatus expectedStatus = GameStatus.MATCH;
        assertEquals(expectedCardNumber, actual.getPlayerCards().size());
        assertEquals(expectedStatus, actual.getStatus());

        System.out.println("Player手札");
        actual.getPlayerCards().stream().forEach(System.out::println);
    }

    @Test
    public void standTest_notBrackJack() {
        CreditManager creditManger = new CreditManager();
        CardPicker picker = new SpecificCardPicker(new Card(Rank.NINE, Suit.HEART));
        Game actual = new Game(creditManger, picker);
        actual.stand();
        int expectedCardNumber = 2;
        GameStatus expectedStatus = GameStatus.DEALER_TURN;
        assertEquals(expectedCardNumber, actual.getPlayerCards().size());
        assertEquals(expectedStatus, actual.getStatus());

        System.out.println("Player手札");
        actual.getPlayerCards().stream().forEach(System.out::println);
    }

    @Test
    public void dealerPlayTest_under17() {
        CreditManager creditManger = new CreditManager();
        SpecificCardPicker picker = new SpecificCardPicker(new Card(Rank.NINE, Suit.HEART));
        Game actual = new Game(creditManger, picker);
        actual.stand();
        picker.setCard(new Card(Rank.FOUR, Suit.CLUB));
        actual.dealerPlay();
        int expectedCardNumber = 3;
        GameStatus expectedStatus = GameStatus.DEALER_TURN;
        assertEquals(expectedCardNumber, actual.getDealerCards().size());
        assertEquals(expectedStatus, actual.getStatus());

        System.out.println("Player手札");
        actual.getDealerCards().stream().forEach(System.out::println);
    }

    @Test
    public void dealerPlayTest_over17() {
        CreditManager creditManger = new CreditManager();
        SpecificCardPicker picker = new SpecificCardPicker(new Card(Rank.NINE, Suit.HEART));
        Game actual = new Game(creditManger, picker);
        actual.stand();
        picker.setCard(new Card(Rank.SIX, Suit.CLUB));
        actual.dealerPlay();
        actual.dealerPlay();
        int expectedCardNumber = 3;
        GameStatus expectedStatus = GameStatus.MATCH;
        assertEquals(expectedCardNumber, actual.getDealerCards().size());
        assertEquals(expectedStatus, actual.getStatus());

        System.out.println("Player手札");
        actual.getDealerCards().stream().forEach(System.out::println);
    }

    @Test
    public void dealerPlayTest_17() {
        CreditManager creditManger = new CreditManager();
        SpecificCardPicker picker = new SpecificCardPicker(new Card(Rank.ACE, Suit.SPADE));
        Game actual = new Game(creditManger, picker);
        actual.stand();
        picker.setCard(new Card(Rank.FIVE, Suit.CLUB));
        actual.dealerPlay();
        int expectedCardNumber = 3;
        GameStatus expectedStatus = GameStatus.DEALER_TURN;
        assertEquals(expectedCardNumber, actual.getDealerCards().size());
        assertEquals(expectedStatus, actual.getStatus());

        System.out.println("Player手札");
        actual.getDealerCards().stream().forEach(System.out::println);
    }
}
