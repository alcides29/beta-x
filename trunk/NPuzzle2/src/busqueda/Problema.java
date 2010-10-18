/*
 * creado el 19-08-2010
 */

package busqueda;

import busqueda.Nodo;
import busqueda.Estrategia;

/**
 *
 * @author gusamasan
 */
public interface Problema {
    public void inicializar( Estrategia unaEstrategia );
    public void expandir( Nodo nodoActual, Estrategia unaEstrategia );
    public boolean esNodoMeta( Nodo nodoActual );
}
