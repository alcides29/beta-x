package busqueda;


import java.io.*;
import java.util.*;
import java.util.Random;
import java.text.DecimalFormat;

public class BusquedaAvara{
	private Map <String, Integer> mapa;
	private Map <String[][], String[][]> secuenciaMov;
	private Queue <String[][]> cola;
	private String [][] estadoFinal;
	public Stack <int[]> pilaSol;
	private String [][] estadoInicial;
        private PriorityQueue<NodoCola> pCola;
	public int nEstadosRecorridos;
	public int longitudLevels;
	public long tRecorrerTotalNodos;
	public long tSolucionOpt;
	private long tIniExpNodos;
	public int contSoluciones = 0;
        private Heuristica h;
        private Boolean encontrado;

	public BusquedaAvara(String [][] estadoFinal,String [][]estadoInicial){
		this.cola = new LinkedList<String[][]>();
		this.mapa = new HashMap<String, Integer>();// para no usar nodos repetidos
		this.estadoFinal = estadoFinal;//estado obejetivo del tablero
		this.secuenciaMov = new HashMap<String[][], String[][]>();//historial de jugadas, asocia el estado actual al estado anterior que lo genera
		this.pilaSol = new Stack<int[]>();
		this.estadoInicial = estadoInicial;
		this.longitudLevels = 0;
                this.pCola = new PriorityQueue<NodoCola>();
                this.h = new Heuristica();
                this.encontrado = false;
	}

	public void siguienteMovida(String [][] cad, int i){
		String [][] car = new String [cad.length][cad.length];
		car = copiar(car, cad);

		switch(i)
		{
			case 0:
				arriba(car);
			case 1:
                                abajo(car);
			case 2:
                                izquierda(car);
			case 3:
                                derecha(car);
		}
	}

	public void derecha(String [][] cadMat){
		String cero= "0";
		int [] pos = new int[2];
		String [][] stateAnt = new String [cadMat.length][cadMat.length];
		String [][] stateAct = new String [cadMat.length][cadMat.length];

		stateAnt = copiar(stateAnt,cadMat);
		stateAct = copiar(stateAct,cadMat);
		pos = getIndexString(cadMat,cero);

		if(pos[1] < cadMat.length -1  ){
			String c = "";
			c = c + stateAct[pos[0]][pos[1]];
			stateAct[pos[0]][pos[1]] = stateAct[pos[0]][pos[1]+1];
			stateAct[pos[0]][pos[1]+1] = c;
			evaluarEstados(stateAct,stateAnt);
		}
	}

	public void izquierda(String [][] cadMat){
		String cero= "0";
		int [] pos = new int[2];
		String [][] stateAnt = new String [cadMat.length][cadMat.length];
                String [][] stateAct = new String [cadMat.length][cadMat.length];

		stateAnt = copiar(stateAnt,cadMat);
		stateAct = copiar(stateAct,cadMat);
		pos = getIndexString(cadMat,cero);

		if(pos[1] > 0 ){
			String c = cadMat[pos[0]][pos[1]];
			stateAct[pos[0]][pos[1]] = stateAct[pos[0]][pos[1]-1];
			stateAct[pos[0]][pos[1]-1] = c;
			evaluarEstados(stateAct,stateAnt);
		}
	}

	public void arriba(String [][] cadMat){
		String cero= "0";
		int [] pos = new int[2];
		pos = getIndexString(cadMat,cero);
		String [][] stateAnt = new String [cadMat.length][cadMat.length];
                String [][] stateAct = new String [cadMat.length][cadMat.length];

		stateAnt = copiar(stateAnt,cadMat);
		stateAct = copiar(stateAct,cadMat);
		if(pos[0] > 0 ){
			String c = stateAct[pos[0]][pos[1]];
			stateAct[pos[0]][pos[1]] = stateAct[pos[0]-1][pos[1]];
			stateAct[pos[0]-1][pos[1]] = c;
			evaluarEstados(stateAct,stateAnt);
		}
	}

	public void abajo(String [][] cadMat){
		String cero= "0";
		int [] pos = new int[2];
		pos = getIndexString(cadMat,cero);
		String [][] stateAnt = new String [cadMat.length][cadMat.length];
		String [][] stateAct = new String [cadMat.length][cadMat.length];

		stateAnt = copiar(stateAnt,cadMat);
		stateAct = copiar(stateAct,cadMat);
		if(pos[0] < cadMat.length-1 ){
			String c = stateAct[pos[0]][pos[1]];
			stateAct[pos[0]][pos[1]] = stateAct[pos[0]+1][pos[1]];
			stateAct[pos[0]+1][pos[1]] = c;
			evaluarEstados(stateAct,stateAnt);
		}
	}

	private int [] getIndexString(String [][]  cadMat, String caracter){
		int n = cadMat.length;
		int [] index  = new int [2];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if( cadMat[i][j].compareTo(caracter) == 0){
					index[0] = i;
					index[1] = j;
					return index;
				}
			}
		}
		index[0]=-1;
		index[1]=-1;
		return index;
	}

	private void add(String [][] estadoAct, String [][] estadoAnt){
		int valor;
		String aux2 = new String();
		String aux = new String();
                aux2 = cadena(estadoAct);
               
                if(!this.mapa.containsKey(aux2)){
                    if(estadoAnt != null){
                        aux = cadena(estadoAnt);
			valor = this.mapa.get(aux) + 1;
                    }else{
			valor =0;
                    }

                    System.out.println("valor: "+valor);
                    this.longitudLevels = valor + 2;
                    if(!comparar(estadoAnt,this.estadoFinal)){
                            this.mapa.put(aux2, valor);
                         //   this.cola.add(estadoAct);
                            this.pCola.add(new NodoCola(estadoAct,this.h.hDistanciaManhattan(estadoAct)+valor));
                            this.secuenciaMov.put(estadoAct, estadoAnt);
                    }
		}
        }

        private String cadena(String [][] mat){
            String str = new String();
            for(int k=0; k < mat.length; k++){
			for(int l=0; l< mat.length; l++){
				str =str + mat[k][l] ;
			}
		}
            return str;
        }
        
	public void busquedaAmplitud(){
   		long tFinExpNodos;
		String movidaAnt[][];

		add(this.estadoInicial,null);

		this.tIniExpNodos = System.currentTimeMillis();
                //ojo
                boolean soloMejor;
                if (estadoInicial.length>3){
                    soloMejor = true;
                } else{
                    soloMejor = false;
                }
  		//while( !this.cola.isEmpty()&&(!soloMejor||this.contSoluciones < 1)){// && this.contSoluciones <= 1 ){
                while(this.contSoluciones == 0){
          	   // movidaAnt = this.cola.remove();
                    movidaAnt = this.pCola.remove().board;
                    System.out.println("***************************************************************\n");
                    imprimir(movidaAnt);
                    for(int i=0; i<4; i++)//los 4 posibles movimientos
			siguienteMovida(movidaAnt,i);
                    this.nEstadosRecorridos++;
   		}
 System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n");
		this.nEstadosRecorridos++;
		tFinExpNodos = System.currentTimeMillis();
		this.tRecorrerTotalNodos = tFinExpNodos - this.tIniExpNodos;

		/*System.out.printf("\nTotal de estados recorridos: %d",nEstadosRecorridos);
		System.out.print("\nCantidad de niveles: " + this.longitudLevels + "\n ");
   		System.out.printf("\nTiempo de recorrer todos los nodos : " + valor.format ((float)this.tRecorrerTotalNodos) );
   		System.out.print("\nTiempo de encontrar la solucion optima : " + valor.format ((float)this.tSolucionOpt));*/

	}

	private boolean comparar(String [][] estadoF, String [][] b ){
		if( estadoF == null) return false;
		for(int i=0; i< estadoF.length; i++){
			 for(int j=0; j< estadoF.length; j++){
				if(estadoF[i][j].compareTo(b[i][j]) != 0 )
					return false;
			}
		}
		return true;
	}

	private void evaluarEstados(String [][] estadoAct, String [][] estadoAnt ){
		add(estadoAct, estadoAnt);
        if(comparar(estadoAct,this.estadoFinal)) { //"passar" esta solo para pruebas de ejecucion
			//System.out.println(  " \nRecorriendo la secuencia al desmapear [....]");
			this.contSoluciones++;
                       
                       if(this.contSoluciones == 1){
                            apilarSolucion(estadoAct,estadoAnt);
                         //   this.encontrado = false;
                       }


		}
	}

	private void apilarSolucion(String [][] actState, String [][] prvState){
		long tFinSolOpt;
		String [][] movs = prvState;
		//System.out.println(  " \nRecorriendo la secuencia al desmapear [....]");
		this.pilaSol.add(getIndexString(actState,"0"));
		this.pilaSol.add(getIndexString(movs,"0"));
		Set set= this.secuenciaMov.keySet();
		Iterator iter = set.iterator();

		while(!comparar(movs,this.estadoInicial) ){
			String [][] clave = (String [][]) iter.next();

			if(comparar(clave,movs)){
				movs = (String [][]) this.secuenciaMov.get(clave);
				if(movs!=null){
					this.pilaSol.add(getIndexString(movs,"0"));
				}else{
					break;
				}
			}
			if(!iter.hasNext())
				iter = set.iterator();
		}

		if(this.contSoluciones == 1){
			tFinSolOpt = System.currentTimeMillis();
			this.tSolucionOpt = tFinSolOpt - this.tIniExpNodos;
                    //    this.encontrado = false;
		}

		/*while(!this.pilaSol.empty()){
			int [] vec = this.pilaSol.pop();
			System.out.println( " X == " + vec[0] + " Y == " + vec[1]);
		}*/
	}

	private void imprimir(String[][] tablero){

		for(int k=0; k < tablero.length; k++){
			for(int l=0; l< tablero.length; l++){

				System.out.print( tablero[k][l] + " ");
			}
			System.out.print(  " \n");
		}
	}

	private String [][] copiar(String[][] a,String[][] b){
		for(int k=0; k < a.length; k++){
			for(int l=0; l< a.length; l++){
				a[k][l] = b[k][l];
			}
		}
		return a;
	}
        public String[][] crearTableroProf(int[][] board){
            String[][] tabla = new String[board.length][board.length];
            for(int i=0;i<board.length; i++){
                for(int j=0; j<board.length; j++){
                    if(board[i][j]==board.length*board.length-1){
                        tabla[i][j]= "0";
                    } else{
                        tabla[i][j]=(new Integer(board[i][j]+1)).toString();
                    }
                }
            }
            return tabla;
        }


        private class NodoCola implements Comparable<NodoCola>{
            String [][] board;
            Integer costo;

            public NodoCola(String [][] estado, int costo){
                board = estado;
                this.costo = costo;
            }

            public int compareTo(NodoCola o) {
                return this.costo.compareTo(o.costo);
            }


        }

}
