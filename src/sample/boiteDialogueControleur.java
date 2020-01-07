package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class boiteDialogueControleur implements Initializable {

    @FXML
    private TextField nomTextF;
    @FXML
    private TextField prixTextF;
    @FXML
    private TextField detailsTextF;
    @FXML
    private TextField quantiteTextF;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void cliqueSurValider(ActionEvent actionEvent) {

    }

    public void cliqueSurAnnuler(ActionEvent actionEvent) {
    }
}
