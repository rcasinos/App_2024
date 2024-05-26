/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Loggeado.Gastos.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;
import model.Category;
import model.Charge;
import modeling.SharedData;

/**
 * FXML Controller class
 *
 * @author Roberto Casinos
 */
public class Expense_viewController implements Initializable {

    @FXML
    private HBox hbox_panel;
    @FXML
    private TextField text_field_name;
    @FXML
    private TextField text_fiel_description;
    @FXML
    private TextField text_fiel_cost;
    @FXML
    private MenuButton category_pciker;
    @FXML
    private TextField text_field_units;
    @FXML
    private ImageView ticket_image;
    @FXML
    private Button remove_img_button;
    @FXML
    private Button change_photo_button;
    @FXML
    private Button save_button;
    @FXML
    private Button edit_button;
    @FXML
    private Button remove_button;
    @FXML
    private Button cancel_button;
    @FXML
    private DatePicker data_expense;
    
    private Category expenseCategory;
    
        
    private Charge charge;
    private Acount acc;
//---------------------------------------------------------------------------------------------------- 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){ 
            
        //Obtenemos el charge que ha sido clickado
        charge = SharedData.getInstance().getActiveCharge();
        
        text_field_name.setText(charge.getName());
        text_fiel_cost.setText(String.valueOf(charge.getCost()));
        text_fiel_description.setText(charge.getDescription());
        text_field_units.setText(String.valueOf(charge.getUnits()));
        //ticket_image.setId(charge.getId());
        //category_pciker.setText(charge.getCategory());
        
    }    
//----------------------------------------------------------------------------------------------------     
    @FXML
    private void remove_img_exited(MouseEvent event) {
                
        //Modificamos el estilo del boton al entrar en el
        remove_img_button.getStyleClass().remove("boton_enfocado_subir_imagen");
        remove_img_button.getStyleClass().add("boton_desenfocado_subir_imagen"); 
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void remove_img_entered(MouseEvent event) {
        //Modificamos el estilo del boton al entrar en el
        remove_img_button.getStyleClass().remove("boton_desenfocado_subir_imagen");
        remove_img_button.getStyleClass().add("boton_enfocado_subir_imagen"); 
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void remove_img_click(MouseEvent event) {
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void change_photo_exited(MouseEvent event) {
                
        //Modificamos el estilo del boton al entrar en el
        change_photo_button.getStyleClass().remove("boton_enfocado_subir_imagen");
        change_photo_button.getStyleClass().add("boton_desenfocado_subir_imagen"); 
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void change_button_click(MouseEvent event) {
        
        FileChooser fileChooser = new FileChooser(); 
        
        //hay que configurar para que el fileChooser solo permita formatos de imagen. Primero crear el filtro y luego añadirlo
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de imagen (*.png, *.jpg, *.jpge)", "*.png", "*.jpg", "*.jpge"); 
        fileChooser.getExtensionFilters().add(extFilter);
        
        //Mostrar la forma de seleccionar el archivo
        File file  = fileChooser.showOpenDialog(new Stage()); 
        
        if (file != null) {
            Image image = new Image(file.toURI().toString()); 
            ticket_image.setImage(image); 
        }        
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void save_exited(MouseEvent event) {
                
        //Modificamos el estilo del boton al entrar en el
        save_button.getStyleClass().remove("boton_enfocado_registro");
        save_button.getStyleClass().add("boton_desenfocado_registro"); 
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void save_entered(MouseEvent event) {
         
        //Modificamos el estilo del boton al entrar en el
        save_button.getStyleClass().remove("boton_desenfocado_registro");
        save_button.getStyleClass().add("boton_enfocado_registro"); 
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void save_change_expense_click(MouseEvent event) throws AcountDAOException, IOException { 
        
        boolean validExpense = validateNumericField(text_fiel_cost);
        boolean validUnits = validateNumericField(text_field_units);

        if (validExpense && validUnits) {
                       
               try {
                    acc = Acount.getInstance();
                    charge.setName(text_field_name.getText());
                    charge.setCost(Double.parseDouble(text_fiel_cost.getText()));
                    charge.setUnits(Integer.parseInt(text_field_units.getText()));
                    charge.setDescription(text_fiel_description.getText());
                    charge.setDate(data_expense.getValue());
                    //charge.setCategory(category_pciker.));
                    charge.setImageScan(ticket_image.getImage());
                    
                    //acc.updateCharge(charge);
               }catch (AcountDAOException ex) {
                     
               }  
                             
        } else {
            System.out.println("Failed to add an expense");
        }  
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void edit_exited(MouseEvent event) {
                
        //Modificamos el estilo del boton al entrar en el
        edit_button.getStyleClass().remove("boton_enfocado_registro");
        edit_button.getStyleClass().add("boton_desenfocado_registro"); 
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void edit_entered(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        edit_button.getStyleClass().remove("boton_desenfocado_registro");
        edit_button.getStyleClass().add("boton_enfocado_registro"); 
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void edit_expense(MouseEvent event) {
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void remove_exp_exited(MouseEvent event) {
                
        //Modificamos el estilo del boton al entrar en el
        remove_button.getStyleClass().remove("boton_enfocado_subir_imagen");
        remove_button.getStyleClass().add("boton_desenfocado_subir_imagen"); 
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void remove_exp_entered(MouseEvent event) {
        //Modificamos el estilo del boton al entrar en el
        remove_button.getStyleClass().remove("boton_desenfocado_subir_imagen");
        remove_button.getStyleClass().add("boton_enfocado_subir_imagen"); 
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void remove_expense_click(MouseEvent event) { 
        
        try {
            Acount acc2 = Acount.getInstance();
            acc2.removeCharge(charge);
            // JavaFXMLApplication.showAlert("Cargo eliminado", "Cargo eliminado correctamente", "El cargo ha sido eliminado correctamente");
            // Click on back
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_view.fxml"));
                Parent homeRoot = loader.load();
                hbox_panel.getChildren().clear();
                hbox_panel.getChildren().add(homeRoot);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(AcountDAOException ex) {
        ex.printStackTrace();
        }catch (IOException ex) {
        ex.printStackTrace();                
        }
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void cancel_exited(MouseEvent event) {
                
        //Modificamos el estilo del boton al entrar en el
        cancel_button.getStyleClass().remove("boton_enfocado_registro");
        cancel_button.getStyleClass().add(".boton_desenfocado_registro"); 
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void cancel_entered(MouseEvent event) {
                
        //Modificamos el estilo del boton al entrar en el
        cancel_button.getStyleClass().remove("boton_enfocado_registro");
        cancel_button.getStyleClass().add(".boton_desenfocado_registro"); 
    }
//---------------------------------------------------------------------------------------------------- 
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
//-----------------------------------------------------------------------------------
    // metodo para validar los campos que solo admitan numeros
    // no funciona para el units ns porque
    private boolean validateNumericField(TextField textField) {
        String text = textField.getText();
        if (text.isEmpty() || !text.matches("\\d+")) {
            textField.getStyleClass().add("expenses-wrong-field");
            textField.getStyleClass().removeAll("expenses-normal-field");
            return false;
        } else {
            textField.getStyleClass().add("expenses-normal-field");
            textField.getStyleClass().removeAll("expenses-wrong-field");
            return true;
        }
    }    
    
//-----------------------------------------------------------------------------------    
    //si clicamos la opcion de nueva categoria nos manda al fxml de AddCategory
    private void handleAddCategory() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCategory.fxml"));
            Parent homeRoot = loader.load();
            hbox_panel.getChildren().clear();
            hbox_panel.getChildren().add(homeRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
    
//-----------------------------------------------------------------------------------   
    @FXML
    private void visualize_categories(MouseEvent event) throws AcountDAOException, IOException {
                
        //obtenemos las categorias del usuario que esta logeado
        List<Category> loggedUserCategories = Acount.getInstance().getUserCategories();
        
        // Limpiar los items existentes
        category_pciker.getItems().clear();
        
        for (Category category : loggedUserCategories) {
            MenuItem menuItem = new MenuItem(category.getName());
            menuItem.setOnAction(e -> {
                category_pciker.setText(category.getName()); 
                expenseCategory = category;
            });
            category_pciker.getItems().add(menuItem);
        }
        
        //añadimos la opcion de nueva categoria
        MenuItem addNewCategoryItem = new MenuItem("New Category");
        System.out.println("Se esta creando una categoria");
        addNewCategoryItem.setOnAction(e -> handleAddCategory());
        category_pciker.getItems().add(addNewCategoryItem); 
        
    }

    @FXML
    private void change_photo_entered(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        change_photo_button.getStyleClass().remove("boton_enfocado_registro");
        change_photo_button.getStyleClass().add(".boton_desenfocado_registro");
    }
}
