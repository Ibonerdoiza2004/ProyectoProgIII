package Clases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaHabitacion extends JFrame {
	protected String nombreHabitacion = null;
	HashMap<String,ArrayList<ArrayList>>coordsMuebles = null; //Los muebles siempre van a aparecer en la habitación
	HashMap<String,ArrayList<ArrayList>>coordsObjetos = null; //Los objetos que aparezcan serán aleatorios, y algunos de ellos podrán aportar alguna pista sobre el asesinato
	JPanel panelHabitacion;
	int posX;
	int posY;
	int j=0;
	int foto = 0;
	ImageIcon sprite = null;
	TipoSprite direccion = TipoSprite.AndarAbajo;
	public VentanaHabitacion() {
		//hacer un switch de la posicion del jugador, para asignar el nombre de la habitación y las posiciones de los objetos.
		setSize(Gestion.sizePantalla);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		setLayout(null);
		getContentPane().setBackground(Color.black);
		this.nombreHabitacion= "Aseo.png";
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
		sprite = new ImageIcon(Gestion.sprites.get(jugador.getPersonaje().getNombre()).get(TipoSprite.AndarAbajo).get(0));
		JLabel labelJugador =new JLabel();
		labelJugador.setIcon(sprite);
		posX = anchoPanel/10; 
		posY = altoPanel/2-sprite.getIconHeight();
		labelJugador.setBounds(posX,posY, labelJugador.getIcon().getIconWidth(), labelJugador.getIcon().getIconHeight());
		
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_LEFT){
					posX= posX-4;
					if(direccion==TipoSprite.AndarIzquierda) {
						j = (j+1)%2;
					}else {
						direccion = TipoSprite.AndarIzquierda;
						j=0;
						foto=0;
					}
				}else if(e.getKeyCode()==KeyEvent.VK_UP){
					posY =posY-4;
					if(direccion==TipoSprite.AndarArriba) {
						j = (j+1)%2;
					}else {
						direccion = TipoSprite.AndarArriba;
						j=0;
						foto=0;
					}
				}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
					posX = posX+4;
					if(direccion==TipoSprite.AndarDerecha) {
						j = (j+1)%2;
					}else {
						direccion = TipoSprite.AndarDerecha;
						j=0;
						foto=0;
					}
				}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
					posY = posY+4;
					if(direccion==TipoSprite.AndarAbajo) {
						j = (j+1)%2;
					}else {
						direccion = TipoSprite.AndarAbajo;
						j=0;
						foto=0;
					}
				}else {
					direccion = null;
				}
				if(j==0&&direccion!=null) {
					if ((direccion ==TipoSprite.AndarAbajo||direccion==TipoSprite.AndarArriba)&&(foto==0)) {
						foto++;
					}
					sprite = new ImageIcon(Gestion.sprites.get(jugador.getPersonaje().getNombre()).get(direccion).get(foto));
					labelJugador.setIcon(sprite);
					foto = (foto+1)%Gestion.sprites.get(jugador.getPersonaje().getNombre()).get(direccion).size();
				}
				labelJugador.setBounds(posX,posY, labelJugador.getIcon().getIconWidth(), labelJugador.getIcon().getIconHeight());
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				foto = 0;
				j=0;
				sprite = new ImageIcon(Gestion.sprites.get(jugador.getPersonaje().getNombre()).get(direccion).get(foto));
				labelJugador.setIcon(sprite);
				
			}
		});
		panelHabitacion.add(labelJugador);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new VentanaHabitacion();
	}
}
