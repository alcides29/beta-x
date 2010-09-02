/*
 * creado el 19-08-2010
 */

package busqueda;

/**
 * @author gusamasan
 */
public interface Solucion {

    public void armarSolucion();
    public int obtenerLongitudDelCamino();
    public int getCantidadNodosExpandidos();
    public void setCantidadNodosExpandidos( int cantidad );
    public Nodo obtenerSiguienteNodo();
    
}
