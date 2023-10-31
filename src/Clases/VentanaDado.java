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
		
	}
	
	private JButton btnTirar;
	private Random r = new Random();
	private JPanel pnlDado;
	private JLabel lblD1;
	private JLabel lblD2;
	
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
	
	public void tirarDado() {
		
		Thread hilo = new Thread() {
			
			@Override
			public void run() {
				
				btnTirar.setEnabled( false );
				long tiemploInicail = System.currentTimeMillis();
				
				while(System.currentTimeMillis() - tiemploInicail < 5000) { //La aimación de tirar dados durará 5 segundos aproximadamente
					
					int num = r.nextInt(6)+1;
					switch(num) {
					case (1):
						lblD1.setIcon(new ImageIcon(getClass().getResource("uno.jpg")));
						break; 
					case (2):
						lblD1.setIcon(new ImageIcon(getClass().getResource("dos.jpg")));
						break;
					case (3):
						lblD1.setIcon(new ImageIcon(getClass().getResource("tres.jpg")));
						break;
					case (4):
						lblD1.setIcon(new ImageIcon(getClass().getResource("cuatro.jpg")));
						break;
					case (5):
						lblD1.setIcon(new ImageIcon(getClass().getResource("cinco.jpg")));
						break;
					case (6):
						lblD1.setIcon(new ImageIcon(getClass().getResource("seis.jpg")));
						break;
					}
					int num2 = r.nextInt(6)+1;
					switch(num2) {
					case (1):
						lblD2.setIcon(new ImageIcon(getClass().getResource("uno.jpg")));
						break;
					case (2):
						lblD2.setIcon(new ImageIcon(getClass().getResource("dos.jpg")));
						break;
					case (3):
						lblD2.setIcon(new ImageIcon(getClass().getResource("tres.jpg")));
						break;
					case (4):
						lblD2.setIcon(new ImageIcon(getClass().getResource("cuatro.jpg")));
						break;
					case (5):
						lblD1.setIcon(new ImageIcon(getClass().getResource("cinco.jpg")));
						break;
					case (6):
						lblD2.setIcon(new ImageIcon(getClass().getResource("seis.jpg")));
						break;
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
				btnTirar.setEnabled(true);
			}
			
		};
		hilo.start();
	}
	
}
