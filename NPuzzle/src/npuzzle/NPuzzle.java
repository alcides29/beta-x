/*
 * creado el 19-08-2010
 */

package npuzzle;


import busqueda.*;
import java.util.*;

/**
 *
 * @author gusamasan
 */
public class NPuzzle implements Problema{
// -----------------------------------------------------------------------------

    public static final int VALOR_VACIO = 9999;

    private int cantidadFilasTablero;

    private HashMap mapa;
    
    private CasillaPuzzle   casillaVacia,
                            tablero[][];

// -----------------------------------------------------------------------------

    /*
    public NPuzzle(){
        this.mapa   = new HashMap();
    }
     */

    /**
     * Constructor
     * @param pN Dimension del tablero
     */
    public NPuzzle( int pN ){
        this.mapa   = new HashMap();
        this.cantidadFilasTablero = pN;
        this.tablero= new CasillaPuzzle[ pN ][ pN ];

    }


    public int getCantidadFilasTablero(){
        return( this.cantidadFilasTablero );
    }

    public void setCantidadFilasTablero( int pCantidadFilasTablero ){
        this.cantidadFilasTablero   = pCantidadFilasTablero;
    }

    public CasillaPuzzle[][] getTablero(){
        return( null );
    }

    public void setTablero( CasillaPuzzle[][] pTablero ){
        this.tablero    = pTablero;
    }

    public void inicializar( Estrategia unaEstrategia ){
    // -------------------------------------------------------------------------

        int fila, columna, menor;
    
    // -------------------------------------------------------------------------

        //this.casillaVacia   = new CasillaPuzzle();

    
            for( fila= 0; fila < this.cantidadFilasTablero; fila++ ){

                for( columna=0; columna < this.cantidadFilasTablero; columna++ ){

                    if( this.tablero[ fila ][ columna ].esCasillaVacia() ){ // es la casilla vacIa
                        this.casillaVacia = this.tablero[ fila ][ columna ];
                        break;
                    }
                }

            }

        if( unaEstrategia.encontroAlMenosUnaSolucion() ){
            this.prepararOtraOpcion( ( CasillaPuzzle )unaEstrategia.obtenerSiguienteNodo(),
                                        unaEstrategia );
        }
        else
            this.expandir( this.casillaVacia, unaEstrategia );
            
        
    }

    private void prepararOtraOpcion( CasillaPuzzle casillaActual, Estrategia unaEstrategia ){
    // -------------------------------------------------------------------------

        CasillaPuzzle casillaPadre, otraOpcion;

    // -------------------------------------------------------------------------

        casillaPadre     = casillaActual.getNodoPadre();

        unaEstrategia.removerSiguienteNodo();

        this.casillaVacia.marcarComoCasillaNoVacia();
        //this.casillaVacia.marcarComoNoVisitado();
        this.casillaVacia.insertarValor( casillaPadre.obtenerValor() );

        this.casillaVacia   = this.tablero[ casillaPadre.getCoordenadaX() ][  casillaPadre.getCoordenadaY() ];
        this.casillaVacia.marcarComoCasillaVacia();

    }

    public void expandir( Nodo nodoActual, Estrategia unaEstrategia ){
    // ------------------------------------------------------------------------

        int xHijo, yHijo, xPadre, yPadre;

        boolean pudoExpandirse;

        CasillaPuzzle nodoHijo, nodoCopia;

        CasillaPuzzle casillaActual;
        
    // ------------------------------------------------------------------------

        casillaActual   = ( CasillaPuzzle )nodoActual;


        pudoExpandirse = false;

        if( casillaActual.getNodoPadre() != null ){
            xPadre  = casillaActual.getNodoPadre().getCoordenadaX();
            yPadre  = casillaActual.getNodoPadre().getCoordenadaY();
        }
        else{
            xPadre  = -1;
            yPadre  = -1;
        }
        //if( !casillaActual.fueExpandido() ){
            // Expandir el hijo de ARRIBA
            xHijo   =  casillaActual.getCoordenadaX() - 1;
            yHijo   =   casillaActual.getCoordenadaY();
            
            if( xHijo >= 0 ){

                nodoHijo    = this.tablero[ xHijo ][ casillaActual.getCoordenadaY() ];

               //if( !nodoHijo.yaFueVisitado() && nodoHijo != casillaActual.getNodoPadre() ){
                if( !elNodoYaFueVisitado( nodoHijo ) && !( xHijo == xPadre && yHijo == yPadre ) ){
                    nodoCopia   = new CasillaPuzzle();
                    nodoCopia.setCoordenadaX( nodoHijo.getCoordenadaX() );
                    nodoCopia.setCoordenadaY( nodoHijo.getCoordenadaY() );
                    nodoCopia.insertarValor( nodoHijo.obtenerValor() );
                    nodoCopia.setNodoPadre( casillaActual );
                    
                    unaEstrategia.insertarNodo( nodoCopia );
                    pudoExpandirse  = true;
                }

            }

            // Expandir el hijo de DERECHA
            xHijo   =  casillaActual.getCoordenadaX();
            yHijo   =  casillaActual.getCoordenadaY() + 1;

            if( yHijo < this.cantidadFilasTablero ){
                nodoHijo    = this.tablero[ casillaActual.getCoordenadaX() ][ yHijo ];

                if( !elNodoYaFueVisitado( nodoHijo ) && !( xHijo == xPadre && yHijo == yPadre ) ){
                    nodoCopia   = new CasillaPuzzle();
                    nodoCopia.setCoordenadaX( nodoHijo.getCoordenadaX() );
                    nodoCopia.setCoordenadaY( nodoHijo.getCoordenadaY() );
                    nodoCopia.insertarValor( nodoHijo.obtenerValor() );
                    nodoCopia.setNodoPadre( casillaActual );


                    unaEstrategia.insertarNodo( nodoCopia );
                    pudoExpandirse  = true;
                }
            }


            // Expandir el hijo de ABAJO
            xHijo   =  casillaActual.getCoordenadaX() + 1;
            yHijo   =   casillaActual.getCoordenadaY();

            if( xHijo < this.cantidadFilasTablero ){
                nodoHijo    = this.tablero[ xHijo ][ casillaActual.getCoordenadaY() ];

                if( !elNodoYaFueVisitado( nodoHijo ) && !( xHijo == xPadre && yHijo == yPadre ) ){
                    nodoCopia   = new CasillaPuzzle();
                    nodoCopia.setCoordenadaX( nodoHijo.getCoordenadaX() );
                    nodoCopia.setCoordenadaY( nodoHijo.getCoordenadaY() );
                    nodoCopia.insertarValor( nodoHijo.obtenerValor() );
                    nodoCopia.setNodoPadre( casillaActual );

                    unaEstrategia.insertarNodo( nodoCopia );
                    pudoExpandirse  = true;
                }
            }


            // Expandir el hijo de IZQUIERDA
            xHijo   =   casillaActual.getCoordenadaX();
            yHijo   =  casillaActual.getCoordenadaY() - 1;

            if( yHijo >= 0 ){
                nodoHijo    = this.tablero[ casillaActual.getCoordenadaX() ][ yHijo ];

                if( !elNodoYaFueVisitado( nodoHijo ) && !( xHijo == xPadre && yHijo == yPadre ) ){
                    nodoCopia   = new CasillaPuzzle();
                    nodoCopia.setCoordenadaX( nodoHijo.getCoordenadaX() );
                    nodoCopia.setCoordenadaY( nodoHijo.getCoordenadaY() );
                    nodoCopia.insertarValor( nodoHijo.obtenerValor() );
                    nodoCopia.setNodoPadre( casillaActual );

                    unaEstrategia.insertarNodo( nodoCopia );
                    pudoExpandirse  = true;
                }
            }
        //}
        //else
        //    casillaActual.marcaComoNoExpandido();
        
        
        if( pudoExpandirse ){ 
           casillaActual.marcarComoExpandido();
        }
        else // ya no hay caminos y como no es meta se debe probar otra opción
            this.prepararOtraOpcion( casillaActual, unaEstrategia );
    }


    private void moverCasillaVacia( CasillaPuzzle nodoActual ){
    // ----------------------------------------------------------------------

        CasillaPuzzle casilla;

    // ----------------------------------------------------------------------

        casilla     = this.tablero[ nodoActual.getCoordenadaX() ][ nodoActual.getCoordenadaY() ];

        this.casillaVacia.insertarValor( casilla.obtenerValor() );
        this.casillaVacia.marcarComoCasillaNoVacia();

        //nodoActual.setNodoPadre( this.casillaVacia );
        nodoActual.marcarComoVisitado();

        this.casillaVacia   = casilla;
        //this.casillaVacia.marcarComoVisitado();
        this.marcarNodoComoVisitado( this.casillaVacia );

        this.casillaVacia.marcarComoCasillaVacia();

        nodoActual.setCoordenadaX( this.casillaVacia.getCoordenadaX() );
        nodoActual.setCoordenadaY( this.casillaVacia.getCoordenadaY() );
        /*
        this.casillaVacia.insertarValor( nodoActual.obtenerValor() );
        this.casillaVacia.marcarComoCasillaNoVacia();
        
        nodoActual.setNodoPadre( this.casillaVacia );        
        
        this.casillaVacia   = nodoActual;
        this.casillaVacia.marcarComoVisitado();        
        this.marcarNodoComoVisitado( this.casillaVacia );

        this.casillaVacia.marcarComoCasillaVacia();
        
         */
    }


    private boolean elTableroEstaOrdenado(){
    // -------------------------------------------------------------------------

        int fila, columna, menor;
        int valorActual;

    // -------------------------------------------------------------------------
        
        menor   = ( ( Integer ) this.tablero[ 0 ][ 0 ].obtenerValor() ).intValue();

        for( fila= 0; fila < this.cantidadFilasTablero; fila++ ){

            for( columna=0; columna < this.cantidadFilasTablero; columna++ ){

                valorActual = ( ( Integer ) this.tablero[ fila ][ columna ].obtenerValor() ).intValue();

                if( valorActual < menor )
                    return(  false );
                else
                    menor   = valorActual;

            }

        }

        return( true );
    }

    
    public boolean esNodoMeta( Nodo nodoActual ){
    // ------------------------------------------------------------------------

        Object         valor;
        boolean         estaOrdenado;
        CasillaPuzzle   casillaAuxiliar, casillaActual;

    // ------------------------------------------------------------------------

        
        /*
        if( ( (CasillaPuzzle )nodoActual ).yaFueVisitado() )
            return( false );
        else{
         *
         */            

            valor   = nodoActual.obtenerValor();

            this.moverCasillaVacia(( CasillaPuzzle ) nodoActual );

            estaOrdenado        = elTableroEstaOrdenado();

            if( estaOrdenado ){                

                casillaActual   = ( CasillaPuzzle )nodoActual;

                casillaAuxiliar = new CasillaPuzzle();
                casillaAuxiliar.insertarValor( valor );
                casillaAuxiliar.setCoordenadaX( casillaActual.getCoordenadaX() );
                casillaAuxiliar.setCoordenadaY( casillaActual.getCoordenadaY() );
                
                this.marcarNodoComoVisitado( casillaAuxiliar );

                return( estaOrdenado );
            }
            else
                return( false );
        //}
    }

    public void marcarNodoComoVisitado( CasillaPuzzle casilla ){
    // -------------------------------------------------------------------------

        String firma;

    // -------------------------------------------------------------------------

        firma   = "" + ( ( Integer )casilla.obtenerValor() ).intValue();
        firma+= casilla.getCoordenadaX();
        firma+= casilla.getCoordenadaY();


        this.mapa.put( firma, firma );
    }

    public void marcarNodoComoNoVisitado( CasillaPuzzle casilla ){
    // -------------------------------------------------------------------------

        String firma;

    // -------------------------------------------------------------------------

        firma   = "" + ( ( Integer )casilla.obtenerValor() ).intValue();
        firma+= casilla.getCoordenadaX();
        firma+= casilla.getCoordenadaY();

        this.mapa.remove( firma );
    }

    public boolean elNodoYaFueVisitado( CasillaPuzzle casilla ){
    // -------------------------------------------------------------------------

        String firma;

    // -------------------------------------------------------------------------

        firma   = "" + ( ( Integer )casilla.obtenerValor() ).intValue();
        firma+= casilla.getCoordenadaX();
        firma+= casilla.getCoordenadaY();

        if( this.mapa.get( firma ) != null )
            return( true );
        else
            return( false );
    }

    /**
     * @function Funcion que genera el tablero
     */
    public void generarTablero(){
    // -------------------------------------------------------------------------

        int fila;
        int columna;
        int valor = 1;
        
    // -------------------------------------------------------------------------

        for( fila= 0; fila < this.cantidadFilasTablero; fila++ ){

            for( columna=0; columna < this.cantidadFilasTablero; columna++ ){
                
                // Asigna la casilla vacia
                if( (fila == this.cantidadFilasTablero-1) && (columna == this.cantidadFilasTablero-1) ){

                    this.tablero[fila][columna] = new CasillaPuzzle();
                    this.tablero[fila][columna].marcarComoCasillaVacia();

                    this.casillaVacia = new CasillaPuzzle();
                    this.casillaVacia.setCoordenadaX(fila);
                    this.casillaVacia.setCoordenadaY(columna);
                    this.casillaVacia.insertarValor(this.VALOR_VACIO);
                }
                else{
                    this.tablero[fila][columna] = new CasillaPuzzle();
                    this.tablero[fila][columna].setCoordenadaX(fila);
                    this.tablero[fila][columna].setCoordenadaY(columna);
                    this.tablero[fila][columna].insertarValor(new Integer(valor));
                    valor++;
                }
            }

        }
        this.imprimirTablero();
    }

    /**
     * @function Funcion que desordena el tablero
     * @note  Para implementacion
     */
    public void desordenarTablero(){
        // ---------------------------------------------------------------------

        Random rnd = new Random();
        int n = 4;
        int i;
        int posicion;
        int anterior = 1;

        // ---------------------------------------------------------------------

        for ( i = 0; i < 100; i++){
            posicion = rnd.nextInt( n );

            // Verifica que la posicion sea valida y no se mude a la posicion anterior
            while( !this.movimientoValido( posicion )  || ( posicion == anterior ) ){
                System.out.println("posicion no valida");
                posicion = rnd.nextInt( n );
            }
            
            System.out.println("Mover: " + posicion);
            this.moverCasilla( posicion );
            // llamar a la la funcion del GUI
            this.imprimirTablero();
        }
    }

    /**
     * @function Verifica si el movimiento es valido
     * @param pos
     * @return boolean
     */
    private boolean movimientoValido( int pos ){
        // ---------------------------------------------------------------------

        int up = 0;
        int down = 1;
        int left = 2;
        int right = 3;

        // ---------------------------------------------------------------------

        if( ( pos == up ) &&  ( this.casillaVacia.getCoordenadaX() -1 ) < 0 )
            return false;
        else if( ( pos == down ) &&  ( this.casillaVacia.getCoordenadaX() + 1 ) == this.cantidadFilasTablero )
            return false;
        else if( ( pos == left ) && ( this.casillaVacia.getCoordenadaY() - 1) < 0 )
            return false;
        else if( ( pos == right ) &&  ( this.casillaVacia.getCoordenadaY() + 1 ) == this.cantidadFilasTablero )
            return false;
        
        return true;
    }

    /**
     * @function Intercambia las casillas al inicio del juego
     * @note Falta completar
     */
    public void moverCasilla( int pos){
        // ---------------------------------------------------------------------

        int up = 0;
        int down = 1;
        int left = 2;
        int right = 3;
        int nuevoValor;
        int newX = this.casillaVacia.getCoordenadaX();
        int newY = this.casillaVacia.getCoordenadaY();


        // ---------------------------------------------------------------------
        
        if( pos == up ){
            nuevoValor = ( ( Integer )this.tablero[newX - 1][newY].obtenerValor() ).intValue();
            this.tablero[newX][newY].insertarValor(nuevoValor);
            this.tablero[newX][newY].marcarComoCasillaNoVacia();
            
            // mover casilla vacia
            this.tablero[newX - 1][newY].marcarComoCasillaVacia();
            this.casillaVacia.setCoordenadaX(newX - 1);


        }
        else if( pos == down ){
            nuevoValor = ( ( Integer )this.tablero[newX + 1][newY].obtenerValor() ).intValue();
            this.tablero[newX][newY].insertarValor(nuevoValor);
            this.tablero[newX][newY].marcarComoCasillaNoVacia();

            // mover casilla vacia
            this.tablero[newX + 1][newY].marcarComoCasillaVacia();
            this.casillaVacia.setCoordenadaX(newX + 1);
        }
        else if( pos == left ){
            nuevoValor = ( ( Integer )this.tablero[newX][newY - 1].obtenerValor() ).intValue();
            this.tablero[newX][newY].insertarValor(nuevoValor);
            this.tablero[newX][newY].marcarComoCasillaNoVacia();

            // mover casilla vacia
            this.tablero[newX][newY - 1].marcarComoCasillaVacia();
            this.casillaVacia.setCoordenadaY(newY - 1);
        }
        else if( pos == right ){
            nuevoValor = ( ( Integer )this.tablero[newX][newY + 1].obtenerValor() ).intValue();
            this.tablero[newX][newY].insertarValor(nuevoValor);
            this.tablero[newX][newY].marcarComoCasillaNoVacia();

            // mover casilla vacia
            this.tablero[newX][newY + 1].marcarComoCasillaVacia();
            this.casillaVacia.setCoordenadaY(newY + 1);
        }
    }

    // Funcion de prueba
    public void imprimirTablero(){

        int i, j;

        for( i = 0; i < this.cantidadFilasTablero; i++){
            for( j = 0; j < this.cantidadFilasTablero; j++){

                System.out.print( "\t" + ( (Integer)this.tablero[i][j].obtenerValor()).intValue() );
            }
            System.out.println();
        }
    }
}
