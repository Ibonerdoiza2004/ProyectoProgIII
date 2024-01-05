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
import java.time.LocalDate;
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

enum Sospechosos{Andoni, Jenny, Carlos, Asier, Nekane, Iñaki}
enum Sitio{CERO_UNO, ASEO, SALA_DE_ORDENADORES, CAFETERIA, LABORATORIO, DECANATO, TREINTA_Y_TRES, GIMNASIO, CLAUSTRO}
enum NombrePersonaje{Rojo, Amarillo, Negro, Verde, Azul, Morado};
enum PosiblesNicks{DetectiveShadow, MysteryMastermind, SleuthSphinx, CovertInvestigator, ClueConqueror, CipherSherlock};

public class MainBD {
	
	private static int Num_Partida = 0;
	private static Connection conn;
	private static Statement statement;
	private static ResultSet rs;
	
	private static DefaultTableModel modeloTablaStats = new DefaultTableModel();
	private static JPanel pnlCentral = new JPanel();
	private static JButton btnTablaStats = new JButton("Tabla Estadísticas");
	private static JButton btnTablaPartida = new JButton("Tabla Partida");
	private static JButton btnUsuario = new JButton("Tabla Usuario");
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
                    + "ID_JUGADOR INTEGER,"
                    + "NOMBRE STRING,"
                    + "PERSONAJE_ASIGNADO STRING,"
                    + "HABITACION_ACTUAL STRING,"
                    + "NUM_PARTIDAS_JUGADAS INTEGER"
                    + ")";
            statement.executeUpdate(crearTablaJugador);
			
			//Datos para tabla Estadísticas
			int i = 0;
			Random r = new Random();
			statement.executeUpdate("delete from estadisticas");
			/**
			 * Elimino lo todos los datos de la tabla
			 * hasta cargar los oficiales
			 */
			int num_partidas_mes = 0; //Voy a necesitar esta variable
			while (i != 20) {
				
				//Datos de prueba para tabla estadísticas:
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
				String sent = "insert into estadisticas values (" + num_partidas_mes + ", " + num_jugadores_reales
						+ ", " + num_npcs + ", " + duracion_partida + ", " + miss_scarlet + ", " + colonel_Mustard
						+ ", " + mrs_white + ", " + mr_green + ", " + mrs_peacock + ", " + profesor_plum + ")";
				System.out.println(sent);
				statement.executeUpdate(sent);
		        
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
//			i = 0;
//			while (i < 50) {
//				//AQUI
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Después de cargar todo, se carga la parte visual
		ini();
		ventBD.setVisible( true );
		
		btnTablaStats.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarTablaEstadisticas();
				ventBD.add(new JScrollPane(tablaEstadisticas), BorderLayout.CENTER);
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
		ventBD.setSize(640, 480);
		ventBD.setLocationRelativeTo( null );
		ventBD.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel pnlBotonera = new JPanel();
		pnlBotonera.add(btnTablaStats); pnlBotonera.add(btnTablaPartida); pnlBotonera.add(btnUsuario);
		
		Vector<String> cabeceras = new Vector<String>();
		cabeceras.add("NUM_PARTIDAS"); cabeceras.add("JUGADORES_REALES"); cabeceras.add("NUM_NPCS");
		cabeceras.add("MEDIA_DUR_PARTIDA"); cabeceras.add("MISS_SCARLET"); cabeceras.add("COLONEL_MUSTARD");
		cabeceras.add("MRS_WHITE"); cabeceras.add("MR_GREEN"); cabeceras.add("MRS_PEACOCK");
		cabeceras.add("PROFESOR_PLUM");
	
		modeloTablaStats.setDataVector(new Vector<Vector<Object>>(), cabeceras);
		tablaEstadisticas = new JTable(modeloTablaStats);
		
		ventBD.add(pnlBotonera, BorderLayout.SOUTH);
	}
	
	private static JTable tablaEstadisticas;
	private static JFrame ventBD;
	
	
	private static void cargarTablaEstadisticas() {
		
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
				modeloTablaStats.addRow(fila);
			}
			tablaEstadisticas.repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
