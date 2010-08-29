/*
 * creado el 19-08-2010
 */

package busqueda;

/**
 *
 * @author gusamasan
 */
public interface Estrategia {

    public void insertarNodo( Nodo unNodoNuevo );

    public boolean encontroAlMenosUnaSolucion();
    
    public Nodo obtenerSiguienteNodo();

    public void removerSiguienteNodo();

    public Solucion retornarSolucion();

    public void aumentarUnaUnidadCantidadNodosVisitados();

    public void disminuirUnaUnidadCantidadNodosVisitados();

    public int getCantidadNodosVisitados();

    public void setCantidadNodosVisitados( int pCantidad );

}
