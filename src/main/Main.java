package main;

import controleur.dao.SetupEM;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application {

            private static Stage primaryStage;

            @FXML
            private ImageView imageView;

            @Override
            public void start(Stage primaryStage) throws Exception{

                new SetupEM();
                Main.primaryStage = primaryStage;
                Parent root = FXMLLoader.load(getClass().getResource("../ressources/connexion.fxml"));


                primaryStage.setTitle("Application de gestion de stock");
                primaryStage.setScene(new Scene(root));
                //Enlever la barre de menu du haut
                //primaryStage.initStyle(StageStyle.UNDECORATED);
                primaryStage.getIcons().add(new Image("images/logoPolytech.png"));
                primaryStage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
