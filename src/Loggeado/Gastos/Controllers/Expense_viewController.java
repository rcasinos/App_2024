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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
    private Category catTmp;
    @FXML
    private StackPane stack_pane;
    @FXML
    private ChoiceBox<String> choice_box_category;
//---------------------------------------------------------------------------------------------------- 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){ 
            
        //Obtenemos el charge que ha sido clickado
        charge = SharedData.getInstance().getActiveCharge();
        
        // Update interface
        text_field_name.setText(charge.getName());
        text_field_units.setText(String.valueOf(charge.getUnits()));
        text_fiel_cost.setText(String.valueOf(charge.getCost()));
        text_fiel_description.setText(charge.getDescription());
        data_expense.setValue(charge.getDate());
        choice_box_category.setValue(charge.getCategory().getName());
        ticket_image.setImage(charge.getImageScan());
        
        //Iniciamos en que no se puedan editar
        text_field_name.setEditable(false);
        text_field_units.setEditable(false);
        text_fiel_description.setEditable(false);
        text_fiel_cost.setEditable(false);
        //Las dos raras
        choice_box_category.setDisable(true);
        data_expense.setEditable(false);
        
    }    
//----------------------------------------------------------------------------------------------------     
    @FXML
    private void remove_img_exited(MouseEvent event) {
                
        //Modificamos el estilo del boton al entrar en el
        remove_img_button.getStyleClass().remove("boton_enfocado_registro");
        remove_img_button.getStyleClass().add("boton_desenfocado_registro"); 
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void remove_img_entered(MouseEvent event) {
        //Modificamos el estilo del boton al entrar en el
        remove_img_button.getStyleClass().remove("boton_desenfocado_registro");
        remove_img_button.getStyleClass().add("boton_enfocado_registro"); 
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void remove_img_click(MouseEvent event) {
        
        Image newImage = new Image(getClass().getResource("/Iconos_App/ticket.jpg").toExternalForm());
        ticket_image.setImage(newImage);   
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void change_photo_exited(MouseEvent event) {
                
        //Modificamos el estilo del boton al entrar en el
        change_photo_button.getStyleClass().remove("boton_enfocado_registro");
        change_photo_button.getStyleClass().add("boton_desenfocado_registro"); 
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
        save_button.getStyleClass().remove("boton_enfocado_subir_imagen");
        save_button.getStyleClass().add("boton_desenfocado_subir_imagen"); 
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void save_entered(MouseEvent event) {
         
        //Modificamos el estilo del boton al entrar en el
        save_button.getStyleClass().remove("boton_desenfocado_subir_imagen");
        save_button.getStyleClass().add("boton_enfocado_subir_imagen"); 
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void save_change_expense_click(MouseEvent event) throws AcountDAOException, IOException { 
        
        try {
                    // Get all categories
                    this.acc = Acount.getInstance();
                    List<Category> l = acc.getUserCategories();
                    for(Category c : l) {
                        if (c.getName().equals(choice_box_category.getValue())) {
                            catTmp = c;
                        }
                    }

                    // Remove this
                    acc.removeCharge(charge);

                    // Register the new one
                    acc.registerCharge(
                            text_field_name.getText(), 
                            text_fiel_description.getText(),
                            Double.parseDouble(text_fiel_cost.getText()), 
                            Integer.parseInt(text_field_units.getText()), 
                            ticket_image.getImage(),
                            data_expense.getValue(), catTmp);

                } catch (AcountDAOException ex) {
                    ex.printStackTrace();
                }
    }
    
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void edit_exited(MouseEvent event) {
                
        //Modificamos el estilo del boton al entrar en el
        edit_button.getStyleClass().remove("boton_enfocado_subir_imagen");
        edit_button.getStyleClass().add("boton_desenfocado_subir_imagen"); 
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void edit_entered(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        edit_button.getStyleClass().remove("boton_desenfocado_subir_imagen");
        edit_button.getStyleClass().add("boton_enfocado_subir_imagen"); 
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void edit_expense(MouseEvent event) {
        
        if (edit_button.isVisible()) {
            edit_button.setVisible(false);
            save_button.setVisible(true);
        } else {
            edit_button.setVisible(true);
            save_button.setVisible(false);
        }

        text_field_name.setEditable(true);
        text_field_units.setEditable(true);
        text_fiel_description.setEditable(true);
        text_fiel_cost.setEditable(true);
        //Las dos raras
        choice_box_category.setDisable(false);
        data_expense.setEditable(true);
       
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
        cancel_button.getStyleClass().remove("boton_enfocado_cancelar");
        cancel_button.getStyleClass().add("boton_desenfocado_cancelar"); 
    }
//---------------------------------------------------------------------------------------------------- 
    @FXML
    private void cancel_entered(MouseEvent event) {
                
        //Modificamos el estilo del boton al entrar en el
        cancel_button.getStyleClass().remove("boton_desenfocado_cancelar");
        cancel_button.getStyleClass().add("boton_enfocado_cancelar"); 
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
        
        // Obtener las categorías del usuario que está logeado
        List<Category> loggedUserCategories = Acount.getInstance().getUserCategories();

        // Limpiar los items existentes
        choice_box_category.getItems().clear();

        // Agregar las categorías al ChoiceBox
        for (Category category : loggedUserCategories) {
            choice_box_category.getItems().add(category.getName());
        }

        // Añadir la opción de nueva categoría
        choice_box_category.getItems().add("New Category");

        // Manejar la selección del ChoiceBox
        choice_box_category.setOnAction(e -> {
            String selected = choice_box_category.getSelectionModel().getSelectedItem();
            if ("New Category".equals(selected)) {
                handleAddCategory();
            } else {
                for (Category category : loggedUserCategories) {
                    if (category.getName().equals(selected)) {
                        expenseCategory = category;
                        break;
                    }
                }
            }
        });
    }

    @FXML
    private void change_photo_entered(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        change_photo_button.getStyleClass().remove("boton_desenfocado_registro");
        change_photo_button.getStyleClass().add("boton_enfocado_registro");
    }

    @FXML
    private void stack_pane_click(KeyEvent event) {
        
        if (edit_button.isVisible()) {
            edit_button.setVisible(false);
            save_button.setVisible(true);
        } else {
            edit_button.setVisible(true);
            save_button.setVisible(false);
        }

        text_field_name.setEditable(true);
        text_field_units.setEditable(true);
        text_fiel_description.setEditable(true);
        text_fiel_cost.setEditable(true);
        //Las dos raras
        choice_box_category.setDisable(false);
        data_expense.setEditable(true);
    }
    
}
