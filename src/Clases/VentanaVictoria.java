package Clases;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaVictoria extends JFrame{

	protected JButton btnVolver;
	
	public VentanaVictoria() {
		
		btnVolver = new JButton("Volver al menú");
		btnVolver.setPreferredSize(new Dimension(220,80));
		btnVolver.setSize(new Dimension(220,80));
		
		JPanel pnlVentana = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon iconoFondo = new ImageIcon(getClass().getResource("VentanaVictoriaFondo.jpg"));
                Image imagenFondo = iconoFondo.getImage();
                g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
      
        pnlVentana.add(btnVolver);
        pnlVentana.setLayout(null);
        btnVolver.setLocation(670,780);
	  
		setContentPane(pnlVentana);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);		
		this.setVisible(true);
		
		
	}
	
	
	public static void main(String[] args) {
		
		VentanaVictoria ventana = new VentanaVictoria();
		

	}

}
