package com.example.eightoff.vista;

import com.example.eightoff.controlador.BotonPista;
import com.example.eightoff.controlador.BotonUndo;
import com.example.eightoff.controlador.ControladorJuego;
import com.example.eightoff.modelo.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Pantalla principal del juego.
 * Dibuja tableaus, reservas y fundaciones centradas.
 * Se comunica únicamente con el ControladorJuego.
 */
public class VistaJuego extends BorderPane{

    private HBox celdasLibres;
    private VBox fundaciones;
    private HBox tableaus;
    private ControladorJuego controlador;

    private String tipoSeleccionado=null;
    private int celdaSeleccionada=-1;
    private int cartaSeleccionada=-1;
    private Movimiento pistaActual=null;

    public VistaJuego(ControladorJuego controlador){
        this.controlador=controlador;
        celdasLibres=new HBox(10);
        fundaciones=new VBox(10);
        tableaus=new HBox(10);

        //Color de fondo
        this.setStyle("-fx-background-color:#0d3b2e;");

        BotonUndo btnDeshacer=new BotonUndo();
        btnDeshacer.setOnAction(e->{
            controlador.deshacerMovimiento();
            dibujarTablero();
        });

        BotonPista btnPista=new BotonPista();
        btnPista.setOnAction(e->{
            pistaActual=controlador.obtenerPista();
            dibujarTablero();
        });

        //Botón de nuevo juego
        Button btnNuevo=new Button("NUEVO");
        String estiloBase="-fx-font-size:16px;-fx-padding:10px 20px;-fx-border-color:#2e8b57;-fx-border-radius:5px;-fx-background-radius:5px;-fx-font-weight:bold;";
        btnNuevo.setStyle(estiloBase+"-fx-background-color:#98fb98;");
        btnNuevo.setOnMouseEntered(e->btnNuevo.setStyle(estiloBase+"-fx-background-color:#90ee90;"));
        btnNuevo.setOnMouseExited(e->btnNuevo.setStyle(estiloBase+"-fx-background-color:#98fb98;"));
        btnNuevo.setOnAction(e->reiniciarJuego());

        //Área superior centrada
        HBox areaSuperior=new HBox(30);
        areaSuperior.setAlignment(Pos.CENTER);
        areaSuperior.setPadding(new Insets(20));
        areaSuperior.getChildren().addAll(celdasLibres,btnPista,btnDeshacer,btnNuevo);

        //Contenedor central que junta fundaciones + tableaus
        HBox centro=new HBox(30);
        centro.setAlignment(Pos.CENTER);
        centro.setPadding(new Insets(10,20,20,20));
        centro.getChildren().addAll(fundaciones,tableaus);

        this.setTop(areaSuperior);
        this.setCenter(centro);

        dibujarTablero();
    }

    //Reinicia el juego creando un nuevo ControladorJuego
    private void reiniciarJuego(){
        this.controlador=new ControladorJuego();
        tipoSeleccionado=null;
        celdaSeleccionada=-1;
        cartaSeleccionada=-1;
        pistaActual=null;
        dibujarTablero();
    }

    public void dibujarTablero(){
        tableaus.getChildren().clear();

        for(int i=0;i<8;i++){
            VBox columna=new VBox();
            Tableau tableau=controlador.getTableros().obtenerPorPosicion(i);

            int cantidad=0;
            while(tableau.obtenerCartaEnPosicion(cantidad)!=null){
                cantidad++;
            }

            if(cantidad<=6){
                columna.setSpacing(-75);
            }else if(cantidad<=10){
                columna.setSpacing(-95);
            }else{
                columna.setSpacing(-110);
            }

            int pos=0;
            CartaInglesa carta=tableau.obtenerCartaEnPosicion(pos);
            final int indice=i;

            if(carta==null){
                Rectangle rect=crearEspacioVacio();
                rect.setOnMouseClicked(e->procesarMovimiento("TABLEAU",indice,0,rect));
                aplicarEstiloPista(rect,"TABLEAU",indice);
                columna.getChildren().add(rect);
            }else{
                while(carta!=null){
                    ImageView img=crearFotoCarta(carta);
                    final int indiceCarta=pos;
                    img.setOnMouseClicked(e->procesarMovimiento("TABLEAU",indice,indiceCarta,img));
                    if(tableau.obtenerCartaEnPosicion(pos+1)==null){
                        aplicarEstiloPista(img,"TABLEAU",indice);
                    }
                    columna.getChildren().add(img);
                    pos++;
                    carta=tableau.obtenerCartaEnPosicion(pos);
                }
            }
            tableaus.getChildren().add(columna);
        }

        //Fundaciones
        fundaciones.getChildren().clear();
        for(int i=0;i<4;i++){
            Fundacion f=controlador.getFundaciones().obtenerPorPosicion(i);
            CartaInglesa tope=f.getUltimaCarta();
            final int indice=i;

            if(tope!=null){
                ImageView img=crearFotoCarta(tope);
                img.setStyle("-fx-effect:dropshadow(three-pass-box,rgba(0,0,0,0.6),8,0,0,0);");
                img.setOnMouseClicked(e->procesarMovimiento("FUNDACION",indice,0,img));
                aplicarEstiloPista(img,"FUNDACION",indice);
                fundaciones.getChildren().add(img);
            }else{
                Rectangle rect=crearEspacioVacio();
                rect.setOnMouseClicked(e->procesarMovimiento("FUNDACION",indice,0,rect));
                aplicarEstiloPista(rect,"FUNDACION",indice);
                fundaciones.getChildren().add(rect);
            }
        }

        //Reservas
        celdasLibres.getChildren().clear();
        for(int i=0;i<8;i++){
            Reserva r=controlador.getReservas().obtenerPorPosicion(i);
            CartaInglesa carta=r.getCarta();
            final int indice=i;

            if(carta!=null){
                ImageView img=crearFotoCarta(carta);
                img.setStyle("-fx-effect:dropshadow(three-pass-box,rgba(0,0,0,0.4),5,0,0,0);");
                img.setOnMouseClicked(e->procesarMovimiento("LIBRE",indice,0,img));
                aplicarEstiloPista(img,"LIBRE",indice);
                celdasLibres.getChildren().add(img);
            }else{
                Rectangle rect=crearEspacioVacio();
                rect.setOnMouseClicked(e->procesarMovimiento("LIBRE",indice,0,rect));
                aplicarEstiloPista(rect,"LIBRE",indice);
                celdasLibres.getChildren().add(rect);
            }
        }
    }

    private ImageView crearFotoCarta(CartaInglesa carta){
        CartaVisual cv=new CartaVisual(carta);
        Image img=new Image(getClass().getResourceAsStream(cv.getRuta()));
        ImageView vista=new ImageView(img);
        vista.setFitWidth(100);
        vista.setPreserveRatio(true);
        return vista;
    }

    private Rectangle crearEspacioVacio(){
        Rectangle r=new Rectangle(100,125);
        r.setFill(Color.rgb(255,255,255,0.25));
        r.setArcWidth(10);
        r.setArcHeight(10);
        return r;
    }

    private void procesarMovimiento(String tipo,int num,int posCarta,Node nodo){
        pistaActual=null;
        if(tipoSeleccionado==null){
            tipoSeleccionado=tipo;
            celdaSeleccionada=num;
            cartaSeleccionada=posCarta;
            //Selección naranja
            nodo.setStyle("-fx-effect:dropshadow(three-pass-box,#ff8c00,18,0.85,0,0);");
        }else{
            boolean movido=controlador.moverCarta(tipoSeleccionado,celdaSeleccionada,cartaSeleccionada,tipo,num);
            tipoSeleccionado=null;
            celdaSeleccionada=-1;
            dibujarTablero();

            if(movido){
                verificarEstadoJuego();
            }
        }
    }

    private void aplicarEstiloPista(Node nodo,String tipo,int indice){
        if(pistaActual!=null){
            if(pistaActual.getTipoOrigen().equalsIgnoreCase(tipo)&&pistaActual.getIdOrigen()==indice){
                //Origen de la pista (rosa)
                nodo.setStyle("-fx-effect:dropshadow(three-pass-box,#ff69b4,22,0.9,0,0);");
            }else if(pistaActual.getTipoDestino().equalsIgnoreCase(tipo)&&pistaActual.getIdDestino()==indice){
                //Destino de la pista (azul cielo)
                nodo.setStyle("-fx-effect:dropshadow(three-pass-box,#00bfff,22,0.9,0,0);");
            }
        }
    }

    //Verifica si el jugador ganó o si ya no hay movimientos
    private void verificarEstadoJuego(){
        if(controlador.estaGanado()){
            mostrarMensaje("¡Ganaste!","Felicidades, completaste el Eight Off.");
            return;
        }

        if(controlador.obtenerPista()==null){
            mostrarMensaje("Sin movimientos","Ya no hay más jugadas posibles.\nEl juego terminó.");
        }
    }

    //Muestra una ventana de mensaje
    private void mostrarMensaje(String titulo,String mensaje){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}