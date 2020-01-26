package sample;

import controlleur.SetupEM;
import controlleur.VendeurDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modele.Vendeur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationPrincipaleControleur implements Initializable {

    private Node[] nodes = new Node[1];
    private boolean estSurMonRayon = false;
    private boolean estSurAutresRayons = false;
    private boolean estSurArticlesReserves = false;
    private Vendeur vendeur;

    @FXML
    private Label nomLabel;

    @FXML
    private VBox pnl_scroll;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VendeurDAO vendeurDAO = new VendeurDAO();
        vendeurDAO.setEntityManager(SetupEM.getEm());

        vendeur = vendeurDAO.trouverVendeurAvecIdentifiant(ConnexionControleur.getIdentifiant());
        nomLabel.setText(vendeur.getPrenom()+" "+vendeur.getNom());
    }

    public void setEstSurAutresRayons(boolean estSurAutresRayons) {
        this.estSurAutresRayons = estSurAutresRayons;
    }

    public void cliqueSurAutresRayons()
    {

        try {
            if(!estSurAutresRayons)
            {
                System.out.println("salut toi");
                //Vider l'ancienne vue
                pnl_scroll.getChildren().clear();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("gestionDesRayons.fxml"));
                Parent parent = loader.load();
                nodes[0] = (Node) parent;
                pnl_scroll.getChildren().add(nodes[0]);

                GestionDesRayonsControleur controleur = loader.getController();
                controleur.setVendeur(vendeur);

                controleur.setVBox(pnl_scroll);
                controleur.setNodes(nodes);
                controleur.setEstUnVendeur(true);
                controleur.remplirTableauDeRayons();
                controleur.setApplicationPrincipaleControleur(this);

                controleur.vue();

                //Mettre à jour les booleens
                estSurAutresRayons = true;
                estSurMonRayon = false;
                estSurArticlesReserves = false;

            }
        } catch (IOException ex) {
            Logger.getLogger(ApplicationPrincipaleControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cliqueSurMonRayon() {
        try {
            if(!estSurMonRayon)
            {
                //Vider l'ancienne vue
                pnl_scroll.getChildren().clear();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("monRayon.fxml"));
                Parent parent = loader.load();
                nodes[0] = (Node) parent;
                pnl_scroll.getChildren().add(nodes[0]);

                MonRayonControleur controleur = loader.getController();
                controleur.setRayon(vendeur.getRayonV());
                controleur.setType(1);
                controleur.vue();
                controleur.remplirTableauDArticles();

                //Mettre à jour les booleens
                estSurMonRayon = true;
                estSurAutresRayons = false;
                estSurArticlesReserves = false;
            }
        } catch (IOException ex) {
            Logger.getLogger(ApplicationPrincipaleControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cliqueSurArticlesReserves() {
    try{

        //Vider l'ancienne vue
        pnl_scroll.getChildren().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("gestionArticlesReserves.fxml"));
        Parent parent = loader.load();
        nodes[0] = (Node) parent;
        pnl_scroll.getChildren().add(nodes[0]);

        GestionArticlesReservesControleur controleur = loader.getController();
        controleur.setEstUnVendeur(true);
        controleur.setVendeur(vendeur);
        controleur.remplirTableauDArticlesReserves(vendeur);


        //Mettre à jour les booleens
        estSurArticlesReserves = true;
        estSurMonRayon = false;
        estSurAutresRayons = false;

        } catch (IOException ex) {
        Logger.getLogger(ApplicationPrincipaleControleur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cliqueSurDeconnecte() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("connexion.fxml"));

        Main.getPrimaryStage().setScene(new Scene(root));
    }
}