package BaseDeDatos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
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

public class MainBD {
	
	private static Connection conn;
	private static Statement statement;
	private static ResultSet rs;
	
	private static DefaultTableModel modeloTablaStats = new DefaultTableModel();
	private static JPanel pnlCentral = new JPanel();
	private static JButton btnTablaStats = new JButton("Tabla Estadísticas");
	private static JButton btnTablaPartida = new JButton("Tabla Partida");
	private static JButton btnUsuario = new JButton("Tabla Usuario");

	public static void main(String[] args) {
		
		Conexion c = new Conexion();
		
		try {
			conn = c.conectar();
			statement = conn.createStatement();
			
			//Primero crear las tablas desde aquí:
			
			
			//Datos para tabla Estadísticas
			int i = 0;
			Random r = new Random();
			statement.executeUpdate("delete from estadisticas");
			/**
			 * Elimino lo todos los datos de la tabla
			 * hasta cargar los oficiales
			 */
			while (i != 20) {
				
				//Datos de prueba para tabla estadísticas:
				int num_partidas_mes = r.nextInt(500)+30;
				int num_jugadores_reales = r.nextInt(2500);
				int num_npcs = 3000-num_jugadores_reales;
				int duracion_partida = r.nextInt(3)+1;
				int miss_scarlet = r.nextInt(500)+50;
				int colonel_Mustard = r.nextInt(500)+50;
				int mrs_white = r.nextInt(500)+50;
				int mr_green = r.nextInt(500)+50;
				int mrs_peacock = r.nextInt(500)+50;
				int profesor_plum = r.nextInt(500)+50;
				String sent = "insert into estadisticas values (" + num_partidas_mes + ", " + num_jugadores_reales
						+ ", " + num_npcs + ", " + duracion_partida + ", " + miss_scarlet + ", " + colonel_Mustard
						+ ", " + mrs_white + ", " + mr_green + ", " + mrs_peacock + ", " + profesor_plum + ")";
				System.out.println(sent);
				statement.executeUpdate(sent);
				
				//Datos de prueba para tabla partida:
				int id_partida = r.nextInt(100+1);
				String fechaAleatoria = "2023-11-13";
		        int duracion_p = r.nextInt(3+1);
		        String ganador = "ganador";
		        int jugadores = r.nextInt(6+1);
		        sent = "insert into partida values (" + id_partida + ", " + fechaAleatoria + ", " + 
		        duracion_p + ", " + ganador + ", " + jugadores + ")";
		        System.out.println(java.sql.Date.valueOf(fechaAleatoria));
		        System.out.println(sent);
		        statement.executeUpdate(sent);
		        
		        //Datos de prueba para tabla Jugador:
		        int id_jugador = r.nextInt(50+1);
		        //____________________________________SEGUIR AQUI
				i ++;
			}
			
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
//					rs.close();
//					statement.close();
//					conn.close();
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
