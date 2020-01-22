package sample;

import controlleur.MagasinDAO;
import controlleur.RayonDAO;
import controlleur.VendeurDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import modele.Article;
import modele.Rayon;
import modele.Vendeur;

import java.net.URL;
import java.util.ResourceBundle;

public class BoiteDialogueModifierVendeurControleur implements Initializable {

        private VendeurDAO vendeurDAO;
        //private Article article;
        private Vendeur vendeur;
        @FXML
        private TextField prenomTextF;
        @FXML
        private TextField nomTextF;
        @FXML
        private TextField nomRayonTextF;
        @FXML
        private TextField identifiantTextF;
        @FXML
        private TextField motDePasseTextF;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
        }

        public void remplirFormulaire(/*Rayon rayon*/)
        {
            if(vendeur != null)
            {
                prenomTextF.setText(vendeur.getPrenom());
                nomTextF.setText(vendeur.getNom() + "");
                nomRayonTextF.setText(vendeur.getRayonV().getNom());
                identifiantTextF.setText(vendeur.getIdentifiant() + "");
                motDePasseTextF.setText(vendeur.getMotDePasse() + "");
            }

            /*else
            {
                article = new Article();
                article.setRayonA(rayon);
            }*/
        }


        public void cliqueSurValider(ActionEvent actionEvent) {

            VendeurDAO.modifierVendeur(vendeur, prenomTextF.getText(), nomTextF.getText(), MagasinDAO.trouverRayonAvecNom(nomRayonTextF.getText()), identifiantTextF.getText(), motDePasseTextF.getText());


        }

        public void cliqueSurAnnuler(ActionEvent actionEvent) {
        }

        public void setVendeur(Vendeur vendeur) {
            this.vendeur = vendeur;
        }


    }
