/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package busqueda;

/**
 *
 * @author Administrador
 */
public class Heuristica {
    //Recibe como parametro una matriz de string y retorna un valor entero
    //La matriz debe tener valores numericos consecutivos.
    //Se considera el tablero resuelto cuando se tiene
    //valores desde la primera fila y primera columna
    //empezando desde el 1, y aumentando acia la derecha
    //hasta el final de la fila, continuando la secuencia luego
    //en laprimera columna de la siguiente fila;
    //la ultima casilla deberá tener el valor 0 (cero)
    //representando esta la casilla vacía
    
    public int hPiezaFueraDeLugar( String[][] board )
    {
        Integer nro = new Integer(1);
        int valor = 0;

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board.length; j++)
            {
                if ( i == board.length - 1 && j == board.length - 1)
                {
                     //no se cuenta la casilla vacia
                }
                else
                {
                    if( !board[i][j].equals( nro.toString() ) )
                    {
                        valor++;
                    }
                }
                nro++;
            }
        }
        return valor;
    }
    
    public int hDistanciaManhattan( String[][] board )
    {
        int aux;
        int valor = 0;

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board.length; j++)
            {
                if( board[i][j].equals("0") )
                {

                }
                else
                {
                    aux = Integer.parseInt( board[i][j] ) -1;
                    valor = valor + Math.abs( aux / board.length - i ) + Math.abs( aux % board.length - j );
                }
            }
        }
        return valor;
    }
}
