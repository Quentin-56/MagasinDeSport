package sample;

import controlleur.MagasinDAO;
import controlleur.RayonDAO;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.Article;
import modele.Rayon;
import modele.Vendeur;
import org.controlsfx.control.textfield.CustomTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    private RayonDAO rayonDAO;

    private ObservableList<Article> produits = FXCollections.observableArrayList();
    private ObservableList<Article> filtreProduits = FXCollections.observableArrayList();

    //private Vendeur vendeur;

    private boolean estUnVendeur;
    private Rayon rayon;

    public MonRayonControleur()
    {
        rayonDAO = new RayonDAO();
        rayonDAO.setEntityManager(SetupEM.getEm());
    }

    public void setEstUnVendeur(boolean estUnVendeur)
    {
        this.estUnVendeur = estUnVendeur;
    }

    public void setRayon(Rayon rayon)
    {
        this.rayon = rayon;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        /*if(estUnVendeur == true)
        {
            VendeurDAO vendeurDAO = new VendeurDAO();
            vendeurDAO.setEntityManager(SetupEM.getEm());
            this.vendeur = vendeurDAO.trouverVendeurAvecIdentifiant(ConnexionControleur.getIdentifiant());
        }*/
        System.out.println("bundle" + resourceBundle);

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
        List<Article> articles = new ArrayList<>();
        //if(estUnVendeur == true)
       // {
       //     articles = rayonDAO.recupererArticleDuRayon(vendeur.getRayonV());
       // }
       // else
        //{
            articles = rayonDAO.recupererArticleDuRayon(rayon);
        //}
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

        //Charger le fichir fmxl
        FXMLLoader loader = new FXMLLoader(getClass().getResource("boiteDialogueArticle.fxml"));
        Parent parent = loader.load();

        // Creer le stage pour la boite de dialogue
        Stage dialogStage = new Stage();
        dialogStage.setTitle(titre);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(Main.getPrimaryStage());
        Scene scene = new Scene(parent);
        dialogStage.setScene(scene);
        //Recuperer le controleur lier à la vue
        BoiteDialogueArticleControleur controleur = loader.getController();
        //Modifier l'article
        if(bool)
        {
            controleur.remplirFormulaire(article);
        }
        else //Ajouter l'article
        {
            controleur.remplirFormulaire(null);
            //controleur.setRayon(vendeur.getRayonV());
        }
        //Indique au controler si c'est a modifier ou a ajouter
        controleur.setEstAModifier(bool);
        controleur.setArticle(article);
        controleur.setDialogStage(dialogStage);
        controleur.setMonRayonControleur(this);

        // Afficher jusqu'à ce que l'utilisateur ferme la fenetre
        dialogStage.showAndWait();
    }

    public void cliqueSurSupprimer(ActionEvent actionEvent) {
        Article article = tableau.getSelectionModel().getSelectedItem();
        rayonDAO.supprimerArticle(article);
        //Actualiser le tableauView
        remplirTableauDArticles();
    }

    public void cliqueSurModifier(ActionEvent actionEvent) throws IOException {

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

    public void cliqueSurAjouter(ActionEvent actionEvent) throws IOException {
        editerFormulaire("Ajouter article",false);

    }

    public void cliqueSurReserver(ActionEvent actionEvent) {
    }

    public void cliqueSurSearch(ActionEvent actionEvent) {
        mettreAJourFiltre();
    }

    public void cliqueSurSupprimerFiltre(ActionEvent actionEvent) {
         viderBarreRecherche();
         remplirTableauDArticles();
    }

    public void cliquerSurTransferer(ActionEvent actionEvent)
    {

    }

    public void vue()
    {
        if(estUnVendeur == true)
        {
            transfererButton.setVisible(false);
        }
    }
}

