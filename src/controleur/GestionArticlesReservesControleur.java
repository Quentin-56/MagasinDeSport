package controleur;

import controleur.dao.ChefMagasinDAO;
import controleur.dao.MagasinDAO;
import controleur.dao.SetupEM;
import controleur.dao.VendeurDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private boolean estUnVendeur;
    private Vendeur vendeur;
    private VendeurDAO vendeurDAO;

    public void setEstUnVendeur(boolean estUnVendeur)
    {
        this.estUnVendeur = estUnVendeur;
    }

    public void setVendeur(Vendeur vendeur)
    {
        this.vendeur = vendeur;
    }

    public GestionArticlesReservesControleur()
    {
        magasinDAO = new MagasinDAO();
        magasinDAO.setEntityManager(SetupEM.getEm());
        vendeurDAO = new VendeurDAO();
        vendeurDAO.setEntityManager(SetupEM.getEm());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        detailsLabel.setWrapText(true);

        if(estUnVendeur == true)
        {
            this.vendeur = vendeurDAO.trouverVendeurAvecIdentifiant(ConnexionControleur.getIdentifiant());
        }


        ChefMagasinDAO chefMagasinDAO = new ChefMagasinDAO();
        chefMagasinDAO.setEntityManager(SetupEM.getEm());
        this.chefMagasin = chefMagasinDAO.recupererChefMagasin();



        //Specifier quel champ de l'objet produit devra être utilisé pour la colonne
        colNom.setCellValueFactory(new PropertyValueFactory("nom"));
        colQuantiteReservee.setCellValueFactory(new PropertyValueFactory("quantiteReserve"));


        remplirTableauDArticlesReserves(vendeur);



        //Nettoyer les details
        afficherArticleDetails(null);

        tableauArticlesReserves.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> afficherArticleDetails(newValue));
    }


    public void remplirTableauDArticlesReserves(Vendeur vendeur)
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

        //cas du vendeur
        if(estUnVendeur == true)
        {
                articlesReserves = new ArrayList<Article>();
                for(int j = 0; j < vendeur.getRayonV().getListeArticles().size(); ++ j)
                {
                    if(vendeur.getRayonV().getListeReservationArticle().get(j).getQuantiteReserve() > 0)
                    {
                        articlesReserves.add(vendeur.getRayonV().getListeReservationArticle().get(j));
                    }
                }
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


    public void cliqueSurAjouter(ActionEvent actionEvent) throws IOException {
        if(estUnVendeur == true)
        {
            this.vendeur = vendeurDAO.trouverVendeurAvecIdentifiant(ConnexionControleur.getIdentifiant());
        }

        Article article = tableauArticlesReserves.getSelectionModel().getSelectedItem();

        if(article != null)
        {
            //si la quantite de l article est superieure ou egale a 1
            if(article.getQuantite() >= 1)
            {
                article.setQuantite(article.getQuantite() - 1);
                article.setQuantiteReserve(article.getQuantiteReserve() + 1);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajout");
                alert.setContentText("augmentation du stock reserve de 1");
                alert.showAndWait();

                remplirTableauDArticlesReserves(vendeur);
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
        if(estUnVendeur == true)
        {
            this.vendeur = vendeurDAO.trouverVendeurAvecIdentifiant(ConnexionControleur.getIdentifiant());
        }
        Article article = tableauArticlesReserves.getSelectionModel().getSelectedItem();

        if(article != null)
        {
            article.setQuantite(article.getQuantite() + 1);
            article.setQuantiteReserve(article.getQuantiteReserve() - 1);

            remplirTableauDArticlesReserves(vendeur);
        }
    }
}
