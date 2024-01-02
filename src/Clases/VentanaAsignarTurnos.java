package Clases;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaAsignarTurnos extends JPanel{
	JPanel panelIzquierda;
	JLabel izquierdaArriba;
	VentanaDado panelDados;
	int mayorValor = 0;
	int jugadorMayor;
	ArrayList<HashMap<String,Integer>>coordsPosTurno = new ArrayList<>();
	JLabel lComienza;
	JPanel panelDerecha = new JPanel(null);
	int anchoSprite;
	int altoSprite;
	public VentanaAsignarTurnos() {
		setSize(Gestion.sizePantalla);
		setLayout(new GridLayout(1,2));
		panelIzquierda = new JPanel(null);
		izquierdaArriba = new JLabel("");
		izquierdaArriba.setOpaque(false);
		izquierdaArriba.setHorizontalAlignment(JLabel.CENTER);
		izquierdaArriba.setFont(new Font("Serif", Font.BOLD, 50));
		izquierdaArriba.setForeground(Color.YELLOW);
		izquierdaArriba.setBounds(0, 0, (int) Gestion.sizePantalla.getWidth()/2, 80);
		panelIzquierda.add(izquierdaArriba);
		add(panelIzquierda);
		add(panelDerecha);
		
		for(Jugador jugador:Gestion.jugadores) {
			System.out.println(jugador.personaje.getNombre());
		}
		Image spritePersonaje = Gestion.sprites.get(Gestion.jugadores.get(0).getPersonaje().getNombre()).get(TipoSprite.AndarAbajo).get(0);
	    
	    altoSprite = (int)Gestion.sizePantalla.getHeight()/(Gestion.jugadores.size()+1);
	    
		anchoSprite = (int) ((double) spritePersonaje.getWidth(null) / spritePersonaje.getHeight(null) * altoSprite);
		
		int espacioEntreSprites = ((int)Gestion.sizePantalla.getHeight()-altoSprite*Gestion.jugadores.size())/(Gestion.jugadores.size()+1);
		
		for(int i = 0; i<Gestion.jugadores.size();i++) {
			spritePersonaje = Gestion.sprites.get(Gestion.jugadores.get(i).getPersonaje().getNombre()).get(TipoSprite.AndarAbajo).get(0);;
		    spritePersonaje = spritePersonaje.getScaledInstance(anchoSprite, altoSprite, Image.SCALE_SMOOTH);
		    ImageIcon iconSprite = new ImageIcon(spritePersonaje);
			JLabel lSprite = new JLabel();
			lSprite.setIcon(iconSprite);
			int x = ((int)Gestion.sizePantalla.getWidth()/5)-anchoSprite/2;
			int y = i*altoSprite+espacioEntreSprites*(i+1);
			HashMap<String,Integer>mapa= new HashMap<>();
			mapa.put("x", (x+anchoSprite));
			mapa.put("y", y);
			coordsPosTurno.add(mapa);
			lSprite.setBounds(x, y, anchoSprite, altoSprite);
			panelDerecha.add(lSprite);
		}
		lComienza = new JLabel("COMIENZA");
		lComienza.setFont(new Font("Serif", Font.BOLD, 33));
		lComienza.setOpaque(false);
		panelDerecha.add(lComienza);
		lComienza.setBounds(0,0,0,0);
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				panelDados = new VentanaDado((int) Gestion.sizePantalla.getWidth()/2, (int) Gestion.sizePantalla.getHeight(), 50, true);
				panelDados.setBounds(0,0,(int) Gestion.sizePantalla.getWidth()/2,(int) Gestion.sizePantalla.getHeight());
				panelIzquierda.add(panelDados);
				panelIzquierda.repaint();
				ArrayList<Integer>jugadores = new ArrayList<>();
				for (int i = 0; i<Gestion.jugadores.size();i++) {
					jugadores.add(i);
				}
				comienza(jugadores);
				
			}
		});
		
		t.start();
		Gestion.ventanaJuego.add(this);
		
	}
	public void comienza(ArrayList<Integer>jugadores) {
		mayorValor=0;
		ArrayList<Integer>nuevosJugadores = new ArrayList<>();
		ArrayList<JLabel>empatados = new ArrayList<>();
		HashMap<Integer,JLabel>resultados = new HashMap<>();
		
		for(Integer i : jugadores) {
			izquierdaArriba.setText("Turno de "+Gestion.jugadores.get(i).getPersonaje().getNombre());
			String lock = "lock";
			synchronized (lock) {
				 try {
		                lock.wait();
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
			}
			System.out.println(panelDados.valorDado1+panelDados.valorDado2);
			if(nuevosJugadores.size()==1&&jugadores.get(jugadores.size()-1)==i&&i!=mayorValor) {
				panelDados.reutilizar=false;
			}
			while(panelDados.enEjecucion.get()) {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			panelDados.enEjecucion.set(true);
			if(panelDados.valorDado1+panelDados.valorDado2>mayorValor) {
				nuevosJugadores.clear();
				nuevosJugadores.add(i);
				for(JLabel empatado:empatados) {
					panelDerecha.remove(empatado);
				}
				empatados.clear();
				mayorValor = panelDados.valorDado1+panelDados.valorDado2;
				jugadorMayor = i+1;
				lComienza.setBounds(coordsPosTurno.get(i).get("x"),coordsPosTurno.get(i).get("y")+altoSprite/2-33, 200,200);
			}else if (panelDados.valorDado1+panelDados.valorDado2==mayorValor) {
				nuevosJugadores.add(i);
				jugadorMayor = -1;
				if(nuevosJugadores.size()==2) {
					JLabel mayor1 = new JLabel("EMPATE");
					mayor1.setFont(new Font("Serif", Font.BOLD, 33));
					mayor1.setOpaque(false);
					mayor1.setBounds(coordsPosTurno.get(nuevosJugadores.get(0)).get("x"),coordsPosTurno.get(nuevosJugadores.get(0)).get("y")+altoSprite/2-33, 200,200);
					panelDerecha.add(mayor1);
					JLabel mayor2 = new JLabel("EMPATE");
					mayor2.setFont(new Font("Serif", Font.BOLD, 33));
					mayor2.setOpaque(false);
					mayor2.setBounds(coordsPosTurno.get(nuevosJugadores.get(1)).get("x"),coordsPosTurno.get(nuevosJugadores.get(1)).get("y")+altoSprite/2-33, 200,200);
					panelDerecha.add(mayor2);
					panelDerecha.repaint();
					empatados.add(mayor1);
					empatados.add(mayor2);
				}else {
					JLabel mayor = new JLabel("EMPATE");
					mayor.setFont(new Font("Serif", Font.BOLD, 33));
					mayor.setOpaque(false);
					mayor.setBounds(coordsPosTurno.get(i).get("x"),coordsPosTurno.get(i).get("y")+altoSprite/2-33, 200,200);
					panelDerecha.add(mayor);
					empatados.add(mayor);
					panelDerecha.repaint();
				}
				lComienza.setBounds(0, 0, 0, 0);
			}
			JLabel resultado = new JLabel((panelDados.valorDado1+panelDados.valorDado2)+"");
			resultado.setFont(new Font("Serif", Font.BOLD, 33));
			resultado.setOpaque(false);
			resultado.setBounds((int)Gestion.sizePantalla.getWidth()/5-anchoSprite/2-50, coordsPosTurno.get(i).get("y")+altoSprite/2-33,200 , 200);
			panelDerecha.add(resultado);
			resultados.put(i, resultado);
			panelDerecha.repaint();
			panelIzquierda.repaint();
		}
		if(!panelDados.reutilizar) {
			Gestion.setNumTurno(nuevosJugadores.get(0));
			System.out.println(Gestion.getNumTurno());
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			eliminarPanel();
		}
		if(nuevosJugadores.size()>1) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(JLabel empatado:empatados) {
				panelDerecha.remove(empatado);
			}
			for(JLabel resultado:resultados.values()) {
				panelDerecha.remove(resultado);
			}
			empatados.clear();
			repaint();
			comienza(nuevosJugadores);
		}
	}
	public void eliminarPanel() {
		Gestion.ventanaJuego.remove(this);
        Gestion.ventanaJuego.repaint();
	}
	public static void main(String[] args) {
		Gestion.ventanaJuego= new JFrame();
		Gestion.ventanaJuego.setLayout(null);
		Gestion.ventanaJuego.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Gestion.ventanaJuego.setSize(Gestion.sizePantalla);
		Gestion.ventanaJuego.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Gestion.ventanaJuego.setUndecorated(true);
		Gestion.ventanaJuego.setVisible(true);
		VentanaAsignarTurnos v = new VentanaAsignarTurnos();
	}
}
