package com.example.eightoff.modelo;

/**
 * Representa los cuatro palos de la baraja inglesa.
 * Cada palo tiene un peso, un nombre para las imágenes y un color.
 */
public enum Palo{
    TREBOL(1,"trebol","negro"),
    DIAMANTE(2,"diamante","rojo"),
    CORAZON(3,"corazon","rojo"),
    PICAS(4,"picas","negro");

    private final int peso;
    private final String nombre;
    private final String color;

    Palo(int peso,String nombre,String color){
        this.peso=peso;
        this.nombre=nombre;
        this.color=color;
    }

    public int getPeso(){
        return peso;
    }

    public String getNombre(){
        return nombre;
    }

    public String getColor(){
        return color;
    }
}