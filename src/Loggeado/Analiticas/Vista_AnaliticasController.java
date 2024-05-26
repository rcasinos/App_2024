/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Loggeado.Analiticas;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import model.Category;
import model.Charge;
import model.User;
import model.Acount;
import model.AcountDAOException;
import model.AcountDAO;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import model.Charge;
import model.Category;
/**
 * FXML Controller class
 *
 * @author Roberto Casinos
 */
public class Vista_AnaliticasController implements Initializable {

    @FXML
    private PieChart idPieChart;
    
    private List<Charge> charges = new ArrayList<>();
    @FXML
    private ChoiceBox<String> Choice_filtrado_general;
    @FXML
    private ChoiceBox<String> choice_month_1;
    @FXML
    private ChoiceBox<String> choice_year_1;
    @FXML
    private ChoiceBox<String> choice_month_2;
    @FXML
    private ChoiceBox<String> choice_year_2;
    @FXML
    private Button Filter_button;
    @FXML
    private StackPane stackpane;
    @FXML
    private BarChart<String, Number> bar_chart_mes;
    @FXML
    private BarChart<String, Number> bar_chart_ano;
    @FXML
    private PieChart pie_compara_meses;
    @FXML
    private Label mes_1_label;
    @FXML
    private Label mes_2_label;
    @FXML
    private Label label_categorias;
    @FXML
    private Label label_ano;
    @FXML
    private Label label_mes;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Ponemos a invisible todas las opciones
        bar_chart_ano.setVisible(false);
        bar_chart_ano.setVisible(false);
        idPieChart.setVisible(false);
        label_ano.setVisible(false);
        label_categorias.setVisible(false);
        label_mes.setVisible(false);          
        //----------------------------------------------------------------------------------------------
        // Initialize choice box
        ObservableList<String> options = FXCollections.observableArrayList("Anual", "Mensual", "Categorias");
        Choice_filtrado_general.setItems(options);
        //----------------------------------------------------------------------------------------------
        // Obtenemos la lista de cargos del usuario actual
        try {
            charges = Acount.getInstance().getUserCharges();
        } catch (AcountDAOException ex) {
            Logger.getLogger(Vista_AnaliticasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Vista_AnaliticasController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Creamos un mapa para almacenar los años y meses con gastos
        Map<Integer, List<Integer>> yearMonthMap = new HashMap<>();

        // Iteramos sobre los cargos y agregamos los años y meses a yearMonthMap
        for (Charge charge : charges) {
            int year = charge.getDate().getYear(); // Suponiendo que getDate() devuelve la fecha del cargo
            int month = charge.getDate().getMonthValue();
            if (!yearMonthMap.containsKey(year)) {
                yearMonthMap.put(year, new ArrayList<>());
            }
            if (!yearMonthMap.get(year).contains(month)) {
                yearMonthMap.get(year).add(month);
                System.out.println("Año: " + year + ", Mes: " + Month.of(month).toString());
            }
        }

        // Agregar opción predeterminada al inicio de la lista de años
        ObservableList<String> years = FXCollections.observableArrayList("Seleccione año");
        yearMonthMap.keySet().forEach(year -> years.add(Integer.toString(year)));
        choice_year_1.setItems(years);
        choice_year_2.setItems(years);

        // Escuchar cambios de selección en el choice box del año
        choice_year_1.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.equals("Seleccione año")) {
                int selectedYear = Integer.parseInt(newVal);
                ObservableList<String> months = FXCollections.observableArrayList();
                System.out.println(months);
                yearMonthMap.get(selectedYear).forEach(month -> months.add(String.valueOf(Month.of(month))));
                choice_month_1.setItems(months);
                System.out.println("Opciones de meses para el año " + selectedYear + ": " + months);
            }
        });  
        // Escuchar cambios de selección en el choice box del año
        choice_year_2.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.equals("Seleccione año")) {
                int selectedYear = Integer.parseInt(newVal);
                ObservableList<String> months = FXCollections.observableArrayList();
                System.out.println(months);
                yearMonthMap.get(selectedYear).forEach(month -> months.add(String.valueOf(Month.of(month))));
                choice_month_2.setItems(months);
                System.out.println("Opciones de meses para el año " + selectedYear + ": " + months);
            }
        });  
    //---------------------------------------------------------------------------------------------------------------------------    
        // Obtenemos la lista de cargos del usuario actual
        try {
            charges = Acount.getInstance().getUserCharges();
        } catch (AcountDAOException ex) {
            Logger.getLogger(Vista_AnaliticasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Vista_AnaliticasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    //---------------------------------------------------------------------------------------------------------------------------
        // Creamos un mapa para almacenar el conteo de gastos por categoría
        Map<Category, Integer> categoryCountMap = new HashMap<>();

        // Iteramos sobre los cargos y contamos las ocurrencias de cada categoría
        for (Charge charge : charges) {
            Category category = charge.getCategory();
            if (category != null) { // Aseguramos que la categoría no sea nula
                categoryCountMap.put(category, categoryCountMap.getOrDefault(category, 0) + 1);
            }
        }

        // Ordenamos el mapa por el valor del conteo en orden descendente
        List<Map.Entry<Category, Integer>> sortedEntries = new ArrayList<>(categoryCountMap.entrySet());
        sortedEntries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Creamos las secciones del pie chart para las tres categorías con más ocurrencias
        int otherCount = 0;
        for (int i = 0; i < sortedEntries.size(); i++) {
            Map.Entry<Category, Integer> entry = sortedEntries.get(i);
            if (i < 3) {
                PieChart.Data slice = new PieChart.Data(entry.getKey().getName(), entry.getValue());
                idPieChart.getData().add(slice);
            } else {
                otherCount += entry.getValue();
            }
        }

        // Añadimos una sección para "Otros" que incluye el recuento de las categorías restantes
        if (otherCount > 0) {
            PieChart.Data otherSlice = new PieChart.Data("Otros", otherCount);
            idPieChart.getData().add(otherSlice);
        }
    //---------------------------------------------------------------------------------------------------------------------------    
    Choice_filtrado_general.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                switch (newVal) {
                    case "Anual":
                        showBarChartAno();
                        break;
                    case "Mensual":
                        showBarChartMes();
                        break;
                    case "Categorias":
                        showPieChart();
                        break;
                }
            }
        });    
    //---------------------------------------------------------------------------------------------------------------------------        
    }
    @FXML
    private void visualize_opciones_1(MouseEvent event) {
    }
    //---------------------------------------------------------------------------------------------------------------------------
    @FXML
    private void compare_months(MouseEvent event) {
        // Obtener los valores seleccionados de los ChoiceBox
        String selectedMonth1 = choice_month_1.getValue();
        String selectedMonth2 = choice_month_2.getValue();
        String selectedYear1 = choice_year_1.getValue();
        String selectedYear2 = choice_year_2.getValue();

        // Verificar que se hayan seleccionado valores válidos
        if (selectedMonth1 != null && selectedMonth2 != null && selectedYear1 != null && selectedYear2 != null &&
            !selectedYear1.equals("Seleccione año") && !selectedYear2.equals("Seleccione año")) {
            
            int year1 = Integer.parseInt(selectedYear1);
            int year2 = Integer.parseInt(selectedYear2);
            int month1 = Month.valueOf(selectedMonth1.toUpperCase()).getValue();
            int month2 = Month.valueOf(selectedMonth2.toUpperCase()).getValue();

            // Calcular los gastos de cada mes/año
            double moneyMonth1 = calculateMonthlyExpenses(year1, month1);
            double moneyMonth2 = calculateMonthlyExpenses(year2, month2);
            
            // Convertir los valores a string y establecerlos en los labels
            mes_1_label.setText(String.format("%.2f", moneyMonth1));
            mes_2_label.setText(String.format("%.2f", moneyMonth2));

            // Actualizar el PieChart con los nuevos valores
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            pieChartData.add(new PieChart.Data("Mes 1 (" + selectedMonth1 + " " + selectedYear1 + ")", moneyMonth1));
            pieChartData.add(new PieChart.Data("Mes 2 (" + selectedMonth2 + " " + selectedYear2 + ")", moneyMonth2));

            pie_compara_meses.setData(pieChartData);
        }
    } 
    //---------------------------------------------------------------------------------------------------------------------------     
    private double calculateMonthlyExpenses(int year, int month) {
        return charges.stream()
                .filter(charge -> charge.getDate().getYear() == year && charge.getDate().getMonthValue() == month)
                .mapToDouble(charge -> charge.getCost() * charge.getUnits())
                .sum();
    }

    //---------------------------------------------------------------------------------------------------------------------------
    private void showBarChartAno() {
        bar_chart_ano.setVisible(true);
        bar_chart_mes.setVisible(false);
        idPieChart.setVisible(false);
        updateBarChartAno();
        label_ano.setVisible(true);
        label_categorias.setVisible(false);
        label_mes.setVisible(false);
    }
    //---------------------------------------------------------------------------------------------------------------------------
    private void showBarChartMes() {
        bar_chart_ano.setVisible(false);
        bar_chart_mes.setVisible(true);
        idPieChart.setVisible(false);
        updateBarChartMes();
        label_ano.setVisible(false);
        label_categorias.setVisible(false);
        label_mes.setVisible(true);
    }
    //---------------------------------------------------------------------------------------------------------------------------
    private void showPieChart() {
        bar_chart_ano.setVisible(false);
        bar_chart_mes.setVisible(false);
        idPieChart.setVisible(true);
        label_ano.setVisible(false);
        label_categorias.setVisible(true);
        label_mes.setVisible(false);        
    }
    //---------------------------------------------------------------------------------------------------------------------------
    private void updateBarChartAno() {

        bar_chart_ano.getData().clear();

        CategoryAxis xAxis = (CategoryAxis) bar_chart_ano.getXAxis();
        xAxis.getCategories().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        Map<Integer, Double> annualExpenses = new HashMap<>();

        for (Charge charge : charges) {
            int year = charge.getDate().getYear();
            double cost = charge.getCost() * charge.getUnits();
            annualExpenses.put(year, annualExpenses.getOrDefault(year, 0.0) + cost);
        }

        for (Map.Entry<Integer, Double> entry : annualExpenses.entrySet()) {
            String yearStr = String.valueOf(entry.getKey());
            series.getData().add(new XYChart.Data<>(yearStr, entry.getValue()));
            xAxis.getCategories().add(yearStr);
        }

        bar_chart_ano.getData().add(series);

        // Añadir leyenda personalizada
        bar_chart_ano.setLegendVisible(true);
        bar_chart_ano.setLegendSide(Side.BOTTOM);


    }
    //---------------------------------------------------------------------------------------------------------------------------
    private void updateBarChartMes() {
        
        bar_chart_mes.getData().clear();

        CategoryAxis xAxis = (CategoryAxis) bar_chart_mes.getXAxis();
        xAxis.getCategories().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        Map<Integer, Double> dailyExpenses = new HashMap<>();
        LocalDate now = LocalDate.now();
        YearMonth currentMonth = YearMonth.of(now.getYear(), now.getMonth());

        for (Charge charge : charges) {
            LocalDate date = charge.getDate();
            if (YearMonth.from(date).equals(currentMonth)) {
                int day = date.getDayOfMonth();
                double cost = charge.getCost() * charge.getUnits();
                dailyExpenses.put(day, dailyExpenses.getOrDefault(day, 0.0) + cost);
            }
        }

        for (Map.Entry<Integer, Double> entry : dailyExpenses.entrySet()) {
            String dayStr = String.valueOf(entry.getKey());
            series.getData().add(new XYChart.Data<>(dayStr, entry.getValue()));
            xAxis.getCategories().add(dayStr);
        }

        bar_chart_mes.getData().add(series);

        // Añadir leyenda personalizada
        bar_chart_mes.setLegendVisible(true);
        bar_chart_mes.setLegendSide(Side.BOTTOM);
    }
    //---------------------------------------------------------------------------------------------------------------------------    
}
