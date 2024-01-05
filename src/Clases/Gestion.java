 package Clases;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;



enum TipoSprite{AndarArriba, AndarIzquierda, AndarAbajo, AndarDerecha, AgarrarArriba, AgarrarDerecha, AgarrarAbajo, AgarrarIzquierda};
public class Gestion {

	//Atributos
	protected static Dimension sizePantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private static int numTurno = 0;
	protected static ArrayList<Jugador> jugadores = new ArrayList<Jugador>(); //Va a tener todos los jugadores de la partida.
	 //Esto va a ser la suma del resultado de los dados que te han tocado al tirarlos en tu turno.s
	protected static int numFilas = 25;
	protected static int numColumnas = 24;
	protected static Contenedor datosPartida = new Contenedor();
	protected static ArrayList<ArrayList<Integer>> tablero = Gestion.crearTablero(numFilas, numColumnas);
	protected static HashMap<NombrePersonaje,HashMap<TipoSprite,ArrayList<Image>>> sprites = crearSprites();
	protected static ArrayList<Asesinato>acusacion = new ArrayList<>();
	protected static JFrame ventanaJuego;
	protected static HashMap<Asesinato, Jugador>cartasEnsenyadas = new HashMap<>();
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
	public static HashMap<Asesinato, String> creacionAnotaciones() {
		HashMap<Asesinato,String> anotacionesVacias = new HashMap<>();
		for(Asesinato asesinato: datosPartida.todasLasCartas) {
			anotacionesVacias.put(asesinato, "");
		}
		return anotacionesVacias;
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
	
	
}
