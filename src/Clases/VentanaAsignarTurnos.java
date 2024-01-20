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
		
		for(Jugador j : Gestion.jugadores) {
			if(j.npc) {
				for(Asesinato carta: j.cartas) {
					 j.infoParaNpcs.get(carta).replace(INFONPCS.ENSENYADA, true);
				}
			}
			
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
		lComienza.setVerticalAlignment(JLabel.NORTH);
		lComienza.setFont(new Font("Serif", Font.BOLD, 33));
		lComienza.setOpaque(false);
		panelDerecha.add(lComienza);
		lComienza.setBounds(0,0,0,0);
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				panelDados = new VentanaDado((int) Gestion.sizePantalla.getWidth()/2, (int) Gestion.sizePantalla.getHeight(), 50);
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
		ArrayList<JLabel>flechas = new ArrayList<>();
		ImageIcon iconFlechaDerecha = new ImageIcon(getClass().getResource("flechaDerecha.png"));
		int ancho = 150;
		int margen = ((int)Gestion.sizePantalla.getWidth()/5-anchoSprite/2-ancho-20)/3;
		int alto = iconFlechaDerecha.getIconHeight()*ancho/iconFlechaDerecha.getIconWidth();
        Image flechaDerecha = iconFlechaDerecha.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_SMOOTH);
        iconFlechaDerecha = new ImageIcon(flechaDerecha);
        
		for(Integer j: jugadores) {
			JLabel lflecha = new JLabel();
//			lflecha.setOpaque(false);
			lflecha.setIcon(iconFlechaDerecha);
			lflecha.setBounds(margen, coordsPosTurno.get(j).get("y")+altoSprite/2-alto/2+15, ancho, alto);
			flechas.add(lflecha);
			panelDerecha.add(lflecha);
		}
		panelDerecha.repaint();
		panelDados.btnTirar.setEnabled(true);
		mayorValor=0;
		ArrayList<Integer>nuevosJugadores = new ArrayList<>();
		ArrayList<JLabel>empatados = new ArrayList<>();
		HashMap<Integer,JLabel>resultados = new HashMap<>();
		
		for(Integer i : jugadores) {
			izquierdaArriba.setText("Turno de "+Gestion.jugadores.get(i).getPersonaje().getNombre());
			if(Gestion.jugadores.get(i).npc) {
				panelDados.btnTirar.setEnabled(false);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				panelDados.tirarDado();
			}
			String lock = "lock";
			synchronized (lock) {
				 try {
		                lock.wait();
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
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
				lComienza.setBounds(coordsPosTurno.get(i).get("x"),coordsPosTurno.get(i).get("y")+altoSprite/2-16, 200, 50);
			}else if (panelDados.valorDado1+panelDados.valorDado2==mayorValor) {
				nuevosJugadores.add(i);
				jugadorMayor = -1;
				if(nuevosJugadores.size()==2) {
					JLabel mayor1 = new JLabel("EMPATE");
					mayor1.setFont(new Font("Serif", Font.BOLD, 33));
					mayor1.setVerticalAlignment(JLabel.NORTH);
					mayor1.setOpaque(false);
					mayor1.setBounds(coordsPosTurno.get(nuevosJugadores.get(0)).get("x"),coordsPosTurno.get(nuevosJugadores.get(0)).get("y")+altoSprite/2-16, 200, 50);
					panelDerecha.add(mayor1);
					JLabel mayor2 = new JLabel("EMPATE");
					mayor2.setFont(new Font("Serif", Font.BOLD, 33));
					mayor2.setOpaque(false);
					mayor2.setVerticalAlignment(JLabel.NORTH);
					mayor2.setBounds(coordsPosTurno.get(nuevosJugadores.get(1)).get("x"),coordsPosTurno.get(nuevosJugadores.get(1)).get("y")+altoSprite/2-16, 200, 50);
					panelDerecha.add(mayor2);
					panelDerecha.repaint();
					empatados.add(mayor1);
					empatados.add(mayor2);
				}else {
					JLabel mayor = new JLabel("EMPATE");
					mayor.setFont(new Font("Serif", Font.BOLD, 33));
					mayor.setVerticalAlignment(JLabel.NORTH);
					mayor.setOpaque(false);
					mayor.setBounds(coordsPosTurno.get(i).get("x"),coordsPosTurno.get(i).get("y")+altoSprite/2-16, 200,50);
					panelDerecha.add(mayor);
					empatados.add(mayor);
					panelDerecha.repaint();
				}
				lComienza.setBounds(0, 0, 0, 0);
			}
			JLabel resultado = new JLabel((panelDados.valorDado1+panelDados.valorDado2)+"");
			resultado.setFont(new Font("Serif", Font.BOLD, 33));
			resultado.setOpaque(false);
			resultado.setBounds(2*margen+ancho+20, coordsPosTurno.get(i).get("y")+altoSprite/2-16,200 , 50);
			panelDerecha.add(resultado);
			resultados.put(i, resultado);
			panelDerecha.repaint();
			panelDados.btnTirar.setEnabled(true);
			panelIzquierda.repaint();
		}
		panelDados.btnTirar.setEnabled(false);
		if(nuevosJugadores.size()>1) {
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(JLabel empatado:empatados) {
				panelDerecha.remove(empatado);
			}
			for(JLabel resultado:resultados.values()) {
				panelDerecha.remove(resultado);
			}
			for(JLabel flecha:flechas) {
				panelDerecha.remove(flecha);
			}
			empatados.clear();
			resultados.clear();
			flechas.clear();
			repaint();
			comienza(nuevosJugadores);
		}else {
			Gestion.setNumTurno(nuevosJugadores.get(0));
			for(int i = 0;i<Gestion.jugadores.size();i++) {
				if(Gestion.jugadores.get(i).personaje.nombre==NombrePersonaje.Rojo) {
					
				}else if(Gestion.jugadores.get(i).personaje.nombre==NombrePersonaje.Amarillo) {
					
				}else if(Gestion.jugadores.get(i).personaje.nombre==NombrePersonaje.Negro) {
					
				}else if(Gestion.jugadores.get(i).personaje.nombre==NombrePersonaje.Verde) {
					
				}else if(Gestion.jugadores.get(i).personaje.nombre==NombrePersonaje.Azul) {
					
				}else if(Gestion.jugadores.get(i).personaje.nombre==NombrePersonaje.Morado) {
					
				}
//				Añadir la posicion de inicio a cada uno
				Gestion.jugadores.get((Gestion.getNumTurno()+i)%Gestion.jugadores.size()).posicion= new int[] {0,9};
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			panelDados.btnTirar.setFocusable(false);
			int numeroJugador=Gestion.getNumTurno();
			while(Gestion.jugadores.get(numeroJugador).npc&&(numeroJugador+1)%Gestion.jugadores.size()!=Gestion.getNumTurno()) {
				numeroJugador = (numeroJugador+1)%Gestion.jugadores.size();
			}
			if(Gestion.jugadores.get((numeroJugador+1)%Gestion.jugadores.size())!=Gestion.jugadores.get(Gestion.getNumTurno())) {
				new VentanaTexto("TURNO DE "+Gestion.jugadores.get(numeroJugador).getPersonaje().getNombre().toString().toUpperCase(),numeroJugador);
				eliminarPanel();
				VentanaCartasInicio v =new VentanaCartasInicio(numeroJugador);
				v.setVisible(false);
				String lockSiguienteVentana = "siguienteVentana";
				synchronized (lockSiguienteVentana) {
					try {
						lockSiguienteVentana.wait();
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
				v.setVisible(true);
			}else {
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
			}
		}
	}
	public void eliminarPanel() {
		Gestion.ventanaJuego.remove(this);
        Gestion.ventanaJuego.repaint();
	}
	
}