package sample;

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

        //private Vendeur vendeur;
        private ChefMagasin chefMagasin;

        public GestionDesVendeursControleur()
        {
            vendeurDAO = new VendeurDAO();
            vendeurDAO.setEntityManager(SetupEM.getEm());
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle)
        {
            //this.vendeur = VendeurDAO.trouverVendeurAvecIdentifiant(ConnexionControleur.getIdentifiant());
            //this.chefMagasin = ChefMagasinDAO.recupererChefMagasin();

            //Specifier quel champ de l'objet produit devra être utilisé pour la colonne
            colPrenom.setCellValueFactory(new PropertyValueFactory("Prenom"));
            colNom.setCellValueFactory(new PropertyValueFactory("Nom"));

            //List<Vendeur> vendeurs = vendeurDAO.recupererVendeurs();
            //produits.addAll(vendeurs);

            tableauVendeurs.setItems(produits);

            //Nettoyer les details
            afficherVendeursDetails(null);

            tableauVendeurs.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> afficherVendeursDetails(newValue));

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

        public void cliqueSurSupprimer(ActionEvent actionEvent) {

            Vendeur vendeur =  tableauVendeurs.getSelectionModel().getSelectedItem();
            //Si un vendeur est selectionne
            if(vendeur != null) {
                vendeurDAO.supprimerVendeur(vendeur.getIdPersonne(),vendeur.getRayonV());
            }
            //il manque un refresh immediat de la vue

        }

        public void cliqueSurModifier(ActionEvent actionEvent) throws IOException {

            Vendeur vendeur =  tableauVendeurs.getSelectionModel().getSelectedItem();

            //Si un vendeur est selectionne
            if(vendeur != null) {
                // LE TOUT A FAIRE DANS UNE FONCTION A PART
                //Charger le fichir fmxl
                FXMLLoader loader = new FXMLLoader(getClass().getResource("boiteDialogueModifierVendeur.fxml"));
                Parent parent = loader.load();

                // Creer le stage pour la boite de dialogue
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Modifier Vendeur");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(Main.getPrimaryStage());
                Scene scene = new Scene(parent);
                dialogStage.setScene(scene);

               /* //Recuperer le controleur lier à la vue
                BoiteDialogueModifierVendeurControleur controleur = loader.getController();
                controleur.setVendeur(vendeur);
                controleur.remplirFormulaire(vendeur.getRayonV());*/

                // Afficher jusqu'à ce que l'utilisateur ferme la fenetre
                dialogStage.showAndWait();
            }
        }


        public void cliqueSurAjouter(ActionEvent actionEvent) throws IOException {

            // LE TOUT A FAIRE DANS UNE FONCTION A PART
            //Charger le fichir fmxl
            FXMLLoader loader = new FXMLLoader(getClass().getResource("boiteDialogueVendeur.fxml"));
            Parent parent = loader.load();

            // Creer le stage pour la boite de dialogue
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ajouter Vendeur");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(Main.getPrimaryStage());
            Scene scene = new Scene(parent);
            dialogStage.setScene(scene);

            //Recuperer le controleur lier à la vue
            BoiteDialogueVendeurControleur controleur = loader.getController();
            //controleur.setVendeur(vendeur);
            //controleur.remplirFormulaire(/*vendeur.getRayonV()*/);


            // Afficher jusqu'à ce que l'utilisateur ferme la fenetre
            dialogStage.showAndWait();
        }

}
