package com.example.eightoff;

import com.example.eightoff.controlador.ControladorJuego;
import com.example.eightoff.vista.VistaJuego;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Punto de entrada de la aplicación.
 * Crea el controlador y la vista, y muestra la ventana del juego.
 */
public class Main extends Application{

    @Override
    public void start(Stage stage){
        ControladorJuego controlador=new ControladorJuego();
        VistaJuego vista=new VistaJuego(controlador);
        Scene scene=new Scene(vista,1366,768);
        stage.setTitle("Eight Off");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}