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
	
	private JButton btnTirar = new JButton("Tirar Dado!");
	private Random r = new Random();
	//private JPanel pnlDado;
	private JLabel lblD1;
	private JLabel lblD2;
	private int valorDado1;
	private int valorDado2;
	private Gestion g = new Gestion();
	//private int newHeight;
	//private JPanel pnlVentana;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        ImageIcon iconoFondo = new ImageIcon(getClass().getResource("FondoDados.jpeg"));
        Image imagenFondo = iconoFondo.getImage();
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
	}
	
	public VentanaDado(int ancho, int alto) {
		
		setSize(ancho, alto);
		
		this.setLayout(null);  //Componentes por coordenadas
		
		lblD1 = new JLabel();
		ImageIcon originalIcon = new ImageIcon(getClass().getResource("UNO.png"));
		Image originalImage = originalIcon.getImage();

		// Calcula el nuevo tamaño para la imagen manteniendo la proporción
		int newHeight = getHeight()/5;
		int newWidth = (int) ((double) originalImage.getWidth(null) / originalImage.getHeight(null) * newHeight);  // Establece el nuevo ancho deseado

		// Escala la imagen al nuevo tamaño
		Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

		// Crea un nuevo ImageIcon con la imagen escalada
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		
		lblD1.setIcon(scaledIcon);
		lblD1.setBounds((int)(getWidth()/2 - newWidth/2), 30, (int)(getWidth()/2 + newWidth/2), 30 + newHeight);
		lblD1.setSize(newWidth, newHeight);
		
		lblD2 = new JLabel();
		int x = lblD1.getX();
		int y = lblD1.getY() + lblD1.getHeight() + 10;  // 10 es la separación deseada entre los JLabels

		// Crear el segundo JLabel
		lblD2.setIcon(new ImageIcon(getClass().getResource("UNO.png")));
		lblD2.setBounds(x, y, x + newWidth, y + newHeight);
		lblD2.setSize(newWidth, newHeight);
		
		//lblD2.setBounds((int)(getWidth()/2-lblD2.getIcon().getIconWidth()/2), 30, getWidth()/2+lblD2.getIcon().getIconWidth()/2, 9*(30+lblD1.getIcon().getIconHeight()));
		
		btnTirar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				tirarDado();
			}
		});
		btnTirar.setBackground(Color.green);
		btnTirar.setBounds(20 , getHeight()-120 ,getWidth()-40 , 100);
		
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
	
	protected void esconderBoton() {
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
						lblD1.setIcon(new ImageIcon(getClass().getResource("UNO.png")));
						setValorDado1(num);
						System.out.println(valorDado1);
						break; 
					case (2):
						lblD1.setIcon(new ImageIcon(getClass().getResource("DOS.png")));
						setValorDado1(num);
						System.out.println(valorDado1);
						break;
					case (3):
						lblD1.setIcon(new ImageIcon(getClass().getResource("TRES.png")));
						setValorDado1(num);
						System.out.println(valorDado1);
						break;
					case (4):
						lblD1.setIcon(new ImageIcon(getClass().getResource("CUATRO.png")));
						setValorDado1(num);
						System.out.println(valorDado1);
						break;
					case (5):
						lblD1.setIcon(new ImageIcon(getClass().getResource("CINCO.png")));
						setValorDado1(num);
						System.out.println(valorDado1);
						break;
					case (6):
						lblD1.setIcon(new ImageIcon(getClass().getResource("SEIS.png")));
						setValorDado1(num);
						System.out.println(valorDado1);
						break;
					}
					
					int num2 = r.nextInt(6)+1;
					switch(num2) {
					case (1):
						lblD2.setIcon(new ImageIcon(getClass().getResource("UNO.png")));
						setValorDado2(num2);
						break;
					case (2):
						lblD2.setIcon(new ImageIcon(getClass().getResource("DOS.png")));
						setValorDado2(num2);
						break;
					case (3):
						lblD2.setIcon(new ImageIcon(getClass().getResource("TRES.png")));
						setValorDado2(num2);
						break;
					case (4):
						lblD2.setIcon(new ImageIcon(getClass().getResource("CUATRO.png")));
						setValorDado2(num2);
						break;
					case (5):
						lblD1.setIcon(new ImageIcon(getClass().getResource("CINCO.png")));
						setValorDado2(num2);
						break;
					case (6):
						lblD2.setIcon(new ImageIcon(getClass().getResource("SEIS.png")));
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
					
					//lblD1.setBounds((int)(getWidth()/2 - newWidth/2), 30, (int)(getWidth()/2 + newWidth/2), 30 + newHeight);
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