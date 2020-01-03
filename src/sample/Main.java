package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

        public class Main extends Application {

            @FXML
            private ImageView imageView;

            @Override
            public void start(Stage primaryStage) throws Exception{


        Parent root = FXMLLoader.load(getClass().getResource("formulaire.fxml"));
        primaryStage.setTitle("Application de gestion de stock");
        primaryStage.setScene(new Scene(root));
        //Enlever la barre de menu du haut
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(new Image("images/logoPolytech.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
