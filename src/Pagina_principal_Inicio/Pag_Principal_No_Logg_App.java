package Pagina_principal_Inicio;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//si esto se sube a desarrollo me voy a jugar un brawl

public class Pag_Principal_No_Logg_App extends Application {
        
    @Override
    public void start(Stage stage) throws Exception {
        
        //======================================================================
        // 1- creación del grafo de escena a partir del fichero FXML
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("Vista_Pag_Principal_No_Logg.fxml"));
        Parent root = loader.load();
        //======================================================================
        // 2- creación de la escena con el nodo raiz del grafo de escena
        Scene scene = new Scene(root);
        //======================================================================        
        // 3- asiganación de la escena al Stage que recibe el metodo 
        //     - configuracion del stage
        //     - se muestra el stage de manera no modal mediante el metodo show()
        
        stage.setMaximized(true);
        stage.centerOnScreen();
        
        stage.setScene(scene);
        stage.setTitle("Rocketeer");
        stage.show();       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }


    
}