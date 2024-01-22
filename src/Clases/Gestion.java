package Clases;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;



enum TipoSprite{AndarArriba, AndarIzquierda, AndarAbajo, AndarDerecha, AgarrarArriba, AgarrarDerecha, AgarrarAbajo, AgarrarIzquierda};
public class Gestion {

	//Atributos
	protected static Dimension sizePantalla = Toolkit.getDefaultToolkit().getScreenSize();
	protected static int numTurno = 0;
	protected static ArrayList<Jugador> jugadores = new ArrayList<Jugador>(); //Va a tener todos los jugadores de la partida.
	 //Esto va a ser la suma del resultado de los dados que te han tocado al tirarlos en tu turno.s
	protected static int numFilas = 25;
	protected static int numColumnas = 24;
	protected static Contenedor datosPartida;
	protected static ArrayList<ArrayList<Integer>> tablero = Gestion.crearTablero(numFilas, numColumnas);
	protected static HashMap<NombrePersonaje,HashMap<TipoSprite,ArrayList<Image>>> sprites = crearSprites();
	protected static ArrayList<Asesinato>acusacion = new ArrayList<>();
	protected static JFrame ventanaJuego;
	protected static HashMap<Asesinato, Jugador>cartasEnsenyadas = new HashMap<>();
	protected static VentanaInicio vInicio= null;
	protected static Thread tMusica;
	protected static AtomicBoolean dejarDeSonar = new AtomicBoolean();
	protected static Player player;
	protected static Class siguientePanel;
	protected static JDesktopPane dPane;
	protected static String ventanaTexto;
	protected static int ventanaTextoInt;
	public static int getNumTurno() {
		return numTurno;
	}

	public static void setNumTurno(int numTurno) {
		Gestion.numTurno = numTurno;
	}
	public static void aumentarTurno() {
		Gestion.numTurno = (Gestion.numTurno+1)%jugadores.size();
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		Gestion.jugadores = jugadores;
	}

	

	public Contenedor getDatosPartida() {
		return datosPartida;
	}

	public void setDatosPartida(Contenedor datosPartida) {
		Gestion.datosPartida = datosPartida;
	}
	
	

	
	public static HashMap<Asesinato,ArrayList<Boolean>> creacionLista() {
		HashMap<Asesinato,ArrayList<Boolean>> listaVacia = new HashMap<>();
		for(Asesinato asesinato: datosPartida.todasLasCartas) {
			ArrayList <Boolean>cadaCarta = new ArrayList<>();
			for(int j=0;j<5;j++) {
				cadaCarta.add(false);
			}
			listaVacia.put(asesinato, cadaCarta);
		}
		return listaVacia;
	}
	public static ArrayList<String> creacionAnotaciones() {
		ArrayList<String> anotacionesVacias = new ArrayList<>();
		for(Asesinato asesinato: datosPartida.todasLasCartas) {
			anotacionesVacias.add("");
		}
		return anotacionesVacias;
	}
	public static HashMap<Asesinato,HashMap<INFONPCS, Object>>creacionInfoParaNpcs(){
		HashMap<Asesinato,HashMap<INFONPCS, Object>>info = new HashMap<>();
		for(Asesinato asesinato: datosPartida.todasLasCartas) {
			HashMap<INFONPCS, Object>cadaCarta = new HashMap<>();
			cadaCarta.put(INFONPCS.SINENSENYARME, 0);
			cadaCarta.put(INFONPCS.ENSENYADA, false);
			info.put(asesinato, cadaCarta);
		}
		return info;
	}
	public static void repartirCartas(ArrayList<Asesinato> cartas) {
		ArrayList<Asesinato>copiaCartas = new ArrayList<>(cartas);
		int numCartas = cartas.size();
		int j = 0;
		for(int i = 0; i<numCartas; i++) {
			Asesinato carta = copiaCartas.get((int)(Math.random()*copiaCartas.size()));
			if(carta.implicado == false) {
				(jugadores.get(j%jugadores.size())).cartas.add(carta);
				j++;
			}
			copiaCartas.remove(carta);
		}
	}
	
	public static ArrayList<ArrayList<Integer>> crearTablero(int filas, int columnas) {
		ArrayList<ArrayList<Integer>>tablero = new ArrayList<>();
		for (int i=0; i<filas; i++) {
			ArrayList<Integer>fila = new ArrayList<>();
			for (int j=0; j<columnas; j++) {
				if (i==5&&j==5) {
					fila.add(2);
				}else if(i==13&&j==7) {
					fila.add(3);
				}else if(i==22&&j==6) {
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
				}else if (((i>10 && i<16)&&(j==10 || j==14))||((i==10 || i==16)&&(j>9 && j<15))) {
					fila.add(11);
				}
				else if ((i<7&&j<6)  ||  ((i==9&&j<5)||((i>9&&i<16)&&(j<8)))  ||  ((i>18)&&(j<7))  ||
						(i==0&&(j>9&&j<14))||((i>0&&i<8)&&(j>7&&j<16))  ||  ((i>10 && i<16)&&(j>10 && j<14))  ||  ((i>17)&&(j>8 && j<15)) ||
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
	public static ArrayList<ArrayList<Integer>> caminoMasCorto(int filaInicio, int columnaInicio, int filaFinal, int columnaFinal) {
		ArrayList<Integer> ultimoVertice = new ArrayList<>();
		ultimoVertice.add(filaFinal);
		ultimoVertice.add(columnaFinal);
		HashMap <ArrayList<Integer>,ArrayList<ArrayList<Integer>>> vertices = new HashMap<>();
		HashMap<ArrayList<Integer>,ArrayList<ArrayList<Integer>>> verticesAdyacentes = new HashMap<>();
		ArrayList<Integer> verticeActual = new ArrayList<>();
		verticeActual.add(filaInicio);
		verticeActual.add(columnaInicio);
		ArrayList<ArrayList<Integer>> solucion = new ArrayList<>();
		solucion.add(verticeActual);
		verticesAdyacentes.put(verticeActual, solucion);
		while (true) {
			verticeActual = ((ArrayList<Integer>) verticesAdyacentes.keySet().toArray()[0]);
			for (ArrayList<Integer> i:verticesAdyacentes.keySet()) {
				if (verticesAdyacentes.get(i).size() < verticesAdyacentes.get(verticeActual).size()) {
					verticeActual = i;
				}
			}
			vertices.put(verticeActual, verticesAdyacentes.get(verticeActual));
			verticesAdyacentes.remove(verticeActual);
			
			if((verticeActual.equals(ultimoVertice)||Gestion.tablero.get(verticeActual.get(0)).get(verticeActual.get(1))!=11)) {
				ArrayList<Integer>nuevoVerticeDerecha = new ArrayList<>();
				nuevoVerticeDerecha.add(verticeActual.get(0));
				nuevoVerticeDerecha.add((verticeActual.get(1)+1));
				if(!vertices.containsKey(nuevoVerticeDerecha)) {
					if (verticeActual.get(1)!=Gestion.tablero.get(verticeActual.get(0)).size()-1) {
						if(Gestion.tablero.get(verticeActual.get(0)).get(verticeActual.get(1)+1)!=0) {
							
							solucion = new ArrayList<>(vertices.get(verticeActual));
							solucion.add(nuevoVerticeDerecha);
							if(!verticesAdyacentes.containsKey(nuevoVerticeDerecha)) {
								verticesAdyacentes.put(nuevoVerticeDerecha, solucion);
							}
						}
					}
				}
				ArrayList<Integer>nuevoVerticeIzquierda = new ArrayList<>();
				nuevoVerticeIzquierda.add(verticeActual.get(0));
				nuevoVerticeIzquierda.add(verticeActual.get(1)-1);
				if(!vertices.containsKey(nuevoVerticeIzquierda)) {
					if(verticeActual.get(1)!=0) {
						if(Gestion.tablero.get(verticeActual.get(0)).get(verticeActual.get(1)-1)!=0) {
							solucion = new ArrayList<>(vertices.get(verticeActual));
							solucion.add(nuevoVerticeIzquierda);
							if(!verticesAdyacentes.containsKey(nuevoVerticeIzquierda)) {
								verticesAdyacentes.put(nuevoVerticeIzquierda, solucion);
							}
						}
					}
				}
				ArrayList<Integer>nuevoVerticeArriba = new ArrayList<>();
				nuevoVerticeArriba.add(verticeActual.get(0)-1);
				nuevoVerticeArriba.add(verticeActual.get(1));
				if(!vertices.containsKey(nuevoVerticeArriba)) {
					if(verticeActual.get(0)!=0) {
						if(Gestion.tablero.get(verticeActual.get(0)-1).get(verticeActual.get(1))!=0) {
							solucion = new ArrayList<>(vertices.get(verticeActual));
							solucion.add(nuevoVerticeArriba);
							if(!verticesAdyacentes.containsKey(nuevoVerticeArriba)) {
								verticesAdyacentes.put(nuevoVerticeArriba, solucion);
							}
						}
					}
				}
				ArrayList<Integer>nuevoVerticeAbajo = new ArrayList<>();
				nuevoVerticeAbajo.add(verticeActual.get(0)+1);
				nuevoVerticeAbajo.add(verticeActual.get(1));
				if(!vertices.containsKey(nuevoVerticeAbajo)) {
					if(verticeActual.get(0)!=Gestion.tablero.size()-1) {
						if(Gestion.tablero.get(verticeActual.get(0)+1).get(verticeActual.get(1))!=0) {
							solucion = new ArrayList<>(vertices.get(verticeActual));
							solucion.add(nuevoVerticeAbajo);
							if(!verticesAdyacentes.containsKey(nuevoVerticeAbajo)) {
								verticesAdyacentes.put(nuevoVerticeAbajo, solucion);
							}
						}
					}
				}
			}
			
			
			
			if((verticeActual.equals(ultimoVertice))) {
				break;
			}
		}
		
		return vertices.get(verticeActual);
	}
		
	public static void logicaAcusar(Jugador jug) {
		Gestion.acusacion = new ArrayList<>();
		int minimoArma = Integer.MAX_VALUE;
		int minimoSospechoso = Integer.MAX_VALUE;
		if(Gestion.tablero.get(jug.posicion[0]).get(jug.posicion[1])==11) {
			for(Asesinato carta : jug.infoParaNpcs.keySet()) {
				if((int)jug.infoParaNpcs.get(carta).get(INFONPCS.SINENSENYARME)==Integer.MAX_VALUE) {
					Gestion.acusacion.add(carta);
				}
			}
		}else {
			boolean armaConocida=false;
			boolean sospechosoConocido = false;
			for(Asesinato carta : jug.infoParaNpcs.keySet()) {
				if(!((boolean) jug.infoParaNpcs.get(carta).get(INFONPCS.ENSENYADA))) {
					if((int)jug.infoParaNpcs.get(carta).get(INFONPCS.SINENSENYARME)<minimoArma&&carta instanceof Arma) {
						minimoArma = (int)jug.infoParaNpcs.get(carta).get(INFONPCS.SINENSENYARME);
					}else if((int)jug.infoParaNpcs.get(carta).get(INFONPCS.SINENSENYARME)<minimoSospechoso&&carta instanceof Sospechoso) {
						minimoSospechoso = (int)jug.infoParaNpcs.get(carta).get(INFONPCS.SINENSENYARME);
					}
					if((int)jug.infoParaNpcs.get(carta).get(INFONPCS.SINENSENYARME)==Integer.MAX_VALUE) {
						if(carta instanceof Sospechoso) {
							sospechosoConocido= true;
						}
						if(carta instanceof Arma) {
							armaConocida= true;
						}
					}
				}	
			}
			Sospechoso s = null;
			Arma a = null;
			if(sospechosoConocido) {
				ArrayList<Asesinato>cartasJugador = new ArrayList<>();
				for (Asesinato carta:jug.cartas) {
					if(carta instanceof Sospechoso) {
						cartasJugador.add(carta);
					}
				}
				if(!cartasJugador.isEmpty()) {
					s=(Sospechoso) cartasJugador.get((int)(Math.random()*cartasJugador.size()));
				}else {
					s=Gestion.datosPartida.sospechosos.get((int)(Math.random()*Gestion.datosPartida.sospechosos.size()));
				}
			}else {
				ArrayList<Sospechoso>sospechosoAcusado = new ArrayList<>();
				for(Asesinato carta : jug.infoParaNpcs.keySet()) {
					if(!((boolean) jug.infoParaNpcs.get(carta).get(INFONPCS.ENSENYADA))) {
						if((int)jug.infoParaNpcs.get(carta).get(INFONPCS.SINENSENYARME)==minimoSospechoso&&carta instanceof Sospechoso) {
							sospechosoAcusado.add((Sospechoso) carta);
						}
					}
				}
				s = sospechosoAcusado.get((int)(Math.random()*sospechosoAcusado.size()));
			}
			if(armaConocida) {
				ArrayList<Asesinato>cartasJugador = new ArrayList<>();
				for (Asesinato carta:jug.cartas) {
					if(carta instanceof Arma) {
						cartasJugador.add(carta);
					}
				}
				if(!cartasJugador.isEmpty()) {
					a=(Arma) cartasJugador.get((int)(Math.random()*cartasJugador.size()));
				}else {
					a=Gestion.datosPartida.armas.get((int)(Math.random()*Gestion.datosPartida.armas.size()));
				}
			}else {
				ArrayList<Arma>armaAcusado = new ArrayList<>();
				for(Asesinato carta : jug.infoParaNpcs.keySet()) {
					if(!((boolean) jug.infoParaNpcs.get(carta).get(INFONPCS.ENSENYADA))) {
						if((int)jug.infoParaNpcs.get(carta).get(INFONPCS.SINENSENYARME)==minimoArma&&carta instanceof Arma) {
							armaAcusado.add((Arma) carta);
						}
					}
				}
				a = armaAcusado.get((int)(Math.random()*armaAcusado.size()));
			}
			
			if(!((int)jug.infoParaNpcs.get(s).get(INFONPCS.SINENSENYARME)==Integer.MAX_VALUE)) {
				Gestion.aumentarTurnosSinEnsenyar(s,Gestion.jugadores.get(Gestion.getNumTurno()));
			}
			if(!((int)jug.infoParaNpcs.get(a).get(INFONPCS.SINENSENYARME)==Integer.MAX_VALUE)) {
				Gestion.aumentarTurnosSinEnsenyar(a,Gestion.jugadores.get(Gestion.getNumTurno()));
			}
			Gestion.acusacion.add(s);
			Gestion.acusacion.add(a);
		}
	}
	public static void aumentarTurnosSinEnsenyar(Asesinato carta, Jugador jugador) {
		jugador.infoParaNpcs.get(carta).replace(INFONPCS.SINENSENYARME, (int)jugador.infoParaNpcs.get(carta).get(INFONPCS.SINENSENYARME)+1);
	}
	public static ArrayList<Integer> logicaMover(Jugador jug, int movimientos) {
		int count =0;
		Lugar habitacionConocida = null;
		int minPedidaHabitacionN = Integer.MAX_VALUE;
		ArrayList<Asesinato> minPedidaHabitacion= new ArrayList<>();
		for(Asesinato carta : jug.infoParaNpcs.keySet()) {
			if(carta instanceof Lugar){
				if((int)jug.infoParaNpcs.get(carta).get(INFONPCS.SINENSENYARME)<minPedidaHabitacionN) {
					minPedidaHabitacion.clear();
					minPedidaHabitacionN=(int)jug.infoParaNpcs.get(carta).get(INFONPCS.SINENSENYARME);
					minPedidaHabitacion.add(carta);
				}else if ((int)jug.infoParaNpcs.get(carta).get(INFONPCS.SINENSENYARME)==minPedidaHabitacionN) {
					minPedidaHabitacion.add(carta);
				}	
			}
			if((int)jug.infoParaNpcs.get(carta).get(INFONPCS.SINENSENYARME)==Integer.MAX_VALUE) {
				count++;
				System.out.println("++");
				if(carta instanceof Lugar) {
					habitacionConocida= (Lugar)carta;
				}
			}
		}
		ArrayList<Integer>casillaMasCercanaAPuerta=null;
		if(count==3) {
			System.out.println("Centro");
//			Ir al centro para acusar
			int longCaminoMasCorto = Integer.MAX_VALUE;
			for (int i = 0; i<Gestion.tablero.size();i++){
				for(int j =0; j<Gestion.tablero.get(i).size();j++) {
					if(Gestion.tablero.get(i).get(j)==11) {
						System.out.println("Es casilla centro");
						ArrayList<ArrayList<Integer>>posible= caminoMasCorto(Gestion.jugadores.get(Gestion.getNumTurno()).posicion[0], Gestion.jugadores.get(Gestion.getNumTurno()).posicion[1], i, j);
						if(longCaminoMasCorto>posible.size()) {	
							if(posible.size()<=movimientos) {
								casillaMasCercanaAPuerta = new ArrayList<>(posible.get(posible.size()-1));
							}else {
								casillaMasCercanaAPuerta = new ArrayList<>(posible.get(movimientos));
							}
							longCaminoMasCorto = posible.size();
						}
						
					}
				}
			}
			System.out.println(casillaMasCercanaAPuerta);
		}else {
//			Diferenciar si ya conozco alguno, se que lo conozco porque es integer max value
//			Si no conozco ninguna carta seguir acusando diferentes cartas
//			Si conozco alguno, perguntar alguna desconocida y dos cartas que tenga yo
//			Modificar el metodo de logicaAcusar para que despues de la segunda pregunta, (haga una pregunta random de una carta suba a 3 y) use la formula de arriba
			
			int longCaminoMasCorto = Integer.MAX_VALUE;
			if(habitacionConocida == null) {
				for (int i = 0; i<Gestion.tablero.size();i++){
					for(int j =0; j<Gestion.tablero.get(i).size();j++) {
						if(Gestion.tablero.get(i).get(j)!=0 &&Gestion.tablero.get(i).get(j)!=1 && Gestion.tablero.get(i).get(j)!=11 &&
								 minPedidaHabitacion.contains(Gestion.datosPartida.lugares.get(Gestion.tablero.get(i).get(j)-2))) {
							ArrayList<ArrayList<Integer>>posible= caminoMasCorto(Gestion.jugadores.get(Gestion.getNumTurno()).posicion[0], Gestion.jugadores.get(Gestion.getNumTurno()).posicion[1], i, j);
							if(longCaminoMasCorto>posible.size()) {	
								if(posible.size()<=movimientos) {
									casillaMasCercanaAPuerta = new ArrayList<>(posible.get(posible.size()-1));
								}else {
									casillaMasCercanaAPuerta = new ArrayList<>(posible.get(movimientos));
								}
								longCaminoMasCorto = posible.size();
							}
							
						}
					}
				}
			}else {
				ArrayList<Asesinato>cartasJugador = new ArrayList<>();
				for (Asesinato carta:jug.cartas) {
					if(carta instanceof Lugar) {
						cartasJugador.add(carta);
					}
				}
				if(cartasJugador.isEmpty()) {
					for (int i = 0; i<Gestion.tablero.size();i++){
						for(int j =0; j<Gestion.tablero.get(i).size();j++) {
							if(Gestion.tablero.get(i).get(j)!=0 &&Gestion.tablero.get(i).get(j)!=1 && Gestion.tablero.get(i).get(j)!=11) {
								ArrayList<ArrayList<Integer>>posible= caminoMasCorto(Gestion.jugadores.get(Gestion.getNumTurno()).posicion[0], Gestion.jugadores.get(Gestion.getNumTurno()).posicion[1], i, j);
								if(longCaminoMasCorto>posible.size()) {	
									if(posible.size()<=movimientos) {
										casillaMasCercanaAPuerta = new ArrayList<>(posible.get(posible.size()-1));
									}else {
										casillaMasCercanaAPuerta = new ArrayList<>(posible.get(movimientos));
									}
									longCaminoMasCorto = posible.size();
								}
								
							}
						}
					}
				}else {
					for (int i = 0; i<Gestion.tablero.size();i++){
						for(int j =0; j<Gestion.tablero.get(i).size();j++) {
							if(Gestion.tablero.get(i).get(j)!=0 &&Gestion.tablero.get(i).get(j)!=1 && Gestion.tablero.get(i).get(j)!=11 &&
									cartasJugador.contains(Gestion.datosPartida.lugares.get(Gestion.tablero.get(i).get(j)))) {
								ArrayList<ArrayList<Integer>>posible= caminoMasCorto(Gestion.jugadores.get(Gestion.getNumTurno()).posicion[0], Gestion.jugadores.get(Gestion.getNumTurno()).posicion[1], i, j);
								if(longCaminoMasCorto>posible.size()) {	
									if(posible.size()<=movimientos) {
										casillaMasCercanaAPuerta = new ArrayList<>(posible.get(posible.size()-1));
									}else {
										casillaMasCercanaAPuerta = new ArrayList<>(posible.get(movimientos));
									}
									longCaminoMasCorto = posible.size();
								}
								
							}
						}
					}
				}
			}
			
		}
		return casillaMasCercanaAPuerta;
	}
	public static void logicaMarcarLista(Jugador j) {
		
		for(Asesinato carta : Gestion.acusacion) {
			if((boolean)j.infoParaNpcs.get(carta).get(INFONPCS.ENSENYADA)== false) {
				if(Gestion.cartasEnsenyadas.isEmpty()&&!j.cartas.contains(carta)) {
					j.infoParaNpcs.get(carta).replace(INFONPCS.SINENSENYARME, Integer.MAX_VALUE);
				}
				if(Gestion.cartasEnsenyadas.containsKey(carta)) {
					j.infoParaNpcs.get(carta).replace(INFONPCS.ENSENYADA, true);
					System.out.println("CARTA PEDIDA ENSEÑADA: "+carta);
					
				}else {
					j.infoParaNpcs.get(carta).replace(INFONPCS.SINENSENYARME, j.infoParaNpcs.get(carta).get(INFONPCS.SINENSENYARME));
					System.out.println("CARTA PEDIDA NO ENSEÑADA: "+carta);
					
				}
			}
		}
		int sospechososMarcados = 0;
		Asesinato sosSinMarcar =null;
		int armasMarcadas = 0;
		Asesinato armaSinMarcar =null;
		int lugaresMarcados = 0;
		Asesinato lugSinMarcar =null;
		for(Asesinato carta:j.infoParaNpcs.keySet()) {
			if((boolean)j.infoParaNpcs.get(carta).get(INFONPCS.ENSENYADA)==true) {
				if(carta instanceof Sospechoso) {
					sospechososMarcados++;
				}else if(carta instanceof Arma) {
					armasMarcadas++;
				}else {
					lugaresMarcados++;
				}
			}else {
				if(carta instanceof Sospechoso) {
					sosSinMarcar = carta;
				}else if(carta instanceof Arma) {
					armaSinMarcar = carta;
				}else {
					lugSinMarcar = carta;
				}
			}
		}
		if(sospechososMarcados==Gestion.datosPartida.sospechosos.size()-1) {
			j.infoParaNpcs.get(sosSinMarcar).replace(INFONPCS.SINENSENYARME,Integer.MAX_VALUE);
			System.out.println("Culpable "+sosSinMarcar);
			j.infoParaNpcs.get(sosSinMarcar).get(INFONPCS.SINENSENYARME);
		}
		if(armasMarcadas==Gestion.datosPartida.armas.size()-1) {
			j.infoParaNpcs.get(armaSinMarcar).replace(INFONPCS.SINENSENYARME,Integer.MAX_VALUE);
			System.out.println("Culpable "+armaSinMarcar);
		}
		if(lugaresMarcados==Gestion.datosPartida.lugares.size()-1) {
			j.infoParaNpcs.get(lugSinMarcar).replace(INFONPCS.SINENSENYARME,Integer.MAX_VALUE);
			System.out.println("Culpable "+lugSinMarcar);
		}
		for(Asesinato carta:j.infoParaNpcs.keySet()) {
			if((boolean)j.infoParaNpcs.get(carta).get(INFONPCS.ENSENYADA)) {
				System.out.println(carta);
			}
		}
	}

	public static void main(String[] args) {
		
		//MÉTODO 1
		//System.out.println(g.crearMatriz(23, 23));
		//System.out.println(g.elevarMatriz(12,g.crearMatriz(23, 23)));
		//MÉTODO 2
		ArrayList<ArrayList<Integer>> tablero = Gestion.crearTablero(numFilas, numColumnas);
		System.out.println(tablero);
		for(ArrayList<Integer>a:tablero) {
			for(Integer i: a) {
				if(i==1) {
					System.out.print("()");
				}else if(i==0){
					System.out.print("  ");
				}else if(i==11){
				System.out.print("II");
				}else {
					System.out.print(i+" "); 
				}
			}
			System.out.println("");
		}
		
	}
	public static HashMap<NombrePersonaje,HashMap<TipoSprite,ArrayList<Image>>> crearSprites(){
		int inicioX = 4;
		int ancho = 54;
		int entreImagenesX = 10;
		int inicioY = 515;
		int alto = 60;
		int entreImagenesY = 4;
		HashMap<NombrePersonaje,HashMap<TipoSprite, ArrayList<Image>>>mapaPorPersonaje= new HashMap<>();
		for (int i=0; i<NombrePersonaje.values().length;i++) {
			NombrePersonaje personaje = NombrePersonaje.values()[i];
			BufferedImage imagenHojaSprites = null;
			try {
				imagenHojaSprites = ImageIO.read(new File(("src/sprites/"+personaje+".png")));
			} catch (IOException e) {
				e.printStackTrace();
				crearSprites();
			}
			HashMap<TipoSprite,ArrayList<Image>>mapaPorTipo = new HashMap<>();
			for(int j=0;j<TipoSprite.values().length;j++) {
				TipoSprite tipoSprite = TipoSprite.values()[j];
				ArrayList<Image>sprites = new ArrayList<>();
				int numSprites;
				if(tipoSprite.toString().startsWith("Agarrar")) {
					numSprites=6;
				}else {
					numSprites=9;
				}
				for (int k=0;k<numSprites;k++) {
					Image sprite =  imagenHojaSprites.getSubimage(inicioX+k*(ancho+entreImagenesX), inicioY+j*(alto+entreImagenesY), ancho, 60);
					sprites.add(sprite);
				}
				mapaPorTipo.put(tipoSprite, sprites);
			}
			mapaPorPersonaje.put(personaje, mapaPorTipo);
		}
			
		         
		   
		return mapaPorPersonaje;
	}
	public static void sonar() {
		try {
			Gestion.player = new Player (new FileInputStream ("src/Clases/musicaproyecto.mp3"));
			Gestion.player.play ();
			Gestion.player.close ();
			if(!Gestion.dejarDeSonar.get()) {
				sonar();
			}
		} catch (JavaLayerException | FileNotFoundException e) {
			e.printStackTrace();
		}			
	}
	
}