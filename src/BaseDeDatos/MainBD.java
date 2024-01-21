package BaseDeDatos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Clases.Jugador;
import Clases.Partida;
import Clases.VentanaNJugadores;

enum Sitio{CERO_UNO, ASEO, SALA_DE_ORDENADORES, CAFETERIA, LABORATORIO, DECANATO, TREINTA_Y_TRES, GIMNASIO, CLAUSTRO}
enum NombrePersonaje{Rojo, Amarillo, Negro, Verde, Azul, Morado};
enum PosiblesNicks{DetectiveShadow, MysteryMastermind, SleuthSphinx, CovertInvestigator, ClueConqueror, CipherSherlock};

public class MainBD {
	
	private static int NumJugadoresRegistrados = 1;
	private static int numPartidaMes = 0;
	private static Connection conn;
	private static Statement statement;
	private static ResultSet rs;
	
	private static JFrame ventBD;
	private static JTable tablaDatos;
	private static DefaultTableModel modeloTabla;
	private static JPanel pnlCentral = new JPanel();
	private static JButton btnTablaStats = new JButton("Tabla Estadísticas");
	private static JButton btnTablaPartida = new JButton("Tabla Partida");
	private static JButton btnJugador = new JButton("Tabla Jugador");
	private static JButton btnParticipa = new JButton("Tabla de realción Participa");
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
	private static final LocalDate currentMonth = LocalDate.of(2023, 12, 01);
	private static ConexionSQlite conexion;
	private static int num_partidas_mes;
	private static VentanaNJugadores vn;
	private int numJugsPartida = 0;
	private ArrayList<String> jugsPartida;
	public static Partida partida;

	public static void main(String[] args) {
		
	}
	
	public void iniciarBD() {
		partida = new Partida();
		conexion = new ConexionSQlite();
		
		try {
			try {
				conn = conexion.connect();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			statement = conn.createStatement();
			
			//Primero crear las tablas desde aquí:
			//Estas tres líneas son provisionales
			statement.executeUpdate("DROP TABLE IF EXISTS estadisticas");
			statement.executeUpdate("DROP TABLE IF EXISTS partida");
			statement.executeUpdate("DROP TABLE IF EXISTS jugador");
			statement.executeUpdate("DROP TABLE IF EXISTS participa");
			
			String crearTablaEstadisticas = "CREATE TABLE ESTADISTICAS ("
					+ "MES_STATS STRING,"
			        + "NUM_PARTIDAS_MES INTEGER,"
			        + "NUM_JUGADORES_REALES INTEGER,"
			        + "NUM_NPCS INTEGER,"
			        + "MEDIA_DURACION_PARTIDA INTEGER,"
			        + "MISS_SCARLET INTEGER,"
			        + "COLONER_MUSTARD INTEGER,"
			        + "MRS_WHITE INTEGER,"
			        + "MR_GREEN INTEGER,"
			        + "MRS_PEACOCK INTEGER,"
			        + "PROFESOR_PLUM INTEGER"
			        + ")";
			
			statement.executeUpdate(crearTablaEstadisticas);
			
			String crearTablaPartida = "CREATE TABLE PARTIDA ("
			        + "ID_PARTIDA STRING,"
			        + "FECHA_PARTIDA TEXT,"
			        + "DURACION INTEGER,"
			        + "GANADOR STRING,"
			        + "NUM_JUGS INTEGER"
			        + ")";
			statement.executeUpdate(crearTablaPartida);
			
            String crearTablaJugador = "CREATE TABLE JUGADOR ("
                    + "ID_JUGADOR STRING,"
                    + "NOMBRE STRING,"
                    + "PERSONAJE_ASIGNADO STRING,"
                    + "HABITACION_MAS_VISITADA STRING,"
                    + "NUM_PARTIDAS_JUGADAS INTEGER"
                    + ")";
            statement.executeUpdate(crearTablaJugador);
            
            String crearTablaRelacion = "CREATE TABLE PARTICIPA (" //Con las claves primarias de partida y jugador
                    + "ID_JUGADOR STRING,"
                    + "ID_PARTIDA STRING,"
                    + "FECHA_PARTIDAS STRING,"
                    + "PRIMARY KEY (ID_JUGADOR,ID_PARTIDA),"
                    + "FOREIGN KEY (ID_JUGADOR) REFERENCES JUGADOR(ID_JUGADOR),"
                    + "FOREIGN KEY (ID_PARTIDA) REFERENCES PARTIDA(ID_PARTIDA)"
                    + ")";
            statement.executeUpdate(crearTablaRelacion);
            
			//Datos para tabla Estadísticas
			int i = 0;
			Random r = new Random();
			statement.executeUpdate("delete from estadisticas");
			/**
			 * Elimino lo todos los datos de la tabla
			 * hasta cargar los oficiales
			 */
			num_partidas_mes = 0; //Voy a necesitar esta variable
			@SuppressWarnings("deprecation")
			LocalDate fechaInicial = LocalDate.of(2022, 5, 1);
			String fechaTexto;
			while (i != 20) {
				//Datos de prueba para tabla estadísticas:
				fechaTexto = formatter.format(fechaInicial);
				num_partidas_mes = r.nextInt(100)+1;
				int num_jugadores_reales = r.nextInt(450)+1;
				int num_npcs = 600-num_jugadores_reales;
				int duracion_partida = r.nextInt(3)+1;
				int numEscogidos = 0;
				int miss_scarlet = r.nextInt(100)+1;
				int colonel_Mustard = r.nextInt(100)+1;
				int mrs_white = r.nextInt(100)+1;
				int mr_green = r.nextInt(100)+1;
				int mrs_peacock = r.nextInt(100)+1;
				numEscogidos += miss_scarlet; numEscogidos += colonel_Mustard; numEscogidos += mrs_white;
				numEscogidos += mr_green; numEscogidos += mrs_peacock;
				int profesor_plum = 600 - numEscogidos;
				String sent = "insert into estadisticas values ( '" + fechaTexto + "', "+ num_partidas_mes + ", " + num_jugadores_reales
						+ ", " + num_npcs + ", " + duracion_partida + ", " + miss_scarlet + ", " + colonel_Mustard
						+ ", " + mrs_white + ", " + mr_green + ", " + mrs_peacock + ", " + profesor_plum + ")";
				//System.out.println(sent);
				statement.executeUpdate(sent);
				fechaInicial = fechaInicial.plusMonths(1);
		        
		        //Datos de prueba para tabla Jugador:
		        //int id_jugador = r.nextInt(50+1);
		        
				i ++;
			}
			
			//Cargar datos de las partidas del último mes
			i = 0;
			NombrePersonaje[] valores = NombrePersonaje.values();
			while (i < num_partidas_mes) { //Son los datos de las partidas y jugadores del último mes
				//Para insertar mejor crear PrepraedStatements
				PreparedStatement psPartida = conn.prepareStatement("INSERT INTO partida VALUES (?,?,?,?,?)");
				
				//Datos de prueba para tabla partida:
				String id_partida = "P"+(i+1);
				//String fechaAleatoria = "2023-11-13";
				//LocalDate fechaAleatoria = LocalDate.of(2023, 11, r.nextInt(29) + 1);
				//String fechaFormateada = fechaInicial.minusMonths(1).toString();
				String fechaFormateada = formatter.format(currentMonth);
		        int duracion_p = r.nextInt(3)+1;
		        String ganador = valores[r.nextInt(valores.length-1)+1].name();
		        int numJugadores = r.nextInt(6)+1; //Atributo a utilizar
		        psPartida.setString(1, id_partida);
		        psPartida.setString(2, fechaFormateada);
		        psPartida.setInt(3, duracion_p);
		        psPartida.setString(4, ganador);
		        psPartida.setInt(5, numJugadores);
		        datosParticipa(id_partida, numJugadores);
		        psPartida.executeUpdate();
//				String sent = "insert into partida values (" + id_partida + ", " + fechaFormateada
//						+ ", " + duracion_p + ", " + ganador + ", " + numJugadores + ")";
//				System.out.println(sent);
//				statement.executeUpdate(sent);
		        i ++;
			}
			
			//Cargar los datos de las partidas
			cargarDatosPartida();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(2000); //Simplemente para que se cargen todos los datos antes de cargarlos en las tablas
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public void cargarVentanaDatos() {
		//Después de cargar todo, se carga la parte visual, las JTables
		ini();
		ventBD.setVisible( true );
		
		btnTablaStats.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cambiarTabla();
				cargarTablaEstadisticas();
				ventBD.repaint();
				ventBD.revalidate();
			}
		});
		
		btnTablaPartida.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			cambiarTabla();
			cargarTablaPartida();
			ventBD.repaint();
			ventBD.revalidate();
			}
		});
		
		btnParticipa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cambiarTabla();
				cargarTablaParticipa();
				ventBD.repaint();
				ventBD.revalidate();
				
			}
		});
		
		btnJugador.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cambiarTabla();
				cargarTablaJugador();
				ventBD.repaint();
				ventBD.revalidate();
			}
		});
		
		ventBD.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					conexion.disconnect(); //Acabar la conexón con la BD al cerrar la ventana
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}

		});
	}
	
	private static void ini() {
		ventBD = new JFrame();
		ventBD.setLayout(new BorderLayout());
		ventBD.setSize(800, 600);
		ventBD.setLocationRelativeTo( null );
		ventBD.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		modeloTabla = new DefaultTableModel();
		tablaDatos = new JTable();
		ventBD.add(new JScrollPane(tablaDatos), BorderLayout.CENTER); //Solo hacerlo una vez
		
		JPanel pnlBotonera = new JPanel();
		pnlBotonera.add(btnTablaStats); pnlBotonera.add(btnTablaPartida); pnlBotonera.add(btnJugador);
		pnlBotonera.add(btnParticipa);
		
		ventBD.add(pnlBotonera, BorderLayout.SOUTH);
	}
	
	
	private static void cargarTablaEstadisticas() {
		
		Vector<String> cabeceras = new Vector<String>();
		cabeceras.add("MES_STATS"); cabeceras.add("NUM_PARTIDAS"); cabeceras.add("JUGADORES_REALES"); cabeceras.add("NUM_NPCS");
		cabeceras.add("MEDIA_DUR_PARTIDA"); cabeceras.add("MISS_SCARLET"); cabeceras.add("COLONEL_MUSTARD");
		cabeceras.add("MRS_WHITE"); cabeceras.add("MR_GREEN"); cabeceras.add("MRS_PEACOCK");
		cabeceras.add("PROFESOR_PLUM");
	
		modeloTabla.setDataVector(new Vector<Vector<Object>>(), cabeceras);
		tablaDatos.setModel(modeloTabla);
		
		String sent = "";
		try {
			sent = "select * from estadisticas";
			rs = statement.executeQuery(sent);
			while (rs.next()) {
				String fechaStats = rs.getString(1);
				int num_partidas = rs.getInt(2);
				int jug_reales = rs.getInt(3);
				int npcs = rs.getInt(4);
				int media_h_partidas = rs.getInt(5);
				int miss_scarlet = rs.getInt(6);
				int colonel_mustard = rs.getInt(7);
				int mrs_white = rs.getInt(8);
				int mr_green = rs.getInt(9);
				int mrs_peacock = rs.getInt(10);
				int profesor_plum = rs.getInt(11);
				Vector<Object> fila = new Vector<Object>();
				fila.add(fechaStats); fila.add(num_partidas); fila.add(jug_reales); fila.add(npcs);
				fila.add(media_h_partidas); fila.add(miss_scarlet); fila.add(colonel_mustard);
				fila.add(mrs_white); fila.add(mr_green); fila.add(mrs_peacock); fila.add(profesor_plum);
				modeloTabla.addRow(fila);
			}
			tablaDatos.repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void cargarTablaPartida() {
		
		Vector<String> cabeceras = new Vector<String>();
		cabeceras.add("ID_PARTIDA"); cabeceras.add("FECHA_PARTIDA"); cabeceras.add("DURACION");
		cabeceras.add("GANADOR"); cabeceras.add("NUM_JUGS");
		//Estas dos líneas de abajo las vuelva poner por si esta es la primera tabla que se selecciona
		modeloTabla.setDataVector(new Vector<Vector<Object>>(), cabeceras);
		tablaDatos.setModel(modeloTabla);
		
		String sent = "SELECT * FROM PARTIDA";
		try {
			rs = statement.executeQuery(sent);
			while (rs.next()) {
				String idPartida = rs.getString(1);
				String fechaPartida = rs.getString(2);
				int duracion = rs.getInt(3);
				String ganador = rs.getString(4);
				int numJugadores = rs.getInt(5);
				Vector<Object> fila = new Vector<Object>();
				fila.add(idPartida); fila.add(fechaPartida); fila.add(duracion); fila.add(ganador); fila.add(numJugadores);
				modeloTabla.addRow(fila);
			}
			tablaDatos.repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void cargarTablaJugador() {
		
		Vector<String> cabeceras = new Vector<String>();
		cabeceras.add("ID_JUGADOR"); cabeceras.add("NOMBRE"); cabeceras.add("PERSONAJE_ASIGNADO");
		cabeceras.add("HABITACION_MAS_VISITADA"); cabeceras.add("NUM_PARTIDAS_JUGADAS");
		modeloTabla.setDataVector(new Vector<Vector<Object>>(), cabeceras);
		tablaDatos.setModel(modeloTabla);
		
		String sent = "SELECT * FROM JUGADOR";
		try {
			rs = statement.executeQuery(sent);
			
			while (rs.next()) {
				String idJugador = rs.getString(1);
				String nombre = rs.getString(2);
				String personajeAsignado = rs.getString(3);
				String habitacion = rs.getString(4);
				int partidasJugadas = rs.getInt(5);
				Vector<Object> fila = new Vector<Object>();
				fila.add(idJugador); fila.add(nombre); fila.add(personajeAsignado); fila.add(habitacion);
				fila.add(partidasJugadas);
				modeloTabla.addRow(fila);
			}
			tablaDatos.repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void cargarTablaParticipa() {
		Vector<String> cabeceras = new Vector<String>();
		cabeceras.add("ID_JUGADOR"); cabeceras.add("ID_PARTIDA"); cabeceras.add("FECHA_PARTIDAS");
		modeloTabla.setDataVector(new Vector<Vector<Object>>(), cabeceras);
		tablaDatos.setModel(modeloTabla);
		
		String sent = "SELECT * FROM PARTICIPA;";
		try {
			rs = statement.executeQuery(sent);
			 while (rs.next()) {
				 String idJugador = rs.getString(1);
				 String idPartida = rs.getString(2);
				 String fecha = rs.getString(3);
				 Vector<String> fila = new Vector<String>();
				 fila.add(idJugador); fila.add(idPartida); fila.add(fecha);
				 modeloTabla.addRow(fila);
			 }
			 tablaDatos.repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void cargarDatosPartida() throws SQLException {
		Random r = new Random();
		NombrePersonaje[] valores = NombrePersonaje.values();
		PosiblesNicks[] valoresNicks = PosiblesNicks.values();
		Sitio[] valoresSitio = Sitio.values();
		PreparedStatement psJugador = conn.prepareStatement("INSERT INTO JUGADOR VALUES (?, ?, ?, ?, ?)");
		int i = 1;
		while (i <= num_partidas_mes*6) {
			String idJugador = "J"+i;
			String nom = valoresNicks[r.nextInt(valoresNicks.length-1)+1].toString();
			String personajeAsigando = valores[r.nextInt(valores.length-1)+1].toString();
			String habitacion = valoresSitio[r.nextInt(valoresSitio.length-1)+1].toString();
			Integer numPartidas = r.nextInt(50)+1;
			psJugador.setString(1, idJugador);
			psJugador.setString(2, nom);
			psJugador.setString(3, personajeAsigando);
			psJugador.setString(4, habitacion);
			psJugador.setInt(5, numPartidas);
			psJugador.executeUpdate();
			i ++;
		}
	}
	
	private static void datosParticipa(String idPartda, int numJugadores) throws SQLException {
		int i = 1;
		PreparedStatement psParticipa = conn.prepareStatement("INSERT INTO PARTICIPA VALUES (?, ?, ?)");
		while (i <= numJugadores) {
			String idJugador = "J"+i;
			psParticipa.setString(1, idJugador);
			psParticipa.setString(2, idPartda);
			psParticipa.setString(3, formatter.format(currentMonth));
			psParticipa.executeUpdate();
			i ++;
		}
	}
	
	//Meter datos en tablas al crear partida real (usar este método al meter datos partida):
	public boolean anyadirJugador(Jugador jugador) {
		try {
			NumJugadoresRegistrados = 1;
			rs = statement.executeQuery("SELECT * FROM jugador");
			while (rs.next()){
				NumJugadoresRegistrados ++; //Para que sea el id de un nuevo jugador
			}
//			String sent = "INSERT INTO jugador VALUES (" +
//					"'J" + NumJugadoresRegistrados + "', " +
//					"'" + jugador.getNick() + "', " + "'" + null + "'" + 
//					null + ", " + 0 + ")";
			String sent = "INSERT INTO jugador VALUES (" +
			"'J" + NumJugadoresRegistrados + "', " +
			"'" + jugador.getNick() + "', " + " NULL , NULL , " + 0 + ")"; //Esto cambiarlo 
			statement.executeUpdate(sent);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean anyadirRelacion(String idJugador, String idPartida, String fecha) {
		try {
			String sent = "INSERT INTO participa VALUES (" +
					"'" + idJugador + "', " + "'" + idPartida + "', '" + fecha + "')";
			statement.executeUpdate(sent);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean anyadirPartida(Partida p) {
		try {
			numPartidaMes = 1;
			rs = statement.executeQuery("SELECT * FROM partida"); //Where fecha es = la de este mes
			while (rs.next()) {
				numPartidaMes ++;
			}
			//Añadir la partida:
			String sent = "INSERT INTO partida VALUES ('P" + numPartidaMes + "', '" + p.getFecha() + "', " +
			        p.getDuracion() + ", NULL, " + p.getJugadoresPartida().size() + ")";
			statement.executeUpdate(sent);
			partida.setIdPartida("P"+numPartidaMes);
			//Y ahora meter los datos en la tabla relación:
			for (int i = 0; i < partida.getJugadoresPartida().size(); i ++) {
				anyadirRelacion(jugsPartida.get(i), partida.getIdPartida(), partida.getFecha());
			}
			
			//Insertar los datos de los jugadores de la partida
//			for (Jugador j: p.getJugadoresPartida()) {
//				anyadirJugador(j); //Añado cada jugador
//				String idJ = "J" + NumJugadoresRegistrados;
//				anyadirRelacion(idJ, p.getIdPartida(), p.getFecha()); //Añado cada relación
//			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//Métodos para comprobar si jugador está registrado depués de registrarse al iniciar la partida
	public boolean registrarJugador(String nick, String pass) {
		partida.getJugadoresPartida().add(new Jugador(nick, pass));
		return registrarJugador(new Jugador(nick, pass));
	}
	
	public boolean registrarJugador(Jugador jugador) {
		if (jugador.getNick() == null || jugador.getPass() == null || 
			jugador.getNick().isEmpty() || jugador.getPass().isEmpty()) {
			return false;
			}
		try {
			rs = statement.executeQuery("SELECT * FROM jugador WHERE NOMBRE = '" + jugador.getNick() + "'");
			int contador = 0;
			while (rs.next()) {
				contador ++;
			}
			if (contador != 0) {
				return false;
			} else {
				//LO AÑADO
				//System.out.println(jugador);
				anyadirJugador(jugador);
				return loginJugador(jugador.getNick());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean loginJugador(String nick) {
		try {
			rs = statement.executeQuery("SELECT * FROM jugador WHERE NOMBRE = '" + nick + "'");
			int contador = 0;
			while (rs.next()) {
				contador ++;
			}
			if (contador == 0) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public void cogerJugs(VentanaNJugadores vn) {
		numJugsPartida = (int) vn.getSpnJugs().getValue();
		//System.out.println((int) vn.getSpnJugs().getValue());
		//Leer los jugadores de la base de datos y quedarte con estos últimos:
		ArrayList<String> arr = new ArrayList<String>();
		int numJugs = verNumJugsRegistrado();
		//System.out.println(numJugs - numJugsPartida);
		try {
			rs = statement.executeQuery("SELECT * FROM jugador;");
			while (rs.next()) {
				String id = rs.getString(1);
				int numId = Integer.parseInt(id.substring(1)); //Coger la parte numérica del id 
				if (numId > (numJugs - numJugsPartida)) {
					arr.add(id);
				}
			}
			System.out.println(arr); //Ya tengo los ids de los jugadores de esta partida
			jugsPartida = new ArrayList<String>(arr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setterarPersonaje(int numElegido, String personajeElegido) {
		String idVerdadero;
		if (jugsPartida.size() == 1) {
			idVerdadero = jugsPartida.get(0);
		} else {
			idVerdadero = jugsPartida.get(numElegido-1);
		}
		//System.out.println("Mirar " + idVerdadero);
		try {
			statement.executeUpdate("UPDATE jugador set PERSONAJE_ASIGNADO = '" +
			personajeElegido + "' WHERE ID_JUGADOR = '" + idVerdadero + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Integer verNumJugsRegistrado() {
		int numJugs = 0;
		try {
			rs = statement.executeQuery("SELECT * FROM jugador");
			while (rs.next()){
				numJugs ++; //Para que sea el id de un nuevo jugador
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numJugs;
	}
	
	private static void cambiarTabla() {
		modeloTabla.setRowCount(0);
		modeloTabla.setColumnCount(0);
		tablaDatos.repaint();
	}
	
}
