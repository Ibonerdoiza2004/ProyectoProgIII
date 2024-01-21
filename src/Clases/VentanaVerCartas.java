package Clases;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

public class VentanaVerCartas extends JPanel{
	
	protected JPanel pnlIzquierda;
	protected JPanel pnlDerecha;
	protected JLabel jugador1;
	protected JLabel jugador2;
	protected JLabel jugador3;
	protected JLabel carta1;
	protected JLabel carta2;
	protected JLabel carta3;
	protected JButton continuar;
	
	public VentanaVerCartas() {
		setVisible(false);
		this.setLayout(new GridLayout(1,2));
		setSize(Gestion.sizePantalla);
		
		pnlIzquierda = new JPanel();
		pnlIzquierda.setLayout(null);
		
		
		pnlDerecha = new JPanel();
		pnlDerecha.setLayout(null);
		
		this.add(pnlIzquierda);
		this.add(pnlDerecha);
		
		
		JPanel panelLista = new JPanel(null);
		panelLista.setBounds(0, 0, (int)(Gestion.sizePantalla.getWidth()/2) ,(int)Gestion.sizePantalla.getHeight()-150);
		panelLista.add(new Lista(panelLista, Gestion.getNumTurno()).sPane);
		pnlDerecha.add(panelLista);
		
		ArrayList<Asesinato> acusacion = Gestion.acusacion;
		HashMap<Asesinato, Jugador> dadas = Gestion.cartasEnsenyadas;
		
		carta1 = new JLabel();
		ImageIcon imgCarta1 = acusacion.get(0).getFoto();
		Image image1 = imgCarta1.getImage();
		Image newImage1 = image1.getScaledInstance((int)Gestion.sizePantalla.getHeight()*7/34, (int)Gestion.sizePantalla.getHeight()*5/19, Image.SCALE_SMOOTH);
		ImageIcon newIcon1 = new ImageIcon(newImage1);
		carta1.setIcon(newIcon1);
		carta1.setBounds((int)Gestion.sizePantalla.getWidth()*3/34, (int)Gestion.sizePantalla.getHeight()*1/19, (int)Gestion.sizePantalla.getHeight()*7/34, (int)Gestion.sizePantalla.getHeight()*5/19);
		pnlIzquierda.add(carta1);
		
		
		carta2 = new JLabel();
		ImageIcon imgCarta2 = acusacion.get(1).getFoto();
		Image image2 = imgCarta2.getImage();
		Image newImage2 = image2.getScaledInstance((int)Gestion.sizePantalla.getHeight()*7/34, (int)Gestion.sizePantalla.getHeight()*5/19, Image.SCALE_SMOOTH);
		ImageIcon newIcon2 = new ImageIcon(newImage2);
		carta2.setIcon(newIcon2);
		carta2.setBounds((int)Gestion.sizePantalla.getWidth()*3/34, (int)Gestion.sizePantalla.getHeight()*7/19, (int)Gestion.sizePantalla.getHeight()*7/34, (int)Gestion.sizePantalla.getHeight()*5/19);
		pnlIzquierda.add(carta2);
		
		carta3 = new JLabel();
		ImageIcon imgCarta3 = acusacion.get(2).getFoto();
		Image image3 = imgCarta3.getImage();
		Image newImage3 = image3.getScaledInstance((int)Gestion.sizePantalla.getHeight()*7/34, (int)Gestion.sizePantalla.getHeight()*5/19, Image.SCALE_SMOOTH);
		ImageIcon newIcon3 = new ImageIcon(newImage3);
		carta3.setIcon(newIcon3);
		carta3.setBounds((int)Gestion.sizePantalla.getWidth()*3/34, (int)Gestion.sizePantalla.getHeight()*13/19, (int)Gestion.sizePantalla.getHeight()*7/34, (int)Gestion.sizePantalla.getHeight()*5/19);
		pnlIzquierda.add(carta3);
		
		continuar = new JButton("CONTINUAR");
		continuar.setBounds(0, (int)(Gestion.sizePantalla.getHeight())-150 ,(int)Gestion.sizePantalla.getWidth()/2, 150);
		pnlDerecha.add(continuar);
		
		continuar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				continuar.setEnabled(false);
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Gestion.aumentarTurno();
						Gestion.siguientePanel=VentanaTablero.class;
						new VentanaTexto("TURNO DE "+Gestion.jugadores.get(Gestion.getNumTurno()).getPersonaje().getNombre().toString().toUpperCase(),Gestion.getNumTurno());
						eliminarPanel();
						VentanaTablero v = new VentanaTablero();
					}
				});
				t.start();
			}
		});
		
		for (int i=0; i<acusacion.size(); i++) {
			JLabel label = new JLabel();
			if (dadas.containsKey(acusacion.get(i))) {
				Personaje personaje = dadas.get(acusacion.get(i)).getPersonaje();
				NombrePersonaje nombre= personaje.getNombre();
				ImageIcon sprite = new ImageIcon(Gestion.sprites.get(nombre).get(TipoSprite.AndarAbajo).get(0));
				Image image = sprite.getImage();
				Image newImage = image.getScaledInstance(180, 190, Image.SCALE_SMOOTH);
				ImageIcon newIcon = new ImageIcon(newImage);
				label.setIcon(newIcon);
				if (i==0) {
					label.setBounds((int)Gestion.sizePantalla.getWidth()*10/34, (int)Gestion.sizePantalla.getHeight()*1/19, (int)Gestion.sizePantalla.getHeight()*6/34, (int)Gestion.sizePantalla.getHeight()*4/19);
					pnlIzquierda.add(label);
				} else if (i== 1) {
					label.setBounds((int)Gestion.sizePantalla.getWidth()*10/34, (int)Gestion.sizePantalla.getHeight()*7/19, (int)Gestion.sizePantalla.getHeight()*6/34, (int)Gestion.sizePantalla.getHeight()*4/19);
					pnlIzquierda.add(label);
				} else {
					label.setBounds((int)Gestion.sizePantalla.getWidth()*10/34, (int)Gestion.sizePantalla.getHeight()*13/19, (int)Gestion.sizePantalla.getHeight()*6/34, (int)Gestion.sizePantalla.getHeight()*4/19);
					pnlIzquierda.add(label);
				}
			} else {
				ImageIcon tachado = new ImageIcon(getClass().getResource("tachado.png"));
				Image image = tachado.getImage();
				Image newImage = image.getScaledInstance(180, 190, Image.SCALE_SMOOTH);
				ImageIcon newIcon = new ImageIcon(newImage);
				label.setIcon(newIcon);
				if (i==0) {
					label.setBounds((int)Gestion.sizePantalla.getWidth()*10/34, (int)Gestion.sizePantalla.getHeight()*1/19, (int)Gestion.sizePantalla.getHeight()*6/34, (int)Gestion.sizePantalla.getHeight()*4/19);
					pnlIzquierda.add(label);
				} else if (i== 1) {
					label.setBounds((int)Gestion.sizePantalla.getWidth()*10/34, (int)Gestion.sizePantalla.getHeight()*7/19, (int)Gestion.sizePantalla.getHeight()*6/34, (int)Gestion.sizePantalla.getHeight()*4/19);
					pnlIzquierda.add(label);
				} else {
					label.setBounds((int)Gestion.sizePantalla.getWidth()*10/34, (int)Gestion.sizePantalla.getHeight()*13/19, (int)Gestion.sizePantalla.getHeight()*6/34, (int)Gestion.sizePantalla.getHeight()*4/19);
					pnlIzquierda.add(label);
				}
			}
		}
		Gestion.ventanaJuego.add(this);
		revalidate();
	}
	
	public void eliminarPanel() {
		Gestion.ventanaJuego.remove(this);
		Gestion.ventanaJuego.repaint();
	}
	
	public static void main(String[] args) {
		Jugador j1;
		Jugador j2;
		Jugador j3;
		Jugador j4;
		Gestion.jugadores = new ArrayList<>();
		j1 = new Jugador(new Personaje(), true);
		Gestion.jugadores.add(j1);
		j2 = new Jugador(new Personaje(), true);
		Gestion.jugadores.add(j2);
		j3 = new Jugador(new Personaje(), true);
		Gestion.jugadores.add(j3);
		j4 = new Jugador(new Personaje(), true);
		Gestion.jugadores.add(j4);
		Gestion.repartirCartas(Gestion.datosPartida.todasLasCartas);
		Gestion.cartasEnsenyadas=new HashMap<>();
		int i = 0;
		Asesinato cartaJ2 = j2.cartas.get(i);
		while(!(cartaJ2 instanceof Sospechoso)) {
			i++;
			cartaJ2 = j2.cartas.get(i);
		}
		Gestion.cartasEnsenyadas.put(cartaJ2, j2);
		Gestion.cartasEnsenyadas.put(Gestion.datosPartida.armas.get(0), j2);

		Gestion.acusacion=new ArrayList<>();
		Gestion.acusacion.add(cartaJ2);
		Gestion.acusacion.add(Gestion.datosPartida.armas.get(0));
		Gestion.acusacion.add(Gestion.datosPartida.lugares.get(0));
		JFrame ventana = new JFrame();
		ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventana.setUndecorated(true);
		ventana.add(new VentanaVerCartas());
		ventana.setVisible(true);
		
	}

}
