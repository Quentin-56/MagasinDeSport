package sample;

import controlleur.ChefMagasinDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import modele.ChefMagasin;

import java.net.URL;
import java.util.ResourceBundle;

public class applicationPrincipaleChefControleur implements Initializable {

    private Node[] nodes = new Node[1];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChefMagasin chefMagasin = ChefMagasinDAO.recupererChefMagasin();
    }

    public void cliqueSurGestionDesRayons(ActionEvent actionEvent) {

    }


}
