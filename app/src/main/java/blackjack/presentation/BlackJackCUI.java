package blackjack.presentation;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Scanner;

import blackjack.application.BlackJackService;
import blackjack.application.GameDto;
import blackjack.domain.OutOfAmountRangeException;

public class BlackJackCUI {
    private BlackJackService service;
    private ResourceBundle massages = ResourceBundle.getBundle("massages");

    public BlackJackCUI(BlackJackService service) {
        this.service = service;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String status;
        while (true) {
            if (!playGame(scanner)) {
                break;
            }
            if (!bet(scanner)) {
                break;
            }
            deal();
            status = playerTurn(scanner);
            if (status.equals("PLAYER_TURN")) {
                break;
            }

            if (status.equals("DEALER_TURN")) {
                status = dealerTurn();
            }

            if (status.equals("MATCH")) {
                decide();
            }
        }
        System.out.println(massages.getString("end"));
        scanner.close();
    }

    private boolean playGame(Scanner scanner) {
        System.out.println(massages.getString("select_start"));
        System.out.println(massages.getString("option_yes_no"));
        if (!scanner.hasNextInt()) {
            return false;
        }
        if (scanner.nextInt() != 1) {
            return false;
        }
        return true;
    }

    private boolean bet(Scanner scanner) {
        BigDecimal credit = service.getCredit();
        System.out.println(MessageFormat.format(massages.getString("current_credit"), credit.toString()));
        if (credit.compareTo(BigDecimal.ONE) < 0) {
            System.out.println(massages.getString("error_no_credit"));
            return false;
        }
        System.out.println(massages.getString("place_bet"));
        if (!scanner.hasNextInt()) {
            System.out.println(massages.getString("error_invalid"));
            return false;
        }
        int wager = scanner.nextInt();
        try {
            service.bet(BigDecimal.valueOf(wager));
        } catch(OutOfAmountRangeException e) {
            System.out.println(e.getMessage());
            return false;
        }
        
        System.out.println(MessageFormat.format(massages.getString("wager"), wager));
        System.out.println();
        sleepOneSecond();
        return true;
    }

    private void deal() {
        GameDto gameDto = service.deal();
        System.out.println(massages.getString("deal_cards"));
        System.out.println();
        sleepOneSecond();

        System.out.print(massages.getString("player_cards"));
        gameDto.playerHand().stream().forEach(card -> System.out.printf("[%s] ", card));
        System.out.println();
        System.out.println(MessageFormat.format(massages.getString("score"), gameDto.playerScore()));
        System.out.println();
        sleepOneSecond();

        System.out.print(massages.getString("dealer_cards"));
        System.out.printf("[%s] ", gameDto.dealerHand().get(0));
        System.out.println("[??]");
        System.out.println(MessageFormat.format(massages.getString("score"), "??"));
        System.out.println();
        sleepOneSecond();
    }

    private String playerTurn(Scanner scanner) {
        GameDto gameDto;
        String status = "PLAYER_TURN";
        while (status.equals("PLAYER_TURN")) {
            System.out.println(massages.getString("select_player_action"));
            System.out.println(massages.getString("option_player_action"));

            if (!scanner.hasNextInt()) {
                System.out.println(massages.getString("error_invalid"));
                break;
            }

            int actionNumber = scanner.nextInt();
            if (actionNumber != 1 && actionNumber != 2) {
                System.out.println(massages.getString("error_out_of_range"));
                break;
            }

            if (actionNumber == 2) {
                gameDto = service.stand();
                status = gameDto.status();
                System.out.println(massages.getString("player_turn_end"));
                System.out.println();
                sleepOneSecond();
                break;
            }

            gameDto = service.hit();
            status = gameDto.status();

            System.out.println(massages.getString("add_player_card"));
            sleepOneSecond();
            System.out.print(massages.getString("player_cards"));
            gameDto.playerHand().stream().forEach(card -> System.out.printf("[%s] ", card));
            System.out.println();
            System.out.println(MessageFormat.format(massages.getString("score"), gameDto.playerScore()));
            System.out.println();
            sleepOneSecond();
        }
        return status;
    }

    private String dealerTurn() {
        GameDto gameDto = service.dealerTurn();
        System.out.println(massages.getString("dealer_turn_start"));
        sleepOneSecond();
        if (gameDto.dealerHand().size() > 2) {
            System.out.println(massages.getString("add_dealer_card"));
        }
        System.out.print(massages.getString("dealer_cards"));
        gameDto.dealerHand().stream().forEach(card -> System.out.printf("[%s] ", card));
        System.out.println();
        System.out.println(MessageFormat.format(massages.getString("score"), gameDto.dealerScore()));
        sleepOneSecond();
        return gameDto.status();
    }

    private void decide() {
        String decision = service.decide();

        if ("WIN_BLACKJACK".equals(decision)) {
            System.out.println(massages.getString("decision_win"));
            System.out.println(massages.getString("decision_blackjack"));
        } else if ("WIN_NORMAL".equals(decision)) {
            System.out.println(massages.getString("decision_win"));
        } else if ("LOSE".equals(decision)) {
            System.out.println(massages.getString("decision_lose"));
        } else {
            System.out.println(massages.getString("decision_tie"));
        }
        System.out.println();
        sleepOneSecond();
        System.out.println(MessageFormat.format(massages.getString("current_credit"), service.getCredit().toString()));
        System.out.println();
        sleepOneSecond();
    }

    private void sleepOneSecond() {
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
    }

}
