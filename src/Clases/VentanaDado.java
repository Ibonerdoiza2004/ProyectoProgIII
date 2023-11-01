package Clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaDado extends JFrame{
	
	private static final Dimension TAMAYO = new Dimension(480, 640);
	
	//Creo este main para hacer las pruebas (luego se creará esta ventana para tirar los datos)
	public static void main(String[] args) {
		
		VentanaDado ventDados = new VentanaDado();
		ventDados.setVisible(true);
		//Mirar si funcionan los datos. Ver si se actualiza el movimineto del jugador (las celdas que pede avanzar, el atributo movimiento)
		
	}
	
	private JButton btnTirar;
	private Random r = new Random();
	private JPanel pnlDado;
	private JLabel lblD1;
	private JLabel lblD2;
	private int valorDado1;
	private int valorDado2;
	private Gestion g = new Gestion();
	
	public VentanaDado() {
		
		setSize(TAMAYO);
		setTitle("Ventana dados");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo( null ); //La pone respectivo a la ventana
		
		pnlDado = new JPanel();
		pnlDado.setBackground(Color.gray);
		
		lblD1 = new JLabel();
		lblD2 = new JLabel();
		
		btnTirar = new JButton("Tirar Dado!");
		btnTirar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				tirarDado();
			}
		});
		
		getContentPane().add(btnTirar, BorderLayout.NORTH);
		
	}
	
	
	public int getValorDado1() {
		return valorDado1;
	}

	public void setValorDado1(int valorDado1) {
		this.valorDado1 = valorDado1;
	}

	public int getValorDado2() {
		return valorDado2;
	}

	public void setValorDado2(int valorDado2) {
		this.valorDado2 = valorDado2;
	}


	public void tirarDado() {
		
		JLabel lDado1 = new JLabel();
		JLabel lDado2 = new JLabel();
		
		Thread hilo = new Thread() {
			
			@Override
			public void run() {
				
				btnTirar.setEnabled( false );
				long tiemploInicial = System.currentTimeMillis();
				
				while(System.currentTimeMillis() - tiemploInicial < 5000) { //La aimación de tirar dados durará 5 segundos aproximadamente
					
					int num = r.nextInt(6)+1;
					switch(num) {
					case (1):
						lblD1.setIcon(new ImageIcon(getClass().getResource("uno.jpg")));
						setValorDado1(num);
						System.out.println(valorDado1);
						break; 
					case (2):
						lblD1.setIcon(new ImageIcon(getClass().getResource("dos.jpg")));
						setValorDado1(num);
						System.out.println(valorDado1);
						break;
					case (3):
						lblD1.setIcon(new ImageIcon(getClass().getResource("tres.jpg")));
						setValorDado1(num);
						System.out.println(valorDado1);
						break;
					case (4):
						lblD1.setIcon(new ImageIcon(getClass().getResource("cuatro.jpg")));
						setValorDado1(num);
						System.out.println(valorDado1);
						break;
					case (5):
						lblD1.setIcon(new ImageIcon(getClass().getResource("cinco.jpg")));
						setValorDado1(num);
						System.out.println(valorDado1);
						break;
					case (6):
						lblD1.setIcon(new ImageIcon(getClass().getResource("seis.jpg")));
						setValorDado1(num);
						System.out.println(valorDado1);
						break;
					}
					int num2 = r.nextInt(6)+1;
					switch(num2) {
					case (1):
						lblD2.setIcon(new ImageIcon(getClass().getResource("uno.jpg")));
						setValorDado2(num2);
						break;
					case (2):
						lblD2.setIcon(new ImageIcon(getClass().getResource("dos.jpg")));
						setValorDado2(num2);
						break;
					case (3):
						lblD2.setIcon(new ImageIcon(getClass().getResource("tres.jpg")));
						setValorDado2(num2);
						break;
					case (4):
						lblD2.setIcon(new ImageIcon(getClass().getResource("cuatro.jpg")));
						setValorDado2(num2);
						break;
					case (5):
						lblD1.setIcon(new ImageIcon(getClass().getResource("cinco.jpg")));
						setValorDado2(num2);
						break;
					case (6):
						lblD2.setIcon(new ImageIcon(getClass().getResource("seis.jpg")));
						setValorDado2(num2);
						break;
					}
					
					//Cojo las fotos después de que hayan pasado entre 1.8 y 2 segundos pero que siga la animacíon para que de tiempo a cargar las fotos
					if (System.currentTimeMillis() - tiemploInicial > 1800 & System.currentTimeMillis() - tiemploInicial < 2000) { //Guardar la foto al de dos sgundos y al de cinco que se pongan esas dos fotos
						lDado1.setIcon(lblD1.getIcon());
						lDado2.setIcon(lblD2.getIcon());
						int n1 = getValorDado1();
						int n2 = getValorDado2();
						System.out.println(n1 + n2 + "------------"); //Prueba
						g.tiradaDados(n1, n2);
						System.out.println("Celdas que puede avanzar este jugador: " + g.getMovimiento());
					}
					
					pnlDado.add(lblD1);
					pnlDado.add(lblD2);
					getContentPane().add(pnlDado);
					
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				lblD1.setIcon(lDado1.getIcon());
				lblD2.setIcon(lDado2.getIcon());
				pnlDado.add(lblD1);
				pnlDado.add(lblD2);
				btnTirar.setEnabled(true);
			}
			
		};
		hilo.start();
	}
	
}
