/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
    private Iterator    iteradorCola;
    private Queue colaSolucion, colaAuxiliar;

    // ------------------------------------------------------------------------

    public SolucionEnAnchura(Queue unaCola){
        this.colaSolucion = unaCola;
    }

    public void armarSolucion() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int obtenerLongitudDelCamino() {
        return this.longitudDelCamino;
    }

    public Nodo obtenerSiguienteNodo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
