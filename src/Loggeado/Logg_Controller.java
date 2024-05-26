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

import com.itextpdf.html2pdf.HtmlConverter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import model.Acount;
import model.AcountDAOException;
import model.Charge;

/**
 *
 * @author jsoler
 */
public class Logg_Controller implements Initializable {
    
    // Array de labels
    private Label labelSeleccionado = null;
    
    private User user;
    private Acount acc;

    @FXML
    private Label boton_mi_perfil;
    @FXML
    private Label boton_gastos;
    @FXML
    private Label boton_analiticas;
    @FXML
    private Label boton_exportar;
    private Label boton_configuracion;
    @FXML
    private Pane panel_principal;
    @FXML
    private Label boton_cerrar_sesion;
    private ImageView imageUSer;
    private Label nameUser;
    
   
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        
    }   
    //----------------------------------------------------------------------------------------------------------

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
//----------------------------------------------------------------------------------------------------------

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
//----------------------------------------------------------------------------------------------------------

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
//----------------------------------------------------------------------------------------------------------
    @FXML
    private void gastos_click(MouseEvent event) throws AcountDAOException {
        
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
//----------------------------------------------------------------------------------------------------------

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
//----------------------------------------------------------------------------------------------------------

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
//----------------------------------------------------------------------------------------------------------

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
//----------------------------------------------------------------------------------------------------------

    @FXML
    private void exportar_click(MouseEvent event) throws IOException {
        
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

        try {
                acc = Acount.getInstance();
                this.user = acc.getLoggedUser();
                String fechaHoy = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
                String absPathOfImage = getClass().getResource("../Iconos_estilo/Union_Letras_logo.png").toExternalForm();
                System.out.println(absPathOfImage);
                String html = "<!DOCTYPE html><html lang='en'><head><meta charset='utf-8' /><meta name='viewport' content='width=device-width, initial-scale=1' /><title>ROCKETEER</title><link rel='icon' href='./images/favicon.png' type='image/x-icon' /><style>body{font-family:'Helvetica Neue','Helvetica',Helvetica,Arial,sans-serif;text-align:center;color:#000;background-color:#fff;}body h1,body h2,body h3{font-weight:300;margin-bottom:0;padding-bottom:0;}body h1{color:#4a90e2;}body h2{color:#3379ca;}body h3{margin-top:10px;margin-bottom:20px;font-style:italic;}body a{color:#06f;}.header-title{font-size:50px;font-weight:bold;color:#095bb8;margin:20px 0;}.invoice-box{max-width:800px;margin:auto;padding:30px;border:1px solid #1336a7;box-shadow:0 0 10px rgba(0,0,0,.15);font-size:16px;line-height:24px;color:#000;background-color:#aed5fa;}.invoice-box table{width:100%;line-height:inherit;text-align:left;border-collapse:collapse;}.invoice-box table td{padding:5px;vertical-align:top;}.invoice-box table tr td:nth-child(3),.a{text-align:right;}.invoice-box table tr.top table td{padding-bottom:20px;}.invoice-box table tr.top table td.title{font-size:45px;line-height:45px;color:#000;}.invoice-box table tr.information table td{padding-bottom:40px;}.invoice-box table tr.heading td{background:hsl(0,0%,99%);border-bottom:1px solid #006deb;font-weight:bold;color:#006deb;}.invoice-box table tr.details td{padding-bottom:20px;}.invoice-box table tr.item td{border-bottom:1px solid #eee;}.invoice-box table tr.item.last td{border-bottom:none;}.invoice-box table tr.total td:nth-child(2){border-top:2px solid #eee;font-weight:bold;color:#000;}}@media only screen and (max-width:600px){.invoice-box table tr.top table td{width:100%;display:block;text-align:center;}.invoice-box table tr.information table td{width:100%;display:block;text-align:center;}}</style></head><body><div class='header-title'>ROCKETEER</div><div class='invoice-box'><table><tr class='top'><td colspan='3'><table><tr><td class='title'><img style='height:100px;' src='" + absPathOfImage + "' alt='Company logo' style='width:100%;max-width:300px' /></td><td><h2>👽 EXPENSE SUMMARY 👽</h2></td></tr></table></td></tr><tr class='information'><td colspan='3'><table><tr><td>Name 📕 : "+this.user.getName()+"<br />Surname 📗 : "+this.user.getSurname()+"<br /></td><td>Email 📙 : "+this.user.getEmail()+"<br />Today's Date 📘 : "+fechaHoy+"<br /></td></tr></table></td></tr><tr class='heading'><td>Expense 🚀</td><td>Cost 💵</td><td float='right'>Units ✏️</td></tr>";
                
                
                List<Charge> l = this.acc.getUserCharges();
                float i = 0;
                for (Charge c : l) {
                    i += c.getCost()*c.getUnits();
                    html += "<tr class='item'><td>" + c.getName() + "</td><td>" + c.getCost() + " €</td><td float='right'>"+c.getUnits()+"</td></tr>";
                }
                // Two decimal
                html += "<tr class='total'><td></td> <td></td><td style='text-align: end'><b>Total: "+ String.format("%.2f", i) + " €</b></td></tr></table></div></body></html>";
                exportHTMLToPDF(html);

            }
            catch (AcountDAOException ex) {
                ex.printStackTrace();
            }
    }
 
//-------------------------------------------------------------------------------
    public static void exportHTMLToPDF(String html) {
        if (html == null || html.isEmpty()) {
            System.out.println("No hay contenido que exportar a PDF.");
            return;
        }

        System.out.println("Eligiendo archivo...");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos PDF", "*.pdf"));
        Stage st2 = new Stage();
        File file = fileChooser.showSaveDialog(st2);

        if (file != null) {
            System.out.println("¡Archivo seleccionado!");
            try (FileOutputStream fos = new FileOutputStream(file)) {
                HtmlConverter.convertToPdf(html, fos);
                
                System.out.println("PDF creado con éxito.");
            } catch (FileNotFoundException e) {
                System.out.println("Error al crear el archivo PDF: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error al crear el archivo PDF: " + e.getMessage());
            }
        }
    }    
//----------------------------------------------------------------------------------------------------------

    private void configuracion_desenfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        boton_configuracion.getStyleClass().remove("label_enfocado_azul");
        boton_configuracion.getStyleClass().add("label_desenfocado_azul");
    }

    private void configuracion_enfoque(MouseEvent event) {
        
        //Modificamos el estilo del boton al entrar en el
        boton_configuracion.getStyleClass().remove("label_desenfocado_azul");
        boton_configuracion.getStyleClass().add("label_enfocado_azul");
    }
//----------------------------------------------------------------------------------------------------------

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
//----------------------------------------------------------------------------------------------------------

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
//----------------------------------------------------------------------------------------------------------

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
//----------------------------------------------------------------------------------------------------------
