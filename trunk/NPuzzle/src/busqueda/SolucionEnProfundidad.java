/*
 * creado el 19-08-2010
 */

package busqueda;

import java.util.Stack;
import java.util.Iterator;

/**
 *
 * @author gusamasan
 */
public class SolucionEnProfundidad implements Solucion{
// -----------------------------------------------------------------------------

    private int longitudDelCamino;

    private Iterator    iteradorPila;

    private Stack pilaSolucion, pilaAuxiliar;

// -----------------------------------------------------------------------------

    public SolucionEnProfundidad( Stack unaPila ){
        this.pilaSolucion   = unaPila;
    }



    public int obtenerLongitudDelCamino(){
        return( this.longitudDelCamino );
    }

    public void armarSolucion(){
    // -------------------------------------------------------------------------

        
    // -------------------------------------------------------------------------

        this.iteradorPila   = this.pilaSolucion.iterator();
        
    }

    public Nodo obtenerSiguienteNodo(){
    // ---------------------------------------------------------------

        Nodo nodo;
    // ---------------------------------------------------------------

        if( this.iteradorPila.hasNext() )
            nodo = ( Nodo )this.iteradorPila.next();
        else
            nodo = null;
        
        return( nodo );
    }
}
