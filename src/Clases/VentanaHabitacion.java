package Clases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaHabitacion extends JFrame {
	protected String nombreHabitacion = null;
	HashMap<String,ArrayList<ArrayList>>coordsMuebles = null; //Los muebles siempre van a aparecer en la habitación
	HashMap<String,ArrayList<ArrayList>>coordsObjetos = null; //Los objetos que aparezcan serán aleatorios, y algunos de ellos podrán aportar alguna pista sobre el asesinato
	JPanel panelHabitacion;
	public VentanaHabitacion() {
		//hacer un switch de la posicion del jugador, para asignar el nombre de la habitación y las posiciones de los objetos.
		setSize(Gestion.sizePantalla);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		setLayout(null);
		getContentPane().setBackground(Color.black);
		this.nombreHabitacion= "Sala_Rafaela_Ybarra.png";
		Image imagenTablero = (new ImageIcon(getClass().getResource(nombreHabitacion))).getImage();
		int altoPanel = getHeight();
		int anchoPanel = (int) ((double) imagenTablero.getWidth(null) / imagenTablero.getHeight(null) * altoPanel);
		if (anchoPanel>getWidth()) {
			anchoPanel = getWidth();
			altoPanel = (int) ((double) imagenTablero.getHeight(null) / imagenTablero.getWidth(null) * anchoPanel);
		}

		panelHabitacion = new JPanel(null) {
			
			@Override
			protected void paintComponent(Graphics g) {
				
				g.drawImage(imagenTablero, 0, 0, getWidth(), getHeight(), this);
			}
		};
		panelHabitacion.setBounds((getWidth()-anchoPanel)/2,(getHeight()-altoPanel)/2,anchoPanel,altoPanel);
		add(panelHabitacion);
		for(int i=0; i<5;i++) {
			Jugador jugador = new Jugador(new Personaje(), false);
			do {
			jugador.posicion=new int[] {(int)(Math.random()*24),(int)(Math.random()*24)};
			}while(Gestion.tablero.get(jugador.posicion[0]).get(jugador.posicion[1])!=1);
			Gestion.jugadores.add(jugador);//Borrar cuando ya esté añadida la funcionalidad final
		}
		Jugador jugador = Gestion.jugadores.get(Gestion.getNumTurno());
		ImageIcon sprite = new ImageIcon(Gestion.sprites.get(jugador.getPersonaje().getNombre()).get(TipoSprite.AndarAbajo).get(0));
		JLabel labelJugador =new JLabel();
		labelJugador.setIcon(sprite);
		labelJugador.setBounds(anchoPanel/10, altoPanel/2-sprite.getIconHeight(), labelJugador.getIcon().getIconWidth(), labelJugador.getIcon().getIconHeight());
		panelHabitacion.add(labelJugador);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new VentanaHabitacion();
	}
}
