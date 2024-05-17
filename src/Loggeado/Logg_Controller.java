/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loggeado;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

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
    
   
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Loggeado/Exportar/Vista_Exportar.fxml"));
            Node ventana = loader.load(); // Obtener el nodo raíz del archivo FXML

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

}
