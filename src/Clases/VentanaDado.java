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
	protected JButton btnTirar = new JButton("Tirar Dado!");
	private Random r = new Random();
	//private JPanel pnlDado;
	private JLabel lblD1;
	private JLabel lblD2;
	protected int valorDado1;
	protected int valorDado2;
	protected Thread hilo;
//	protected boolean repintarDado;
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
		ImageIcon originalIcon = new ImageIcon(getClass().getResource("1.png"));
		Image originalImage = originalIcon.getImage();

		// Calcula el nuevo tamaño para la imagen manteniendo la proporción
		int tamanyoHastaBoton = getHeight()-120;
		int newHeight = 2*getHeight()/5;
		int newWidth = (int) ((double) originalImage.getWidth(null) / originalImage.getHeight(null) * newHeight);  // Establece el nuevo ancho deseado

		// Escala la imagen al nuevo tamaño
		Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

		// Crea un nuevo ImageIcon con la imagen escalada
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		lblD1.setIcon(scaledIcon);
		lblD1.setBounds((int)(getWidth()/2 - newWidth/2), (tamanyoHastaBoton-2*newHeight)/3, newWidth, newHeight);
		lblD2 = new JLabel();
		int x = lblD1.getX();
		int y = lblD1.getY() + lblD1.getHeight() + (tamanyoHastaBoton-2*newHeight)/3;  // 10 es la separación deseada entre los JLabels

		// Crear el segundo JLabel
		lblD2.setIcon(scaledIcon);
		lblD2.setBounds(x, y, newWidth, newHeight);
		lblD2.setSize(newWidth, newHeight);
		//lblD2.setBounds((int)(getWidth()/2-lblD2.getIcon().getIconWidth()/2), 30, getWidth()/2+lblD2.getIcon().getIconWidth()/2, 9*(30+lblD1.getIcon().getIconHeight()));
		btnTirar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tirarDado();
			}
		});
		btnTirar.setBackground(Color.green);
		btnTirar.setBounds(20 , tamanyoHastaBoton ,getWidth()-40 , 100);
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
		hilo = new Thread() {
			@Override
			public void run() {
				btnTirar.setEnabled( false );
				long tiemploInicial = System.currentTimeMillis();
				add(lblD1);
				add(lblD2);
				String lock = "lock";
				synchronized (lock) {
					valorDado1 = r.nextInt(6)+1;
					valorDado2 = r.nextInt(6)+1;
					lock.notifyAll();
					}
				//System.out.println(valorDado1 +" "+valorDado2);
				//System.out.println(valorDado1 +" "+valorDado2);
				while(System.currentTimeMillis() - tiemploInicial < 5000) { //La aimación de tirar dados durará 5 segundos aproximadamente
					int num = r.nextInt(6)+1;
					
					corregirImagen(lblD1, new ImageIcon(getClass().getResource(num+".png")));
					//lblD1.setIcon(new ImageIcon(getClass().getResource(num+".png")));
					int num2 = r.nextInt(6)+1;
					corregirImagen(lblD2, new ImageIcon(getClass().getResource(num2+".png")));
					//lblD2.setIcon(new ImageIcon(getClass().getResource(num2+".png")));
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				corregirImagen(lblD1, new ImageIcon(getClass().getResource(valorDado1+".png")));
				corregirImagen(lblD2, new ImageIcon(getClass().getResource(valorDado2+".png")));
			}
		};
		hilo.start();
	}
	
	public void corregirImagen(JLabel lblDado, ImageIcon imagenOriginal) {
		Image originalImage = imagenOriginal.getImage();
		int tamanyoHastaBoton = getHeight()-120;
		int newHeight = 2*getHeight()/5;
		int newWidth = (int) ((double) originalImage.getWidth(null) / originalImage.getHeight(null) * newHeight);
		Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		lblDado.setIcon(scaledIcon);
	}
}