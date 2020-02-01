package controleur;

import controleur.dao.MagasinDAO;
import controleur.dao.SetupEM;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Rayon;

public class BoiteDialogueRayonControleur {

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

    public void cliqueSurValider() {
        if(lesChampsSontValides()) {
            MagasinDAO magasinDAO = new MagasinDAO();
            magasinDAO.setEntityManager(SetupEM.getEm());

            if (estAModifier == true) {          
                rayon.setNom(nomTextF.getText());
                magasinDAO.modifierRayon(rayon);

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

    public void cliqueSurAnnuler() {
        //Fermer le formulaire
        dialogStage.close();
    }
}
