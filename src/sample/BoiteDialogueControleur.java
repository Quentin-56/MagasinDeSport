package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import modele.Article;

import java.net.URL;
import java.util.ResourceBundle;

public class BoiteDialogueControleur implements Initializable {

    private Article article;
    @FXML
    private TextField nomTextF;
    @FXML
    private TextField prixTextF;
    @FXML
    private TextField detailsTextF;
    @FXML
    private TextField quantiteTextF;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * Rempli le formulaire avec les champs associe à l'article si il n'est pas null, sinon les champs sont vides
     * @param article
     */
    public void remplirFormulaire(Article article)
    {
        if(article != null)
        {
            nomTextF.setText(article.getNom());
            prixTextF.setText(article.getPrix() + "");
            detailsTextF.setText(article.getDetails());
            quantiteTextF.setText(article.getQuantite() + "");
        }
        else{
            //Ne pas pré remplir les champs
        }
    }

    public void cliqueSurValider(ActionEvent actionEvent) {

    }

    public void cliqueSurAnnuler(ActionEvent actionEvent) {
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
