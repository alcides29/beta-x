/**
 * Clase que implementa los métodos especificados en la interfaz Nodo. Las instancias
 * de esta clase son utilizadas para crear el tablero del rompecabezas y para obtener
 * datos útiles en el proceso de búsqueda de una solución.
 * 
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


    private int         coordenadaX ,
                        coordenadaY;

    private int         valorActual;

    private boolean     esCasillaVacia  ,
                        expandido       ,
                        visitado;
    
    String              movimiento;

    CasillaPuzzle       nodoPadre;

// -----------------------------------------------------------------------------


    /**
     * Constructor
     */
    public CasillaPuzzle(){
        this.valorActual    = 0;
        this.visitado       = false;
        this.esCasillaVacia = false;
        this.nodoPadre      = null;
        this.expandido      = false;
    }


    
    /**
     * Retorna la referencia a el nodo padre
     *
     * @param ninguno.
     * @return objeto del tipo CasillaPuzzle que representa el nodo padre
     * @throws No dispara ninguna excepcion.
     */
    public CasillaPuzzle getNodoPadre(){
        return( this.nodoPadre );
    }


    /**
     * Asigna un nodo padre para la instancia de esta clase
     *
     * @param objeto del tipo CasillaPuzzle que representa el nodo padre
     * @return void
     * @throws No dispara ninguna excepcion.
     */
    public void setNodoPadre( CasillaPuzzle pNodo ){
        this.nodoPadre  = pNodo;
    }


    /**
     * Retorna la fila del tablero en que está ubicada la casilla.
     *
     * @param ninguno.
     * @return int que indica la X del par (x,y) de la ubicación
     * @throws No dispara ninguna excepcion.
     */
    public int getCoordenadaX(){
        return( this.coordenadaX );
    }


    /**
     * Asigna la fila del tablero en que está ubicada la casilla.
     *
     * @param int que indica la X del par (x,y) de la ubicación
     * @return void
     * @throws No dispara ninguna excepcion.
     */
    public void setCoordenadaX( int pX ){
        this.coordenadaX = pX;
    }


    /**
     * Retorna la columna del tablero en que está ubicada la casilla.
     *
     * @param ninguno.
     * @return int que indica la Y del par (x,y) de la ubicación
     * @throws No dispara ninguna excepcion.
     */
    public int getCoordenadaY(){
        return( this.coordenadaY );
    }

    

    /**
     * Asigna la columna del tablero en que está ubicada la casilla.
     *
     * @param int que indica la Y del par (x,y) de la ubicación
     * @return void
     * @throws No dispara ninguna excepcion.
     */
    public void setCoordenadaY( int pY ){
        this.coordenadaY = pY;
    }


    /**
     * Implementa el método obtenerClon definido en la interfaz Nodo. Se utiliza
     * especialmente a la hora de preparar la solución encontrada.
     *
     * @param   ninguno
     * @return  objeto del tipo Nodo que contiene información vital extraído del
     *          la instancia de esta clase
     * @throws No dispara ninguna excepcion.
     */
    public Nodo obtenerClon(){
    // ------------------------------------------------------------------------

        CasillaPuzzle unClon;

    // ------------------------------------------------------------------------

        unClon = new CasillaPuzzle();

        unClon.insertarValor(   this.obtenerValor()     );
        unClon.setCoordenadaX(  this.getCoordenadaX()   );
        unClon.setCoordenadaY(  this.getCoordenadaY()   );

        if( this.yaFueVisitado() )
            unClon.marcarComoVisitado();

        return( unClon );
    }


    /**
     * Retorna el valor almacenado en la instancia de este objeto. En principio
     * se almacenan números, pero se podrían almacenar otros tipos de datos adecuados
     *
     * @param   ninguno
     * @return  objeto del tipo Object que contiene el valor almacenado
     * @throws No dispara ninguna excepcion.
     */
    public Object obtenerValor(){
        return( new Integer( this.valorActual ) );
    }
    

    /**
     * Asigna un valor para la instancia de este objeto. En principio
     * se almacenan números, pero se podrían almacenar otros tipos de datos adecuados
     *
     * @param   objeto del tipo Object que contiene el valor a asignar
     * @return  void
     * @throws No dispara ninguna excepcion.
     */
    public void insertarValor( Object pValor ){
        this.valorActual    = ( (Integer)pValor ).intValue();
    }


    /**
     * Indica si una casilla ya fue visitada o no
     *
     * @param   ninguno
     * @return  boolean que indica (true) si ya se visitó; (false) en caso contrario
     * @throws No dispara ninguna excepcion.
     */
    public boolean yaFueVisitado(){
        return( this.visitado );
    }



    /**
     * Marca a la instancia de este objeto como visitado
     *
     * @param   ninguno
     * @return  void
     * @throws No dispara ninguna excepcion.
     */
    public void marcarComoVisitado(){
        this.visitado = true;
    }



    /**
     * Indica que este nodo no ha sido visitado
     *
     * @param   ninguno
     * @return  void
     * @throws No dispara ninguna excepcion.
     */
    public void marcarComoNoVisitado(){
        this.visitado = false;
    }


    /**
     * Indica si la instancia de esta clase representa la casilla vacía
     *
     * @param   ninguno
     * @return  boolean que indica si es la casilla vacía (true) o no (false)
     * @throws No dispara ninguna excepcion.
     */
    public boolean esCasillaVacia(){
        return( this.esCasillaVacia );
    }



    /**
     * Convierte a la instancia de esta clase como una casilla vacía
     *
     * @param   ninguno
     * @return  void
     * @throws  No dispara ninguna excepcion.
     */
    public void marcarComoCasillaVacia(){
        this.esCasillaVacia = true;
        this.valorActual    = NPuzzle.VALOR_VACIO;
    }


    /**
     * Indica que la instancia de esta clase no es una casilla vacía. Por defecto
     * las instancias creadas no se marcan como casillas vacías.
     *
     * @param   ninguno
     * @return  void
     * @throws  No dispara ninguna excepcion.
     */
    public void marcarComoCasillaNoVacia(){
        this.esCasillaVacia = false;
    }


    /**
     * Indica que la instancia de esta clase ya fue expandida
     *
     * @param   ninguno
     * @return  boolean que indica si fue expadida (true) o no (false)
     * @throws  No dispara ninguna excepcion.
     */
    public boolean fueExpandido(){
        return( this.expandido );
    }



    /**
     * Indica si la instancia de esta clase forma parte del camino que lleva a la
     * solución
     *
     * @param   ninguno
     * @return  boolean que indica si forma parte (true) o no (false)
     * @throws  No dispara ninguna excepcion.
     */
    public boolean formaParteDelCamino(){
        return( this.yaFueVisitado() );
    }


    /**
     * Indica que la instancia de esta clase no fue expandida
     *
     * @param   ninguno
     * @return  void
     * @throws  No dispara ninguna excepcion.
     */
    public void marcaComoNoExpandido(){
        this.expandido = false;
    }


    /**
     * Indica que la instancia de esta clase ya fue expandida
     *
     * @param   ninguno
     * @return  void
     * @throws  No dispara ninguna excepcion.
     */
    public void marcarComoExpandido(){
        this.expandido = true;
    }
}