/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package busqueda;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 *
 * @author alcides
 */
public class EstrategiaEnAnchura implements Estrategia {
    // ------------------------------------------------------------------------

    private Solucion unaSolucion;
    private Queue cola;

    // ------------------------------------------------------------------------

    public EstrategiaEnAnchura(){
        this.cola = new Queue();
        this.unaSolucion = new SolucionEnAnchura( this.cola);
    }

    public void insertarNodo(Nodo unNodoNuevo) {
        this.cola.add(unNodoNuevo);
    }

    public boolean encontroAlMenosUnaSolucion() {
        return( !this.cola.isEmpty() );
    }

    public Nodo obtenerSiguienteNodo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removerSiguienteNodo() {
        this.cola.remove();
    }

    public Solucion retornarSolucion() {
        this.unaSolucion.armarSolucion();
        return (this.unaSolucion);
    }

}
