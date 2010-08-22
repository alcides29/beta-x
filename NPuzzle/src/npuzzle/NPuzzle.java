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

    public static final int VALOR_VACIO = 1234567890;

    private int cantidadFilasTablero;

    private HashMap mapa;
    
    private CasillaPuzzle   casillaVacia,
                            tablero[][];



    public NPuzzle(){
        this.mapa   = new HashMap();
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

        this.casillaVacia   = new CasillaPuzzle();

    
            for( fila= 0; fila < this.cantidadFilasTablero; fila++ ){

                for( columna=0; columna < this.cantidadFilasTablero; columna++ ){

                    if( this.tablero[ fila ][ columna ].esCasillaVacia() ){ // es la casilla vacIa
                        this.casillaVacia = this.tablero[ fila ][ columna ];
                        break;
                    }
                }

            }

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

        /*
        if( ( (CasillaPuzzle )nodoActual ).yaFueVisitado() )
            return( false );
        else{
         *
         */
            this.moverCasillaVacia(( CasillaPuzzle ) nodoActual );

            return( elTableroEstaOrdenado() );
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
}
