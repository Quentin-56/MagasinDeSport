package sample;

import controlleur.RayonDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import modele.Article;
import modele.Rayon;
import modele.Vendeur;

import java.net.URL;
import java.util.ResourceBundle;

public class BoiteDialogueControleur implements Initializable {

    private RayonDAO rayonDAO;
    private Article article;
    private Vendeur vendeur;
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

    public void remplirFormulaire(Rayon rayon)
    {
        if(article != null)
        {
            nomTextF.setText(article.getNom());
            prixTextF.setText(article.getPrix() + "");
            detailsTextF.setText(article.getDetails());
            quantiteTextF.setText(article.getQuantite() + "");
        }

        else
        {
            article = new Article();
            article.setRayonA(rayon);
        }
    }

    //num permet de savoir si on ajoute un article, modifie un article, ajoute un article, ...
    public void cliqueSurValider(ActionEvent actionEvent/*, int num*/) {
        //if(num == 0)
        //{
            rayonDAO.creerArticle(nomTextF.getText(), Integer.parseInt(quantiteTextF.getText()), detailsTextF.getText(), article.getRayonA(), Double.parseDouble(prixTextF.getText()));
        //}

    }

    public void cliqueSurAnnuler(ActionEvent actionEvent) {
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
