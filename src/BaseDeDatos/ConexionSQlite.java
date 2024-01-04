package BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQlite {
	
	private Connection conn;
	
	//Solo vamos a crear métodos para conectarnos y desconectarnos de la BD:
	
	public Connection connect() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.sqlite.JDBC");
		
		conn = DriverManager.getConnection("jdbc:sqlite:bd_proyecto_prog.bd");
		
		return conn;
	}
	
	public void disconnect() throws SQLException {
		conn.close();
	}

}
