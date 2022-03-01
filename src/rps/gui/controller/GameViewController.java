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

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {
    @FXML
    public Text aiName;
    @FXML
    private ImageView human;
    @FXML
    private ImageView robot;

    ConsoleApp consoleApp = new ConsoleApp();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aiName.setText(consoleApp.getRandomBotName());
        // TODO
    }

    public void paper(MouseEvent mouseEvent) {
        //Move(paper());
        //Move.Paper;
        String paperIMGFilePath = System.getProperty("user.dir") + "\\src\\rps\\gui\\view\\Image\\paper.png";
        Image paperIMG = new Image(paperIMGFilePath);
        human.setImage(paperIMG);
    }

    public void rock(MouseEvent mouseEvent) {
        //Move.Rock;
        String rockIMGFilePath = System.getProperty("user.dir") + "\\src\\rps\\gui\\view\\Image\\rock.png";
        Image rockIMG = new Image(rockIMGFilePath);
        human.setImage(rockIMG);
    }

    public void scissor(MouseEvent mouseEvent) {
        //Move.Scissor
        String scissorIMGFilePath = System.getProperty("user.dir") + "\\src\\rps\\gui\\view\\Image\\scissor.png";
        Image scissorIMG = new Image(scissorIMGFilePath);
        human.setImage(scissorIMG);
    }
}
