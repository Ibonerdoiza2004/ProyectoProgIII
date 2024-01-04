package Clases;
import java.awt.Image;
import java.util.HashMap;

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
		coordY = 3*alto/40;
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
