package com.example.eightoff.modelo;

/**
 * Representa una celda libre (free cell).
 * Solo puede guardar una carta a la vez.
 */
public class Reserva{

    private CartaInglesa carta;

    public Reserva(){
        carta=null;
    }

    public boolean estaVacia(){
        return carta==null;
    }

    public void setCarta(CartaInglesa carta){
        this.carta=carta;
    }

    public CartaInglesa getCarta(){
        return carta;
    }

    //Quita y regresa la carta
    public CartaInglesa removerCarta(){
        CartaInglesa temp=carta;
        carta=null;
        return temp;
    }
}