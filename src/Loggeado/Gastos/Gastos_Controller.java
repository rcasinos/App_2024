package Loggeado.Gastos;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import model.Acount;
import model.AcountDAO;
import model.AcountDAOException;
import model.Charge;
import model.User;
import modeling.MyChargeModel;

public class Gastos_Controller implements Initializable {

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
    
    @FXML
    private HBox hbox_titulo;
    @FXML
    private Button view_all_expenses;
    @FXML
    private Button view_all_categories_button;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
        category_column.setCellValueFactory(new PropertyValueFactory<>("category"));
        color_column.setCellValueFactory(new PropertyValueFactory<>("description")); // Assuming color is in description
        cost_column.setCellValueFactory(new PropertyValueFactory<>("cost"));

        // Load data into table view
        expenses_tableview.setItems(chargeList);
        
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
        
        // Get user info
            Acount acc = null;
            AcountDAO accD = null;

            try {
                acc = Acount.getInstance();
                this.loggedUser = acc.getLoggedUser();
            }
            catch (IOException ex) {
            }
            catch (AcountDAOException ex) {
            }

            // Get all charges
            List<Charge> charges = null;
            try {
                System.out.println(acc.getLoggedUser().getNickName());
                charges = acc.getUserCharges();
            }
            catch (AcountDAOException ex) {
            }
            catch (NullPointerException d) {
                System.out.println(d);
                charges = null;
            }

            /*
            // Add example test
            List<Category> categories = null;
            try {
                categories = acc.getUserCategories();
                acc.registerCharge("Comida", "Pizza", 12.3, 1, this.user.getImage(), LocalDate.now(), categories.get(0));
            }
            catch (AcountDAOException ex) {
                Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
            }
            */

            if (charges != null || !charges.isEmpty()) {
                // Remove all first
                chargeList.removeAll(chargeList);

                System.out.println("Tamaño: " + charges.size());
                int t_size = charges.size();
                // Set columns
                name_column.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
                color_column.setCellValueFactory(new PropertyValueFactory<>("chargeName"));
                category_column.setCellValueFactory(new PropertyValueFactory<>("chargePrice"));
                cost_column.setCellValueFactory(new PropertyValueFactory<>("chargeUds"));

                try {
                    // Insert to model MyChargeModel

                    // Not showing data to table
                    int i = 0;
                    for (Charge charge : charges) {
                        System.out.println(i+"/"+t_size);
                        MyChargeModel myCharge = new MyChargeModel(charge.getCategory().getName(), charge.getName(), Double.toString(charge.getCost()), Integer.toString(charge.getUnits()), charge.getDate().toString(), charge);
                        System.out.println(myCharge.getCategoryName() + " " + myCharge.getChargeName() + " " + myCharge.getChargePrice() + " " + myCharge.getChargeUds() + " " + myCharge.getChargeDate());
                        //chargeList.add(myCharge);
                        i++;
                    }

                    // Set data
                    expenses_tableview.setItems(chargeList);
                }
                catch (NullPointerException ex) {
                    System.out.println("Vacio");
                }
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
}
