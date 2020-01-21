package sample;

import com.jfoenix.controls.JFXComboBox;
import controlleur.MagasinDAO;
import controlleur.VendeurDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import modele.Article;
import modele.Rayon;
import modele.Vendeur;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BoiteDialogueAjouterVendeurControleur implements Initializable{

        private VendeurDAO vendeurDAO;
        //private Article article;
        //private Vendeur vendeur;
        @FXML
        private TextField prenomTextF;
        @FXML
        private TextField nomTextF;
        @FXML
        private ComboBox<String> nomRayonCombo;
        @FXML
        private TextField identifiantTextF;
        @FXML
        private TextField motDePasseTextF;

        //private ComboBox<String> nomRayonCombo;
        //private ObservableList<String> produits = FXCollections.observableArrayList();

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                List<Rayon> rayons = MagasinDAO.recupererRayon();

                List<String> nomRayons = new ArrayList<String>();
                for(int i = 0; i < rayons.size(); ++i)
                {
                        nomRayons.add(rayons.get(i).getNom());
                }
                //produits.addAll(nomRayons);
                //nomRayonCombo = new ComboBox<String>(FXCollections
                 //       .observableArrayList(nomRayons));
                //nomRayonCombo.show();
                //nomRayonCombo.setVisible(true);*/
                nomRayonCombo.getItems().setAll(nomRayons);

        }

        //public void remplirFormulaire(/*Rayon rayon*/)
        /*{
            if(vendeur != null)
            {
                prenomTextF.setText(vendeur.getPrenom());
                nomTextF.setText(vendeur.getNom() + "");
                nomRayonTextF.setText(vendeur.getRayonV().getNom());
                identifiantTextF.setText(vendeur.getIdentifiant() + "");
                motDePasseTextF.setText(vendeur.getMotDePasse() + "");
            }*/

            /*else
            {
                article = new Article();
                article.setRayonA(rayon);
            }*/
        //}


        public void cliqueSurValider(ActionEvent actionEvent) {

            VendeurDAO.creerVendeur(nomTextF.getText(), prenomTextF.getText(), identifiantTextF.getText(), motDePasseTextF.getText(), MagasinDAO.trouverRayonAvecNom((String)nomRayonCombo.getSelectionModel().getSelectedItem()));


        }

        public void cliqueSurAnnuler(ActionEvent actionEvent) {
        }

        /*public void setVendeur(Vendeur vendeur) {
            this.vendeur = vendeur;
        }*/



}
