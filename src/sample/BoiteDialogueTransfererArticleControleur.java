package sample;

import controlleur.MagasinDAO;
import controlleur.RayonDAO;
import controlleur.SetupEM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Article;
import modele.Rayon;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BoiteDialogueTransfererArticleControleur implements Initializable {

    private Stage dialogStage;
    private MonRayonControleur monRayonControleur;
    private Article article;
    private MagasinDAO magasinDAO;
    @FXML
    private ComboBox<String> nomRayonCombo;


    public void setMonRayonControleur(MonRayonControleur monRayonControleur) {
        this.monRayonControleur = monRayonControleur;
    }


    public void setArticle(Article article) {
        this.article = article;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        magasinDAO = new MagasinDAO();
        magasinDAO.setEntityManager(SetupEM.getEm());

        List<Rayon> rayons = magasinDAO.recupererRayon();

        List<String> nomRayons = new ArrayList<String>();
        for(int i = 0; i < rayons.size(); ++i)
        {
            nomRayons.add(rayons.get(i).getNom());
        }

        nomRayonCombo.getItems().setAll(nomRayons);
    }


    public void cliqueSurValider(ActionEvent actionEvent) {
        RayonDAO rayonDAO = new RayonDAO();
        rayonDAO.setEntityManager(SetupEM.getEm());

        rayonDAO.transfererUnArticle(article, magasinDAO.trouverRayonAvecNom(nomRayonCombo.getSelectionModel().getSelectedItem()));

        dialogStage.close();

        //Actualiser le tableView
        monRayonControleur.remplirTableauDArticles();
        monRayonControleur.viderBarreRecherche();
    }

    public void cliqueSurAnnuler(ActionEvent actionEvent) {
        //Fermer le formulaire
        dialogStage.close();
    }
}
