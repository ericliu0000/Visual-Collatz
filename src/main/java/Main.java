import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Parent mainRoot;

    static {
        try {
            mainRoot = FXMLLoader.load(Main.class.getResource("main.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Scene mainScene = new Scene(mainRoot);

    @Override
    public void start(Stage stage) {
        stage.setTitle("Main");
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}