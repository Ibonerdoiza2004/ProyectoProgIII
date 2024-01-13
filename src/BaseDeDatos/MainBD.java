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

enum Sitio{CERO_UNO, ASEO, SALA_DE_ORDENADORES, CAFETERIA, LABORATORIO, DECANATO, TREINTA_Y_TRES, GIMNASIO, CLAUSTRO}
enum NombrePersonaje{Rojo, Amarillo, Negro, Verde, Azul, Morado};
enum PosiblesNicks{DetectiveShadow, MysteryMastermind, SleuthSphinx, CovertInvestigator, ClueConqueror, CipherSherlock};

public class MainBD {
	
	private static int Num_Partida = 0;
	private static Connection conn;
	private static Statement statement;
	private static ResultSet rs;
	
	private static DefaultTableModel modeloTabla;
	private static JPanel pnlCentral = new JPanel();
	private static JButton btnTablaStats = new JButton("Tabla Estadísticas");
	private static JButton btnTablaPartida = new JButton("Tabla Partida");
	private static JButton btnJugador = new JButton("Tabla Jugador");
	private static ConexionSQlite conexion;

	public static void main(String[] args) {
		
		//Conexion c = new Conexion();
		conexion = new ConexionSQlite();
		
		try {
			//conn = c.conectar();
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
			
			String crearTablaEstadisticas = "CREATE TABLE ESTADISTICAS ("
					+ "MES_STATS TEXT,"
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
			        + "ID_PARTIDA INTEGER,"
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
                    + "PRIMARY KEY (ID_JUGADOR,ID_PARTIDA),"
                    + "FOREIGN KEY (ID_JUGADOR) REFERENCES JUGADOR(ID_JUGADOR),"
                    + "FOREIGN KEY (ID_PARTIDA), REFERENCES PARTIDA(ID_PARTIDA)"
                    + ")";
            
			
			//Datos para tabla Estadísticas
			int i = 0;
			Random r = new Random();
			statement.executeUpdate("delete from estadisticas");
			/**
			 * Elimino lo todos los datos de la tabla
			 * hasta cargar los oficiales
			 */
			int num_partidas_mes = 0; //Voy a necesitar esta variable
			@SuppressWarnings("deprecation")
			LocalDate fechaInicial = LocalDate.of(2023, 5, 1);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
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
				String sent = "insert into estadisticas values (" + fechaTexto + ", "+ num_partidas_mes + ", " + num_jugadores_reales
						+ ", " + num_npcs + ", " + duracion_partida + ", " + miss_scarlet + ", " + colonel_Mustard
						+ ", " + mrs_white + ", " + mr_green + ", " + mrs_peacock + ", " + profesor_plum + ")";
				System.out.println(sent);
				statement.executeUpdate(sent);
				fechaInicial = fechaInicial.plusMonths(1);
		        
		        //Datos de prueba para tabla Jugador:
		        //int id_jugador = r.nextInt(50+1);
		        
				i ++;
			}
			
			//Cargar datos de las partidas del último mes
			i = 0;
			NombrePersonaje[] valores = NombrePersonaje.values();
			while (i < num_partidas_mes) {
				Num_Partida ++;
				//Para insertar mejor crear PrepraedStatements
				PreparedStatement psPartida = conn.prepareStatement("INSERT INTO partida VALUES (?,?,?,?,?)");
				
				//Datos de prueba para tabla partida:
				int id_partida = Num_Partida;
				//String fechaAleatoria = "2023-11-13";
				LocalDate fechaAleatoria = LocalDate.of(2023, 11, r.nextInt(29) + 1);
				String fechaFormateada = fechaAleatoria.toString();
		        int duracion_p = r.nextInt(3)+1;
		        String ganador = valores[r.nextInt(valores.length-1)+1].name();
		        int numJugadores = r.nextInt(6)+1;
		        psPartida.setInt(1, id_partida);
		        psPartida.setString(2, fechaFormateada);
		        psPartida.setInt(3, duracion_p);
		        psPartida.setString(4, ganador);
		        psPartida.setInt(5, numJugadores);
		        psPartida.executeUpdate();
//				String sent = "insert into partida values (" + id_partida + ", " + fechaFormateada
//						+ ", " + duracion_p + ", " + ganador + ", " + numJugadores + ")";
//				System.out.println(sent);
//				statement.executeUpdate(sent);
		        i ++;
			}
			
			//Cargar datos para 50 jugadores:
			PosiblesNicks[] valoresNicks = PosiblesNicks.values();
			Sitio[] valoresSitio = Sitio.values();
			PreparedStatement psJugador = conn.prepareStatement("INSERT INTO JUGADOR VALUES (?, ?, ?, ?, ?)");
			i = 0;
			while (i < 50) {
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(2000); //Simplemente para que se cargen todos los datos antes de cargarlos en las tablas
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
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
		
		ventBD.add(pnlBotonera, BorderLayout.SOUTH);
	}
	
	private static JTable tablaDatos;
	private static JFrame ventBD;
	
	
	private static void cargarTablaEstadisticas() {
		
		Vector<String> cabeceras = new Vector<String>();
		cabeceras.add("NUM_PARTIDAS"); cabeceras.add("JUGADORES_REALES"); cabeceras.add("NUM_NPCS");
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
				int num_partidas = rs.getInt(1);
				int jug_reales = rs.getInt(2);
				int npcs = rs.getInt(3);
				int media_h_partidas = rs.getInt(4);
				int miss_scarlet = rs.getInt(5);
				int colonel_mustard = rs.getInt(6);
				int mrs_white = rs.getInt(7);
				int mr_green = rs.getInt(8);
				int mrs_peacock = rs.getInt(9);
				int profesor_plum = rs.getInt(10);
				Vector<Integer> fila = new Vector<Integer>();
				fila.add(num_partidas); fila.add(jug_reales); fila.add(npcs);
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
				int idPartida = rs.getInt(1);
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
	
	private static void cambiarTabla() {
		modeloTabla.setRowCount(0);
		modeloTabla.setColumnCount(0);
		tablaDatos.repaint();
	}
	
}
