/*
 * creado el 19-08-2010
 */

package npuzzle;


import busqueda.*;

/**
 *
 * @author gusamasan
 */
public class NPuzzle implements Problema{

    private int cantidadFilasTablero;

    private CasillaPuzzle   casillaVacia,
                            tablero[][];



    public NPuzzle(){
        
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

                    if( this.tablero[ fila ][ columna ].obtenerValor() == null ){ // es la casilla vacIa
                        this.casillaVacia = this.tablero[ fila ][ columna ];
                        break;
                    }
                }

            }

        this.expandir( this.casillaVacia, unaEstrategia );
        
    }

    public void expandir( Nodo nodoActual, Estrategia unaEstrategia ){
    // ------------------------------------------------------------------------

        CasillaPuzzle nodoHijo;
        
    // ------------------------------------------------------------------------

      
        // expandir el nodo actual: para cada hijo, ejecutar la LINEA siguiente
        //unaEstrategia.insertarNodo( nodoHijo );
    }


    private void moverCasillaVacia( CasillaPuzzle nodoActual ){
    // ----------------------------------------------------------------------



    // ----------------------------------------------------------------------


        this.casillaVacia.insertarValor( nodoActual.obtenerValor() );
        this.casillaVacia   = nodoActual;
        this.casillaVacia.insertarValor( null );
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
    
        this.moverCasillaVacia(( CasillaPuzzle ) nodoActual );

        return( elTableroEstaOrdenado() );

        
    }
}
