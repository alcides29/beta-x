/*
 * creado el 19-08-2010
 */

package busqueda;

import java.util.Stack;
import java.util.Iterator;
import java.util.LinkedList;

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

        Nodo unNodo;

        LinkedList lista;

    // -------------------------------------------------------------------------

        this.iteradorPila   = this.pilaSolucion.iterator();

        unNodo                  = ( Nodo )this.pilaSolucion.lastElement(); //this.obtenerSiguienteNodo();
        this.longitudDelCamino  = 0;

        //lista   = new LinkedList();
        while( unNodo != null ){
                       
            if( unNodo.formaParteDelCamino()){
                //lista.push( unNodo );
                this.longitudDelCamino++;
            }

            unNodo  = this.obtenerSiguienteNodo();
        }
        

        this.iteradorPila   = this.pilaSolucion.iterator();
        //this.iteradorPila       = lista.iterator();
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
