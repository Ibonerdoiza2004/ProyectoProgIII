package Clases;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
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
	protected static HashMap<NombrePersonaje,HashMap<TipoSprite,ArrayList<Image>>> sprites= crearSprites();
	
	public static int getNumTurno() {
		return numTurno;
	}

	public static void setNumTurno(int numTurno) {
		Gestion.numTurno = numTurno;
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

	
	public static void eleccionJugadores(int numJugadores) {
		for (int i = 0; i<numJugadores; i++) {
			HashMap<Asesinato, OpcionesLista> listaVacia = new HashMap<>();
			for(Asesinato asesinato: datosPartida.todasLasCartas) {
				listaVacia.put(asesinato, OpcionesLista.NO);
			}
			Jugador jugador = new Jugador(new ArrayList<Asesinato>(), new Personaje(/*El enum */) , new HashMap<Implicados, Asesinato>(), false,  new int[]{0,0}, listaVacia, false); //En la clase personaje hay que crear un constructor que reciba solamente un enum con el nombre del personaje
			jugadores.add(jugador);
		}
	}
	public void creacionJugadoresIA(int numJugadoresIA) {
		for (int i = 0; i<numJugadoresIA; i++) {
			HashMap<Asesinato, OpcionesLista> listaVacia = new HashMap<>();
			for(Asesinato asesinato: datosPartida.todasLasCartas) {
				listaVacia.put(asesinato, OpcionesLista.NO);
			}
			Jugador jugador = new Jugador(new ArrayList<Asesinato>(), new Personaje(),  new HashMap<Implicados, Asesinato>(), false, new int[]{0,0}, listaVacia, true); //En la clase personaje hay que crear un constructor que reciba solamente un enum con el nombre del personaje
			jugadores.add(jugador);
		}
	}
	public static void repartirCartas(ArrayList<Asesinato> cartas) {
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
	public static void eleccionOrdenJugadores(ArrayList<Jugador>jugadores){
		for (Jugador j:jugadores) {
			//Añadir la ventana de tirar dados
			//Poner una pantalla para avisar que le toca al siguiente jugador
			//Guardar el mayor valor y quien lo ha conseguido
		}
		//Establecer turno con el codigo del jugador que ha conseguido el mayor valor
	}
	
	//Este método lo llamo desde la clase 'VentanaDado' para pasarle los valores

	
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
				} else if (((i+1==j)&&(j%columnasMapa!=0)) || ((i-1==j) && (i%columnasMapa!=0))){
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
	
	public ArrayList<ArrayList<Integer>> elevarMatriz(int potencia, ArrayList<ArrayList<Integer>>matriz) {
		ArrayList<ArrayList<Integer>>resultado= new ArrayList<>(matriz);
		for (int p = 1; p<potencia;p++) {
			ArrayList<ArrayList<Integer>>temp= new ArrayList<>();
			for(int i = 0; i<matriz.size();i++) {
				ArrayList<Integer>fila= new ArrayList<>();
				for(int j = 0;j<matriz.get(0).size();j++) {
					int pos = 0;
					for(int k = 0;k<matriz.get(0).size();k++) {
						pos = pos + matriz.get(i).get(k)*resultado.get(k).get(j);
					}
					fila.add(pos);
				}
				temp.add(fila);
			}
			resultado = new ArrayList<>(temp);
		}
		return resultado;
	}
	
	
	
	//En este método se crea una matriz de 23x23 para representar el tablero. Añade un 1 en las posiciones que son utilizables, 
	//es decir, que tienen casillas, y añade un 0 en las posiciones que no lo son (habitaciones). 
	//Una vez creado el tablero, cuando el jugador tira los dados, hay una función recursiva que recibe la posición del jugador
	//y los movimientos que tiene y calcula a qué casillas puede llegar
	//VENTAJAS: Es mucho más eficiente que el otro método (Comprobar con 12 movimientos)
	//DESVENTAJAS: Solo calcula los movimientos para la casilla actual (El otro método se puede cargar antes de que empiece 
	//             la partida y reutilizarlo)
	public static  HashMap<String,ArrayList<ArrayList<Integer>>>calcularMovimiento(int fila, int columna, int movimiento, ArrayList<ArrayList<Integer>> tablero){
		ArrayList <ArrayList<Integer>> casillasPosibles = new ArrayList<>();
		ArrayList <ArrayList<Integer>> puertasPosibles = new ArrayList<>();
		HashMap<String,ArrayList <ArrayList<Integer>>>movimientosPosibles = new HashMap<>();
		movimientoCasillasRecursive(fila, columna, movimiento, 0, casillasPosibles, puertasPosibles, tablero);
		movimientosPosibles.put("casillasPosibles", casillasPosibles);
		movimientosPosibles.put("puertasPosibles", puertasPosibles);
		return movimientosPosibles;
	}
	
	private static void  movimientoCasillasRecursive(int fila, int columna, int movimiento, int iteracion, ArrayList <ArrayList<Integer>> casillasPosibles, ArrayList <ArrayList<Integer>> puertasPosibles, ArrayList<ArrayList<Integer>> tablero) {
		ArrayList<Integer> pos = new ArrayList<>();
		pos.add(fila);
		pos.add(columna);
		if(!casillasPosibles.contains(pos)&&!puertasPosibles.contains(pos)) {
			if(tablero.get(fila).get(columna)==1) {
				casillasPosibles.add(pos);
			}else {
				puertasPosibles.add(pos);
			}
		}
		if(iteracion<movimiento) {
			if((fila!=tablero.size()-1)&&(tablero.get(fila+1).get(columna)!=0)) {
				movimientoCasillasRecursive(fila+1, columna, movimiento, iteracion+1, casillasPosibles, puertasPosibles, tablero);
			}
			if((fila!=0)&&(tablero.get(fila-1).get(columna)!=0)) {
				movimientoCasillasRecursive(fila-1, columna, movimiento, iteracion+1, casillasPosibles, puertasPosibles, tablero);
			}
			if((columna!=tablero.get(fila).size()-1)&&(tablero.get(fila).get(columna+1)!=0)) {
				movimientoCasillasRecursive(fila, columna+1, movimiento, iteracion+1, casillasPosibles, puertasPosibles, tablero);
			}
			if((columna!=0)&&(tablero.get(fila).get(columna-1)!=0)) {	
				movimientoCasillasRecursive(fila, columna-1, movimiento, iteracion+1, casillasPosibles, puertasPosibles, tablero);
			}
			
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
					
				}else if ((i<7&&j<6)  ||  ((i==9&&j<5)||((i>9&&i<16)&&(j<8)))  ||  ((i>18)&&(j<7))  ||
						(i==0&&(j>9&&j<14))||((i>0&&i<8)&&(j>7&&j<16))  ||  ((i>9 && i<17)&&(j>9 && j<15))  ||  ((i>17)&&(j>8 && j<15)) ||
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

	public void turnoJugador() {
		
	}
	

	public static void main(String[] args) {
		Gestion.eleccionJugadores(3);
		Gestion.repartirCartas(Gestion.datosPartida.todasLasCartas);
		
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
				}
				else {
					System.out.print(i+" ");
				}
			}
			System.out.println("");
		}
		HashMap<String,ArrayList<ArrayList<Integer>>> movimientos = Gestion.calcularMovimiento(8, 10, 12, tablero);
		System.out.println(movimientos);
		System.out.println(movimientos.size());
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
				// TODO Auto-generated catch block
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
