package FriedGoldenFlower;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Dome {
    public static void main(String[] args) {
        CardBox cardBox = new CardBox(new ArrayList<Card>());//创建卡包
        PlayerGroup playerGroup = new PlayerGroup(new ArrayList<Player>());//创建玩家组
        PlayerGroup playerTemporaryGroup = new PlayerGroup(new ArrayList<Player>());//创建玩家组
        MoneyPool prizePool = new MoneyPool();//创建奖金池
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] type = {"方", "梅", "红", "黑"};
        String[] symbol = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        int index = 0;
        for (String s : symbol) {
            for (String t : type) {
                Card card = new Card(index, t, s);
                cardBox.saveCard(card);
                index++;
            }
        }//创建卡牌
        cardBox.shuffleCard();//洗牌
        boolean start = true;
        int baseMoney = 5;//初始赌注
        int topMoney = 100;//单次最大赌注
        int playerMoney = 500;//玩家初始赌注

        while (start) {//游戏初始化
            System.out.println("请输入玩家人数(最多17人):");
            int playerNumber = scanner.nextInt();
            playerGroup.setPlayerNumber(playerNumber);
            if (playerNumber >= 2 && playerNumber <= 17) {
                for (int i = 0; i < playerNumber; i++) {
                    MoneyPool moneyPool = new MoneyPool((i + 1) + "号玩家", playerMoney);//创建赌注池
                    Player player = new Player(new ArrayList<Card>(), (i + 1) + "号玩家", (i + 1), moneyPool);//创建玩家
                    moneyPool.reduceMoney(baseMoney);//玩家支付初始赌注
                    player.setCard(cardBox, i);
                    player.setCard(cardBox, i + playerNumber);
                    player.setCard(cardBox, i + 2 * playerNumber);//发牌
                    player.sort();//手牌排序
                    playerGroup.setPlayer(player);//玩家归组
                    playerTemporaryGroup.setPlayer(player);//玩家归临时组
                }
                prizePool.addMoney(baseMoney * playerNumber);//初始赌注注入奖金池
                start = false;
            } else {
                System.out.println("玩家人数不符合要求，请重新确认");
            }
        }
        //循环结束条件，一半人输光
        while (playerGroup.getPlayers().size() > 1) {
            int numberPlayer = playerGroup.getPlayers().size();//剩余玩家人数
            GameManage gameManage = new GameManage(numberPlayer, prizePool, playerGroup, playerTemporaryGroup);//创建游戏管理
            gameManage.setBet(baseMoney, topMoney);//每次加注不少于初始赌注
            gameManage.operate();//一局游戏过程
            /*下一局*/
            cardBox.shuffleCard();//重新洗牌
            for (int i = 0; i < playerGroup.getPlayers().size(); i++) {
                Player player = playerGroup.getPlayers().get(i);
                player.getMoneyPool().reduceMoney(baseMoney);//玩家支付基础赌注
                player.clearCard();//清空玩家手牌
                player.setStateCard(false);//修改手牌状态为未查看
                player.setCard(cardBox, i);
                player.setCard(cardBox, i + playerGroup.getPlayerNumber());
                player.setCard(cardBox, i + 2 * playerGroup.getPlayerNumber());
                player.sort();//玩家手牌排序
            }
            prizePool.setMoney(baseMoney * playerGroup.getPlayers().size());//初始赌注注入奖金池
        }
        System.out.println("游戏结束，欢迎再次使用");

    }
}
