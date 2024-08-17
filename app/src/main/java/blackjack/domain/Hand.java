package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class Hand {
    private List<Card> cards;
    private static final int INITIAL_NUMBER_OF_CARDS = 2;
    private static final int ACE_BONUS = 10;
    private static final int MAX_SCORE = 21;

    public Hand() {
        cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void addCard(Card card) {
        cards.add(Objects.requireNonNull(card));
    }

    public int score() {
        int score = cards.stream().map(card -> card.getPoints()).reduce(0, Integer::sum);
        if (hasAce() && score + ACE_BONUS <= MAX_SCORE) {
            score = score + ACE_BONUS;
        }
        return score;
    }

    public boolean isBust() {
        if (score() > MAX_SCORE) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBlackJack() {
        if (cards.size() > INITIAL_NUMBER_OF_CARDS) {
            return false;
        }

        if (score() != MAX_SCORE) {
            return false;
        }

        return true;
    }

    public boolean hasAce() {
        List<Rank> ranks = cards.stream().map(card -> card.getRank()).collect(Collectors.toList());
        if (ranks.contains(Rank.ACE)) {
            return true;
        } else {
            return false;
        }
    }
}
