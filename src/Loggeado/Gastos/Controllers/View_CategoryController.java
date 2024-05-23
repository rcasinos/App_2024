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
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import model.Category;
import modeling.SharedCategoryData;

/**
 * FXML Controller class
 *
 * @author Roberto Casinos
 */
public class View_CategoryController implements Initializable {

    @FXML
    private HBox hbox_panel;
    @FXML
    private Button edit_button;
    @FXML
    private Button remove_button;
    @FXML
    private Button cancel_button;
    @FXML
    private ListView<Category> list_view_categories;

    /**
     * Initializes the controller class.
     */
//-----------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCategories();
    }    
//-----------------------------------------------------------------------
    private void loadCategories() {
       ObservableList<Category> categories = SharedCategoryData.getInstance().getCategories();
       list_view_categories.setItems(categories);
    } 
    
//-----------------------------------------------------------------------    
    @FXML
    private void edit_category(ActionEvent event) {
    }
//-----------------------------------------------------------------------
    @FXML
    private void remove_category(ActionEvent event) {
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
    

