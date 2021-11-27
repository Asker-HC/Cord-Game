package FriedGoldenFlower;

import java.util.ArrayList;

public class PoolGroup {
    private ArrayList<MoneyPool> moneyPools;

    public PoolGroup() {
    }

    public PoolGroup(ArrayList<MoneyPool> moneyPools) {
        this.moneyPools = moneyPools;
    }

    public ArrayList<MoneyPool> getMoneyPools() {
        return moneyPools;
    }

    public void setMoneyPools(ArrayList<MoneyPool> moneyPools) {
        this.moneyPools = moneyPools;
    }

    public void setMoneyPool(MoneyPool moneyPool) {
        moneyPools.add(moneyPool);
    }

    @Override
    public String toString() {
        return "PoolGroup{" +
                "moneyPools=" + moneyPools +
                '}';
    }
}
