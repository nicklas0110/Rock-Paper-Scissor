package rps.gui;

// Java imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX implementation of the RPS game
 *
 * @author smsj
 */
public class JavaFXApp extends Application {

    public static void launch() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        Image icon = new Image(getClass().getResourceAsStream("view/Image/icon.png"));
        stage.getIcons().add(icon);


        Parent root = FXMLLoader.load(getClass().getResource("view/EnterName.fxml"));
        stage.setTitle("Rock-Paper-Scissor");
        stage.setScene(new Scene(root));
        stage.show();

    }
}
