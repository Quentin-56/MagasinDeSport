package sample;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import java.util.regex.Pattern;

public class FormulaireController
{
    @FXML
    JFXPasswordField passwordField;
    @FXML
    JFXCheckBox checkBox;

    private static final String COMPLEX_PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$";

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile(COMPLEX_PASSWORD_REGEX);


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

    public void clickOnLogIn(ActionEvent actionEvent) {
        String password = passwordField.getText();
        System.out.println(passwordIsValid(password));


    }
}
