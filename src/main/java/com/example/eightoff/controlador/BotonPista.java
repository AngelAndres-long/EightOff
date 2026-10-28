package com.example.eightoff.controlador;

import javafx.scene.Cursor;
import javafx.scene.control.Button;

/**
 * Botón personalizado para solicitar una pista.
 */
public class BotonPista extends Button{

    public BotonPista(){
        super("PISTA");
        String estiloBase="-fx-font-size:16px;-fx-padding:10px 20px;-fx-border-color:#d4af37;-fx-border-radius:5px;-fx-background-radius:5px;-fx-font-weight:bold;";
        this.setStyle(estiloBase+"-fx-background-color:#fffacd;");
        this.setCursor(Cursor.HAND);
        this.setOnMouseEntered(e->this.setStyle(estiloBase+"-fx-background-color:#eee8aa;"));
        this.setOnMouseExited(e->this.setStyle(estiloBase+"-fx-background-color:#fffacd;"));
    }
}