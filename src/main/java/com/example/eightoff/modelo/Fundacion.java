package com.example.eightoff.modelo;

/**
 * Representa un montículo de un solo palo.
 * Se construye desde el As hasta el Rey.
 * Al inicio puede recibir cualquier As.
 */
public class Fundacion{

    private ListaSimple<CartaInglesa> cartas;
    private Palo palo; //se define cuando llega el primer As

    public Fundacion(){
        this.cartas=new ListaSimple<>();
        this.palo=null;
    }

    //Intenta agregar una carta
    public boolean agregarCarta(CartaInglesa carta){
        //Si está vacía, solo acepta As
        if(cartas.isEmpty()){
            if(carta.getValor()==14){
                cartas.insertaFin(carta);
                this.palo=carta.getPalo(); //se fija el palo
                return true;
            }
            return false;
        }

        //Ya tiene cartas, debe ser del mismo palo y el siguiente valor
        if(carta.getPalo()!=palo) return false;

        CartaInglesa ultima=cartas.getLast();

        //Después del As (14) viene el 2
        if(ultima.getValor()==14 && carta.getValor()==2){
            cartas.insertaFin(carta);
            return true;
        }

        //Caso normal
        if(ultima.getValor()+1==carta.getValor()){
            cartas.insertaFin(carta);
            return true;
        }

        return false;
    }

    public CartaInglesa removerUltimaCarta(){
        if(cartas.isEmpty()) return null;
        CartaInglesa c=cartas.eliminaFin();
        if(cartas.isEmpty()){
            palo=null; //se libera el palo si queda vacía
        }
        return c;
    }

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