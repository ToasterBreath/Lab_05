import java.util.Random;

public class RandomStrategy {
    Random rand = new Random();

    public String getMove(String playerMove) {
        int randInt = rand.nextInt(3) + 1;

        String computerMove = "";
        switch (randInt) {
            case 1:
                computerMove = "P";
                break;
            case 2:
                computerMove = "S";
                break;
            case 3:
                computerMove = "R";
                break;
            default:
                computerMove = "X";
                break;
        }
        return computerMove;
    }
}
