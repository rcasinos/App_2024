package Pagina_principal_Inicio;
        
import javafx.geometry.Rectangle2D;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

//Import del lanzador
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author jsoler
 */
public class Pag_Principal_No_Logg_Controller implements Initializable {

    @FXML
    private Button boton_crear_cuenta;
    @FXML
    public Button boton_inicio_sesion;

    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void crear_cuenta_click(MouseEvent event) throws Exception {
        
        // Cargar el FXML de la ventana emergente
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Registro/Prueba_registro.fxml"));
        Parent root = loader.load();

        // Crear una nueva escena y un nuevo escenario para la ventana emergente
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        // Se bloquea la ventana desde donde se lanza la nueva ventana
        stage.initModality(Modality.APPLICATION_MODAL);

        // Obtenemos la ventana como objeto para aplicarle opciones
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        //Creamos un efecto de desenfoque
        BoxBlur blur = new BoxBlur(10, 10, 1);

        //Aplicamos el efecto de desenfoque
        primaryStage.getScene().getRoot().setEffect(blur);
        
        // Obtener dimensiones de la pantalla principal
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

        // Ajustar la altura de la ventana emergente al máximo posible
        double windowHeight = bounds.getHeight();
        stage.setHeight(windowHeight);

        // Limitar al ancho de la ventana emergente
        double maxWindowWidth = 800;
        double windowWidth = Math.min(bounds.getWidth(), maxWindowWidth);
        stage.setWidth(windowWidth);
        
        // Mostrar la ventana emergente
        stage.showAndWait();

        // Restaurar la opacidad de la ventana principal cuando se cierre la ventana emergente
        primaryStage.getScene().getRoot().setEffect(null);
        
        }

    @FXML
    private void crear_cuenta_desenfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al salir de el
        boton_crear_cuenta.getStyleClass().remove("boton_enfocado_crear_cuenta");
        boton_crear_cuenta.getStyleClass().add("boton_desenfocado_crear_cuenta");
    }

    @FXML
    private void crear_cuenta_enfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        boton_crear_cuenta.getStyleClass().remove("boton_desenfocado_crear_cuenta");
        boton_crear_cuenta.getStyleClass().add("boton_enfocado_crear_cuenta");
    }

    @FXML
    private void iniciar_sesion_desenfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al salir de el
        boton_inicio_sesion.getStyleClass().remove("boton_enfocado_inicio_sesion");
        boton_inicio_sesion.getStyleClass().add("boton_desenfocado_inicio_sesion");
    }

    @FXML
    private void iniciar_sesion_enfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar a el
        boton_inicio_sesion.getStyleClass().remove("boton_desenfocado_inicio_sesion");
        boton_inicio_sesion.getStyleClass().add("boton_enfocado_inicio_sesion");
    }

    @FXML
    private void iniciar_sesion_click(MouseEvent event) throws Exception {
        
        // Cargar el FXML de la ventana emergente
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Inicio_de_Sesion/Vista_Inicio_Sesion.fxml"));
        Parent root = loader.load();

        // Crear una nueva escena y un nuevo escenario para la ventana emergente
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        // Se bloquea la ventana desde donde se lanza la nueva ventana
        stage.initModality(Modality.APPLICATION_MODAL);

        // Obtenemos la ventana como objeto para aplicarle opciones
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        //Creamos un efecto de desenfoque
        BoxBlur blur = new BoxBlur(10, 10, 1);

        //Aplicamos el efecto de desenfoque
        primaryStage.getScene().getRoot().setEffect(blur);
        
        // Obtener dimensiones de la pantalla principal
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

        // Ajustar la altura de la ventana emergente al máximo posible
        double windowHeight = bounds.getHeight();
        stage.setHeight(windowHeight);

        // Limitar al ancho de la ventana emergente
        double maxWindowWidth = 800;
        double windowWidth = Math.min(bounds.getWidth(), maxWindowWidth);
        stage.setWidth(windowWidth);
        
        // Mostrar la ventana emergente
        stage.showAndWait();

        // Restaurar la opacidad de la ventana principal cuando se cierre la ventana emergente
        primaryStage.getScene().getRoot().setEffect(null);
    }

    
    }
    
