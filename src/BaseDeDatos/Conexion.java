package BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver"; //Este String es exclusivo para MySQL
	private static final String URL = "jdbc:mysql://localhost:3306/bd_proyecto_prog";
	private static final String USUARIO = "root";
	private static final String CLAVE = "***********";
	
	static {
		try {
			Class.forName(CONTROLADOR);
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}
	
	public Connection conectar() {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");
			
		} catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}
		
		return conn;
	}
	
//	public static void main(String[] args) {
//		Conexion c = new Conexion();
//		c.conectar();
//	}

}
