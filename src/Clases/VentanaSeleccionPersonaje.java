package Clases;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class VentanaSeleccionPersonaje extends JFrame{
	
	protected JLabel lblElige;
	protected JLabel j1;
	protected JButton continuar;
	protected JButton atras;
	protected ArrayList<Personaje> personajes;
	protected ArrayList<Personaje> elegidos;
	private int cont;
	private int posicion;
	
	public VentanaSeleccionPersonaje() {
		
		this.setVisible(true);
		this.setLayout(null);
		this.setFocusable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Dimension sizePantalla = Toolkit.getDefaultToolkit().getScreenSize();
		double ancho = sizePantalla.getWidth();
		double altura = sizePantalla.getHeight();
		
		cont = 1;
		lblElige = new JLabel("J"+cont+" ELIGE TU PERSONAJE");
		lblElige.setBounds((int) Math.round(3*ancho/34), (int) Math.round(1*altura/19), 
				(int) Math.round(28*ancho/34), (int) Math.round(3*altura/19));
		Font eliFont = lblElige.getFont();
		lblElige.setFont(new Font(eliFont.getName(), eliFont.BOLD, 40));
		lblElige.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblElige);
		
		j1 = new JLabel ("J1");
		j1.setBounds((int) Math.round(2*ancho/34), (int) Math.round(5*altura/19), 
				(int) Math.round(2*ancho/34), (int) Math.round(2*altura/19));
		j1.setFont(new Font(eliFont.getName(), eliFont.BOLD, 25));
		j1.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(j1);
		
		continuar = new JButton("Continuar");
		continuar.setBounds((int) Math.round(7*ancho/34), (int) Math.round(15*altura/19), 
				(int) Math.round(7*ancho/34), (int) Math.round(2*altura/19));
		this.add(continuar);
		
		atras = new JButton ("Atras");
		atras.setBounds((int) Math.round(21*ancho/34), (int) Math.round(15*altura/19), 
				(int) Math.round(7*ancho/34), (int) Math.round(2*altura/19));
		this.add(atras);
		
		
		posicion = 1;
		
		this.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		            if (posicion == 1 || posicion == 5) {
		                j1.setLocation(j1.getX() + (int) Math.round(5*ancho/34), j1.getY());
		                if (posicion < 6) posicion++;
		            } else if (posicion >= 2 && posicion <= 4) {
		                j1.setLocation(j1.getX() + (int) Math.round(6*ancho/34), j1.getY());
		                if (posicion < 6) posicion++;
		            }
		        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) { 
		            if (posicion == 6 || posicion == 2) {
		                j1.setLocation(j1.getX() - (int) Math.round(5*ancho/34), j1.getY());
		                if (posicion > 1) posicion--;
		            } else if (posicion >= 3 && posicion <= 5) {
		                j1.setLocation(j1.getX() - (int) Math.round(6*ancho/34), j1.getY());
		                if (posicion > 1) posicion--;
		            }
		        }
		        
		    }
		});
		
		continuar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}
		
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VentanaSeleccionPersonaje v = new VentanaSeleccionPersonaje();

	}
	

}
