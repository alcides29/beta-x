/*
 * creado el 19-08-2010
 */

package busqueda;

/**
 *
 * @author gusamasan
 */
public class SolucionFalsa implements Solucion{

    public void armarSolucion(){

    }
    
    public int obtenerLongitudDelCamino(){
        return( -1 );
    }

    public Nodo obtenerSiguienteNodo(){
        return( null  );
    }

    public int getCantidadNodosExpandidos(){
        return( 0 );
    }

    public void setCantidadNodosExpandidos( int pCantidadNodos ){
    }
    
    public int getCantidadNodosVisitados(){
        return( 0 );
    }

    public void setCantidadNodosVisitados( int pCantidadNodos ){
    }
}
