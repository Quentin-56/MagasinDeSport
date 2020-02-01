package controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import modele.Article;

import java.net.URL;
import java.util.ResourceBundle;
import controleur.dao.RayonDAO;
import controleur.dao.SetupEM;
import modele.Rayon;

public class BoiteDialogueArticleControleur implements Initializable {

    private Stage dialogStage;
    private MonRayonControleur monRayonControleur;
    private boolean estAModifier;
    private Article article;
    private Rayon rayon;

    @FXML
    private TextField nomTextF;
    @FXML
    private TextField prixTextF;
    @FXML
    private TextArea detailsTextF;
    @FXML
    private TextField quantiteTextF;

    public void setRayon(Rayon rayon) {
        this.rayon = rayon;
    }

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
        detailsTextF.setWrapText(true);

        //Limiter les details à moins de
        TextFormatter<Object> limitSize = new TextFormatter<>(change ->
                change.getControlNewText().length() <= 255 ? change : null);

        detailsTextF.setTextFormatter(limitSize);
    }

    public void remplirFormulaire(Article article) {
        if (article != null) {
            nomTextF.setText(article.getNom());
            prixTextF.setText(article.getPrix() + "");
            detailsTextF.setText(article.getDetails());
            quantiteTextF.setText(article.getQuantite() + "");
        }
    }

    private boolean lesChampsSontValides(){
        String messageErreur = "";

        if (nomTextF.getText() == null || nomTextF.getText().length() == 0 || nomTextF.getText().length() > 255) {
            messageErreur += "Nom de l'article non valide!\n";
        }
        if (detailsTextF.getText() == null || detailsTextF.getText().length() == 0) {
            messageErreur += "Details non valide!\n";
        }

        if (prixTextF.getText() == null || prixTextF.getText().length() == 0) {
            messageErreur += "Prix non valide!\n";
        } else {
            try {
                if(Double.parseDouble(prixTextF.getText())<0)
                {
                    messageErreur+= "Le prix doit être positif!\n";
                }
            } catch (NumberFormatException e) {
                messageErreur += "Le prix doit être un nombre!\n";
            }
        }

        if (quantiteTextF.getText() == null || quantiteTextF.getText().length() == 0) {
            messageErreur += "Quantité non valide!\n";
        }else {
            try {
                    if(Integer.parseInt(quantiteTextF.getText())<0)
                    {
                        messageErreur+= "La quantite doit être positive!\n";
                    }
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

    public void cliqueSurValider() {
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
                rayonDAO.creerArticle(nomTextF.getText(), Integer.parseInt(quantiteTextF.getText()), detailsTextF.getText(), rayon, Double.parseDouble(prixTextF.getText()));
                //Fermer le formulaire
                dialogStage.close();
            }
            //Actualiser le tableView dans tout les cas
            monRayonControleur.remplirTableauDArticles();
            monRayonControleur.viderBarreRecherche();
        }
    }

    public void cliqueSurAnnuler() {
        //Fermer le formulaire
        dialogStage.close();
    }

}

