package BaseDeDatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Vector;

import javax.swing.JTable;

public class MainBD {

	public static void main(String[] args) {
		
		Conexion c = new Conexion();
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			conn = c.conectar();
			statement = conn.createStatement();
			// rs =...
			
			//Datos para tabla Estadísticas
			int i = 0;
			Random r = new Random();
			while (i != 10) {
				int num_partidas = r.nextInt(500)+30;
				int num_jugadores_reales = r.nextInt(2500+1500);
				int num_npcs = 3000-num_jugadores_reales;
				int duracion_partida = r.nextInt(3+1);
				int miss_scarlet = r.nextInt(500)+50;
				int colonel_Mustard = r.nextInt(500)+50;
				int mrs_white = r.nextInt(500)+50;
				int mr_green = r.nextInt(500)+50;
				int mrs_peacock = r.nextInt(500)+50;
				int profesor_plum = r.nextInt(500)+50;
				String sent = "insert into estadisticas values (" + num_jugadores_reales + ", " + num_jugadores_reales
						+ ", " + num_npcs + ", " + duracion_partida + ", " + miss_scarlet + ", " + colonel_Mustard
						+ ", " + mrs_white + ", " + mr_green + ", " + mrs_peacock + ", " + profesor_plum + ")";
				System.out.println(sent);
				statement.executeUpdate(sent);
				i ++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		try {
//			conn = c.conectar();
//			statement = conn.createStatement();
//			rs = statement.executeQuery("select * from usuario");
//			
//			while (rs.next()) {
//				int id = rs.getInt(1);
//				String nomUsuario = rs.getString(2);
//				String clave = rs.getString(3);
//				
//				System.out.println(id + " - " + nomUsuario + " - " + clave);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (statement != null) {
//					statement.close();
//				}
////				if (conn != null) {
////					conn.close();
////				}
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
	}
	
	private static JTable tablaEstadisticas;
	
	private static void cargarTablaEstadisticas() {
		Vector<String> cabeceras = new Vector<String>();
		cabeceras.add("NUM_PARTIDAS"); cabeceras.add("JUGADORES_REALES"); cabeceras.add("");
	}
	
}
