package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class applicationPrincipaleControleur {

    private Node[] nodes = new Node[1];
    private boolean estSurMonRayon = false;
    private boolean estSurAutresRayons = false;
    private boolean estSurArticlesReserves = false;

   //public Node[] nodes;

    @FXML
    private Label label;

    @FXML
    private VBox pnl_scroll;

   /* @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        refreshNodes();
    }*/

    /*private void refreshNodes(Produit p)
    {
        //pnl_scroll.getChildren().clear();

        //for(int i = 0; i<compteur; i++)
            try {
                nodes[compteur] = (Node) FXMLLoader.load(getClass().getResource("Item.fxml"));
                pnl_scroll.getChildren().add(nodes[compteur]);

            } catch (IOException ex) {
                Logger.getLogger(applicationPrincipaleControleur.class.getName()).log(Level.SEVERE, null, ex);
            }

        compteur++;
    }*/
    public static void initApplicationPrincipale()
    {

    }

    public void cliqueSurAutresRayons(ActionEvent actionEvent)
    {
        try {
            if(!estSurAutresRayons)
            {
                //Vider l'ancienne vue
                pnl_scroll.getChildren().clear();

                nodes[0] = (Node) FXMLLoader.load(getClass().getResource("autresRayons.fxml"));
                pnl_scroll.getChildren().add(nodes[0]);

                //Mettre à jour les booleens
                estSurAutresRayons = true;
                estSurMonRayon = false;
                estSurArticlesReserves = false;
            }
        } catch (IOException ex) {
            Logger.getLogger(applicationPrincipaleControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cliqueSurMonRayon(ActionEvent actionEvent) {
        try {
            if(!estSurMonRayon)
            {
                //Vider l'ancienne vue
                pnl_scroll.getChildren().clear();

                nodes[0] = (Node) FXMLLoader.load(getClass().getResource("monRayon.fxml"));
                pnl_scroll.getChildren().add(nodes[0]);

                //Mettre à jour les booleens
                estSurMonRayon = true;
                estSurAutresRayons = false;
                estSurArticlesReserves = false;
            }
        } catch (IOException ex) {
            Logger.getLogger(applicationPrincipaleControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cliqueSurArticlesReserves(ActionEvent actionEvent) {
        System.out.println("Ne fait encore rien");
    }

    public void cliqueSurDeconnecte(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("connexion.fxml"));

        Main.getPrimaryStage().setScene(new Scene(root));
    }
}
