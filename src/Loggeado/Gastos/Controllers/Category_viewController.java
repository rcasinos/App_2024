/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Loggeado.Gastos.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Roberto Casinos
 */
public class Category_viewController implements Initializable {

    @FXML
    private HBox hbox_panel;
    @FXML
    private TextField categoryName;
    @FXML
    private TextField categoryDescription;
    @FXML
    private Button save_button;
    @FXML
    private Button edit_button;
    @FXML
    private Button remove_button;
    @FXML
    private Button cancel_button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void save_exited(MouseEvent event) {
    }

    @FXML
    private void save_entered(MouseEvent event) {
    }

    @FXML
    private void save_change_expense_click(MouseEvent event) {
        
    }

    @FXML
    private void edit_exited(MouseEvent event) {
    }

    @FXML
    private void edit_entered(MouseEvent event) {
    }

    @FXML
    private void edit_expense(MouseEvent event) {
    }

    @FXML
    private void remove_exp_exited(MouseEvent event) {
    }

    @FXML
    private void remove_exp_entered(MouseEvent event) {
    }

    @FXML
    private void remove_expense_click(MouseEvent event) {
    }

    @FXML
    private void cancel_exited(MouseEvent event) {
    }

    @FXML
    private void cancel_entered(MouseEvent event) {
    }

    @FXML
    private void cancelButtonPressed(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_view.fxml"));
            Parent homeRoot = loader.load();
            hbox_panel.getChildren().clear();
            hbox_panel.getChildren().add(homeRoot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
