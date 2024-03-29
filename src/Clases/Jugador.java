package Clases;

import java.io.Serializable;
import java.util.*;
enum INFONPCS{SINENSENYARME, ENSENYADA}
public class Jugador implements Serializable{ //Al crear un nuevo jugador el jugador tiene que tener todos estos datos.
	
	private Random r = new Random();
	
	private static int contador = 1;
	private String nick;
	private String pass;
	private String personajeElegido;
	protected int codigo;
	protected ArrayList<Asesinato> cartas = new ArrayList<>(); //Cartas que le tocan
	protected Personaje personaje = null; //El personaje elegido
	protected HashMap<Implicados, Asesinato> acusacion = new HashMap<>(); //Para al acusar que salga un HashMap con las tres acusaciones
	protected boolean acusacionFinal = false; // Para cuando quieras hacer tu acusación final
	protected HashMap<Asesinato, ArrayList<Boolean>> lista = new HashMap<>(); //Lista que se rellena con la información
	protected int[] posicion = new int[2];
	protected boolean npc;
	protected ArrayList<String>anotaciones = new ArrayList<>();
	protected int anteriorPuerta = -1;
	protected HashMap<Asesinato,HashMap<INFONPCS, Object>>infoParaNpcs = new HashMap<>();
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
	
	public HashMap<Asesinato, ArrayList<Boolean>> getLista() {
		return lista;
	}


	public void setLista(HashMap<Asesinato, ArrayList<Boolean>> lista) {
		this.lista = lista;
	}
	

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPersonajeElegido() {
		return personajeElegido;
	}

	public void setPersonajeElegido(String personajeElegido) {
		this.personajeElegido = personajeElegido;
	}
	
	public Jugador(String nick, String pass) {
		this.nick = nick;
		this.pass = pass;
	}

	public Jugador(Personaje personaje, boolean npc) {
		this.codigo = contador;
		contador ++;
		this.personaje = personaje;
		this.cartas = new ArrayList<Asesinato>();
		this.acusacionFinal = false;
		this.acusacion = new HashMap<Implicados, Asesinato>();
		this.lista = Gestion.creacionLista();
		this.anotaciones = Gestion.creacionAnotaciones();
		this.infoParaNpcs=Gestion.creacionInfoParaNpcs();
		this.npc = npc;
		anteriorPuerta = -1;
	}

	
	public Jugador(ArrayList<Asesinato> cartas, Personaje personaje, HashMap<Implicados, Asesinato> acusacion,
			boolean acusacionFinal,int[] posicion, HashMap<Asesinato, ArrayList<Boolean>> listaVacia, boolean npc, int anteriorPuerta, HashMap<Asesinato,HashMap<INFONPCS, Object>>infoParaNpcs) {
		super();
		this.codigo = contador;
		contador ++;
		this.cartas = cartas;
		this.personaje = personaje;
		this.posicion = posicion;
		this.acusacion = acusacion;
		this.acusacionFinal = acusacionFinal;
		this.lista = listaVacia;
		this.npc = npc;
		this.anteriorPuerta=anteriorPuerta;
		this.infoParaNpcs=infoParaNpcs;
	}
	@Override
	public String toString() {
		return "Jugador [r=" + r + ", codigo=" + codigo + ", cartas=" + cartas + ", personaje=" + personaje
				+ ", acusacion=" + acusacion + ", acusacionFinal=" + acusacionFinal + ", lista=" + lista + ", posicion="
				+ Arrays.toString(posicion) + ", npc=" + npc + "]";
	}

}