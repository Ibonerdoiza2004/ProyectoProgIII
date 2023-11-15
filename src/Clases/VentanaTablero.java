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
	public VentanaTablero() {
		Dimension sizePantalla = Toolkit.getDefaultToolkit().getScreenSize();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		setSize(sizePantalla);
		setLayout(null);
		int altoBoton = 100;
		//PanelDesplegable
				panelDesplegable = new JPanel() {
					@Override
					public void paintComponent(Graphics g) {
						System.out.println(getWidth());
						ImageIcon iconoLista = new ImageIcon(getClass().getResource("seis.jpg"));
						Image imagenLista = iconoLista.getImage();
						
		                g.drawImage(imagenLista, 0, altoBoton, getWidth(), getHeight()-altoBoton, this);
					}
				};
				panelDesplegable.setLayout(null);
				panelDesplegable.setBounds((int)sizePantalla.getHeight(), (int)sizePantalla.getHeight(), (int)(sizePantalla.getWidth()-sizePantalla.getHeight()),(int)sizePantalla.getHeight()-2*altoBoton);
				
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
				System.out.println(getWidth());
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
				
				Thread t1 = new Thread(new Runnable() {
					@Override
					public void run() {
						int coordX = (int)(sizePantalla.getHeight());
						int ancho = (int)(sizePantalla.getWidth()-coordX);
						double coordY = sizePantalla.getHeight();
						int alto = (int)(sizePantalla.getHeight()-2*altoBoton);
						botonDesplegar.setEnabled(false);
						while((int)coordY>2*altoBoton) {
							
							coordY = coordY-0.2;
							System.out.println(coordY);
							panelDesplegable.setBounds(coordX,(int)coordY,ancho,alto);
						}
						botonPlegar.setEnabled(true);
					}
				});
				t1.start();
				System.out.println(botonPlegar.getSize()+""+botonPlegar.getBounds());
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
						double coordY = 2*altoBoton;
						int alto = (int)(sizePantalla.getHeight()-2*altoBoton);
						while((int)coordY<=sizePantalla.getHeight()) {
							coordY = coordY+0.07;
							System.out.println(coordY);
							panelDesplegable.setBounds(coordX,(int)coordY,ancho,alto);
						}
						botonDesplegar.setEnabled(true);
					}
				});
				t2.start();
				
				System.out.println(botonPlegar.getSize()+""+botonPlegar.getBounds());
				System.out.println(botonPlegar.getSize()+""+botonPlegar.getBounds());
			}
		});
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new VentanaTablero();
	}
}
