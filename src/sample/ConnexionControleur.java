package sample;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controlleur.ConnexionDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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

    private static String identifiant;

    private static final String COMPLEX_PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$";

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(COMPLEX_PASSWORD_REGEX);

    public ConnexionControleur(){


        /*btn.pressedProperty().addListener((observable, wasPressed, pressed) -> {
            System.out.println("changed");
            if (pressed) {
                System.out.println("hello");
            } else {
                System.out.println("bye");
            }
        });*/
    }
    
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
                //Retenir l'identifiant
                ConnexionControleur.identifiant = identifiant;
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