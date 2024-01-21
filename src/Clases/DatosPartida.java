package Clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.tools.FileObject;

public class DatosPartida implements Serializable {
	
	private static final String nomFichero = "partidasGuardadas";
			
	//private DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	private ArrayList<DatosPartida> partidasGuardadas = new ArrayList<DatosPartida>();
	
	private String identificadorPartida; //Se va a identificar con la fecha en la que se guardó
	private int numTurno;
	private ArrayList<Jugador> jugadoresPartida;
	private ArrayList<Asesinato> acusacion;
	private HashMap<Asesinato, Jugador> cartasEnsenyadas;
	private JPanel siguientePanel;

	public static void main(String[] args) {
		DatosPartida dp = new DatosPartida(0, null, null, null, new JPanel());
		dp.guardarPartida(new DatosPartida(3, null, null, null, new JPanel()));
		dp.cargarPartidaJO();
	}
	
	public DatosPartida(int numturno, ArrayList<Jugador> jugadores, ArrayList<Asesinato> acus, HashMap<Asesinato, Jugador> cartasensenyadas, JPanel sigPanel) { //Aquí vienen los datos desde Gestión
		numTurno = numturno;
		jugadoresPartida = jugadores;
		acusacion = acus;
		cartasEnsenyadas = cartasensenyadas;
		siguientePanel = sigPanel;
		
        //LocalDateTime fechaHoraActual = LocalDateTime.now();
        String nombrePartida = JOptionPane.showInputDialog("Ingresa el nombre para guardar la partida:");
        
        //identificadorPartida = nombrePartida + " " + fechaHoraActual.format(formateador);
        identificadorPartida = nombrePartida;
	}
	
	public String getIdentificadorPartida() {
		return identificadorPartida;
	}

	public void setIdentificadorPartida(String identificadorPartida) {
		this.identificadorPartida = identificadorPartida;
	}

	public int getNumTurno() {
		return numTurno;
	}

	public void setNumTurno(int numTurno) {
		this.numTurno = numTurno;
	}

	public ArrayList<Jugador> getJugadoresPartida() {
		return jugadoresPartida;
	}

	public void setJugadoresPartida(ArrayList<Jugador> jugadoresPartida) {
		this.jugadoresPartida = jugadoresPartida;
	}

	public ArrayList<Asesinato> getAcusacion() {
		return acusacion;
	}

	public void setAcusacion(ArrayList<Asesinato> acusacion) {
		this.acusacion = acusacion;
	}

	public HashMap<Asesinato, Jugador> getCartasEnsenyadas() {
		return cartasEnsenyadas;
	}

	public void setCartasEnsenyadas(HashMap<Asesinato, Jugador> cartasEnsenyadas) {
		this.cartasEnsenyadas = cartasEnsenyadas;
	}

	public JPanel getSiguientePanel() {
		return siguientePanel;
	}

	public void setSiguientePanel(JPanel siguientePanel) {
		this.siguientePanel = siguientePanel;
	}

	private void guardarPartida(DatosPartida dp) {
		File f = new File(nomFichero);
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(dp);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cargarPartidaJO() {
		
		ArrayList<String> ids = new ArrayList<String>();
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichero));
			DatosPartida dp = (DatosPartida) ois.readObject();
			while (dp != null) {
				partidasGuardadas.add(dp);
				ids.add(dp.getIdentificadorPartida());
			}
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		String[] opciones = new String[ids.size()];
		for (int i = 0; i < ids.size(); i ++) {
			opciones[i] = ids.get(i);
		}
        JComboBox<String> comboBox = new JComboBox<>(opciones);
        
        int opcionSeleccionada = JOptionPane.showOptionDialog(
                null,
                comboBox,
                "Selecciona la partida a cargar",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                null);

        if (opcionSeleccionada == JOptionPane.OK_OPTION) {
            // Obtener el elemento seleccionado del JComboBox
            Object opcionSeleccionadaTexto = comboBox.getSelectedItem();
            JOptionPane.showMessageDialog(null, "Has seleccionado: " + opcionSeleccionadaTexto);
        } else {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
        }
	}
	
}
