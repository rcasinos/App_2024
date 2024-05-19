package pruebas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.scene.Scene;

public class GastosController implements Initializable {

    @FXML
    private TableView<Gasto> tablaGastos;
    @FXML
    private TableColumn<Gasto, String> columnaNombre;
    @FXML
    private TableColumn<Gasto, Double> columnaCantidad;
    @FXML
    private TableColumn<Gasto, LocalDate> columnaFecha;

    private final ObservableList<Gasto> listaGastos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configurar las columnas
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        // Agregar algunos datos de muestra
        listaGastos.add(new Gasto("Comida", 50.0, LocalDate.now().minusDays(2)));
        listaGastos.add(new Gasto("Transporte", 30.0, LocalDate.now().minusDays(10)));
        listaGastos.add(new Gasto("Entretenimiento", 20.0, LocalDate.now().minusDays(20)));

        // Agregar datos a la tabla
        tablaGastos.setItems(listaGastos);


    }

    public static class Gasto {
        private final String nombre;
        private final double cantidad;
        private final LocalDate fecha;

        public Gasto(String nombre, double cantidad, LocalDate fecha) {
            this.nombre = nombre;
            this.cantidad = cantidad;
            this.fecha = fecha;
        }

        public String getNombre() {
            return nombre;
        }

        public double getCantidad() {
            return cantidad;
        }

        public LocalDate getFecha() {
            return fecha;
        }
    }
}

