/*
 * creado el 19-08-2010
 */

package npuzzle;


import busqueda.*;

/**
 *
 * @author gusamasan
 */
public class NPuzzle implements Problema{
    public NPuzzle(){
        
    }


    public void inicializar( Estrategia unaEstrategia ){
        // buscar ubicaciOn del casillero blanco

        // hacer copia, si hace falta, de la estructura de deatos que representa el tablero
        // de nUmeros
    }

    public void expandir( Nodo nodoActual, Estrategia unaEstrategia ){
    // ------------------------------------------------------------------------

        CasillaPuzzle nodoHijo;
        
    // ------------------------------------------------------------------------

      
        // expandir el nodo actual: para cada hijo, ejecutar la LINEA siguiente
        unaEstrategia.insertarNodo( nodoHijo );
    }

    public boolean esNodoMeta( Nodo nodoActual ){
        return( false );
    }
}
