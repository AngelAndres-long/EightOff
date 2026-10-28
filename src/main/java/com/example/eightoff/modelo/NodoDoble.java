package com.example.eightoff.modelo;

/**
 * Nodo para la lista doblemente enlazada circular.
 * Tiene referencias al anterior y al siguiente.
 */
public class NodoDoble<T>{

    private T dato;
    private NodoDoble<T> prev;
    private NodoDoble<T> next;

    public NodoDoble(T dato){
        this.dato=dato;
        this.prev=null;
        this.next=null;
    }

    public T getDato(){
        return dato;
    }

    public void setDato(T dato){
        this.dato=dato;
    }

    public NodoDoble<T> getPrev(){
        return prev;
    }

    public void setPrev(NodoDoble<T> prev){
        this.prev=prev;
    }

    public NodoDoble<T> getNext(){
        return next;
    }

    public void setNext(NodoDoble<T> next){
        this.next=next;
    }
}