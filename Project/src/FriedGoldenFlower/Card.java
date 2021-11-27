package FriedGoldenFlower;

import java.util.Objects;

public class Card {
    private int num;
    private String type;
    private String symbol;

    public Card() {
    }

    public Card(int num, String type, String symbol) {
        this.num = num;
        this.type = type;
        this.symbol = symbol;
    }

    public int getNum() {
        return num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Card{" +
                "num=" + num +
                ", type='" + type + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(type, card.type) &&
                Objects.equals(symbol, card.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, symbol);
    }
}
