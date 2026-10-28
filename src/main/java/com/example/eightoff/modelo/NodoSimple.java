package com.example.eightoff.modelo;

/**
 * Nodo para la lista simple enlazada.
 * Guarda un dato y la referencia al siguiente nodo.
 */
public class NodoSimple<T>{

    private T dato;
    private NodoSimple<T> sig;

    public NodoSimple(T dato){
        this.dato=dato;
        this.sig=null;
    }

    public T getDato(){
        return dato;
    }

    public void setDato(T dato){
        this.dato=dato;
    }

    public NodoSimple<T> getSig(){
        return sig;
    }

    public void setSig(NodoSimple<T> sig){
        this.sig=sig;
    }
}