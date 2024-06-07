import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    @Override
    public void start(Stage Stage) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("loginScene.fxml"));
            Scene scene = new Scene(root);

            String css = this.getClass().getResource("LoginScene.css").toExternalForm();
            scene.getStylesheets().add(css);
            Stage.setScene(scene);
            Stage.initStyle(StageStyle.UNDECORATED);
            Stage.show();
     
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}