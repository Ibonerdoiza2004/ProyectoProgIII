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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VentanaTablero extends JPanel{
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
	Jugador jugador = Gestion.jugadores.get(Gestion.getNumTurno());
	JLabel labelTablero = new JLabel();
	JPanel panelDerecha = new JPanel();
	HashMap<ArrayList<Integer>,JLabel>labelCasillas;
	VentanaDado panelDados = new VentanaDado((int)Gestion.sizePantalla.getWidth()-(int)Gestion.sizePantalla.getHeight(), (int)Gestion.sizePantalla.getHeight()-altoBoton, 0);
	JButton botonDesplegar;
	JButton botonPlegar;
	JPanel panelDesplegable = new JPanel();
	JPanel panelLista;
	JPanel panelTablero;
	ArrayList<JLabel>casillasDelCamino = new ArrayList<>();
	boolean estadoBoton =true;
	
	
	public VentanaTablero() {
		setSize(Gestion.sizePantalla);
		setLayout(null);
		ImageIcon iconFlechaArriba = new ImageIcon(getClass().getResource("flechaArriba.png"));
        Image flechaArriba = iconFlechaArriba.getImage();
        ImageIcon iconFlechaAbajo = new ImageIcon(getClass().getResource("flechaAbajo.png"));
        Image flechaAbajo = iconFlechaAbajo.getImage();
		int anchoSimbolos = flechaArriba.getWidth(null)*altoBoton/flechaArriba.getHeight(null);
		int centroBotones = (int)(Gestion.sizePantalla.getWidth()-Gestion.sizePantalla.getHeight())/2;        
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

		panelDerecha.setLayout(null);
		panelLista = new JPanel(null);
		panelLista.setBounds(0, altoBoton, (int)(Gestion.sizePantalla.getWidth()-Gestion.sizePantalla.getHeight()),(int)Gestion.sizePantalla.getHeight()-altoBoton-inicioPanelDesplegable);
		panelLista.add(new Lista(panelLista, Gestion.getNumTurno()).sPane);
		panelDesplegable.setLayout(null);
		panelDesplegable.setBounds(0, (int)Gestion.sizePantalla.getHeight(), (int)(Gestion.sizePantalla.getWidth()-Gestion.sizePantalla.getHeight()),(int)Gestion.sizePantalla.getHeight()-2*altoBoton);
		panelDesplegable.add(panelLista);
		botonPlegar = new JButton() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(flechaAbajo, centroBotones-anchoSimbolos/2, 0, anchoSimbolos, altoBoton, this);
				
			}
		};
		botonPlegar.setFocusable(false);
		botonPlegar.setVerticalAlignment(SwingConstants.CENTER);
		botonPlegar.setBounds(0,0,(int)(Gestion.sizePantalla.getWidth()-Gestion.sizePantalla.getHeight()), altoBoton);
			
		panelDesplegable.add(botonPlegar);
		panelDerecha.add(panelDesplegable);

		//Panel Derecha
			//PanelDados
		
		panelDados.setOpaque(false);
		panelDerecha.add(panelDados);
		
			//PanelBoton
		botonDesplegar = new JButton() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(flechaArriba, centroBotones-anchoSimbolos/2, 0, anchoSimbolos, altoBoton, this);
				
			}
		};
		botonDesplegar.setFocusable(false);
		botonDesplegar.setVerticalAlignment(SwingConstants.CENTER);
		botonDesplegar.setBounds(0, (int)Gestion.sizePantalla.getHeight()-altoBoton, (int)(Gestion.sizePantalla.getWidth()-Gestion.sizePantalla.getHeight()),altoBoton );
		panelDerecha.add(botonDesplegar);
		
		panelDerecha.setBounds((int)Gestion.sizePantalla.getHeight(), 0, (int)Gestion.sizePantalla.getWidth(),(int)Gestion.sizePantalla.getHeight());
		add(panelDerecha);
		if(Gestion.jugadores.get(Gestion.getNumTurno()).npc) {
			panelDados.btnTirar.setEnabled(false);
			botonDesplegar.setEnabled(false);
		}
		botonDesplegar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelDesplegable.setVisible(true);
				estadoBoton = panelDados.btnTirar.isEnabled();
				panelDados.btnTirar.setEnabled(false);
				botonPlegar.setEnabled(false);
				botonDesplegar.setVisible(false);
				
				Thread t1 = new Thread(new Runnable() {
					@Override
					public void run() {
						int coordX = 0;
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
								e.printStackTrace();
							}
						}
						panelDados.setVisible(false);
						botonPlegar.setEnabled(true);
						
					}
				});
				t1.start();
			}
		});

		botonPlegar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				botonPlegar.setEnabled(false);
				panelDados.setVisible(true);
				Thread t2 = new Thread(new Runnable() {
					@Override
					public void run() {
						int coordX = 0;
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
								e.printStackTrace();
							}
						}
						botonDesplegar.setVisible(true);
						panelDados.btnTirar.setEnabled(estadoBoton);
						panelDesplegable.setVisible(false);
					}
				});
				t2.start();
				
			}
		});
		crearCasillas();
		dibujarSprites();
		String lockAnyadirALaVentana = "AnyadirALaVentana";
		synchronized (lockAnyadirALaVentana) {
			try {
				lockAnyadirALaVentana.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Gestion.ventanaJuego.add(this);
		Gestion.ventanaJuego.repaint();
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
	
	public void dibujarSprites() {
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
	//En este método se crea una matriz de 23x23 para representar el tablero. Añade un 1 en las posiciones que son utilizables, 
		//es decir, que tienen casillas, y añade un 0 en las posiciones que no lo son (habitaciones). 
		//Una vez creado el tablero, cuando el jugador tira los dados, hay una función recursiva que recibe la posición del jugador
		//y los movimientos que tiene y calcula a qué casillas puede llegar
		//VENTAJAS: Es mucho más eficiente que el otro método (Comprobar con 12 movimientos)
		//DESVENTAJAS: Solo calcula los movimientos para la casilla actual (El otro método se puede cargar antes de que empiece 
		//             la partida y reutilizarlo)
		public  HashMap<String,ArrayList<ArrayList<Integer>>>calcularMovimiento(int fila, int columna, int movimiento, ArrayList<ArrayList<Integer>> tablero){
			ArrayList <ArrayList<Integer>> casillasPosibles = new ArrayList<>();
			ArrayList <ArrayList<Integer>> puertasPosibles = new ArrayList<>();
			HashMap<String,ArrayList <ArrayList<Integer>>>movimientosPosibles = new HashMap<>();
			movimientoCasillasRecursive(fila, columna, movimiento, 0, casillasPosibles, puertasPosibles, tablero);
			movimientosPosibles.put("casillasPosibles", casillasPosibles);
			movimientosPosibles.put("puertasPosibles", puertasPosibles);
			return movimientosPosibles;
		}
		
		private void  movimientoCasillasRecursive(int fila, int columna, int movimiento, int iteracion, ArrayList <ArrayList<Integer>> casillasPosibles, ArrayList <ArrayList<Integer>> puertasPosibles, ArrayList<ArrayList<Integer>> tablero) {
			ArrayList<Integer> pos = new ArrayList<>();
			pos.add(fila);
			pos.add(columna);
			if(!casillasPosibles.contains(pos)&&!puertasPosibles.contains(pos)) {
				if(tablero.get(fila).get(columna)==1) {
					casillasPosibles.add(pos);
				}else {
					if(tablero.get(fila).get(columna)!=Gestion.jugadores.get(Gestion.getNumTurno()).anteriorPuerta) {
						puertasPosibles.add(pos);
					}
				}
			}
			if(iteracion<movimiento&&tablero.get(fila).get(columna)!=11) {
				if((fila!=tablero.size()-1)&&(tablero.get(fila+1).get(columna)!=0)&&(tablero.get(fila+1).get(columna)!=Gestion.jugadores.get(Gestion.getNumTurno()).anteriorPuerta)) {
					movimientoCasillasRecursive(fila+1, columna, movimiento, iteracion+1, casillasPosibles, puertasPosibles, tablero);
				}
				if((fila!=0)&&(tablero.get(fila-1).get(columna)!=0)&&(tablero.get(fila-1).get(columna)!=Gestion.jugadores.get(Gestion.getNumTurno()).anteriorPuerta)) {
					movimientoCasillasRecursive(fila-1, columna, movimiento, iteracion+1, casillasPosibles, puertasPosibles, tablero);
				}
				if((columna!=tablero.get(fila).size()-1)&&(tablero.get(fila).get(columna+1)!=0)&&(tablero.get(fila).get(columna+1)!=Gestion.jugadores.get(Gestion.getNumTurno()).anteriorPuerta)) {
					movimientoCasillasRecursive(fila, columna+1, movimiento, iteracion+1, casillasPosibles, puertasPosibles, tablero);
				}
				if((columna!=0)&&(tablero.get(fila).get(columna-1)!=0)&&(tablero.get(fila).get(columna-1)!=Gestion.jugadores.get(Gestion.getNumTurno()).anteriorPuerta)) {	
					movimientoCasillasRecursive(fila, columna-1, movimiento, iteracion+1, casillasPosibles, puertasPosibles, tablero);
				}
				
			}
			
		}
	public void pintarCasillas() {		 
		if(Gestion.jugadores.get(Gestion.getNumTurno()).npc) {
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
		int movimientos = (panelDados.valorDado1+panelDados.valorDado2);
		HashMap<String,ArrayList<ArrayList<Integer>>>movimientosPosibles = calcularMovimiento(jugador.posicion[0], jugador.posicion[1], movimientos, Gestion.tablero);
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
		if(!Gestion.jugadores.get(Gestion.getNumTurno()).npc) {
			seleccionarCasilla();
		}else {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			Lógica mover
			
			ArrayList<Integer>casillaMasCercanaAPuerta = Gestion.logicaMover(jugador, movimientos);
			
			filaSeleccionada=casillaMasCercanaAPuerta.get(0);
			columnaSeleccionada=casillaMasCercanaAPuerta.get(1);
			labelSeleccionada = labelCasillas.get(casillaMasCercanaAPuerta);
			casillaSeleccionadaY = coordsFilas.get(casillaMasCercanaAPuerta.get(0));
			casillaSeleccionadaX = coordsColumnas.get(casillaMasCercanaAPuerta.get(1));
			alSeleccionarCasilla();
		}
	}
	public void seleccionarCasilla() {
		panelTablero.addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				if(!Gestion.jugadores.get(Gestion.getNumTurno()).npc) {
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
				
				
			}
			
		});
		panelTablero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				alSeleccionarCasilla();				}
			
		});
	}
	public void moverPersonaje() {
		enum Direccion{Arriba,Abajo,Iquierda,Derecha}
		Thread hiloMoverPersonaje = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ImageIcon sprite = null;
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
				if(Gestion.tablero.get(filaSeleccionada).get(columnaSeleccionada)!=1) {
					Gestion.jugadores.get(Gestion.getNumTurno()).anteriorPuerta=Gestion.tablero.get(filaSeleccionada).get(columnaSeleccionada);
				}
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				botonDesplegar.setEnabled(false);
				botonPlegar.setEnabled(false);
				if(Gestion.tablero.get(jugador.posicion[0]).get(jugador.posicion[1])==1){
					Gestion.aumentarTurno();
					new VentanaTexto("TURNO DE "+Gestion.jugadores.get(Gestion.getNumTurno()).getPersonaje().getNombre().toString().toUpperCase(),Gestion.getNumTurno());
					eliminarPanel();
					VentanaTablero v = new VentanaTablero();
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
					if(Gestion.tablero.get(jugador.posicion[0]).get(jugador.posicion[1])!=11){
						Gestion.cartasEnsenyadas.clear();
						//Aquí se añade un nuevo panel con VentanaAcusacion
						if(Gestion.jugadores.get(Gestion.getNumTurno()).npc) {
//							Hacer acusacion
							Gestion.acusacion=new ArrayList<>();
							Gestion.logicaAcusar(jugador);
							Gestion.acusacion.add(Gestion.datosPartida.lugares.get(Gestion.tablero.get(jugador.posicion[0]).get(jugador.posicion[1])-2));
							Gestion.aumentarTurnosSinEnsenyar(Gestion.datosPartida.lugares.get(Gestion.tablero.get(jugador.posicion[0]).get(jugador.posicion[1])-2), jugador);
							int jug = (Gestion.getNumTurno()+1)%Gestion.jugadores.size();
							while(Gestion.jugadores.get(jug).npc&&Gestion.jugadores.get(jug)!=Gestion.jugadores.get(Gestion.getNumTurno())) {
//								Enseñar carta
								ArrayList<Asesinato>posiblesCartas = new ArrayList<>();
								for (Asesinato carta: Gestion.jugadores.get(jug).cartas) {
									if(Gestion.acusacion.contains(carta)) {
										posiblesCartas.add(carta);
									}
								}
								if(!posiblesCartas.isEmpty()) {
									Gestion.cartasEnsenyadas.put(posiblesCartas.get((int)(Math.random()*posiblesCartas.size())), Gestion.jugadores.get(jug));
								}
								jug = (jug+1)%Gestion.jugadores.size();
							}
							if(Gestion.jugadores.get(jug)!=Gestion.jugadores.get(Gestion.getNumTurno())) {
								new VentanaTexto("TURNO DE "+Gestion.jugadores.get(jug).getPersonaje().getNombre().toString().toUpperCase(),jug);
								eliminarPanel();
								VentanaDarCarta v = new VentanaDarCarta(jug);
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
								Gestion.logicaMarcarLista(Gestion.jugadores.get(Gestion.getNumTurno()));
								Gestion.aumentarTurno();
								new VentanaTexto("TURNO DE "+Gestion.jugadores.get(Gestion.getNumTurno()).getPersonaje().getNombre().toString().toUpperCase(),Gestion.getNumTurno());
								eliminarPanel();
								VentanaTablero v = new VentanaTablero();
							}
						}else {
							VentanaAcusacion v=new VentanaAcusacion();
							v.setVisible(true);
							eliminarPanel();
						}
					}else {
						if(Gestion.jugadores.get(Gestion.getNumTurno()).npc) {
							Gestion.acusacion.clear();
							Gestion.logicaAcusar(jugador);
							if(Gestion.acusacion.get(0).equals(Gestion.datosPartida.implicados.get(Implicados.PERSONA))&&
									Gestion.acusacion.get(1).equals(Gestion.datosPartida.implicados.get(Implicados.ARMA))&&
									Gestion.acusacion.get(2).equals(Gestion.datosPartida.implicados.get(Implicados.LUGAR))) {
								new VentanaVictoria();
								eliminarPanel();
							}else {
								new VentanaDerrota();
								eliminarPanel();
							}
						}else {
							new VentanaTexto("ACUSACIÓN FINAL DE "+Gestion.jugadores.get(Gestion.getNumTurno()).getPersonaje().getNombre().toString().toUpperCase(),Gestion.getNumTurno());
							eliminarPanel();
							VentanaAcusacion v=new VentanaAcusacion();
							String lockAnyadirALaVentana = "AnyadirALaVentana";
							synchronized (lockAnyadirALaVentana) {
								try {
									lockAnyadirALaVentana.wait();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							v.setVisible(true);
						}
					}
					
					
				}
			}
		});
		hiloMoverPersonaje.start();
		
	}
	public void alSeleccionarCasilla() {
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
			
			
			caminoMasCorto = Gestion.caminoMasCorto(jugador.posicion[0], jugador.posicion[1], filaSeleccionada, columnaSeleccionada);
			
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
	public void eliminarPanel() {
		Gestion.ventanaJuego.remove(this);
        Gestion.ventanaJuego.repaint();
	}
	
	
}