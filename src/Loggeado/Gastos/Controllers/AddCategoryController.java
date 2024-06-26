/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Loggeado.Gastos.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.Acount;
import model.AcountDAOException;
import model.Charge;
import modeling.SharedData;

/**
 * FXML Controller class
 *
 * @author Roberto Casinos
 */
public class AddCategoryController implements Initializable {

    @FXML
    private HBox hbox_panel;
    @FXML
    private TextField categoryName;
    @FXML
    private TextField categoryDescription;
    @FXML
    private Button cancel_button;
    @FXML
    private Button saveChangesButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    
    //creamos una categoría y si todo sale correctamente enviamos un mensaje de "categoria creada correctamente" 
    //para salir a la ventana de gastos lo haremos con el boton de "atras"
    @FXML
    private void createCategory(ActionEvent event) throws AcountDAOException, IOException {
        boolean isCategoryCreated = false; 
     //---------------------------------------------------

        if(categoryName.getText() != null && categoryDescription.getText() != null) {
            isCategoryCreated = Acount.getInstance().registerCategory(categoryName.getText(), categoryDescription.getText());
        }
        //---------------------------------------------------
        if (isCategoryCreated) {
            //correctLabel.setVisible(true);
            //Effects.createFadeAnimation(correctLabel);
            System.out.println("Category succesfully created");
        } else {
            //errorLabel.setVisible(true);
            //Effects.createFadeAnimation(errorLabel);
            System.out.println("Something went wrong");
        }
    //---------------------------------------------------
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("View_Category.fxml"));
            Parent homeRoot = loader.load();
            hbox_panel.getChildren().clear();
            hbox_panel.getChildren().add(homeRoot);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void cancel_exited(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        cancel_button.getStyleClass().remove("boton_enfocado_registro");
        cancel_button.getStyleClass().add("boton_desenfocado_registro");          
    }

    @FXML
    private void cancel_entered(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        cancel_button.getStyleClass().remove("boton_desenfocado_registro");
        cancel_button.getStyleClass().add("boton_enfocado_registro");          
    }

    @FXML
    private void add_exited(MouseEvent event) {
         
        //Modificamos el estilo del boton al entrar en el
        saveChangesButton.getStyleClass().remove("boton_enfocado_subir_imagen");
        saveChangesButton.getStyleClass().add("boton_desenfocado_subir_imagen");         
    }

    @FXML
    private void add_entered(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        saveChangesButton.getStyleClass().remove("boton_desenfocado_subir_imagen");
        saveChangesButton.getStyleClass().add("boton_enfocado_subir_imagen");          
    }
    
 
    
}
