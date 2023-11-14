package Clases;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaDerrota extends JFrame{
	
	protected JButton btnCerrar;
	
	public VentanaDerrota() {
		
		btnCerrar = new JButton("Cerrar");
		
		JPanel pnlVentana = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon iconoFondo = new ImageIcon(getClass().getResource("VentanaVictoriaFondo.jpg"));
                Image imagenFondo = iconoFondo.getImage();
                g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        pnlVentana.add(btnCerrar, BorderLayout.SOUTH);
       
		
        btnCerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();	
			}
		});
        
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
