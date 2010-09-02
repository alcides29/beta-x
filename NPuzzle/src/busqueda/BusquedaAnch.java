package busqueda;


import java.io.*;
import java.util.*;
import java.util.Random;
import java.text.DecimalFormat;

public class BusquedaAnch{
	private Map <String, Integer> mapa;
	private Map <String[][], String[][]> secuenciaMov;
	private Queue <String[][]> cola;	
	private String [][] estadoFinal;
	public Stack <int[]> pilaSol;
	private String [][] estadoInicial;
	public int nEstadosRecorridos;
	public int longitudLevels;
	public double tRecorrerTotalNodos;
	public double tSolucionOpt;
	private long tIniExpNodos;
	public int contSoluciones = 0;
	
	public BusquedaAnch(String [][] estadoFinal,String [][]estadoInicial){
		this.cola = new LinkedList<String[][]>(); 
		this.mapa = new HashMap<String, Integer>();// para no usar nodos repetidos
		this.estadoFinal = estadoFinal;//estado obejetivo del tablero
		this.secuenciaMov = new HashMap<String[][], String[][]>();//historial de jugadas, asocia el estado actual al estado anterior que lo genera
		this.pilaSol = new Stack<int[]>();
		this.estadoInicial = estadoInicial;
		this.longitudLevels = 0;
	}
	
	public String [][] siguienteMovida(String [][] cad, int i){	
		String [][] car = new String [cad.length][cad.length];
		car = copiar(car, cad);
		
		switch(i)
		{
			case 0: 
				return arriba(car); 		
			case 1: 
				return abajo(car);				
			case 2:
	        	return izquierda(car);
			case 3:
        		return derecha(car);   	
		}
		return null;
	}

	public String [][] derecha(String [][] cadMat){	
		String cero= "0";
		int [] pos = new int[2];
		String [][] stateAnt = new String [cadMat.length][cadMat.length];
		stateAnt = copiar(stateAnt,cadMat);
		pos = getIndexString(cadMat,cero);
		
		if(pos[1] < cadMat.length -1  ){
			String c = ""; 
			c = c + cadMat[pos[0]][pos[1]];	
			cadMat[pos[0]][pos[1]] = cadMat[pos[0]][pos[1]+1];
			cadMat[pos[0]][pos[1]+1] = c;	
			evaluarEstados(cadMat,stateAnt);
			return cadMat;	
		}		
		return null;
	}

	public String [][] izquierda(String [][] cadMat){	
		String cero= "0";
		int [] pos = new int[2];
		String [][] stateAnt = new String [cadMat.length][cadMat.length];
		stateAnt = copiar(stateAnt,cadMat);
		pos = getIndexString(cadMat,cero);
		
		if(pos[1] > 0 ){
			String c = cadMat[pos[0]][pos[1]];	
			cadMat[pos[0]][pos[1]] = cadMat[pos[0]][pos[1]-1];
			cadMat[pos[0]][pos[1]-1] = c;
			evaluarEstados(cadMat,stateAnt);
			return cadMat;
		}	
		return null;
	}

	public String [][] arriba(String [][] cadMat){	
		String cero= "0";
		int [] pos = new int[2];
		pos = getIndexString(cadMat,cero);
		String [][] stateAnt = new String [cadMat.length][cadMat.length];
		stateAnt = copiar(stateAnt,cadMat);
		
		if(pos[0] > 0 ){
			String c = cadMat[pos[0]][pos[1]];	
			cadMat[pos[0]][pos[1]] = cadMat[pos[0]-1][pos[1]];
			cadMat[pos[0]-1][pos[1]] = c;
			evaluarEstados(cadMat,stateAnt);
			return cadMat;
		}	
		return null;
	}

	public String [][] abajo(String [][] cadMat){	
		String cero= "0";
		int [] pos = new int[2];
		pos = getIndexString(cadMat,cero);
		String [][] stateAnt = new String [cadMat.length][cadMat.length];
		stateAnt = copiar(stateAnt,cadMat);
		
		if(pos[0] < cadMat.length-1 ){
			String c = cadMat[pos[0]][pos[1]];	
			cadMat[pos[0]][pos[1]] = cadMat[pos[0]+1][pos[1]];
			cadMat[pos[0]+1][pos[1]] = c;		
			evaluarEstados(cadMat,stateAnt);		
			return cadMat;
		}	
		return null;
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
		long t_iniCola, t_finCola;
		int valor;
		String aux2 = new String(); 
		String aux = new String(); 
		
		for(int k=0; k < estadoAct.length; k++){
			for(int l=0; l< estadoAct.length; l++){
				aux2 =aux2 + estadoAct[k][l] ;
			}
		}			
	
        if(!this.mapa.containsKey(aux2)){	
			if(estadoAnt != null){
				for(int i=0; i< estadoAnt.length; i++){
					for(int j=0; j< estadoAnt.length; j++){
						aux = aux + estadoAnt[i][j];
					}
				}
				valor = this.mapa.get(aux) + 1;
			}else{
				valor =0;					
			}
			
			this.longitudLevels = valor + 2;
		    t_iniCola = System.currentTimeMillis();
			if(!comparar(estadoAnt,this.estadoFinal)){
				this.mapa.put(aux2, valor);
				this.cola.add(estadoAct);
				this.secuenciaMov.put(estadoAct, estadoAnt); 
			}
		}
    }
	
	public void busquedaAmplitud(){ 
   		long tFinExpNodos;
		String movida [][];
		String movidaAnt[][];
		
		add(this.estadoInicial,null);
		
		this.tIniExpNodos = System.currentTimeMillis();
  		while(!this.cola.isEmpty()){  
			movidaAnt = this.cola.remove();
			for(int i=0; i<4; i++)//los 4 posibles movimientos
				movida = siguienteMovida(movidaAnt,i);
		
        	this.nEstadosRecorridos++;
   		}
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
			System.out.println(  " \nRecorriendo la secuencia al desmapear [....]");
			this.contSoluciones++;
			apilarSolucion(estadoAct,estadoAnt);
		}
	}
	
	private void apilarSolucion(String [][] actState, String [][] prvState){
		long tFinSolOpt;
		String [][] movs = prvState;
		System.out.println(  " \nRecorriendo la secuencia al desmapear [....]");
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
		}
		
		while(!this.pilaSol.empty()){
			int [] vec = this.pilaSol.pop();
			System.out.println( " X == " + vec[0] + " Y == " + vec[1]);
		}
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

}
