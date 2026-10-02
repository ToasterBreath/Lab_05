import java.util.Map;

class CheatStrategy implements Strategy
{
    @Override
    public String getMove(String playerMove, String lastMove, Map tally)
    {
        String computerMove = "";
        switch (playerMove)
        {
            case "R":
                computerMove = "P";
                break;
            case "P":
                computerMove = "S";
                break;
            case "S":
                computerMove = "R";
                break;
            default:
                computerMove = "X";
                break;
        }
        return computerMove;
    }
}