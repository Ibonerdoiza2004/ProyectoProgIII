package Clases;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import BaseDeDatos.MainBD;


public class VentanaSeleccionPersonaje extends JPanel{
	
	protected JLabel lblElige;
	protected JLabel j1;
	protected JLabel j2;
	protected JLabel j3;
	protected JLabel j4;
	protected JLabel j5;
	protected JLabel j6;
	protected boolean p1ocp;
	protected boolean p2ocp;
	protected boolean p3ocp;
	protected boolean p4ocp;
	protected boolean p5ocp;
	protected boolean p6ocp;
	protected JButton continuar;
	protected JButton atras;
	private int cont;
	protected int jugadores;
	protected JLabel lblScarlett;
	
	private static MainBD bd;
	private VentanaNJugadores vn;
	
	public VentanaSeleccionPersonaje(MainBD bd) {
		
		this.bd = bd;
		
		this.setLayout(null);
		this.setFocusable(true);
		
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
		lblElige.setForeground(Color.WHITE);
		this.add(lblElige);
		
		continuar = new JButton("Continuar");
		continuar.setFocusable(false);
		continuar.setBounds((int) Math.round(7*ancho/34), (int) Math.round(15*altura/19), 
				(int) Math.round(7*ancho/34), (int) Math.round(2*altura/19));
		this.add(continuar);
		
		atras = new JButton ("Atras");
		atras.setFocusable(false);
		atras.setBounds((int) Math.round(21*ancho/34), (int) Math.round(15*altura/19), 
				(int) Math.round(7*ancho/34), (int) Math.round(2*altura/19));
		this.add(atras);
		
		ArrayList<Integer> posicion1 = new ArrayList<Integer>();
		posicion1.add((int) Math.round(2*ancho/34));
		posicion1.add((int) Math.round(5*altura/19));
		
		ArrayList<Integer> posicion2 = new ArrayList<Integer>();
		posicion2.add(posicion1.get(0) + (int) Math.round(5*ancho/34));
		posicion2.add((int) Math.round(5*altura/19));
		
		ArrayList<Integer> posicion3 = new ArrayList<Integer>();
		posicion3.add(posicion2.get(0) + (int) Math.round(6*ancho/34));
		posicion3.add((int) Math.round(5*altura/19));
		
		ArrayList<Integer> posicion4 = new ArrayList<Integer>();
		posicion4.add(posicion3.get(0) + (int) Math.round(6*ancho/34));
		posicion4.add((int) Math.round(5*altura/19));
		
		ArrayList<Integer> posicion5 = new ArrayList<Integer>();
		posicion5.add(posicion4.get(0) + (int) Math.round(6*ancho/34));
		posicion5.add((int) Math.round(5*altura/19));
		
		ArrayList<Integer> posicion6 = new ArrayList<Integer>();
		posicion6.add(posicion5.get(0) + (int) Math.round(5*ancho/34));
		posicion6.add((int) Math.round(5*altura/19));
		
		j1 = new JLabel ("J1");
		j1.setBounds(posicion1.get(0), posicion1.get(1), 
				(int) Math.round(2*ancho/34), (int) Math.round(2*altura/19));
		p1ocp = true;
		j1.setFont(new Font(eliFont.getName(), eliFont.BOLD, 25));
		j1.setHorizontalAlignment(SwingConstants.CENTER);
		j1.setForeground(Color.WHITE);
		this.add(j1);
		
		j2 = new JLabel ("J2");
		j2.setBounds(posicion2.get(0), posicion1.get(1), 
				(int) Math.round(2*ancho/34), (int) Math.round(2*altura/19));
		j2.setVisible(false);
		j2.setFont(new Font(eliFont.getName(), eliFont.BOLD, 25));
		j2.setHorizontalAlignment(SwingConstants.CENTER);
		j2.setForeground(Color.WHITE);
		this.add(j2);
		
		
		j3 = new JLabel ("J3");
		j3.setBounds(posicion2.get(0), posicion1.get(1), 
				(int) Math.round(2*ancho/34), (int) Math.round(2*altura/19));
		j3.setVisible(false);
		j3.setFont(new Font(eliFont.getName(), eliFont.BOLD, 25));
		j3.setHorizontalAlignment(SwingConstants.CENTER);
		j3.setForeground(Color.WHITE);
		this.add(j3);
		
		j4 = new JLabel ("J4");
		j4.setBounds(posicion2.get(0), posicion1.get(1), 
				(int) Math.round(2*ancho/34), (int) Math.round(2*altura/19));
		j4.setVisible(false);
		j4.setFont(new Font(eliFont.getName(), eliFont.BOLD, 25));
		j4.setHorizontalAlignment(SwingConstants.CENTER);
		j4.setForeground(Color.WHITE);
		this.add(j4);
		
		j5 = new JLabel ("J5");
		j5.setBounds(posicion2.get(0), posicion1.get(1), 
				(int) Math.round(2*ancho/34), (int) Math.round(2*altura/19));
		j5.setVisible(false);
		j5.setFont(new Font(eliFont.getName(), eliFont.BOLD, 25));
		j5.setHorizontalAlignment(SwingConstants.CENTER);
		j5.setForeground(Color.WHITE);
		this.add(j5);
		
		j6 = new JLabel ("J6");
		j6.setBounds(posicion2.get(0), posicion1.get(1), 
				(int) Math.round(2*ancho/34), (int) Math.round(2*altura/19));
		j6.setVisible(false);
		j6.setFont(new Font(eliFont.getName(), eliFont.BOLD, 25));
		j6.setHorizontalAlignment(SwingConstants.CENTER);
		j6.setForeground(Color.WHITE);
		this.add(j6);
		
		this.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent e) {
		    	if (cont == 1) {
		    		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		    			if (j1.getX() == posicion1.get(0)) {
		    				p1ocp = false;
		    				j1.setLocation(posicion2.get(0), posicion1.get(1));
		    				p2ocp = true;
		    			} else if (j1.getX() == posicion2.get(0)) {
		    				p2ocp = false;
		    				j1.setLocation(posicion3.get(0), posicion1.get(1));
		    				p3ocp = true;
		    			} else if (j1.getX() == posicion3.get(0)) {
		    				p3ocp = false;
		    				j1.setLocation(posicion4.get(0), posicion1.get(1));
		    				p4ocp = true;
		    			} else if (j1.getX() == posicion4.get(0)) {
		    				p4ocp = false;
		    				j1.setLocation(posicion5.get(0), posicion1.get(1));
		    				p5ocp = true;
		    			} else if (j1.getX() == posicion5.get(0)) {
		    				p5ocp = false;
		    				j1.setLocation(posicion6.get(0), posicion1.get(1));
		    				p6ocp = true;
		    			}
		    			
		    		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) { 
		    			if (j1.getX() == posicion6.get(0)) {
		    				p6ocp = false;
		    				j1.setLocation(posicion5.get(0), posicion1.get(1));
		    				p5ocp = true;
		    			} else if (j1.getX() == posicion5.get(0)) {
		    				p5ocp = false;
		    				j1.setLocation(posicion4.get(0), posicion1.get(1));
		    				p4ocp = true;
		    			} else if (j1.getX() == posicion4.get(0)) {
		    				p4ocp = false;
		    				j1.setLocation(posicion3.get(0), posicion1.get(1));
		    				p3ocp = true;
		    			} else if (j1.getX() == posicion3.get(0)) {
		    				p3ocp = false;
		    				j1.setLocation(posicion2.get(0), posicion1.get(1));
		    				p2ocp = true;
		    			} else if (j1.getX() == posicion2.get(0)) {
		    				p2ocp = false;
		    				j1.setLocation(posicion1.get(0), posicion1.get(1));
		    				p1ocp = true;
		    			}
		    		}
		    	} else if (cont == 2) {
		    		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		    		    if ((j2.getX() == posicion1.get(0)) & !p2ocp) {
		    		    	p1ocp = false;
		    		        j2.setLocation(posicion2.get(0), posicion1.get(1));
		    		        p2ocp = true;
		    		    } else if ((j2.getX() == posicion1.get(0)) & p2ocp) {
		    		    	p1ocp = false;
		    		    	j2.setLocation(posicion3.get(0), posicion1.get(1));
		    		    	p3ocp = true;
		    		    } 
		    		    
		    		    else if ((j2.getX() == posicion2.get(0)) & !p3ocp) {
		    		    	p2ocp = false;
		    		        j2.setLocation(posicion3.get(0), posicion1.get(1));
		    		        p3ocp = true;
		    		    } else if ((j2.getX() == posicion2.get(0)) & p3ocp) {
		    		    	p2ocp = false;
		    		    	j2.setLocation(posicion4.get(0), posicion1.get(1));
		    		    	p4ocp = true;
		    		    }
		    		    
		    		    else if ((j2.getX() == posicion3.get(0)) & !p4ocp) {
		    		    	p3ocp = false;
		    		        j2.setLocation(posicion4.get(0), posicion1.get(1));
		    		        p4ocp = true;
		    		    } else if ((j2.getX() == posicion3.get(0)) & p4ocp) {
		    		    	p3ocp = false;
		    		    	j2.setLocation(posicion5.get(0), posicion1.get(1));
		    		    	p5ocp = true;
		    		    }
		    		    
		    		    else if ((j2.getX() == posicion4.get(0)) & !p5ocp) {
		    		    	p4ocp = false;
		    		        j2.setLocation(posicion5.get(0), posicion1.get(1));
		    		        p5ocp = true;
		    		    } else if ((j2.getX() == posicion4.get(0)) & p5ocp) {
		    		    	p4ocp = false;
		    		    	j2.setLocation(posicion6.get(0), posicion1.get(1));
		    		    	p6ocp = true;
		    		    }
		    		    
		    		    else if ((j2.getX() == posicion5.get(0)) & !p6ocp) {
		    		    	p5ocp = false;
		    		        j2.setLocation(posicion6.get(0), posicion1.get(1));
		    		        p6ocp = true;
		    		    }
		    		    
		    		    
		    		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		    			
		    			 if ((j2.getX() == posicion6.get(0)) & !p5ocp) {
		    				 p6ocp = false;
			    		     j2.setLocation(posicion5.get(0), posicion1.get(1));
			    		     p5ocp = true;
			    		 } else if ((j2.getX() == posicion6.get(0)) & p5ocp) {
			    			 p6ocp = false;
			    			 j2.setLocation(posicion4.get(0), posicion1.get(1));
			    			 p4ocp = true;
			    		 }
		    			 
			    		 else if ((j2.getX() == posicion5.get(0)) & !p4ocp) {
			    			 p5ocp = false;
			    			 j2.setLocation(posicion4.get(0), posicion1.get(1));
			    			 p4ocp = true;
			    		} else if ((j2.getX() == posicion5.get(0)) & p4ocp) {
			    			 p5ocp = false;
			    			 j2.setLocation(posicion3.get(0), posicion1.get(1));
			    			 p3ocp = true;
			    			    
			    		} else if ((j2.getX() == posicion4.get(0)) & !p3ocp) {
			    			 p4ocp = false;
			    			 j2.setLocation(posicion3.get(0), posicion1.get(1));
			    			 p3ocp = true;
			    		} else if ((j2.getX() == posicion4.get(0)) & p3ocp) {
			    			 p4ocp = false;
			    			 j2.setLocation(posicion2.get(0), posicion1.get(1));
			    			 p2ocp = true;
			    			 
			    		} else if ((j2.getX() == posicion3.get(0)) & !p2ocp) {
			    		    p3ocp = false;
			    		    j2.setLocation(posicion2.get(0), posicion1.get(1));
			    		    p2ocp = true;
			    		} else if ((j2.getX() == posicion3.get(0)) & p2ocp) {
			    		    p3ocp = false;
			    		    j2.setLocation(posicion1.get(0), posicion1.get(1));
			    		    p1ocp = true;
		    		    } else if (j2.getX() == posicion2.get(0) & !p1ocp) {
		    		    	p2ocp = false;
		    		        j2.setLocation(posicion1.get(0), posicion1.get(1));
		    		        p1ocp = true;
		    		    }
		    		}
		    	} else if (cont == 3) {
		    		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		    		    if ((j3.getX() == posicion1.get(0)) & !p2ocp) {
		    		        p1ocp = false;
		    		        j3.setLocation(posicion2.get(0), posicion1.get(1));
		    		        p2ocp = true;
		    		    } else if ((j3.getX() == posicion1.get(0)) & p2ocp & !p3ocp) {
		    		        p1ocp = false;
		    		        j3.setLocation(posicion3.get(0), posicion1.get(1));
		    		        p3ocp = true;
		    		    } else if ((j3.getX() == posicion1.get(0)) & p2ocp & p3ocp) {
		    		        p1ocp = false;
		    		        j3.setLocation(posicion4.get(0), posicion1.get(1));
		    		        p4ocp = true;
		    		        
		    			} else if ((j3.getX() == posicion2.get(0)) & !p3ocp) {
		    		        p2ocp = false;
		    		        j3.setLocation(posicion3.get(0), posicion1.get(1));
		    		        p3ocp = true;
		    		    } else if ((j3.getX() == posicion2.get(0)) & p3ocp & !p4ocp) {
		    		        p2ocp = false;
		    		        j3.setLocation(posicion4.get(0), posicion1.get(1));
		    		        p4ocp = true;
		    		    } else if ((j3.getX() == posicion2.get(0)) & p3ocp & p4ocp) {
		    		        p1ocp = false;
		    		        j3.setLocation(posicion5.get(0), posicion1.get(1));
		    		        p5ocp = true;
		    		        
		    			} else if ((j3.getX() == posicion3.get(0)) & !p4ocp) {
		    		        p3ocp = false;
		    		        j3.setLocation(posicion4.get(0), posicion1.get(1));
		    		        p4ocp = true;
		    		    } else if ((j3.getX() == posicion3.get(0)) & p4ocp & !p5ocp) {
		    		        p3ocp = false;
		    		        j3.setLocation(posicion5.get(0), posicion1.get(1));
		    		        p5ocp = true;
		    		    } else if ((j3.getX() == posicion3.get(0)) & p4ocp & p5ocp) {
		    		        p1ocp = false;
		    		        j3.setLocation(posicion6.get(0), posicion1.get(1));
		    		        p6ocp = true;
		    		        
		    		    } else if ((j3.getX() == posicion4.get(0)) & !p5ocp) {
		    		        p4ocp = false;
		    		        j3.setLocation(posicion5.get(0), posicion1.get(1));
		    		        p5ocp = true;
		    		    } else if ((j3.getX() == posicion4.get(0)) & p5ocp & !p6ocp) {
		    		        p4ocp = false;
		    		        j3.setLocation(posicion6.get(0), posicion1.get(1));
		    		        p6ocp = true; 
		    		        
		    		    } else if ((j3.getX() == posicion5.get(0)) & !p6ocp) {
		    		        p5ocp = false;
		    		        j3.setLocation(posicion6.get(0), posicion1.get(1));
		    		        p6ocp = true;
		    		    }
		    		    
		    		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		    		    if ((j3.getX() == posicion6.get(0)) & !p5ocp) {
		    		        p6ocp = false;
		    		        j3.setLocation(posicion5.get(0), posicion1.get(1));
		    		        p5ocp = true;
		    		    } else if ((j3.getX() == posicion6.get(0)) & p5ocp & !p4ocp) {
		    		        p6ocp = false;
		    		        j3.setLocation(posicion4.get(0), posicion1.get(1));
		    		        p4ocp = true;
		    		    } else if ((j3.getX() == posicion6.get(0)) & p5ocp & p4ocp) {
		    		        p6ocp = false;
		    		        j3.setLocation(posicion3.get(0), posicion1.get(1));
		    		        p3ocp = true;
		    		    
		    		    } else if ((j3.getX() == posicion5.get(0)) & !p4ocp) {
		    		        p5ocp = false;
		    		        j3.setLocation(posicion4.get(0), posicion1.get(1));
		    		        p4ocp = true;
		    		    } else if ((j3.getX() == posicion5.get(0)) & p4ocp & !p3ocp) {
		    		        p5ocp = false;
		    		        j3.setLocation(posicion3.get(0), posicion1.get(1));
		    		        p3ocp = true;
		    		    } else if ((j3.getX() == posicion5.get(0)) & p4ocp & p3ocp) {
		    		        p6ocp = false;
		    		        j3.setLocation(posicion2.get(0), posicion1.get(1));
		    		        p2ocp = true;
		    		        
		    		    } else if ((j3.getX() == posicion4.get(0)) & !p3ocp) {
		    		        p4ocp = false;
		    		        j3.setLocation(posicion3.get(0), posicion1.get(1));
		    		        p3ocp = true;
		    		    } else if ((j3.getX() == posicion4.get(0)) & p3ocp & !p2ocp) {
		    		        p4ocp = false;
		    		        j3.setLocation(posicion2.get(0), posicion1.get(1));
		    		        p2ocp = true;
		    		    } else if ((j3.getX() == posicion4.get(0)) & p3ocp & p2ocp) {
		    		        p6ocp = false;
		    		        j3.setLocation(posicion1.get(0), posicion1.get(1));
		    		        p1ocp = true;
		    		        
		    		    } else if ((j3.getX() == posicion3.get(0)) & !p2ocp) {
		    		        p3ocp = false;
		    		        j3.setLocation(posicion2.get(0), posicion1.get(1));
		    		        p2ocp = true;
		    		    } else if ((j3.getX() == posicion3.get(0)) & p2ocp & !p1ocp) {
		    		        p3ocp = false;
		    		        j3.setLocation(posicion1.get(0), posicion1.get(1));
		    		        p1ocp = true;
		    		        
		    		    } else if (j3.getX() == posicion2.get(0) & !p1ocp) {
		    		        p2ocp = false;
		    		        j3.setLocation(posicion1.get(0), posicion1.get(1));
		    		        p1ocp = true;
		    		    }
		    		}	
		    		
		    	} else if (cont == 4) {
		    		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		    		    if ((j4.getX() == posicion1.get(0)) & !p2ocp) {
		    		        p1ocp = false;
		    		        j4.setLocation(posicion2.get(0), posicion1.get(1));
		    		        p2ocp = true;
		    		    } else if ((j4.getX() == posicion1.get(0)) & p2ocp & !p3ocp) {
		    		        p1ocp = false;
		    		        j4.setLocation(posicion3.get(0), posicion1.get(1));
		    		        p3ocp = true;
		    		    } else if ((j4.getX() == posicion1.get(0)) & p2ocp & p3ocp & !p4ocp) {
		    		        p1ocp = false;
		    		        j4.setLocation(posicion4.get(0), posicion1.get(1));
		    		        p4ocp = true;
		    		    } else if ((j4.getX() == posicion1.get(0)) & p2ocp & p3ocp & p4ocp) {
		    		        p1ocp = false;
		    		        j4.setLocation(posicion5.get(0), posicion1.get(1));
		    		        p5ocp = true;
		    		        
		    		    } else if ((j4.getX() == posicion2.get(0)) & !p3ocp) {
		    		        p2ocp = false;
		    		        j4.setLocation(posicion3.get(0), posicion1.get(1));
		    		        p3ocp = true;
		    		    } else if ((j4.getX() == posicion2.get(0)) & p3ocp & !p4ocp) {
		    		        p2ocp = false;
		    		        j4.setLocation(posicion4.get(0), posicion1.get(1));
		    		        p4ocp = true;
		    		    } else if ((j4.getX() == posicion2.get(0)) & p3ocp & p4ocp & !p5ocp) {
		    		        p2ocp = false;
		    		        j4.setLocation(posicion5.get(0), posicion1.get(1));
		    		        p5ocp = true;
		    		    } else if ((j4.getX() == posicion2.get(0)) & p3ocp & p4ocp & p5ocp) {
		    		        p2ocp = false;
		    		        j4.setLocation(posicion6.get(0), posicion1.get(1));
		    		        p6ocp = true;
		    		        
		    		    } else if ((j4.getX() == posicion3.get(0)) & !p4ocp) {
		    		        p3ocp = false;
		    		        j4.setLocation(posicion4.get(0), posicion1.get(1));
		    		        p4ocp = true;
		    		    } else if ((j4.getX() == posicion3.get(0)) & p4ocp & !p5ocp) {
		    		        p3ocp = false;
		    		        j4.setLocation(posicion5.get(0), posicion1.get(1));
		    		        p5ocp = true;
		    		    } else if ((j4.getX() == posicion3.get(0)) & p4ocp & p5ocp) {
		    		        p3ocp = false;
		    		        j4.setLocation(posicion6.get(0), posicion1.get(1));
		    		        p6ocp = true;
		    		    } else if ((j4.getX() == posicion3.get(0)) & p4ocp & p5ocp & !p6ocp) {
		    		        p3ocp = false;
		    		        j4.setLocation(posicion6.get(0), posicion1.get(1));
		    		        p6ocp = true;
		    		    
		    			} else if ((j4.getX() == posicion4.get(0)) & !p5ocp) {
		    		        p4ocp = false;
		    		        j4.setLocation(posicion5.get(0), posicion1.get(1));
		    		        p5ocp = true;
		    		    } else if ((j4.getX() == posicion4.get(0)) & p5ocp & !p6ocp) {
		    		        p4ocp = false;
		    		        j4.setLocation(posicion6.get(0), posicion1.get(1));
		    		        p6ocp = true;
		    		        
		    		    } else if ((j4.getX() == posicion5.get(0)) & !p6ocp) {
		    		        p5ocp = false;
		    		        j4.setLocation(posicion6.get(0), posicion1.get(1));
		    		        p6ocp = true;
		    		    }
		    		    
		    		} if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		    		    if ((j4.getX() == posicion6.get(0)) & !p5ocp) {
		    		        p6ocp = false;
		    		        j4.setLocation(posicion5.get(0), posicion1.get(1));
		    		        p5ocp = true;
		    		    } else if ((j4.getX() == posicion6.get(0)) & p5ocp & !p4ocp) {
		    		        p6ocp = false;
		    		        j4.setLocation(posicion4.get(0), posicion1.get(1));
		    		        p4ocp = true;
		    		    } else if ((j4.getX() == posicion6.get(0)) & p5ocp & p4ocp & !p3ocp) {
		    		        p6ocp = false;
		    		        j4.setLocation(posicion3.get(0), posicion1.get(1));
		    		        p3ocp = true;
		    		    } else if ((j4.getX() == posicion6.get(0)) & p5ocp & p4ocp & p3ocp) {
		    		        p6ocp = false;
		    		        j4.setLocation(posicion2.get(0), posicion1.get(1));
		    		        p2ocp = true;
		    		        
		    		    } else if ((j4.getX() == posicion5.get(0)) & !p4ocp) {
		    		        p5ocp = false;
		    		        j4.setLocation(posicion4.get(0), posicion1.get(1));
		    		        p4ocp = true;
		    		    } else if ((j4.getX() == posicion5.get(0)) & p4ocp & !p3ocp) {
		    		        p5ocp = false;
		    		        j4.setLocation(posicion3.get(0), posicion1.get(1));
		    		        p3ocp = true;
		    		    } else if ((j4.getX() == posicion5.get(0)) & p4ocp & p3ocp & !p2ocp) {
		    		        p5ocp = false;
		    		        j4.setLocation(posicion2.get(0), posicion1.get(1));
		    		        p2ocp = true;
		    		    } else if ((j4.getX() == posicion5.get(0)) & p4ocp & p3ocp & p2ocp) {
		    		        p6ocp = false;
		    		        j4.setLocation(posicion1.get(0), posicion1.get(1));
		    		        p1ocp = true;
		    		        
		    		    } else if ((j4.getX() == posicion4.get(0)) & !p3ocp) {
		    		        p4ocp = false;
		    		        j4.setLocation(posicion3.get(0), posicion1.get(1));
		    		        p3ocp = true;
		    		    } else if ((j4.getX() == posicion4.get(0)) & p3ocp & !p2ocp) {
		    		        p4ocp = false;
		    		        j4.setLocation(posicion2.get(0), posicion1.get(1));
		    		        p2ocp = true;
		    		    } else if ((j4.getX() == posicion4.get(0)) & p3ocp & p2ocp & !p1ocp) {
		    		        p4ocp = false;
		    		        j4.setLocation(posicion1.get(0), posicion1.get(1));
		    		        p1ocp = true;
		    		        
		    		    } else if ((j4.getX() == posicion3.get(0)) & !p2ocp) {
		    		        p3ocp = false;
		    		        j4.setLocation(posicion2.get(0), posicion1.get(1));
		    		        p2ocp = true;
		    		    } else if ((j4.getX() == posicion3.get(0)) & p2ocp & !p1ocp) {
		    		        p3ocp = false;
		    		        j4.setLocation(posicion1.get(0), posicion1.get(1));
		    		        p1ocp = true;
		    		    }
		    		}
		    	} else if (cont == 5) {
		    		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		    		    if ((j5.getX() == posicion1.get(0)) & !p2ocp) {
		    		        p1ocp = false;
		    		        j5.setLocation(posicion2.get(0), posicion1.get(1));
		    		        p2ocp = true;
		    		    } else if ((j5.getX() == posicion1.get(0)) & p2ocp & !p3ocp) {
		    		        p1ocp = false;
		    		        j5.setLocation(posicion3.get(0), posicion1.get(1));
		    		        p3ocp = true;
		    		    } else if ((j5.getX() == posicion1.get(0)) & p2ocp & p3ocp & !p4ocp) {
		    		        p1ocp = false;
		    		        j5.setLocation(posicion4.get(0), posicion1.get(1));
		    		        p4ocp = true;
		    		    } else if ((j5.getX() == posicion1.get(0)) & p2ocp & p3ocp & p4ocp & !p5ocp) {
		    		        p1ocp = false;
		    		        j5.setLocation(posicion5.get(0), posicion1.get(1));
		    		        p5ocp = true;
		    		    } else if ((j5.getX() == posicion1.get(0)) & p2ocp & p3ocp & p4ocp & p5ocp) {
		    		        p1ocp = false;
		    		        j5.setLocation(posicion6.get(0), posicion1.get(1));
		    		        p6ocp = true;
		    		        
		    		    } else if ((j5.getX() == posicion2.get(0)) & !p3ocp) {
		    		        p2ocp = false;
		    		        j5.setLocation(posicion3.get(0), posicion1.get(1));
		    		        p3ocp = true;
		    		    } else if ((j5.getX() == posicion2.get(0)) & p3ocp & !p4ocp) {
		    		        p2ocp = false;
		    		        j5.setLocation(posicion4.get(0), posicion1.get(1));
		    		        p4ocp = true;
		    		    } else if ((j5.getX() == posicion2.get(0)) & p3ocp & p4ocp & !p5ocp) {
		    		        p2ocp = false;
		    		        j5.setLocation(posicion5.get(0), posicion1.get(1));
		    		        p5ocp = true;
		    		    } else if ((j5.getX() == posicion2.get(0)) & p3ocp & p4ocp & p5ocp & !p6ocp) {
		    		        p2ocp = false;
		    		        j5.setLocation(posicion6.get(0), posicion1.get(1));
		    		        p6ocp = true;
		    		        
		    		    } else if ((j5.getX() == posicion3.get(0)) & !p4ocp) {
		    		        p3ocp = false;
		    		        j5.setLocation(posicion4.get(0), posicion1.get(1));
		    		        p4ocp = true;
		    		    } else if ((j5.getX() == posicion3.get(0)) & p4ocp & !p5ocp) {
		    		        p3ocp = false;
		    		        j5.setLocation(posicion5.get(0), posicion1.get(1));
		    		        p5ocp = true;
		    		    } else if ((j5.getX() == posicion3.get(0)) & p4ocp & p5ocp & !p6ocp) {
		    		        p3ocp = false;
		    		        j5.setLocation(posicion6.get(0), posicion1.get(1));
		    		        p6ocp = true;
		    		        
		    		    } else if ((j5.getX() == posicion4.get(0)) & !p5ocp) {
		    		        p4ocp = false;
		    		        j5.setLocation(posicion5.get(0), posicion1.get(1));
		    		        p5ocp = true;
		    		    } else if ((j5.getX() == posicion4.get(0)) & p5ocp & !p6ocp) {
		    		        p4ocp = false;
		    		        j5.setLocation(posicion6.get(0), posicion1.get(1));
		    		        p6ocp = true;
		    		        
		    		    } else if ((j5.getX() == posicion5.get(0)) & !p6ocp) {
		    		        p5ocp = false;
		    		        j5.setLocation(posicion6.get(0), posicion1.get(1));
		    		        p6ocp = true;
		    		    }
		    		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		    		    if ((j5.getX() == posicion6.get(0)) & !p5ocp) {
		    		        p6ocp = false;
		    		        j5.setLocation(posicion5.get(0), posicion1.get(1));
		    		        p5ocp = true;
		    		    } else if ((j5.getX() == posicion6.get(0)) & p5ocp & !p4ocp) {
		    		        p6ocp = false;
		    		        j5.setLocation(posicion4.get(0), posicion1.get(1));
		    		        p4ocp = true;
		    		    } else if ((j5.getX() == posicion6.get(0)) & p5ocp & p4ocp & !p3ocp) {
		    		        p6ocp = false;
		    		        j5.setLocation(posicion3.get(0), posicion1.get(1));
		    		        p3ocp = true;
		    		    } else if ((j5.getX() == posicion6.get(0)) & p5ocp & p4ocp & p3ocp & !p2ocp) {
		    		        p6ocp = false;
		    		        j5.setLocation(posicion2.get(0), posicion1.get(1));
		    		        p2ocp = true;
		    		    } else if ((j5.getX() == posicion6.get(0)) & p5ocp & p4ocp & p3ocp & p2ocp) {
		    		        p6ocp = false;
		    		        j5.setLocation(posicion1.get(0), posicion1.get(1));
		    		        p1ocp = true;
		    		        
		    		    } else if ((j5.getX() == posicion5.get(0)) & !p4ocp) {
		    		        p5ocp = false;
		    		        j5.setLocation(posicion4.get(0), posicion1.get(1));
		    		        p4ocp = true;
		    		    } else if ((j5.getX() == posicion5.get(0)) & p4ocp & !p3ocp) {
		    		        p5ocp = false;
		    		        j5.setLocation(posicion3.get(0), posicion1.get(1));
		    		        p3ocp = true;
		    		    } else if ((j5.getX() == posicion5.get(0)) & p4ocp & p3ocp & !p2ocp) {
		    		        p5ocp = false;
		    		        j5.setLocation(posicion2.get(0), posicion1.get(1));
		    		        p2ocp = true;
		    		    } else if ((j5.getX() == posicion5.get(0)) & p4ocp & p3ocp & p2ocp & !p1ocp) {
		    		        p5ocp = false;
		    		        j5.setLocation(posicion1.get(0), posicion1.get(1));
		    		        p1ocp = true;
		    		        
		    		    } else if ((j5.getX() == posicion4.get(0)) & !p3ocp) {
		    		        p4ocp = false;
		    		        j5.setLocation(posicion3.get(0), posicion1.get(1));
		    		        p3ocp = true;
		    		    } else if ((j5.getX() == posicion4.get(0)) & p3ocp & !p2ocp) {
		    		        p4ocp = false;
		    		        j5.setLocation(posicion2.get(0), posicion1.get(1));
		    		        p2ocp = true;
		    		    } else if ((j5.getX() == posicion4.get(0)) & p3ocp & p2ocp & !p1ocp) {
		    		        p4ocp = false;
		    		        j5.setLocation(posicion1.get(0), posicion1.get(1));
		    		        p1ocp = true;
		    		        
		    		    } else if ((j5.getX() == posicion3.get(0)) & !p2ocp) {
		    		        p3ocp = false;
		    		        j5.setLocation(posicion2.get(0), posicion1.get(1));
		    		        p2ocp = true;
		    		    } else if ((j5.getX() == posicion3.get(0)) & p2ocp & !p1ocp) {
		    		        p3ocp = false;
		    		        j5.setLocation(posicion1.get(0), posicion1.get(1));
		    		        p1ocp = true;
		    		        
		    		    } else if ((j5.getX() == posicion2.get(0)) & !p1ocp) {
		    		        p5ocp = false;
		    		        j5.setLocation(posicion1.get(0), posicion1.get(1));
		    		        p1ocp = true;
		    		    }
		    		}
		    	}     
		    }
		});
		
		int p = 1;
		for (int i=0; i<NombrePersonaje.values().length;i++) {
			ImageIcon sprite = new ImageIcon(Gestion.sprites.get(NombrePersonaje.values()[i]).get(TipoSprite.AndarAbajo).get(0));
			Image image = sprite.getImage();
			Image newImage = image.getScaledInstance(180, 190, Image.SCALE_SMOOTH);
			ImageIcon newIcon = new ImageIcon(newImage);
			JLabel lblFoto = new JLabel();
			lblFoto.setIcon(newIcon);
			lblFoto.setBounds((int) Math.round(p*ancho/34), (int) Math.round(-10*altura/19), 
				(int) Math.round(50*ancho/34), (int) Math.round(40*altura/19));
			this.add(lblFoto);
			this.repaint();
			if (i == 0 || i == 4) {
				p = p +5;
			} else {
				p = p+6;
			}
		}
		
		jugadores = Gestion.jugadores.size();
		
		continuar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cont++;
				System.out.println(cont);
				if (cont <= jugadores) {
				lblElige.setText("J"+cont+" ELIGE TU PERSONAJE");
				if (cont == 2) {
					if (j1.getX() == posicion1.get(0)) {
						j1.setForeground(Color.RED);
						Gestion.jugadores.get(0).setPersonaje(new Personaje(NombrePersonaje.values()[0]));
						bd.setterarPersonaje(1, "Rojo");
						System.out.println("ROJO");
					} else if (j1.getX() == posicion2.get(0)) {
						j1.setForeground(Color.YELLOW);
						Gestion.jugadores.get(0).setPersonaje(new Personaje(NombrePersonaje.values()[1]));
						bd.setterarPersonaje(1, "Amarillo");
					} else if (j1.getX() == posicion3.get(0)) {
						j1.setForeground(Color.BLACK);
						Gestion.jugadores.get(0).setPersonaje(new Personaje(NombrePersonaje.values()[2]));
						bd.setterarPersonaje(1, "Negro");
					} else if (j1.getX() == posicion4.get(0)) {
						j1.setForeground(Color.GREEN);
						Gestion.jugadores.get(0).setPersonaje(new Personaje(NombrePersonaje.values()[3]));
						bd.setterarPersonaje(1, "Verde");
					} else if (j1.getX() == posicion5.get(0)) {
						j1.setForeground(Color.BLUE);
						Gestion.jugadores.get(0).setPersonaje(new Personaje(NombrePersonaje.values()[4]));
						bd.setterarPersonaje(1, "Azul");
					} else if (j1.getX() == posicion6.get(0)) {
						j1.setForeground(Color.MAGENTA);
						Gestion.jugadores.get(0).setPersonaje(new Personaje(NombrePersonaje.values()[5]));
						bd.setterarPersonaje(1, "Morado");
					}
					
					if (p1ocp) {
						j2.setLocation(posicion2.get(0), posicion1.get(1));
						p2ocp = true;
					} else {
						j2.setLocation(posicion1.get(0), posicion1.get(1));
						p1ocp = true;
					}
					j2.setVisible(true);
				} else if (cont == 3) {
					if (j2.getX() == posicion1.get(0)) {
						j2.setForeground(Color.RED);
						Gestion.jugadores.get(1).setPersonaje(new Personaje(NombrePersonaje.values()[0]));
						bd.setterarPersonaje(2, "Rojo");
					} else if (j2.getX() == posicion2.get(0)) {
						j2.setForeground(Color.YELLOW);
						Gestion.jugadores.get(1).setPersonaje(new Personaje(NombrePersonaje.values()[1]));
						bd.setterarPersonaje(2, "Amarillo");
						System.out.println("AMARILLO");
					} else if (j2.getX() == posicion3.get(0)) {
						j2.setForeground(Color.BLACK);
						Gestion.jugadores.get(1).setPersonaje(new Personaje(NombrePersonaje.values()[2]));
						bd.setterarPersonaje(2, "Negro");
					} else if (j2.getX() == posicion4.get(0)) {
						j2.setForeground(Color.GREEN);
						Gestion.jugadores.get(1).setPersonaje(new Personaje(NombrePersonaje.values()[3]));
						bd.setterarPersonaje(2, "Verde");
					} else if (j2.getX() == posicion5.get(0)) {
						j2.setForeground(Color.BLUE);
						Gestion.jugadores.get(1).setPersonaje(new Personaje(NombrePersonaje.values()[4]));
						bd.setterarPersonaje(2, "Azul");
					} else if (j2.getX() == posicion6.get(0)) {
						j2.setForeground(Color.MAGENTA);
						Gestion.jugadores.get(1).setPersonaje(new Personaje(NombrePersonaje.values()[5]));
						bd.setterarPersonaje(2, "Morado");
					}
					
					if (!p1ocp) {
						j3.setLocation(posicion1.get(0), posicion1.get(1));
						p1ocp = true;
					} else if (!p2ocp) {
						j3.setLocation(posicion2.get(0), posicion1.get(1));
						p2ocp = true;
					} else {
						j3.setLocation(posicion3.get(0), posicion1.get(1));
						p3ocp = true;
					}
					j3.setVisible(true);
				} else if (cont == 4) {
					//System.out.println("xhabhyfevv");
					if (j3.getX() == posicion1.get(0)) {
						j3.setForeground(Color.RED);
						Gestion.jugadores.get(2).setPersonaje(new Personaje(NombrePersonaje.values()[0]));
						bd.setterarPersonaje(3, "Rojo");
					} else if (j3.getX() == posicion2.get(0)) {
						j3.setForeground(Color.YELLOW);
						Gestion.jugadores.get(2).setPersonaje(new Personaje(NombrePersonaje.values()[1]));
						bd.setterarPersonaje(3, "Amarillo");
					} else if (j3.getX() == posicion3.get(0)) {
						j3.setForeground(Color.BLACK);
						Gestion.jugadores.get(2).setPersonaje(new Personaje(NombrePersonaje.values()[2]));
						bd.setterarPersonaje(3, "Negro");
						System.out.println("NEGRO");
					} else if (j3.getX() == posicion4.get(0)) {
						j3.setForeground(Color.GREEN);
						Gestion.jugadores.get(2).setPersonaje(new Personaje(NombrePersonaje.values()[3]));
						bd.setterarPersonaje(3, "Verde");
					} else if (j3.getX() == posicion5.get(0)) {
						j3.setForeground(Color.BLUE);
						Gestion.jugadores.get(2).setPersonaje(new Personaje(NombrePersonaje.values()[4]));
						bd.setterarPersonaje(3, "Azul");
					} else if (j3.getX() == posicion6.get(0)) {
						j3.setForeground(Color.MAGENTA);
						Gestion.jugadores.get(2).setPersonaje(new Personaje(NombrePersonaje.values()[5]));
						bd.setterarPersonaje(3, "Morado");
					}
					if (!p1ocp){
						j4.setLocation(posicion1.get(0), posicion1.get(1));
						p1ocp = true;
					} else if (!p2ocp) {
						j4.setLocation(posicion2.get(0), posicion1.get(1));
						p2ocp = true;
					} else if (!p3ocp) {
						j4.setLocation(posicion3.get(0), posicion1.get(1));
						p3ocp = true;
					}else {
						j4.setLocation(posicion4.get(0), posicion1.get(1));
						p4ocp = true;
					}
					j4.setVisible(true);
				} else if (cont == 5) {
					if (j4.getX() == posicion1.get(0)) {
						j4.setForeground(Color.RED);
						Gestion.jugadores.get(3).setPersonaje(new Personaje(NombrePersonaje.values()[0]));
						bd.setterarPersonaje(4, "Rojo");
					} else if (j4.getX() == posicion2.get(0)) {
						j4.setForeground(Color.YELLOW);
						Gestion.jugadores.get(3).setPersonaje(new Personaje(NombrePersonaje.values()[1]));
						bd.setterarPersonaje(4, "Amarillo");
					} else if (j4.getX() == posicion3.get(0)) {
						j2.setForeground(Color.BLACK);
						Gestion.jugadores.get(3).setPersonaje(new Personaje(NombrePersonaje.values()[2]));
						bd.setterarPersonaje(4, "Negro");
					} else if (j4.getX() == posicion4.get(0)) {
						j4.setForeground(Color.GREEN);
						Gestion.jugadores.get(3).setPersonaje(new Personaje(NombrePersonaje.values()[3]));
						bd.setterarPersonaje(4, "Verde");
					} else if (j4.getX() == posicion5.get(0)) {
						j4.setForeground(Color.BLUE);
						Gestion.jugadores.get(3).setPersonaje(new Personaje(NombrePersonaje.values()[4]));
						bd.setterarPersonaje(4, "Azul");
					} else if (j4.getX() == posicion6.get(0)) {
						j4.setForeground(Color.MAGENTA);
						Gestion.jugadores.get(3).setPersonaje(new Personaje(NombrePersonaje.values()[5]));
						bd.setterarPersonaje(4, "Morado");
					}
					if (!p1ocp){
						j5.setLocation(posicion1.get(0), posicion1.get(1));
						p1ocp = true;
					} else if (!p2ocp) {
						j5.setLocation(posicion2.get(0), posicion1.get(1));
						p2ocp = true;
					} else if (!p3ocp) {
						j5.setLocation(posicion3.get(0), posicion1.get(1));
						p3ocp = true;
					} else if (!p4ocp) {
						j5.setLocation(posicion4.get(0), posicion1.get(1));
						p4ocp = true;
					} else {
						j5.setLocation(posicion5.get(0), posicion1.get(1));
						p5ocp = true;
					}
					j5.setVisible(true);
				} else if (cont == 6) {
					if (j5.getX() == posicion1.get(0)) {
						j5.setForeground(Color.RED);
						Gestion.jugadores.get(4).setPersonaje(new Personaje(NombrePersonaje.values()[0]));
						bd.setterarPersonaje(5, "Rojo");
					} else if (j5.getX() == posicion2.get(0)) {
						j5.setForeground(Color.YELLOW);
						Gestion.jugadores.get(4).setPersonaje(new Personaje(NombrePersonaje.values()[1]));
						bd.setterarPersonaje(5, "Amarillo");
					} else if (j5.getX() == posicion3.get(0)) {
						j2.setForeground(Color.BLACK);
						Gestion.jugadores.get(4).setPersonaje(new Personaje(NombrePersonaje.values()[2]));
						bd.setterarPersonaje(5, "Negro");
					} else if (j5.getX() == posicion4.get(0)) {
						j5.setForeground(Color.GREEN);
						Gestion.jugadores.get(4).setPersonaje(new Personaje(NombrePersonaje.values()[3]));
						bd.setterarPersonaje(5, "Verde");
					} else if (j5.getX() == posicion5.get(0)) {
						j5.setForeground(Color.BLUE);
						Gestion.jugadores.get(4).setPersonaje(new Personaje(NombrePersonaje.values()[4]));
						bd.setterarPersonaje(5, "Azul");
					} else if (j5.getX() == posicion6.get(0)) {
						j5.setForeground(Color.MAGENTA);
						Gestion.jugadores.get(4).setPersonaje(new Personaje(NombrePersonaje.values()[5]));
						bd.setterarPersonaje(5, "Morado");
					}
					if (!p1ocp){
						j6.setLocation(posicion1.get(0), posicion1.get(1));
						p1ocp = true;
					} else if (!p2ocp) {
						j6.setLocation(posicion2.get(0), posicion1.get(1));
						p2ocp = true;
					} else if (!p3ocp) {
						j6.setLocation(posicion3.get(0), posicion1.get(1));
						p3ocp = true;
					} else if (!p4ocp) {
						j6.setLocation(posicion4.get(0), posicion1.get(1));
						p4ocp = true;
					} else if (!p5ocp) {
						j6.setLocation(posicion5.get(0), posicion1.get(1));
						p5ocp = true;
					} else {
						j6.setLocation(posicion6.get(0), posicion1.get(1));
						p6ocp = true;
					}
					j6.setVisible(true);
					if (j6.getX() == posicion1.get(0)) {
						j6.setForeground(Color.RED);
						Gestion.jugadores.get(5).setPersonaje(new Personaje(NombrePersonaje.values()[0]));
					} else if (j6.getX() == posicion2.get(0)) {
						j6.setForeground(Color.YELLOW);
						Gestion.jugadores.get(5).setPersonaje(new Personaje(NombrePersonaje.values()[1]));
					} else if (j6.getX() == posicion3.get(0)) {
						j2.setForeground(Color.BLACK);
						Gestion.jugadores.get(5).setPersonaje(new Personaje(NombrePersonaje.values()[2]));
					} else if (j6.getX() == posicion4.get(0)) {
						j6.setForeground(Color.GREEN);
						Gestion.jugadores.get(5).setPersonaje(new Personaje(NombrePersonaje.values()[3]));
					} else if (j6.getX() == posicion5.get(0)) {
						j6.setForeground(Color.BLUE);
						Gestion.jugadores.get(5).setPersonaje(new Personaje(NombrePersonaje.values()[4]));
					} else if (j6.getX() == posicion6.get(0)) {
						j6.setForeground(Color.MAGENTA);
						Gestion.jugadores.get(5).setPersonaje(new Personaje(NombrePersonaje.values()[5]));
					}
				} 
				
			} else if (cont == jugadores + 1) {
				if (cont == 4) {
					if (j3.getX() == posicion1.get(0)) {
						j3.setForeground(Color.RED);
						Gestion.jugadores.get(2).setPersonaje(new Personaje(NombrePersonaje.values()[0]));
					} else if (j3.getX() == posicion2.get(0)) {
						j3.setForeground(Color.YELLOW);
						Gestion.jugadores.get(2).setPersonaje(new Personaje(NombrePersonaje.values()[1]));
					} else if (j3.getX() == posicion3.get(0)) {
						j3.setForeground(Color.BLACK);
						Gestion.jugadores.get(2).setPersonaje(new Personaje(NombrePersonaje.values()[2]));
					}else if (j3.getX() == posicion4.get(0)) {
						j3.setForeground(Color.GREEN);
						Gestion.jugadores.get(2).setPersonaje(new Personaje(NombrePersonaje.values()[3]));
					} else if (j3.getX() == posicion5.get(0)) {
						j3.setForeground(Color.BLUE);
						Gestion.jugadores.get(2).setPersonaje(new Personaje(NombrePersonaje.values()[4]));
					} else if (j3.getX() == posicion6.get(0)) {
						j3.setForeground(Color.MAGENTA);
						Gestion.jugadores.get(2).setPersonaje(new Personaje(NombrePersonaje.values()[5]));
					}
				} if (cont == 5) {
					if (j4.getX() == posicion1.get(0)) {
						j4.setForeground(Color.RED);
						Gestion.jugadores.get(3).setPersonaje(new Personaje(NombrePersonaje.values()[0]));
					} else if (j4.getX() == posicion2.get(0)) {
						j4.setForeground(Color.YELLOW);
						Gestion.jugadores.get(3).setPersonaje(new Personaje(NombrePersonaje.values()[1]));
					} else if (j4.getX() == posicion3.get(0)) {
						j4.setForeground(Color.BLACK);
						Gestion.jugadores.get(3).setPersonaje(new Personaje(NombrePersonaje.values()[2]));
					}else if (j4.getX() == posicion4.get(0)) {
						j4.setForeground(Color.GREEN);
						Gestion.jugadores.get(3).setPersonaje(new Personaje(NombrePersonaje.values()[3]));
					} else if (j4.getX() == posicion5.get(0)) {
						j4.setForeground(Color.BLUE);
						Gestion.jugadores.get(3).setPersonaje(new Personaje(NombrePersonaje.values()[4]));
					} else if (j4.getX() == posicion6.get(0)) {
						j4.setForeground(Color.MAGENTA);
						Gestion.jugadores.get(3).setPersonaje(new Personaje(NombrePersonaje.values()[5]));
					}
				} if (cont == 6) {
					if (j5.getX() == posicion1.get(0)) {
						j5.setForeground(Color.RED);
						Gestion.jugadores.get(4).setPersonaje(new Personaje(NombrePersonaje.values()[0]));
					} else if (j5.getX() == posicion2.get(0)) {
						j5.setForeground(Color.YELLOW);
						Gestion.jugadores.get(4).setPersonaje(new Personaje(NombrePersonaje.values()[1]));
					} else if (j5.getX() == posicion3.get(0)) {
						j5.setForeground(Color.BLACK);
						Gestion.jugadores.get(4).setPersonaje(new Personaje(NombrePersonaje.values()[2]));
					}else if (j5.getX() == posicion4.get(0)) {
						j5.setForeground(Color.GREEN);
						Gestion.jugadores.get(4).setPersonaje(new Personaje(NombrePersonaje.values()[3]));
					} else if (j5.getX() == posicion5.get(0)) {
						j5.setForeground(Color.BLUE);
						Gestion.jugadores.get(4).setPersonaje(new Personaje(NombrePersonaje.values()[4]));
					} else if (j5.getX() == posicion6.get(0)) {
						j5.setForeground(Color.MAGENTA);
						Gestion.jugadores.get(4).setPersonaje(new Personaje(NombrePersonaje.values()[5]));
					}
				} if (cont == 7) {
					if (j6.getX() == posicion1.get(0)) {
						j6.setForeground(Color.RED);
						Gestion.jugadores.get(5).setPersonaje(new Personaje(NombrePersonaje.values()[0]));
					} else if (j6.getX() == posicion2.get(0)) {
						j6.setForeground(Color.YELLOW);
						Gestion.jugadores.get(5).setPersonaje(new Personaje(NombrePersonaje.values()[1]));
					} else if (j6.getX() == posicion3.get(0)) {
						j6.setForeground(Color.BLACK);
						Gestion.jugadores.get(5).setPersonaje(new Personaje(NombrePersonaje.values()[2]));
					} else if (j6.getX() == posicion4.get(0)) {
						j6.setForeground(Color.GREEN);
						Gestion.jugadores.get(5).setPersonaje(new Personaje(NombrePersonaje.values()[3]));
					} else if (j6.getX() == posicion5.get(0)) {
						j6.setForeground(Color.BLUE);
						Gestion.jugadores.get(5).setPersonaje(new Personaje(NombrePersonaje.values()[4]));
					} else if (j6.getX() == posicion6.get(0)) {
						j6.setForeground(Color.MAGENTA);
						Gestion.jugadores.get(5).setPersonaje(new Personaje(NombrePersonaje.values()[5]));
					}
				}
			} else if (cont > jugadores + 1) {
				//Empezar partida y añadir los datos de la partida a la BD
				eliminarPanel();
				Gestion.repartirCartas(Gestion.datosPartida.todasLasCartas);

				bd.anyadirPartida(bd.partida);
				if (bd.hayNull()) {
					bd.actualizarColor(Gestion.jugadores.get(Gestion.jugadores.size()-1).getPersonaje().getNombre().toString());
				}
				new VentanaAsignarTurnos();
			}
		}
	});
		
		atras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cont != 1) {
					if (cont == 2) {
						j1.setForeground(Color.WHITE);
						Gestion.jugadores.get(0).setPersonaje(null);
						int lgr2 = j2.getX();
						if (lgr2 == posicion1.get(0)) {
							p1ocp = false;
						} else if (lgr2 == posicion2.get(0)) {
							p2ocp = false;
						} else if (lgr2 == posicion3.get(0)) {
							p3ocp = false;
						} else if (lgr2 == posicion4.get(0)) {
							p4ocp = false;
						} else if (lgr2 == posicion5.get(0)) {
							p5ocp = false;
						} else if (lgr2 == posicion6.get(0)) {
							p6ocp = false;
						}
						j2.setVisible(false);
					} else if (cont == 3) {
						j2.setForeground(Color.WHITE);
						Gestion.jugadores.get(1).setPersonaje(null);
						int lgr3 = j3.getX();
						if (lgr3 == posicion1.get(0)) {
							p1ocp = false;
						} else if (lgr3 == posicion2.get(0)) {
							p2ocp = false;
						} else if (lgr3 == posicion3.get(0)) {
							p3ocp = false;
						} else if (lgr3 == posicion4.get(0)) {
							p4ocp = false;
						} else if (lgr3 == posicion5.get(0)) {
							p5ocp = false;
						} else if (lgr3 == posicion6.get(0)) {
							p6ocp = false;
						}
						j3.setVisible(false);
					} else if (cont == 4) {
						j3.setForeground(Color.WHITE);
						Gestion.jugadores.get(2).setPersonaje(null);
						int lgr4 = j4.getX();
						if (lgr4 == posicion1.get(0)) {
							p1ocp = false;
						} else if (lgr4 == posicion2.get(0)) {
							p2ocp = false;
						} else if (lgr4 == posicion3.get(0)) {
							p3ocp = false;
						} else if (lgr4 == posicion4.get(0)) {
							p4ocp = false;
						} else if (lgr4 == posicion5.get(0)) {
							p5ocp = false;
						} else if (lgr4 == posicion6.get(0)) {
							p6ocp = false;
						}
						j4.setVisible(false);
					} else if (cont == 5) {
						j4.setForeground(Color.WHITE);
						Gestion.jugadores.get(3).setPersonaje(null);
						int lgr5 = j5.getX();
						if (lgr5 == posicion1.get(0)) {
							p1ocp = false;
						} else if (lgr5 == posicion2.get(0)) {
							p2ocp = false;
						} else if (lgr5 == posicion3.get(0)) {
							p3ocp = false;
						} else if (lgr5 == posicion4.get(0)) {
							p4ocp = false;
						} else if (lgr5 == posicion5.get(0)) {
							p5ocp = false;
						} else if (lgr5 == posicion6.get(0)) {
							p6ocp = false;
						}
						j5.setVisible(false);
					} else if (cont == 6) {
						j5.setForeground(Color.WHITE);
						Gestion.jugadores.get(4).setPersonaje(null);
						int lgr6 = j6.getX();
						if (lgr6 == posicion1.get(0)) {
							p1ocp = false;
						} else if (lgr6 == posicion2.get(0)) {
							p2ocp = false;
						} else if (lgr6 == posicion3.get(0)) {
							p3ocp = false;
						} else if (lgr6 == posicion4.get(0)) {
							p4ocp = false;
						} else if (lgr6 == posicion5.get(0)) {
							p5ocp = false;
						} else if (lgr6 == posicion6.get(0)) {
							p6ocp = false;
						}
						j6.setVisible(false);
					} else if (cont ==7) {
						j6.setForeground(Color.WHITE);
						Gestion.jugadores.get(5).setPersonaje(null);
					}
					cont--;
					lblElige.setText("J"+cont+" ELIGE TU PERSONAJE");
				} else if (cont == 1){
					eliminarPanel();					
					anadirPanel();
					vn.getBtnRegistro().setEnabled(true);
					bd.eliminarJugadores();
					vn.numVecesRegistrado = 0;
					bd.getJugsPartida().clear();
				} 
				
			}	
		});
		setSize(Gestion.sizePantalla);
		Gestion.ventanaJuego.add(this);
		revalidate();
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon iconoFondo = new ImageIcon(getClass().getResource("FondoInicioDifuminado.jpg"));
        Image imagenFondo = iconoFondo.getImage();
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
    }
		
	public void eliminarPanel() {
		Gestion.ventanaJuego.remove(this);
        Gestion.ventanaJuego.repaint();
	}
	
	public void anadirPanel() {
		vn = new VentanaNJugadores(this.bd);
		Gestion.ventanaJuego.add(vn);
		Gestion.ventanaJuego.repaint();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VentanaNJugadores v = new VentanaNJugadores(new MainBD());
	}
	

}
