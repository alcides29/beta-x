/*
 * creado el 19-08-2010
 */

package npuzzle;


import busqueda.Nodo;

/**
 *
 * @author gusamasan
 */
public class CasillaPuzzle implements Nodo{
// -----------------------------------------------------------------------------

    public final String MOVIMIENTO_ARRIBA   = "A";
    public final String MOVIMIENTO_ABAJO    = "B";
    public final String MOVIMIENTO_IZQUIERDA= "C";
    public final String MOVIMIENTO_DERECHA  = "D";


    private int coordenadaX ,
                coordenadaY;

    private int valorActual;

    private boolean visitado;
    
    String movimiento;

// -----------------------------------------------------------------------------

    public CasillaPuzzle(){
        this.valorActual    = 0;
        this.visitado       = false;
    }
    
    public Object obtenerValor(){
        return( this.valorActual );
    }

    public boolean yaFueVisitado(){
        return( this.visitado );
    }

    public void marcarComoVisitado(){
        this.visitado = true;
    }

    public void marcarComoNoVisitado(){
        this.visitado = false;
    }

}
