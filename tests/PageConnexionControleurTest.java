/*import controlleur.SetupEM;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import sample.Main;

public class PageConnexionControleurTest extends ApplicationTest {
    @Override
    public void start(Stage stage) throws Exception {
        new SetupEM();
        Parent mainNode = FXMLLoader.load(Main.class.getResource("connexion.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }
    @Before
    public void setUp () throws Exception{

    }
    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
    @Test
    public void cliquerSurSeConnecterSansNomUtilisateur(){

        clickOn("#textField");
        write("");
    }
}*/
