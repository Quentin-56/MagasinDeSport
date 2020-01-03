package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.awt.event.ActionEvent;

public class ItemController {

    @FXML
    public static Label name;

    public void deleteButtonAction(javafx.event.ActionEvent actionEvent) {

        System.out.println("HELLO");
        System.out.println(actionEvent.getSource());

    }
}
