import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;

public class RockPaperScissorsFrame extends JFrame {
    JPanel mainPnl, titlePnl, btnPnl, statsPnl, historyPnl, wrapperPnl;
    JLabel titleLbl, playerWinsLbl, computerWinsLbl, tiesLbl;
    JTextArea playerWinsTA, computerWinsTA, tiesTA, historyTA;
    ImageIcon rock, paper, scissors;
    JButton quitBtn, rockBtn, paperBtn, scissorsBtn;
    int playerWins = 0, computerWins = 0, ties = 0;
    JScrollPane scroller;
    String lastMove = null;

    Map<String, Integer> tally = new HashMap<>();

    strategy Strat = new strategy();
    /*
    Creates main frame.

    This forms the base of the gui.
    */
    public RockPaperScissorsFrame(){
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        add(mainPnl);

        tally.put("R", 0);
        tally.put("P", 0);
        tally.put("S", 0);

        createTitlePanel();
        createWrapperPanel();
        createHistoryPanel();
        setTitle("Rock Paper Scissors");
        setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().width * 0.75),(int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.75));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    /**
     * Creates title panel.
     *
     * Assigns it to north of main frame
     */
    public void createTitlePanel(){
        titlePnl = new JPanel();
        titleLbl = new JLabel("Rock Paper Scissors Game", JLabel.CENTER);
        titleLbl.setFont(new Font ("Times New Roman", Font.PLAIN,32));
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        titleLbl.setVerticalTextPosition(JLabel.TOP);
        titlePnl.add(titleLbl);
        mainPnl.add(titlePnl, BorderLayout.NORTH);
        titlePnl.setVisible(true);
    }
    /**
     * Creates wrapper panel.
     *
     * Wraps together button and stats panel.
     * Assigns it to the center of main frame.
     */
    public void createWrapperPanel(){
        wrapperPnl = new JPanel();
        wrapperPnl.setLayout(new BorderLayout());
        createButtonPanel();
        createStatsPanel();
        mainPnl.add(wrapperPnl, BorderLayout.CENTER);
        wrapperPnl.setVisible(true);
    }
    /**
     * Creates Button panel.
     *
     * This panel handles the main Rock Paper Scissor buttons along with the quit button.
     * Assigned to the south of the wrapper panel.
     */
    public void createButtonPanel(){
        btnPnl = new JPanel();
        btnPnl.setLayout(new GridLayout(2,3,20,20));
        quitBtn = new JButton("Quit");
        rockBtn = new JButton("Rock");
        paperBtn = new JButton("Paper");
        scissorsBtn = new JButton("Scissors");
        rock = new ImageIcon("src/rock.jpg");
        Image rockStandIn = rock.getImage();
        Image scaledRock = rockStandIn.getScaledInstance(100,100, Image.SCALE_SMOOTH);
        rock = new ImageIcon(scaledRock);
        paper = new ImageIcon("src/paper.jpg");
        Image paperStandIn = paper.getImage();
        Image scaledPaper = paperStandIn.getScaledInstance(100,100, Image.SCALE_SMOOTH);
        paper = new ImageIcon(scaledPaper);
        scissors = new ImageIcon("src/scissors.jpg");
        Image scissorsStandIn = scissors.getImage();
        Image scaledScissors = scissorsStandIn.getScaledInstance(100,100, Image.SCALE_SMOOTH);
        scissors = new ImageIcon(scaledScissors);
        rockBtn.setIcon(rock);
        scissorsBtn.setIcon(scissors);
        paperBtn.setIcon(paper);
        btnPnl.add(rockBtn);
        btnPnl.add(paperBtn);
        btnPnl.add(scissorsBtn);
        btnPnl.add(new JLabel(""));
        btnPnl.add(quitBtn);
        btnPnl.add(new JLabel(""));
        wrapperPnl.add(btnPnl, BorderLayout.SOUTH);
        btnPnl.setVisible(true);

        quitBtn.addActionListener((ActionEvent ae) ->{
            int response = JOptionPane.showConfirmDialog(quitBtn,"Are you sure you want to quit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        });
        rockBtn.addActionListener((ActionEvent ae) ->{
            determineWinner("R", Strat.getMove("R",lastMove,tally));
            tally.put("R",(int)tally.get("R")+1);
            //System.out.println(tally.get("R"));
            lastMove = "R";
        });
        paperBtn.addActionListener((ActionEvent ae) ->{
            determineWinner("P", Strat.getMove("P",lastMove,tally));
            tally.put("P",(int)tally.get("P")+1);
            //System.out.println(tally.get("P"));
            lastMove = "P";
        });
        scissorsBtn.addActionListener((ActionEvent ae) ->{
            determineWinner("S", Strat.getMove("S",lastMove,tally));
            tally.put("S",(int)tally.get("S") + 1);
            //System.out.println(tally.get("S"));
            lastMove = "S";
        });
    }
    /**
     * Creates tje stats panel.
     *
     * This panel handles the win and tie counts.
     * Assigned to the north of the wrapper panel.
     */
    public void createStatsPanel(){
        statsPnl = new JPanel();
        statsPnl.setLayout(new GridLayout(2,3,20,20));
        playerWinsLbl = new JLabel("Player Wins", JLabel.CENTER);
        playerWinsLbl.setFont(new Font("MS Comic Sans", Font.PLAIN, 24));
        computerWinsLbl = new JLabel("Computer Wins", JLabel.CENTER);
        computerWinsLbl.setFont(new Font("MS Comic Sans", Font.PLAIN, 24));
        tiesLbl = new JLabel("Ties", JLabel.CENTER);
        tiesLbl.setFont(new Font("MS Comic Sans", Font.PLAIN, 24));
        playerWinsTA = new JTextArea();
        playerWinsTA.setFont(new Font("MS Comic Sans", Font.PLAIN, 24));
        playerWinsTA.append(String.valueOf(playerWins));
        computerWinsTA = new JTextArea();
        computerWinsTA.setFont(new Font("MS Comic Sans", Font.PLAIN, 24));
        computerWinsTA.append(String.valueOf(computerWins));
        tiesTA = new JTextArea();
        tiesTA.setFont(new Font("MS Comic Sans", Font.PLAIN, 24));
        tiesTA.append(String.valueOf(ties));
        playerWinsTA.setEditable(false);
        computerWinsTA.setEditable(false);
        tiesTA.setEditable(false);

        statsPnl.add(playerWinsLbl);
        statsPnl.add(tiesLbl);
        statsPnl.add(computerWinsLbl);
        statsPnl.add(playerWinsTA);
        statsPnl.add(tiesTA);
        statsPnl.add(computerWinsTA);

        wrapperPnl.add(statsPnl, BorderLayout.NORTH);
        statsPnl.setVisible(true);

    }
    /**
     * Creates history panel.
     *
     * This panel shows the game history and bot reason for choosing what the bot chose.
     */
    public void createHistoryPanel(){
        historyPnl = new JPanel();
        historyTA = new JTextArea(15,50);
        historyTA.setEditable(false);
        historyTA.setFont(new Font("MS Comic Sans", Font.PLAIN, 16));
        scroller = new JScrollPane(historyTA);
        historyPnl.add(scroller);
        wrapperPnl.add(historyPnl, BorderLayout.CENTER);
    }
    /**
     * This determines the winner of the game with the given inputs from computer and player.
     *
     * It just uses a nested switch case to determine the outcome.
     *
     * @param player The single letter R, P, or S, representing the player's move
     * @param computer The single letter R, P, or S, representing the computer's move
     */
    public void determineWinner(String player, String computer){
        switch (player){
            case "R":
                switch (computer){
                    case "R":
                        historyTA.append("Rock ties Rock. (Tie! Computer: " + Strat.computerStrategy + ")\n");
                        ties++;
                        tiesTA.setText(String.valueOf(ties));
                        break;
                    case "P":
                        historyTA.append("Paper beats Rock. (Computer Wins! Computer: " + Strat.computerStrategy + ")\n");
                        computerWins++;
                        computerWinsTA.setText(String.valueOf(computerWins));
                        break;
                    case "S":
                        historyTA.append("Rock beats Scissors. (Player Wins! Computer: " + Strat.computerStrategy + ")\n");
                        playerWins++;
                        playerWinsTA.setText(String.valueOf(playerWins));
                        break;
                }
                break;
            case "P":
                switch (computer){
                    case "R":
                        historyTA.append("Paper beats Rock. (Player Wins! Computer: " + Strat.computerStrategy + ")\n");
                        playerWins++;
                        playerWinsTA.setText(String.valueOf(playerWins));
                        break;
                    case "P":
                        historyTA.append("Paper ties Paper. (Tie! Computer: " + Strat.computerStrategy + ")\n");
                        ties++;
                        tiesTA.setText(String.valueOf(ties));
                        break;
                    case "S":
                        historyTA.append("Scissors beats Paper. (Computer Wins! Computer: " + Strat.computerStrategy + ")\n");
                        computerWins++;
                        computerWinsTA.setText(String.valueOf(computerWins));
                        break;
                }
                break;
            case "S":
                switch (computer){
                    case "R":
                        historyTA.append("Rock beats Scissors. (Computer Wins! Computer: " + Strat.computerStrategy + ")\n");
                        computerWins++;
                        computerWinsTA.setText(String.valueOf(computerWins));
                        break;
                    case "P":
                        historyTA.append("Scissors beats Paper. (Player Wins! Computer: " + Strat.computerStrategy + ")\n");
                        playerWins++;
                        playerWinsTA.setText(String.valueOf(playerWins));
                        break;
                    case "S":
                        historyTA.append("Scissors ties Scissors. (Tie! Computer: " + Strat.computerStrategy + ")\n");
                        ties++;
                        tiesTA.setText(String.valueOf(ties));
                        break;
                }
                break;
        }
    }

}
class strategy implements Strategy{
    CheatStrategy cheat = new CheatStrategy();
    leastUsedStrategy least = new leastUsedStrategy();
    mostUsedStrategy most = new mostUsedStrategy();
    lastUsedStrategy last = new lastUsedStrategy();
    RandomStrategy random = new RandomStrategy();

    Random rand = new Random();

    String computerMove, computerStrategy;

    @Override
    public String getMove(String playerMove, String lastMove, Map tally){
        int randInt = rand.nextInt(1,101);
        if(1<=randInt && randInt<=10){
            computerMove = cheat.getMove(playerMove, lastMove, tally);
            computerStrategy = "Cheat";
        } else if (11<=randInt && randInt<=30) {
            computerMove = least.getMove(playerMove, lastMove, tally);
            computerStrategy = "Least Used";
        } else if (31<=randInt && randInt<=50) {
            computerMove = most.getMove(playerMove, lastMove, tally);
            computerStrategy = "Most Used";
        } else if (51<=randInt && randInt<=70){
            if(lastMove == null){
                computerMove = random.getMove(playerMove, lastMove, tally);
                computerStrategy = "Random";
            }else {
                computerMove = last.getMove(playerMove, lastMove, tally);
                computerStrategy = "Last Used";
            }
        } else if (71<=randInt && randInt<=100) {
            computerMove = random.getMove(playerMove, lastMove, tally);
            computerStrategy = "Random";
        }
        return computerMove;
    }
}
class leastUsedStrategy implements Strategy{
    String computerMove;
    @Override
    public String getMove(String playerMove, String lastMove, Map tally){
        Random rand = new Random();
        int R = (int) tally.get("R");
        int P = (int) tally.get("P");
        int S = (int) tally.get("S");

        int minCount = Math.min(R, Math.min(P,S));

        ArrayList<String> leastUsed = new ArrayList<>();

        if(R == minCount){
            leastUsed.add("Rock");
        }
        if(P == minCount){
            leastUsed.add("Paper");
        }
        if(S == minCount){
            leastUsed.add("Scissors");
        }

        int randDex = rand.nextInt(leastUsed.size());

        switch (leastUsed.get(randDex)){
            case "Rock":
                computerMove = "P";
                break;
            case "Paper":
                computerMove = "S";
                break;
            case "Scissors":
                computerMove = "R";
                break;
        }
        return computerMove;
    }
}
class mostUsedStrategy implements Strategy{
    String computerMove;
    @Override
    public String getMove(String playerMove, String lastMove, Map tally){
        Random rand = new Random();
        int R = (int) tally.get("R");
        int P = (int) tally.get("P");
        int S = (int) tally.get("S");

        int maxCount = Math.max(R, Math.max(P,S));

        ArrayList<String> mostUsed = new ArrayList<>();

        if(R == maxCount){
            mostUsed.add("Rock");
        }
        if(P == maxCount){
            mostUsed.add("Paper");
        }
        if(S == maxCount){
            mostUsed.add("Scissors");
        }

        int randDex = rand.nextInt(mostUsed.size());

        switch (mostUsed.get(randDex)){
            case "Rock":
                computerMove = "P";
                break;
            case "Paper":
                computerMove = "S";
                break;
            case "Scissors":
                computerMove = "R";
                break;
        }
        return computerMove;
    }
}
class lastUsedStrategy implements Strategy{
    String computerMove;
    @Override
    public String getMove(String playerMove, String lastMove, Map tally){
        switch(lastMove){
            case "R":
                computerMove = "R";
                break;
            case "P":
                computerMove = "P";
                break;
            case "S":
                computerMove = "S";
                break;
        }
        return computerMove;
    }
}