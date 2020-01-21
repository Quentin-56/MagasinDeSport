package sample;

import controlleur.RayonDAO;
import controlleur.SetupEM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Article;
import modele.Rayon;
import java.net.URL;
import java.util.ResourceBundle;

public class BoiteDialogueControleur implements Initializable {

    private Stage dialogStage;
    private Article article;
    private MonRayonControleur monRayonControleur;
    private boolean estAModifier;

    @FXML
    private TextField nomTextF;
    @FXML
    private TextField prixTextF;
    @FXML
    private TextField detailsTextF;
    @FXML
    private TextField quantiteTextF;

    public void setMonRayonControleur(MonRayonControleur monRayonControleur) {
        this.monRayonControleur = monRayonControleur;
    }

    public void setEstAModifier(boolean estAModifier) {
        this.estAModifier = estAModifier;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

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

        RayonDAO rayonDAO = new RayonDAO();
        rayonDAO.setEntityManager(SetupEM.getEm());

        if(estAModifier == true)
        {
            Article articleModifie = new Article(article);
            //A FAIRE DANS UNE FONCTION
            articleModifie.setNom(nomTextF.getText());
            articleModifie.setDetails(detailsTextF.getText());
            articleModifie.setPrix(Double.parseDouble(prixTextF.getText()));
            articleModifie.setQuantite(Integer.parseInt(quantiteTextF.getText()));

            rayonDAO.modifierArticle(articleModifie);
            //Fermer le formulaire
            dialogStage.close();
        }
        else
        {
            rayonDAO.creerArticle(nomTextF.getText(),Integer.parseInt(quantiteTextF.getText()),detailsTextF.getText(),article.getRayonA(),Double.parseDouble(prixTextF.getText()));
            //Fermer le formulaire
            dialogStage.close();
        }
        //Actualiser le tableView dans tout les cas
        monRayonControleur.remplirTableauDArticles();
    }

    public void cliqueSurAnnuler(ActionEvent actionEvent) {
        //Fermer le formulaire
        dialogStage.close();
    }
}