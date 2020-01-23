package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Article;

import java.net.URL;
import java.util.ResourceBundle;
import controlleur.RayonDAO;
import controlleur.SetupEM;
import modele.Rayon;

public class BoiteDialogueArticleControleur implements Initializable {

    private Stage dialogStage;
    private MonRayonControleur monRayonControleur;
    private VisiterRayonControleur visiterRayonControleur;
    private boolean estAModifier;
    private Article article;
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

    public void setVisiterRayonControleur(VisiterRayonControleur visiterRayonControleur) {
        this.visiterRayonControleur = visiterRayonControleur;
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

    public void remplirFormulaire(Article article) {
        if (article != null) {
            nomTextF.setText(article.getNom());
            prixTextF.setText(article.getPrix() + "");
            detailsTextF.setText(article.getDetails());
            quantiteTextF.setText(article.getQuantite() + "");
        }
    }

    private boolean lesChampsSontValides() {
        String messageErreur = "";

        if (nomTextF.getText() == null || nomTextF.getText().length() == 0) {
            messageErreur += "Nom de l'article non valide!\n";
        }
        if (detailsTextF.getText() == null || detailsTextF.getText().length() == 0) {
            messageErreur += "Details non valide!\n";
        }

        if (prixTextF.getText() == null || prixTextF.getText().length() == 0) {
            messageErreur += "Prix non valide!\n";
        } else {
            try {
                System.out.println(Double.parseDouble(prixTextF.getText()));
            } catch (NumberFormatException e) {
                messageErreur += "Le prix doit être un nombre!\n";
            }
        }

        if (quantiteTextF.getText() == null || quantiteTextF.getText().length() == 0) {
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

            if (estAModifier == true) {
                Article articleModifie = new Article(article);
                //A FAIRE DANS UNE FONCTION
                articleModifie.setNom(nomTextF.getText());
                articleModifie.setDetails(detailsTextF.getText());
                articleModifie.setPrix(Double.parseDouble(prixTextF.getText()));
                articleModifie.setQuantite(Integer.parseInt(quantiteTextF.getText()));

                rayonDAO.modifierArticle(articleModifie);
                //Fermer le formulaire
                dialogStage.close();
            } else {
                rayonDAO.creerArticle(nomTextF.getText(), Integer.parseInt(quantiteTextF.getText()), detailsTextF.getText(), article.getRayonA(), Double.parseDouble(prixTextF.getText()));
                //Fermer le formulaire
                dialogStage.close();
            }
            //Actualiser le tableView dans tout les cas
            monRayonControleur.remplirTableauDArticles();
            monRayonControleur.viderBarreRecherche();
        }
    }

    public void cliqueSurAnnuler(ActionEvent actionEvent) {
        //Fermer le formulaire
        dialogStage.close();
    }

}

