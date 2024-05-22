/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loggeado;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.User;

/*
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
*/

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jsoler
 */
public class Logg_Controller implements Initializable {
    
    // Array de labels
    private Label labelSeleccionado = null;

    @FXML
    private Label boton_mi_perfil;
    @FXML
    private Label boton_gastos;
    @FXML
    private Label boton_analiticas;
    @FXML
    private Label boton_exportar;
    @FXML
    private Label boton_configuracion;
    @FXML
    private Pane panel_principal;
    @FXML
    private Label boton_cerrar_sesion;
    @FXML
    private ImageView imageUSer;
    @FXML
    private Label nameUser;
    
   
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        
    }   
    
    private void populateUserDetails(User user) {
      
            nameUser.setText(user.getNickName());
            imageUSer.setImage(user.getImage());      
    
    }

    @FXML
    private void mi_perfil_desenfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        boton_mi_perfil.getStyleClass().remove("label_enfocado_azul");
        boton_mi_perfil.getStyleClass().add("label_desenfocado_azul");
    }

    @FXML
    private void mi_perfil_enfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        boton_mi_perfil.getStyleClass().remove("label_desenfocado_azul");
        boton_mi_perfil.getStyleClass().add("label_enfocado_azul");
    }

    @FXML
    private void mi_perfil_click(MouseEvent event) {
        //Quitamos el seleccionado del labelSeleccionado
        if(labelSeleccionado != null){
        labelSeleccionado.getStyleClass().remove("label_seleccionado_azul");
        labelSeleccionado.getStyleClass().add("label_desenfocado_azul");
        }
        
        //Quitamos el estilo enfocado y metemos el click
        boton_mi_perfil.getStyleClass().remove("label_enfocado_azul");
        boton_mi_perfil.getStyleClass().add("label_seleccionado_azul");
        
        //Seleccionamos el labelactual como el seleccionado
        labelSeleccionado = boton_mi_perfil;
        
        //CAMBIO DE ESCENA 
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Loggeado/Perfil/Vista_Mi_Perfil.fxml"));
            Node ventana = loader.load(); // Obtener el nodo raíz del archivo FXML
            
            //Verificar si el nodo raiz es de tipo Region
            if (ventana instanceof Region){
                Region region = (Region) ventana;
                
                //Vincular tamaño nueva ventana con el tamaño panel principal
                region.prefWidthProperty().bind(panel_principal.widthProperty());
                region.prefHeightProperty().bind(panel_principal.heightProperty());
            }
            // Agregar la ventana al Pane
            if (panel_principal.getChildren() != null){
                //Borrar la ventana anterior
                panel_principal.getChildren().clear();
            }
            panel_principal.getChildren().add(ventana);

            // Ahora puedes acceder a los métodos y variables públicas del controlador de la ventana incrustada
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void gastos_desenfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        boton_gastos.getStyleClass().remove("label_enfocado_azul");
        boton_gastos.getStyleClass().add("label_desenfocado_azul");
    }

    @FXML
    private void gastos_enfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        boton_gastos.getStyleClass().remove("label_desenfocado_azul");
        boton_gastos.getStyleClass().add("label_enfocado_azul");
    }

    @FXML
    private void gastos_click(MouseEvent event) {
        
        //Quitamos el seleccionado del labelSeleccionado
        if(labelSeleccionado != null){
        labelSeleccionado.getStyleClass().remove("label_seleccionado_azul");
        labelSeleccionado.getStyleClass().add("label_desenfocado_azul");
        }
        
        //Quitamos el estilo enfocado y metemos el click
        boton_gastos.getStyleClass().remove("label_enfocado_azul");
        boton_gastos.getStyleClass().add("label_seleccionado_azul");
        
        //Seleccionamos el labelactual como el seleccionado
        labelSeleccionado = boton_gastos;
        
                //CAMBIO DE ESCENA 
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Loggeado/Gastos/Vista_Gastos.fxml"));
            Node ventana = loader.load(); // Obtener el nodo raíz del archivo FXML
            
            //Verificar si el nodo raiz es de tipo Region
            if (ventana instanceof Region){
                Region region = (Region) ventana;
                
                //Vincular tamaño nueva ventana con el tamaño panel principal
                region.prefWidthProperty().bind(panel_principal.widthProperty());
                region.prefHeightProperty().bind(panel_principal.heightProperty());
            }
            // Agregar la ventana al Pane
            if (panel_principal.getChildren() != null){
                //Borrar la ventana anterior
                panel_principal.getChildren().clear();
            }
            panel_principal.getChildren().add(ventana);

            // Ahora puedes acceder a los métodos y variables públicas del controlador de la ventana incrustada
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void analiticas_desenfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        boton_analiticas.getStyleClass().remove("label_enfocado_azul");
        boton_analiticas.getStyleClass().add("label_desenfocado_azul");
    }

    @FXML
    private void analiticas_enfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        boton_analiticas.getStyleClass().remove("label_desenfocado_azul");
        boton_analiticas.getStyleClass().add("label_enfocado_azul");
    }

    @FXML
    private void analiticas_click(MouseEvent event) {
        
        //Quitamos el seleccionado del labelSeleccionado
        if(labelSeleccionado != null){
        labelSeleccionado.getStyleClass().remove("label_seleccionado_azul");
        labelSeleccionado.getStyleClass().add("label_desenfocado_azul");
        }
        
        //Quitamos el estilo enfocado y metemos el click
        boton_analiticas.getStyleClass().remove("label_enfocado_azul");
        boton_analiticas.getStyleClass().add("label_seleccionado_azul");
        
        //Seleccionamos el labelactual como el seleccionado
        labelSeleccionado = boton_analiticas;
        
        //CAMBIO DE ESCENA 
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Loggeado/Analiticas/Vista_Analiticas.fxml"));
            Node ventana = loader.load(); // Obtener el nodo raíz del archivo FXML

            //Verificar si el nodo raiz es de tipo Region
            if (ventana instanceof Region){
                Region region = (Region) ventana;
                
                //Vincular tamaño nueva ventana con el tamaño panel principal
                region.prefWidthProperty().bind(panel_principal.widthProperty());
                region.prefHeightProperty().bind(panel_principal.heightProperty());
            }
            
            // Agregar la ventana al Pane
            if (panel_principal.getChildren() != null){
                //Borrar la ventana anterior
                panel_principal.getChildren().clear();
            }
            panel_principal.getChildren().add(ventana);

            // Ahora puedes acceder a los métodos y variables públicas del controlador de la ventana incrustada
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exportar_desenfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        boton_exportar.getStyleClass().remove("label_enfocado_azul");
        boton_exportar.getStyleClass().add("label_desenfocado_azul");
    }

    @FXML
    private void exportar_enfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        boton_exportar.getStyleClass().remove("label_desenfocado_azul");
        boton_exportar.getStyleClass().add("label_enfocado_azul");
    }

    @FXML
    private void exportar_click(MouseEvent event) {
        
        //Quitamos el seleccionado del labelSeleccionado
        if(labelSeleccionado != null){
        labelSeleccionado.getStyleClass().remove("label_seleccionado_azul");
        labelSeleccionado.getStyleClass().add("label_desenfocado_azul");
        }
        
        //Quitamos el estilo enfocado y metemos el click
        boton_exportar.getStyleClass().remove("label_enfocado_azul");
        boton_exportar.getStyleClass().add("label_seleccionado_azul");
        
        //Seleccionamos el labelactual como el seleccionado
        labelSeleccionado = boton_exportar;

        /*
        // Crear un FileChooser para que el usuario pueda seleccionar dónde guardar el PDF
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            try {
                // Crear el documento PDF
                PdfWriter writer = new PdfWriter(file);
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);

                // Crear una fuente
                PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
                PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

                // Título de la App y Resumen de Gastos
                String appName = "Nombre de la App";
                String year = "2024"; // Ejemplo
                String usuario = "Usuario Loggeado"; // Ejemplo
                double totalGasto = 1200.0; // Ejemplo

                Paragraph appTitle = new Paragraph(appName)
                        .setFont(bold)
                        .setFontSize(20)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginBottom(10);
                document.add(appTitle);

                Paragraph title = new Paragraph("Resumen de gastos " + year)
                        .setFont(bold)
                        .setFontSize(18)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginBottom(20);
                document.add(title);

                Paragraph summary = new Paragraph("El usuario " + usuario + " ha gastado $" + totalGasto + " en total")
                        .setFont(font)
                        .setFontSize(14)
                        .setTextAlignment(TextAlignment.LEFT)
                        .setMarginBottom(20);
                document.add(summary);

                // Tabla de Gasto Mensual
                float[] monthlyColumnWidths = {1, 2};
                Table monthlyTable = new Table(UnitValue.createPercentArray(monthlyColumnWidths));
                monthlyTable.setWidth(UnitValue.createPercentValue(100));

                // Encabezados de la tabla mensual
                monthlyTable.addHeaderCell(new Cell().add(new Paragraph("Mes").setFont(bold).setFontSize(12).setTextAlignment(TextAlignment.CENTER)));
                monthlyTable.addHeaderCell(new Cell().add(new Paragraph("Total Gastos").setFont(bold).setFontSize(12).setTextAlignment(TextAlignment.CENTER)));

                // Añadir filas con datos mensuales
                for (int i = 1; i <= 12; i++) {
                    monthlyTable.addCell(new Cell().add(new Paragraph(String.valueOf(i)).setFont(font).setFontSize(12).setTextAlignment(TextAlignment.CENTER)));
                    monthlyTable.addCell(new Cell().add(new Paragraph("$" + (i * 100)).setFont(font).setFontSize(12).setTextAlignment(TextAlignment.RIGHT)));
                }
                document.add(monthlyTable);

                // Variación de Gastos por Categoría
                Map<String, Double> categoriaGastos = new HashMap<>();
                categoriaGastos.put("Alimentación", 300.0);
                categoriaGastos.put("Transporte", 200.0);
                categoriaGastos.put("Entretenimiento", 400.0);
                categoriaGastos.put("Salud", 300.0);

                Paragraph categoryTitle = new Paragraph("Variación de gastos por categoría")
                        .setFont(bold)
                        .setFontSize(16)
                        .setTextAlignment(TextAlignment.LEFT)
                        .setMarginTop(20)
                        .setMarginBottom(10);
                document.add(categoryTitle);

                float[] categoryColumnWidths = {3, 2};
                Table categoryTable = new Table(UnitValue.createPercentArray(categoryColumnWidths));
                categoryTable.setWidth(UnitValue.createPercentValue(100));

                // Encabezados de la tabla de categorías
                categoryTable.addHeaderCell(new Cell().add(new Paragraph("Categoría").setFont(bold).setFontSize(12).setTextAlignment(TextAlignment.CENTER)));
                categoryTable.addHeaderCell(new Cell().add(new Paragraph("Total Gastos").setFont(bold).setFontSize(12).setTextAlignment(TextAlignment.CENTER)));

                // Añadir filas con datos de categorías
                for (Map.Entry<String, Double> entry : categoriaGastos.entrySet()) {
                    categoryTable.addCell(new Cell().add(new Paragraph(entry.getKey()).setFont(font).setFontSize(12).setTextAlignment(TextAlignment.LEFT)));
                    categoryTable.addCell(new Cell().add(new Paragraph("$" + entry.getValue()).setFont(font).setFontSize(12).setTextAlignment(TextAlignment.RIGHT)));
                }
                document.add(categoryTable);

                // Cerrar el documento
                document.close();

                System.out.println("PDF exportado con éxito a: " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al exportar el PDF.");
            }
        } */
    }

    @FXML
    private void configuracion_desenfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        boton_configuracion.getStyleClass().remove("label_enfocado_azul");
        boton_configuracion.getStyleClass().add("label_desenfocado_azul");
    }

    @FXML
    private void configuracion_enfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        boton_configuracion.getStyleClass().remove("label_desenfocado_azul");
        boton_configuracion.getStyleClass().add("label_enfocado_azul");
    }

    @FXML
    private void configuracion_click(MouseEvent event) {
        
        //Quitamos el seleccionado del labelSeleccionado
        if(labelSeleccionado != null){
        labelSeleccionado.getStyleClass().remove("label_seleccionado_azul");
        labelSeleccionado.getStyleClass().add("label_desenfocado_azul");
        }
        
        //Quitamos el estilo enfocado y metemos el click
        boton_configuracion.getStyleClass().remove("label_enfocado_azul");
        boton_configuracion.getStyleClass().add("label_seleccionado_azul");
        
        //Seleccionamos el labelactual como el seleccionado
        labelSeleccionado = boton_configuracion;

        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Loggeado/Configuracion/Vista_Configuracion.fxml"));
            Node ventana = loader.load(); // Obtener el nodo raíz del archivo FXML

            //Verificar si el nodo raiz es de tipo Region
            if (ventana instanceof Region){
                Region region = (Region) ventana;
                
                //Vincular tamaño nueva ventana con el tamaño panel principal
                region.prefWidthProperty().bind(panel_principal.widthProperty());
                region.prefHeightProperty().bind(panel_principal.heightProperty());
            }            
            
            // Agregar la ventana al Pane
            if (panel_principal.getChildren() != null){
                //Borrar la ventana anterior
                panel_principal.getChildren().clear();
            }
            panel_principal.getChildren().add(ventana);

            // Ahora puedes acceder a los métodos y variables públicas del controlador de la ventana incrustada
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cerrar_sesion_desenfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        boton_cerrar_sesion.getStyleClass().remove("label_enfocado_azul");
        boton_cerrar_sesion.getStyleClass().add("label_desenfocado_azul");
    }

    @FXML
    private void cerrar_sesion_enfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        boton_cerrar_sesion.getStyleClass().remove("label_desenfocado_azul");
        boton_cerrar_sesion.getStyleClass().add("label_enfocado_azul");
        
    }

    @FXML
    private void cerrar_sesion_click(MouseEvent event) throws IOException {
        
        //Quitamos el seleccionado del labelSeleccionado
        if(labelSeleccionado != null){
        labelSeleccionado.getStyleClass().remove("label_seleccionado_azul");
        labelSeleccionado.getStyleClass().add("label_desenfocado_azul");
        }
        
        //Quitamos el estilo enfocado y metemos el click
        boton_cerrar_sesion.getStyleClass().remove("label_enfocado_azul");
        boton_cerrar_sesion.getStyleClass().add("label_seleccionado_azul");
        
        //Seleccionamos el labelactual como el seleccionado
        labelSeleccionado = boton_cerrar_sesion;
        
        // Cargar el FXML de la ventana emergente
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Pagina_principal_Inicio/Vista_Pag_Principal_No_Logg.fxml"));
                Parent root = loader.load();

                // Crear una nueva escena y un nuevo escenario para la ventana emergente
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);

                // Obtenemos la ventana como objeto para aplicarle opciones
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                //cierro pestaña de inicio
                primaryStage.close();

                stage.setMaximized(true);
                stage.centerOnScreen();

                // Mostrar la ventana emergente
                stage.show();
    }

}
