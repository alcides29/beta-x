/*
 * creado el 19-08-2010
 */

package busqueda;

/**
 *
 * @author gusamasan
 */
public class BusquedaSinInformacion {

    public BusquedaSinInformacion(){
        
    }

    public Solucion buscarSolucion( Problema unProblema, Estrategia unaEstrategia ){
    // ------------------------------------------------------------------------

        Nodo nodoActual;
        
    // ------------------------------------------------------------------------

            unProblema.inicializar( unaEstrategia );
            
            while(true){

                    nodoActual  = unaEstrategia.obtenerSiguienteNodo();
                    
                    if( nodoActual != null ){
                        return( new SolucionFalsa() );
                    }
                    else {

                        if( unProblema.esNodoMeta( nodoActual ) ){
                            return( unaEstrategia.retornarSolucion() );
                        }
                        else{
                            unProblema.expandir( nodoActual, unaEstrategia );
                        }
                    }
            }
            
    }
}
