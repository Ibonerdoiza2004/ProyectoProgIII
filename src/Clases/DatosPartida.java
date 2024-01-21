package Clases;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DatosPartida {
	
	private int numTurno;
	private ArrayList<Jugador> jugadoresPartida;
	private ArrayList<Asesinato> acusacion;
	private HashMap<Asesinato, Jugador> cartasEnsenyadas;
	private JPanel siguientePanel;

	public static void main(String[] args) {
		DatosPartida dp = new DatosPartida();
		dp.cargarPartidaJO();
	}
	
	public DatosPartida() { //Aquí vienen los datos de la partida
		
	}
	
	public void cargarPartidaJO() {
        String[] opciones = {"Opción 1", "Opción 2", "Opción 3"};

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
