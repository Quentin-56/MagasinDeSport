package sample;

import controlleur.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.*;
import org.controlsfx.control.textfield.CustomTextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionDesRayonsControleur implements Initializable {
    @FXML
    private TableView<Rayon> tableauRayons;
    @FXML
    private TableColumn<Rayon, String> colRayon;
    @FXML
    private CustomTextField filtreTextField;
    @FXML
    private Button ajouterButton;
    @FXML
    private Button modifierButton;
    @FXML
    private Button supprimerButton;

    private MagasinDAO magasinDAO;
    private ChefMagasin chefMagasin;

    private ObservableList<Rayon> produits = FXCollections.observableArrayList();
    private ObservableList<Rayon> filtreProduits = FXCollections.observableArrayList();

    private VBox pnl_scroll;
    private Node[] nodes;
    private boolean estUnVendeur;
    private Vendeur vendeur;

    public void setVBox(VBox vBox)
    {
        this.pnl_scroll = vBox;
    }

    public VBox getVBox()
    {
        return pnl_scroll;
    }

    public Node[] getNodes()
    {
        return nodes;
    }

    public void setNodes(Node[] no)
    {
        this.nodes = no;
    }

    public void setEstUnVendeur(boolean estUnVendeur)
    {
        this.estUnVendeur = estUnVendeur;
    }

    public void setVendeur(Vendeur vendeur)
    {
        this.vendeur = vendeur;
    }

    public GestionDesRayonsControleur()
    {
        magasinDAO = new MagasinDAO();
        magasinDAO.setEntityManager(SetupEM.getEm());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ChefMagasinDAO chefMagasinDAO = new ChefMagasinDAO();
        chefMagasinDAO.setEntityManager(SetupEM.getEm());
        this.chefMagasin = chefMagasinDAO.recupererChefMagasin();

        //Specifier quel champ de l'objet produit devra être utilisé pour la colonne
        colRayon.setCellValueFactory(new PropertyValueFactory("nom"));

        remplirTableauDeRayons();
    }

    public void remplirTableauDeRayons()
    {

        List<Rayon> rayons = magasinDAO.recupererRayon();


        if(estUnVendeur == true)
        {
            rayons.remove(vendeur.getRayonV());
        }
        filtreProduits.clear();
        filtreProduits.addAll(rayons);

        produits.clear();
        produits.addAll((rayons));

        tableauRayons.setItems(filtreProduits);
    }

    private void mettreAJourFiltre() {
        filtreProduits.clear();

        for (Rayon r : produits) {
            if (matchFiltre(r)) {
                filtreProduits.add(r);
            }
        }
    }

    /**
     * Vider ce qui a ete tape dans la barre de recherche
     */
    public void viderBarreRecherche()
    {
        filtreTextField.setText("");
    }

    private boolean matchFiltre(Rayon rayon) {
        String filtrerString = filtreTextField.getText();
        if (filtrerString == null || filtrerString.isEmpty()) {
            //Pas de filtre afficher tout les articles
            return true;
        }
        String lowerCaseFilterString = filtrerString.toLowerCase();

        if (rayon.getNom().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        }
        else{
            //Aucun match trouve
            return false;
        }
    }

    public void editerFormulaire(String titre, boolean bool) throws IOException {
        Rayon rayon = tableauRayons.getSelectionModel().getSelectedItem();

        //Charger le fichir fmxl
        FXMLLoader loader = new FXMLLoader(getClass().getResource("boiteDialogueRayon.fxml"));
        Parent parent = loader.load();

        // Creer le stage pour la boite de dialogue
        Stage dialogStage = new Stage();
        dialogStage.setTitle(titre);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(Main.getPrimaryStage());
        Scene scene = new Scene(parent);
        dialogStage.setScene(scene);
        //Recuperer le controleur lier à la vue
        BoiteDialogueRayonControleur controleur = loader.getController();
        //Modifier le rayon
        if(bool == true)
        {
            controleur.remplirFormulaire(rayon);
        }
        else
        {
            controleur.remplirFormulaire(null);
        }
        //Indique au controler si c'est a modifier ou a ajouter
        controleur.setEstAModifier(bool);
        controleur.setRayon(rayon);
        controleur.setDialogStage(dialogStage);
        controleur.setGestionDesRayonsControleur(this);

        // Afficher jusqu'à ce que l'utilisateur ferme la fenetre
        dialogStage.showAndWait();
    }

    public void cliqueSurSupprimer(ActionEvent actionEvent) {
        MagasinDAO magasinDAO = new MagasinDAO();
        magasinDAO.setEntityManager(SetupEM.getEm());
        Rayon rayon = tableauRayons.getSelectionModel().getSelectedItem();
        magasinDAO.supprimerRayon(rayon.getIdRayon());
        //Actualiser le tableauView
        remplirTableauDeRayons();
    }

    public void cliqueSurModifier(ActionEvent actionEvent) throws IOException {

        if(tableauRayons.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur modifier rayon");
            alert.setContentText("Veuillez selectionner un rayon dans la liste");
            alert.showAndWait();
        }
        else
        {
            editerFormulaire("Modifier rayon", true);
        }
    }

    public void cliqueSurAjouter(ActionEvent actionEvent) throws IOException {
        editerFormulaire("Ajouter rayon",false);
    }

    public void cliqueSurVisiter(ActionEvent actionEvent) {
        try {
            //applicationPrincipaleChefControleur app = new applicationPrincipaleChefControleur();
            //Vider l'ancienne vue
            pnl_scroll.getChildren().clear();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("monRayon.fxml"));
            Parent parent = loader.load();
            nodes[0] = (Node) parent;
            pnl_scroll.getChildren().add(nodes[0]);

            MonRayonControleur controleur = loader.getController();

            Rayon rayon = tableauRayons.getSelectionModel().getSelectedItem();

            // permet de savoir quel rayon afficher
            controleur.setRayon(rayon);

            if(estUnVendeur == false)
            {
                controleur.settype(0);
            }
            if(estUnVendeur == true)
            {
                controleur.settype(2);
            }

            controleur.vue();
            controleur.remplirTableauDArticles();

        } catch (IOException ex) {
            Logger.getLogger(applicationPrincipaleControleur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cliqueSurSearch(ActionEvent actionEvent) {
        mettreAJourFiltre();
    }

    public void cliqueSurSupprimerFiltre(ActionEvent actionEvent) {
        viderBarreRecherche();
        remplirTableauDeRayons();
    }

    public void vue()
    {
        if(estUnVendeur == true)
        {
            ajouterButton.setVisible(false);
            modifierButton.setVisible(false);
            supprimerButton.setVisible(false);
        }
    }
}
