package com.example.eightoff.modelo;

/**
 * Guarda la información de un movimiento realizado.
 * Se usa para poder deshacer jugadas.
 */
public class Movimiento{

    private String tipoOrigen;
    private int idOrigen;
    private String tipoDestino;
    private int idDestino;
    private int cantidadCartas;

    public Movimiento(String tipoOrigen,int idOrigen,String tipoDestino,int idDestino,int cantidadCartas){
        this.tipoOrigen=tipoOrigen;
        this.idOrigen=idOrigen;
        this.tipoDestino=tipoDestino;
        this.idDestino=idDestino;
        this.cantidadCartas=cantidadCartas;
    }

    public String getTipoOrigen(){
        return tipoOrigen;
    }

    public int getIdOrigen(){
        return idOrigen;
    }

    public String getTipoDestino(){
        return tipoDestino;
    }

    public int getIdDestino(){
        return idDestino;
    }

    public int getCantidadCartas(){
        return cantidadCartas;
    }
}