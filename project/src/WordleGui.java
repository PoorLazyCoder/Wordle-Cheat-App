import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;


public class WordleGui  extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        Parent root = null;

        try{
            root =  loader.load( getClass().getResource("/WordleGui.fxml"));
        }catch (Exception ex){
             ex.printStackTrace();
        }

        Scene scene = new Scene(root, 1000, 675);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Wordle Cheat");
        primaryStage.show();
    }


}
