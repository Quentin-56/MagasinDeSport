package sample;

import controlleur.*;
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
import modele.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GestionArticlesReservesControleur implements Initializable {

    @FXML
    private TableView<Article> tableauArticlesReserves;
    @FXML
    private TableColumn<Article, String> colNom;
    @FXML
    private TableColumn<Article, String> colQuantiteReservee;
    @FXML
    private Label rayonLabel;
    @FXML
    private Label detailsLabel;
    @FXML
    private Label prixLabel;

    private MagasinDAO magasinDAO;

    private ObservableList<Article> produits = FXCollections.observableArrayList();
    private ChefMagasin chefMagasin;
    //private Vendeur vendeur;

    public GestionArticlesReservesControleur()
    {
        magasinDAO = new MagasinDAO();
        magasinDAO.setEntityManager(SetupEM.getEm());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //this.vendeur = VendeurDAO.trouverVendeurAvecIdentifiant(ConnexionControleur.getIdentifiant());
        ChefMagasinDAO chefMagasinDAO = new ChefMagasinDAO();
        chefMagasinDAO.setEntityManager(SetupEM.getEm());
        this.chefMagasin = chefMagasinDAO.recupererChefMagasin();

        //Specifier quel champ de l'objet produit devra être utilisé pour la colonne
        colNom.setCellValueFactory(new PropertyValueFactory("nom"));
        colQuantiteReservee.setCellValueFactory(new PropertyValueFactory("quantiteReserve"));

        remplirTableauDArticlesReserves();

        //Nettoyer les details
        afficherArticleDetails(null);

        tableauArticlesReserves.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> afficherArticleDetails(newValue));
    }


    public void remplirTableauDArticlesReserves()
    {
        List<Article> articlesReserves = new ArrayList<Article>();

        List<Rayon> rayons = magasinDAO.recupererRayon();
        for (int i = 0; i < rayons.size(); ++i) {
            for(int j = 0; j < rayons.get(i).getListeReservationArticle().size(); ++j)
            {
                //si la quantite reservee de l article est supereure stricte a 0 alors on ajoute l article a la liste articlesReserves
                if(rayons.get(i).getListeReservationArticle().get(j).getQuantiteReserve() > 0)
                {
                    articlesReserves.add(rayons.get(i).getListeReservationArticle().get(j));
                }
            }

        }

        for(int i = 0; i < articlesReserves.size(); ++i)
        {
            System.out.println("nom " + articlesReserves.get(i).getNom());
            System.out.println("quantite reservee " + articlesReserves.get(i).getQuantiteReserve());
        }

        produits.clear();
        produits.addAll(articlesReserves);

        tableauArticlesReserves.setItems(produits);
    }

    public void afficherArticleDetails(Article article)
    {
        if(article != null)
        {
            detailsLabel.setText(article.getDetails());
            rayonLabel.setText(article.getRayonA().getNom()+"");
            prixLabel.setText(article.getPrix()+"");
        }
        else
        {
            detailsLabel.setText("");
            rayonLabel.setText("");
            prixLabel.setText("");
        }
    }


    /*public void editerFormulaire(String titre, boolean bool) throws IOException {
        Article article = tableau.getSelectionModel().getSelectedItem();

        //Charger le fichir fmxl
        FXMLLoader loader = new FXMLLoader(getClass().getResource("boiteDialogue.fxml"));
        Parent parent = loader.load();

        // Creer le stage pour la boite de dialogue
        Stage dialogStage = new Stage();
        dialogStage.setTitle(titre);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(Main.getPrimaryStage());
        Scene scene = new Scene(parent);
        dialogStage.setScene(scene);
        //Recuperer le controleur lier à la vue
        BoiteDialogueControleur controleur = loader.getController();
        //Modifier l'article
        if(bool == true)
        {
            controleur.remplirFormulaire(article);
        }
        else
        {
            controleur.remplirFormulaire(null);
        }
        //Indique au controler si c'est a modifier ou a ajouter
        controleur.setEstAModifier(bool);
        controleur.setArticle(article);
        controleur.setDialogStage(dialogStage);
        controleur.setMonRayonControleur(this);

        // Afficher jusqu'à ce que l'utilisateur ferme la fenetre
        dialogStage.showAndWait();
    }*/


    public void cliqueSurAjouter(ActionEvent actionEvent) throws IOException {

        Article article = tableauArticlesReserves.getSelectionModel().getSelectedItem();

        if(article != null)
        {
            //si la quantite de l article est superieure ou egale a 1
            if(article.getQuantite() >= 1)
            {
                article.setQuantite(article.getQuantite() - 1);
                article.setQuantiteReserve(article.getQuantiteReserve() + 1);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajout");
                alert.setContentText("augmentation du stock reserve de 1");
                alert.showAndWait();

                remplirTableauDArticlesReserves();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("l article n est plus en stock");
                alert.showAndWait();
            }
        }

    }

    public void cliqueSurRetirer(ActionEvent actionEvent) throws IOException {
        Article article = tableauArticlesReserves.getSelectionModel().getSelectedItem();

        if(article != null)
        {
            //si la quantite reservee de l article est superieure stricte a 1
            //if(article.getQuantiteReserve() > 1)
            //{
            article.setQuantite(article.getQuantite() + 1);
            article.setQuantiteReserve(article.getQuantiteReserve() - 1);

            remplirTableauDArticlesReserves();
            //}

        /*else
        {
            article.setQuantite(article.getQuantite() + 1);
            article.setQuantiteReserve(article.getQuantiteReserve() - 1);

            //on enleve l article de la liste des articles reserves de son rayon
            article.getRayonA().getListeReservationArticle().remove(article);
        }*/
        }


    }


    public void cliqueSurSearch(ActionEvent actionEvent) {
        System.out.println("Je fais une recherche");
    }
}
