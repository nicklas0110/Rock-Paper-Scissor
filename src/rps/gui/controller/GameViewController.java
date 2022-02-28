package rps.gui.controller;

// Java imports
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import rps.bll.game.Move;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {

    @FXML
    private ImageView human;
    @FXML
    private ImageView robot;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        // TODO
    }

    public void paper(MouseEvent mouseEvent) throws FileNotFoundException {
        //Move.Paper;
        Image imgPaper = new Image(getClass().getResourceAsStream("src/rps/gui/view/Image/paper.png"));
        human.setImage(imgPaper);
    }

    public void rock(MouseEvent mouseEvent) {
        //Move.Rock;
        Image rockIMG = new Image("src/rps/gui/Image/rock.png");
        human.setImage(rockIMG);
    }

    public void scissor(MouseEvent mouseEvent) {
        //Move.Scissor

        Image scissorIMG = new Image("C:\\Users\\Nickl\\Documents\\GitHub\\Rock-Paper-Scissor\\src\\rps\\gui\\view\\Image\\scissor.png");
        human.setImage(scissorIMG);
    }
}
