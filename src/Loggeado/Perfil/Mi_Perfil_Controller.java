package Loggeado.Perfil;

import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private Text msg_ini_nickname;
    @FXML
    private Text msg_nick_use;
    @FXML
    private Text msg_nick_spaces;

    // Método para inicializar los componentes
    public void initialize() {
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
            textFieldPassword.setText("Falta por implementar"); // No llenar el campo de password por seguridad
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
    }

    @FXML
    private void change_picture_entered(MouseEvent event) {
    }

    @FXML
    private void edit_exited(MouseEvent event) {
    }

    @FXML
    private void edit_entered(MouseEvent event) {
    }

    @FXML
    private void guardarCambios_salir(MouseEvent event) {
    }

    @FXML
    private void guardarCambios_entered(MouseEvent event) {
        
        
        
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
        
         Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5),e -> msg_nombre.setVisible(false)));
            timeline.setCycleCount(1); // Solo ejecutar una vez
            timeline.play();
    }

   // tengo que cambiar esto porque n ova como quiero que vaya
    
    private void informar_apellido() {
        
        
        msg_apellido.setVisible(true);
        
         Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5),e -> msg_apellido.setVisible(false)));
            timeline.setCycleCount(1); // Solo ejecutar una vez
            timeline.play();
    }

}
