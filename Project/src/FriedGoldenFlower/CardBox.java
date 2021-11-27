package FriedGoldenFlower;

import java.util.ArrayList;
import java.util.Collections;

public class CardBox implements BoxOperate {
    private ArrayList<Card> cards;

    public CardBox() {
    }

    public CardBox(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    @Override
    public void saveCard(Card card) {
        this.cards.add(card);
    }

    @Override
    public Card distributeCard(int i) {
        return this.cards.get(i);
    }

    @Override
    public void shuffleCard() {
        Collections.shuffle(this.cards);
    }
}
