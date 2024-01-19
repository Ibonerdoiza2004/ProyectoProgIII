package Clases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Npc {
	Jugador j1;
	Jugador j2;
	Jugador j3;
	Jugador j4;
	
	@Before
	public void setUp() throws Exception {
		Gestion.jugadores = new ArrayList<>();
		j1 = new Jugador(new Personaje(), true);
		Gestion.jugadores.add(j1);
		j2 = new Jugador(new Personaje(), true);
		Gestion.jugadores.add(j2);
		j3 = new Jugador(new Personaje(), true);
		Gestion.jugadores.add(j3);
		j4 = new Jugador(new Personaje(), true);
		Gestion.jugadores.add(j4);
		Gestion.repartirCartas(Gestion.datosPartida.todasLasCartas);
		Gestion.cartasEnsenyadas=new HashMap<>();
		int i = 0;
		Asesinato cartaJ2 = j2.cartas.get(i);
		while(!(cartaJ2 instanceof Sospechoso)) {
			i++;
			cartaJ2 = j2.cartas.get(i);
		}
		Gestion.cartasEnsenyadas.put(cartaJ2, j2);

		Gestion.acusacion=new ArrayList<>();
		Gestion.acusacion.add(cartaJ2);
		Gestion.acusacion.add(Gestion.datosPartida.armas.get(0));
		Gestion.acusacion.add(Gestion.datosPartida.lugares.get(0));
		j1.infoParaNpcs=Gestion.creacionInfoParaNpcs();
		for(Asesinato carta:j1.infoParaNpcs.keySet()) {
			if(!Gestion.datosPartida.implicados.containsValue(carta)&&!carta.equals(cartaJ2)&&!(carta instanceof Lugar)) {
				j1.infoParaNpcs.get(carta).replace(INFONPCS.ENSENYADA, true);
			}
		}
		Gestion.logicaMarcarLista(j1);
	}

	@After
	public void tearDown() throws Exception {
	}

//	Test para marcarLista(CUANDO SOLO QUEDA UNA CARTA SIN ENSEÑAR DE ALGÚN TIPO, ESTÁ IMPLICADO)
	@Test
	public void testLogicaMarcarLista() {
		
		assertEquals((int)j1.infoParaNpcs.get(Gestion.datosPartida.implicados.get(Implicados.PERSONA)).get(INFONPCS.SINENSENYARME), Integer.MAX_VALUE);
	}
//	Test para acusar(CUANDO SOLO QUEDA UNA CARTA SIN ENSEÑAR DE ALGÚN TIPO, PREGUNTA POR CARTAS QUE TIENE DE ESE TIPO)
	@Test
	public void testLogicaAcusar() {
		Gestion.acusacion = new ArrayList<>();
		Gestion.logicaAcusar(j1);
		assertFalse(Gestion.acusacion.contains(Gestion.datosPartida.implicados.get(Implicados.PERSONA)));
	}

}
