package com.example.eightoff.modelo;

/**
 * Representa una columna del tablero.
 * Las cartas se construyen hacia abajo del mismo palo.
 * Solo se puede colocar un Rey en una columna vacía.
 */
public class Tableau{

    private ListaSimple<CartaInglesa> cartas;

    public Tableau(){
        cartas=new ListaSimple<>();
    }

    //Verifica si se puede agregar una carta
    public boolean sePuedeAgregarCarta(CartaInglesa carta){
        if(cartas.isEmpty()){
            return carta.getValor()==13; //solo Rey
        }
        CartaInglesa ultima=verCarta();
        return ultima.mismoPalo(carta)&&ultima.getValor()==carta.getValor()+1;
    }

    //Agrega una carta al final
    public void agregarCarta(CartaInglesa carta){
        carta.voltearArriba();
        cartas.insertaFin(carta);
    }

    //Agrega un bloque de cartas
    public void agregarBloque(ListaSimple<CartaInglesa> bloque){
        for(int i=0;i<bloque.contarElementos();i++){
            cartas.insertaFin(bloque.obtenerPorPosicion(i));
        }
    }

    //Regresa la carta de arriba sin eliminarla
    public CartaInglesa verCarta(){
        if(cartas.isEmpty()) return null;
        return cartas.getLast();
    }

    //Obtiene la carta en una posición
    public CartaInglesa obtenerCartaEnPosicion(int pos){
        return cartas.obtenerPorPosicion(pos);
    }

    //Obtiene un bloque válido de cartas a partir de una posición
    public ListaSimple<CartaInglesa> obtenerIleraDeCartas(int indiceInicio){
        ListaSimple<CartaInglesa> bloque=new ListaSimple<>();
        CartaInglesa actual=cartas.obtenerPorPosicion(indiceInicio);
        if(actual==null) return null;

        bloque.insertaFin(actual);
        for(int i=indiceInicio+1;i<cartas.contarElementos();i++){
            CartaInglesa siguiente=cartas.obtenerPorPosicion(i);
            if(actual.mismoPalo(siguiente)&&actual.getValor()==siguiente.getValor()+1){
                bloque.insertaFin(siguiente);
                actual=siguiente;
            }else{
                return null;
            }
        }
        return bloque;
    }

    //Elimina las últimas n cartas
    public void removerCartasFinales(int cantidad){
        for(int i=0;i<cantidad;i++){
            cartas.eliminaFin();
        }
    }

    //Obtiene y elimina un bloque de cartas del final
    public ListaSimple<CartaInglesa> obtenerYRemoverBloque(int cantidad){
        ListaSimple<CartaInglesa> bloque=new ListaSimple<>();
        int total=cartas.contarElementos();
        int indiceInicio=total-cantidad;
        for(int i=0;i<cantidad;i++){
            bloque.insertaFin(cartas.obtenerPorPosicion(indiceInicio+i));
        }
        for(int i=0;i<cantidad;i++){
            cartas.eliminaFin();
        }
        return bloque;
    }

    public boolean isEmpty(){
        return cartas.isEmpty();
    }

    public int contarCartas(){
        return cartas.contarElementos();
    }
}