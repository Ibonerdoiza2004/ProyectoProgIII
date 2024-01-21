package Clases;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Guardado {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String nombrePartida;
    	nombrePartida = JOptionPane.showInputDialog("Ingresa el nombre para guardar la partida:");
    	while(nombrePartida==null||nombrePartida.isBlank()) {
    		nombrePartida = JOptionPane.showInputDialog("Ingresa el nombre para guardar la partida:");
    	}
    	Gestion.datosPartida= new Contenedor();
    	ArrayList<Jugador>jugadores=new ArrayList<Jugador>();
    	jugadores.add(new Jugador(new Personaje(),false));
    	DatosPartida dp = new DatosPartida(0, jugadores, new ArrayList<Asesinato>(), new HashMap<Asesinato,Jugador>(), VentanaTablero.class, nombrePartida);
    	dp.guardarPartida(dp);
	}

}
