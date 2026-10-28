package com.example.eightoff.controlador;

import com.example.eightoff.modelo.*;

/**
 * Orquesta la comunicación entre la vista y el modelo.
 * No contiene lógica del juego, solo llama a los métodos de JuegoEightOff.
 */
public class ControladorJuego{

    private JuegoEightOff juego;

    public ControladorJuego(){
        juego=new JuegoEightOff();
    }

    public boolean moverCarta(String tipoOrigen,int numOrigen,int posCarta,String tipoDestino,int numDestino){
        return juego.moverCarta(tipoOrigen,numOrigen,posCarta,tipoDestino,numDestino);
    }

    public void deshacerMovimiento(){
        juego.deshacerMovimiento();
    }

    public Movimiento obtenerPista(){
        return juego.obtenerPista();
    }

    public boolean estaGanado(){
        return juego.estaGanado();
    }

    public ListaSimple<Tableau> getTableros(){
        return juego.getTableros();
    }

    public ListaSimple<Reserva> getReservas(){
        return juego.getReservas();
    }

    public ListaSimple<Fundacion> getFundaciones(){
        return juego.getFundaciones();
    }
}