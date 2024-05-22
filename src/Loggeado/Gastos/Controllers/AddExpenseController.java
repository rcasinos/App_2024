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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
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

/**
 * FXML Controller class
 *
 * @author Roberto Casinos
 */
public class AddExpenseController implements Initializable {

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
    
    private Category expenseCategory;
    @FXML
    private Button remove_img_button;
    @FXML
    private Button change_photo_button;
    @FXML
    private Button save_button;

    /**
     * Initializes the controller class.
     */
//-----------------------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
//-----------------------------------------------------------------------------------
    @FXML
    private void save_change_expense_click(MouseEvent event) throws AcountDAOException, IOException {
        
        boolean validExpense = validateNumericField(text_fiel_cost);
        boolean validUnits = validateNumericField(text_field_units);
        
        
        if (validExpense && validUnits) {
            System.out.println("1");
            String expenseName = text_field_name.getText();
            String expenseDescription = text_fiel_description.getText();
            double expenseAmount = Double.parseDouble(text_fiel_cost.getText()); 
            int expenseUnits = Integer.parseInt(text_field_units.getText());
            LocalDate expenseDate = LocalDate.now();
            Image expenseTicket = ticket_image.getImage();
            System.out.println("2");
            boolean isCorrectAdded = Acount.getInstance().registerCharge(expenseName, expenseDescription, expenseAmount, expenseUnits, expenseTicket, expenseDate, expenseCategory);
            System.out.println("3");
        } else {
            System.out.println("Failed to add an expense");
        }        
    }
//-----------------------------------------------------------------------------------   

    @FXML
    private void remove_img_exited(MouseEvent event) {
    }

    @FXML
    private void remove_img_entered(MouseEvent event) {
    }

    @FXML
    private void remove_img_click(MouseEvent event) {
    }

    @FXML
    private void change_photo_exited(MouseEvent event) {
    }

    @FXML
    private void change_mouse_entered(MouseEvent event) {
    }

}
