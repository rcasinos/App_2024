/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Registro;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author berjo
 */
public class Registro_Controller implements Initializable {

    @FXML
    private Button boton_subir_foto;
    @FXML
    private ImageView imagen_foto_perfil;
    @FXML
    private Button boton_registro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("He llegado a interfaz registro");
    }    

    @FXML
    private void subir_foto_desenfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al salir de el
        boton_subir_foto.getStyleClass().remove("boton_enfocado_crear_cuenta");
        boton_subir_foto.getStyleClass().add("boton_desenfocado_crear_cuenta");
    }

    @FXML
    private void subir_foto_enfoque(MouseEvent event) {
       
        //Modificamos el estilo del boton al entrar en el
        boton_subir_foto.getStyleClass().remove("boton_desenfocado_crear_cuenta");
        boton_subir_foto.getStyleClass().add("boton_enfocado_crear_cuenta");
    }

    @FXML
    private void subir_foto_click(MouseEvent event) {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar foto de perfil");
        // Configura los filtros de archivo para que solo muestre archivos de imagen
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.gif")
        );

        // Abre el diálogo de selección de archivos
        List<File> files = fileChooser.showOpenMultipleDialog(boton_subir_foto.getScene().getWindow());
        if (files != null) {
            // Selecciona solo la primera imagen si se seleccionaron múltiples imágenes
            File selectedFile = files.get(0);
            // Carga la imagen seleccionada y la muestra en el ImageView
            Image image = new Image(selectedFile.toURI().toString());
            imagen_foto_perfil.setImage(image);
        }
    }

    @FXML
    private void registro_desenfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        boton_registro.getStyleClass().remove("boton_enfocado_registro");
        boton_registro.getStyleClass().add(".boton_desenfocado_registro");
    }

    @FXML
    private void registro_enfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        boton_registro.getStyleClass().remove(".boton_desenfocado_registro");
        boton_registro.getStyleClass().add("boton_enfocado_registro");
    }

    @FXML
    private void registro_click(MouseEvent event) {
    }
}    
    
