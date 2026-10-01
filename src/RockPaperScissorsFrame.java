import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RockPaperScissorsFrame extends JFrame {
    JPanel mainPnl, titlePnl, btnPnl, statsPnl, historyPnl, wrapperPnl;
    JLabel titleLbl, playerWinsLbl, computerWinsLbl, tiesLbl;
    JTextArea playerWinsTA, computerWinsTA, tiesTA, historyTA;
    ImageIcon rock, paper, scissors;
    JButton quitBtn, rockBtn, paperBtn, scissorsBtn;
    int playerWins, computerWins, ties;
    JScrollPane scroller;

    public RockPaperScissorsFrame(){
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        add(mainPnl);
        createTitlePanel();
        createWrapperPanel();
        createHistoryPanel();
        setTitle("Rock Paper Scissors");
        setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().width * 0.75),(int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.75));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
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
    public void createWrapperPanel(){
        wrapperPnl = new JPanel();
        wrapperPnl.setLayout(new BorderLayout());
        createButtonPanel();
        createStatsPanel();
        mainPnl.add(wrapperPnl, BorderLayout.CENTER);
        wrapperPnl.setVisible(true);
    }
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
    }
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
        playerWinsTA.append("0");
        computerWinsTA = new JTextArea();
        computerWinsTA.setFont(new Font("MS Comic Sans", Font.PLAIN, 24));
        computerWinsTA.append("0");
        tiesTA = new JTextArea();
        tiesTA.setFont(new Font("MS Comic Sans", Font.PLAIN, 24));
        tiesTA.append("0");
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
    public void createHistoryPanel(){
        historyPnl = new JPanel();
        historyTA = new JTextArea(15,50);
        historyTA.setEditable(false);
        historyTA.setFont(new Font("MS Comic Sans", Font.PLAIN, 16));
        scroller = new JScrollPane(historyTA);
        historyPnl.add(scroller);
        wrapperPnl.add(historyPnl, BorderLayout.CENTER);
    }
}
