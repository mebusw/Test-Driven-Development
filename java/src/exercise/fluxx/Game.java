package exercise.fluxx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jacky on 15/10/18.
 */
public class Game {
    public boolean isRunning = false;
    public List<Player> players;
    public List<Card> pool;
    public int currPlayerId = 0;
    public List<Card> table;

    public void addPlayers(Player... players) {
        this.players = new ArrayList(Arrays.asList(players));
    }

    public void addToPool(Card... pile) {
        this.pool = new ArrayList(Arrays.asList(pile));
    }

    public void start() {
        isRunning = true;
        for (Player player : players) {
            for (int i = 0; i < 3; i++) {
                player.hands.add(new Card());
            }
        }
        table = new ArrayList<>();
    }

    public void drawAction() {
        getCurrPlayer().hands.add(pool.remove(0));
    }

    private Player getCurrPlayer() {
        return players.get(currPlayerId);
    }

    public void playAction() {
        table.add(getCurrPlayer().hands.remove(0));
    }

    private void moveToNextPlayer() {
        currPlayerId = (currPlayerId + 1) % players.size();
    }

    public void tick() {
        drawAction();
        playAction();
        moveToNextPlayer();
    }

    public void putOnTable(Rule rule) {

    }
}
