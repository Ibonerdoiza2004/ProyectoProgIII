package Clases;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Partida {
	
	private String idPartida;
	private String fecha;
	private int duracion;
	private String nickGanador;
	private int numJugadores;
	private ArrayList<Jugador> jugadoresPartida;
	
	public String getIdPartida() {
		return idPartida;
	}
	public void setIdPartida(String idPartida) {
		this.idPartida = idPartida;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getNickGanador() {
		return nickGanador;
	}
	public void setNickGanador(String nickGanador) {
		this.nickGanador = nickGanador;
	}
	public int getNumJugador() {
		return numJugadores;
	}
	public void setNumJugador(int numJugador) {
		this.numJugadores = numJugador;
	}
	
	public ArrayList<Jugador> getJugadoresPartida() {
		return jugadoresPartida;
	}
	public void setJugadoresPartida(ArrayList<Jugador> jugadoresPartida) {
		this.jugadoresPartida = jugadoresPartida;
	}
	
	public Partida() {
		jugadoresPartida = new ArrayList<Jugador>();
		YearMonth ym = YearMonth.now();
		DateTimeFormatter formatoAnyoMes = DateTimeFormatter.ofPattern("yyyy-MM");
		fecha = ym.format(formatoAnyoMes);
		numJugadores = 0;
		duracion = 0;
	}
	
	public Partida(int numPartida, int numJugadores) {
		jugadoresPartida = new ArrayList<Jugador>();
		idPartida = "P"+numPartida;
		numJugadores = numJugadores;
		
	}
	@Override
	public String toString() {
		return "Partida [idPartida=" + idPartida + ", fecha=" + fecha + ", duracion=" + duracion + ", nickGanador="
				+ nickGanador + ", numJugador=" + numJugadores + ", jugadoresPartida=" + jugadoresPartida + "]";
	}
}
