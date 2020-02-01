package controleur;

import controleur.dao.SetupEM;
import controleur.dao.VendeurDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import main.Main;
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
                chargerPage(0);
            }
        } catch (IOException ex) {
            Logger.getLogger(ApplicationPrincipaleControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cliqueSurMonRayon() {
        try {
            if(!estSurMonRayon)
            {
                chargerPage(1);
            }
        } catch (IOException ex) {
            Logger.getLogger(ApplicationPrincipaleControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cliqueSurArticlesReserves() {
    try{
        if(!estSurArticlesReserves)
        {
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
            loader = new FXMLLoader(getClass().getResource("../ressources/gestionDesRayons.fxml"));
        }
        else if(nombre == 1)
        {
            loader = new FXMLLoader(getClass().getResource("../ressources/monRayon.fxml"));
        }
        else if(nombre == 2)
        {
            loader = new FXMLLoader(getClass().getResource("../ressources/gestionArticlesReserves.fxml"));
        }

        Parent parent = loader.load();
        nodes[0] = (Node) parent;
        pnl_scroll.getChildren().add(nodes[0]);

        if(nombre == 0)
        {
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
        else if(nombre == 1)
        {
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
        else if(nombre == 2)
        {
            GestionArticlesReservesControleur controleur = loader.getController();
            controleur.setEstUnVendeur(true);
            controleur.setVendeur(vendeur);
            controleur.remplirTableauDArticlesReserves(vendeur);


            //Mettre à jour les booleens
            estSurArticlesReserves = true;
            estSurMonRayon = false;
            estSurAutresRayons = false;
        }

    }
}