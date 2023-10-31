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
		turno = turno;
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
				} else if (((i+1==j)&&(j%columnasMapa!=0)) || ((i-i==j) && (i%columnasMapa!=0))){
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


	public void turnoJugador() {
		
	}
	
	

	public static void main(String[] args) {
		Gestion g =new Gestion();
		g.eleccionJugadores(3);
		g.repartirCartas(g.datosPartida.todasLasCartas);
		System.out.println(g.jugadores);
		
		g.crearMatriz(529, 529);
	}
	
}
