package Clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VentanaDado extends JPanel{
	
	private static final Dimension TAMAYO = new Dimension(480, 640);
	
	//Creo este main para hacer las pruebas (luego se creará esta ventana para tirar los datos)
	public static void main(String[] args) {
		
		VentanaDado ventDados = new VentanaDado();
		ventDados.setVisible(true);
		//Mirar si funcionan los datos. Ver si se actualiza el movimineto del jugador (las celdas que pede avanzar, el atributo movimiento)
		
	}
	
	private JButton btnTirar = new JButton("Tirar Dado!");
	private Random r = new Random();
	//private JPanel pnlDado;
	private JLabel lblD1;
	private JLabel lblD2;
	private int valorDado1;
	private int valorDado2;
	private Gestion g = new Gestion();
	//private JPanel pnlVentana;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        ImageIcon iconoFondo = new ImageIcon(getClass().getResource("FondoDados.jpeg"));
        Image imagenFondo = iconoFondo.getImage();
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
	}
	
	public VentanaDado() {
		
		setSize(TAMAYO);
		
		this.setLayout(null);  //4: dos dados y dos botones
 
		
		
//		pnlDado = new JPanel(new GridLayout(2,1));
//		pnlDado.setBackground(Color.gray);
		
		lblD1 = new JLabel();
		
//		lblD1.setIcon(new ImageIcon(getClass().getResource("uno.jpg")));
//		lblD1.setBounds((int)(getWidth()/2-lblD1.getIcon().getIconWidth()/2), 30, getWidth()/2+lblD1.getIcon().getIconWidth()/2, 30+lblD1.getIcon().getIconHeight());
//		lblD1.setSize(400, 200);
		ImageIcon originalIcon = new ImageIcon(getClass().getResource("uno.jpg"));
		Image originalImage = originalIcon.getImage();

		// Calcula el nuevo tamaño para la imagen manteniendo la proporción
		int newHeight = getHeight();
		int newWidth = (int) ((double) originalImage.getWidth(null) / originalImage.getHeight(null) * newHeight);  // Establece el nuevo ancho deseado

		// Escala la imagen al nuevo tamaño
		Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

		// Crea un nuevo ImageIcon con la imagen escalada
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		
		// Establece el nuevo ImageIcon en tu JLabel
		lblD1.setIcon(scaledIcon);
		lblD1.setBounds((int)(getWidth()/2 - newWidth/2), 30, (int)(getWidth()/2 + newWidth/2), 30 + newHeight);
		lblD1.setSize(newWidth, newHeight);
		
		
		
		lblD2 = new JLabel();
		lblD2.setIcon(new ImageIcon(getClass().getResource("uno.jpg")));
		
		//lblD2.setBounds((int)(getWidth()/2-lblD2.getIcon().getIconWidth()/2), 30, getWidth()/2+lblD2.getIcon().getIconWidth()/2, 9*(30+lblD1.getIcon().getIconHeight()));
		
		btnTirar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				tirarDado();
			}
		});
		btnTirar.setBackground(Color.green);
		btnTirar.setBounds(0 , getHeight() ,getWidth()+100 , 100);
		
		this.add(lblD1);
		this.add(lblD2);
		this.add(btnTirar);
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
	
	protected void escoderBoton() {
		btnTirar.setVisible( false );
	}
	protected void verBoton() {
		btnTirar.setVisible( true );
	}
	public void tirarDado() {
		
//		JLabel lDado1 = new JLabel();
//		JLabel lDado2 = new JLabel();
		
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
						lblD1.setIcon(lblD1.getIcon());
						lblD2.setIcon(lblD2.getIcon());
						int n1 = getValorDado1();
						int n2 = getValorDado2();
						System.out.println(n1 + n2 + "------------"); //Prueba
						g.tiradaDados(n1, n2);
						System.out.println("Celdas que puede avanzar este jugador: " + g.getMovimiento());
					}
					
					lblD1.setBounds((int)(getWidth()/2-lblD1.getIcon().getIconWidth()/2), 30, getWidth()/2+lblD1.getIcon().getIconWidth()/2, 30+lblD1.getIcon().getIconHeight());
					add(lblD1);
					add(lblD2);
					//setContentPane(pnlVentana);
					
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				//Esto se hace simplemente para que vaya cargando el mapa
				lblD1.setIcon(lblD1.getIcon());
				lblD2.setIcon(lblD2.getIcon());
				add(lblD1);
				add(lblD2);
				//setContentPane(pnlVentana);
				btnTirar.setEnabled(true);
			}
			
		};
		hilo.start();
	}
	
}