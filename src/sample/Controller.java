package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller  {

    private Node[] nodes = new Node[10];
    private int compteur = 0;
    private ArrayList<Integer> tableau = new ArrayList<Integer>();

   //public Node[] nodes;

    @FXML
    private Label label;

    @FXML
    private VBox pnl_scroll;

    @FXML
    private void handleButtonAction(MouseEvent event) {
        Produit p = new Produit("echarpe",3);
        refreshNodes(p);
    }

   /* @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        refreshNodes();
    }*/

    private void refreshNodes(Produit p)
    {
        //pnl_scroll.getChildren().clear();

        //for(int i = 0; i<compteur; i++)
            try {
                nodes[compteur] = (Node) FXMLLoader.load(getClass().getResource("Item.fxml"));
                pnl_scroll.getChildren().add(nodes[compteur]);

            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        compteur++;
    }
}
