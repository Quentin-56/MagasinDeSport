package sample;

import controlleur.BoiteAOutil;
import controlleur.RayonDAO;
import controlleur.SetupEM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.Article;
import modele.Rayon;
import org.controlsfx.control.textfield.CustomTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MonRayonControleur implements Initializable {
    @FXML
    private TableView<Article> tableau;
    @FXML
    private TableColumn<Article, String> colNom;
    @FXML
    private TableColumn<Article, String> colQuantite;
    @FXML
    private Label detailsLabel;
    @FXML
    private Label reservationLabel;
    @FXML
    private Label prixLabel;
    @FXML
    private CustomTextField filtreTextField;
    @FXML
    private Button transfererButton;
    @FXML
    private Button btnAdd;
    @FXML
    private Button supprimerButton;
    @FXML
    private Button modifierButton;
    @FXML
    private Button reserverButton;
    @FXML
    private Button btnRetourEnArriere;

    private ObservableList<Article> produits = FXCollections.observableArrayList();
    private ObservableList<Article> filtreProduits = FXCollections.observableArrayList();

    private int type;
    private Rayon rayon;
    private ApplicationPrincipaleControleur applicationPrincipaleControleur;
    private ApplicationPrincipaleChefControleur applicationPrincipaleChefControleur;
    private RayonDAO rayonDAO;

    public MonRayonControleur()
    {
        rayonDAO = new RayonDAO();
        rayonDAO.setEntityManager(SetupEM.getEm());
    }

    public void setApplicationPrincipaleControleur(ApplicationPrincipaleControleur applicationPrincipaleControleur) {
        this.applicationPrincipaleControleur = applicationPrincipaleControleur;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public void setRayon(Rayon rayon)
    {
        this.rayon = rayon;
    }

    public void setApplicationPrincipaleChefControleur(ApplicationPrincipaleChefControleur applicationPrincipaleChefControleur) {
        this.applicationPrincipaleChefControleur = applicationPrincipaleChefControleur;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Specifier quel champ de l'objet produit devra être utilisé pour la colonne
        colNom.setCellValueFactory(new PropertyValueFactory("nom"));
        colQuantite.setCellValueFactory(new PropertyValueFactory("quantite"));

        remplirTableauDArticles();

        //Nettoyer les details
        afficherArticleDetails(null);

        tableau.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> afficherArticleDetails(newValue));

    }

    public void remplirTableauDArticles()
    {
        List<Article> articles = rayonDAO.recupererArticleDuRayon(rayon);

        filtreProduits.clear();
        filtreProduits.addAll(articles);

        produits.clear();
        produits.addAll((articles));

        tableau.setItems(filtreProduits);
    }

    public void afficherArticleDetails(Article article)
    {
        if(article != null)
        {
            detailsLabel.setText(article.getDetails());
            reservationLabel.setText(article.getQuantiteReserve()+"");
            prixLabel.setText(article.getPrix()+"");
        }
        else
        {
            detailsLabel.setText("");
            reservationLabel.setText("");
            prixLabel.setText("");
        }
    }

    private void mettreAJourFiltre() {
        filtreProduits.clear();

        for (Article a : produits) {
            if (matchFiltre(a)) {
                filtreProduits.add(a);
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

    private boolean matchFiltre(Article article) {
        String filtrerString = filtreTextField.getText();
        if (filtrerString == null || filtrerString.isEmpty()) {
            //Pas de filtre afficher tout les articles
            return true;
        }
        String lowerCaseFilterString = filtrerString.toLowerCase();

        if (article.getNom().toLowerCase().contains(lowerCaseFilterString)) {
            return true;
        }
        else{
            //Aucun match trouve
            return false;
        }
    }

    public void editerFormulaire(String titre, boolean bool) throws IOException {
        Article article = tableau.getSelectionModel().getSelectedItem();

        Object[] res = new BoiteAOutil().creerBoiteDialogue (titre, 1);

        //Recuperer le controleur lier à la vue
        BoiteDialogueArticleControleur controleur = ((FXMLLoader)res[0]).getController();
        //Modifier l'article
        if(bool)
        {
            controleur.remplirFormulaire(article);
        }
        else //Ajouter l'article
        {
            controleur.remplirFormulaire(null);
            controleur.setRayon(rayon);
        }
        //Indique au controler si c'est a modifier ou a ajouter
        controleur.setEstAModifier(bool);
        controleur.setArticle(article);
        controleur.setDialogStage((Stage)res[1]);
        controleur.setMonRayonControleur(this);


        // Afficher jusqu'à ce que l'utilisateur ferme la fenetre
        ((Stage)res[1]).showAndWait();
    }

    public void cliqueSurSupprimer() {
        Article article = tableau.getSelectionModel().getSelectedItem();
        rayonDAO.supprimerArticle(article);
        //Actualiser le tableauView
        remplirTableauDArticles();
    }

    public void cliqueSurModifier() throws IOException {

        if(tableau.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur modifier article");
            alert.setContentText("Veuillez selectionner un article dans la liste");
            alert.showAndWait();
        }
        else
        {
            editerFormulaire("Modifier article", true);
        }
    }

    public void cliqueSurAjouter() throws IOException {
        editerFormulaire("Ajouter article",false);

    }

    public void cliqueSurReserver() throws IOException {
        Article article = tableau.getSelectionModel().getSelectedItem();

        Object[] res = new BoiteAOutil().creerBoiteDialogue ("reserver " + article.getNom(), 2);

        //Recuperer le controleur lier à la vue
        BoiteDialogueReservationArticleControleur controleur = ((FXMLLoader)res[0]).getController();

        controleur.setArticle(article);
        controleur.setDialogStage((Stage)res[1]);
        controleur.setMonRayonControleur(this);

        // Afficher jusqu'à ce que l'utilisateur ferme la fenetre
        ((Stage)res[1]).showAndWait();
    }

    public void cliqueSurSearch() {
        mettreAJourFiltre();
    }

    public void cliqueSurSupprimerFiltre() {
         viderBarreRecherche();
         remplirTableauDArticles();
    }

    public void cliquerSurTransferer() throws IOException {
        Article article = tableau.getSelectionModel().getSelectedItem();

        Object[] res = new BoiteAOutil().creerBoiteDialogue ("Transferer " + article.getNom(), 3);

        //Recuperer le controleur lier à la vue
        BoiteDialogueTransfererArticleControleur controleur = ((FXMLLoader)res[0]).getController();

        controleur.setArticle(article);
        controleur.setDialogStage((Stage)res[1]);
        controleur.setMonRayonControleur(this);

        // Afficher jusqu'à ce que l'utilisateur ferme la fenetre
        ((Stage)res[1]).showAndWait();
    }

    public void vue()
    {
        //cas vendeur qui regarde son rayon
        if(type == 1)
        {
            transfererButton.setVisible(false);
            btnRetourEnArriere.setVisible(false);
        }

        //cas vendeur qui regarde un autre rayon
        if(type == 2)
        {
            btnAdd.setVisible(false);
            transfererButton.setVisible(false);
            modifierButton.setVisible(false);
            supprimerButton.setVisible(false);
            reserverButton.setVisible(false);
        }
    }

    public void cliqueSurRetourEnArriere() throws IOException {

        if(applicationPrincipaleControleur !=null)
        {
            applicationPrincipaleControleur.setEstSurAutresRayons(false);
            applicationPrincipaleControleur.cliqueSurAutresRayons();
        }
        else{
            applicationPrincipaleChefControleur.setEstSurGestionDesRayons(false);
            applicationPrincipaleChefControleur.cliqueSurGestionDesRayons();
        }

    }
}