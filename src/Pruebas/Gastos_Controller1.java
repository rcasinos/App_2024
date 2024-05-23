package Pruebas;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Acount;
import model.AcountDAO;
import model.AcountDAOException;
import model.Category;
import model.Charge;
import model.User;
import modeling.MyChargeModel;
import modeling.SharedData;

public class Gastos_Controller1 implements Initializable {

    @FXML
    private TableView<Charge> expenses_tableview;
    @FXML
    private TableColumn<Charge, String> name_column;
    @FXML
    private TableColumn<Charge, String> category_column;
    @FXML
    private TableColumn<Charge, String> color_column;
    @FXML
    private TableColumn<Charge, Double> cost_column;
    @FXML
    private Button add_expense_button;
    @FXML
    private Button create_category_button;
    @FXML
    private Button edit_expense_button;
    @FXML
    private Button remove_exp_button;
    @FXML
    private Button remove_img_button;
    @FXML
    private Button change_photo_button;

    private ObservableList<Charge> chargeList;
    private User loggedUser;
    private Acount acc;
    private Category expenseCategory;
    
    @FXML
    private HBox hbox_titulo;
    @FXML
    private Button view_all_expenses;
    @FXML
    private Button view_all_categories_button;
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
    private HBox hbox_panel;
    @FXML
    private Button save_button;
    @FXML
    private ImageView ticket_image;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        hbox_panel.setVisible(false);
        
        //Obtener la instancia de acount y el usuario logg
        try{
        Acount acc = Acount.getInstance();
        loggedUser = acc.getLoggedUser();
        
        //Obtener la lista de cargos del usuario logg
        chargeList = FXCollections.observableArrayList(acc.getUserCharges());
        
        } catch (AcountDAOException | IOException e){
            e.printStackTrace();
        }
        

        // Setup table columns
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        category_column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory().getName()));
        color_column.setCellValueFactory(new PropertyValueFactory<>("description")); // Assuming color is in description
        cost_column.setCellValueFactory(cellData -> {
            Charge charge = cellData.getValue();
            double total = charge.getUnits() * charge.getCost();
            return new SimpleDoubleProperty(total).asObject();
        });        
        
        // Crear una animación de cambio de color
        Timeline colorTransition = new Timeline(
            new KeyFrame(Duration.seconds(0), 
                new KeyValue(hbox_titulo.styleProperty(), "-fx-background-color: #ffffff;")),
            new KeyFrame(Duration.seconds(3), 
                new KeyValue(hbox_titulo.styleProperty(), "-fx-background-color: #f0f8ff;")), // Muy ligero azul
            new KeyFrame(Duration.seconds(6), 
                new KeyValue(hbox_titulo.styleProperty(), "-fx-background-color: #e0f0ff;")), // Ligero azul
            new KeyFrame(Duration.seconds(9), 
                new KeyValue(hbox_titulo.styleProperty(), "-fx-background-color: #d0e8ff;")), // Azul claro
            new KeyFrame(Duration.seconds(12), 
                new KeyValue(hbox_titulo.styleProperty(), "-fx-background-color: #ffffff;")) // De vuelta a blanco
        );
        colorTransition.setCycleCount(Timeline.INDEFINITE);
        colorTransition.setAutoReverse(true);

        // Iniciar la animación
        colorTransition.play();
    }
    
    private void loadUserCharges() throws AcountDAOException, IOException {
        
        List<Charge> userCharges = Acount.getInstance().getUserCharges();
        chargeList = FXCollections.observableArrayList(userCharges);
        SharedData.getInstance().getCharges().setAll(userCharges);
        expenses_tableview.setItems(chargeList);
    }

    // Método para actualizar el TableView con la lista de cargos actualizada
    public void updateTableView() {
        try {
            loadUserCharges(); // Vuelve a cargar los cargos del usuario
        } catch (AcountDAOException | IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void add_expense_exited(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        add_expense_button.getStyleClass().remove("boton_enfocado_subir_imagen");
        add_expense_button.getStyleClass().add("boton_desenfocado_subir_imagen");
    }

    @FXML
    private void add_expense_entered(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        add_expense_button.getStyleClass().remove("boton_desenfocado_subir_imagen");
        add_expense_button.getStyleClass().add("boton_enfocado_subir_imagen");
    }

    @FXML
    private void add_expense_click(MouseEvent event) {
        
    //CAMBIO DE ESCENA 
            try {
                // Cargar el archivo FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Loggeado/Gastos/Controller/AddExpense.fxml"));
                Node ventana = loader.load(); // Obtener el nodo raíz del archivo FXML

                //Verificar si el nodo raiz es de tipo Region
                if (ventana instanceof Region){
                    Region region = (Region) ventana;

                    //Vincular tamaño nueva ventana con el tamaño panel principal
                    region.prefWidthProperty().bind(hbox_panel.widthProperty());
                    region.prefHeightProperty().bind(hbox_panel.heightProperty());
                }
                // Agregar la ventana al Pane
                if (hbox_panel.getChildren() != null){
                    //Borrar la ventana anterior
                    hbox_panel.getChildren().clear();
                }
                hbox_panel.getChildren().add(ventana);

                // Ahora puedes acceder a los métodos y variables públicas del controlador de la ventana incrustada
            } catch (IOException e) {
                e.printStackTrace();
            }
        
    }

    @FXML
    private void create_category_exited(MouseEvent event) {
        //Modificamos el estilo del boton al entrar en el
        create_category_button.getStyleClass().remove("boton_enfocado_subir_imagen");
        create_category_button.getStyleClass().add("boton_desenfocado_subir_imagen");
    }

    @FXML
    private void create_category_entered(MouseEvent event) {
        //Modificamos el estilo del boton al entrar en el
        create_category_button.getStyleClass().remove("boton_desenfocado_subir_imagen");
        create_category_button.getStyleClass().add("boton_enfocado_subir_imagen");
    }

    @FXML
    private void create_category_click(MouseEvent event) {
        //Modificamos el estilo del boton al entrar en el
        showAlert("Create Category", "Category creation logic goes here.");
    }

    @FXML
    private void edit_expense_exited(MouseEvent event) {
        //Modificamos el estilo del boton al entrar en el
        edit_expense_button.getStyleClass().remove("boton_enfocado_subir_imagen");
        edit_expense_button.getStyleClass().add("boton_desenfocado_subir_imagen");
    }

    @FXML
    private void edit_expense_entered(MouseEvent event) {
        //Modificamos el estilo del boton al entrar en el
        edit_expense_button.getStyleClass().remove("boton_desenfocado_subir_imagen");
        edit_expense_button.getStyleClass().add("boton_enfocado_subir_imagen");
    }

    @FXML
    private void edit_exp_click(MouseEvent event) {
        // Add logic for editing an existing expense
        Charge selectedCharge = expenses_tableview.getSelectionModel().getSelectedItem();
        if (selectedCharge != null) {
            selectedCharge.setName("Edited Name");
            expenses_tableview.refresh();
        } else {
            showAlert("Edit Expense", "No expense selected.");
        }
    }

    @FXML
    private void remove_exp_exited(MouseEvent event) {
        //Modificamos el estilo del boton al entrar en el
        remove_exp_button.getStyleClass().remove("boton_enfocado_subir_imagen");
        remove_exp_button.getStyleClass().add("boton_desenfocado_subir_imagen");
    }

    @FXML
    private void remove_exp_entered(MouseEvent event) {
        //Modificamos el estilo del boton al entrar en el
        remove_exp_button.getStyleClass().remove("boton_desenfocado_subir_imagen");
        remove_exp_button.getStyleClass().add("boton_enfocado_subir_imagen");
    }

    @FXML
    private void remove_exp_click(MouseEvent event) {
        /*
        // Add logic for removing an expense
        Charge selectedCharge = expenses_tableview.getSelectionModel().getSelectedItem();
        if (selectedCharge != null) {
            chargeList.remove(selectedCharge);
            loggedUser.removeCharge(selectedCharge);
        } else {
            showAlert("Remove Expense", "No expense selected.");
        }
        */
    }

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
        // Add logic for removing an image from an expense
        Charge selectedCharge = expenses_tableview.getSelectionModel().getSelectedItem();
        if (selectedCharge != null) {
            selectedCharge.setImageScan(null);
            expenses_tableview.refresh();
        } else {
            showAlert("Remove Image", "No expense selected.");
        }
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
    private void change_button_click(MouseEvent event) {
        // Add logic for changing the photo of an expense
        showAlert("Change Photo", "Change photo logic goes here.");
        
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

    private void showAlert(String title, String message) {
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void view_expense_exited(MouseEvent event) {

        view_all_expenses.getStyleClass().remove("boton_enfocado_registro");
        view_all_expenses.getStyleClass().add("boton_desenfocado_registro");        
    }

    @FXML
    private void view_expense_entered(MouseEvent event) {
        
        view_all_expenses.getStyleClass().remove("boton_desenfocado_registro");
        view_all_expenses.getStyleClass().add("boton_enfocado_registro");
    }

    @FXML
    private void view_expense_click(MouseEvent event) {
        
    }

    @FXML
    private void view_category_exited(MouseEvent event) {
        
        view_all_categories_button.getStyleClass().remove("boton_enfocado_registro");
        view_all_categories_button.getStyleClass().add("boton_desenfocado_registro");        
    }

    @FXML
    private void view_category_entered(MouseEvent event) {
        
        view_all_categories_button.getStyleClass().remove("boton_desenfocado_registro");
        view_all_categories_button.getStyleClass().add("boton_enfocado_registro");        
    }

    @FXML
    private void view_all_categories(MouseEvent event) {
        
    }

    @FXML
    private void save_change_expense_click(MouseEvent event) throws AcountDAOException, IOException  {
        boolean validExpense = validateNumericField(text_fiel_cost);
        boolean validUnits = validateNumericField(text_field_units);
        
        
        if (validExpense && validUnits) {
            String expenseName = text_field_name.getText();
            String expenseDescription = text_fiel_description.getText();
            double expenseAmount = Double.parseDouble(text_fiel_cost.getText()); 
            int expenseUnits = Integer.parseInt(text_field_units.getText());
            LocalDate expenseDate = LocalDate.now();
            Image expenseTicket = ticket_image.getImage();

            boolean isCorrectAdded = Acount.getInstance().registerCharge(expenseName, expenseDescription, expenseAmount, expenseUnits, expenseTicket, expenseDate, expenseCategory);
                     
        } else {
            System.out.println("Failed to add an expense");
        }
        
        hbox_panel.setVisible(true);
    }

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

    @FXML
    private void visualize_categories(ActionEvent event) throws AcountDAOException, IOException {
        
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
        /*
        MenuItem addNewCategoryItem = new MenuItem("New Category");
        addNewCategoryItem.setOnAction(e -> handleAddCategory());
        category_pciker.getItems().add(addNewCategoryItem); */
    }
    
}


