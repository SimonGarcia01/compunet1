package com.example.curriculum_sis;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    /*@FXML
    private Button show_me;

    private Parent root;

     */


    // HACER QUE FUNCIONA. NECESITO QUE CAMBIE DE ESCENA CUANDO SE LE DE CLICK AL BOTON. ¡OJO!
    // CON LA AYUDA DE DIOS TODO VA A ESTAR BIEN.
    /*
    @FXML
    public void showMe(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("principal_page.fxml"));
            root = fxmlLoader.load();
            Node n = (Node) event.getSource();
            Scene scene = n.getScene();
            Stage stage = (Stage) scene.getWindow();
            scene = new Scene(root, 768, 607);
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

     */

}