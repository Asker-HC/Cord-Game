package FriedGoldenFlower;

import java.util.ArrayList;

public class PlayerGroup {
    private ArrayList<Player> players;
    private int playerNumber;

    public PlayerGroup() {
    }

    public PlayerGroup(ArrayList<Player> players) {
        this.players = players;
    }

    public PlayerGroup(ArrayList<Player> players, int playerNumber) {
        this.players = players;
        this.playerNumber = playerNumber;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setPlayer(Player player) {
        this.players.add(player);
    }

    public void delete(Player player) {
        players.remove(player);
        this.playerNumber--;
    }

    @Override
    public String toString() {
        return "PlayerGroup{" +
                "players=" + players +
                '}';
    }
}
