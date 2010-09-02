/*
 * creado el 19-08-2010
 */

package npuzzle;


import busqueda.*;
import npuzzleGUI.NpuzzleGUI;
import java.util.*;

/**
 *
 * @author gusamasan
 */
public class NPuzzle implements Problema{
// -----------------------------------------------------------------------------

    public static final int VALOR_VACIO = 9999;

    private int     cantidadFilasTablero    ,
                    menorCantidadVisitados;

    private HashMap mapa;
    
    private CasillaPuzzle   casillaVacia,
                            tablero[][];

    private Estrategia unaEstrategia;

// -----------------------------------------------------------------------------

    /**
     * Constructor
     * @param pN Dimension del tablero
     */
    public NPuzzle( int pN ){
        this.mapa   = new HashMap();
        this.cantidadFilasTablero = pN;
        this.tablero= new CasillaPuzzle[ pN ][ pN ];
        this.menorCantidadVisitados = 30;
    }


    public int getCantidadFilasTablero(){
        return( this.cantidadFilasTablero );
    }

    public void setCantidadFilasTablero( int pCantidadFilasTablero ){
        this.cantidadFilasTablero   = pCantidadFilasTablero;
    }

    public Estrategia getUnaEstrategia() {
        return unaEstrategia;
    }

    public void setUnaEstrategia(Estrategia unaEstrategia) {
        this.unaEstrategia = unaEstrategia;
    }

    public CasillaPuzzle[][] getTablero(){
        return( this.tablero );
    }

    public void setTablero( CasillaPuzzle[][] pTablero ){
        this.tablero    = pTablero;
    }

    private void retroceder( Estrategia unaEstrategia ){
    // -----------------------------------------------------------------------------

        int xPadre, yPadre;
        
        CasillaPuzzle nodoActual, nodoPadre;

    // -----------------------------------------------------------------------------

        nodoActual  = ( CasillaPuzzle )unaEstrategia.obtenerSiguienteNodo();
        nodoPadre   = nodoActual.getNodoPadre();

        this.casillaVacia.insertarValor( nodoActual.obtenerValor() );
        this.casillaVacia.marcarComoCasillaNoVacia();

        xPadre       = nodoPadre.getCoordenadaX();
        yPadre       = nodoPadre.getCoordenadaY();
        this.casillaVacia   = this.tablero[ xPadre ][ yPadre ];
        
        this.casillaVacia.marcarComoCasillaVacia();


        while( nodoActual != null ){            
            unaEstrategia.removerSiguienteNodo();            
            this.marcarNodoComoNoVisitado( nodoActual );

            nodoActual  = ( CasillaPuzzle )unaEstrategia.obtenerSiguienteNodo();            

            if( nodoActual != null  ){
                nodoPadre   = nodoActual.getNodoPadre();
                
                xPadre       = nodoPadre.getCoordenadaX();
                yPadre       = nodoPadre.getCoordenadaY();

                if( !(this.casillaVacia.getCoordenadaX() == xPadre &&
                    this.casillaVacia.getCoordenadaY() == yPadre ) ){                    

                    this.casillaVacia.insertarValor( nodoActual.obtenerValor() );
                    this.casillaVacia.marcarComoCasillaNoVacia();

                    this.casillaVacia   = this.tablero[ xPadre ][ yPadre ];
                    this.casillaVacia.marcarComoCasillaVacia();
                }
                else
                    break;
            }
            else{
                //unaEstrategia.removerSiguienteNodo();
                break;
            }
        }


    }

    public void inicializar( Estrategia unaEstrategia ){
    // -------------------------------------------------------------------------

        int fila, columna, xPadre, yPadre;
        CasillaPuzzle nodoActual, nodoPadre;

    // -------------------------------------------------------------------------

            for( fila= 0; fila < this.cantidadFilasTablero; fila++ ){

                for( columna=0; columna < this.cantidadFilasTablero; columna++ ){

                    if( this.tablero[ fila ][ columna ].esCasillaVacia() ){ // es la casilla vacIa
                        this.casillaVacia = this.tablero[ fila ][ columna ];
                        break;
                    }
                }

            }

        if( unaEstrategia.encontroAlMenosUnaSolucion() ){            
            xPadre  = -1;
            yPadre  = -1;

            nodoActual   = ( CasillaPuzzle )unaEstrategia.obtenerSiguienteNodo();

            nodoPadre           = nodoActual.getNodoPadre();

            while( nodoPadre.getCoordenadaX() != xPadre || nodoPadre.getCoordenadaY() != yPadre  ){

                tablero[ nodoActual.getCoordenadaX() ][ nodoActual.getCoordenadaY() ].insertarValor( nodoActual.obtenerValor() );
                tablero[ nodoActual.getCoordenadaX() ][ nodoActual.getCoordenadaY() ].marcarComoCasillaNoVacia();

                xPadre          = nodoPadre.getCoordenadaX();
                yPadre          = nodoPadre.getCoordenadaY();

                tablero[ xPadre ][ yPadre ].marcarComoCasillaVacia();

                this.casillaVacia   = tablero[ xPadre ][ yPadre ];

                this.unaEstrategia.removerSiguienteNodo();

                this.marcarNodoComoNoVisitado( nodoActual );

                nodoActual   = ( CasillaPuzzle )unaEstrategia.obtenerSiguienteNodo();
                if(nodoActual!=null){
                    nodoPadre           = nodoActual.getNodoPadre();
                }
                
            }
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

        boolean pudoExpandirse, posibleCiclo;

        CasillaPuzzle nodoHijo, nodoCopia;

        CasillaPuzzle casillaActual;
        
    // ------------------------------------------------------------------------

        casillaActual   = ( CasillaPuzzle )nodoActual;


        pudoExpandirse  = false;
        posibleCiclo    = false;

        if( casillaActual.getNodoPadre() != null ){
            xPadre  = casillaActual.getNodoPadre().getCoordenadaX();
            yPadre  = casillaActual.getNodoPadre().getCoordenadaY();
        }
        else{
            xPadre  = -1;
            yPadre  = -1;
        }

        
        
        if( this.unaEstrategia.getCantidadNodosVisitados() < this.menorCantidadVisitados ){
            
            // Expandir el hijo de ARRIBA
            xHijo   =  casillaActual.getCoordenadaX() - 1;
            yHijo   =   casillaActual.getCoordenadaY();
            
            if( xHijo >= 0 ){

                nodoHijo    = this.tablero[ xHijo ][ casillaActual.getCoordenadaY() ];

               //if( !nodoHijo.yaFueVisitado() && nodoHijo != casillaActual.getNodoPadre() ){
                if( !elNodoYaFueVisitado( nodoHijo ) && !( xHijo == xPadre && yHijo == yPadre )  )
                {
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

                if( !elNodoYaFueVisitado( nodoHijo ) && !( xHijo == xPadre && yHijo == yPadre )  )
                {
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

                if( !elNodoYaFueVisitado( nodoHijo ) && !( xHijo == xPadre && yHijo == yPadre )  )
                {
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

                if( !elNodoYaFueVisitado( nodoHijo ) && !( xHijo == xPadre && yHijo == yPadre )  )
                {
                    nodoCopia   = new CasillaPuzzle();
                    nodoCopia.setCoordenadaX( nodoHijo.getCoordenadaX() );
                    nodoCopia.setCoordenadaY( nodoHijo.getCoordenadaY() );
                    nodoCopia.insertarValor( nodoHijo.obtenerValor() );
                    nodoCopia.setNodoPadre( casillaActual );

                    unaEstrategia.insertarNodo( nodoCopia );
                    pudoExpandirse  = true;
                }
            }
        }
        //else
        //    casillaActual.marcaComoNoExpandido();
        
        
        if( !posibleCiclo && pudoExpandirse ){
           casillaActual.marcarComoExpandido();
        }
        else // ya no hay caminos y como no es meta se debe probar otra opción
            this.retroceder(unaEstrategia);// this.prepararOtraOpcion( casillaActual, unaEstrategia );
    }


    private void moverCasillaVacia( CasillaPuzzle nodoActual ){
    // ----------------------------------------------------------------------

        CasillaPuzzle casilla;

    // ----------------------------------------------------------------------

        casilla     = this.tablero[ nodoActual.getCoordenadaX() ][ nodoActual.getCoordenadaY() ];

        this.casillaVacia.insertarValor( casilla.obtenerValor() );
        this.casillaVacia.marcarComoCasillaNoVacia();

       
        nodoActual.marcarComoVisitado();
       
        this.marcarNodoComoVisitado( casilla );
        this.casillaVacia   = casilla;
        
        this.casillaVacia.marcarComoCasillaVacia();

        nodoActual.setCoordenadaX( this.casillaVacia.getCoordenadaX() );
        nodoActual.setCoordenadaY( this.casillaVacia.getCoordenadaY() );
        
    }


    private boolean elTableroEstaOrdenado(){
    // -------------------------------------------------------------------------

        int fila, columna, menor;
        int valorActual;

    // -------------------------------------------------------------------------

        if( this.casillaVacia.getCoordenadaX() == (this.cantidadFilasTablero -1 ) &&
                this.casillaVacia.getCoordenadaY() == (this.cantidadFilasTablero -1 ) ){
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
        else
            return( false );
        
    }

    
    public boolean esNodoMeta( Nodo nodoActual ){
    // ------------------------------------------------------------------------

        int             xPadre, yPadre;
        Object         valor;
        boolean         estaOrdenado, repetir;
        CasillaPuzzle   padre, casillaAuxiliar, casillaActual;

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

                this.menorCantidadVisitados = this.unaEstrategia.getCantidadNodosVisitados();
                
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
        firma+= this.casillaVacia.getCoordenadaX();
        firma+= this.casillaVacia.getCoordenadaY();
        this.unaEstrategia.aumentarUnaUnidadCantidadNodosVisitados();
                
        this.mapa.put( firma, firma );
    }

    public void marcarNodoComoNoVisitado( CasillaPuzzle casilla ){
    // -------------------------------------------------------------------------

        String firma;

    // -------------------------------------------------------------------------

        firma   = "" + ( ( Integer )casilla.obtenerValor() ).intValue();
        firma+= casilla.getCoordenadaX();
        firma+= casilla.getCoordenadaY();
        firma+= this.casillaVacia.getCoordenadaX();
        firma+= this.casillaVacia.getCoordenadaY();
        this.unaEstrategia.disminuirUnaUnidadCantidadNodosVisitados();
        
        this.mapa.remove( firma );
    }

    public boolean elNodoYaFueVisitado( CasillaPuzzle casilla ){
    // -------------------------------------------------------------------------

        String firma;

    // -------------------------------------------------------------------------

        firma   = "" + ( ( Integer )casilla.obtenerValor() ).intValue();
        firma+= casilla.getCoordenadaX();
        firma+= casilla.getCoordenadaY();
        firma+= this.casillaVacia.getCoordenadaX();
        firma+= this.casillaVacia.getCoordenadaY();        
        
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

                    this.casillaVacia       = this.tablero[ fila ][ columna ];
                    this.casillaVacia.setCoordenadaX(fila);
                    this.casillaVacia.setCoordenadaY(columna);
                    /*
                    this.casillaVacia = new CasillaPuzzle();
                    this.casillaVacia.setCoordenadaX(fila);
                    this.casillaVacia.setCoordenadaY(columna);
                    this.casillaVacia.insertarValor( new Integer( this.VALOR_VACIO ) );
                     *
                     */
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
    }

    
    /**
     * @function Funcion que genera el tablero
     */
    public void generarTablero(int[][] board){
    // -------------------------------------------------------------------------

        int fila;
        int columna;
        int valor = 1;
        
    // -------------------------------------------------------------------------

        for( fila= 0; fila < board.length; fila++ ){

            for( columna=0; columna < board.length; columna++ ){
                
                // Asigna la casilla vacia
                if( board[fila][columna]==(board.length*board.length)-1 ){
                    this.tablero[fila][columna] = new CasillaPuzzle();
                    
                    this.tablero[fila][columna].marcarComoCasillaVacia();

                    this.casillaVacia       = this.tablero[ fila ][ columna ];
                    this.casillaVacia.setCoordenadaX(fila);
                    this.casillaVacia.setCoordenadaY(columna);
                    /*
                    this.casillaVacia = new CasillaPuzzle();
                    this.casillaVacia.setCoordenadaX(fila);
                    this.casillaVacia.setCoordenadaY(columna);
                    this.casillaVacia.insertarValor( new Integer( this.VALOR_VACIO ) );
                     *
                     */
                }
                else{
                    this.tablero[fila][columna] = new CasillaPuzzle();
                    this.tablero[fila][columna].setCoordenadaX(fila);
                    this.tablero[fila][columna].setCoordenadaY(columna);
                    this.tablero[fila][columna].insertarValor(new Integer(board[fila][columna]+1));
                    valor++;
                }
            }
        }
    }
    
    /**
     * @function Funcion que desordena el tablero
     * @param ventana
     */
    public void desordenarTablero(NpuzzleGUI ventana){
        // ---------------------------------------------------------------------

        Random rnd = new Random();
        int n = 4;
        int i;
        int posicion;
        int anterior = 2;
        int iteraciones = 1700 * this.cantidadFilasTablero*this.cantidadFilasTablero/2;

        // ---------------------------------------------------------------------

        for ( i = 0; i < iteraciones; i++){
            posicion = ( rnd.nextInt( n ) + 1 );

            // Verifica que la posicion sea valida y no se mude a la posicion anterior
            while( !this.movimientoValido( posicion )  || ( posicion == anterior ) ){
                posicion = rnd.nextInt( n );
            }
            
            anterior = this.posicionAnterior( posicion );
            this.moverCasilla( posicion );
            ventana.moveBlank(posicion); // llamar a la funcion del GUI
            //this.imprimirTablero();
        }
        //System.out.println("Posicion 0, 0: " + this.tablero[0][0].getCoordenadaX() + ", " + this.tablero[0][0].getCoordenadaY() );
        //System.out.println("Posicion 0, 1: " + this.tablero[0][1].getCoordenadaX() + ", " + this.tablero[0][1].getCoordenadaY() );
        //System.out.println("Posicion 1, 0: " + this.tablero[1][0].getCoordenadaX() + ", " + this.tablero[1][0].getCoordenadaY() );
        //System.out.println("Posicion 1, 1: " + this.tablero[1][1].getCoordenadaX() + ", " + this.tablero[1][1].getCoordenadaY() );
        //System.out.println("Casilla vacia: " + this.casillaVacia.getCoordenadaX() + ", " + this.casillaVacia.getCoordenadaY() );
    }

    public void desordenarTablero(){
        // ---------------------------------------------------------------------

        Random rnd = new Random();
        int n = 4;
        int i;
        int posicion;
        int anterior = 2;
        int iteraciones = 1500;

        // ---------------------------------------------------------------------

        for ( i = 0; i < iteraciones; i++){
            posicion = ( rnd.nextInt( n ) + 1 );

            // Verifica que la posicion sea valida y no se mude a la posicion anterior
            while( !this.movimientoValido( posicion )  || ( posicion == anterior ) ){
                posicion = rnd.nextInt( n );
            }

            anterior = this.posicionAnterior( posicion );
            this.moverCasilla( posicion );            
        }
    }

    // Calcula la posicion anterior de donde vino la casilla vacia
    private int posicionAnterior( int pos ){
        // ---------------------------------------------------------------------

        int left = 1;
        int down = 2;
        int right = 3;
        int up = 4;
        int anterior = 0;

        // ---------------------------------------------------------------------

        if( pos == up )
            anterior = down;
        else if( pos == down )
            anterior = up;
        else if( pos == left )
            anterior = right;
        else if( pos == right )
            anterior = left;

        return anterior;
    }

    /**
     * @function Verifica si el movimiento es valido
     * @param pos
     * @return boolean
     */
    private boolean movimientoValido( int pos ){
        // ---------------------------------------------------------------------

        int left = 1;
        int down = 2;
        int right = 3;
        int up = 4;

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
     * @param pos
     */
    public void moverCasilla( int pos){
        // ---------------------------------------------------------------------

        int up = 4;
        int down = 2;
        int left = 1;
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

    /*
     * Conversor del tablero de Int a String
     */
    public String [][] conversor(){
        String[][] board = new String[this.cantidadFilasTablero][this.cantidadFilasTablero];
        int i, j;

        for( i = 0; i < this.cantidadFilasTablero; i++){
            for( j = 0; j < this.cantidadFilasTablero; j++){

                // verifica si es la casilla vacia
                if( this.tablero[i][j].esCasillaVacia() )
                    board[i][j] = "0";
                else
                    board[i][j] = ( (Integer)this.tablero[i][j].obtenerValor()).toString();

                //System.out.print( "\t" + board[i][j] );
            }
            //System.out.println();
        }

        return board;
    }
}
