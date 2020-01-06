package sample;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controlleur.ConnexionDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class ConnexionControleur
{
    @FXML
    JFXTextField textField;
    @FXML
    JFXPasswordField passwordField;
    @FXML
    JFXCheckBox checkBox;
    @FXML
    Label utilisateurLabel;
    @FXML
    Label mdpLabel;

    private static final String COMPLEX_PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$";

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile(COMPLEX_PASSWORD_REGEX);

    public ConnexionControleur(
    ){ }

    public boolean passwordIsValid(String password)
    {
        if (PASSWORD_PATTERN.matcher(password).matches())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void clickOnLogIn(ActionEvent actionEvent) throws IOException {
        String password = passwordField.getText();
        String identifiant = textField.getText();

        if(ConnexionDAO.verifierIdentifiant(identifiant) == true)
        {
            utilisateurLabel.setText("");
            if(ConnexionDAO.verifierMotDePasse(identifiant, password) == true)
            {
                mdpLabel.setText("");
                if(ConnexionDAO.leChefSeConnecte(identifiant) == true)
                {

                }
                else
                {
                    Parent root = FXMLLoader.load(getClass().getResource("applicationPrincipale.fxml"));
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
}