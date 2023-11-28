package Clases;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class VentanaTablero extends JFrame{
	int coorXInicioTablero;
	int coorXFinalTablero;
	int columnaSeleccionada = -1;
	int coorYInicioTablero;
	int coorYFinalTablero;
	int altoBoton = 100;
	double anchoColumnaTablero;
	double altoFilaTablero;
	HashMap<Integer, ArrayList<Integer>>coordsFilas = null;
	HashMap<Integer, ArrayList<Integer>>coordsColumnas = null;
	JLabel labelSeleccionada = null;
	int filaSeleccionada = -1;
	ArrayList<Integer> casillaSeleccionadaY = null;
	ArrayList<Integer> casillaSeleccionadaX = null;
	ArrayList<Integer>destino = null;
	ArrayList<ArrayList<Integer>>caminoMasCorto=null;
	JLabel labelJugador;
//	Jugador jugador = Gestion.jugadores.get(Gestion.getNumTurno());
	JLabel labelTablero = new JLabel();
	JPanel panelDerecha = new JPanel();
	HashMap<ArrayList<Integer>,JLabel>labelCasillas;
	VentanaDado panelDados = new VentanaDado((int)Gestion.sizePantalla.getWidth()-(int)Gestion.sizePantalla.getHeight(), (int)Gestion.sizePantalla.getHeight()-altoBoton);
	JButton botonDesplegar = new JButton();
	JButton botonPlegar = new JButton();
	JPanel panelDesplegable = new JPanel();
	JPanel panelLista;
	JPanel panelTablero;
	ArrayList<JLabel>casillasDelCamino = new ArrayList<>();
	
	
	
	public VentanaTablero() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(Gestion.sizePantalla);
		setLayout(null);
		coorXInicioTablero =(int) (2*Gestion.sizePantalla.getHeight())/36;
		coorXFinalTablero =(int) (Gestion.sizePantalla.getHeight()-(Gestion.sizePantalla.getHeight())/19);
		coorYInicioTablero = (int) (Gestion.sizePantalla.getHeight())/27;
		coorYFinalTablero =(int) (Gestion.sizePantalla.getHeight()-(Gestion.sizePantalla.getHeight())/29);
		anchoColumnaTablero = ((double)(-coorXInicioTablero+coorXFinalTablero))/(double)(Gestion.numColumnas);
		altoFilaTablero = ((double)(-coorYInicioTablero+coorYFinalTablero))/((double)Gestion.numFilas);
		
		//PanelIzquierda
				panelTablero =new JPanel() {//Usar este método paintComponent para probar si las filas y columnas están bien guardadas
				
					@Override
					protected void paintComponent(Graphics g) {
						Image imagenTablero = (new ImageIcon(getClass().getResource("tablero.jpg"))).getImage();
						g.drawImage(imagenTablero, 0, 0, getWidth(), getHeight(), this);
//						if (coordsFilas!=null) {
//							System.out.println(coorYInicioTablero+" "+coorYFinalTablero);
//							System.out.println();
//							System.out.println(altoFilaTablero);
//							for (int i = 0; i<coordsFilas.size()||i<coordsColumnas.size();i++) {
//								if(i<coordsFilas.size()) {
//									g.drawLine(0, coordsFilas.get(i).get(0), getWidth(), coordsFilas.get(i).get(0));
//								}
//								if(i<coordsColumnas.size()) {
//									g.drawLine(coordsColumnas.get(i).get(0),0,coordsColumnas.get(i).get(0) ,getHeight() );
//								}
//							}
//
//							g.drawLine(0, coordsFilas.get(coordsFilas.size()-1).get(1), getWidth(), coordsFilas.get(coordsFilas.size()-1).get(1));
//							g.drawLine(coordsColumnas.get(coordsColumnas.size()-1).get(1), 0, coordsColumnas.get(coordsColumnas.size()-1).get(1), getHeight());
//							System.out.println(coordsFilas.get(coordsColumnas.size()-1).get(1));
//						}
						
					}
				};
				panelTablero.setLayout(null);
				panelTablero.setBounds(0,0,(int)Gestion.sizePantalla.getHeight(),(int)Gestion.sizePantalla.getHeight());
				add(panelTablero);
				
		//PanelDesplegable	
		int inicioPanelDesplegable =0;
//		panelLista = new JPanel() {
//			@Override
//			public void paintComponent(Graphics g) {
//				ImageIcon iconoLista = new ImageIcon(getClass().getResource("seis.jpg"));
//				Image imagenLista = iconoLista.getImage();
//				g.drawImage(imagenLista, 0, 0, getWidth(), getHeight(), this);
//			}
//		};
		panelLista = new JPanel();
		TablaLista modeloTabla = new TablaLista();
		JTable tablaLista = new JTable(modeloTabla);
		panelLista.add(tablaLista);
		panelDesplegable.setLayout(null);
		panelLista.setBounds(0, altoBoton, (int)(Gestion.sizePantalla.getWidth()-Gestion.sizePantalla.getHeight()),(int)Gestion.sizePantalla.getHeight()-altoBoton-inicioPanelDesplegable);
		panelDesplegable.add(panelLista);
		panelDesplegable.setBounds((int)Gestion.sizePantalla.getHeight()-altoBoton, (int)Gestion.sizePantalla.getHeight(), (int)(Gestion.sizePantalla.getWidth()-Gestion.sizePantalla.getHeight()),(int)Gestion.sizePantalla.getHeight()-2*altoBoton);
		
		botonPlegar.setFocusable(false);
		botonPlegar.setText("\\/");
		botonPlegar.setVerticalAlignment(SwingConstants.CENTER);
		botonPlegar.setBounds(0,0,(int)(Gestion.sizePantalla.getWidth()-Gestion.sizePantalla.getHeight()), altoBoton);
			
		panelDesplegable.add(botonPlegar);
		add(panelDesplegable);
		
		//Panel Derecha
			//PanelDados
		panelDerecha.setLayout(null);
//		JPanel panelDados = new JPanel() {
//			@Override
//			public void paintComponent(Graphics g) {
//				ImageIcon iconoDados = new ImageIcon(getClass().getResource("cinco.jpg"));
//				Image imagenDados = iconoDados.getImage();
//                g.drawImage(imagenDados, 0, 0, getWidth(), getHeight(), this);
//			}
//		};
		//panelDados= new VentanaDado();
		
		//panelDados.setSize((int)Gestion.sizePantalla.getWidth()-(int)Gestion.sizePantalla.getHeight(), (int)Gestion.sizePantalla.getHeight()-altoBoton);
		panelDerecha.add(panelDados);
			//PanelBoton
		botonDesplegar.setFocusable(false);
		botonDesplegar.setText("/\\");
		botonDesplegar.setVerticalAlignment(SwingConstants.CENTER);
		botonDesplegar.setBounds(0, (int)Gestion.sizePantalla.getHeight()-altoBoton, (int)(Gestion.sizePantalla.getWidth()-Gestion.sizePantalla.getHeight()),altoBoton );
		panelDerecha.add(botonDesplegar);
		
		panelDerecha.setBounds((int)Gestion.sizePantalla.getHeight(), 0, (int)Gestion.sizePantalla.getWidth(),(int)Gestion.sizePantalla.getHeight());
		add(panelDerecha);
		
		botonDesplegar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelDesplegable.setVisible(true);
				botonPlegar.setEnabled(false);
				botonDesplegar.setVisible(false);
				
				Thread t1 = new Thread(new Runnable() {
					@Override
					public void run() {
						int coordX = (int)(Gestion.sizePantalla.getHeight());
						int ancho = (int)(Gestion.sizePantalla.getWidth()-coordX);
						int coordY = (int)Gestion.sizePantalla.getHeight();
						int alto = (int)(Gestion.sizePantalla.getHeight()-inicioPanelDesplegable);
						while((int)coordY>inicioPanelDesplegable) {
							if(coordY-inicioPanelDesplegable<5) {
								coordY = coordY-1;
							}else if (coordY-inicioPanelDesplegable<alto/28) {
								coordY = coordY-4;
							}else if (coordY-inicioPanelDesplegable<alto/14) {
								coordY = coordY-8;
							}else if(coordY-inicioPanelDesplegable<alto/8){
								coordY = coordY-16;
							}else if(coordY-inicioPanelDesplegable<alto/4) {
								coordY = coordY-32;
							}else if(coordY-inicioPanelDesplegable<alto/2) {
								coordY = coordY-64;
							}else {
								coordY = coordY-100;
							}
							panelDesplegable.setBounds(coordX, coordY,ancho,alto);
							try {
								Thread.sleep(12);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						botonPlegar.setEnabled(true);
						panelDerecha.remove(panelDados);
						
					}
				});
				t1.start();
			}
		});
		botonPlegar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelDerecha.add(panelDados);
				botonPlegar.setEnabled(false);
				Thread t2 = new Thread(new Runnable() {
					@Override
					public void run() {
						int coordX = (int)(Gestion.sizePantalla.getHeight());
						int ancho = (int)(Gestion.sizePantalla.getWidth()-coordX);
						int coordY = inicioPanelDesplegable;
						int alto = (int)(Gestion.sizePantalla.getHeight()-2*altoBoton);
						while((int)coordY<=Gestion.sizePantalla.getHeight()-altoBoton) {
							if(Gestion.sizePantalla.getHeight()-altoBoton-coordY<5) {
								coordY = coordY+1;
							}else if (Gestion.sizePantalla.getHeight()-altoBoton-coordY<alto/28) {
								coordY = coordY+4;
							}else if (Gestion.sizePantalla.getHeight()-altoBoton-coordY<alto/14) {
								coordY = coordY+8;
							}else if(Gestion.sizePantalla.getHeight()-altoBoton-coordY<alto/8) {
								coordY = coordY+16;
							}else if(Gestion.sizePantalla.getHeight()-altoBoton-coordY<alto/4) {
								coordY = coordY+32;
							}else if(Gestion.sizePantalla.getHeight()-altoBoton-coordY<alto/2) {
								coordY = coordY+64;
							}else {
								coordY = coordY+100;
							}
							panelDesplegable.setBounds(coordX,(int)coordY,ancho,alto);
							try {
								Thread.sleep(16);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						panelDesplegable.setVisible(false);
						botonDesplegar.setVisible(true);
					}
				});
				t2.start();
				
			}
		});
		crearCasillas();
		dibujarSprites();
		setVisible(true);		       
		pintarCasillas();
		    
		
	}
	public void crearCasillas() {
		coordsFilas= new HashMap<>();
		coordsColumnas= new HashMap<>();
		for (int i=0; i<Gestion.numFilas || i<Gestion.numColumnas;i++) {
			if(i<Gestion.numFilas) {
				ArrayList<Integer>filaActual = new ArrayList<>();
				filaActual.add((int)(coorYInicioTablero+altoFilaTablero*i));
				filaActual.add((int)(coorYInicioTablero+altoFilaTablero*(i+1)));
				coordsFilas.put(i, filaActual);
			}
			if(i<Gestion.numColumnas) {
				ArrayList<Integer>columnaActual = new ArrayList<>();
				columnaActual.add((int)(coorXInicioTablero+anchoColumnaTablero*i));
				columnaActual.add((int)(coorXInicioTablero+anchoColumnaTablero*(i+1)));
				coordsColumnas.put(i, columnaActual);
			}
		}
//		System.out.println(coordsFilas.size() + " " + coordsFilas);
//		System.out.println(coordsColumnas.size() + " " + coordsColumnas);
	}
	public ArrayList<ArrayList<Integer>> caminoMasCorto(int filaInicio, int columnaInicio, int filaFinal, int columnaFinal) {
		ArrayList<Integer> ultimoVertice = new ArrayList<>();
		ultimoVertice.add(filaFinal);
		ultimoVertice.add(columnaFinal);
		HashMap <ArrayList<Integer>,ArrayList<ArrayList<Integer>>> vertices = new HashMap<>();
		HashMap<ArrayList<Integer>,ArrayList<ArrayList<Integer>>> verticesAdyacentes = new HashMap<>();
		ArrayList<Integer> verticeActual = new ArrayList<>();
		verticeActual.add(filaInicio);
		verticeActual.add(columnaInicio);
		ArrayList<ArrayList<Integer>> solucion = new ArrayList<>();
		solucion.add(verticeActual);
		verticesAdyacentes.put(verticeActual, solucion);
		while (true) {
			verticeActual = ((ArrayList<Integer>) verticesAdyacentes.keySet().toArray()[0]);
			for (ArrayList<Integer> i:verticesAdyacentes.keySet()) {
				if (verticesAdyacentes.get(i).size() < verticesAdyacentes.get(verticeActual).size()) {
					verticeActual = i;
				}
			}
			vertices.put(verticeActual, verticesAdyacentes.get(verticeActual));
			verticesAdyacentes.remove(verticeActual);
			
			
			ArrayList<Integer>nuevoVerticeDerecha = new ArrayList<>();
			nuevoVerticeDerecha.add(verticeActual.get(0));
			nuevoVerticeDerecha.add((verticeActual.get(1)+1));
			if(!vertices.containsKey(nuevoVerticeDerecha)) {
				if (verticeActual.get(1)!=Gestion.tablero.get(verticeActual.get(0)).size()-1) {
					if(Gestion.tablero.get(verticeActual.get(0)).get(verticeActual.get(1)+1)!=0) {
						solucion = new ArrayList<>(vertices.get(verticeActual));
						solucion.add(nuevoVerticeDerecha);
						if(!verticesAdyacentes.containsKey(nuevoVerticeDerecha)) {
							verticesAdyacentes.put(nuevoVerticeDerecha, solucion);
						}
					}
				}
			}
			ArrayList<Integer>nuevoVerticeIzquierda = new ArrayList<>();
			nuevoVerticeIzquierda.add(verticeActual.get(0));
			nuevoVerticeIzquierda.add(verticeActual.get(1)-1);
			if(!vertices.containsKey(nuevoVerticeIzquierda)) {
				if(verticeActual.get(1)!=0) {
					if(Gestion.tablero.get(verticeActual.get(0)).get(verticeActual.get(1)-1)!=0) {
						solucion = new ArrayList<>(vertices.get(verticeActual));
						solucion.add(nuevoVerticeIzquierda);
						if(!verticesAdyacentes.containsKey(nuevoVerticeIzquierda)) {
							verticesAdyacentes.put(nuevoVerticeIzquierda, solucion);
						}
					}
				}
			}
			ArrayList<Integer>nuevoVerticeArriba = new ArrayList<>();
			nuevoVerticeArriba.add(verticeActual.get(0)-1);
			nuevoVerticeArriba.add(verticeActual.get(1));
			if(!vertices.containsKey(nuevoVerticeArriba)) {
				if(verticeActual.get(0)!=0) {
					if(Gestion.tablero.get(verticeActual.get(0)-1).get(verticeActual.get(1))!=0) {
						solucion = new ArrayList<>(vertices.get(verticeActual));
						solucion.add(nuevoVerticeArriba);
						if(!verticesAdyacentes.containsKey(nuevoVerticeArriba)) {
							verticesAdyacentes.put(nuevoVerticeArriba, solucion);
						}
					}
				}
			}
			ArrayList<Integer>nuevoVerticeAbajo = new ArrayList<>();
			nuevoVerticeAbajo.add(verticeActual.get(0)+1);
			nuevoVerticeAbajo.add(verticeActual.get(1));
			if(!vertices.containsKey(nuevoVerticeAbajo)) {
				if(verticeActual.get(0)!=Gestion.tablero.size()-1) {
					if(Gestion.tablero.get(verticeActual.get(0)+1).get(verticeActual.get(1))!=0) {
						solucion = new ArrayList<>(vertices.get(verticeActual));
						solucion.add(nuevoVerticeAbajo);
						if(!verticesAdyacentes.containsKey(nuevoVerticeAbajo)) {
							verticesAdyacentes.put(nuevoVerticeAbajo, solucion);
						}
					}
				}
			}
			
			
			if((verticeActual.equals(ultimoVertice))) {
				break;
			}
		}
		
		return vertices.get(verticeActual);
	}
	public void dibujarSprites() {
		for(int i=0; i<5;i++) {
			Jugador jugador = new Jugador(new Personaje(), false);
			do {
			jugador.posicion=new int[] {(int)(Math.random()*24),(int)(Math.random()*24)};
			}while(Gestion.tablero.get(jugador.posicion[0]).get(jugador.posicion[1])!=1);
			Gestion.jugadores.add(jugador);//Borrar cuando ya esté añadida la funcionalidad final
		}
		Jugador jugador = Gestion.jugadores.get(Gestion.getNumTurno());
		ImageIcon sprite = new ImageIcon(Gestion.sprites.get(jugador.getPersonaje().getNombre()).get(TipoSprite.AndarAbajo).get(0));
		labelJugador =new JLabel();
		labelJugador.setIcon(sprite);
		labelJugador.setBounds((coordsColumnas.get(jugador.posicion[1]).get(0)+coordsColumnas.get(jugador.posicion[1]).get(1))/2-labelJugador.getIcon().getIconWidth()/2, (coordsFilas.get(jugador.posicion[0]).get(0)+coordsFilas.get(jugador.posicion[0]).get(1))/2-labelJugador.getIcon().getIconHeight()+5 , labelJugador.getIcon().getIconWidth(), labelJugador.getIcon().getIconHeight());
		panelTablero.add(labelJugador);
		panelTablero.setComponentZOrder(labelJugador, 0);
		JLabel casillaInicio= new JLabel() {
			protected void paintComponent(Graphics g) {
				
				g.setColor(Color.WHITE);
				g.fillRect(1, 1, getWidth()-2, getHeight()-2);
				Graphics2D g2d = (Graphics2D) g;
		        g2d.setStroke(new BasicStroke(2));
				g2d.setColor(Color.black);
				g2d.drawRect(1, 1, getWidth()-2, getHeight()-2);
				
			};
		};
		ArrayList<Integer> filaInicio = coordsFilas.get(jugador.posicion[0]);
		ArrayList<Integer> columnaInicio = coordsColumnas.get(jugador.posicion[1]);
		casillaInicio.setBounds(columnaInicio.get(0)-1, filaInicio.get(0)-1, columnaInicio.get(1)-columnaInicio.get(0)+2, filaInicio.get(1)-filaInicio.get(0)+2);
		casillasDelCamino.add(casillaInicio);
		panelTablero.add(casillaInicio);
		panelTablero.setComponentZOrder(casillaInicio, 1);
		panelTablero.repaint();
	}
	public void pintarCasillas() {		 
		Jugador jugador = Gestion.jugadores.get(Gestion.getNumTurno());
		
		String lock = "lock";
		synchronized (lock) {
			 try {
	                lock.wait();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
		}
		int movimientos = (panelDados.valorDado1+panelDados.valorDado2);
		HashMap<String,ArrayList<ArrayList<Integer>>>movimientosPosibles = Gestion.calcularMovimiento(jugador.posicion[0], jugador.posicion[1], movimientos, Gestion.tablero);
		 try {
		    panelDados.hilo.join();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		labelCasillas= new HashMap<>();
		for(ArrayList<ArrayList<Integer>>casillasPosibles:movimientosPosibles.values()) {
			for(ArrayList<Integer>casilla:casillasPosibles) {
				ArrayList<Integer> casillaSeleccionadaY = coordsFilas.get(casilla.get(0));
				ArrayList<Integer> casillaSeleccionadaX = coordsColumnas.get(casilla.get(1));
				JLabel labelCasilla = new JLabel() {
					protected void paintComponent(Graphics g) {
						if(movimientosPosibles.get("puertasPosibles").equals(casillasPosibles)) {
							g.setColor(Color.GREEN);
						}else {
							g.setColor(Color.BLUE);
						}
						g.fillRect(1, 1, getWidth()-2, getHeight()-2);
						Graphics2D g2d = (Graphics2D) g;
				        g2d.setStroke(new BasicStroke(2));
						g2d.setColor(Color.black);
						g2d.drawRect(1, 1, getWidth()-2, getHeight()-2);
						
					};
				};
				labelCasilla.setBounds(casillaSeleccionadaX.get(0)-1, casillaSeleccionadaY.get(0)-1, casillaSeleccionadaX.get(1)-casillaSeleccionadaX.get(0)+2, casillaSeleccionadaY.get(1)-casillaSeleccionadaY.get(0)+2);
				labelCasillas.put(casilla, labelCasilla);
				panelTablero.add(labelCasilla);
			}
			panelTablero.repaint();
		}
		seleccionarCasilla();
	}
	public void seleccionarCasilla() {
		panelTablero.addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				int tempY = filaSeleccionada;
				int tempX = columnaSeleccionada;
				for (int i = 0; i<coordsFilas.size();i++) {
					ArrayList<Integer>coordsFila = coordsFilas.get(i);
					if (coordsFila.get(0)<e.getY()&&coordsFila.get(1)>e.getY()) {
						filaSeleccionada = i;
						break;
					}
				}
				for (int i = 0; i<coordsColumnas.size();i++) {
					ArrayList<Integer>coordsColumna = coordsColumnas.get(i);
					if (coordsColumna.get(0)<e.getX()&&coordsColumna.get(1)>e.getX()) {
						columnaSeleccionada = i;
						break;
					}
				}
				
				if(tempY!=filaSeleccionada||tempX!=columnaSeleccionada) {
					if(labelSeleccionada!=null) {
						labelSeleccionada.setBounds(casillaSeleccionadaX.get(0)-1, casillaSeleccionadaY.get(0)-1, casillaSeleccionadaX.get(1)-casillaSeleccionadaX.get(0)+2, casillaSeleccionadaY.get(1)-casillaSeleccionadaY.get(0)+2);
					}
					if(filaSeleccionada!=-1&&columnaSeleccionada!=-1) {	
						ArrayList<Integer>casillaSeleccionada= new ArrayList<>();
						casillaSeleccionada.add(filaSeleccionada);
						casillaSeleccionada.add(columnaSeleccionada);
						labelSeleccionada = labelCasillas.get(casillaSeleccionada);
						if(labelSeleccionada!=null) {
							casillaSeleccionadaY = coordsFilas.get(casillaSeleccionada.get(0));
							casillaSeleccionadaX = coordsColumnas.get(casillaSeleccionada.get(1));
							labelSeleccionada.setBounds(casillaSeleccionadaX.get(0)-5, casillaSeleccionadaY.get(0)-5, casillaSeleccionadaX.get(1)-casillaSeleccionadaX.get(0)+10, casillaSeleccionadaY.get(1)-casillaSeleccionadaY.get(0)+10);
							panelTablero.setComponentZOrder(labelSeleccionada, 1);
						}
					}
				}
				
			}
			
		});
		panelTablero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(filaSeleccionada!=-1&&columnaSeleccionada!=-1&&labelSeleccionada!=null) {
					for (MouseMotionListener listener : panelTablero.getMouseMotionListeners()) {
					    panelTablero.removeMouseMotionListener(listener);
					}
					for (MouseListener listener : panelTablero.getMouseListeners()) {
					    panelTablero.removeMouseListener(listener);
					}
					Jugador jugador = Gestion.jugadores.get(Gestion.getNumTurno());
					for(JLabel casilla:labelCasillas.values()) {
						panelTablero.remove(casilla);
					}
					labelSeleccionada.setBounds(casillaSeleccionadaX.get(0)-1, casillaSeleccionadaY.get(0)-1, casillaSeleccionadaX.get(1)-casillaSeleccionadaX.get(0)+2, casillaSeleccionadaY.get(1)-casillaSeleccionadaY.get(0)+2);
					JLabel labelDestino= new JLabel() {
						protected void paintComponent(Graphics g) {
							
							g.setColor(Color.RED);
							g.fillRect(1, 1, getWidth()-2, getHeight()-2);
							Graphics2D g2d = (Graphics2D) g;
					        g2d.setStroke(new BasicStroke(2));
							g2d.setColor(Color.black);
							g2d.drawRect(1, 1, getWidth()-2, getHeight()-2);
							
						};
					};
					labelDestino.setBounds(casillaSeleccionadaX.get(0)-1, casillaSeleccionadaY.get(0)-1, casillaSeleccionadaX.get(1)-casillaSeleccionadaX.get(0)+2, casillaSeleccionadaY.get(1)-casillaSeleccionadaY.get(0)+2);
					panelTablero.add(labelDestino);
					panelTablero.setComponentZOrder(labelDestino, 1);
					
					
					caminoMasCorto = caminoMasCorto(jugador.posicion[0], jugador.posicion[1], filaSeleccionada, columnaSeleccionada);
					
					for(int i = 1; i<caminoMasCorto.size()-1;i++) {
						JLabel siguienteCasilla= new JLabel() {
							protected void paintComponent(Graphics g) {
								
								g.setColor(Color.MAGENTA);
								g.fillRect(1, 1, getWidth()-2, getHeight()-2);
								Graphics2D g2d = (Graphics2D) g;
						        g2d.setStroke(new BasicStroke(2));
								g2d.setColor(Color.black);
								g2d.drawRect(1, 1, getWidth()-2, getHeight()-2);
								
							};
						};
						ArrayList<Integer> fila = coordsFilas.get(caminoMasCorto.get(i).get(0));
						ArrayList<Integer> columna = coordsColumnas.get(caminoMasCorto.get(i).get(1));
						siguienteCasilla.setBounds(columna.get(0)-1, fila.get(0)-1, columna.get(1)-columna.get(0)+2, fila.get(1)-fila.get(0)+2);
						panelTablero.add(siguienteCasilla);
						casillasDelCamino.add(siguienteCasilla);
						panelTablero.setComponentZOrder(siguienteCasilla, 1);
						panelTablero.repaint();
					}
					
					moverPersonaje();
				}
			}
		});
	}
	public void moverPersonaje() {
		enum Direccion{Arriba,Abajo,Iquierda,Derecha}
		Thread hiloMoverPersonaje = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ImageIcon sprite = null;
				Jugador jugador = Gestion.jugadores.get(Gestion.getNumTurno());
				TipoSprite direccion = TipoSprite.AndarAbajo;
				ArrayList<Integer>ubicacionActual = new ArrayList<>();
				int casillaActualY = Gestion.jugadores.get(Gestion.getNumTurno()).posicion[0];
				int casillaActualX = Gestion.jugadores.get(Gestion.getNumTurno()).posicion[1];
				ArrayList<Integer>coordsFilaActual = coordsFilas.get(casillaActualY);
				ArrayList<Integer>coordsColumnaActual = coordsColumnas.get(casillaActualX);
				int yActual = (coordsFilaActual.get(1)+coordsFilaActual.get(0))/2-labelJugador.getHeight()+5;
				int xActual = (coordsColumnaActual.get(1)+coordsColumnaActual.get(0))/2-labelJugador.getWidth()/2;
				ubicacionActual.add(yActual);
				ubicacionActual.add(xActual);
				int j=0;
				int foto = 0;
				for (int i = 0; i<caminoMasCorto.size();i++) {
					ArrayList<Integer>casillaDestino=caminoMasCorto.get(i);
					ArrayList<Integer>coordsFilaDestino= coordsFilas.get(casillaDestino.get(0));
					ArrayList<Integer>coordsColumnaDestino= coordsColumnas.get(casillaDestino.get(1));
					int yDestino = (coordsFilaDestino.get(1)+coordsFilaDestino.get(0))/2-labelJugador.getHeight()+5;
					int xDestino = (coordsColumnaDestino.get(1)+coordsColumnaDestino.get(0))/2-labelJugador.getWidth()/2;
					boolean coordsDiferentes = true;
					while(coordsDiferentes) {
						if(yDestino<yActual) {
							yActual--;
							if(direccion==TipoSprite.AndarArriba) {
								j = (j+1)%5;
							}else {
								direccion = TipoSprite.AndarArriba;
								j=0;
								foto=0;
							}
						}else if(yDestino>yActual){
							yActual++;
							if(direccion==TipoSprite.AndarAbajo) {
								j = (j+1)%5;
							}else {
								direccion = TipoSprite.AndarAbajo;
								j=0;
								foto=0;
							}
							
						}else if(xDestino<xActual) {
							xActual--;
							if(direccion==TipoSprite.AndarIzquierda) {
								j = (j+1)%5;
							}else {
								direccion = TipoSprite.AndarIzquierda;
								j=0;
								foto=0;
							}
						}else if(xDestino>xActual) {
							xActual++;
							if(direccion==TipoSprite.AndarDerecha) {
								j = (j+1)%5;
							}else {
								direccion = TipoSprite.AndarDerecha;
								j=0;
								foto=0;
							}
						}else {
							coordsDiferentes=false;
						}
						if(j==0) {
							if (direccion ==TipoSprite.AndarAbajo||direccion==TipoSprite.AndarArriba) {
								if(foto==0) {
									foto++;
								}
							}
							sprite = new ImageIcon(Gestion.sprites.get(jugador.getPersonaje().getNombre()).get(direccion).get(foto));
							labelJugador.setIcon(sprite);
							foto = (foto+1)%Gestion.sprites.get(jugador.getPersonaje().getNombre()).get(direccion).size();
						}
						labelJugador.setBounds(xActual,yActual,labelJugador.getWidth(),labelJugador.getHeight());
						try {
							Thread.sleep(8);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(i>0 && i<casillasDelCamino.size()) {
						panelTablero.remove(casillasDelCamino.get(i));
						panelTablero.repaint();
					}else if(i==0) {
						panelTablero.remove(casillasDelCamino.get(0));
						panelTablero.repaint();
					}
				}
				sprite = new ImageIcon(Gestion.sprites.get(jugador.getPersonaje().getNombre()).get(TipoSprite.AndarAbajo).get(0));
				labelJugador.setIcon(sprite);
				jugador.posicion= new int[] {filaSeleccionada,columnaSeleccionada};
			}
		});
		hiloMoverPersonaje.start();
		
	}
	
	public static void main(String[] args) {
		VentanaTablero v = new VentanaTablero();
		for(ArrayList<Integer>a:Gestion.tablero) {
			for(Integer i: a) {
				if(i==1) {
					System.out.print("()");
				}else if(i==0){
					System.out.print("  ");
				}
				else {
					System.out.print(i+" ");
				}
			}
			System.out.println("");
		}
//		Gestion.crearTablero(Gestion.numFilas, Gestion.numColumnas);
//		System.out.println(v.caminoMasCorto(0,6, 20,22));
		
	}
	
}
