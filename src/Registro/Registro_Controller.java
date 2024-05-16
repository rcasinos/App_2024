/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Registro;

import java.io.File;
import static java.lang.Boolean.FALSE;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

//Imports de nuevas lib
import model.Acount;
import model.AcountDAOException;

/**
 * FXML Controller class
 *
 * @author berjo
 */
public class Registro_Controller implements Initializable {
    
    //creacion de variables boleanas (privadas)
    private BooleanProperty validName;
    private BooleanProperty validNickname;
    private BooleanProperty validEmail;
    private BooleanProperty validPassword;
    private BooleanProperty validPicture;
    
    
    //variables del .fxml
    @FXML
    private Button boton_subir_foto;
    @FXML
    private ImageView imagen_foto_perfil;
    @FXML
    private Button boton_registro;
    @FXML
    private TextField apodo_field;
    @FXML
    private TextField contrasena_field;
    @FXML
    private TextField nombre_field;
    @FXML
    private TextField apellido_field;
    @FXML
    private TextField email_field;
    
    // Declaraciones locales
    String name, surname, email, user, pass, date;
    
    Image img;  
    LocalDate fecha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Iniciamos los atributos booleanos
        validName = new SimpleBooleanProperty();
        validEmail = new SimpleBooleanProperty();
        validNickname = new SimpleBooleanProperty();
        validPassword = new SimpleBooleanProperty();
        validPicture = new SimpleBooleanProperty();
        
        //Los ponemos a falso
        validName.setValue(FALSE);
        validNickname.setValue(FALSE);
        validEmail.setValue(FALSE);
        validPassword.setValue(FALSE);
        validPicture.setValue(FALSE);
        
        //Creamos envoltorio para el boton de registrarse y que solo se active cuando 
        // todas las opciones esten bien puestas y correctas
        //boton_subir_foto1.disableProperty().bind(validName.not().or(validNickname.not().or(validEmail.not().or(validPassword.not().or(validPicture.not())))));
        
        
    }

    @FXML
    private void subir_foto_desenfoque(MouseEvent event) {

        
        //Modificamos el estilo del boton al entrar en el
        boton_subir_foto.getStyleClass().remove("boton_enfocado_subir_imagen");
        boton_subir_foto.getStyleClass().add("boton_desenfocado_subir_imagen");
    }

    @FXML
    private void subir_foto_enfoque(MouseEvent event) {      
       
        //Modificamos el estilo del boton al entrar en el
        boton_subir_foto.getStyleClass().remove("boton_desenfocado_subir_imagen");
        boton_subir_foto.getStyleClass().add("boton_enfocado_subir_imagen");
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
        boton_registro.getStyleClass().add("boton_desenfocado_registro");
    }

    @FXML
    private void registro_enfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        boton_registro.getStyleClass().remove("boton_desenfocado_registro");
        boton_registro.getStyleClass().add("boton_enfocado_registro");
    }

    @FXML
    private void registro_click(MouseEvent event) throws Exception {
        
        //registro de variables
        name = nombre_field.getText();
        surname = apellido_field.getText();
        email = email_field.getText();
        user = apodo_field.getText();
        pass = contrasena_field.getText();
        img = imagen_foto_perfil.getImage();
        fecha = LocalDate.now();
        
        try{
            Acount acc = Acount.getInstance();
        
            acc.registerUser(name, surname, email, user, pass, img, fecha);      
            
        } catch (AcountDAOException e){
            //Logger.getLogger(Registro_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //espere
        
        
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
        
        }
    }



