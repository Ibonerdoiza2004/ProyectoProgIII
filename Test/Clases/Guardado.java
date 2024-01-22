package Clases;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
public class Guardado {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGuardarPartida() {
		String nombrePartida;
    	nombrePartida = JOptionPane.showInputDialog("Ingresa el nombre para guardar la partida:");
    	if(nombrePartida==null||nombrePartida.isBlank()) {
    		JOptionPane.showMessageDialog( Gestion.ventanaJuego, "No se ha podido guardar la partida", "Error", JOptionPane.ERROR_MESSAGE);
    	}else {
    		Gestion.datosPartida= new Contenedor();
        	ArrayList<Jugador>jugadores=new ArrayList<Jugador>();
        	jugadores.add(new Jugador(new Personaje(),false));
        	DatosPartida dp = new DatosPartida(0, jugadores,new ArrayList<Asesinato>(), new HashMap<Asesinato, Jugador>(), VentanaTablero.class, nombrePartida, Gestion.ventanaTexto, Gestion.ventanaTextoInt, new Contenedor(),new JDesktopPane());
        	DatosPartida.guardarPartida(dp);
    	}
    	
	}
//	@Test
	public void testCargarPartida() {
		ArrayList<String> ids = new ArrayList<String>();
		File file = new File("partidasGuardadas.dat");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		ArrayList<DatosPartida>partidas = new ArrayList<>();
		try {
			fis = new FileInputStream(file);
			ois= new ObjectInputStream(fis);
			Object partida = ois.readObject();
			while(partida!=null) {
				if(partida instanceof DatosPartida) {
					partidas.add((DatosPartida)partida);
					ids.add(((DatosPartida)partida).getIdentificadorPartida());
				}
				partida = ois.readObject();
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				ois.close();
				for(DatosPartida partida:partidas) {
					System.out.println(partida.getIdentificadorPartida());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
            int opcionSeleccionadaIndex = comboBox.getSelectedIndex();
            JOptionPane.showMessageDialog(null, "Has seleccionado: " + comboBox.getItemAt(opcionSeleccionadaIndex));
        } else {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
        }
		
		
	}

}
