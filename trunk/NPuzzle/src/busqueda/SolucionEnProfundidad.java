/*
 * creado el 19-08-2010
 */

package busqueda;

import java.util.Stack;

/**
 *
 * @author gusamasan
 */
public class SolucionEnProfundidad implements Solucion{
// -----------------------------------------------------------------------------

    private int longitudDelCamino;
    
    private Stack pilaSolucion, pilaAuxiliar;

// -----------------------------------------------------------------------------

    public SolucionEnProfundidad( Stack unaPila ){
        this.pilaAuxiliar   = unaPila;
    }



    public int obtenerLongitudDelCamino(){
        return( this.longitudDelCamino );
    }

    public void armarSolucion(){
    // -------------------------------------------------------------------------

        
    // -------------------------------------------------------------------------

        
    }

    public Nodo obtenerSiguienteNodo(){
        return( null );
    }
}
