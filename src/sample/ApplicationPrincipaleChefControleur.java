package sample;

import controlleur.BoiteAOutil;
import controlleur.ChefMagasinDAO;
import controlleur.SetupEM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.ChefMagasin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationPrincipaleChefControleur implements Initializable {

    private ChefMagasin chefMagasin;
    private Node[] nodes = new Node[1];
    private boolean estSurGestionDesRayons = false;
    private boolean estSurGestionDesVendeurs = false;
    private boolean estSurGestionDesArticlesReserves = false;
    @FXML
    private Label nomLabel;
    @FXML
    private VBox pnl_scroll;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChefMagasinDAO chefMagasinDAO = new ChefMagasinDAO();
        chefMagasinDAO.setEntityManager(SetupEM.getEm());
        chefMagasin = chefMagasinDAO.recupererChefMagasin();

        nomLabel.setText(chefMagasin.getPrenom()+" "+chefMagasin.getNom());
    }

    public void setEstSurGestionDesRayons(boolean estSurGestionDesRayons) {
        this.estSurGestionDesRayons = estSurGestionDesRayons;
    }

    public void cliqueSurGestionDesVendeurs() {
        try {
            if(!estSurGestionDesVendeurs)
            {
                chargerPage(0);
            }
        } catch (IOException ex) {
            Logger.getLogger(ApplicationPrincipaleControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cliqueSurParametres() throws IOException {

        Object[] res = new BoiteAOutil().creerBoiteDialogue ("Modifier Parametres", 0);

        //Recuperer le controleur lier à la vue
        BoiteDialogueParametresControleur controleur = ((FXMLLoader)res[0]).getController();
        controleur.setChefMagasin(chefMagasin);
        controleur.remplirFormulaire();
        controleur.setDialogStage((Stage)res[1]);
        controleur.setApplicationPrincipaleChefControleur(this);
        controleur.setNomLabel(nomLabel);

        // Afficher jusqu'à ce que l'utilisateur ferme la fenetre
        ((Stage)res[1]).showAndWait();
    }

    public void cliqueSurGestionDesRayons() throws IOException {
        try {
            if (!estSurGestionDesRayons) {
                chargerPage(1);
            }
        } catch (IOException ex) {
            Logger.getLogger(ApplicationPrincipaleControleur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cliqueSurGestionArticlesReserves() throws IOException {
        try {
            if (!estSurGestionDesArticlesReserves) {
                chargerPage(2);
            }
        } catch (IOException ex) {
            Logger.getLogger(ApplicationPrincipaleControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cliqueSurDeconnecte() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("connexion.fxml"));

        Main.getPrimaryStage().setScene(new Scene(root));
    }

    /**
     *
     * @param nombre differencie les cas
     */
    public void chargerPage(int nombre) throws IOException {
        //vider l'ancienne vue
        pnl_scroll.getChildren().clear();
        FXMLLoader loader = new FXMLLoader();
        if(nombre == 0)
        {
            loader = new FXMLLoader(getClass().getResource("gestionDesVendeurs.fxml"));
        }
        else if(nombre == 1)
        {
            loader = new FXMLLoader(getClass().getResource("gestionDesRayons.fxml"));
        }
        else if(nombre == 2)
        {
            loader = new FXMLLoader(getClass().getResource("gestionArticlesReserves.fxml"));
        }

        Parent parent = loader.load();
        nodes[0] = (Node) parent;
        pnl_scroll.getChildren().add(nodes[0]);

        if(nombre == 0)
        {
            GestionDesVendeursControleur controleur = loader.getController();

            estSurGestionDesVendeurs = true;
            estSurGestionDesRayons = false;
            estSurGestionDesArticlesReserves = false;
        }
        else if(nombre == 1)
        {
            GestionDesRayonsControleur controleur = loader.getController();

            controleur.setVBox(pnl_scroll);
            controleur.setNodes(nodes);
            controleur.setEstUnVendeur(false);
            controleur.setApplicationPrincipaleChefControleur(this);

            estSurGestionDesRayons = true;
            estSurGestionDesVendeurs = false;
            estSurGestionDesArticlesReserves = false;
        }
        else if(nombre == 2)
        {
            GestionArticlesReservesControleur controleur = loader.getController();
            controleur.setEstUnVendeur(false);

            estSurGestionDesArticlesReserves = true;
            estSurGestionDesRayons = false;
            estSurGestionDesVendeurs = false;
        }

    }

}
