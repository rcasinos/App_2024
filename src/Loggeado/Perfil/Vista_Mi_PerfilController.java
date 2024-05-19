package Loggeado.Perfil;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Vista_Mi_PerfilController {

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

    //=========================================================
    // you must initialize here all related with the object 
    public void initialize(URL url, ResourceBundle rb) {
        // No se pueden editar de primeras
        textFieldNickname.setEditable(false);
        textFieldNombre.setEditable(false);
        textFieldApellido.setEditable(false);
        textFieldCorreo.setEditable(false);
        textFieldPassword.setEditable(false);        
    }   

    @FXML
    private void editarPerfil_click(MouseEvent event) {
        
        if (!editando) {
            // Habilitar la edición de los campos
            textFieldNickname.setEditable(true);
            textFieldNombre.setEditable(true);
            textFieldApellido.setEditable(true);
            textFieldCorreo.setEditable(true);
            textFieldPassword.setEditable(true);
            editando = true;
        }
    }

    @FXML
    private void guardarCambios_click(MouseEvent event) {
        
            if (editando) {
            // Guardar los cambios
            String nuevoNickname = textFieldNickname.getText();
            String nuevoNombre = textFieldNombre.getText();
            String nuevoApellido = textFieldApellido.getText();
            String nuevoCorreo = textFieldCorreo.getText();
            String nuevaPassword = textFieldPassword.getText();
            
            // Aquí iría la lógica para guardar los cambios en la base de datos o donde corresponda

            // Deshabilitar la edición de los campos
            textFieldNickname.setEditable(false);
            textFieldNombre.setEditable(false);
            textFieldApellido.setEditable(false);
            textFieldCorreo.setEditable(false);
            textFieldPassword.setEditable(false);
            editando = false;
        }
    }
}
