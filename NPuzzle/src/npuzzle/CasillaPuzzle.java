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

    private boolean esCasillaVacia  ,
                    expandido       ,
                    visitado;
    
    String movimiento;

    CasillaPuzzle   nodoPadre;

// -----------------------------------------------------------------------------

    public CasillaPuzzle(){
        this.valorActual    = 0;
        this.visitado       = false;
        this.esCasillaVacia = false;
        this.nodoPadre      = null;
        this.expandido      = false;
    }


    public CasillaPuzzle getNodoPadre(){
        return( this.nodoPadre );
    }

    public void setNodoPadre( CasillaPuzzle pNodo ){
        this.nodoPadre  = pNodo;
    }

    public int getCoordenadaX(){
        return( this.coordenadaX );
    }

    public void setCoordenadaX( int pX ){
        this.coordenadaX = pX;
    }

    public int getCoordenadaY(){
        return( this.coordenadaY );
    }

    public void setCoordenadaY( int pY ){
        this.coordenadaY = pY;
    }

    public Object obtenerValor(){
        return( new Integer( this.valorActual ) );
    }

    public void insertarValor( Object pValor ){
        this.valorActual    = ( (Integer)pValor ).intValue();
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

    public boolean esCasillaVacia(){
        return( this.esCasillaVacia );
    }

    public void marcarComoCasillaVacia(){
        this.esCasillaVacia = true;
        this.valorActual    = NPuzzle.VALOR_VACIO;
    }

    public void marcarComoCasillaNoVacia(){
        this.esCasillaVacia = false;
    }

    public boolean fueExpandido(){
        return( this.expandido );
    }

    public void marcaComoNoExpandido(){
        this.expandido = false;
    }

    public void marcarComoExpandido(){
        this.expandido = true;
    }
}
