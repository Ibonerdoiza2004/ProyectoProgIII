package Clases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

public class VentanaDerrota extends JFrame{
	
	protected JButton btnContinuar;
	
	public VentanaDerrota() {
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.setPreferredSize(new Dimension(220,80));
		btnContinuar.setSize(new Dimension(220,80));
		
		JPanel pnlVentana = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon iconoFondo = new ImageIcon(getClass().getResource("VentanaDerrotaFondo.jpg"));
                Image imagenFondo = iconoFondo.getImage();
                g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            }
        };

        
        pnlVentana.add(btnContinuar);
        pnlVentana.setLayout(null);
        btnContinuar.setLocation(670,780);
        
		setContentPane(pnlVentana);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
		
		
	}
	
	
	public static void main(String[] args) {
		
		VentanaDerrota ventana = new VentanaDerrota();
	}

}
