/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Loggeado.Gastos;

import java.io.IOException;
import java.net.URL;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import model.Acount;
import model.AcountDAOException;
import model.Category;
import model.Charge;
import model.User;
import modeling.SharedData;

/**
 * FXML Controller class
 *
 * @author Roberto Casinos
 */
public class Vista_GastosController implements Initializable {

    @FXML
    private HBox hbox_titulo;
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
    private Button view_all_expenses;
    @FXML
    private Button view_all_categories_button;
    @FXML
    private Button edit_expense_button;
    @FXML
    private Button remove_exp_button;
    @FXML
    private HBox hbox_panel;
    
    private ObservableList<Charge> chargeList;
    private User loggedUser;
    private Acount acc;
    private Category expenseCategory;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
//----------------------------------------------------------------------------------------------------    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        color_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        category_column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory().getName()));
        cost_column.setCellValueFactory(cellData -> {
            Charge charge = cellData.getValue();
            double total = charge.getUnits() * charge.getCost();
            return new SimpleDoubleProperty(total).asObject();
        });

        try {
            loadUserCharges();
        } catch (AcountDAOException | IOException ex) {
            ex.printStackTrace();
        }       
        
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
//----------------------------------------------------------------------------------------------------    
    private void loadUserCharges() throws AcountDAOException, IOException {
        List<Charge> userCharges = Acount.getInstance().getUserCharges();
        chargeList = FXCollections.observableArrayList(userCharges);
        SharedData.getInstance().getCharges().setAll(userCharges);
        expenses_tableview.setItems(chargeList);
    }
//----------------------------------------------------------------------------------------------------
    // Método para actualizar el TableView con la lista de cargos actualizada
    public void updateTableView() {
        try {
            loadUserCharges(); // Vuelve a cargar los cargos del usuario
        } catch (AcountDAOException | IOException ex) {
           ex.printStackTrace();
        }
    }
//----------------------------------------------------------------------------------------------------
    @FXML
    private void add_expense_exited(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        add_expense_button.getStyleClass().remove("boton_enfocado_subir_imagen");
        add_expense_button.getStyleClass().add("boton_desenfocado_subir_imagen");        
    }
//----------------------------------------------------------------------------------------------------
    @FXML
    private void add_expense_entered(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        add_expense_button.getStyleClass().remove("boton_desenfocado_subir_imagen");
        add_expense_button.getStyleClass().add("boton_enfocado_subir_imagen");        
    }
//----------------------------------------------------------------------------------------------------
    @FXML
    private void add_expense_click(MouseEvent event) {
        
    //CAMBIO DE ESCENA 
            try {
                // Cargar el archivo FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Loggeado/Gastos/Controllers/AddExpense.fxml"));
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
//----------------------------------------------------------------------------------------------------
    @FXML
    private void create_category_exited(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        create_category_button.getStyleClass().remove("boton_enfocado_subir_imagen");
        create_category_button.getStyleClass().add("boton_desenfocado_subir_imagen");     
    }
//----------------------------------------------------------------------------------------------------
    @FXML
    private void create_category_entered(MouseEvent event) {
        //Modificamos el estilo del boton al entrar en el
        create_category_button.getStyleClass().remove("boton_desenfocado_subir_imagen");
        create_category_button.getStyleClass().add("boton_enfocado_subir_imagen");
    }
//----------------------------------------------------------------------------------------------------
    @FXML
    private void create_category_click(MouseEvent event) {
        
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCategory.fxml"));
            Parent homeRoot = loader.load();
            hbox_panel.getChildren().clear();
            hbox_panel.getChildren().add(homeRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//----------------------------------------------------------------------------------------------------
    @FXML
    private void view_expense_exited(MouseEvent event) {
        
        view_all_expenses.getStyleClass().remove("boton_enfocado_registro");
        view_all_expenses.getStyleClass().add("boton_desenfocado_registro"); 
    }
//----------------------------------------------------------------------------------------------------
    @FXML
    private void view_expense_entered(MouseEvent event) {
        view_all_expenses.getStyleClass().remove("boton_desenfocado_registro");
        view_all_expenses.getStyleClass().add("boton_enfocado_registro");
    }
//----------------------------------------------------------------------------------------------------
    @FXML
    private void view_expense_click(MouseEvent event) {
    }
//----------------------------------------------------------------------------------------------------
    @FXML
    private void view_category_exited(MouseEvent event) {

        view_all_categories_button.getStyleClass().remove("boton_enfocado_registro");
        view_all_categories_button.getStyleClass().add("boton_desenfocado_registro");                
    }
//----------------------------------------------------------------------------------------------------
    @FXML
    private void view_category_entered(MouseEvent event) {
        
        view_all_categories_button.getStyleClass().remove("boton_desenfocado_registro");
        view_all_categories_button.getStyleClass().add("boton_enfocado_registro");          
    }
//----------------------------------------------------------------------------------------------------
    @FXML
    private void view_all_categories(MouseEvent event) {
    }
//----------------------------------------------------------------------------------------------------
    @FXML
    private void edit_expense_exited(MouseEvent event) {
        //Modificamos el estilo del boton al entrar en el
        edit_expense_button.getStyleClass().remove("boton_enfocado_subir_imagen");
        edit_expense_button.getStyleClass().add("boton_desenfocado_subir_imagen");
    }
//----------------------------------------------------------------------------------------------------
    @FXML
    private void edit_expense_entered(MouseEvent event) {
        //Modificamos el estilo del boton al entrar en el
        edit_expense_button.getStyleClass().remove("boton_desenfocado_subir_imagen");
        edit_expense_button.getStyleClass().add("boton_enfocado_subir_imagen");
    }
//----------------------------------------------------------------------------------------------------
    @FXML
    private void edit_exp_click(MouseEvent event) {
    }
//----------------------------------------------------------------------------------------------------
    @FXML
    private void remove_exp_exited(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        remove_exp_button.getStyleClass().remove("boton_enfocado_subir_imagen");
        remove_exp_button.getStyleClass().add("boton_desenfocado_subir_imagen");
    }
//----------------------------------------------------------------------------------------------------
    @FXML
    private void remove_exp_entered(MouseEvent event) {
        //Modificamos el estilo del boton al entrar en el
        remove_exp_button.getStyleClass().remove("boton_desenfocado_subir_imagen");
        remove_exp_button.getStyleClass().add("boton_enfocado_subir_imagen");
    }
//----------------------------------------------------------------------------------------------------
    @FXML
    private void remove_exp_click(MouseEvent event) {
    }
 //----------------------------------------------------------------------------------------------------   
}
