package sample;

import controlleur.MagasinDAO;
import controlleur.RayonDAO;
import controlleur.SetupEM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Article;
import modele.Magasin;
import modele.Rayon;

import java.net.URL;
import java.util.ResourceBundle;

public class BoiteDialogueRayonControleur implements Initializable {

    private Stage dialogStage;
    private GestionDesRayonsControleur gestionDesRayonsControleur;
    private boolean estAModifier;
    private Rayon rayon;
    @FXML
    private TextField nomTextF;

    public void setGestionDesRayonsControleur(GestionDesRayonsControleur gestionDesRayonsControleur) {
        this.gestionDesRayonsControleur = gestionDesRayonsControleur;
    }

    public void setEstAModifier(boolean estAModifier) {
        this.estAModifier = estAModifier;
    }

    public void setRayon(Rayon rayon) {
        this.rayon = rayon;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void remplirFormulaire(Rayon rayon) {
        if (rayon != null) {
            nomTextF.setText(rayon.getNom());
        }
    }

    private boolean lesChampsSontValides() {
        String messageErreur = "";

        if (nomTextF.getText() == null || nomTextF.getText().length() == 0) {
            messageErreur += "Nom de rayon non valide!\n";
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
            MagasinDAO magasinDAO = new MagasinDAO();
            magasinDAO.setEntityManager(SetupEM.getEm());

            if (estAModifier == true) {
                Rayon rayonModifie = new Rayon(rayon);
                //A FAIRE DANS UNE FONCTION
                rayonModifie.setNom(nomTextF.getText());

                magasinDAO.modifierRayon(rayonModifie);

                //Fermer le formulaire
                dialogStage.close();
            } else {
                magasinDAO.creerRayon(nomTextF.getText());

                //Fermer le formulaire
                dialogStage.close();
            }
            //Actualiser le tableView dans tout les cas
            gestionDesRayonsControleur.remplirTableauDeRayons();
            gestionDesRayonsControleur.viderBarreRecherche();
        }
    }

    public void cliqueSurAnnuler(ActionEvent actionEvent) {
        //Fermer le formulaire
        dialogStage.close();
    }
}
