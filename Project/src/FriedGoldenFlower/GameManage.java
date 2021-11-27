package FriedGoldenFlower;

import java.util.Scanner;

public class GameManage {
    private int currentPlayer;
    private int numberPlayer;
    private boolean end;
    private boolean betDouble;
    private int minBet;
    private int maxBet;
    private Player loser;

    private MoneyPool prizePool;
    private PlayerGroup playerGroup;
    private PlayerGroup playerTemporaryGroup;

    Scanner scanner = new Scanner(System.in);

    public void operate() {
        while (playerTemporaryGroup.getPlayers().size() > 1) {//决出这一局最后一个玩家
            showWindow();
            boolean judge = judgeGame();
            while (!end && !judge) {
                System.out.println("请" + playerTemporaryGroup.getPlayers().get(this.currentPlayer).getName() + "输入操作码:");
                String str = scanner.next();
                switch (str) {//该玩家操作结束
                    case "1":
                        System.out.println("请输入加注金额");
                        int money = scanner.nextInt();
                        addMoney(money);
                        break;
                    case "2":
                        otherCompare();
                        break;
                    case "3":
                        showHoldCard();
                        break;
                    case "4":
                        disCardHand();
                        break;
                    case "0":
                        emptyMoney();
                        break;
                    default:
                        System.out.println("玩家输入有误，请重新输入");
                }
            }
            end = false;
            orderPlayer();
        }
        endGame();
        System.out.println("本局游戏已结束，即将开启下一局...");
    }

    //玩家操作提示
    private void showWindow() {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("******欢迎" + playerTemporaryGroup.getPlayers().get(this.currentPlayer).getName() + "******");
        showState();
        System.out.println("[1] 加注  过");
        System.out.println("[2] 加注  PK");
        System.out.println("[3] 查看手牌");
        System.out.println("[4] 丢掉手牌");
        System.out.println("[0] 梭哈");
    }

    //状态显示
    private void showState() {
        //显示存活玩家
        System.out.print("存活玩家:");
        for (Player player : playerTemporaryGroup.getPlayers()) {
            System.out.print(player.getName() + " ");
        }
        System.out.println();
        //显示奖金池
        System.out.println("奖金池内:" + prizePool.getMoney());
        //显示自己赌注
        System.out.println("个人赌注:" + playerTemporaryGroup.getPlayers().get(this.currentPlayer).getMoneyPool().getMoney());
        if (!playerTemporaryGroup.getPlayers().get(this.currentPlayer).isStateCard()) {
            System.out.println("手牌状态:未查看");
        } else {
            System.out.println("手牌状态:已查看");
        }
        showBetIterval();//显示个人加注区间
    }

    //显示个人加注区间
    private int showBetIterval() {
        if (this.betDouble) {
            if (playerTemporaryGroup.getPlayers().get(this.currentPlayer).isStateCard()) {//当前玩家已看牌
                System.out.println("加注区间:[" + this.minBet + "," + this.maxBet + "]");
                return this.minBet;
            } else {
                System.out.println("加注区间:[" + (this.minBet / 2 + 1) + "," + this.maxBet / 2 + "]");
                return this.minBet / 2 + 1;
            }
        } else {
            if (playerTemporaryGroup.getPlayers().get(this.currentPlayer).isStateCard()) {//当前玩家已看牌
                System.out.println("加注区间:[" + 2 * this.minBet + "," + this.maxBet + "]");
                return 2 * this.minBet;
            } else {
                System.out.println("加注区间:[" + this.minBet + "," + this.maxBet / 2 + "]");
                return this.minBet;
            }
        }
    }

    //显示手牌
    private void showHoldCard() {
        playerTemporaryGroup.getPlayers().get(this.currentPlayer).setStateCard(true);
        playerTemporaryGroup.getPlayers().get(this.currentPlayer).showCard();
        scanner.nextLine();
        //playerTemporaryGroup.getPlayers().get(this.currentPlayer).controlFrame(false);
        System.out.println("手牌状态:已查看");
        showBetIterval();
    }

    //玩家加注
    private void addMoney(int money) {
        if (this.betDouble) {//前玩家已看牌
            if (playerTemporaryGroup.getPlayers().get(this.currentPlayer).isStateCard()) {//当前玩家已看牌
                if (money >= this.minBet && money <= this.maxBet) {
                    //加注
                    this.minBet = money;
                    this.prizePool.addMoney(this.minBet);
                    playerTemporaryGroup.getPlayers().get(this.currentPlayer).getMoneyPool().reduceMoney(this.minBet);
                    System.out.println("加注" + money + "成功");
                    this.end = true;
                } else {
                    System.out.println("加注金额应不少于" + this.minBet + "不高于" + this.maxBet);
                }
            } else {//当前玩家未看牌
                if (money % 2 == 0) {//变偶为奇
                    money--;
                }
                if (money >= (this.minBet / 2) && money <= this.maxBet / 2) {
                    //加注
                    this.minBet = money;
                    this.prizePool.addMoney(this.minBet);
                    playerTemporaryGroup.getPlayers().get(this.currentPlayer).getMoneyPool().reduceMoney(this.minBet);
                    System.out.println("加注" + money + "成功");
                    this.end = true;
                } else {
                    System.out.println("加注金额应不少于" + (this.minBet / 2) + "不高于" + this.maxBet / 2);
                }
            }
        } else {//前玩家未看牌
            if (playerTemporaryGroup.getPlayers().get(this.currentPlayer).isStateCard()) {//当前玩家已看牌
                if (money >= 2 * this.minBet && money <= this.maxBet) {
                    //加注
                    this.minBet = money;
                    this.prizePool.addMoney(this.minBet);
                    playerTemporaryGroup.getPlayers().get(this.currentPlayer).getMoneyPool().reduceMoney(this.minBet);
                    System.out.println("加注" + money + "成功");
                    this.end = true;
                } else {
                    System.out.println("加注金额应不少于" + 2 * this.minBet + "不高于" + this.maxBet);
                }
            } else {//当前玩家未看牌
                if (money >= this.minBet && money <= this.maxBet / 2) {
                    //加注
                    this.minBet = money;
                    this.prizePool.addMoney(this.minBet);
                    playerTemporaryGroup.getPlayers().get(this.currentPlayer).getMoneyPool().reduceMoney(this.minBet);
                    System.out.println("加注" + money + "成功");
                    this.end = true;
                } else {
                    System.out.println("加注金额应不少于" + this.minBet + "不高于" + this.maxBet / 2);
                }
            }
        }
        betDouble = playerTemporaryGroup.getPlayers().get(this.currentPlayer).isStateCard();//获取前玩家的看牌状态
    }

    //玩家PK加注
    private void loseMoney() {
        if (this.betDouble) {
            if (playerTemporaryGroup.getPlayers().get(this.currentPlayer).isStateCard()) {//当前玩家已看牌
                addMoney(this.minBet);
            } else {
                addMoney(this.minBet / 2 + 1);
            }
        } else {
            if (playerTemporaryGroup.getPlayers().get(this.currentPlayer).isStateCard()) {//当前玩家已看牌
                addMoney(2 * this.minBet);
            } else {
                addMoney(this.minBet);
            }
        }
    }

    //玩家PK
    private void otherCompare() {
        Arena arena = new Arena();//创建竞技场
        System.out.println("请输入你要PK的玩家序号:");
        int playerNum = scanner.nextInt();
        if (playerNum == playerTemporaryGroup.getPlayers().get(this.currentPlayer).getNum()) {
            System.out.println("玩家不能与自己PK");
            return;
        } else {
            for (int i = 0; i < playerTemporaryGroup.getPlayers().size(); i++) {
                Player player = playerTemporaryGroup.getPlayers().get(i);
                if (playerNum == player.getNum()) {
                    loseMoney();
                    arena.setPlayer(player, playerTemporaryGroup.getPlayers().get(this.currentPlayer));
                    this.loser = arena.compare();
                    betDouble = playerTemporaryGroup.getPlayers().get(this.currentPlayer).isStateCard();//获取前玩家的看牌状态
                    playerTemporaryGroup.getPlayers().remove(this.loser);
                }
                if (i == playerTemporaryGroup.getPlayers().size() - 1 && playerTemporaryGroup.getPlayers().size() == this.numberPlayer) {
                    System.out.println("未查找到该玩家");
                    this.end = false;
                    return;
                }
            }
            this.end = true;
        }
    }

    //玩家丢弃手牌
    private void disCardHand() {
        betDouble = playerTemporaryGroup.getPlayers().get(this.currentPlayer).isStateCard();//获取前玩家的看牌状态
        this.loser = playerTemporaryGroup.getPlayers().get(this.currentPlayer);
        System.out.println(playerTemporaryGroup.getPlayers().get(this.currentPlayer).getName() + "已成功退出该局");
        playerTemporaryGroup.getPlayers().remove(this.currentPlayer);
        this.end = true;
    }

    //玩家孤注一掷
    private void emptyMoney() {
        System.out.println("[0] 梭哈");
    }

    //本局游戏结束
    private void endGame() {
        Player winner = this.playerTemporaryGroup.getPlayers().get(0);
        winner.getMoneyPool().addMoney(this.prizePool.getMoney());//胜利者获取奖池奖金
        System.out.println("本局游戏结束");
        System.out.println("获胜者为:" + winner.getName() + "本局获得奖金为:" + this.prizePool.getMoney());
        this.playerTemporaryGroup.getPlayers().clear();
        System.out.println("本局游戏结算:");
        for (Player player : this.playerGroup.getPlayers()) {//进行各玩家清算，并确定下一局玩家
            System.out.print(player.getName() + ",剩余赌注:" + player.getMoneyPool().getMoney());
            if (player.getMoneyPool().getMoney() > this.minBet + this.maxBet) {
                System.out.println("进入下一局");
                this.playerTemporaryGroup.getPlayers().add(player);
            } else {
                System.out.println("已被淘汰");
            }
        }
        this.playerGroup.getPlayers().clear();
        for (Player player : this.playerTemporaryGroup.getPlayers()) {//将符合要求玩家添加到下一局
            this.playerGroup.getPlayers().add(player);
        }
    }

    //判断玩家赌注是否能够继续本局游戏(个人赌注为负)
    private boolean judgeGame() {
        if (playerTemporaryGroup.getPlayers().get(this.currentPlayer).getMoneyPool().getMoney() < 0) {
            playerTemporaryGroup.getPlayers().remove(this.currentPlayer);
            System.out.println("赌注为负，已退出该局游戏");
            return true;
        }
        return false;
    }

    //当前玩家获取
    private void orderPlayer() {
        if (playerTemporaryGroup.getPlayers().size() == this.numberPlayer) {//没有玩家结束游戏
            if (this.currentPlayer + 1 < this.numberPlayer) {
                this.currentPlayer++;
            } else {
                this.currentPlayer = this.currentPlayer + 1 - this.numberPlayer;
            }
        } else {//有玩家结束游戏
            this.numberPlayer--;
            if (playerTemporaryGroup.getPlayers().size() == 1) {
                return;
            } else if (this.loser.getNum() > playerTemporaryGroup.getPlayers().get(this.currentPlayer).getNum()) {
                if (this.currentPlayer + 1 < this.numberPlayer) {
                    this.currentPlayer++;
                } else {
                    this.currentPlayer = this.currentPlayer + 1 - this.numberPlayer;
                }
            }
        }
    }

    public GameManage() {
    }

    public GameManage(int numberPlayer, MoneyPool prizePool, PlayerGroup playerGroup, PlayerGroup playerTemporaryGroup) {
        this.numberPlayer = numberPlayer;
        this.prizePool = prizePool;
        this.playerGroup = playerGroup;
        this.playerTemporaryGroup = playerTemporaryGroup;
    }

    public void setBet(int minBet, int maxBet) {
        this.minBet = minBet;
        this.maxBet = maxBet;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public PlayerGroup getPlayerGroup() {
        return playerGroup;
    }

    public void setPlayerGroup(PlayerGroup playerGroup) {
        this.playerGroup = playerGroup;
    }

}
