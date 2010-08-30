
package busqueda;

import java.util.Iterator;
import java.util.Queue;

/**
 *
 * @author alcides
 */
public class SolucionEnAnchura implements Solucion {
    // ------------------------------------------------------------------------

    private int longitudDelCamino;
    private Iterator iteradorCola;
    private Queue colaSolucion;

    // ------------------------------------------------------------------------

    // Para verificar....
    public SolucionEnAnchura(Queue unaCola){
        this.colaSolucion = unaCola;
    }

    // Para verificar...
    public void armarSolucion() {
        Nodo unNodo;
        this.iteradorCola = this.colaSolucion.iterator();
        unNodo = ( Nodo )this.colaSolucion.element(); //this.obtenerSiguienteNodo();
        this.longitudDelCamino = 0;


        while( unNodo != null ){

            if( unNodo.formaParteDelCamino())
                this.longitudDelCamino++;

            unNodo = this.obtenerSiguienteNodo();
        }

        this.iteradorCola = this.colaSolucion.iterator();
    }

    public int obtenerLongitudDelCamino() {
        return this.longitudDelCamino;
    }

    // Para verificar...
    public Nodo obtenerSiguienteNodo() {
        Nodo nodo;

        if( this.iteradorCola.hasNext() )
            nodo = ( Nodo )this.iteradorCola.next();
        else
            nodo = null;

        return( nodo );
    }

}
