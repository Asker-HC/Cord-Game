package FriedGoldenFlower;

public class Arena {
    private int grade1;//级别
    private int grade2;
    private Player player1;//玩家
    private Player player2;

    public Arena() {
    }

    public int getGrade1() {
        return grade1;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer(Player player1, Player player2) {//写入玩家
        this.player1 = player1;
        this.player2 = player2;
    }

    public void setGrade1(int grade1) {
        this.grade1 = grade1;
    }

    public int getGrade2() {
        return grade2;
    }

    public void setGrade2(int grade2) {
        this.grade2 = grade2;
    }

    /*两个玩家的牌比较大小

     */
    public Player compare() {//玩家PK
        this.rating();//手牌定级
        Card card11 = this.player1.getPalyer().get(0);
        Card card12 = this.player1.getPalyer().get(1);
        Card card13 = this.player1.getPalyer().get(2);
        Card card21 = this.player2.getPalyer().get(0);
        Card card22 = this.player2.getPalyer().get(1);
        Card card23 = this.player2.getPalyer().get(2);
        //同级别比较
        if (this.grade1 == this.grade2) {
            //非同级别1比较
            if (this.grade1 != 1) {
                if (card13.getSymbol().equals(card23)) {
                    if (card12.getSymbol().equals(card22.getSymbol())) {
                        if (card11.getSymbol().equals(card21.getSymbol())) {
                            if (card13.getNum() > card23.getNum()) {
                                System.out.println(this.player1.getName() + "获胜");
                                return player2;
                            } else {
                                System.out.println(this.player2.getName() + "获胜");
                                return player1;
                            }
                        } else if (card11.getNum() > card21.getNum()) {
                            System.out.println(this.player1.getName() + "获胜");
                            return player2;
                        } else {
                            System.out.println(this.player2.getName() + "获胜");
                            return player1;
                        }
                    } else if (card12.getNum() > card22.getNum()) {
                        System.out.println(this.player1.getName() + "获胜");
                        return player2;
                    } else {
                        System.out.println(this.player2.getName() + "获胜");
                        return player1;
                    }
                } else if (card13.getNum() > card23.getNum()) {
                    System.out.println(this.player1.getName() + "获胜");
                    return player2;
                } else {
                    System.out.println(this.player2.getName() + "获胜");
                    return player1;
                }
                //同级别1比较
            } else {
                Card s1 = card11.getSymbol().equals(card12.getSymbol()) ? card11 : card13;
                Card s2 = card21.getSymbol().equals(card22.getSymbol()) ? card21 : card23;
                int[] other = new int[3];
                if (s1.getSymbol().equals(s2.getSymbol())) {
                    if (card12.getSymbol().equals(s1.getSymbol())) {
                        other[1] = card13.getNum();
                    } else {
                        other[1] = card12.getNum();
                    }
                    if (card22.getSymbol().equals(s2.getSymbol())) {
                        other[2] = card23.getNum();
                    } else {
                        other[2] = card22.getNum();
                    }
                    if (other[1] > other[2]) {
                        System.out.println(this.player1.getName() + "获胜");
                        return player2;
                    } else {
                        System.out.println(this.player2.getName() + "获胜");
                        return player1;
                    }
                } else {
                    if (s1.getNum() > s2.getNum()) {
                        System.out.println(this.player1.getName() + "获胜");
                        return player2;
                    } else {
                        System.out.println(this.player2.getName() + "获胜");
                        return player1;
                    }
                }
            }
            //不同级别比较
        } else if (this.grade1 > this.grade2) {
            System.out.println(this.player1.getName() + "获胜");
            return player2;
        } else {
            System.out.println(this.player2.getName() + "获胜");
            return player1;
        }
    }

    public void rating() {//手牌定级
        Card card11 = this.player1.getPalyer().get(0);
        Card card12 = this.player1.getPalyer().get(1);
        Card card13 = this.player1.getPalyer().get(2);
        Card card21 = this.player2.getPalyer().get(0);
        Card card22 = this.player2.getPalyer().get(1);
        Card card23 = this.player2.getPalyer().get(2);
        /*级别
        5级：豹子   4级：金花顺  3级：金花   2级：顺    1级：对
        */
        /*玩家1*/
        if (card11.getSymbol().equals(card12.getSymbol()) && card11.getSymbol().equals(card13.getSymbol())) {
            this.grade1 = 5;//三张符号相同
        } else if (card11.getSymbol().equals(card12.getSymbol()) || card12.getSymbol().equals(card13.getSymbol()) || card11.getSymbol().equals(card13.getSymbol())) {
            this.grade1 = 1;//仅有两张符号相同
        } else {//三张不同符号
            //三张同花色
            if (card11.getType().equals(card12.getType()) && card11.getType().equals(card13.getType())) {
                //三张成顺
                if (card13.getNum() - card12.getNum() == 4 && card12.getNum() - card11.getNum() == 4) {
                    this.grade1 = 4;
                } else {
                    this.grade1 = 3;
                }
                //三张不同花色
            } else {
                //三张成顺
                String symbol = "2345678910JQKA";
                if (card12.getSymbol().charAt(0) == symbol.charAt(card13.getNum() / 4 - 1) && card12.getSymbol().charAt(0) == symbol.charAt(card11.getNum() / 4 + 1)) {
                    this.grade1 = 2;
                    //三张不成顺
                } else {
                    this.grade1 = 0;
                }
            }
        }
        /*玩家2*/
        if (card21.getSymbol().equals(card22.getSymbol()) && card21.getSymbol().equals(card23.getSymbol())) {
            this.grade2 = 5;//三张符号相同
        } else if (card21.getSymbol().equals(card22.getSymbol()) || card22.getSymbol().equals(card23.getSymbol()) || card21.getSymbol().equals(card23.getSymbol())) {
            this.grade2 = 1;//仅有两张符号相同
        } else {//三张不同符号
            //三张同花色
            if (card21.getType().equals(card22.getType()) && card21.getType().equals(card23.getType())) {
                //三张成顺
                if (card23.getNum() - card22.getNum() == 4 && card22.getNum() - card21.getNum() == 4) {
                    this.grade2 = 4;
                } else {
                    this.grade2 = 3;
                }
                //三张不同花色
            } else {
                //三张成顺
                String symbol = "2345678910JQKA";
                if (card22.getSymbol().charAt(0) == symbol.charAt(card23.getNum() / 4 - 1) && card22.getSymbol().charAt(0) == symbol.charAt(card21.getNum() / 4 + 1)) {
                    this.grade2 = 2;
                    //三张不成顺
                } else {
                    this.grade2 = 0;
                }
            }
        }
    }

}
