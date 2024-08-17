package blackjack.ui;

import java.util.Scanner;

import blackjack.application.GameDto;
import blackjack.presentation.BlackJackController;

public class CharacterUserInterface {
    private BlackJackController controller;

    public CharacterUserInterface(BlackJackController controller) {
        this.controller = controller;
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

            if (status.equals("DEALER_TURN")) {
                status = dealerTurn();
            }

            if (status.equals("MATCH")) {
                decide();
            }
        }
        System.out.println("ブラックジャックを終了します。");
        scanner.close();
    }

    private boolean playGame(Scanner scanner) {
        System.out.println("ブラックジャックをしますか？");
        System.out.println("1: はい　2: いいえ");
        if (!scanner.hasNextInt()) {
            return false;
        }
        if (scanner.nextInt() != 1) {
            return false;
        }
        return true;
    }

    private boolean bet(Scanner scanner) {
        System.out.printf("あなたの現在の所持チップは%d枚です。%n", controller.getCredit());
        System.out.println("賭けるチップの枚数を入力してください。");
        if (!scanner.hasNextInt()) {
            return false;
        }
        int wager = scanner.nextInt();
        controller.bet(wager);
        System.out.printf("賭けチップ枚数：%s枚%n", wager);
        System.out.println();
        sleepOneSecond();
        return true;
    }

    private void deal() {
        controller.deal();
        GameDto gameDto = controller.getGameDto();
        System.out.println("カードを配ります。");
        System.out.println();
        sleepOneSecond();

        System.out.print("あなたのカード：");
        gameDto.playerHand().stream().forEach(card -> System.out.printf("[%s] ", card));
        System.out.println();
        System.out.printf("得点：%d点%n", gameDto.playerScore());
        System.out.println();
        sleepOneSecond();

        System.out.print("ディーラーのカード：");
        System.out.printf("[%s] ", gameDto.dealerHand().get(0));
        System.out.println("[??]");
        System.out.println("得点：??点");
        System.out.println();
        System.out.println();
        sleepOneSecond();
    }

    private String playerTurn(Scanner scanner) {
        GameDto gameDto;
        String status = "PLAYER_TURN";
        while (status.equals("PLAYER_TURN")) {
            System.out.println("アクションを選択してください。");
            System.out.println("1: カードを引く　2: ターンを終了する");

            if (!scanner.hasNextInt()) {
                System.out.println("入力が正しくありません。");
                continue;
            }

            int actionNumber = scanner.nextInt();
            if (actionNumber != 1 && actionNumber != 2) {
                System.out.println("選択肢の範囲内で入力してください。");
                continue;
            }

            if (actionNumber == 2) {
                gameDto = controller.stand();
                status = gameDto.status();
                System.out.println("プレイヤーのターンを終了します。");
                System.out.println();
                sleepOneSecond();
                break;
            }

            gameDto = controller.hit();
            status = gameDto.status();

            System.out.println("カードが1枚追加されました。");
            sleepOneSecond();
            System.out.print("あなたのカード：");
            gameDto.playerHand().stream().forEach(card -> System.out.printf("[%s] ", card));
            System.out.println();
            System.out.printf("得点：%d点%n", gameDto.playerScore());
            System.out.println();
            sleepOneSecond();
        }
        return status;
    }

    private String dealerTurn() {
        GameDto gameDto = controller.dealerTurn();
        System.out.println("ディーラーのターンです。");
        if (gameDto.dealerHand().size() > 2) {
            System.out.println("ディーラーがカードを追加します。");
        }
        System.out.print("ディーラーのカード：");
        gameDto.dealerHand().stream().forEach(card -> System.out.printf("[%s] ", card));
        System.out.println();
        System.out.printf("得点：%d点%n", gameDto.dealerScore());
        System.out.println();
        sleepOneSecond();
        return gameDto.status();
    }

    private void decide() {
        String decision = controller.decide();

        if ("WIN_BLACKJACK".equals(decision)) {
            System.out.println("プレイヤーの勝ちです。");
            System.out.println("プレイヤーはブラックジャックです。");
        } else if ("WIN_NORMAL".equals(decision)) {
            System.out.println("プレイヤーの勝ちです。");
        } else if ("LOSE".equals(decision)) {
            System.out.println("プレイヤーの負けです。");
        } else {
            System.out.println("引き分けです。");
        }
        System.out.println();
        sleepOneSecond();
        System.out.printf("あなたの所持チップは%d枚になりました。%n", controller.getCredit());
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
