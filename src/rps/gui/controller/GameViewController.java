package rps.gui.controller;

// Java imports

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import rps.bll.game.Move;
import rps.gui.ConsoleApp;

import rps.bll.game.GameManager;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.game.ResultType;
import rps.bll.player.IPlayer;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

import static java.lang.Integer.valueOf;

/**
 * @author smsj
 */
public class GameViewController implements Initializable {
    @FXML
    public Text aiName;
    @FXML
    private ImageView human;
    @FXML
    private ImageView robot;
    @FXML
    private Text round;
    @FXML
    private Text gamestate;

    public String playerMove = "";

    ConsoleApp consoleApp = new ConsoleApp();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aiName.setText(consoleApp.getRandomBotName());
    }

    public void paper(MouseEvent mouseEvent) {
        playerMove = "Paper";
        startGame();

        String paperIMGFilePath = System.getProperty("user.dir") + "\\src\\rps\\gui\\view\\Image\\paper.png";
        Image paperIMG = new Image(paperIMGFilePath);
        human.setImage(paperIMG);
    }

    public void rock(MouseEvent mouseEvent) {
        playerMove = "Rock";
        startGame();

        String rockIMGFilePath = System.getProperty("user.dir") + "\\src\\rps\\gui\\view\\Image\\rock.png";
        Image rockIMG = new Image(rockIMGFilePath);
        human.setImage(rockIMG);
    }

    public void scissor(MouseEvent mouseEvent) {
        playerMove = "Scissor";
        startGame();

        String scissorIMGFilePath = System.getProperty("user.dir") + "\\src\\rps\\gui\\view\\Image\\scissor.png";
        Image scissorIMG = new Image(scissorIMGFilePath);
        human.setImage(scissorIMG);
    }


    /**
     * Starts the game
     */
    public void startGame() {
        ;

        IPlayer human = new Player(playerName(), PlayerType.Human);
        IPlayer bot = new Player(aiName.getText(), PlayerType.AI);

        GameManager ge = new GameManager(human, bot);

        while (true) {
            String playerMove = getPlayerMove();

            ge.playRound(Move.valueOf(playerMove));
            System.out.println(ge.playRound(Move.valueOf(playerMove)));

            ge.getGameState().getHistoricResults().forEach((result) -> {
                gamestate.setText(getResultAsString(result));
                round.setText(getRoundAsString(result));
            });
            break;
        }
    }


    private String playerName() {
        String pn = "Player 1";
        return pn;
        //TODO
        //getTextField
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
                "HAL"
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
                result.getWinnerPlayer().getPlayerName() +
                        " (" + result.getWinnerMove() + ") " +
                        statusText + result.getLoserPlayer().getPlayerName() +
                        " (" + result.getLoserMove() + ")!";
    }

    public String getRoundAsString(Result result) {
        String statusText = result.getType() == ResultType.Win ? "wins over " : "ties ";

        return "Round #" + result.getRoundNumber() + ":";
    }


}
