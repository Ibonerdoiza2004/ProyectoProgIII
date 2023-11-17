package Clases;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;

public class VentanaTablero extends JFrame{
	JLabel labelTablero = new JLabel();
	JPanel panelDerecha = new JPanel();
	JPanel panelDados;
	JButton botonDesplegar = new JButton();
	JButton botonPlegar = new JButton();
	JPanel panelDesplegable = new JPanel();
	JPanel panelLista;
	public VentanaTablero() {
		Dimension sizePantalla = Toolkit.getDefaultToolkit().getScreenSize();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		setSize(sizePantalla);
		setLayout(null);
		int altoBoton = 100;
		
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
		panelLista.setBounds(0, altoBoton, (int)(sizePantalla.getWidth()-sizePantalla.getHeight()),(int)sizePantalla.getHeight()-3*altoBoton);
		panelDesplegable.add(panelLista);
		panelDesplegable.setBounds((int)sizePantalla.getHeight()-altoBoton, (int)sizePantalla.getHeight(), (int)(sizePantalla.getWidth()-sizePantalla.getHeight()),(int)sizePantalla.getHeight()-2*altoBoton);
		
		botonPlegar.setFocusable(false);
		botonPlegar.setText("\\/");
		botonPlegar.setVerticalAlignment(SwingConstants.CENTER);
		botonPlegar.setBounds(0,0,(int)(sizePantalla.getWidth()-sizePantalla.getHeight()), altoBoton);
			
		panelDesplegable.add(botonPlegar);
		add(panelDesplegable);
		
		//PanelIzquierda
		ImageIcon iconoTablero =new ImageIcon( new ImageIcon(getClass().getResource("tablero.jpg")).getImage().getScaledInstance((int)sizePantalla.getHeight(), (int)sizePantalla.getHeight(), java.awt.Image.SCALE_SMOOTH));
		labelTablero.setIcon(iconoTablero);
		labelTablero.setBounds(0,0,(int)sizePantalla.getHeight(),(int)sizePantalla.getHeight());
		add(labelTablero);
		
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
		
		panelDados.setSize((int)sizePantalla.getWidth()-(int)sizePantalla.getHeight(), (int)sizePantalla.getHeight()-altoBoton);
		panelDerecha.add(panelDados);
			//PanelBoton
		botonDesplegar.setFocusable(false);
		botonDesplegar.setText("/\\");
		botonDesplegar.setVerticalAlignment(SwingConstants.CENTER);
		botonDesplegar.setBounds(0, (int)sizePantalla.getHeight()-altoBoton, (int)(sizePantalla.getWidth()-sizePantalla.getHeight()),altoBoton );
		panelDerecha.add(botonDesplegar);
		
		panelDerecha.setBounds((int)sizePantalla.getHeight(), 0, (int)sizePantalla.getWidth(),(int)sizePantalla.getHeight());
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
						int coordX = (int)(sizePantalla.getHeight());
						int ancho = (int)(sizePantalla.getWidth()-coordX);
						int coordY = (int)sizePantalla.getHeight();
						int alto = (int)(sizePantalla.getHeight()-inicioPanelDesplegable);
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
						int coordX = (int)(sizePantalla.getHeight());
						int ancho = (int)(sizePantalla.getWidth()-coordX);
						int coordY = inicioPanelDesplegable;
						int alto = (int)(sizePantalla.getHeight()-2*altoBoton);
						while((int)coordY<=sizePantalla.getHeight()-altoBoton) {
							if(sizePantalla.getHeight()-altoBoton-coordY<5) {
								coordY = coordY+1;
							}else if (sizePantalla.getHeight()-altoBoton-coordY<alto/28) {
								coordY = coordY+4;
							}else if (sizePantalla.getHeight()-altoBoton-coordY<alto/14) {
								coordY = coordY+8;
							}else if(sizePantalla.getHeight()-altoBoton-coordY<alto/8) {
								coordY = coordY+16;
							}else if(sizePantalla.getHeight()-altoBoton-coordY<alto/4) {
								coordY = coordY+32;
							}else if(sizePantalla.getHeight()-altoBoton-coordY<alto/2) {
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
	
	public static void main(String[] args) {
		new VentanaTablero();
	}
}
