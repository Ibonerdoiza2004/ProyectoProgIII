package Clases;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaDarCarta extends JPanel{
	private JPanel pCartaElegida = new JPanel(null);
	private JPanel pCartasPoseidas = new JPanel(null);
	private JPanel pCartasPedidas = new JPanel(null);
	private JPanel pBotones = new JPanel(null);
	private JPanel pArriba = new JPanel(new GridLayout(1,2));
	private JPanel pAbajo = new JPanel(null);
	private JLabel labelCartaMovida = null;
	private Asesinato cartaMovida = null;
	private JLabel labelCartaElegida = null;
	private ArrayList<Asesinato>cartas = null;
	protected Asesinato cartaParaMostrar = null;
	private int anchoCartaPoseida = 0;
	private int altoCartaPoseida = 0;
	private int index = 0;
	private int clickX = 0;
	private int clickY = 0;
	private int inicioX;
	private int inicioY;
	int numeroJugador;
	private ImageIcon recuadroCarta = null;
	public VentanaDarCarta(int numJugador) {
		
		numeroJugador = numJugador;
		setLayout(new GridLayout(2,1));
		setOpaque(false);
		pArriba.setOpaque(false);
		pAbajo.setOpaque(false);
		pCartasPoseidas.setOpaque(false);
		pCartaElegida.setOpaque(false);
		pCartasPedidas.setOpaque(false);
		setBounds(0, 0, (int)Gestion.sizePantalla.getWidth(),(int) Gestion.sizePantalla.getHeight());
		Gestion.ventanaJuego.add(this);
		JLabel l = new JLabel("AAAAAAAAAA");
		l.setBounds(0, 0, 100, 100);
		pArriba.add(pCartasPedidas);
		pArriba.add(pCartaElegida);
		this.add(pArriba);
		this.add(pAbajo);
		int anchoBotones = 300;
		
		pCartasPoseidas.setBounds(0, 0, (int)Gestion.sizePantalla.getWidth()-anchoBotones, (int)Gestion.sizePantalla.getHeight()/2 );
		
		pAbajo.add(pCartasPoseidas);
		
		
	    ImageIcon cartaPedida= Gestion.acusacion.get(0).getFoto(); //hay que añadir fotos a todos los posibles implicados de la clase asesinato
	    Image imagenCarta = cartaPedida.getImage();
	    
	    int altoCarta = (int)Gestion.sizePantalla.getHeight()/2-150;
		int anchoCarta = (int) ((double) imagenCarta.getWidth(null) / imagenCarta.getHeight(null) * altoCarta);
		if (anchoCarta>(int)Gestion.sizePantalla.getWidth()/(2*(Gestion.acusacion.size()+1))) {
			anchoCarta = (int)Gestion.sizePantalla.getWidth()/(2*(Gestion.acusacion.size()+1));
			altoCarta = (int) ((double) imagenCarta.getHeight(null) / imagenCarta.getWidth(null) * anchoCarta);
		}
		int espacioEntreCartasPedidas = ((int)Gestion.sizePantalla.getWidth()/2-anchoCarta*Gestion.acusacion.size())/(Gestion.acusacion.size()+1);
		
		for (int i = 0; i <Gestion.acusacion.size();i++) {
	    	cartaPedida=Gestion.acusacion.get(i).getFoto();
		    imagenCarta = cartaPedida.getImage();
		    imagenCarta = imagenCarta.getScaledInstance(anchoCarta, altoCarta, Image.SCALE_SMOOTH);
		    cartaPedida = new ImageIcon(imagenCarta);
			JLabel lCartaPedida = new JLabel();
			lCartaPedida.setIcon(cartaPedida);
			lCartaPedida.setBounds(i*anchoCarta+(i+1)*espacioEntreCartasPedidas,((int)Gestion.sizePantalla.getHeight()/3-imagenCarta.getHeight(null))/2,anchoCarta,altoCarta);
			pCartasPedidas.add(lCartaPedida);
	    }
	    JLabel lAcusacion = new JLabel("Acusación");
		lAcusacion.setFont(new Font("Serif", Font.BOLD, 33));
		lAcusacion.setBounds((int)Gestion.sizePantalla.getWidth()/4-75,((((int)Gestion.sizePantalla.getHeight()/2-imagenCarta.getHeight(null))/2)-33)/2 , 150, 30);
		pCartasPedidas.add(lAcusacion);
		
		
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

		pBotones.setBounds((int)Gestion.sizePantalla.getWidth()-anchoBotones, 0, anchoBotones-espacioEntreCartasPoseidas, (int)Gestion.sizePantalla.getHeight()/2);
		JButton bContinuar = new JButton("Continuar");
		bContinuar.setFocusable(false);
		bContinuar.setFont(new Font("Serif", Font.BOLD, 33));
		bContinuar.setHorizontalAlignment(JLabel.CENTER);
		bContinuar.setBounds(0, 2*pBotones.getHeight()/5, pBotones.getWidth(), pBotones.getHeight()/5);
		pBotones.add(bContinuar);
		pAbajo.add(pBotones);
		
		ArrayList<JLabel>labelCartas = new ArrayList<>();
	    cartas = new ArrayList<Asesinato>(jugador.cartas);
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
		lPoseidas.setBounds(pCartasPoseidas.getWidth()/2-131,10 , 262, 33);
		pCartasPoseidas.add(lPoseidas);
		
		
		JLabel lRecuadro = new JLabel();
		lRecuadro.setIcon(recuadroCarta);
		lRecuadro.setBounds(((int)Gestion.sizePantalla.getWidth()/2-recuadroCarta.getImage().getWidth(null))/2,((int)Gestion.sizePantalla.getHeight()/2-recuadroCarta.getImage().getHeight(null))/2 , recuadroCarta.getImage().getWidth(null), recuadroCarta.getImage().getHeight(null));
		pCartaElegida.add(lRecuadro);
		JLabel lElegida = new JLabel("Carta para enseñar");
		lElegida.setHorizontalAlignment(JLabel.CENTER);
		lElegida.setFont(new Font("Serif", Font.BOLD, 33));
		lElegida.setBounds((int)Gestion.sizePantalla.getWidth()/4-175, 10 , 350, 33);
		pCartaElegida.add(lElegida);
		repaint();
		
		boolean enable = true;
		for(Asesinato carta:jugador.cartas) {
			if (Gestion.acusacion.contains(carta)){
				enable = false;
			}
		}
		revalidate();
		bContinuar.setEnabled(enable);
		
		bContinuar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					Thread t = new Thread(new Runnable() {
						
						@Override
						public void run() {
							bContinuar.setEnabled(false);
							if(cartaParaMostrar!=null) {
								Gestion.cartasEnsenyadas.put(cartaParaMostrar, Gestion.jugadores.get(numeroJugador));
							}
							numeroJugador = (numeroJugador+1)%Gestion.jugadores.size();
							while(Gestion.jugadores.get(numeroJugador).npc&&numeroJugador!=Gestion.getNumTurno()) {
			//					Enseñar carta
								ArrayList<Asesinato>posiblesCartas = new ArrayList<>();
								for (Asesinato carta: Gestion.jugadores.get(numeroJugador).cartas) {
									if(Gestion.acusacion.contains(carta)) {
										posiblesCartas.add(carta);
									}
								}
								if(!posiblesCartas.isEmpty()) {
									Gestion.cartasEnsenyadas.put(posiblesCartas.get((int)(Math.random()*posiblesCartas.size())), Gestion.jugadores.get(numeroJugador));
								}
								numeroJugador = (numeroJugador+1)%Gestion.jugadores.size();
							}
							if(Gestion.jugadores.get(numeroJugador)!=Gestion.jugadores.get(Gestion.getNumTurno())) {
								new VentanaTexto("TURNO DE "+Gestion.jugadores.get(numeroJugador).getPersonaje().getNombre().toString().toUpperCase(),numeroJugador);
								eliminarPanel();
								String lockSiguienteVentana = "siguienteVentana";
								synchronized (lockSiguienteVentana) {
									try {
										lockSiguienteVentana.wait();
									} catch (InterruptedException ex) {
										ex.printStackTrace();
									}
								}
								new VentanaDarCarta(numeroJugador);
							}else {
								if(Gestion.jugadores.get(Gestion.getNumTurno()).npc) {
									Gestion.logicaMarcarLista(Gestion.jugadores.get(Gestion.getNumTurno()));
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
									new VentanaVerCartas();
								}
								
							}
						}
					});
					t.start();
				
			}
		});
		
		Gestion.ventanaJuego.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				boolean pass = false;
				if(labelCartaMovida != null) {
					remove(labelCartaMovida);
					repaint();
					if (e.getX() >= lRecuadro.getX()+(int)Gestion.sizePantalla.getWidth()/2 && e.getX() <= lRecuadro.getX()+(int)Gestion.sizePantalla.getWidth()/2+lRecuadro.getWidth()
						&& e.getY() >= lRecuadro.getY() && e.getY() <= lRecuadro.getY()+lRecuadro.getHeight()) {
							if(Gestion.acusacion.contains(cartaMovida)){
								pass=true;
								if(labelCartaElegida==null) {
									labelCartaMovida.setBounds(lRecuadro.getX(), lRecuadro.getY(), lRecuadro.getWidth(), lRecuadro.getHeight());
									pCartaElegida.add(labelCartaMovida);
									labelCartaElegida = labelCartaMovida;
									cartaParaMostrar = cartaMovida;
									labelCartas.set(index, null);
									cartas.set(index, null);
								}else {	
									if (index!=-1) {
										labelCartas.set(index, labelCartaElegida);
										labelCartas.get(index).setBounds(coordsCartas.get(index).get(0),coordsCartas.get(index).get(1),anchoCartaPoseida,altoCartaPoseida);
										cartas.set(index, cartaParaMostrar);
										pCartasPoseidas.add(labelCartas.get(index));
										labelCartaMovida.setBounds(lRecuadro.getX(), lRecuadro.getY(), lRecuadro.getWidth(), lRecuadro.getHeight());
										labelCartaElegida = labelCartaMovida;
										cartaParaMostrar = cartaMovida;
										pCartaElegida.add(labelCartaElegida);
										
									}else {
										pass=false;
									}
								}
							}
							labelCartaMovida.repaint();
					}else {
						for (int i = 0; i < coordsCartas.size(); i++){
							if(e.getX() >= coordsCartas.get(i).get(0) && e.getX() <= coordsCartas.get(i).get(0)+anchoCartaPoseida
									&& e.getY() >= coordsCartas.get(i).get(1)+(int)Gestion.sizePantalla.getHeight()/2 && e.getY() <= coordsCartas.get(i).get(1)+(int)Gestion.sizePantalla.getHeight()/2+altoCartaPoseida) {
									pass = true;
									if (labelCartas.get(i) != null) {
											if (index==-1) {
												if(Gestion.acusacion.contains(cartas.get(i))) {	
													labelCartaElegida = labelCartas.get(i) ;
													cartaParaMostrar= cartas.get(i);
													labelCartaElegida.setBounds(lRecuadro.getX(), lRecuadro.getY(), lRecuadro.getWidth(), lRecuadro.getHeight());
													labelCartaMovida.setBounds(coordsCartas.get(i).get(0), coordsCartas.get(i).get(1), anchoCartaPoseida, altoCartaPoseida);
													labelCartas.set(i, labelCartaMovida);
													cartas.set(i, cartaMovida);
													pCartaElegida.add(labelCartaElegida);
													pCartasPoseidas.add(labelCartas.get(i));
												}else {
													pass=false;
												}
											}else {
												labelCartas.set(index,  labelCartas.get(i));
												cartas.set(index, cartas.get(i));
												labelCartas.set(i, labelCartaMovida);
												cartas.set(i, cartaMovida);
												labelCartas.get(index).setBounds(coordsCartas.get(index).get(0),coordsCartas.get(index).get(1),anchoCartaPoseida,altoCartaPoseida);
												labelCartas.get(i).setBounds(coordsCartas.get(i).get(0), coordsCartas.get(i).get(1), anchoCartaPoseida, altoCartaPoseida);
												
												
												pCartasPoseidas.add(labelCartas.get(index));
												pCartasPoseidas.add(labelCartas.get(i));
											}
										}else {
											if (index==-1) {
												labelCartaMovida.setBounds(coordsCartas.get(i).get(0), coordsCartas.get(i).get(1), anchoCartaPoseida, altoCartaPoseida);
												pCartaElegida.add(labelCartaMovida);
												labelCartas.set(i, labelCartaMovida);
												cartas.set(i, cartaMovida);
												labelCartaElegida = null;
												cartaParaMostrar = null;
												pCartasPoseidas.add(labelCartas.get(i));
											}else {
												labelCartaMovida.setBounds(coordsCartas.get(i).get(0), coordsCartas.get(i).get(1), anchoCartaPoseida, altoCartaPoseida);
												pCartaElegida.add(labelCartaMovida);
												labelCartas.set(i, labelCartaMovida);
												cartas.set(i, cartaMovida);
												labelCartas.set(index, null);
												cartas.set(index, null);
												pCartasPoseidas.add(labelCartas.get(i));
											}
										}
							}
						}
						labelCartaMovida.repaint();
					}
					if (!pass) {
						labelCartaMovida.setBounds(inicioX, inicioY, anchoCartaPoseida, altoCartaPoseida);
						if(index == -1) {
							pCartaElegida.add(labelCartaMovida);
						}else {
							pCartasPoseidas.add(labelCartaMovida);
						}
						labelCartaMovida.repaint();
					}
					if(cartaParaMostrar==null) {
						boolean enable = true;
						for(Asesinato carta:jugador.cartas) {
							if (Gestion.acusacion.contains(carta)){
								enable = false;
							}
						}
						bContinuar.setEnabled(enable);
					}else {
						bContinuar.setEnabled(true);
					}
				}
				
			}
			
			
			@Override
			public void mousePressed(MouseEvent e) {
				labelCartaMovida = null;
				cartaMovida = null;
				clickX = e.getX();
				clickY = e.getY();
				if (e.getX() >= lRecuadro.getX()+(int)Gestion.sizePantalla.getWidth()/2 && e.getX() <= lRecuadro.getX()+(int)Gestion.sizePantalla.getWidth()/2+lRecuadro.getWidth()
				&& e.getY() >= lRecuadro.getY() && e.getY() <= lRecuadro.getY()+lRecuadro.getHeight()&&labelCartaElegida!=null) {
					labelCartaMovida = labelCartaElegida;
					cartaMovida = cartaParaMostrar;
					index = -1;
					pCartaElegida.remove(labelCartaMovida);
					inicioX = labelCartaMovida.getX();
					inicioY = labelCartaMovida.getY();
					labelCartaMovida.setBounds(-clickX+((int)Gestion.sizePantalla.getWidth()/2+labelCartaMovida.getX())+e.getX(), -clickY+labelCartaMovida.getY()+e.getY(),
						anchoCartaPoseida, altoCartaPoseida);
					add(labelCartaMovida);
					labelCartaMovida.repaint();
					setComponentZOrder(labelCartaMovida, 0);
					setComponentZOrder(pArriba, 1);
					
				}
				for (int i = 0; i < coordsCartas.size(); i++){
					if(labelCartas.get(i) != null && e.getX() >= coordsCartas.get(i).get(0) && e.getX() <= coordsCartas.get(i).get(0)+anchoCartaPoseida
							&& e.getY() >= coordsCartas.get(i).get(1)+(int)Gestion.sizePantalla.getHeight()/2 && e.getY() <= coordsCartas.get(i).get(1)+(int)Gestion.sizePantalla.getHeight()/2+altoCartaPoseida) {
						labelCartaMovida = labelCartas.get(i);
						cartaMovida = cartas.get(i);
						index = i;
						pCartasPoseidas.remove(labelCartaMovida);
						inicioX = labelCartaMovida.getX();
						inicioY = labelCartaMovida.getY();
						labelCartaMovida.setBounds(-clickX+labelCartaMovida.getX()+e.getX(), -clickY+e.getY()+(labelCartaMovida.getY()+(int)Gestion.sizePantalla.getHeight()/2),
							anchoCartaPoseida, altoCartaPoseida);
						add(labelCartaMovida);
						labelCartaMovida.repaint();
						setComponentZOrder(labelCartaMovida, 0);
						setComponentZOrder(pArriba, 1);
						
						break;
					}
				}
				
					
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		Gestion.ventanaJuego.addMouseMotionListener(new MouseMotionAdapter() {
			
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if (labelCartaMovida!=null) {
					if(index ==-1) {
						labelCartaMovida.setBounds(-clickX+((int)Gestion.sizePantalla.getWidth()/2+inicioX)+e.getX(), -clickY+inicioY+e.getY(),
							anchoCartaPoseida, altoCartaPoseida);
					}else {
						labelCartaMovida.setBounds(-clickX+inicioX+e.getX(), -clickY+e.getY()+(inicioY+(int)Gestion.sizePantalla.getHeight()/2),
								anchoCartaPoseida, altoCartaPoseida);
					}
				}
				
			}
		});
		
	}
	public void eliminarPanel() {
		Gestion.ventanaJuego.remove(this);
		Gestion.ventanaJuego.repaint();
	}
}
