package sample;

import controlleur.ChefMagasinDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.ChefMagasin;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class applicationPrincipaleChefControleur implements Initializable {

    private ChefMagasin chefMagasin;
    private Node[] nodes = new Node[1];
    private boolean estSurGestionDesRayons = false;
    private boolean estSurGestionDesVendeurs = false;
    private boolean estSurGestionDesArticlesReserves = false;
    @FXML
    private VBox pnl_scroll;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chefMagasin = ChefMagasinDAO.recupererChefMagasin();
    }

    public void cliqueSurGestionDesVendeurs(ActionEvent actionEvent) {
        try {
            if(!estSurGestionDesVendeurs)
            {
                /*Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Test Connection");
                alert.setContentText("Connect to the database successfully!");
                alert.showAndWait();*/

                //Vider l'ancienne vue
                pnl_scroll.getChildren().clear();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("gestionDesVendeurs.fxml"));
                Parent parent = loader.load();
                nodes[0] = (Node) parent;
                pnl_scroll.getChildren().add(nodes[0]);

                GestionDesVendeursControleur controleur = loader.getController();

                //Mettre à jour les booleens
                estSurGestionDesVendeurs = true;
                estSurGestionDesRayons = false;
                estSurGestionDesArticlesReserves = false;
            }
        } catch (IOException ex) {
            Logger.getLogger(applicationPrincipaleControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void cliqueSurParametres(ActionEvent actionEvent) throws IOException {
        // LE TOUT A FAIRE DANS UNE FONCTION A PART
        //Charger le fichir fmxl
        FXMLLoader loader = new FXMLLoader(getClass().getResource("boiteDialogueParametres.fxml"));
        Parent parent = loader.load();

        // Creer le stage pour la boite de dialogue
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Modifier Parametres");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(Main.getPrimaryStage());
        Scene scene = new Scene(parent);
        dialogStage.setScene(scene);

        //Recuperer le controleur lier à la vue
        BoiteDialogueParametresControleur controleur = loader.getController();
        controleur.setChefMagasin(chefMagasin);
        controleur.remplirFormulaire();

        // Afficher jusqu'à ce que l'utilisateur ferme la fenetre
        dialogStage.showAndWait();
        dialogStage.close();

    }

    public void cliqueSurGestionDesRayons(ActionEvent actionEvent) throws IOException {
        /*try {
            if (!estSurGestionDesRayons) {

                //Vider l'ancienne vue
                pnl_scroll.getChildren().clear();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("applicationPrincipale.fxml"));
                Parent parent = loader.load();
                nodes[0] = (Node) parent;
                pnl_scroll.getChildren().add(nodes[0]);

                applicationPrincipaleControleur controleur = loader.getController();

                //Mettre à jour les booleens
                estSurGestionDesRayons = true;
                estSurGestionDesVendeurs = false;
                estSurGestionDesArticlesReserves = false;
            }
        } catch (IOException ex) {
            Logger.getLogger(applicationPrincipaleControleur.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }

    public void cliqueSurGestionArticlesReserves(ActionEvent actionEvent) throws IOException {
        try {
            if (!estSurGestionDesArticlesReserves) {

                //Vider l'ancienne vue
                pnl_scroll.getChildren().clear();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("gestionArticlesReserves.fxml"));
                Parent parent = loader.load();
                nodes[0] = (Node) parent;
                pnl_scroll.getChildren().add(nodes[0]);

                GestionArticlesReservesControleur controleur = loader.getController();

                //Mettre à jour les booleens
                estSurGestionDesArticlesReserves = true;
                estSurGestionDesRayons = false;
                estSurGestionDesVendeurs = false;
            }
        } catch (IOException ex) {
            Logger.getLogger(applicationPrincipaleControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cliqueSurDeconnecte(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("connexion.fxml"));

        Main.getPrimaryStage().setScene(new Scene(root));
    }


}
