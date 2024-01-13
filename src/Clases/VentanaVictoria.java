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

public class VentanaVictoria extends JPanel{

	protected JButton btnVolverAlMenu;
	
	public VentanaVictoria() {
		
		btnVolverAlMenu = new JButton("Volver al menú");
		btnVolverAlMenu.setPreferredSize(new Dimension(220,80));
		btnVolverAlMenu.setSize(new Dimension(220,80));
		
      
        this.add(btnVolverAlMenu);
        this.setLayout(null);
        btnVolverAlMenu.setLocation(670,780);
	  
		btnVolverAlMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnVolverAlMenu.setEnabled(false);
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						Gestion.ventanaJuego.add(Gestion.vInicio);
						Gestion.ventanaJuego.repaint();
						eliminarPanel();
					}
				});
				t.start();
			}
		});
		setBounds(0,0,(int)Gestion.sizePantalla.getWidth(),(int)Gestion.sizePantalla.getHeight());
		Gestion.ventanaJuego.add(this);
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon iconoFondo = new ImageIcon(getClass().getResource("VentanaVictoriaFondo.jpg"));
        Image imagenFondo = iconoFondo.getImage();
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
    }
	public void eliminarPanel() {
		Gestion.ventanaJuego.remove(this);
        Gestion.ventanaJuego.repaint();
	}

}
