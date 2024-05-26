/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Loggeado.Gastos.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
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
import javafx.util.Callback;
import javafx.util.StringConverter;
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
    @FXML
    private DatePicker data_expense;
    @FXML
    private ChoiceBox<String> choice_box_category;
    @FXML
    private Button cancel_button;

    /**
     * Initializes the controller class.
     */
//-----------------------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Convertidor de cadena para formatear la fecha
        String pattern = "yyyy-MM-dd";
        data_expense.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
        
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
            // Crear una alerta de confirmación
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmación de Gasto");
            alert.setHeaderText("Confirmar Registro de Gasto");
            alert.setContentText("¿Estás seguro de que deseas añadir este gasto?");

            // Mostrar la alerta y esperar la respuesta del usuario
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // El usuario confirmó, proceder con la adición del gasto
                String expenseName = text_field_name.getText();
                String expenseDescription = text_fiel_description.getText();
                double expenseAmount = Double.parseDouble(text_fiel_cost.getText());
                int expenseUnits = Integer.parseInt(text_field_units.getText());

                // Obtener la fecha seleccionada en el DatePicker
                LocalDate selectedDate = data_expense.getValue();
                LocalDate expenseDate = (selectedDate != null) ? selectedDate : LocalDate.now();

                Image expenseTicket = ticket_image.getImage();

                boolean isCorrectAdded = Acount.getInstance().registerCharge(expenseName, expenseDescription, expenseAmount, expenseUnits, expenseTicket, expenseDate, expenseCategory);

                if (isCorrectAdded) {
                    System.out.println("Gasto añadido correctamente");
                     // Limpiar todos los campos
                    text_field_name.clear();
                    text_fiel_description.clear();
                    text_fiel_cost.clear();
                    text_field_units.clear();
                    data_expense.setValue(null);
                    ticket_image.setImage(null); // Asumiendo que quieres limpiar la imagen del ticket
                    
                } else {
                    System.out.println("Fallo al añadir el gasto");
                }
            } else {
                // El usuario canceló, no hacer nada
                System.out.println("Registro de gasto cancelado");
            }
        } else {
            System.out.println("Fallo al añadir el gasto: campos inválidos");
        }  
    }

//-----------------------------------------------------------------------------------   

    @FXML
    private void remove_img_exited(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        remove_img_button.getStyleClass().remove("boton_enfocado_registro");
        remove_img_button.getStyleClass().add("boton_desenfocado_registro");         
    }

    @FXML
    private void remove_img_entered(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        remove_img_button.getStyleClass().remove("boton_desenfocado_registro");
        remove_img_button.getStyleClass().add("boton_enfocado_registro");         
    }

    @FXML
    private void remove_img_click(MouseEvent event) {
        
        Image newImage = new Image(getClass().getResource("/Iconos_App/ticket.jpg").toExternalForm());
        ticket_image.setImage(newImage);     
    }

    @FXML
    private void change_photo_exited(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        change_photo_button.getStyleClass().remove("boton_enfocado_registro");
        change_photo_button.getStyleClass().add("boton_desenfocado_registro");         
    }

    @FXML
    private void change_mouse_entered(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        change_photo_button.getStyleClass().remove("boton_desenfocado_registro");
        change_photo_button.getStyleClass().add("boton_enfocado_registro");         
    }

    @FXML
    private void add_exited(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        save_button.getStyleClass().remove("boton_enfocado_subir_imagen");
        save_button.getStyleClass().add("boton_desenfocado_subir_imagen");         
    }

    @FXML
    private void add_entered(MouseEvent event) {
         
        //Modificamos el estilo del boton al entrar en el
        save_button.getStyleClass().remove("boton_desenfocado_subir_imagen");
        save_button.getStyleClass().add("boton_enfocado_subir_imagen");        
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
