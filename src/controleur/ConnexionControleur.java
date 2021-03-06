package controleur;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controleur.dao.ConnexionDAO;
import controleur.dao.SetupEM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConnexionControleur implements Initializable
{
    @FXML
    private Button eyeBtn;
    @FXML
    private JFXTextField textField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXCheckBox checkBox;
    @FXML
    private Label utilisateurLabel;
    @FXML
    private Label mdpLabel;

    private Boolean mdpEstCache = true;

    private ConnexionDAO connexion;

    private static String identifiant;

    public ConnexionControleur(){
        connexion = new ConnexionDAO();
        connexion.setEntityManager(SetupEM.getEm());
    }

    public void clickOnLogIn(ActionEvent actionEvent) throws IOException {
        String password = passwordField.getText();
        String identifiant = textField.getText();

        if(connexion.verifierIdentifiant(identifiant) == true)
        {
            utilisateurLabel.setText("");
            if(connexion.verifierMotDePasse(identifiant, password) == true)
            {
                //Retenir l'identifiant
                ConnexionControleur.identifiant = identifiant;
                mdpLabel.setText("");
                Parent root = null;
                if(connexion.leChefSeConnecte(identifiant) == true)
                {
                    root = FXMLLoader.load(getClass().getResource("../ressources/applicationPrincipaleChef.fxml"));
                    Main.getPrimaryStage().setScene(new Scene(root));
                }
                else
                {
                    //Est un vendeur
                    root = FXMLLoader.load(getClass().getResource("../ressources/applicationPrincipale.fxml"));
                    Main.getPrimaryStage().setScene(new Scene(root));
                }
            }
            else
            {
                //Affichage si mot de passe incorrect
                mdpLabel.setText("Mot de passe incorrect");
                //Effacer le mot de passe incorrect
                passwordField.setText("");
            }
        }
        else
        {
            utilisateurLabel.setText("Nom d'utilisateur incorrect");
        }
    }

    //GETTERS ET SETTERS

    public static String getIdentifiant() {
        return identifiant;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        eyeBtn.pressedProperty().addListener((observable, wasPressed, pressed) -> {
            if (pressed) {
                if(!passwordField.getText().isEmpty())
                {
                    passwordField.setPromptText(passwordField.getText());
                    passwordField.setText("");
                    mdpEstCache = false;
                }
            } else {
                if(!mdpEstCache)
                {
                    passwordField.setText(passwordField.getPromptText());
                    passwordField.setPromptText("Mot de passe");
                    mdpEstCache = true;
                }
            }});
    }
}