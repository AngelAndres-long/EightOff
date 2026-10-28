package com.example.eightoff.modelo;

/**
 * Clase base de una carta.
 * Contiene valor, palo, color y si está boca arriba.
 * Implementa Comparable para poder ordenar las cartas.
 */
public abstract class Carta implements Comparable<Carta>{

    private int valor;
    protected Palo palo;
    private String color;
    private boolean bocaArriba;

    public Carta(int valor,Palo palo,String color){
        this.valor=valor;
        this.palo=palo;
        this.color=color;
        this.bocaArriba=false;
    }

    //Voltea la carta hacia arriba
    public void voltearArriba(){
        bocaArriba=true;
    }

    //Voltea la carta hacia abajo
    public void voltearAbajo(){
        bocaArriba=false;
    }

    public boolean estaBocaArriba(){
        return bocaArriba;
    }

    public int getValor(){
        return valor;
    }

    public Palo getPalo(){
        return palo;
    }

    public String getColor(){
        return color;
    }

    //Verifica si dos cartas son del mismo palo
    public boolean mismoPalo(Carta otra){
        return this.palo==otra.palo;
    }

    //Verifica si esta carta puede ir sobre otra en el tableau
    public boolean esSiguienteEnTableau(Carta otra){
        return mismoPalo(otra)&&this.valor==otra.valor+1;
    }

    //Verifica si esta carta puede ir sobre otra en la fundación
    public boolean esSiguienteEnFundacion(Carta otra){
        return mismoPalo(otra)&&this.valor==otra.valor-1;
    }

    @Override
    public String toString(){
        if(!bocaArriba){
            return "X";
        }
        String simbolo;
        switch(valor){
            case 14: simbolo="A"; break;
            case 11: simbolo="J"; break;
            case 12: simbolo="Q"; break;
            case 13: simbolo="K"; break;
            default: simbolo=String.valueOf(valor);
        }
        return simbolo+palo.getNombre().substring(0,1).toUpperCase();
    }
}