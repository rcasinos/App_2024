package Loggeado.Perfil;

import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Acount;
import model.AcountDAOException;
import model.User;

public class Mi_Perfil_Controller {

    @FXML
    private ImageView imagenPerfil;

    @FXML
    private Label labelNickname;

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelApellido;

    @FXML
    private Label labelCorreo;

    @FXML
    private Label labelPassword;

    @FXML
    private TextField textFieldNickname;

    @FXML
    private TextField textFieldNombre;

    @FXML
    private TextField textFieldApellido;

    @FXML
    private TextField textFieldCorreo;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private Button botonEdit;

    @FXML
    private Button botonSave;

    private boolean editando = false;
    private User loggedUser;
    @FXML
    private Button butom_Image;
    @FXML
    private Button botonCancel;
    @FXML
    private Text text_nickname;
    @FXML
    private Text msg_nombre;
    @FXML
    private Text msg_apellido;
    @FXML
    private Text msg_ini_email;
    @FXML
    private Text msg_err_email;
    @FXML
    private Text msg_ini_pssw;
    @FXML
    private Text msg_err_pssw;
    @FXML
    private Label fecha_de_registro;
    @FXML
    private Button alertButton;
    
    private Acount acc;
    // Método para inicializar los componentes
    public void initialize() throws AcountDAOException, IOException {
        
        //Mostramos la fecha en que se registro la persona
        Acount acc = Acount.getInstance();
        User user = acc.getLoggedUser();
        
        LocalDate registerDate = user.getRegisterDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Puedes cambiar el patrón al formato deseado
        String formattedDate = registerDate.format(formatter);
        
        fecha_de_registro.setText(formattedDate);
    //-------------------------------------------------------------------------   
     alertButton.setOnAction(event -> mostrarAlerta(textFieldPassword));
    //----------------------------------------------------------------- 
    //Ocultamos los mensajes de error de contraseña y de correo 
         msg_err_email.setVisible(false);
         msg_ini_email.setVisible(false);
         //---------------------------------
         msg_ini_pssw.setVisible(false);
         msg_err_pssw.setVisible(false);
 //--------------------------------------------------------------------------
          textFieldCorreo.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!User.checkEmail(newValue)) {
                    System.out.println("Apodo no valido");
                    msg_err_email.setVisible(true);
                    msg_ini_email.setVisible(false);
                    //validEmail.setValue(false);
                } else {
                    msg_err_email.setVisible(false);
                    msg_ini_email.setVisible(false);
                   // validEmail.setValue(true);

                }
            }
        });
 //---------------------------------------------------------------------------
  textFieldCorreo.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                informar_correo();
            }
        });
 
 
 
 // Envoltura a CONTRASENA para checkear su vericidad
         textFieldPassword.textProperty().addListener((observable, oldValue, newValue) -> {

            System.out.println("Nueva contrasena ingresada: " + newValue); // Debug

            if (!User.checkPassword(newValue)) {
                System.out.println("Contrasena incorrecta, avisando a mensaje de error");
                msg_ini_pssw.setVisible(false);
                msg_err_pssw.setVisible(true);
                
               // validPassword.setValue(false);

            } else {
                System.out.println("Contrasena correcta");
                msg_ini_pssw.setVisible(false);
                msg_err_pssw.setVisible(false);
               // validPassword.setValue(true);
            }
        });
        
     //--------------------------------------------------------------------------------  
     msg_nombre.setVisible(false);
     text_nickname.setVisible(false);
     msg_apellido.setVisible(false);
    //Ocultamos el boton de save los cambios ya que no hay cambios hechos
    // ademas este estará deshabilitado hasta que s ehayan realizado cambios en alguno de los campos
    // del perfil    
     botonSave.setVisible(false);
     botonSave.setDisable(true);
     //boton para cambiar la imagen tambien oculto hasta qeu se le de al edit
     butom_Image.setVisible(false);
     butom_Image.setDisable(true);
     //boton de cancelar el edit tambien invisible y deshabilitado
     botonCancel.setVisible(false);
     botonCancel.setDisable(true);
     
     //--------------------------------------------------------------------------------
     // evento que cuando le das a cancelar vuelve a cambiar los botones , rollo la visibilidad
     botonCancel.setOnMouseClicked(event -> {
         
      setEditableFields(false);
         //botonCancel.requestFocus();
        botonSave.setVisible(false);
        botonSave.setDisable(true);
     //boton para cambiar la imagen tambien oculto hasta qeu se le de al edit
        butom_Image.setVisible(false);
         butom_Image.setDisable(true);
     //boton de cancelar el edit tambien invisible y deshabilitado
         botonCancel.setVisible(false);
        botonCancel.setDisable(true);   
     // VOlvemos a mostrar el boton de edit
      botonEdit.setVisible(true);
          
        });
 //--------------------------------------------------------------------------------
       textFieldNombre.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                informar_nombre();
            }
        });
       
       textFieldApellido.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                informar_apellido();
            }
        });
      
          
     //--------------------------------------------------------------------------------
      
          
          
     
     //listener que se encarga de mostrar los demas botones y habilitarlos
     botonEdit.setOnMouseClicked(event -> {
            //botonEdit.requestFocus();
            
             
                 setEditableFields(true);
                //mostramos el boton save pero no esta habilitado
                botonSave.setVisible(true);
                botonSave.setDisable(true);
                //MOstramos y habilitamos el botn de cambiar la foto
                butom_Image.setVisible(true);
                butom_Image.setDisable(false);
                //Mostramos el boton de cancelar y lo habilitamos
                 botonCancel.setVisible(true);
                 botonCancel.setDisable(false);   
            
         });
    //-------------------------------------------------------------------------------- 
      
        ChangeListener<String> changeListener = (observable, oldValue, newValue)->{
                if (!textFieldNombre.getText().isEmpty() || textFieldApellido.getText().isEmpty() || textFieldPassword.getText().isEmpty() || textFieldCorreo.getText().isEmpty()) {
                 botonSave.setDisable(false);
        } else {
                    botonSave.setDisable(true);
        }
    
    };
        textFieldNombre.textProperty().addListener(changeListener);
        textFieldApellido.textProperty().addListener(changeListener);
        textFieldCorreo.textProperty().addListener(changeListener);
        textFieldPassword.textProperty().addListener(changeListener);
     
     
        
    //-------------------------------------------------------------------------------- 
        try {
            // Inicializa la imagen del usuario cuando la escena se carga
            imagenPerfil.setImage(getLoggedUserImage());
            loggedUser = Acount.getInstance().getLoggedUser();
            populateUserDetails(loggedUser);
        } catch (AcountDAOException | IOException e) {
            e.printStackTrace();
        }

        // Deshabilitar la edición de los campos inicialmente
        textFieldNickname.setEditable(false);
        setEditableFields(false);
    }
 //-------------------------------------------------------------------------------- 
    // Método para llenar los detalles del usuario en los campos de texto
    private void populateUserDetails(User user) {
        if (user != null) {
            textFieldNickname.setText(user.getNickName());
            textFieldNombre.setText(user.getName());
            textFieldApellido.setText(user.getSurname());
            textFieldCorreo.setText(user.getEmail());
            textFieldPassword.setText(user.getPassword()); // No llenar el campo de password por seguridad
        } else {
            System.out.println("El usuario logueado es nulo.");
        }
    }
 //-------------------------------------------------------------------------------- 
    // Método para obtener la imagen del usuario logueado
    public Image getLoggedUserImage() throws AcountDAOException, IOException {
        return Acount.getInstance().getLoggedUser().getImage();
    }
 //-------------------------------------------------------------------------------- 
    // Evento para habilitar la edición de los campos
   
 //-------------------------------------------------------------------------------- 
    // Evento para guardar los cambios y deshabilitar la edición
    @FXML
    private void guardarCambios_click(MouseEvent event) {
       
            // Obtener los valores de los campos de texto
            String nuevoNombre = textFieldNombre.getText();
            String nuevoApellido = textFieldApellido.getText();
            String nuevoCorreo = textFieldCorreo.getText();
            String nuevaPassword = textFieldPassword.getText();

            // Validar los datos
            if (validateInputs(nuevoNombre, nuevoApellido, nuevoCorreo, nuevaPassword)) {
                try {
                    loggedUser.setName(nuevoNombre);
                    loggedUser.setSurname(nuevoApellido);
                    loggedUser.setEmail(nuevoCorreo);
                    if (!nuevaPassword.isEmpty()) {
                        loggedUser.setPassword(nuevaPassword);
                    }

                    // Deshabilitar la edición de los campos
                    setEditableFields(false);
                    botonCancel.setVisible(false);
                    botonCancel.setDisable(true);
                    botonSave.setVisible(false);
                    botonSave.setDisable(true);
                    butom_Image.setVisible(false);
                    butom_Image.setDisable(true);
                            
                    System.out.println("Datos cambiado correctamente");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // Mostrar mensaje de error en la validación
                System.out.println("Error en la validación de los datos.");
            }
        
    }


    // Métodos para comprobar el formato de los datos introducidos
    private boolean validateName(String name) {
        return !name.trim().isEmpty() && name.matches("[a-zA-Z]+") && !name.contains(" ");
    }

    private boolean validateSurname(String surname) {
        return !surname.trim().isEmpty() && surname.matches("[a-zA-Z]+") && !surname.contains(" ");
    }

    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validatePassword(String password) {
        return password.isEmpty() || password.length() >= 6;
    }

    private boolean validateInputs(String name, String surname, String email, String password) {
        return validateName(name) && validateSurname(surname) && validateEmail(email) && validatePassword(password);
    }

    // Método para habilitar o deshabilitar la edición de los campos
    private void setEditableFields(boolean editable) {
        textFieldNombre.setEditable(editable);
        textFieldApellido.setEditable(editable);
        textFieldCorreo.setEditable(editable);
        textFieldPassword.setEditable(editable);
    }

    @FXML
    private void change_picture_exited(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        butom_Image.getStyleClass().remove("boton_enfocado_perfil");
        butom_Image.getStyleClass().add("boton_desenfocado_perfil");        
    }

    @FXML
    private void change_picture_entered(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        butom_Image.getStyleClass().remove("boton_desenfocado_perfil");
        butom_Image.getStyleClass().add("boton_enfocado_perfil");        
    }

    @FXML
    private void edit_exited(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        botonEdit.getStyleClass().remove("boton_enfocado_perfil");
        botonEdit.getStyleClass().add("boton_desenfocado_perfil");        
    }

    @FXML
    private void edit_entered(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        botonEdit.getStyleClass().remove("boton_desenfocado_perfil");
        botonEdit.getStyleClass().add("boton_enfocado_perfil");        
    }

    @FXML
    private void guardarCambios_salir(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        botonSave.getStyleClass().remove("boton_enfocado_perfil");
        botonSave.getStyleClass().add("boton_desenfocado_perfil");        
    }

    @FXML
    private void guardarCambios_entered(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        botonSave.getStyleClass().remove("boton_desenfocado_perfil");
        botonSave.getStyleClass().add("boton_enfocado_perfil");        
              
    }

    @FXML
    private void change_picture_click(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de imagen (.png, *.jpg, *.jpeg)", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(new Stage());

        Image image = null;
        if (file != null) {
            image = new Image(file.toURI().toString());
            imagenPerfil.setImage(image);
        }

        // Lógica para guardar la nueva imagen en la base de datos
        if (image != null) {
            try {
                // Obtener el usuario logueado
                User loggedUser = Acount.getInstance().getLoggedUser();

                // Actualizar la imagen del usuario en la base de datos
                loggedUser.setImage(image);

                // Mostrar mensaje de éxito
                System.out.println("Imagen guardada correctamente.");
            } catch (AcountDAOException | IOException e) {
                // Manejar excepción en caso de error al guardar la imagen
                e.printStackTrace();
                // Mostrar mensaje de error al usuario
                System.err.println("Error al guardar la imagen.");
            }
        }
    }

    @FXML
    private void cancelEdit(ActionEvent event) {
        
        populateUserDetails(loggedUser);
      
        
    }

    @FXML
    private void informar_user(MouseEvent event) {
        
        text_nickname.setVisible(true);
        
         Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5),e -> text_nickname.setVisible(false)));
            timeline.setCycleCount(1); // Solo ejecutar una vez
            timeline.play();
       
    }
//--------------------------------------------------------------------------------------------------------------------
   
    private void informar_nombre() {
        
        
        msg_nombre.setVisible(true);
        
         Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3),e -> msg_nombre.setVisible(false)));
            timeline.setCycleCount(1); // Solo ejecutar una vez
            timeline.play();
    }

   // tengo que cambiar esto porque n ova como quiero que vaya
    
    private void informar_apellido() {
        
        
        msg_apellido.setVisible(true);
        
         Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3),e -> msg_apellido.setVisible(false)));
            timeline.setCycleCount(1); // Solo ejecutar una vez
            timeline.play();
    }
    
    private void informar_correo(){
        
         msg_ini_email.setVisible(true);
        
         Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3),e -> msg_ini_email.setVisible(false)));
            timeline.setCycleCount(1); // Solo ejecutar una vez
            timeline.play();
        
    }
    
    
    
     private void mostrarAlerta(TextField textField) {
         //Modificar la alerta, hacerla de un estilo chulo, a parte hay que cambiar 
         //que sea un passwordfield
         //que el boton de editar se habilite cuando des a editar el perfil
         // que al cambiar la contraseña se cambie en el paswordfield
         // espero deseo y rezo para poder poner el checkpassword dentro de la alerta para q no deje poner
         // una contraseña random
         // con esto y un bizcocho, nos vemos mañna a las ocho
         
         
         
         
        // Crear la alerta
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Opciones");
        alert.setHeaderText("Elige una opción");
        alert.setContentText("Selecciona una de las opciones:");

        // Crear los botones de opciones
        ButtonType buttonTypeMostrar = new ButtonType("Mostrar Texto");
        ButtonType buttonTypeNuevaContraseña = new ButtonType("Nueva Contraseña");
       // ButtonType buttonTypeCancelar = new ButtonType( ButtonType.CLOSE);

        // Agregar los botones a la alerta
        alert.getButtonTypes().setAll(buttonTypeMostrar, buttonTypeNuevaContraseña /*buttonTypeCancelar*/);

        // Manejar la selección del usuario
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeMostrar) {
            // Mostrar el texto del TextField
            Alert infoAlert = new Alert(AlertType.INFORMATION);
            infoAlert.setTitle("Texto del TextField");
            infoAlert.setHeaderText(null);
            infoAlert.setContentText("El texto es: " + textField.getText());
            infoAlert.showAndWait();
        } else if (result.isPresent() && result.get() == buttonTypeNuevaContraseña) {
            // Pedir al usuario que ingrese una nueva contraseña
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Nueva Contraseña");
            dialog.setHeaderText("Ingresa una nueva contraseña");
            dialog.setContentText("Contraseña:");

            // Obtener la nueva contraseña
            Optional<String> newPassword = dialog.showAndWait();
            newPassword.ifPresent(password -> {
                Alert infoAlert = new Alert(AlertType.INFORMATION);
                infoAlert.setTitle("Contraseña Actualizada");
                infoAlert.setHeaderText(null);
                infoAlert.setContentText("Nueva contraseña establecida.");
                infoAlert.showAndWait();
                // Aquí puedes manejar la nueva contraseña según sea necesario
                System.out.println("Nueva contraseña: " + password);
            });
        }
    }

    @FXML
    private void edit_pass_exited(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        alertButton.getStyleClass().remove("boton_enfocado_perfil");
        alertButton.getStyleClass().add("boton_desenfocado_perfil");           
    }

    @FXML
    private void edit_pass_entered(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        alertButton.getStyleClass().remove("boton_desenfocado_perfil");
        alertButton.getStyleClass().add("boton_enfocado_perfil");           
    }

    @FXML
    private void cancel_exited(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        botonCancel.getStyleClass().remove("boton_enfocado_cancelar");
        botonCancel.getStyleClass().add("boton_desenfocado_cancelar");           
    }

    @FXML
    private void cancel_entered(MouseEvent event) {
         
        //Modificamos el estilo del boton al entrar en el
        botonCancel.getStyleClass().remove("boton_desenfocado_cancelar");
        botonCancel.getStyleClass().add("boton_enfocado_cancelar");          
    }

}
