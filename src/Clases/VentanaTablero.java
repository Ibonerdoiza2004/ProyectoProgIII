package Clases;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VentanaTablero extends JFrame{
	int coorXInicioTablero;
	int coorXFinalTablero;
	int coorYInicioTablero;
	int coorYFinalTablero;
	double anchoColumnaTablero;
	double altoFilaTablero;
	HashMap<Integer, ArrayList<Integer>>coordsFilas = null;
	HashMap<Integer, ArrayList<Integer>>coordsColumnas = null;
	JLabel labelTablero = new JLabel();
	JPanel panelDerecha = new JPanel();
	JPanel panelDados;
	JButton botonDesplegar = new JButton();
	JButton botonPlegar = new JButton();
	JPanel panelDesplegable = new JPanel();
	JPanel panelLista;
	JPanel panelTablero;
	public VentanaTablero() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(Gestion.sizePantalla);
		setLayout(null);
		int altoBoton = 100;
		coorXInicioTablero =(int) (2*Gestion.sizePantalla.getHeight())/37;
		coorXFinalTablero =(int) (Gestion.sizePantalla.getHeight()-(Gestion.sizePantalla.getHeight())/19);
		coorYInicioTablero = (int) (Gestion.sizePantalla.getHeight())/29;
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
		int inicioPanelDesplegable = 2*altoBoton;
		panelLista = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				ImageIcon iconoLista = new ImageIcon(getClass().getResource("seis.jpg"));
				Image imagenLista = iconoLista.getImage();
				g.drawImage(imagenLista, 0, 0, getWidth(), getHeight(), this);
			}
		};
		panelDesplegable.setLayout(null);
		panelLista.setBounds(0, altoBoton, (int)(Gestion.sizePantalla.getWidth()-Gestion.sizePantalla.getHeight()),(int)Gestion.sizePantalla.getHeight()-3*altoBoton);
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
		JPanel panelDados = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				ImageIcon iconoDados = new ImageIcon(getClass().getResource("cinco.jpg"));
				Image imagenDados = iconoDados.getImage();
                g.drawImage(imagenDados, 0, 0, getWidth(), getHeight(), this);
			}
		};
		
		panelDados.setSize((int)Gestion.sizePantalla.getWidth()-(int)Gestion.sizePantalla.getHeight(), (int)Gestion.sizePantalla.getHeight()-altoBoton);
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
								Thread.sleep(16);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
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
		
		setVisible(true);
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
	
	public static void main(String[] args) {
		VentanaTablero v = new VentanaTablero();
		v.crearCasillas();
		v.revalidate();
		v.repaint();
		Gestion.crearTablero(Gestion.numFilas, Gestion.numColumnas);
		System.out.println(v.caminoMasCorto(0,6, 20,22));
		
	}
}
