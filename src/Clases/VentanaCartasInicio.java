package Clases;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaCartasInicio extends JPanel{
	private JPanel pCartasPoseidas = new JPanel(null);
	private JPanel pBotones = new JPanel(null);
	private JPanel pLista = new JPanel(null);
	private int anchoCartaPoseida = 0;
	private int altoCartaPoseida = 0;
	int numeroJugador;
	private ImageIcon recuadroCarta = null;
	
	public VentanaCartasInicio(int numJugador) {
		setVisible(false);
		Gestion.ventanaJuego.add(this);
		numeroJugador = numJugador;
		setLayout(null);
		setBounds(0, 0, (int)Gestion.sizePantalla.getWidth(),(int) Gestion.sizePantalla.getHeight());
		setVisible(false);
		int anchoBotones = 600;
		pCartasPoseidas.setBounds(0, (int)Gestion.sizePantalla.getHeight()/5, (int)Gestion.sizePantalla.getWidth()/2, 3*(int)Gestion.sizePantalla.getHeight()/5-150 );
		
		
		
	    ImageIcon cartaPedida= Gestion.jugadores.get(numeroJugador).cartas.get(0).getFoto(); //hay que añadir fotos a todos los posibles implicados de la clase asesinato
	    Image imagenCarta = cartaPedida.getImage();
	    
	    int altoCarta = 3*(int)Gestion.sizePantalla.getHeight()/5-150;
		int anchoCarta = (int) ((double) imagenCarta.getWidth(null) / (2*imagenCarta.getHeight(null) * altoCarta));
		if (anchoCarta>(int)Gestion.sizePantalla.getWidth()/(2*(Gestion.jugadores.get(numeroJugador).cartas.size()+1))) {
			anchoCarta = (int)Gestion.sizePantalla.getWidth()/(2*(Gestion.jugadores.get(numeroJugador).cartas.size()+1));
			altoCarta = (int) ((double) imagenCarta.getHeight(null) / imagenCarta.getWidth(null) * anchoCarta);
		}
		
		
	    Jugador jugador = Gestion.jugadores.get(numeroJugador);//
	    
	    ImageIcon cartaPoseida = jugador.cartas.get(0).getFoto();
	   
		Image imagenCartaPoseida = cartaPoseida.getImage();
	    
	    altoCartaPoseida = pCartasPoseidas.getHeight()-150;
	    
		anchoCartaPoseida = (int) ((double) imagenCartaPoseida.getWidth(null) / imagenCartaPoseida.getHeight(null) * altoCartaPoseida);
		if (anchoCartaPoseida>pCartasPoseidas.getWidth()/(jugador.cartas.size()+1)) {
			anchoCartaPoseida = pCartasPoseidas.getWidth()/(jugador.cartas.size()+1);
			altoCartaPoseida = (int) ((double) imagenCartaPoseida.getHeight(null) / imagenCartaPoseida.getWidth(null) * anchoCartaPoseida);
		}
		int espacioEntreCartasPoseidas = (pCartasPoseidas.getWidth()-anchoCartaPoseida*jugador.cartas.size())/(jugador.cartas.size()+1);

		pBotones.setBounds((int)Gestion.sizePantalla.getWidth()/4-anchoBotones/2, (int)(9*Gestion.sizePantalla.getHeight()/10)-150, anchoBotones-espacioEntreCartasPoseidas, 150);
		JButton bContinuar = new JButton("Continuar");
		bContinuar.setFocusable(false);
		bContinuar.setFont(new Font("Serif", Font.BOLD, 33));
		bContinuar.setHorizontalAlignment(JLabel.CENTER);
		bContinuar.setBounds(0, 0, pBotones.getWidth(), pBotones.getHeight());
		pBotones.add(bContinuar);
		
		ArrayList<JLabel>labelCartas = new ArrayList<>();
		ArrayList<ArrayList<Integer>>coordsCartas = new ArrayList<>();
		for (int i = 0; i <jugador.cartas.size();i++) {
			
			cartaPoseida=jugador.cartas.get(i).getFoto();
			
	    	
		    imagenCartaPoseida = cartaPoseida.getImage();
		    imagenCartaPoseida = imagenCartaPoseida.getScaledInstance(anchoCartaPoseida, altoCartaPoseida, Image.SCALE_SMOOTH);
		    cartaPoseida = new ImageIcon(imagenCartaPoseida);
			JLabel lCartaPoseida = new JLabel();
			lCartaPoseida.setIcon(cartaPoseida);
			int x = i*anchoCartaPoseida+espacioEntreCartasPoseidas*(i+1);
			int y = (pCartasPoseidas.getHeight()-imagenCartaPoseida.getHeight(null))/2;
			ArrayList<Integer>coordsActual = new ArrayList<>();
			coordsActual.add(x);
			coordsActual.add(y);
			coordsCartas.add(coordsActual);
			
		    recuadroCarta = new ImageIcon(new ImageIcon(getClass().getResource("recuadroCarta.png")).getImage().getScaledInstance(anchoCartaPoseida, altoCartaPoseida, Image.SCALE_SMOOTH));
			JLabel lRecuadroCartaPoseida = new JLabel();
			lRecuadroCartaPoseida.setIcon(recuadroCarta);
			lRecuadroCartaPoseida.setBounds(x, y, anchoCartaPoseida,altoCartaPoseida);
			pCartasPoseidas.add(lRecuadroCartaPoseida);
			
			lCartaPoseida.setIcon(cartaPoseida);
			lCartaPoseida.setBounds(x, y, anchoCartaPoseida,altoCartaPoseida);
			pCartasPoseidas.add(lCartaPoseida);
			labelCartas.add(lCartaPoseida);
			
	    }
	    JLabel lPoseidas = new JLabel("Cartas en posesión");
		lPoseidas.setFont(new Font("Serif", Font.BOLD, 33));
		lPoseidas.setHorizontalAlignment(JLabel.CENTER);
		lPoseidas.setVerticalAlignment(JLabel.CENTER);
		lPoseidas.setBounds(pCartasPoseidas.getWidth()/2-150, 0 , 300, pCartasPoseidas.getY());
		add(pCartasPoseidas);
		pLista.setBounds((int)(Gestion.sizePantalla.getWidth()/2), 0, (int)(Gestion.sizePantalla.getWidth()/2) ,(int)Gestion.sizePantalla.getHeight());
		pLista.add(new Lista(pLista, numeroJugador).sPane);
		add(pLista);
		add(lPoseidas);
		add(pBotones);
		
		bContinuar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					Thread t = new Thread(new Runnable() {
						
						@Override
						public void run() {
							pLista.setFocusable(false);
							bContinuar.setEnabled(false);
							numeroJugador = (numeroJugador+1)%Gestion.jugadores.size();
							while(Gestion.jugadores.get(numeroJugador).npc&&numeroJugador!=Gestion.getNumTurno()) {
								numeroJugador = (numeroJugador+1)%Gestion.jugadores.size();
							}
							if(Gestion.jugadores.get(numeroJugador)!=Gestion.jugadores.get(Gestion.getNumTurno())) {
								new VentanaTexto("TURNO DE "+Gestion.jugadores.get(numeroJugador).getPersonaje().getNombre().toString().toUpperCase(),numeroJugador);
								eliminarPanel();
								VentanaCartasInicio v = new VentanaCartasInicio(numeroJugador);
								String lockAnyadirALaVentana = "AnyadirALaVentana";
								synchronized (lockAnyadirALaVentana) {
									try {
										lockAnyadirALaVentana.wait();
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								v.setVisible(true);
							}else {
								new VentanaTexto("TURNO DE "+Gestion.jugadores.get(Gestion.getNumTurno()).getPersonaje().getNombre().toString().toUpperCase(),Gestion.getNumTurno());
								eliminarPanel();
								VentanaTablero v = new VentanaTablero();
							}
						}
					});
					t.start();
				
			}
		});
		
		
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
		ventana.add(new VentanaCartasInicio(0));
		ventana.setVisible(true);
	}
}
