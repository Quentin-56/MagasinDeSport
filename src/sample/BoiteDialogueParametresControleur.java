package sample;

import controlleur.ChefMagasinDAO;
import controlleur.VendeurDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import modele.ChefMagasin;
import modele.Vendeur;

import java.net.URL;
import java.util.ResourceBundle;

public class BoiteDialogueParametresControleur implements Initializable {

    private ChefMagasin chefMagasin;
    @FXML
    private TextField nomTextF;
    @FXML
    private TextField prenomTextF;
    @FXML
    private TextField motDePasseTextF;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void remplirFormulaire()
    {
        //if(vendeur != null)
        //{
            nomTextF.setText(chefMagasin.getNom());
            prenomTextF.setText(chefMagasin.getPrenom() + "");
            motDePasseTextF.setText(chefMagasin.getMotDePasse());
        //}

            /*else
            {
                article = new Article();
                article.setRayonA(rayon);
            }*/
    }


    public void cliqueSurValider(ActionEvent actionEvent) {

        ChefMagasinDAO.modifierChefMagasin(chefMagasin, nomTextF.getText(), prenomTextF.getText(), motDePasseTextF.getText());


    }

    public void cliqueSurAnnuler(ActionEvent actionEvent) {
    }

    public void setChefMagasin(ChefMagasin chefMagasin) {
        this.chefMagasin = chefMagasin;
    }
}
