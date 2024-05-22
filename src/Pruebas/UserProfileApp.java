package pruebas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserProfileApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserProfile.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        // Dentro del método initialize()
        scene.getStylesheets().add(getClass().getResource("pruebas.css").toExternalForm());
        primaryStage.setTitle("Perfil de Usuario");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
