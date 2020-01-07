package sample;

import controlleur.RayonDAO;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.Article;
import modele.Vendeur;

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


    private ObservableList<Article> produits = FXCollections.observableArrayList();

    private Vendeur vendeur;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        this.vendeur = VendeurDAO.trouverVendeurAvecIdentifiant(ConnexionControleur.getIdentifiant());

        //Specifier quel champ de l'objet produit devra être utilisé pour la colonne
        colNom.setCellValueFactory(new PropertyValueFactory("nom"));
        colQuantite.setCellValueFactory(new PropertyValueFactory("quantite"));

        List<Article> articles = RayonDAO.recupererArticleDuRayon(vendeur.getRayonV());
        produits.addAll(articles);

        tableau.setItems(produits);

        //Nettoyer les details
        afficherArticleDetails(null);


        tableau.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> afficherArticleDetails(newValue));
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

    public void cliqueSurSupprimer(ActionEvent actionEvent) {
    }

    public void cliqueSurModifier(ActionEvent actionEvent) throws IOException {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("boiteDialogue.fxml"));
        //loader.setLocation(applicationPrincipaleControleur.class.getResource("boiteDialogue.fxml"));
        //AnchorPane page = (AnchorPane) loader.load();
        Parent parent = loader.load();



        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(Main.getPrimaryStage());
        Scene scene = new Scene(parent);
        dialogStage.setScene(scene);


        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
    }

    public void cliqueSurReserver(ActionEvent actionEvent) {
    }
}
