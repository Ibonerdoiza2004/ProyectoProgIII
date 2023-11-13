package Clases;

import javax.swing.*;

public class VentanaAjustes extends JFrame {
	
	private JLabel lblMusica;
	private JSpinner spinMusica;
	private JLabel lblSonido;
	private JSpinner spinSonido;
	
	public VentanaAjustes() {
		
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("AJUSTES");
		this.setVisible(true);
	
	}
}
