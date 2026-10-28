package com.example.eightoff.modelo;

/**
 * Carta de la baraja inglesa.
 * Extiende Carta e implementa la comparación por valor y palo.
 */
public class CartaInglesa extends Carta{

    public CartaInglesa(int valor,Palo palo){
        super(valor,palo,palo.getColor());
    }

    @Override
    public int compareTo(Carta otra){
        if(this.getValor()!=otra.getValor()){
            return this.getValor()-otra.getValor();
        }
        return this.getPalo().getPeso()-otra.getPalo().getPeso();
    }
}