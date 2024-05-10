/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.File;
import java.net.URL;
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
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author berjo
 */
public class FXMLSignUpController implements Initializable {

    @FXML
    private Label lIncorrectPassword;
    @FXML
    private Label lpassDifferent;
    @FXML
    private Button bCancel;
    @FXML
    private Button bAccept;
    @FXML
    private TextField nameUser;
    @FXML
    private PasswordField nickName;
    @FXML
    private Label errorNickName;
    @FXML
    private StackPane visiblePassword;
    @FXML
    private PasswordField pPassword;
    @FXML
    private Label errorPassword;
    @FXML
    private ToggleButton buttomVer;
    @FXML
    private PasswordField correoUser;
    @FXML
    private Label errorCorreo;
    @FXML
    private Button perfilButtom;
    @FXML
    private ImageView imageController;
    @FXML
    private TextField txtLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Prueba de proteccion a master");
    }    

    @FXML
    private void verPassword(ActionEvent event) {
        //Se cambia ya la imagen de los ojos pero cuando desaparece la passwordField no se ve el textField con la contraseña
        // no se si es que no se llega a escribir o me falta algo pero bueno ya lo veré
        
        txtLabel.setVisible(!txtLabel.isVisible());
	pPassword.setVisible(!pPassword.isVisible());
	Image ver = new Image(new File("src/icons/ojo abierto black.png").toURI().toString());
	Image nover = new Image(new File("src/icons/ojo cerrado.png").toURI().toString());
	if(buttomVer.isSelected()) {
	    imageController.setImage(ver);
	} else {
	    imageController.setImage(nover);
	}
    }

    @FXML
    private void aceptoRegistro(ActionEvent event) {
    }

    @FXML
    private void imagePerfilPressed(ActionEvent event) {
        
        //Aqui cuando el user le de al boton apra poner una foto de usuario
        // lo unico que habrá que hacer será dejarle poder poner una imagen en su perfil
        //podrá poner una imagen suya desde sus archivos o elegir alguno de las que pongamos por defecto
        
    }
    
}
