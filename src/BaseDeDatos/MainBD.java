package BaseDeDatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainBD {

	public static void main(String[] args) {
		
		Conexion c = new Conexion();
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			conn = c.conectar();
			statement = conn.createStatement();
			rs = statement.executeQuery("select * from usuario");
			
			while (rs.next()) {
				int id = rs.getInt(1);
				String nomUsuario = rs.getString(2);
				String clave = rs.getString(3);
				
				System.out.println(id + " - " + nomUsuario + " - " + clave);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
//				if (conn != null) {
//					conn.close();
//				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
