/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio_de_Sesion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author jsoler
 */
public class Inicio_Sesion_Controller implements Initializable {

    @FXML
    private Button boton_siguiente;
    @FXML
    private Button boton_olvido_contrasena;
    @FXML
    private Label label_registrate;

    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void siguiente_click(MouseEvent event) {
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

        // Crear una nueva escena y un nuevo escenario para la ventana emergente
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        // Se bloquea la ventana desde donde se lanza la nueva ventana
        stage.initModality(Modality.APPLICATION_MODAL);

        // Obtenemos la ventana como objeto para aplicarle opciones
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        //cierro pestaña de inicio
        primaryStage.close();
        
        // Mostrar la ventana emergente
        stage.showAndWait();
        
        }
    }
    

