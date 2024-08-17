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
        int expectedCardNumber = 0;
        GameStatus expectedStatus = GameStatus.PLAYER_TURN;
        assertEquals(expectedCardNumber, actual.getPlayerCards().size());
        assertEquals(expectedCardNumber, actual.getDealerCards().size());
        assertEquals(expectedStatus, actual.getStatus());

        System.out.println("constructorTest");
        System.out.println("Player手札");
        actual.getPlayerCards().stream().forEach(System.out::println);
        System.out.println("Dealer手札");
        actual.getDealerCards().stream().forEach(System.out::println);
        System.out.println();  
    }

    @Test
    public void betTest() {
        CreditManager actual = new CreditManager();
        CardPicker picker = new RandomCardPicker();
        Game game = new Game(actual, picker);
        BigDecimal expectedWager = BigDecimal.ONE;
        BigDecimal expectedCredit = BigDecimal.valueOf(100);
        GameStatus expectedStatus = GameStatus.PLAYER_TURN;
        game.bet(expectedWager);
        assertEquals(expectedWager, actual.getWager());
        assertEquals(expectedCredit, actual.getCredit());
        assertEquals(expectedStatus, game.getStatus());
    }

    @Test
    public void hitTest_bust() {
        CreditManager creditManger = new CreditManager();
        SpecificCardPicker picker = new SpecificCardPicker(new Card(Rank.ACE, Suit.SPADE));
        Game actual = new Game(creditManger, picker);
        actual.deal();
        picker.setCard(new Card(Rank.KING, Suit.CLUB));
        actual.hit();
        picker.setCard(new Card(Rank.KING, Suit.DIAMOND));
        actual.hit();
        int expectedCardNumber = 4;
        GameStatus expectedStatus = GameStatus.MATCH;
        assertEquals(expectedCardNumber, actual.getPlayerCards().size());
        assertEquals(expectedStatus, actual.getStatus());

        System.out.println("hitTest_bust");
        System.out.println("Player手札");
        actual.getPlayerCards().stream().forEach(System.out::println);
        System.out.println();
    }

    @Test
    public void hitTest_notBust() {
        CreditManager creditManger = new CreditManager();
        CardPicker picker = new SpecificCardPicker(new Card(Rank.ACE, Suit.SPADE));
        Game actual = new Game(creditManger, picker);
        actual.deal();
        actual.hit();
        int expectedCardNumber = 3;
        int expectedScore = 14;
        GameStatus expectedStatus = GameStatus.PLAYER_TURN;
        assertEquals(expectedCardNumber, actual.getPlayerCards().size());
        assertEquals(expectedStatus, actual.getStatus());
        assertEquals(expectedScore, actual.getPlayerScore());

        System.out.println("hitTest_notBust");
        System.out.println("Player手札");
        actual.getPlayerCards().stream().forEach(System.out::println);
        System.out.println();
    }

    @Test
    public void standTest_brackJack() {
        CreditManager creditManger = new CreditManager();
        CardPicker picker = new SpecificCardPicker(new Card(Rank.TEN, Suit.HEART));
        Game actual = new Game(creditManger, picker);
        actual.deal();
        actual.stand();
        int expectedCardNumber = 2;
        GameStatus expectedStatus = GameStatus.MATCH;
        assertEquals(expectedCardNumber, actual.getPlayerCards().size());
        assertEquals(expectedStatus, actual.getStatus());

        System.out.println("standTest_brackJack");
        System.out.println("Player手札");
        actual.getPlayerCards().stream().forEach(System.out::println);
        System.out.println();
    }

    @Test
    public void standTest_notBrackJack() {
        CreditManager creditManger = new CreditManager();
        CardPicker picker = new SpecificCardPicker(new Card(Rank.NINE, Suit.HEART));
        Game actual = new Game(creditManger, picker);
        actual.deal();
        actual.stand();
        int expectedCardNumber = 2;
        GameStatus expectedStatus = GameStatus.DEALER_TURN;
        assertEquals(expectedCardNumber, actual.getPlayerCards().size());
        assertEquals(expectedStatus, actual.getStatus());

        System.out.println("standTest_notBrackJack");
        System.out.println("Player手札");
        actual.getPlayerCards().stream().forEach(System.out::println);
        System.out.println();
    }

    @Test
    public void dealerPlayTest_under17() {
        CreditManager creditManger = new CreditManager();
        SpecificCardPicker picker = new SpecificCardPicker(new Card(Rank.NINE, Suit.HEART));
        Game actual = new Game(creditManger, picker);
        actual.deal();
        actual.stand();
        picker.setCard(new Card(Rank.FOUR, Suit.CLUB));
        actual.dealerPlay();
        int expectedCardNumber = 3;
        GameStatus expectedStatus = GameStatus.DEALER_TURN;
        assertEquals(expectedCardNumber, actual.getDealerCards().size());
        assertEquals(expectedStatus, actual.getStatus());

        System.out.println("dealerPlayTest_under17");
        System.out.println("Dealer手札");
        actual.getDealerCards().stream().forEach(System.out::println);
        System.out.println();
    }

    @Test
    public void dealerPlayTest_over17() {
        CreditManager creditManger = new CreditManager();
        SpecificCardPicker picker = new SpecificCardPicker(new Card(Rank.NINE, Suit.HEART));
        Game actual = new Game(creditManger, picker);
        actual.deal();
        actual.stand();
        picker.setCard(new Card(Rank.SIX, Suit.CLUB));
        actual.dealerPlay();
        actual.dealerPlay();
        int expectedCardNumber = 3;
        GameStatus expectedStatus = GameStatus.MATCH;
        assertEquals(expectedCardNumber, actual.getDealerCards().size());
        assertEquals(expectedStatus, actual.getStatus());

        System.out.println("dealerPlayTest_over17");
        System.out.println("Dealer手札");
        actual.getDealerCards().stream().forEach(System.out::println);
        System.out.println();
    }

    @Test
    public void dealerPlayTest_17() {
        CreditManager creditManger = new CreditManager();
        SpecificCardPicker picker = new SpecificCardPicker(new Card(Rank.ACE, Suit.SPADE));
        Game actual = new Game(creditManger, picker);
        actual.deal();
        actual.stand();
        picker.setCard(new Card(Rank.FIVE, Suit.CLUB));
        actual.dealerPlay();
        int expectedCardNumber = 3;
        int expectedScore = 17;
        GameStatus expectedStatus = GameStatus.DEALER_TURN;
        assertEquals(expectedCardNumber, actual.getDealerCards().size());
        assertEquals(expectedStatus, actual.getStatus());
        assertEquals(expectedScore, actual.getDealerScore());

        System.out.println("dealerPlayTest_17");
        System.out.println("Dealer手札");
        actual.getDealerCards().stream().forEach(System.out::println);
        System.out.println();
    }

    @Test
    public void decideTest_win_blackjack() {
        CreditManager creditManger = new CreditManager();
        SpecificCardPicker picker = new SpecificCardPicker(new Card(Rank.ACE, Suit.SPADE));
        Game game = new Game(creditManger, picker);
        game.hit();
        picker.setCard(new Card(Rank.KING, Suit.CLUB));
        game.hit();
        game.stand();
        picker.setCard(new Card(Rank.KING, Suit.HEART));
        game.dealerPlay();
        picker.setCard(new Card(Rank.NINE, Suit.HEART));
        game.dealerPlay();
        picker.setCard(new Card(Rank.TWO, Suit.SPADE));
        game.dealerPlay();
        Decision actual = game.decide();
        Decision expected = Decision.WIN_BLACKJACK;
        assertEquals(expected, actual);
    }

    @Test
    public void decideTest_win_dealer_bust() {
        CreditManager creditManger = new CreditManager();
        SpecificCardPicker picker = new SpecificCardPicker(new Card(Rank.SEVEN, Suit.SPADE));
        Game game = new Game(creditManger, picker);
        game.hit();
        picker.setCard(new Card(Rank.QUEEN, Suit.CLUB));
        game.hit();
        game.stand();
        picker.setCard(new Card(Rank.KING, Suit.HEART));
        game.dealerPlay();
        picker.setCard(new Card(Rank.SIX, Suit.HEART));
        game.dealerPlay();
        picker.setCard(new Card(Rank.SIX, Suit.SPADE));
        game.dealerPlay();
        Decision actual = game.decide();
        Decision expected = Decision.WIN_NORMAL;
        assertEquals(expected, actual);
    }

    @Test
    public void decideTest_win_score() {
        CreditManager creditManger = new CreditManager();
        SpecificCardPicker picker = new SpecificCardPicker(new Card(Rank.EIGHT, Suit.SPADE));
        Game game = new Game(creditManger, picker);
        game.hit();
        picker.setCard(new Card(Rank.QUEEN, Suit.CLUB));
        game.hit();
        game.stand();
        picker.setCard(new Card(Rank.KING, Suit.HEART));
        game.dealerPlay();
        picker.setCard(new Card(Rank.SEVEN, Suit.HEART));
        game.dealerPlay();
        Decision actual = game.decide();
        Decision expected = Decision.WIN_NORMAL;
        assertEquals(expected, actual);
    }

    @Test
    public void decideTest_tie_blackjack() {
        CreditManager creditManger = new CreditManager();
        SpecificCardPicker picker = new SpecificCardPicker(new Card(Rank.ACE, Suit.SPADE));
        Game game = new Game(creditManger, picker);
        game.hit();
        picker.setCard(new Card(Rank.KING, Suit.CLUB));
        game.hit();
        game.stand();
        picker.setCard(new Card(Rank.KING, Suit.DIAMOND));
        game.dealerPlay();
        picker.setCard(new Card(Rank.ACE, Suit.CLUB));
        game.dealerPlay();
        Decision actual = game.decide();
        Decision expected = Decision.TIE;

        assertEquals(expected, actual);
    }

    @Test
    public void decideTest_tie_score() {
        CreditManager creditManger = new CreditManager();
        SpecificCardPicker picker = new SpecificCardPicker(new Card(Rank.SEVEN, Suit.SPADE));
        Game game = new Game(creditManger, picker);
        game.hit();
        picker.setCard(new Card(Rank.QUEEN, Suit.CLUB));
        game.hit();
        game.stand();
        picker.setCard(new Card(Rank.KING, Suit.HEART));
        game.dealerPlay();
        picker.setCard(new Card(Rank.SEVEN, Suit.HEART));
        game.dealerPlay();
        Decision actual = game.decide();
        Decision expected = Decision.TIE;
        assertEquals(expected, actual);
    }

    @Test
    public void decideTest_lose_bust() {
        CreditManager creditManger = new CreditManager();
        SpecificCardPicker picker = new SpecificCardPicker(new Card(Rank.KING, Suit.SPADE));
        Game game = new Game(creditManger, picker);
        game.hit();
        picker.setCard(new Card(Rank.KING, Suit.CLUB));
        game.hit();
        picker.setCard(new Card(Rank.KING, Suit.DIAMOND));
        game.hit();
        Decision actual = game.decide();
        Decision expected = Decision.LOSE;
        assertEquals(expected, actual);
    }

    @Test
    public void decideTest_lose_score() {
        CreditManager creditManger = new CreditManager();
        SpecificCardPicker picker = new SpecificCardPicker(new Card(Rank.SEVEN, Suit.SPADE));
        Game game = new Game(creditManger, picker);
        game.hit();
        picker.setCard(new Card(Rank.QUEEN, Suit.CLUB));
        game.hit();
        game.stand();
        picker.setCard(new Card(Rank.KING, Suit.HEART));
        game.dealerPlay();
        picker.setCard(new Card(Rank.EIGHT, Suit.HEART));
        game.dealerPlay();
        Decision actual = game.decide();
        Decision expected = Decision.LOSE;
        assertEquals(expected, actual);
    }
}
