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

    private Solucion    unaSolucion;

    private Stack       pila;

// ------------------------------------------------------------------------------


    public EstrategiaEnProfundidad(){
        this.pila           = new Stack();
        this.unaSolucion    = new SolucionEnProfundidad( this.pila );
    }


    public void insertarNodo( Nodo unNodo ){
        this.pila.add( unNodo );
    }

    public Nodo obtenerSiguienteNodo(){
    // ------------------------------------------------------------------------

        Nodo unNodo;

    // ------------------------------------------------------------------------

        if( !pila.empty() ){
            unNodo = (Nodo) pila.firstElement();            
            return( unNodo );
        }
        else
            return( null );
    }

    
    public Solucion retornarSolucion(){
    // -----------------------------------------------------------------------------

    // -----------------------------------------------------------------------------

        this.unaSolucion.armarSolucion();
        
        return( this.unaSolucion );
    }

}
