package exercise.fluxx;

/**
 * Created by jacky on 15/10/18.
 */
public class Draw extends Rule {
    public int amount;

    public Draw(int amount) {
        super();
        this.amount = amount;
        this.text = "Draw " + amount;
    }
}
