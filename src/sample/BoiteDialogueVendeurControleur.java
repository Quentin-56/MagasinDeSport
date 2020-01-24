package sample;

import com.jfoenix.controls.JFXComboBox;
import controlleur.BoiteAOutil;
import controlleur.MagasinDAO;
import controlleur.SetupEM;
import controlleur.VendeurDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Article;
import modele.Rayon;
import modele.Vendeur;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BoiteDialogueVendeurControleur implements Initializable{


        private Stage dialogStage;
        private GestionDesVendeursControleur gestionDesVendeursControleur;
        private boolean estAModifier;
        private Vendeur vendeur;

        //private VendeurDAO vendeurDAO;
        @FXML
        private TextField prenomTextF;
        @FXML
        private TextField nomTextF;
        @FXML
        private ComboBox<String> nomRayonCombo;
        @FXML
        private TextField identifiantTextF;
        @FXML
        private TextField motDePasseTextF;

        //private ComboBox<String> nomRayonCombo;
        //private ObservableList<String> produits = FXCollections.observableArrayList();
        private MagasinDAO magasinDAO;

        public void setGestionDesVendeursControleur(GestionDesVendeursControleur gestionDesVendeursControleur) {
                this.gestionDesVendeursControleur = gestionDesVendeursControleur;
        }

        public void setEstAModifier(boolean estAModifier) {
                this.estAModifier = estAModifier;
        }

        public void setVendeur(Vendeur vendeur) {
                this.vendeur = vendeur;
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
                //produits.addAll(nomRayons);
                //nomRayonCombo = new ComboBox<String>(FXCollections
                 //       .observableArrayList(nomRayons));
                //nomRayonCombo.show();
                //nomRayonCombo.setVisible(true);*/
                nomRayonCombo.getItems().setAll(nomRayons);

        }

        /**
         * Rempli le formulaire avec les champs associe au vendeur si il n'est pas null, sinon les champs sont vides
         * @param vendeur
         */
        public void remplirFormulaire(Vendeur vendeur)
        {
                if(vendeur != null)
                {
                        prenomTextF.setText(vendeur.getPrenom());
                        nomTextF.setText(vendeur.getNom() + "");
                        //nomRayonCombo.setText(vendeur.getRayonV().getNom());
                        identifiantTextF.setText(vendeur.getIdentifiant() + "");
                        motDePasseTextF.setText(vendeur.getMotDePasse() + "");
                }
                else{
                        //Ne pas pré remplir les champs
                }
        }

        private boolean lesChampsSontValides() {
                String messageErreur = "";

                if (prenomTextF.getText() == null || prenomTextF.getText().length() < 2 || BoiteAOutil.checkCaractereSpeciaux(prenomTextF.getText()) == true) {
                        messageErreur += "Prenom de vendeur non valide!\n";
                }
                if (nomTextF.getText() == null || nomTextF.getText().length() < 2 || BoiteAOutil.checkCaractereSpeciaux(nomTextF.getText()) == true) {
                        messageErreur += "Nom de vendeur non valide!\n";
                }
                if (identifiantTextF.getText() == null || identifiantTextF.getText().length() == 0) {
                        messageErreur += "Identifiant non valide!\n";
                }
                if (motDePasseTextF.getText() == null || motDePasseTextF.getText().length() < 8 || BoiteAOutil.checkString(motDePasseTextF.getText()) == false) {
                        messageErreur += "Mot de passe non valide!\n";
                }
                if (nomRayonCombo.getSelectionModel().getSelectedItem() == null || nomRayonCombo.getSelectionModel().getSelectedItem().length() == 0) {
                        messageErreur += "Rayon non valide!\n";
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

                        //VendeurDAO.creerVendeur(nomTextF.getText(), prenomTextF.getText(), identifiantTextF.getText(), motDePasseTextF.getText(), MagasinDAO.trouverRayonAvecNom((String)nomRayonCombo.getSelectionModel().getSelectedItem()));


                        VendeurDAO vendeurDAO = new VendeurDAO();
                        vendeurDAO.setEntityManager(SetupEM.getEm());

                        if (estAModifier == true) {
                                Vendeur vendeurModifie = new Vendeur(vendeur.getNom(), vendeur.getPrenom(), vendeur.getIdentifiant(), vendeur.getMotDePasse(), vendeur.getRayonV());
                                //A FAIRE DANS UNE FONCTION
                                vendeurModifie.setNom(nomTextF.getText());
                                vendeurModifie.setPrenom(prenomTextF.getText());
                                vendeurModifie.setIdentifiant(identifiantTextF.getText());
                                vendeurModifie.setMotDePasse(motDePasseTextF.getText());
                                vendeurModifie.setRayonV(magasinDAO.trouverRayonAvecNom(nomRayonCombo.getSelectionModel().getSelectedItem()));

                                vendeurDAO.modifierVendeur(vendeurModifie);
                                //Fermer le formulaire
                                dialogStage.close();
                        } else {
                                vendeurDAO.creerVendeur(nomTextF.getText(), prenomTextF.getText(), identifiantTextF.getText(), motDePasseTextF.getText(), magasinDAO.trouverRayonAvecNom(nomRayonCombo.getSelectionModel().getSelectedItem()));
                                //Fermer le formulaire
                                dialogStage.close();
                        }
                        //Actualiser le tableView dans tout les cas
                        gestionDesVendeursControleur.remplirTableauDeVendeurs();
                        gestionDesVendeursControleur.viderBarreRecherche();
                }
        }

        public void cliqueSurAnnuler(ActionEvent actionEvent) {
                //Fermer le formulaire
                dialogStage.close();
        }


}
