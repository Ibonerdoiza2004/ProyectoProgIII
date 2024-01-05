package Clases;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class VentanaMoverCartas extends JPanel{
	int coordX;
	int coordY;
	int yAbajo;
	int ancho;
	int alto;
	public VentanaMoverCartas() {
		setBounds(0, 0, (int)Gestion.sizePantalla.getWidth(), (int)Gestion.sizePantalla.getHeight());
		setLayout(null);
		Image imagenAcusador = Gestion.sprites.get(NombrePersonaje.Amarillo/*Gestion.jugadores.get(Gestion.getNumTurno())*/).get(TipoSprite.AndarAbajo).get(0);
		alto = (int)Gestion.sizePantalla.getHeight()/5;//sprite: 2*getHeight/10-> carta: 3*getHeight/10
		ancho = imagenAcusador.getWidth(null)*alto/imagenAcusador.getHeight(null);
		yAbajo = ((int)(37.0*Gestion.sizePantalla.getHeight()/40.0)-alto);
		imagenAcusador = imagenAcusador.getScaledInstance(ancho, alto, java.awt.Image.SCALE_SMOOTH);
		ImageIcon iconAcusador = new ImageIcon(imagenAcusador);
		JLabel lAcusador = new JLabel();
		lAcusador.setIcon(iconAcusador);
		coordX =(int) Gestion.sizePantalla.getWidth()/2-ancho/2;
		coordY = (int)(3*Gestion.sizePantalla.getHeight()/40);
		lAcusador.setBounds(coordX, coordY, ancho, alto);
		add(lAcusador);
		for(int i = 1; i<5/*Gestion.jugadores.size()*/;i++) {
//			Image spritePersonaje = Gestion.sprites.get(Gestion.jugadores.get((i+Gestion.getNumTurno())%Gestion.jugadores.size()).getPersonaje().getNombre()).get(TipoSprite.AndarAbajo).get(0);
			Image spritePersonaje = Gestion.sprites.get(NombrePersonaje.Morado).get(TipoSprite.AndarAbajo).get(0);
			spritePersonaje = spritePersonaje.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		    ImageIcon iconSprite = new ImageIcon(spritePersonaje);
			JLabel lSprite = new JLabel();
			lSprite.setIcon(iconSprite);
			int espacio = (int) (Gestion.sizePantalla.getWidth()-ancho*4/*(Gestion.jugadores.size()-1)*/)/5/*(Gestion.jugadores.size())*/;
			int x = (i-1)*ancho+ espacio*(i);
			lSprite.setBounds(x, yAbajo, ancho, alto);
			add(lSprite);
		}
		int altoCarta = (int) (3*Gestion.sizePantalla.getHeight()/10);
		int anchoCarta = Gestion.datosPartida.armas.get(0).getFoto().getIconWidth()*altoCarta/Gestion.datosPartida.armas.get(0).getFoto().getIconHeight();
		ArrayList<JLabel>cartas = new ArrayList<>();
		for(int i = 1; i<5/*Gestion.jugadores.size()*/;i++) {
			if (true/*Gestion.cartasEnsenyadas.containsValue(Gestion.jugadores.get(i))*/) {
				JLabel lcarta = new JLabel();
				ImageIcon carta = new ImageIcon(/*Gestion.acusacion.get(i).getFoto()*/Gestion.datosPartida.sospechosos.get(1).getFoto().getImage().getScaledInstance(anchoCarta, altoCarta, java.awt.Image.SCALE_SMOOTH));
				lcarta.setIcon(carta);
				int espacio = (int) (Gestion.sizePantalla.getWidth()-ancho*4/*(Gestion.jugadores.size()-1)*/)/5/*(Gestion.jugadores.size())*/;
				int x = (i-1)*ancho+ espacio*(i)+ancho/2-carta.getIconWidth()/2;
				int y = (int) (17*Gestion.sizePantalla.getHeight()/40);
				lcarta.setBounds(x, y, carta.getIconWidth(), carta.getIconHeight());
				add(lcarta);
				cartas.add(lcarta);
			}
		}
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Falta hacer que se muevan las cartas
		
	}
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(Gestion.sizePantalla);
		f.setLayout(null);
		f.setUndecorated(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new VentanaMoverCartas());
		f.setVisible(true);
		
	}
}
