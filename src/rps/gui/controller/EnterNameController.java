package rps.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;


import java.io.IOException;
import java.util.Scanner;

public class EnterNameController {

    @FXML
    private Button openGame;
    @FXML
    private TextField enterName;

    public static String playerName;

    public void openGame(ActionEvent actionEvent) throws IOException {
        playerName = enterName.getText();
        System.out.println(playerName);

        Stage switchScene = (Stage) openGame.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/GameView.fxml"));
        Scene scene = new Scene(parent);
        switchScene.setScene(scene);
    }
}
