package sample;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class ConnexionControleur
{
    @FXML
    JFXPasswordField passwordField;
    @FXML
    JFXCheckBox checkBox;

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

        Parent root = FXMLLoader.load(getClass().getResource("applicationPrincipale.fxml"));

        Main.getPrimaryStage().setScene(new Scene(root));

    }
}