package com.example.eightoff.modelo;

/**
 * Contiene toda la lógica del juego Eight Off.
 * Administra tableaus, reservas, fundaciones y el historial de movimientos.
 * Está completamente separado de la interfaz.
 */
public class JuegoEightOff{

    private ListaSimple<Tableau> tableros;
    private ListaSimple<Reserva> reservas;
    private ListaSimple<Fundacion> fundaciones;
    private ListaSimple<Movimiento> historial;
    private Baraja baraja;

    public JuegoEightOff(){
        tableros=new ListaSimple<>();
        reservas=new ListaSimple<>();
        fundaciones=new ListaSimple<>();
        historial=new ListaSimple<>();

        for(Palo p:Palo.values()){
            fundaciones.insertaFin(new Fundacion(p));
        }
        for(int i=0;i<8;i++){
            reservas.insertaFin(new Reserva());
            tableros.insertaFin(new Tableau());
        }

        baraja=new Baraja();
        repartirCartas();
    }

    //Reparte 6 cartas a cada tableau y 4 a las primeras reservas
    private void repartirCartas(){
        for(int i=0;i<8;i++){
            Tableau t=tableros.obtenerPorPosicion(i);
            for(int j=0;j<6;j++){
                CartaInglesa c=baraja.obtenerUnaCarta();
                c.voltearArriba();
                t.agregarCarta(c);
            }
        }
        for(int i=0;i<4;i++){
            CartaInglesa c=baraja.obtenerUnaCarta();
            c.voltearArriba();
            reservas.obtenerPorPosicion(i).setCarta(c);
        }
    }

    //Intenta mover cartas de un lugar a otro
    public boolean moverCarta(String tipoOrigen,int numOrigen,int posCarta,String tipoDestino,int numDestino){
        if(tipoOrigen.equalsIgnoreCase(tipoDestino)&&numOrigen==numDestino) return false;

        ListaSimple<CartaInglesa> bloque=new ListaSimple<>();

        if(tipoOrigen.equalsIgnoreCase("TABLEAU")){
            Tableau origen=tableros.obtenerPorPosicion(numOrigen);
            bloque=origen.obtenerIleraDeCartas(posCarta);
            if(bloque==null) return false;
        }else if(tipoOrigen.equalsIgnoreCase("LIBRE")){
            CartaInglesa c=reservas.obtenerPorPosicion(numOrigen).getCarta();
            if(c==null) return false;
            bloque.insertaFin(c);
        }else{
            return false; //no se mueve desde fundaciones
        }

        int tamano=bloque.contarElementos();
        CartaInglesa primera=bloque.obtenerPorPosicion(0);
        boolean valido=false;

        if(tipoDestino.equalsIgnoreCase("TABLEAU")){
            Tableau destino=tableros.obtenerPorPosicion(numDestino);
            if(destino.sePuedeAgregarCarta(primera)){
                destino.agregarBloque(bloque);
                valido=true;
            }
        }else if(tipoDestino.equalsIgnoreCase("LIBRE")){
            if(tamano==1){
                Reserva celda=reservas.obtenerPorPosicion(numDestino);
                if(celda.estaVacia()){
                    celda.setCarta(primera);
                    valido=true;
                }
            }
        }else if(tipoDestino.equalsIgnoreCase("FUNDACION")){
            if(tamano==1){
                Fundacion f=fundaciones.obtenerPorPosicion(numDestino);
                if(f.agregarCarta(primera)){
                    valido=true;
                }
            }
        }

        if(valido){
            if(tipoOrigen.equalsIgnoreCase("TABLEAU")){
                tableros.obtenerPorPosicion(numOrigen).removerCartasFinales(tamano);
            }else if(tipoOrigen.equalsIgnoreCase("LIBRE")){
                reservas.obtenerPorPosicion(numOrigen).setCarta(null);
            }
            historial.insertaFin(new Movimiento(tipoOrigen,numOrigen,tipoDestino,numDestino,tamano));
            return true;
        }
        return false;
    }

    //Deshace el último movimiento
    public void deshacerMovimiento(){
        if(historial.isEmpty()) return;

        Movimiento ultimo=historial.eliminaFin();
        ListaSimple<CartaInglesa> bloque=new ListaSimple<>();

        if(ultimo.getTipoDestino().equalsIgnoreCase("TABLEAU")){
            bloque=tableros.obtenerPorPosicion(ultimo.getIdDestino()).obtenerYRemoverBloque(ultimo.getCantidadCartas());
        }else if(ultimo.getTipoDestino().equalsIgnoreCase("LIBRE")){
            Reserva celda=reservas.obtenerPorPosicion(ultimo.getIdDestino());
            bloque.insertaFin(celda.getCarta());
            celda.setCarta(null);
        }else if(ultimo.getTipoDestino().equalsIgnoreCase("FUNDACION")){
            Fundacion f=fundaciones.obtenerPorPosicion(ultimo.getIdDestino());
            bloque.insertaFin(f.removerUltimaCarta());
        }

        if(ultimo.getTipoOrigen().equalsIgnoreCase("TABLEAU")){
            tableros.obtenerPorPosicion(ultimo.getIdOrigen()).agregarBloque(bloque);
        }else if(ultimo.getTipoOrigen().equalsIgnoreCase("LIBRE")){
            reservas.obtenerPorPosicion(ultimo.getIdOrigen()).setCarta(bloque.obtenerPorPosicion(0));
        }
    }

    //Busca y regresa una jugada posible (pista)
    public Movimiento obtenerPista(){
        //Revisa tableaus hacia fundaciones
        for(int i=0;i<8;i++){
            CartaInglesa carta=tableros.obtenerPorPosicion(i).verCarta();
            if(carta!=null){
                for(int j=0;j<4;j++){
                    Fundacion f=fundaciones.obtenerPorPosicion(j);
                    CartaInglesa tope=f.getUltimaCarta();
                    if((tope==null&&carta.getValor()==14)||
                            (tope!=null&&tope.getPalo()==carta.getPalo()&&
                                    (tope.getValor()+1==carta.getValor()||(tope.getValor()==14&&carta.getValor()==2)))){
                        return new Movimiento("TABLEAU",i,"FUNDACION",j,1);
                    }
                }
                //Revisa tableaus hacia otros tableaus
                for(int j=0;j<8;j++){
                    if(i!=j&&tableros.obtenerPorPosicion(j).sePuedeAgregarCarta(carta)){
                        return new Movimiento("TABLEAU",i,"TABLEAU",j,1);
                    }
                }
            }
        }

        //Revisa reservas
        for(int i=0;i<8;i++){
            CartaInglesa carta=reservas.obtenerPorPosicion(i).getCarta();
            if(carta!=null){
                for(int j=0;j<4;j++){
                    Fundacion f=fundaciones.obtenerPorPosicion(j);
                    CartaInglesa tope=f.getUltimaCarta();
                    if((tope==null&&carta.getValor()==14)||
                            (tope!=null&&tope.getPalo()==carta.getPalo()&&
                                    (tope.getValor()+1==carta.getValor()||(tope.getValor()==14&&carta.getValor()==2)))){
                        return new Movimiento("LIBRE",i,"FUNDACION",j,1);
                    }
                }
                for(int j=0;j<8;j++){
                    if(tableros.obtenerPorPosicion(j).sePuedeAgregarCarta(carta)){
                        return new Movimiento("LIBRE",i,"TABLEAU",j,1);
                    }
                }
            }
        }

        //Mover de tableau a reserva vacía
        for(int i=0;i<8;i++){
            if(reservas.obtenerPorPosicion(i).estaVacia()){
                for(int j=0;j<8;j++){
                    CartaInglesa carta=tableros.obtenerPorPosicion(j).verCarta();
                    if(carta!=null){
                        return new Movimiento("TABLEAU",j,"LIBRE",i,1);
                    }
                }
            }
        }
        return null;
    }

    //Verifica si el jugador ya ganó
    public boolean estaGanado(){
        for(int i=0;i<4;i++){
            Fundacion f=fundaciones.obtenerPorPosicion(i);
            if(f.estaVacia()||f.getUltimaCarta().getValor()!=13){
                return false;
            }
        }
        return true;
    }

    public ListaSimple<Tableau> getTableros(){
        return tableros;
    }

    public ListaSimple<Reserva> getReservas(){
        return reservas;
    }

    public ListaSimple<Fundacion> getFundaciones(){
        return fundaciones;
    }
}