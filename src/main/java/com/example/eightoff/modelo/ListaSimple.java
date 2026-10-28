package com.example.eightoff.modelo;

/**
 * Lista enlazada simple.
 * Se usa para los tableaus, las fundaciones y el historial de movimientos.
 */
public class ListaSimple<T>{

    private NodoSimple<T> inicio;

    public ListaSimple(){
        inicio=null;
    }

    //Inserta un elemento al inicio
    public void insertaInicio(T dato){
        NodoSimple<T> n=new NodoSimple<>(dato);
        n.setSig(inicio);
        inicio=n;
    }

    //Inserta un elemento al final
    public void insertaFin(T dato){
        NodoSimple<T> n=new NodoSimple<>(dato);
        if(inicio==null){
            inicio=n;
            return;
        }
        NodoSimple<T> r=inicio;
        while(r.getSig()!=null){
            r=r.getSig();
        }
        r.setSig(n);
    }

    //Elimina y regresa el elemento del inicio
    public T eliminaInicio(){
        if(inicio==null) return null;
        T dato=inicio.getDato();
        inicio=inicio.getSig();
        return dato;
    }

    //Elimina y regresa el elemento del final
    public T eliminaFin(){
        if(inicio==null) return null;
        if(inicio.getSig()==null){
            T dato=inicio.getDato();
            inicio=null;
            return dato;
        }
        NodoSimple<T> r=inicio;
        while(r.getSig().getSig()!=null){
            r=r.getSig();
        }
        T dato=r.getSig().getDato();
        r.setSig(null);
        return dato;
    }

    //Regresa el último elemento sin eliminarlo
    public T getLast(){
        if(inicio==null) return null;
        NodoSimple<T> r=inicio;
        while(r.getSig()!=null){
            r=r.getSig();
        }
        return r.getDato();
    }

    //Obtiene el elemento en una posición
    public T obtenerPorPosicion(int posicion){
        if(inicio==null) return null;
        NodoSimple<T> r=inicio;
        int contador=0;
        while(r!=null){
            if(contador==posicion) return r.getDato();
            contador++;
            r=r.getSig();
        }
        return null;
    }

    //Cuenta cuántos elementos hay
    public int contarElementos(){
        if(inicio==null) return 0;
        int contador=1;
        NodoSimple<T> r=inicio;
        while(r.getSig()!=null){
            contador++;
            r=r.getSig();
        }
        return contador;
    }

    public boolean isEmpty(){
        return inicio==null;
    }
}