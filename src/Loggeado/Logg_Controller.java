/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loggeado;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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
    }

}
