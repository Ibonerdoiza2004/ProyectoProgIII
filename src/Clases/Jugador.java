package Clases;

import java.util.*;
enum OpcionesLista{SI, NO, REGULAR}
public class Jugador {
	private static int codigo;
	protected ArrayList<Asesinato> cartas = new ArrayList<>();
	protected Personaje personaje = null;
	protected HashMap<Implicados, Asesinato>acusacion = new HashMap<>();
	protected boolean acusacionFinal = false;
	protected HashMap<Asesinato, OpcionesLista>lista = new HashMap<>();
	
	public Jugador(ArrayList<Asesinato> cartas, Personaje personaje, HashMap<Implicados, Asesinato> acusacion,
			boolean acusacionFinal, HashMap<Asesinato, OpcionesLista> lista) {
		super();
		this.cartas = cartas;
		this.personaje = personaje;
		this.acusacion = acusacion;
		this.acusacionFinal = acusacionFinal;
		this.lista = lista;
	}
	
}
