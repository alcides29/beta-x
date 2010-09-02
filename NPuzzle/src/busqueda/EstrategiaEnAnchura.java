
package busqueda;

import java.util.Queue;
import java.util.LinkedList;

/**
 *
 * @author alcides
 */
public class EstrategiaEnAnchura implements Estrategia {
    // ------------------------------------------------------------------------

    private int cantidadNodosVisitados;
    private Solucion unaSolucion;
    private Queue cola;

    // ------------------------------------------------------------------------

    public EstrategiaEnAnchura(){
        this.cola = new LinkedList();
        this.unaSolucion = new SolucionEnAnchura( this.cola);
        this.cantidadNodosVisitados = 0;
    }

    public void aumentarUnaUnidadCantidadNodosVisitados(){
        this.cantidadNodosVisitados++;
    }

    public void disminuirUnaUnidadCantidadNodosVisitados(){
        this.cantidadNodosVisitados--;
    }
    
    public void insertarNodo(Nodo unNodoNuevo) {
        this.cola.add(unNodoNuevo);
    }

    public boolean encontroAlMenosUnaSolucion() {
        return( !this.cola.isEmpty() );
    }

    // Para verificar...
    public Nodo obtenerSiguienteNodo() {
        Nodo unNodo;
        
        if( !this.cola.isEmpty() ){
            unNodo = (Nodo) cola.remove();
            return unNodo;
        }else
            return( null );
    }

    public int getCantidadNodosVisitados(){
        return( this.cantidadNodosVisitados );
    }

    public void setCantidadNodosVisitados( int pCantidad ){
        this.cantidadNodosVisitados = pCantidad;
    }
    
    public void removerSiguienteNodo() {
        this.cola.remove();
    }

    public Solucion retornarSolucion() {
        this.unaSolucion.armarSolucion();
        return (this.unaSolucion);
    }

    public void aumentarUnaUnidadCantidadNodosExpandidos(){     
    }

    public void disminuirUnaUnidadCantidadNodosExpandidos(){        
    }
}
