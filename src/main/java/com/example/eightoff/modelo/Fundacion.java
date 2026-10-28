package com.example.eightoff.modelo;

/**
 * Representa un montículo de un solo palo.
 * Se construye desde el As hasta el Rey.
 */
public class Fundacion{

    private ListaSimple<CartaInglesa> cartas;
    private Palo palo;

    public Fundacion(Palo palo){
        this.palo=palo;
        this.cartas=new ListaSimple<>();
    }

    //Intenta agregar una carta. Solo se agrega si es la siguiente del mismo palo
    public boolean agregarCarta(CartaInglesa carta){
        if(carta.getPalo()!=palo) return false;

        if(cartas.isEmpty()){
            if(carta.getValor()==14){ //As
                cartas.insertaFin(carta);
                return true;
            }
            return false;
        }

        CartaInglesa ultima=cartas.getLast();
        if(ultima.getValor()+1==carta.getValor()||(ultima.getValor()==14&&carta.getValor()==2)){
            //Ajuste por si el As se maneja como 14
            if(ultima.getValor()==14&&carta.getValor()==2){
                cartas.insertaFin(carta);
                return true;
            }
            if(ultima.getValor()+1==carta.getValor()){
                cartas.insertaFin(carta);
                return true;
            }
        }
        return false;
    }

    //Elimina y regresa la última carta
    public CartaInglesa removerUltimaCarta(){
        if(cartas.isEmpty()) return null;
        return cartas.eliminaFin();
    }

    //Regresa la última carta sin eliminarla
    public CartaInglesa getUltimaCarta(){
        if(cartas.isEmpty()) return null;
        return cartas.getLast();
    }

    public boolean estaVacia(){
        return cartas.isEmpty();
    }

    public Palo getPalo(){
        return palo;
    }
}