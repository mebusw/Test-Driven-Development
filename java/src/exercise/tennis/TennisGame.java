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
    private String display[] = {"love", "fifteen", "thirty", "forty"};

    public String status() {
        if (playerOneScore >= 4) {
            if (playerOneScore == playerTwoScore) {
                return "Deuce";
            }
            if (playerOneScore - playerTwoScore > 1) {
                return "Player One Win";
            }

            if (playerOneScore - playerTwoScore == 1) {
                return "Player One Advantage";
            }
            return "Player One Win";
        }
        if(playerTwoScore==playerOneScore){
            return display[playerTwoScore]+" All";
        }
        return display[playerOneScore] + " " + display[playerTwoScore];
    }

    public void playerOneScore(int times) {
        playerOneScore += times;
    }

    public void playerTwoScore(int times) {
        playerTwoScore += times;
    }
}
