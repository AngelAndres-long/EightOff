package com.example.eightoff.modelo;

/**
 * Lista doblemente enlazada circular.
 * Se usa exclusivamente para la baraja.
 */
public class ListaDobleCircular<T>{

    private NodoDoble<T> begin;
    private NodoDoble<T> end;

    public ListaDobleCircular(){
        begin=null;
        end=null;
    }

    //Inserta al inicio
    public void insertBeginning(T dato){
        NodoDoble<T> n=new NodoDoble<>(dato);
        if(begin==null){
            begin=n;
            end=n;
            n.setNext(begin);
            n.setPrev(begin);
        }else{
            n.setNext(begin);
            n.setPrev(end);
            begin.setPrev(n);
            end.setNext(n);
            begin=n;
        }
    }

    //Inserta al final
    public void insertEnd(T dato){
        if(begin==null){
            insertBeginning(dato);
        }else{
            NodoDoble<T> n=new NodoDoble<>(dato);
            n.setPrev(end);
            n.setNext(begin);
            end.setNext(n);
            begin.setPrev(n);
            end=n;
        }
    }

    //Elimina y regresa el primer elemento
    public T removeFirst(){
        if(begin==null) return null;
        T dato=begin.getDato();
        if(begin==end){
            begin=null;
            end=null;
            return dato;
        }
        begin=begin.getNext();
        begin.setPrev(end);
        end.setNext(begin);
        return dato;
    }

    //Elimina y regresa el último elemento
    public T removeLast(){
        if(begin==null) return null;
        if(begin==end) return removeFirst();
        T dato=end.getDato();
        end=end.getPrev();
        end.setNext(begin);
        begin.setPrev(end);
        return dato;
    }

    //Elimina el elemento en la posición indicada
    public T remove(int pos){
        if(begin==null) return null;
        if(pos==0) return removeFirst();
        NodoDoble<T> r=begin;
        int contador=0;
        do{
            if(contador==pos){
                if(r==end) return removeLast();
                r.getPrev().setNext(r.getNext());
                r.getNext().setPrev(r.getPrev());
                return r.getDato();
            }
            contador++;
            r=r.getNext();
        }while(r!=begin);
        return null;
    }

    //Regresa el tamaño de la lista
    public int size(){
        if(begin==null) return 0;
        int contador=1;
        NodoDoble<T> r=begin;
        while(r!=end){
            contador++;
            r=r.getNext();
        }
        return contador;
    }

    public boolean isEmpty(){
        return begin==null;
    }
}