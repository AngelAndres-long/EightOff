package com.example.eightoff.controlador;

import javafx.scene.Cursor;
import javafx.scene.control.Button;

/**
 * Botón personalizado para deshacer el último movimiento.
 */
public class BotonUndo extends Button{

    public BotonUndo(){
        super("UNDO");
        String estiloBase="-fx-font-size:16px;-fx-padding:10px 20px;-fx-border-color:#333333;-fx-border-radius:5px;-fx-background-radius:5px;-fx-font-weight:bold;";
        this.setStyle(estiloBase+"-fx-background-color:#ffffff;");
        this.setCursor(Cursor.HAND);
        this.setOnMouseEntered(e->this.setStyle(estiloBase+"-fx-background-color:#dddddd;"));
        this.setOnMouseExited(e->this.setStyle(estiloBase+"-fx-background-color:#ffffff;"));
    }
}