package sample;

import controlleur.ChefMagasinDAO;
import controlleur.SetupEM;
import controlleur.VendeurDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.ChefMagasin;
import modele.Vendeur;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GestionDesVendeursControleur implements Initializable {

        @FXML
        private TableView<Vendeur> tableauVendeurs;
        @FXML
        private TableColumn<Vendeur, String> colPrenom;
        @FXML
        private TableColumn<Vendeur, String> colNom;
        @FXML
        private Label identifiantLabel;
        @FXML
        private Label motDePasseLabel;
        @FXML
        private Label rayonLabel;


        private VendeurDAO vendeurDAO;

        private ObservableList<Vendeur> produits = FXCollections.observableArrayList();


        private ChefMagasin chefMagasin;

        public GestionDesVendeursControleur()
        {
            vendeurDAO = new VendeurDAO();
            vendeurDAO.setEntityManager(SetupEM.getEm());
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle)
        {
            //ChefMagasinDAO chefMagasinDAO = new ChefMagasinDAO();
            //chefMagasinDAO.setEntityManager(SetupEM.getEm());
            this.chefMagasin = ChefMagasinDAO.recupererChefMagasin();

            //Specifier quel champ de l'objet produit devra être utilisé pour la colonne
            colPrenom.setCellValueFactory(new PropertyValueFactory("prenom"));
            colNom.setCellValueFactory(new PropertyValueFactory("nom"));


            remplirTableauDeVendeurs();


            //Nettoyer les details
            afficherVendeursDetails(null);

            tableauVendeurs.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> afficherVendeursDetails(newValue));

        }


        public void remplirTableauDeVendeurs()
        {
            List<Vendeur> vendeurs = vendeurDAO.recupererVendeurs();
            produits.clear();
            produits.addAll(vendeurs);

            tableauVendeurs.setItems(produits);
        }


    public void afficherVendeursDetails(Vendeur vendeur)
        {
            if(vendeur != null)
            {
                identifiantLabel.setText(vendeur.getIdentifiant());
                motDePasseLabel.setText(vendeur.getMotDePasse()+"");
                rayonLabel.setText(vendeur.getRayonV().getNom()+"");
            }
            else
            {
                identifiantLabel.setText("");
                motDePasseLabel.setText("");
                rayonLabel.setText("");
            }
        }

        /**
         *
         * @param titre
         * @param bool
         * @throws IOException
         */
        public void editerFormulaire(String titre, boolean bool) throws IOException {
            Vendeur vendeur = tableauVendeurs.getSelectionModel().getSelectedItem();

            //Charger le fichir fmxl
            FXMLLoader loader = new FXMLLoader(getClass().getResource("boiteDialogueVendeur.fxml"));
            Parent parent = loader.load();

            // Creer le stage pour la boite de dialogue
            Stage dialogStage = new Stage();
            dialogStage.setTitle(titre);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(Main.getPrimaryStage());
            Scene scene = new Scene(parent);
            dialogStage.setScene(scene);
            //Recuperer le controleur lier à la vue
            BoiteDialogueVendeurControleur controleur = loader.getController();
            //Modifier le vendeur
            if(bool == true)
            {
                controleur.remplirFormulaire(vendeur);
            }
            else
            {
                controleur.remplirFormulaire(null);
            }
            //Indique au controler si c'est a modifier ou a ajouter
            controleur.setEstAModifier(bool);
            controleur.setVendeur(vendeur);
            controleur.setDialogStage(dialogStage);
            controleur.setGestionDesVendeursControleur(this);

            // Afficher jusqu'à ce que l'utilisateur ferme la fenetre
            dialogStage.showAndWait();
        }

        public void cliqueSurSupprimer(ActionEvent actionEvent) {

            VendeurDAO vendeurDAO = new VendeurDAO();
            vendeurDAO.setEntityManager(SetupEM.getEm());
            Vendeur vendeur =  tableauVendeurs.getSelectionModel().getSelectedItem();
            //Si un vendeur est selectionne
            if(vendeur != null) {
                vendeurDAO.supprimerVendeur(vendeur.getIdPersonne(),vendeur.getRayonV());
            }
            remplirTableauDeVendeurs();

        }

        public void cliqueSurModifier(ActionEvent actionEvent) throws IOException {

            if(tableauVendeurs.getSelectionModel().getSelectedItem() == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur modifier vendeur");
                alert.setContentText("Veuillez selectionner un vendeur dans la liste");
                alert.showAndWait();
            }
            else
            {
                editerFormulaire("Modifier vendeur", true);
            }

        }


        public void cliqueSurAjouter(ActionEvent actionEvent) throws IOException {
            editerFormulaire("Ajouter vendeur",false);
        }

}
