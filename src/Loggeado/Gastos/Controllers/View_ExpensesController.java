/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Loggeado.Gastos.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import model.Charge;
import modeling.SharedData;

/**
 * FXML Controller class
 *
 * @author Roberto Casinos
 */
public class View_ExpensesController implements Initializable {

    @FXML
    private HBox hbox_panel;
    @FXML
    private Button edit_button;
    @FXML
    private Button remove_button;
    @FXML
    private Button cancel_button;
    @FXML
    private ListView<Charge> list_view_expenses;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
//-----------------------------------------------------------------------    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        ObservableList<Charge> charges = (ObservableList<Charge>) SharedData.getInstance().getCharges();
        list_view_expenses.setItems(charges);
        list_view_expenses.setCellFactory(param -> new ListCell<Charge>() {
        @Override
        protected void updateItem(Charge charge, boolean empty) {
            
            super.updateItem(charge, empty);
            if (empty || charge == null) {
                setText(null);
            } else {
                setText("Name: " +charge.getName() + " - " + charge.getCategory().getName() + " - " + charge.getDate() + " - " + charge.getDescription() + " - $" + (charge.getUnits() * charge.getCost()));
            }
        }
        });
        
    } 
//-----------------------------------------------------------------------
    
//-----------------------------------------------------------------------
    @FXML
    private void edit_expense(ActionEvent event) {
    }
//-----------------------------------------------------------------------
    @FXML
    private void remove_expense(ActionEvent event) {
    }
//-----------------------------------------------------------------------
    @FXML
    private void cancelButtonPressed(ActionEvent event) {       
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddExpense.fxml"));
            Parent homeRoot = loader.load();
            hbox_panel.getChildren().clear();
            hbox_panel.getChildren().add(homeRoot);
        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
//-----------------------------------------------------------------------    
}
