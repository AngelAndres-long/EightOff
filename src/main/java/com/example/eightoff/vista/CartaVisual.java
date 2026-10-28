package com.example.eightoff.vista;

import com.example.eightoff.modelo.CartaInglesa;

/**
 * Genera la ruta de la imagen correspondiente a una carta.
 * Usa los nombres de archivo que están en resources/imagenes/cartas.
 */
public class CartaVisual{

    private String ruta;

    public CartaVisual(CartaInglesa carta){
        ruta=sacarRutaImagen(carta);
    }

    private String sacarRutaImagen(CartaInglesa carta){
        if(carta==null){
            return "/imagenes/cartas/vacio.png"; //opcional, si tienes imagen de vacío
        }
        return "/imagenes/cartas/"+carta.getValor()+carta.getPalo().getNombre()+".png";
    }

    public String getRuta(){
        return ruta;
    }
}