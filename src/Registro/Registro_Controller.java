package Registro;

import java.io.File;
import java.io.IOException;
import static java.lang.Boolean.FALSE;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
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
    @FXML
    private Text msg_ini_nickname;
    @FXML
    private Text msg_nick_use;
    @FXML
    private Text msg_nick_spaces;
    @FXML
    private Text msg_ini_pssw;
    @FXML
    private Text msg_err_pssw;
    @FXML
    private Text msg_ini_email;
    @FXML
    private Text msg_err_email;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Iniciamos los atributos booleanos
        validName = new SimpleBooleanProperty(false);
        validEmail = new SimpleBooleanProperty(false);
        validNickname = new SimpleBooleanProperty(false);
        validPassword = new SimpleBooleanProperty(false);
        validPicture = new SimpleBooleanProperty(false);
//-----------------------------------------------------------------------------------------------------------------/  
        // Mensajes de error a falso de primeras
        msg_nick_use.setVisible(false);
        msg_nick_spaces.setVisible(false);
        msg_ini_nickname.setVisible(false);
        msg_ini_pssw.setVisible(false);
        msg_err_pssw.setVisible(false);
//-----------------------------------------------------------------------------------------------------------------/  
        // Evento para manejar el clic en el campo de texto de apodo
        apodo_field.setOnMouseClicked(event -> {
            // Verificar si el campo de texto de apodo está vacío
            if (apodo_field.getText().isEmpty()) {
                // Mostrar el mensaje de error correspondiente
                msg_ini_nickname.setVisible(true);
            }
        });
//-----------------------------------------------------------------------------------------------------------------/  
        // Evento para manejar el cambio de foco del campo de texto de apodo
        apodo_field.focusedProperty().addListener((observable, oldValue, newValue) -> {
            // Verificar si el campo de texto ha perdido el foco
            if (!newValue) {
                // Ocultar el mensaje de error
                msg_ini_nickname.setVisible(false);
            }
        });
//-----------------------------------------------------------------------------------------------------------------/  
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
                        msg_nick_use.setVisible(true);
                        msg_nick_spaces.setVisible(false);
                        msg_ini_nickname.setVisible(false);
                        tickNickname.setVisible(false);
                        validNickname.setValue(false);
                    } else {
                        System.out.println("El apodo no esta cogido");
                        // Apodo válido y no está en uso, ocultar error y mostrar tick
                        msg_nick_use.setVisible(false);
                        msg_nick_spaces.setVisible(false);
                        msg_ini_nickname.setVisible(false);
                        tickNickname.setVisible(true);
                        validNickname.setValue(true);
                    }
                } catch (AcountDAOException | IOException e) {
                    // Maneja la excepción de acuerdo a tus necesidades, por ejemplo, mostrar un mensaje de error
                    e.printStackTrace();
                }
            } else {
                System.out.println("El apodo no es correcto");
                // Apodo no válido (contiene espacios o no cumple otras reglas)
                msg_nick_use.setVisible(false);
                msg_nick_spaces.setVisible(true);
                msg_ini_nickname.setVisible(false); // Mostrar error por apodo no válido
                tickNickname.setVisible(false); // Ocultar tick
                validNickname.setValue(false);
            }
        });
//-----------------------------------------------------------------------------------------------------------------/  
        // Evento para manejar el clic en el campo de texto de apodo
        contrasena_field.setOnMouseClicked(event -> {
            // Verificar si el campo de texto de apodo está vacío
            if (contrasena_field.getText().isEmpty()) {
                // Mostrar el mensaje de error correspondiente
                msg_ini_pssw.setVisible(true);
            }
        });

        // Evento para manejar el cambio de foco del campo de texto de apodo
        contrasena_field.focusedProperty().addListener((observable, oldValue, newValue) -> {
            // Verificar si el campo de texto ha perdido el foco
            if (!newValue) {
                // Ocultar el mensaje de error
                msg_ini_pssw.setVisible(false);
            }
        });
//-----------------------------------------------------------------------------------------------------------------/  
        // Envoltura a CONTRASENA para checkear su vericidad
        contrasena_field.textProperty().addListener((observable, oldValue, newValue) -> {

            System.out.println("Nueva contrasena ingresada: " + newValue); // Debug

            if (!User.checkPassword(newValue)) {
                System.out.println("Contrasena incorrecta, avisando a mensaje de error");
                msg_ini_pssw.setVisible(false);
                msg_err_pssw.setVisible(true);
                tickContrasena.setVisible(false);
                validPassword.setValue(false);

            } else {
                System.out.println("Contrasena correcta");
                msg_ini_pssw.setVisible(false);
                msg_err_pssw.setVisible(false);
                tickContrasena.setVisible(true);
                validPassword.setValue(true);
            }
        });
//-----------------------------------------------------------------------------------------------------------------/  
        // Evento para manejar el clic en el campo de texto de apodo
        email_field.setOnMouseClicked(event -> {
            // Verificar si el campo de texto de apodo está vacío
            if (email_field.getText().isEmpty()) {
                // Mostrar el mensaje de error correspondiente
                msg_ini_email.setVisible(true);
            }
        });

        // Evento para manejar el cambio de foco del campo de texto de apodo
        email_field.focusedProperty().addListener((observable, oldValue, newValue) -> {
            // Verificar si el campo de texto ha perdido el foco
            if (!newValue) {
                // Ocultar el mensaje de error
                msg_ini_email.setVisible(false);
            }
        });
//-----------------------------------------------------------------------------------------------------------------/  
        //Envoltura a EMAIL para checkear su vericidad
        email_field.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!User.checkEmail(newValue)) {
                    System.out.println("Apodo no valido");
                    msg_err_email.setVisible(true);
                    msg_ini_email.setVisible(false);
                    tickEmail.setVisible(false);
                    validEmail.setValue(false);
                } else {
                    msg_err_email.setVisible(false);
                    msg_ini_email.setVisible(false);
                    tickEmail.setVisible(true);
                    validEmail.setValue(true);

                }
            }
        });
//-----------------------------------------------------------------------------------------------------------------/  
        // Envoltura/Listener para verificar el estado de los campos de nombre y apellido
        ChangeListener<String> nameSurnameListener = (observable, oldValue, newValue) -> {
            boolean isNameValid = !nombre_field.getText().trim().isEmpty();
            boolean isSurnameValid = !apellido_field.getText().trim().isEmpty();

            if (isNameValid && isSurnameValid) {
                tickNombreApellido.setVisible(true);
                validName.setValue(true);
            } else {
                tickNombreApellido.setVisible(false);
                validName.setValue(false);
            }
        };
//-----------------------------------------------------------------------------------------------------------------/  
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
//-----------------------------------------------------------------------------------------------------------------/  
        // Vinculación para habilitar/deshabilitar el botón de registro
        BooleanBinding formValid = validName.and(validNickname).and(validEmail).and(validPassword);
        boton_registro.disableProperty().bind(formValid.not());
    }
//-----------------------------------------------------------------------------------------------------------------/  
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
//-----------------------------------------------------------------------------------------------------------------/  
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
            validPicture.setValue(true);
        }
    }
//-----------------------------------------------------------------------------------------------------------------/  
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
//-----------------------------------------------------------------------------------------------------------------/  
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

            } else {
                System.out.println("Error al registrar el usuario.");
            }
        } catch (AcountDAOException | IOException e) {
            // Manejar excepciones
            e.printStackTrace();
        }
    }
}