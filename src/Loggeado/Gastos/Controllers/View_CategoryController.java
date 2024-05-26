/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Loggeado.Gastos.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.Acount;
import model.AcountDAOException;
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
    private Button remove_button;
    @FXML
    private Button cancel_button;
    
    Category ActiveCategory;
    private List<Category> categories;
    
    @FXML
    private ListView<String> list_view_categories;
    @FXML
    private Button add_button;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
//-----------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        update();     
        
    list_view_categories.setOnMouseClicked(e -> {
            int index = list_view_categories.getSelectionModel().getSelectedIndex();
            System.out.println("Index: " + index);
            if (index >= 0) {
                ActiveCategory = categories.get(index);

                System.out.println("Active Category: " + ActiveCategory.getName());

                remove_button.setDisable(false);

            } else {
                disableActive();
            }
            /*
            if (e.getClickCount() == 2) {
                if (index >= 0) {
                    ActiveCategory = categories.get(index);
                    try {
                        CategoryInfoController c = JavaFXMLApplication.cambiarVentana(JavaFXMLApplication.CATEGORIAINFO).getController();
                        c.setCategory(ActiveCategory);
                        c.setReturn(JavaFXMLApplication.CATEGORIALISTA);
                        ActiveCategory = null;
                        disableActive();
                    } catch ( IOException ex) {
                        Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }*/
        });       
    }       

//-----------------------------------------------------------------------
    @FXML
    private void remove_category(ActionEvent event) throws IOException {
        
            try {
                // Alert confirmation
                Acount acc = Acount.getInstance();
                acc.removeCategory(ActiveCategory);
                ActiveCategory = null;
                update();
                ActiveCategory = null;
                disableActive();
            } catch (AcountDAOException ex) {
                ex.printStackTrace();         
            }        
    }
//-----------------------------------------------------------------------
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
//-----------------------------------------------------------------------    
    @FXML
    private void add_category(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCategory.fxml"));
            Parent homeRoot = loader.load();
            hbox_panel.getChildren().clear();
            hbox_panel.getChildren().add(homeRoot);
        } catch (Exception e) {
            e.printStackTrace();
        }
        update();
        ActiveCategory = null;
        disableActive();
    }
//-----------------------------------------------------------------------     
    public void disableActive() {
        remove_button.setDisable(true);
    }    
//----------------------------------------------------------------------- 
    public void update() {
        // Remove all
        list_view_categories.getItems().removeAll(list_view_categories.getItems());
        // Get all categories
        Acount acc = null;
        try {
            acc = Acount.getInstance();
            categories = acc.getUserCategories();
            for (Category category : categories) {
                list_view_categories.getItems().add(category.getName());
            }
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }
//-----------------------------------------------------------------------     

    @FXML
    private void add_exited(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        add_button.getStyleClass().remove("boton_enfocado_subir_imagen");
        add_button.getStyleClass().add("boton_desenfocado_subir_imagen"); 
    }

    @FXML
    private void add_entered(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        add_button.getStyleClass().remove("boton_desenfocado_subir_imagen");
        add_button.getStyleClass().add("boton_enfocado_subir_imagen");         
    }

    @FXML
    private void remove_exited(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        remove_button.getStyleClass().remove("boton_enfocado_subir_imagen");
        remove_button.getStyleClass().add("boton_desenfocado_subir_imagen");         
    }

    @FXML
    private void remove_entered(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        remove_button.getStyleClass().remove("boton_desenfocado_subir_imagen");
        remove_button.getStyleClass().add("boton_enfocado_subir_imagen");        
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
    
}
    

