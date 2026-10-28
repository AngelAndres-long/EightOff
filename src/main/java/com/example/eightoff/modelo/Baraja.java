package com.example.eightoff.modelo;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Representa la baraja completa de 52 cartas.
 * Usa una lista doble circular y se encarga de crear, mezclar y repartir.
 */
public class Baraja{

    private ListaDobleCircular<CartaInglesa> cartas;

    public Baraja(){
        cartas=new ListaDobleCircular<>();
        llenar();
        mezclar();
    }

    //Crea las 52 cartas (valores del 2 al 14)
    private void llenar(){
        for(int i=2;i<=14;i++){
            for(Palo palo:Palo.values()){
                cartas.insertEnd(new CartaInglesa(i,palo));
            }
        }
    }

    //Mezcla las cartas de forma aleatoria
    private void mezclar(){
        if(cartas.size()<=1) return;
        ArrayList<CartaInglesa> aux=new ArrayList<>();
        while(cartas.size()>0){
            aux.add(cartas.removeLast());
        }
        Collections.shuffle(aux);
        for(CartaInglesa c:aux){
            cartas.insertEnd(c);
        }
    }

    //Saca y regresa una carta de la baraja
    public CartaInglesa obtenerUnaCarta(){
        if(cartas.size()>0){
            return cartas.remove(0);
        }
        return null;
    }

    public int size(){
        return cartas.size();
    }
}