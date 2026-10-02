import java.util.Map;
import java.util.Random;

class RandomStrategy implements Strategy{
    Random rand = new Random();

    @Override
    public String getMove(String playerMove, String lastMove, Map tally) {
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
