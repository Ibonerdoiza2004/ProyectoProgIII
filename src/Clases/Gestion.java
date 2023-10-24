package Clases;

import java.util.ArrayList;

public class Gestion {
	
	//Atributos
	private static int numTurno = 1;
	protected int Turno;
	protected ArrayList<Jugador> jugadores = new ArrayList<Jugador>(); //Va a tener todos los jugadores de la partida
	protected int movimiento; //Esto va a ser la suma del resultado de los dados que te han tocado al tirarlos en tu turno.
	
	protected Contenedor datosPartida;
	
	
}
