package Clases;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class VentanaSeleccionPersonaje extends JFrame{
	
	protected JLabel lblElige;
	protected JLabel j1;
	protected JLabel j2;
	protected JLabel j3;
	protected JLabel j4;
	protected JLabel j5;
	protected JLabel j6;
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
		
		
		
		continuar = new JButton("Continuar");
		continuar.setBounds((int) Math.round(7*ancho/34), (int) Math.round(15*altura/19), 
				(int) Math.round(7*ancho/34), (int) Math.round(2*altura/19));
		this.add(continuar);
		
		atras = new JButton ("Atras");
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
		j1.setFont(new Font(eliFont.getName(), eliFont.BOLD, 25));
		j1.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(j1);
		
		j2 = new JLabel ("J2");
		j2.setBounds(posicion2.get(0), posicion1.get(1), 
				(int) Math.round(2*ancho/34), (int) Math.round(2*altura/19));
		j2.setVisible(false);
		j2.setFont(new Font(eliFont.getName(), eliFont.BOLD, 25));
		j2.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(j2);
		
		
		j3 = new JLabel ("J3");
		j3.setBounds(posicion2.get(0), posicion1.get(1), 
				(int) Math.round(2*ancho/34), (int) Math.round(2*altura/19));
		j3.setVisible(false);
		j3.setFont(new Font(eliFont.getName(), eliFont.BOLD, 25));
		j3.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(j3);
		
		j4 = new JLabel ("J4");
		j4.setBounds(posicion2.get(0), posicion1.get(1), 
				(int) Math.round(2*ancho/34), (int) Math.round(2*altura/19));
		j4.setVisible(false);
		j4.setFont(new Font(eliFont.getName(), eliFont.BOLD, 25));
		j4.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(j4);
		
		j5 = new JLabel ("J5");
		j5.setBounds(posicion2.get(0), posicion1.get(1), 
				(int) Math.round(2*ancho/34), (int) Math.round(2*altura/19));
		j5.setVisible(false);
		j5.setFont(new Font(eliFont.getName(), eliFont.BOLD, 25));
		j5.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(j5);
		
		j6 = new JLabel ("J6");
		j6.setBounds(posicion2.get(0), posicion1.get(1), 
				(int) Math.round(2*ancho/34), (int) Math.round(2*altura/19));
		j6.setVisible(false);
		j6.setFont(new Font(eliFont.getName(), eliFont.BOLD, 25));
		j6.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(j6);
		
		this.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent e) {
		    	if (cont == 1) {
		    		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		    			if (j1.getX() == posicion1.get(0)) {
		    				j1.setLocation(posicion2.get(0), posicion1.get(1));
		    			} else if (j1.getX() == posicion2.get(0)) {
		    				j1.setLocation(posicion3.get(0), posicion1.get(1));
		    			} else if (j1.getX() == posicion3.get(0)) {
		    				j1.setLocation(posicion4.get(0), posicion1.get(1));
		    			} else if (j1.getX() == posicion4.get(0)) {
		    				j1.setLocation(posicion5.get(0), posicion1.get(1));
		    			} else if (j1.getX() == posicion5.get(0)) {
		    				j1.setLocation(posicion6.get(0), posicion1.get(1));
		    			}
		    		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) { 
		    			if (j1.getX() == posicion6.get(0)) {
		    				j1.setLocation(posicion5.get(0), posicion1.get(1));
		    			} else if (j1.getX() == posicion5.get(0)) {
		    				j1.setLocation(posicion4.get(0), posicion1.get(1));
		    			} else if (j1.getX() == posicion4.get(0)) {
		    				j1.setLocation(posicion3.get(0), posicion1.get(1));
		    			} else if (j1.getX() == posicion3.get(0)) {
		    				j1.setLocation(posicion2.get(0), posicion1.get(1));
		    			} else if (j1.getX() == posicion2.get(0)) {
		    				j1.setLocation(posicion1.get(0), posicion1.get(1));
		    			}
		    		}
		    	} else if (cont == 2) {
		    		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		    		    if (j2.getX() == posicion1.get(0)) {
		    		        j2.setLocation(posicion2.get(0), posicion1.get(1));
		    		    } else if (j2.getX() == posicion2.get(0)) {
		    		        j2.setLocation(posicion3.get(0), posicion1.get(1));
		    		    } else if (j2.getX() == posicion3.get(0)) {
		    		        j2.setLocation(posicion4.get(0), posicion1.get(1));
		    		    } else if (j2.getX() == posicion4.get(0)) {
		    		        j2.setLocation(posicion5.get(0), posicion1.get(1));
		    		    } else if (j2.getX() == posicion5.get(0)) {
		    		        j2.setLocation(posicion6.get(0), posicion1.get(1));
		    		    }
		    		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) { 
		    		    if (j2.getX() == posicion6.get(0)) {
		    		        j2.setLocation(posicion5.get(0), posicion1.get(1));
		    		    } else if (j2.getX() == posicion5.get(0)) {
		    		        j2.setLocation(posicion4.get(0), posicion1.get(1));
		    		    } else if (j2.getX() == posicion4.get(0)) {
		    		        j2.setLocation(posicion3.get(0), posicion1.get(1));
		    		    } else if (j2.getX() == posicion3.get(0)) {
		    		        j2.setLocation(posicion2.get(0), posicion1.get(1));
		    		    } else if (j2.getX() == posicion2.get(0)) {
		    		        j2.setLocation(posicion1.get(0), posicion1.get(1));
		    		    }
		    		}
		    	} else if (cont == 3) {
		    		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		    		    if (j3.getX() == posicion1.get(0)) {
		    		        j3.setLocation(posicion2.get(0), posicion1.get(1));
		    		    } else if (j3.getX() == posicion2.get(0)) {
		    		        j3.setLocation(posicion3.get(0), posicion1.get(1));
		    		    } else if (j3.getX() == posicion3.get(0)) {
		    		        j3.setLocation(posicion4.get(0), posicion1.get(1));
		    		    } else if (j3.getX() == posicion4.get(0)) {
		    		        j3.setLocation(posicion5.get(0), posicion1.get(1));
		    		    } else if (j3.getX() == posicion5.get(0)) {
		    		        j3.setLocation(posicion6.get(0), posicion1.get(1));
		    		    }
		    		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) { 
		    		    if (j3.getX() == posicion6.get(0)) {
		    		        j3.setLocation(posicion5.get(0), posicion1.get(1));
		    		    } else if (j3.getX() == posicion5.get(0)) {
		    		        j3.setLocation(posicion4.get(0), posicion1.get(1));
		    		    } else if (j3.getX() == posicion4.get(0)) {
		    		        j3.setLocation(posicion3.get(0), posicion1.get(1));
		    		    } else if (j3.getX() == posicion3.get(0)) {
		    		        j3.setLocation(posicion2.get(0), posicion1.get(1));
		    		    } else if (j3.getX() == posicion2.get(0)) {
		    		        j3.setLocation(posicion1.get(0), posicion1.get(1));
		    		    }
		    		}	
		    	} else if (cont == 4) {
		    		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		    		    if (j4.getX() == posicion1.get(0)) {
		    		        j4.setLocation(posicion2.get(0), posicion1.get(1));
		    		    } else if (j4.getX() == posicion2.get(0)) {
		    		        j4.setLocation(posicion3.get(0), posicion1.get(1));
		    		    } else if (j4.getX() == posicion3.get(0)) {
		    		        j4.setLocation(posicion4.get(0), posicion1.get(1));
		    		    } else if (j4.getX() == posicion4.get(0)) {
		    		        j4.setLocation(posicion5.get(0), posicion1.get(1));
		    		    } else if (j4.getX() == posicion5.get(0)) {
		    		        j4.setLocation(posicion6.get(0), posicion1.get(1));
		    		    }
		    		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) { 
		    		    if (j4.getX() == posicion6.get(0)) {
		    		        j4.setLocation(posicion5.get(0), posicion1.get(1));
		    		    } else if (j4.getX() == posicion5.get(0)) {
		    		        j4.setLocation(posicion4.get(0), posicion1.get(1));
		    		    } else if (j4.getX() == posicion4.get(0)) {
		    		        j4.setLocation(posicion3.get(0), posicion1.get(1));
		    		    } else if (j4.getX() == posicion3.get(0)) {
		    		        j4.setLocation(posicion2.get(0), posicion1.get(1));
		    		    } else if (j4.getX() == posicion2.get(0)) {
		    		        j4.setLocation(posicion1.get(0), posicion1.get(1));
		    		    }
		    		}
		    	} else if (cont == 5) {
		    		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		    		    if (j5.getX() == posicion1.get(0)) {
		    		        j5.setLocation(posicion2.get(0), posicion1.get(1));
		    		    } else if (j5.getX() == posicion2.get(0)) {
		    		        j5.setLocation(posicion3.get(0), posicion1.get(1));
		    		    } else if (j5.getX() == posicion3.get(0)) {
		    		        j5.setLocation(posicion4.get(0), posicion1.get(1));
		    		    } else if (j5.getX() == posicion4.get(0)) {
		    		        j5.setLocation(posicion5.get(0), posicion1.get(1));
		    		    } else if (j5.getX() == posicion5.get(0)) {
		    		        j5.setLocation(posicion6.get(0), posicion1.get(1));
		    		    }
		    		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) { 
		    		    if (j5.getX() == posicion6.get(0)) {
		    		        j5.setLocation(posicion5.get(0), posicion1.get(1));
		    		    } else if (j5.getX() == posicion5.get(0)) {
		    		        j5.setLocation(posicion4.get(0), posicion1.get(1));
		    		    } else if (j5.getX() == posicion4.get(0)) {
		    		        j5.setLocation(posicion3.get(0), posicion1.get(1));
		    		    } else if (j5.getX() == posicion3.get(0)) {
		    		        j5.setLocation(posicion2.get(0), posicion1.get(1));
		    		    } else if (j5.getX() == posicion2.get(0)) {
		    		        j5.setLocation(posicion1.get(0), posicion1.get(1));
		    		    }
		    		}
		    	} else {
		    		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		    		    if (j6.getX() == posicion1.get(0)) {
		    		        j6.setLocation(posicion2.get(0), posicion1.get(1));
		    		    } else if (j6.getX() == posicion2.get(0)) {
		    		        j6.setLocation(posicion3.get(0), posicion1.get(1));
		    		    } else if (j6.getX() == posicion3.get(0)) {
		    		        j6.setLocation(posicion4.get(0), posicion1.get(1));
		    		    } else if (j6.getX() == posicion4.get(0)) {
		    		        j6.setLocation(posicion5.get(0), posicion1.get(1));
		    		    } else if (j6.getX() == posicion5.get(0)) {
		    		        j6.setLocation(posicion6.get(0), posicion1.get(1));
		    		    }
		    		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) { 
		    		    if (j6.getX() == posicion6.get(0)) {
		    		        j6.setLocation(posicion5.get(0), posicion1.get(1));
		    		    } else if (j6.getX() == posicion5.get(0)) {
		    		        j6.setLocation(posicion4.get(0), posicion1.get(1));
		    		    } else if (j6.getX() == posicion4.get(0)) {
		    		        j6.setLocation(posicion3.get(0), posicion1.get(1));
		    		    } else if (j6.getX() == posicion3.get(0)) {
		    		        j6.setLocation(posicion2.get(0), posicion1.get(1));
		    		    } else if (j6.getX() == posicion2.get(0)) {
		    		        j6.setLocation(posicion1.get(0), posicion1.get(1));
		    		    }
		    		}
		    		
		    	}
		        
		    }
		});
		
		continuar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cont != 6) {
				cont++;
				lblElige.setText("J"+cont+" ELIGE TU PERSONAJE");
				if (cont == 2) {
					if (j1.getX() == posicion1.get(0)) {
						j2.setLocation(posicion2.get(0), posicion1.get(1));
					} else {
						j2.setLocation(posicion1.get(0), posicion1.get(1));
					}
					j2.setVisible(true);
				} else if (cont == 3) {
					if (j1.getX() != posicion1.get(0) & j2.getX() != posicion1.get(0)) {
						j3.setLocation(posicion1.get(0), posicion1.get(1));
					} else if (j1.getX() != posicion2.get(0) & j2.getX() != posicion2.get(0)) {
						j3.setLocation(posicion2.get(0), posicion1.get(1));
					} else {
						j3.setLocation(posicion3.get(0), posicion1.get(1));
					}
					j3.setVisible(true);
				} else if (cont == 4) {
					if (j1.getX() != posicion1.get(0) & j2.getX() != posicion1.get(0)
						& j3.getX() != posicion1.get(0)){
						j4.setLocation(posicion1.get(0), posicion1.get(1));
					} else if (j1.getX() != posicion2.get(0) & j2.getX() != posicion2.get(0)
							& j3.getX() != posicion2.get(0)) {
						j4.setLocation(posicion2.get(0), posicion1.get(1));
					} else if (j1.getX() != posicion3.get(0) & j2.getX() != posicion3.get(0)
							& j3.getX() != posicion3.get(0)) {
						j4.setLocation(posicion3.get(0), posicion1.get(1));
					}else {
						j4.setLocation(posicion4.get(0), posicion1.get(1));
					}
					j4.setVisible(true);
				} else if (cont == 5) {
					if (j1.getX() != posicion1.get(0) & j2.getX() != posicion1.get(0)
						& j3.getX() != posicion1.get(0) & j4.getX() != posicion1.get(0)){
							j5.setLocation(posicion1.get(0), posicion1.get(1));
						} else if (j1.getX() != posicion2.get(0) & j2.getX() != posicion2.get(0)
						  & j3.getX() != posicion2.get(0) & j4.getX() != posicion2.get(0)) {
							j5.setLocation(posicion2.get(0), posicion1.get(1));
						} else if (j1.getX() != posicion3.get(0) & j2.getX() != posicion3.get(0)
						  & j3.getX() != posicion3.get(0) & j4.getX() != posicion3.get(0)) {
							j5.setLocation(posicion3.get(0), posicion1.get(1));
						} else if (j1.getX() != posicion4.get(0) & j2.getX() != posicion4.get(0)
								  & j3.getX() != posicion4.get(0) & j4.getX() != posicion4.get(0)) {
									j5.setLocation(posicion4.get(0), posicion1.get(1));
						} else {
							j5.setLocation(posicion5.get(0), posicion1.get(1));
						}
						j5.setVisible(true);
				} else if (cont == 6) {
					if (j1.getX() != posicion1.get(0) & j2.getX() != posicion1.get(0)
						& j3.getX() != posicion1.get(0) & j4.getX() != posicion1.get(0)
						& j5.getX() != posicion1.get(0)){
								j6.setLocation(posicion1.get(0), posicion1.get(1));
							} else if (j1.getX() != posicion2.get(0) & j2.getX() != posicion2.get(0)
							  & j3.getX() != posicion2.get(0) & j4.getX() != posicion2.get(0) 
							  & j5.getX() != posicion2.get(0)) {
								j6.setLocation(posicion2.get(0), posicion1.get(1));
							} else if (j1.getX() != posicion3.get(0) & j2.getX() != posicion3.get(0)
							  & j3.getX() != posicion3.get(0) & j4.getX() != posicion2.get(0) 
							  & j5.getX() != posicion3.get(0)) {
								j6.setLocation(posicion3.get(0), posicion1.get(1));
							} else if (j1.getX() != posicion4.get(0) & j2.getX() != posicion4.get(0)
									  & j3.getX() != posicion4.get(0) & j4.getX() != posicion4.get(0)
									  & j5.getX() != posicion4.get(0)) {
										j6.setLocation(posicion4.get(0), posicion1.get(1));
							} else if (j1.getX() != posicion5.get(0) & j2.getX() != posicion5.get(0)
									  & j3.getX() != posicion5.get(0) & j4.getX() != posicion5.get(0)
									  & j5.getX() != posicion5.get(0)) {
										j6.setLocation(posicion5.get(0), posicion1.get(1));
							
							} else {
								j6.setLocation(posicion6.get(0), posicion1.get(1));
							}
							j6.setVisible(true);
				}
				}
				
			}
		});
		
		atras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (cont != 1) {
					cont--;
					lblElige.setText("J"+cont+" ELIGE TU PERSONAJE");
				} else if (cont == 1){
					dispose();
					new VentanaNJugadores();
				} 
				
			}	
		});
		
		
		
	}
		
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VentanaSeleccionPersonaje v = new VentanaSeleccionPersonaje();
		

	}
	

}
