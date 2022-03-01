package rps.gui.controller;

// Java imports

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import rps.bll.game.Move;
import rps.gui.ConsoleApp;
import rps.gui.controller.EnterNameController;

import rps.bll.game.GameManager;
import rps.bll.game.Result;
import rps.bll.game.ResultType;
import rps.bll.player.IPlayer;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.ResourceBundle;

import static java.lang.Integer.valueOf;


/**
 * @author smsj
 */
public class GameViewController implements Initializable {
    @FXML
    public Text aiName;
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

    private IPlayer human;
    private IPlayer bot;

    private GameManager ge;

    private String paperIMGFilePath = System.getProperty("user.dir") + "\\src\\rps\\gui\\view\\Image\\paper.png";
    private String rockIMGFilePath = System.getProperty("user.dir") + "\\src\\rps\\gui\\view\\Image\\rock.png";
    private String scissorIMGFilePath = System.getProperty("user.dir") + "\\src\\rps\\gui\\view\\Image\\scissor.png";

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

    public void paper(ActionEvent actionEvent) {
        playerMove = "Paper";
        playRound();

        Image paperIMG = new Image(paperIMGFilePath);
        humanImg.setImage(paperIMG);
    }

    public void rock(ActionEvent actionEvent) {
        playerMove = "Rock";
        playRound();

        Image rockIMG = new Image(rockIMGFilePath);
        humanImg.setImage(rockIMG);
    }

    public void scissor(ActionEvent actionEvent) {
        playerMove = "Scissor";
        playRound();

        Image scissorIMG = new Image(scissorIMGFilePath);
        humanImg.setImage(scissorIMG);
    }


    /**
     * Starts the game
     */

    public void playRound() {
        String playerMove = getPlayerMove();
        ge.playRound(Move.valueOf(playerMove));

        ArrayList<Result> results = new ArrayList<>(ge.getGameState().getHistoricResults());
        Result result = results.get(results.size() - 1);

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


}
