package Clases;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class VentanaInicio extends JFrame{
	
	protected JLabel lblLogo;
	protected JPanel pnlCentral;
	protected JPanel pnlLocal;
	protected JButton nuevaOnline;
	protected JButton nuevaLocal;
	protected JButton cargarLocal;
	protected JButton opciones;
	
	public VentanaInicio() {
		
		this.setLayout(new GridLayout(3,3));
		pnlCentral = new JPanel(new GridLayout(3,1));
		
		this.add(new JLabel(" "));
		this.add(new JLabel(" "));
		this.add(new JLabel(" "));
		this.add(new JLabel(" "));
		this.add(pnlCentral);
		this.add(new JLabel(" "));
		this.add(new JLabel(" "));
		this.add(new JLabel(" "));
		this.add(new JLabel(" "));
		
		
		pnlLocal = new JPanel(new GridLayout(1,2));
		
		nuevaOnline = new JButton("Nueva Partida Online");
		pnlCentral.add(nuevaOnline);
		
		
		nuevaLocal = new JButton("<html>Nueva Partida<br>     Local</html>");
		pnlLocal.add(nuevaLocal);
		
		cargarLocal = new JButton("Cargar Local");
		pnlLocal.add(cargarLocal);
		
		pnlCentral.add(pnlLocal);
		
		opciones = new JButton("Opciones");
		pnlCentral.add(opciones);
		
		lblLogo = new JLabel("");
		
		
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("CLUEDO");
		this.setSize(800, 600);
		this.setVisible(true);
		
		Font defaultFont = nuevaOnline.getFont();
		System.out.println(defaultFont);
	}
	
	
	
	
	public static void main(String[] args) {
		
		VentanaInicio ventana = new VentanaInicio();
	}

}
