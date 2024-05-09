/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author berjo
 */
public class FXMLSignUpController implements Initializable {

    @FXML
    private TextField eemail;
    @FXML
    private Label lInconrrectEmail;
    @FXML
    private PasswordField ppassword;
    @FXML
    private Label lIncorrectPassword;
    @FXML
    private PasswordField ppassword2;
    @FXML
    private Label lpassDifferent;
    @FXML
    private PasswordField ppassword21;
    @FXML
    private Button bCancel;
    @FXML
    private Button bAccept;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleBAcceptOnAction(ActionEvent event) {
    }
    
}
