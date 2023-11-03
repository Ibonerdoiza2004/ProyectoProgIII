package Clases;

import java.util.ArrayList;
import java.util.HashMap;

public class Gestion {

	//Atributos
	private static int numTurno = 0;
	protected int turno;
	protected ArrayList<Jugador> jugadores = new ArrayList<Jugador>(); //Va a tener todos los jugadores de la partida.
	protected int movimiento; //Esto va a ser la suma del resultado de los dados que te han tocado al tirarlos en tu turno.
	protected VentanaDado resultadoDado;
	
	protected Contenedor datosPartida;
	
	public static int getNumTurno() {
		return numTurno;
	}

	public static void setNumTurno(int numTurno) {
		Gestion.numTurno = numTurno;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public int getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(int movimiento) {
		this.movimiento = movimiento;
	}

	public Contenedor getDatosPartida() {
		return datosPartida;
	}

	public void setDatosPartida(Contenedor datosPartida) {
		this.datosPartida = datosPartida;
	}

	public Gestion() {
		super();
		turno = 0;
		this.jugadores = new ArrayList<Jugador>();
		this.movimiento = 0;
		this.datosPartida = new Contenedor();
	}
	
	public void eleccionJugadores(int numJugadores) {
		for (int i = 0; i<numJugadores; i++) {
			HashMap<Asesinato, OpcionesLista> listaVacia = new HashMap<>();
			for(Asesinato asesinato: datosPartida.todasLasCartas) {
				listaVacia.put(asesinato, OpcionesLista.NO);
			}
			Jugador jugador = new Jugador(new ArrayList<Asesinato>(), new Personaje(/*El enum */) , new HashMap<Implicados, Asesinato>(), false, listaVacia); //En la clase personaje hay que crear un constructor que reciba solamente un enum con el nombre del personaje
			jugadores.add(jugador);
		}
	}
	public void creacionJugadoresIA(int numJugadoresIA) {
		for (int i = 0; i<numJugadoresIA; i++) {
			HashMap<Asesinato, OpcionesLista> listaVacia = new HashMap<>();
			for(Asesinato asesinato: datosPartida.todasLasCartas) {
				listaVacia.put(asesinato, OpcionesLista.NO);
			}
			Jugador jugador = new Jugador(new ArrayList<Asesinato>(), new Personaje() , new HashMap<Implicados, Asesinato>(), false, listaVacia); //En la clase personaje hay que crear un constructor que reciba solamente un enum con el nombre del personaje
			jugadores.add(jugador);
		}
	}
	public void repartirCartas(ArrayList<Asesinato> cartas) {
		ArrayList<Asesinato>copiaCartas = new ArrayList<>(cartas);
		int numCartas = cartas.size();
		int j = 0;
		for(int i = 0; i<numCartas; i++) {
			Asesinato carta = cartas.get((int)(Math.random()*copiaCartas.size()));
			if(carta.implicado == false) {
				(jugadores.get(j%jugadores.size())).cartas.add(carta);
				j++;
			}
			copiaCartas.remove(carta);
		}
	}
	
	//Este método lo llamo desde la clase 'VentanaDado' para pasarle los valores
	public void tiradaDados(int resultadoDado1, int resultadoDado2) {
		//VentanaDado ventDado = new VentanaDado();
		//ventDado.tirarDado();
		this.movimiento = resultadoDado1 + resultadoDado2;
	}
	
	public ArrayList<ArrayList<Integer>> crearMatriz(int filasMapa, int columnasMapa) {
		ArrayList<ArrayList<Integer>> matrizMapa = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer>unos= new ArrayList<Integer>();
		int verticesMatriz = filasMapa * columnasMapa;
		for (int i=0; i<verticesMatriz; i++) {
			ArrayList<Integer> fila = new ArrayList<Integer>();
			for (int j=0; j<verticesMatriz; j++) {
				if (i==j){
					fila.add(1);
					unos.add(j);
				} else if (((i+1==j)&&(j%columnasMapa!=0)) || ((i-1==j) && (i%columnasMapa!=0))){
					fila.add(1);
					unos.add(j);
				} else if ((j-columnasMapa==i)||(j+columnasMapa==i)){
					fila.add(1);
					unos.add(j);
				} else {
					fila.add(0);
				}
			
			}
			matrizMapa.add(fila);
		}
		return matrizMapa;
	}
	
	public ArrayList<ArrayList<Integer>> elevarMatriz(int potencia, ArrayList<ArrayList<Integer>>matriz) {
		ArrayList<ArrayList<Integer>>resultado= new ArrayList<>(matriz);
		for (int p = 1; p<potencia;p++) {
			ArrayList<ArrayList<Integer>>temp= new ArrayList<>();
			for(int i = 0; i<matriz.size();i++) {
				ArrayList<Integer>fila= new ArrayList<>();
				for(int j = 0;j<matriz.get(0).size();j++) {
					int pos = 0;
					for(int k = 0;k<matriz.get(0).size();k++) {
						pos = pos + matriz.get(i).get(k)*resultado.get(k).get(j);
					}
					fila.add(pos);
				}
				temp.add(fila);
			}
			resultado = new ArrayList<>(temp);
		}
		return resultado;
	}
	
	
	
	//En este mértodo se crea una matriz de 23x23 para representar el tablero. Añade un 1 en las posiciones que son utilizables, 
	//es decir, que tienen casillas, y añade un 0 en las posiciones que no lo son (habitaciones). 
	//Una vez creado el tablero, cuando el jugador tira los dados, hay una función recursiva que recibe la posición del jugador
	//y los movimientos que tiene y calcula a qué casillas puede llegar
	//VENTAJAS: Es mucho más eficiente que el otro método (Comprobar con 12 movimientos)
	//DESVENTAJAS: Solo calcula los movimientos para la casilla actual (El otro método se puede cargar antes de que empiece 
	//             la partida y reutilizarlo)
	public ArrayList<ArrayList<Integer>>calcularMovimiento(int fila, int columna, int movimiento, ArrayList<ArrayList<Integer>> tablero){
		ArrayList <ArrayList<Integer>> casillasPosibles = new ArrayList<>();
		movimientoCasillasRecursive(fila, columna, movimiento, 0, casillasPosibles, tablero);
		return casillasPosibles;
	}
	
	private void movimientoCasillasRecursive(int fila, int columna, int movimiento, int iteracion, ArrayList <ArrayList<Integer>> casillasPosibles, ArrayList<ArrayList<Integer>> tablero) {
		ArrayList<Integer> pos = new ArrayList<>();
		pos.add(fila);
		pos.add(columna);
		if(!casillasPosibles.contains(pos)) {
			casillasPosibles.add(pos);
			if(iteracion<movimiento) {
				if((fila!=tablero.size()-1)&&(tablero.get(fila+1).get(columna)==1)) {
					movimientoCasillasRecursive(fila+1, columna, movimiento, iteracion+1, casillasPosibles, tablero);
				}
				if((fila!=0)&&(tablero.get(fila-1).get(columna)==1)) {
					movimientoCasillasRecursive(fila-1, columna, movimiento, iteracion+1, casillasPosibles, tablero);
				}
				if((columna!=tablero.get(fila).size()-1)&&(tablero.get(fila).get(columna+1)==1)) {
					movimientoCasillasRecursive(fila, columna+1, movimiento, iteracion+1, casillasPosibles, tablero);
				}
				if((columna!=0)&&(tablero.get(fila).get(columna-1)==1)) {	
					movimientoCasillasRecursive(fila, columna-1, movimiento, iteracion+1, casillasPosibles, tablero);
				}
			}
		}
	}
	public ArrayList<ArrayList<Integer>> crearTablero(int filas, int columnas) {
		ArrayList<ArrayList<Integer>>tablero = new ArrayList<>();
		for (int i=0; i<filas; i++) {
			ArrayList<Integer>fila = new ArrayList<>();
			for (int j=0; j<columnas; j++) {
				if (i==5&&j==5) {
					fila.add(2);
					
				}else if(i==13&&j==7) {
					fila.add(3);
				}
				else if(i==22&&j==6) {
					fila.add(4);
				}else if(i==7&&j==12) {
					fila.add(5);
				}else if(i==18&&j==12) {
					fila.add(6);
				}else if(i==2&&j==18) {
					fila.add(7);
				}else if(i==10&&j==18) {
					fila.add(8);
				}else if(i==16&&j==17) {
					fila.add(9);
				}else if(i==23&&j==17) {
					fila.add(10);
					
				}else if ((i<7&&j<6)  ||  ((i==9&&j<5)||((i>9&&i<16)&&(j<8)))  ||  ((i>18)&&(j<7))  ||
						(i==0&&(j>9&&j<14))||((i>0&&i<8)&&(j>7&&j<16))  ||  ((i>9 && i<17)&&(j>9 && j<15))  ||  ((i>17)&&(j>8 && j<15)) ||
						(i<6&&j>17)  || ((i>7&&i<13)&&j>17)  || ((i>13&&i<19)&&j>17)||((i>14&&i<18)&&j==17)  ||  (i>20&&j>16)){  
					fila.add(0);
				}else {	
					fila.add(1);
				}
			}
			tablero.add(fila);
		}
		
		return tablero;
	}

	
	public void turnoJugador() {
		
	}
	

	public static void main(String[] args) {
		Gestion g =new Gestion();
		g.eleccionJugadores(3);
		g.repartirCartas(g.datosPartida.todasLasCartas);
		
		//MÉTODO 1
		//System.out.println(g.crearMatriz(23, 23));
		//System.out.println(g.elevarMatriz(12,g.crearMatriz(23, 23)));
		//MÉTODO 2
		ArrayList<ArrayList<Integer>> tablero = g.crearTablero(25, 24);
		System.out.println(tablero);
		for(ArrayList<Integer>a:tablero) {
			for(Integer i: a) {
				if(i==1) {
					System.out.print("()");
				}else if(i==0){
					System.out.print("  ");
				}
				else {
					System.out.print(i+" ");
				}
			}
			System.out.println("");
		}
		ArrayList<ArrayList<Integer>> movimientos = g.calcularMovimiento(8, 10, 12, tablero);
		System.out.println(movimientos);
		System.out.println(movimientos.size());
	}
	
}
