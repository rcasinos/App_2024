/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Registro;

import java.io.File;
import java.io.IOException;
import static java.lang.Boolean.FALSE;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

//Imports de nuevas lib
import model.Acount;
import model.AcountDAOException;
import model.User;

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
    
    // Declaraciones locales
    String name, surname, email, user, pass, date;
    
    Image img;  
    LocalDate fecha;
           
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
    @FXML
    private Text message_errorNIckname;
    @FXML
    private Text message_errorPassword;
    @FXML
    private Text message_errorEmail;
    @FXML
    private ImageView tickNickname;
    @FXML
    private ImageView tickEmail;
    @FXML
    private ImageView tickNombreApellido;
    @FXML
    private ImageView tickContrasena;
    @FXML
    private Text nombre_txt;
    @FXML
    private Text apellido_txt;
    @FXML
    private ImageView tickFotoSubida;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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
        
        // Envoltura a APODO para checkear su vericidad
        apodo_field.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
        boolean isNickNameValid = User.checkNickName(newValue);
        boolean isNickNameTaken = false;
    
        if (isNickNameValid) {
            System.out.println("El apodo es valido 1");
            try {
                
                Acount acc = Acount.getInstance();
                isNickNameTaken = acc.existsLogin(newValue);
                
                if (isNickNameTaken) {
                        System.out.println("El apodo esta ya cogido");
                        // Apodo está tomado, mostrar error y ocultar tick
                        message_errorNIckname.setVisible(true);
                        tickNickname.setVisible(false);
                }else{
                        System.out.println("El apodo no esta cogido");
                        // Apodo válido y no está en uso, ocultar error y mostrar tick
                        message_errorNIckname.setVisible(false);
                        tickNickname.setVisible(true);
                        
                }} catch (AcountDAOException | IOException e) {                    
                    // Maneja la excepción de acuerdo a tus necesidades, por ejemplo, mostrar un mensaje de error
                    e.printStackTrace();
                }
        } else {
            System.out.println("El apodo no es correcto");
            // Apodo no válido (contiene espacios o no cumple otras reglas)
            message_errorNIckname.setVisible(true); // Mostrar error por apodo no válido
            tickNickname.setVisible(false); // Ocultar tick
            }
        });
 
                  
        // Envoltura a CONTRASENA para checkear su vericidad
        contrasena_field.textProperty().addListener((observable, oldValue, newValue) -> {
            
            System.out.println("Nueva contrasena ingresada: " + newValue); // Debug
               
            if (!User.checkPassword(newValue)) {
                   System.out.println("Contrasena incorrecta, avisando a mensaje de error");
                   message_errorPassword.setVisible(true);
                   tickContrasena.setVisible(false);
                   
            } else {
                   System.out.println("Contrasena correcta");
                   message_errorPassword.setVisible(false);
                   tickContrasena.setVisible(true);
            }
        });
         
                 
        //Envoltura a EMAIL para checkear su vericidad  
         email_field.textProperty().addListener(new ChangeListener<String>() {
         
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!User.checkEmail(newValue)) {
                    System.out.println("Apodo no valido");
                    message_errorEmail.setVisible(true);
                    tickEmail.setVisible(false);
                }else{
                    message_errorEmail.setVisible(false);
                    tickEmail.setVisible(true);
                            
                }
            }
        });
        
        // Listener para verificar el estado de los campos de nombre y apellido
        ChangeListener<String> nameSurnameListener = (observable, oldValue, newValue) -> {
            boolean isNameValid = !nombre_field.getText().trim().isEmpty();
            boolean isSurnameValid = !apellido_field.getText().trim().isEmpty();

            if (isNameValid && isSurnameValid) {
                tickNombreApellido.setVisible(true);
            } else {
                tickNombreApellido.setVisible(false);
            }
        };

        // Asignar el listener a los campos de nombre y apellido
        nombre_field.textProperty().addListener(nameSurnameListener);
        apellido_field.textProperty().addListener(nameSurnameListener);
         
        nombre_field.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && nombre_field.getText().isEmpty()) {
                nombre_txt.setVisible(true);
            } else {
                nombre_txt.setVisible(false);
            }
        });

        apellido_field.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && apellido_field.getText().isEmpty()) {
                apellido_txt.setVisible(true);
            } else {
                apellido_txt.setVisible(false);
            }
        });
        
        //Se activa el boton de creacion de cuenta si los campos obligatorios estan rellenados    
        //COMPLETAR
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

        try {
            // Obtener instancia de Acount
            Acount acount = Acount.getInstance();
            
            //registro de variables
            name = nombre_field.getText();
            surname = apellido_field.getText();
            email = email_field.getText();
            user = apodo_field.getText();
            pass = contrasena_field.getText();
            img = imagen_foto_perfil.getImage();
            fecha = LocalDate.now();
            
            // Registrar el nuevo usuario
            boolean registrationSuccessful = acount.registerUser(name, surname, email, user, pass, img, fecha);
            
            if (registrationSuccessful) {
                System.out.println("Usuario registrado exitosamente.");
            } else {
                System.out.println("Error al registrar el usuario.");
            }
        } catch (AcountDAOException | IOException e) {
            // Manejar excepciones
            e.printStackTrace();
        }
        
        
        
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



