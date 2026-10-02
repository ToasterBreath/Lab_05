import java.util.Map;

public interface Strategy {
    String getMove(String playerMove, String lastMove, Map tally);
}
