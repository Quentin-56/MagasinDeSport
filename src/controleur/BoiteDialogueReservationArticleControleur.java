package controleur;

import controleur.dao.RayonDAO;
import controleur.dao.SetupEM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Article;

import java.net.URL;
import java.util.ResourceBundle;

public class BoiteDialogueReservationArticleControleur implements Initializable {

    private Stage dialogStage;
    private MonRayonControleur monRayonControleur;
    private Article article;
    @FXML
    private TextField quantiteTextF;


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
    }


    private boolean lesChampsSontValides() {
        String messageErreur = "";


        if (quantiteTextF.getText() == null || quantiteTextF.getText().length() == 0 || Integer.parseInt(quantiteTextF.getText()) > article.getQuantite() || Integer.parseInt(quantiteTextF.getText()) < 0) {
            messageErreur += "Quantité non valide!\n";
        }else {
            try {
                Integer.parseInt(quantiteTextF.getText());
            } catch (NumberFormatException e) {
                messageErreur += "La quantite doit être un nombre!\n";
            }
        }

        if (messageErreur.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champs invalides");
            alert.setHeaderText("Veuillez corriger les champs incorrects");
            alert.setContentText(messageErreur);

            alert.showAndWait();

            return false;
        }
    }

    public void cliqueSurValider(ActionEvent actionEvent) {
        if(lesChampsSontValides()) {
            RayonDAO rayonDAO = new RayonDAO();
            rayonDAO.setEntityManager(SetupEM.getEm());

            rayonDAO.reserverUnArticle(article, Integer.parseInt(quantiteTextF.getText()));

            dialogStage.close();

            //Actualiser le tableView
            monRayonControleur.remplirTableauDArticles();
            monRayonControleur.viderBarreRecherche();
        }
    }

    public void cliqueSurAnnuler(ActionEvent actionEvent) {
        //Fermer le formulaire
        dialogStage.close();
    }
}
