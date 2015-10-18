package exercise.fluxx;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacky on 15/10/18.
 */
public class Player {
    public String name;
    public List<Card> hands;

    public Player(String name) {
        this.name = name;
        this.hands = new ArrayList<>();
    }
}
