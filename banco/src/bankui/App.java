package bankui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        App.scene = new Scene(root);
        App.stage = primaryStage;
        App.stage.setTitle("Banco - Entrar");
        App.stage.setScene(App.scene);
        App.stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        App.scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void setTitle(String title) {
        App.stage.setTitle(title);
    }

    public static void main(String[] args) {
        launch(args);
    }
}