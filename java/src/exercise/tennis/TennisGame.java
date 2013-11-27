package exercise.tennis;

/**
 * Created with IntelliJ IDEA.
 * User: jacky
 * Date: 13-11-27
 * Time: 下午9:22
 * To change this template use File | Settings | File Templates.
 */
public class TennisGame {
    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private String translate[] = {"love", "fifteen", "thirty", "forty"};

    public String status() {
        if (canWin()) {
            if (isSameScore()) {
                return "Deuce";
            }
            if (hasWinner()) {
                return highestScoredPlayer() + " Win";
            }

            if (hasAdvantage()) {
                return highestScoredPlayer() + " Advantage";
            }

        }
        if (isSameScore()) {
            return translate[playerTwoScore] + " All";
        }
        return translate[playerOneScore] + " " + translate[playerTwoScore];
    }

    private boolean canWin() {
        return playerOneScore >= 4 || playerTwoScore >= 4;
    }

    private boolean isSameScore() {
        return playerTwoScore == playerOneScore;
    }

    private boolean hasAdvantage() {
        return scoreGap() == 1;
    }

    private boolean hasWinner() {
        return scoreGap() > 1;
    }

    private int scoreGap() {
        return Math.abs(playerOneScore - playerTwoScore);
    }

    private String highestScoredPlayer() {
        return playerOneScore > playerTwoScore ? "Player One" : "Player Two";
    }

    public void playerOneScore(int times) {
        playerOneScore += times;
    }

    public void playerTwoScore(int times) {
        playerTwoScore += times;
    }
}
