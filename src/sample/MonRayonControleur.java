package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class MonRayonControleur implements Initializable {
    @FXML
    private TableView<Article> tableau;
    @FXML
    private TableColumn<Article, String> colNom;
    @FXML
    private TableColumn<Article, String> colPrix;

    private ObservableList<Article> produits = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }
}
