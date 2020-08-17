import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener {

    Image image;

    private String startGameS = "Start Game";
    private String viewHighscoreS = "View High Score";
    private String tutorialS = "Tutorial";

    private static final long serialVersionUID = 1L;
    private String gif1 = "Hunt the Wumpus/res/giph1.gif";
    private String gif2 = "Hunt the Wumpus/res/giph3.gif";
    private String gif3 = "Hunt the Wumpus/res/gif2.gif";


    public String random() {
        int random = (int) ((Math.random() * 4) + 1);
        if (random == 1) {
            return gif1;
            //return "giph1.gif";
        } else if (random == 2) {
            return gif2;
            //return "gif2.gif";
        } else if (random == 3) {
            return gif3;
            //return "giph3.gif";
        } else if (random == 4) {
            return "Hunt the Wumpus/res/giphy.gif";
            //return "giphy.gif";
        } else {
            return "Hunt the Wumpus/res/gif3.gif";
            //return "gif3.gif";
        }
    }

    public MainMenu(){

        // Defines the name of the title screen

        super("Faze Clan Game Menu Screen");
        // Set the JFrame box size

        setSize(500,477);

        // Can resize the window with corners
        setResizable(true);

        // Upon the exit button it will break the JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Setting the layout of the JFrame to the default Java Swing library

        //setLayout(new GridLayout(3,5));

        setLayout(new BorderLayout());

        //String menu = random();

        //change that will work and make cleaner later - ImageIcon imageIcon = new ImageIcon(MainMenu.this.getClass().getResource(random()));

        ImageIcon imageIcon = new ImageIcon(random());

        JLabel background = new JLabel(imageIcon);


        //JLabel background=new JLabel(new ImageIcon("Hunt the Wumpus/res/background.png"));

        background.setLayout(new FlowLayout());

        JButton startGame = new JButton(startGameS);
        JButton viewHighscore = new JButton(viewHighscoreS);
        JButton tutorial = new JButton(tutorialS);


        startGame.addActionListener(this);
        viewHighscore.addActionListener(this);
        tutorial.addActionListener(this);

        add(background);
        background.add(startGame);
        background.add(viewHighscore);
        background.add(tutorial);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /** DEBUG **/
        //System.out.println("Button has been clicked!");

        String name = e.getActionCommand();

        if(name.equals(startGameS)) {
            System.out.println("Pressed start game");

            JOptionPane playerTitleBox = new JOptionPane();
            String playerName = null;

            while (playerName == null) {
                playerName = playerTitleBox.showInputDialog(null, "Enter your player name: ");

            }


            JOptionPane chooseMap = new JOptionPane();
            int map = 0;

            while (map == 0) {

                map = Integer.parseInt(chooseMap.showInputDialog(null, "Choose your map enter 1 - 3: "));

            }


            Player player = new Player(playerName, map);

            try {
                HighScore.updateList(20,player.getPlayerName(),player.getPlayerCaveID() + " ",10,10,10,1);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            System.out.println(player.getPlayerName());
            System.out.println(player.getPlayerCaveID());

            GraphicalInterface game = new GraphicalInterface(map);
        } else if (name.equals(viewHighscoreS)) {
            System.out.println("Pressed view highscores.");

            JDialog highScoreDialog = new JDialog(this);
            highScoreDialog.setSize(800,300);
            highScoreDialog.setTitle("Highscore(s)");
            //highScoreDialog.getContentPane().setBackground(Color.CYAN);
            highScoreDialog.setVisible(true);

            JTextArea jTextArea = new JTextArea(HighScore.getHighScores());
            jTextArea.setEditable(false);

            highScoreDialog.add(jTextArea);



        } else  if (name.equals(tutorialS)) {
            System.out.println("Pressed view tutorial");
            //tutorial();
        }

    }




}