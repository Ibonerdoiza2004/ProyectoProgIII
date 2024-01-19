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
		carta1.setIcon(acusacion.get(0).getFoto());
		carta1.setBounds(0, 0 ,(int)pnlIzquierda.getWidth(), (int)pnlIzquierda.getHeight());
		pnlIzquierda.add(carta1);
		
		
		carta2 = new JLabel();
		carta2.setIcon(acusacion.get(1).getFoto());
		carta2.setBounds(0, 0,(int)pnlIzquierda.getWidth()*6/19, (int)pnlIzquierda.getHeight()*4/19);
		pnlIzquierda.add(carta2);
		
		carta3 = new JLabel();
		carta3.setIcon(Gestion.acusacion.get(2).getFoto());
		carta3.setBounds((int)pnlIzquierda.getWidth()*2/17, (int)(pnlIzquierda.getHeight()*11/19) ,(int)pnlIzquierda.getWidth()*6/19, (int)pnlIzquierda.getHeight()*4/19);
		pnlIzquierda.add(carta3);
		
		continuar = new JButton("CONTINUAR");
		continuar.setBounds(0, (int)(Gestion.sizePantalla.getHeight()*16/19) ,(int)Gestion.sizePantalla.getWidth()/2, (int)Gestion.sizePantalla.getHeight()*3/19);
		pnlDerecha.add(continuar);
		
		continuar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Gestion.aumentarTurno();
				new VentanaTexto("TURNO DE "+Gestion.jugadores.get(Gestion.getNumTurno()).getPersonaje().getNombre().toString().toUpperCase(),Gestion.getNumTurno());
				eliminarPanel();
				String lockSiguienteVentana = "siguienteVentana";
				synchronized (lockSiguienteVentana) {
					try {
						lockSiguienteVentana.wait();
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
				new VentanaTablero();
				eliminarPanel();
			}
		});
		
		for (int i=0; i<acusacion.size(); i++) {
			JLabel label = new JLabel();
			if (dadas.containsKey(acusacion.get(i))) {
				Personaje personaje = dadas.get(acusacion.get(i)).getPersonaje();
				NombrePersonaje nombre= personaje.getNombre();
				label.setIcon(new ImageIcon(Gestion.sprites.get(nombre).get(TipoSprite.AndarAbajo).get(0)));
				if (i==0) {
					label.setBounds((int)Math.round(pnlIzquierda.getWidth()*3/4), (int)(Gestion.sizePantalla.getHeight()*2/19) ,(int)Gestion.sizePantalla.getWidth()*6/19, (int)Gestion.sizePantalla.getHeight()*4/19);
					
				} else if (i== 1) {
					label.setBounds((int)pnlIzquierda.getWidth()*9/17, (int)(Gestion.sizePantalla.getHeight()*8/19) ,(int)Gestion.sizePantalla.getWidth()*6/19, (int)Gestion.sizePantalla.getHeight()*4/19);
					
				} else {
					label.setBounds((int)pnlIzquierda.getWidth()*9/17, (int)(Gestion.sizePantalla.getHeight()*14/19) ,(int)Gestion.sizePantalla.getWidth()*6/19, (int)Gestion.sizePantalla.getHeight()*4/19);
					
				}
			} else {
				
				label.setIcon(new ImageIcon("tachado.png"));
				if (i==0) {
					label.setBounds((int)pnlIzquierda.getWidth()*9/17, (int)(Gestion.sizePantalla.getHeight()*2/19) ,(int)Gestion.sizePantalla.getWidth()*6/19, (int)Gestion.sizePantalla.getHeight()*4/19);
					
				} else if (i== 1) {
					label.setBounds((int)pnlIzquierda.getWidth()*9/17, (int)(Gestion.sizePantalla.getHeight()*8/19) ,(int)Gestion.sizePantalla.getWidth()*6/19, (int)Gestion.sizePantalla.getHeight()*4/19);
					
				} else {
					label.setBounds((int)pnlIzquierda.getWidth()*9/17, (int)(Gestion.sizePantalla.getHeight()*14/19) ,(int)Gestion.sizePantalla.getWidth()*6/19, (int)Gestion.sizePantalla.getHeight()*4/19);
					
				}
			}
		}
		
//		Gestion.ventanaJuego.add(this);
//		revalidate();
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
