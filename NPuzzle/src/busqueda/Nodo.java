/*
 * creado el 19-08-2010
 */

package busqueda;

/**
 *
 * @author gusamasan
 */
public interface Nodo {
    public Object obtenerValor();

    public boolean formaParteDelCamino();

    public Nodo obtenerClon();
}
