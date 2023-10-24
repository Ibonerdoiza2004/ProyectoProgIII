package Clases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaDado extends JFrame{
	
	private static final Dimension TAMAYO = new Dimension(480, 640);
	
	//Creo este main para hacer las pruebas (luego se creará esta ventana para tirar los datos)
//	public static void main(String[] args) {
//		
//		
//		
//	}
	
	private JButton btnTirar;
	private Random r = new Random();
	private JPanel pnlDado;
	private JLabel lblP;
	private JLabel lblD2;
	
	public VentanaDado() {
		
		setSize(TAMAYO);
		setTitle("Ventana dados");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo( null ); //La pone respectivo a la ventana
		
		pnlDado = new JPanel();
		pnlDado.setBackground(Color.gray);
		
		lblP = new JLabel();
		lblD2 = new JLabel();
		
		btnTirar = new JButton("Tirar Dado!");
		btnTirar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Thread hilo = new Thread() {
					
					@Override
					public void run() {
						
						btnTirar.setEnabled( false );
						long tiemploInicail = System.currentTimeMillis();
						
						while(System.currentTimeMillis() - tiemploInicail < 5000) { //La aimación de tirar dados durará 5 segundos aproximadamente
							//Hago la lógica
						}
						
					}
					
				};
				
			}
		});
		
	}
	
}
