package sample;

import controlleur.BoiteAOutil;
import controlleur.ChefMagasinDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.ChefMagasin;
import java.net.URL;
import java.util.ResourceBundle;

public class BoiteDialogueParametresControleur implements Initializable {

    private Stage dialogStage;
    private ApplicationPrincipaleChefControleur applicationPrincipaleChefControleur1;
    private ChefMagasin chefMagasin;
    private ChefMagasinDAO chefMagasinDAO;
    private Label nomLabel;
    @FXML
    private TextField nomTextF;
    @FXML
    private TextField prenomTextF;
    @FXML
    private TextField motDePasseTextF;

    public void setNomLabel(Label nomLabel) {
        this.nomLabel = nomLabel;
    }

    public void setApplicationPrincipaleChefControleur(ApplicationPrincipaleChefControleur applicationPrincipaleChefControleur1) {
        this.applicationPrincipaleChefControleur1 = applicationPrincipaleChefControleur1;
    }

    public void setChefMagasin(ChefMagasin chefMagasin) {
        this.chefMagasin = chefMagasin;
    }

    public void setChefMagasinDAO(ChefMagasinDAO chefMagasinDAO) {
        this.chefMagasinDAO = chefMagasinDAO;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chefMagasinDAO = new ChefMagasinDAO();
    }

    public void remplirFormulaire()
    {
        nomTextF.setText(chefMagasin.getNom());
        prenomTextF.setText(chefMagasin.getPrenom() + "");
        motDePasseTextF.setText(chefMagasin.getMotDePasse());
    }

    private boolean lesChampsSontValides() {
        String messageErreur = "";


        if (motDePasseTextF.getText() == null || motDePasseTextF.getText().length() < 8 || BoiteAOutil.checkString(motDePasseTextF.getText()) == false) {
            messageErreur += "Mot de passe non valide!\n";
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
            chefMagasin.setNom(nomTextF.getText());
            chefMagasin.setPrenom(prenomTextF.getText());
            chefMagasin.setMotDePasse(motDePasseTextF.getText());

            chefMagasinDAO.modifierChefMagasin(chefMagasin);
            nomLabel.setText(chefMagasin.getPrenom()+" "+chefMagasin.getNom());
            //Fermer le formulaire
            dialogStage.close();
        }

    }

    public void cliqueSurAnnuler(ActionEvent actionEvent) {
        //Fermer le formulaire
        dialogStage.close();
    }
}
