/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio_de_Sesion;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;


/**
 *
 * @author jsoler
 */
public class Inicio_Sesion_Controller implements Initializable{

    @FXML
    private Button boton_siguiente;
    @FXML
    private Label label_registrate;
    @FXML
    private ToggleButton verPassword;
    @FXML
    private Button boton_olvido_contrasena;
    @FXML
    private TextField Nombre_field;
    @FXML
    private StackPane stackpane_contrasenas;
    @FXML
    private TextField contrasena_field;
    @FXML
    private PasswordField contrasena_p_field;
    @FXML
    private ImageView ojos_imagen;
    @FXML
    private TextField contrasena_field_prueba;
    


    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        /*Configurar el TextField para mostrar el mismo texto que el PasswordField
         txtUser.textProperty().bindBidirectional(pPasswordUser.textProperty());
         
         //Dejamos el passwordfield visible
          txtUser.setVisible(false);
          
          verPassword.setOnAction(event -> {
              boolean PasswordFieldVisible = pPasswordUser.isVisible();
              pPasswordUser.setVisible(!PasswordFieldVisible);
              txtUser.setVisible(PasswordFieldVisible);
          });
        */ 
          
          
          
        /*viewerPassword.setOnAction(event -> {
          System.out.println("boton pulsado");
                   String password = pPasswordUser.getText();
                   txtUser.setText(password);
        });*/
        
         /*pPasswordUser.focusedProperty().addListener((observable,oldValue,newValue)->{
           if(!newValue){//foco perdido
               checkPassword();}
           });*/
    }

    @FXML
    private void siguiente_desenfocado(MouseEvent event) {
        
         //Modificamos el estilo del boton al salir de el
        boton_siguiente.getStyleClass().remove("boton_enfocado_siguiente");
        boton_siguiente.getStyleClass().add("boton_desenfocado_siguiente");
    }

    @FXML
    private void siguiente_enfocado(MouseEvent event) {
        
         //Modificamos el estilo del boton al salir de el
        boton_siguiente.getStyleClass().remove("boton_desenfocado_siguiente");
        boton_siguiente.getStyleClass().add("boton_enfocado_siguiente");
    }

    @FXML
    private void siguiente_click(MouseEvent event) throws Exception{
        
        String u = Nombre_field.getText();
        String p1 = contrasena_field_prueba.getText();
     
        Acount acc = null;
        boolean valido = false;

        if(u != null && p1 != null && !u.equals("") && !p1.equals(""))  {
            // Hay valor
            try {
                System.out.println("1");
                acc = Acount.getInstance();
                System.out.println("2");                
                valido = acc.logInUserByCredentials(u, p1);
                System.out.println("3");
                
            } catch (AcountDAOException | IOException e) {
                // Manejar excepciones
                e.printStackTrace();
            }
        } else {
            if (u == null || u.equals("")) {
                System.out.println("Usuario no completado");
            }
            if (p1 == null || p1.equals("")) {
                System.out.println("Contraseña no rellenada");
            }            
            return;
        }
        
        if (valido) {
            // Si todo esta Bien:
            // Cargar el FXML de la ventana emergente
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Loggeado/Vista_Logg.fxml"));
            Parent root = loader.load();

            // Crear una nueva escena y un nuevo escenario para la ventana emergente
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);

            // Obtenemos la ventana como objeto para aplicarle opciones
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            //cierro pestaña de inicio
            primaryStage.close();

            stage.setMaximized(true);
            stage.centerOnScreen();

            // Mostrar la ventana emergente
            stage.show();

        }else {
            System.out.println("No es valido?");
        }
    }

    @FXML
    private void contrasena_desenfoque(MouseEvent event) {
        
         //Modificamos el estilo del boton al salir de el
        boton_olvido_contrasena.getStyleClass().remove("boton_enfocado_contrasena");
        boton_olvido_contrasena.getStyleClass().add("boton_desenfocado_contrasena");
    }

    @FXML
    private void contrasena_enfoque(MouseEvent event) {
        
         //Modificamos el estilo del boton al salir de el
        boton_olvido_contrasena.getStyleClass().remove("boton_desenfocado_contrasena");
        boton_olvido_contrasena.getStyleClass().add("boton_enfocado_contrasena");
    }

    @FXML
    private void contrasena_click(MouseEvent event) {
    }

    @FXML
    private void registrate_desenfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al salir de el
        label_registrate.getStyleClass().remove("label_enfocado_azul");
        label_registrate.getStyleClass().add("label_desenfocado_azul");
    }

    @FXML
    private void registrate_enfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al salir de el
        label_registrate.getStyleClass().remove("label_desenfocado_azul");
        label_registrate.getStyleClass().add("label_enfocado_azul");
    }

    @FXML
    private void registrate_click(MouseEvent event) throws Exception{
        
        
        // Cargar el FXML de la ventana emergente
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Registro/Prueba_registro.fxml"));
        Parent root = loader.load();
        
        // Cerramos la ventana anterior de inicio 
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();

        // Crear una nueva escena y un nuevo escenario para la ventana emergente
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        // Se bloquea la ventana desde donde se lanza la nueva ventana
        stage.initModality(Modality.APPLICATION_MODAL);
       
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
        
        // Obtenemos la ventana como objeto para aplicarle opciones
        Stage primaryStage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        //Creamos un efecto de desenfoque
        BoxBlur blur = new BoxBlur(10, 10, 1);

        //Aplicamos el efecto de desenfoque
        primaryStage2.getScene().getRoot().setEffect(blur);

        // Restaurar la opacidad de la ventana principal cuando se cierre la ventana emergente
        primaryStage2.getScene().getRoot().setEffect(null);
        
        }
    
    }

   