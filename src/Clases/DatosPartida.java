package Clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

public class DatosPartida implements Serializable {
	
	private static final String nomFichero = "partidasGuardadas.dat";
			
	//private DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	private String identificadorPartida; //Se va a identificar con la fecha en la que se guardó
	private int numTurno;
	private ArrayList<Jugador> jugadoresPartida;
	private ArrayList<Asesinato> acusacion;
	private HashMap<Asesinato, Jugador> cartasEnsenyadas;
	private Class siguientePanel;
	private String ventanaTexto;
	private int ventanaTextoInt;
	private Contenedor contenedor;
	private JDesktopPane dPane;

//	public static void main(String[] args) {
//		DatosPartida dp = new DatosPartida(0, null, null, null, null);
//		//dp.guardarPartida(new DatosPartida(3, null, null, null, null);
//		dp.cargarPartidaJO();
//	}
	
	public DatosPartida() {
		identificadorPartida = "";
	}
	
	public DatosPartida(int numturno, ArrayList<Jugador> jugadores,ArrayList<Asesinato>acusacion,HashMap<Asesinato,Jugador>cartasEnsenyadas, Class siguientePanel,  String nombrePartida, String ventanaTexto, int ventanaTextoInt, Contenedor contenedor, JDesktopPane dPane) { //Aquí vienen los datos desde Gestión
		numTurno = numturno;
		jugadoresPartida = jugadores;
		this.acusacion=acusacion;
		this.cartasEnsenyadas=cartasEnsenyadas;
		this.siguientePanel=siguientePanel;
        //LocalDateTime fechaHoraActual = LocalDateTime.now();
        //identificadorPartida = nombrePartida + " " + fechaHoraActual.format(formateador);
        identificadorPartida = nombrePartida;
        this.ventanaTexto=ventanaTexto;
        this.ventanaTextoInt=ventanaTextoInt;
        this.contenedor=contenedor;
        this.dPane=dPane;
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

	public Class getSiguientePanel() {
		return siguientePanel;
	}

	public void setSiguientePanel(Class siguientePanel) {
		this.siguientePanel = siguientePanel;
	}
	
	public String getVentanaTexto() {
		return ventanaTexto;
	}

	public void setVentanaTexto(String ventanaTexto) {
		this.ventanaTexto = ventanaTexto;
	}

	public int getVentanaTextoInt() {
		return ventanaTextoInt;
	}

	public void setVentanaTextoInt(int ventanaTextoInt) {
		this.ventanaTextoInt = ventanaTextoInt;
	}

	public Contenedor getContenedor() {
		return contenedor;
	}

	public void setContenedor(Contenedor contenedor) {
		this.contenedor = contenedor;
	}

	public JDesktopPane getdPane() {
		return dPane;
	}

	public void setdPane(JDesktopPane dPane) {
		this.dPane = dPane;
	}

	public static void guardarPartida(DatosPartida dp) {
		File f = new File(nomFichero);
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		ArrayList<DatosPartida>partidas = new ArrayList<>();
		try {
			fis = new FileInputStream(f);
			ois= new ObjectInputStream(fis);
			Object partida = ois.readObject();
			while(partida!=null) {
				if(partida instanceof DatosPartida) {
					partidas.add((DatosPartida)partida);
				}
				partida = ois.readObject();
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ois!=null) {
					ois.close();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		partidas.add(dp);
		try {
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);
			for(int i= 0;i<partidas.size();i++) {
				oos.writeObject(partidas.get(i));
			}
			JOptionPane.showMessageDialog(null, "Se han guardado los datos");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se han guardado los datos correctamente");
		}finally {
			if(oos!=null) {
				try {
					if(oos!=null) {
						oos.close();
					}
				} catch (IOException e) {
				}
			}
		}
	}
	
	public static DatosPartida cargarPartidaJO() {
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
		}finally {
			try {
				if(ois!=null) {
					ois.close();
				}
				
			} catch (IOException e) {
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
            return partidas.get(opcionSeleccionadaIndex);
        } else {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
        }
		return null;
	}

	public static void borrarPartida() {
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
		}finally {
			try {
				if(ois!=null) {
					ois.close();
				}
				
			} catch (IOException e) {
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
            comboBox.remove(opcionSeleccionadaIndex);
            partidas.remove(opcionSeleccionadaIndex);
            JOptionPane.showMessageDialog(null, "Has seleccionado: " + comboBox.getItemAt(opcionSeleccionadaIndex));
            FileOutputStream fos = null;
    		ObjectOutputStream oos = null;
    		try {
    			fos = new FileOutputStream(file);
    			oos = new ObjectOutputStream(fos);
    			for(int i= 0;i<partidas.size();i++) {
    				oos.writeObject(partidas.get(i));
    			}
    			JOptionPane.showMessageDialog(null, "Borrado con éxito");
    		} catch (IOException e) {
    			JOptionPane.showMessageDialog(null, "No se han guardado los datos correctamente");
    		}finally {
    			if(oos!=null) {
    				try {
    					if(oos!=null) {
    						oos.close();
    					}
    				} catch (IOException e) {
    				}
    			}
    		}
        } else {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
        }
	}
	
}
