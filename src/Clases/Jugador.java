package Clases;

import java.util.*;
enum OpcionesLista{SI, NO, REGULAR}

public class Jugador { //Al crear un nuevo jugador el jugador tiene que tener todos estos datos.
	
	

	private Random r = new Random();
	
	private static int contador = 1;
	protected int codigo;
	protected ArrayList<Asesinato> cartas = new ArrayList<>(); //Cartas que le tocan
	protected Personaje personaje = null; //El personaje elegido
	protected HashMap<Implicados, Asesinato> acusacion = new HashMap<>(); //Para al acusar que salga un HashMap con las tres acusaciones
	protected boolean acusacionFinal = false; // Para cuando quieras hacer tu acusación final
	protected HashMap<Asesinato, OpcionesLista> lista = new HashMap<>(); //Lista que se rellena con la información
	protected int[] posicion = new int[2];
	protected boolean npc;
	
	public static int getContador() {
		return contador;
	}

	public int getCodigo() {
		return codigo;
	}

	public ArrayList<Asesinato> getCartas() {
		return cartas;
	}

	public void setCartas(ArrayList<Asesinato> cartas) {
		this.cartas = cartas;
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}

	public HashMap<Implicados, Asesinato> getAcusacion() {
		return acusacion;
	}

	public void setAcusacion(HashMap<Implicados, Asesinato> acusacion) {
		this.acusacion = acusacion;
	}

	public boolean isAcusacionFinal() {
		return acusacionFinal;
	}

	public void setAcusacionFinal(boolean acusacionFinal) {
		this.acusacionFinal = acusacionFinal;
	}
	
	public HashMap<Asesinato, OpcionesLista> getLista() {
		return lista;
	}


	public void setLista(HashMap<Asesinato, OpcionesLista> lista) {
		this.lista = lista;
	}

	public Jugador(Personaje personaje) {
		this.codigo = contador;
		contador ++;
		this.personaje = personaje;
		this.cartas = new ArrayList<Asesinato>();
		this.acusacionFinal = false;
		this.acusacion = new HashMap<Implicados, Asesinato>();
		this.lista = new HashMap<Asesinato, OpcionesLista>();
	}

	
	public Jugador(ArrayList<Asesinato> cartas, Personaje personaje, HashMap<Implicados, Asesinato> acusacion,
			boolean acusacionFinal, HashMap<Asesinato, OpcionesLista> lista) {
		super();
		this.codigo = contador;
		contador ++;
		this.cartas = cartas;
		this.personaje = personaje;
		this.acusacion = acusacion;
		this.acusacionFinal = acusacionFinal;
		this.lista = lista;
	}
	@Override
	public String toString() {
		return "Jugador [codigo=" + codigo + ", cartas=" + cartas + ", personaje=" + personaje.getNombre()
				+ ", acusacion=" + acusacion + ", acusacionFinal=" + acusacionFinal + ", lista=" + lista + "]";
	}

}
