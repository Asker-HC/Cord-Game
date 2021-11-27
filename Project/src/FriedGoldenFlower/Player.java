package FriedGoldenFlower;

import java.awt.*;
import java.util.ArrayList;

public class Player implements PlayerOperate {
    private ArrayList<Card> handCards;
    private boolean stateCard;
    private String name;
    private int num;
    private MoneyPool moneyPool;

    public Player() {
    }

    public Player(ArrayList<Card> handCards, String name, int num, MoneyPool moneyPool) {
        this.handCards = handCards;
        this.name = name;
        this.num = num;
        this.moneyPool = moneyPool;
    }

    public MoneyPool getMoneyPool() {
        return moneyPool;
    }

    public void setMoneyPool(MoneyPool moneyPool) {
        this.moneyPool = moneyPool;
    }

    public boolean isStateCard() {
        return stateCard;
    }

    public void setStateCard(boolean stateCard) {
        this.stateCard = stateCard;
    }

    public int getNum() {
        return num;
    }

    public void clearCard() {
        handCards.clear();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getPalyer() {
        return handCards;
    }

    public void setPalyer(ArrayList<Card> palyer) {
        this.handCards = palyer;
    }

    @Override
    public String toString() {
        return "Player{" +
                "handCards=" + handCards +
                ", stateCard=" + stateCard +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void setCard(CardBox cardBox, int i) {
        this.handCards.add(cardBox.distributeCard(i));
    }

    @Override
    public void showCard() {
        for (Card card : this.handCards) {
            System.out.print(card.getType() + card.getSymbol() + " ");
        }
        System.out.println();
    }

    @Override
    public void sort() {
        if (handCards.size() == 3) {
            Card card1 = new Card();
            Card card2 = new Card();
            Card card3 = new Card();
            if (handCards.get(0).getNum() < handCards.get(1).getNum()) {
                if (handCards.get(2).getNum() < handCards.get(0).getNum()) {
                    card1 = handCards.get(2);
                    card2 = handCards.get(0);
                    card3 = handCards.get(1);
                } else {
                    card1 = handCards.get(0);
                    if (handCards.get(2).getNum() < handCards.get(1).getNum()) {
                        card2 = handCards.get(2);
                        card3 = handCards.get(1);
                    } else {
                        card2 = handCards.get(1);
                        card3 = handCards.get(2);
                    }
                }
            } else {
                if (handCards.get(2).getNum() < handCards.get(1).getNum()) {
                    card1 = handCards.get(2);
                    card2 = handCards.get(1);
                    card3 = handCards.get(0);
                } else {
                    card1 = handCards.get(1);
                    if (handCards.get(2).getNum() < handCards.get(0).getNum()) {
                        card2 = handCards.get(2);
                        card3 = handCards.get(0);
                    } else {
                        card2 = handCards.get(0);
                        card3 = handCards.get(2);
                    }
                }
            }
            handCards.clear();
            handCards.add(card1);
            handCards.add(card2);
            handCards.add(card3);
        } else {
            System.out.println("手牌未满三张");
        }
    }
}
