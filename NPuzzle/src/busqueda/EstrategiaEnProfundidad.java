/*
 * creado el 19-08-2010
 */

package busqueda;

import java.util.Stack;
/**
 * @author gusamasan
 */
public class EstrategiaEnProfundidad implements Estrategia{
// ------------------------------------------------------------------------------

    private int         cantidadNodosVisitados          ,
                        menorCantidadVisitados;
    
    private Solucion    unaSolucion;

    private Stack       pila;

// ------------------------------------------------------------------------------


    public EstrategiaEnProfundidad(){
        this.pila                   = new Stack();
        this.unaSolucion            = new SolucionEnProfundidad( this.pila );
        this.cantidadNodosVisitados = 0;
    }


    public void aumentarUnaUnidadCantidadNodosVisitados(){
        this.cantidadNodosVisitados++;
    }

    public void disminuirUnaUnidadCantidadNodosVisitados(){
        this.cantidadNodosVisitados--;
    }

    public boolean encontroAlMenosUnaSolucion(){
        
        return( !this.pila.empty() );
    }

    public void insertarNodo( Nodo unNodo ){
        this.pila.push( unNodo );

    }

    public Nodo obtenerSiguienteNodo(){
    // ------------------------------------------------------------------------

        Nodo unNodo;

    // ------------------------------------------------------------------------

        if( !pila.empty() ){
            unNodo = (Nodo) pila.lastElement();
            return( unNodo );
        }
        else
            return( null );
    }

    public int getCantidadNodosVisitados(){
        return( this.cantidadNodosVisitados );
    }

    public void setCantidadNodosVisitados( int pCantidad ){
        this.cantidadNodosVisitados = pCantidad;
    }

    public void removerSiguienteNodo(){
        this.pila.pop();
    }

    public Solucion retornarSolucion(){
    // -----------------------------------------------------------------------------;

        SolucionEnProfundidad nuevaSolucion;

    // -----------------------------------------------------------------------------

        nuevaSolucion    = new SolucionEnProfundidad( (Stack)this.pila.clone() );

        nuevaSolucion.armarSolucion();

        return( nuevaSolucion );
    }

}
