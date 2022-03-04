package rps.gui.controller;

// Java imports

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

import rps.bll.game.Move;

import rps.bll.game.GameManager;
import rps.bll.game.Result;
import rps.bll.game.ResultType;
import rps.bll.player.IPlayer;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;



/**
 * @author smsj
 */
public class GameViewController implements Initializable {
    @FXML
    public Text aiName;
    @FXML
    public Text aiPointsTxt;
    @FXML
    public Text playerPointsTxt;
    @FXML
    private ImageView humanImg;
    @FXML
    private ImageView robotImg;
    @FXML
    private Text round;
    @FXML
    private Text gamestate;
    @FXML
    private Text playerTxt;

    public String playerMove = "";
    private MediaPlayer mediaPlayer;


    private IPlayer human;
    private IPlayer bot;

    private GameManager ge;

    private String paperIMGFilePath = System.getProperty("user.dir") + "\\src\\rps\\gui\\view\\Image\\paper.png";
    private String fart = System.getProperty("user.dir") + "\\src\\rps\\gui\\view\\Sound\\fart.mp3";

    private String rockIMGFilePath = System.getProperty("user.dir") + "\\src\\rps\\gui\\view\\Image\\rock.png";
    private String deez = System.getProperty("user.dir") + "\\src\\rps\\gui\\view\\Sound\\Deez-Nuts.mp3";

    private String scissorIMGFilePath = System.getProperty("user.dir") + "\\src\\rps\\gui\\view\\Image\\scissor.png";
    private String bruh = System.getProperty("user.dir") + "\\src\\rps\\gui\\view\\Sound\\BRUH.mp3";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aiName.setText(getRandomBotName());
        playerTxt.setText(playerName());

        human = new Player(playerName(), PlayerType.Human);
        bot = new Player(aiName.getText(), PlayerType.AI);

        ge = new GameManager(human, bot);
    }

    public void paper(ActionEvent actionEvent) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        playerMove = "Paper";
        playRound();

        Image paperIMG = new Image(paperIMGFilePath);
        humanImg.setImage(paperIMG);

        Media media = new Media(new File(fart).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.stop();

    }

    public void rock(ActionEvent actionEvent) throws MalformedURLException {
        playerMove = "Rock";
        playRound();

        Image rockIMG = new Image(rockIMGFilePath);
        humanImg.setImage(rockIMG);

        Media media = new Media(new File(deez).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.stop();
    }

    public void scissor(ActionEvent actionEvent) {
        playerMove = "Scissor";
        playRound();

        Image scissorIMG = new Image(scissorIMGFilePath);
        humanImg.setImage(scissorIMG);

        Media media = new Media(new File(bruh).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.stop();
    }


    /**
     * Starts the game
     */

    public void playRound() {
        String playerMove = getPlayerMove();
        ge.playRound(Move.valueOf(playerMove));

        ArrayList<Result> results = new ArrayList<>(ge.getGameState().getHistoricResults());
        Result result = results.get(results.size() - 1);

        int[] wins = getPlayerWins(results,human);

        playerPointsTxt.setText(""+wins[0]);
        aiPointsTxt.setText(""+wins[1]);

        gamestate.setText(getResultAsString(result));
        round.setText(getRoundAsString(result));
        gamestate.setX(-60);

        Move robotMove = result.getWinnerPlayer().getPlayerType() == PlayerType.AI ? result.getWinnerMove() : result.getLoserMove();

        switch(robotMove) {
            case Rock -> robotImg.setImage(new Image(rockIMGFilePath));
            case Paper -> robotImg.setImage(new Image(paperIMGFilePath));
            case Scissor -> robotImg.setImage(new Image(scissorIMGFilePath));
        }

        System.out.println(getResultAsString(result));
    }


    private String playerName() {

        return EnterNameController.playerName;

    }

    /**
     * Famous robots...
     *
     * @return
     */
    private String getRandomBotName() {
        String[] botNames = new String[]{
                "R2D2",
                "Mr. Data",
                "3PO",
                "Bender",
                "Marvin the Paranoid Android",
                "Bishop",
                "Robot B-9",
                "HAL",
                "B-1 Battle Droid",
                "Wall-E",
                "T-800",
                "Agent Smith",
                "Cortana",
                "AWESOM-O",
                "Optimus Prime"

        };
        int randomNumber = new Random().nextInt(botNames.length - 1);
        return botNames[randomNumber];
    }


    /**
     * @return Rock, Paper or Scissor as String
     */
    public String getPlayerMove() {
        return playerMove;
    }


    /**
     * Provides a custom formatted string representation of a Result
     *
     * @param result
     * @return
     */
    public String getResultAsString(Result result) {
        String statusText = result.getType() == ResultType.Win ? "wins over " : "ties ";

        return
            result.getWinnerPlayer().getPlayerName() + " " +
            statusText + result.getLoserPlayer().getPlayerName();
    }

    public String getRoundAsString(Result result) {

        return "Round #" + result.getRoundNumber() + ":";
    }


    private int[] getPlayerWins(ArrayList<Result> results, IPlayer player)
    {
        int playerWins = 0;
        int aiWins = 0;

        for (Result r:results) {
            if (r.getType() != ResultType.Tie)
            {
                if (r.getWinnerPlayer().getPlayerName() == player.getPlayerName()) {
                    playerWins++;
                } else {
                    aiWins++;
                }
            }
        }

        int[] wins = {playerWins,aiWins};

        return wins;
    }
}
