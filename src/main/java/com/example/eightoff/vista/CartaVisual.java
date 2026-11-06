package com.example.eightoff.vista;

import com.example.eightoff.modelo.CartaInglesa;
import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Map;

/**
 * Genera y cachea las imágenes de las cartas.
 * Las imágenes se cargan una sola vez y se escalan para evitar lag.
 */
public class CartaVisual{

    private static final Map<String,Image> cache=new HashMap<>();

    private final Image imagen;

    public CartaVisual(CartaInglesa carta){
        String ruta="/imagenes/cartas/"+carta.getValor()+carta.getPalo().getNombre()+".png";
        this.imagen=obtenerImagen(ruta);
    }

    private Image obtenerImagen(String ruta){
        if(cache.containsKey(ruta)){
            return cache.get(ruta);
        }
        //Se carga ya escalada a un tamaño razonable
        Image img=new Image(getClass().getResourceAsStream(ruta),120,0,true,true);
        cache.put(ruta,img);
        return img;
    }

    public Image getImagen(){
        return imagen;
    }
}