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
				panelDesplegable.setBounds((int)sizePantalla.getHeight(), 2*altoBoton, (int)(sizePantalla.getWidth()-sizePantalla.getHeight()),(int)sizePantalla.getHeight()-2*altoBoton);
				
				botonPlegar.setFocusable(false);
				botonPlegar.setText("\\/");
				botonPlegar.setVerticalAlignment(SwingConstants.CENTER);
				botonPlegar.setBounds(0,0,(int)(sizePantalla.getWidth()-sizePantalla.getHeight()), altoBoton);
				
				panelDesplegable.add(botonPlegar);
				panelDesplegable.setVisible(false);
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
				panelDesplegable.setVisible(true);
				botonDesplegar.setVisible(false);
				System.out.println(botonPlegar.getSize()+""+botonPlegar.getBounds());
			}
		});
		botonPlegar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelDesplegable.setVisible(false);
				botonDesplegar.setVisible(true);
				System.out.println(botonPlegar.getSize()+""+botonPlegar.getBounds());
			}
		});
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new VentanaTablero();
	}
}
