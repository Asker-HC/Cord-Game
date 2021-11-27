package FriedGoldenFlower;

public class MoneyPool {
    private String name;
    private int money;

    public MoneyPool() {
    }

    public MoneyPool(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void reduceMoney(int money) {
        this.money -= money;
    }

    public void clear() {
        this.money = 0;
    }

    @Override
    public String toString() {
        return "MoneyPool{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
