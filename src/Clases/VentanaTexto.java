package Clases;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.FocusManager;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaTexto extends JPanel{
	float alpha = 1;
	public VentanaTexto(String sTexto) {
		setSize(Gestion.sizePantalla);
		setLayout(null);
		setBackground(Color.BLACK);
		URL fondoURL = getClass().getResource("fondoTexto.jpg");
		ImageIcon fondo = new ImageIcon(fondoURL);
		Image imagenFondo =fondo.getImage().getScaledInstance(getWidth(), getHeight(), java.awt.Image.SCALE_SMOOTH);
		fondo =new ImageIcon(imagenFondo);
		JLabel lFondo = new JLabel();
		lFondo.setIcon(fondo);
		lFondo.setBounds(0, 0, getWidth(), getHeight());
		
		JLabel texto = new JLabel(sTexto);
		texto.setFont(new Font("Serif", Font.BOLD, 80));
	    texto.setForeground(Color.WHITE);
		texto.setVerticalAlignment(JLabel.CENTER);
		texto.setHorizontalAlignment(JLabel.CENTER);
		texto.setOpaque(false);
		texto.setBounds(0, 0, getWidth(), getHeight());
		
		JLabel consejo = new JLabel("Pulsa ENTER para continuar");
		consejo.setFont(new Font("Serif", Font.BOLD, 20));
		consejo.setForeground(Color.WHITE);
		consejo.setVerticalAlignment(JLabel.BOTTOM);
		consejo.setHorizontalAlignment(JLabel.CENTER);
		consejo.setOpaque(false);
		consejo.setBounds(0, 0, getWidth(), getHeight()-10);
		add(texto);
		add(consejo);
		add(lFondo);
		
		Gestion.ventanaJuego.add(this);
		repaint();
		this.requestFocus();
		
		
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(alpha>0) {
				    if(alpha>=0.03) {
				    	alpha -= 0.03;
				    }else{
				    	alpha = 0;
				    }
				    lFondo.setIcon(new ImageIcon(imagenFondo) {
			            @Override
			            public void paintIcon(Component c, Graphics g, int x, int y) {
			                Graphics2D g2 = (Graphics2D) g.create();
			                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			                super.paintIcon(c, g2, x, y);
			                g2.dispose();
			            }
			        });
				    texto.setForeground(new Color(1, 1, 1, alpha));
				    consejo.setForeground(new Color(1, 1, 1, alpha));
				    try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				String lockSiguienteVentana = "siguienteVentana";
				synchronized (lockSiguienteVentana) {
					lockSiguienteVentana.notifyAll();
				}try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				String lockAnyadirALaVentana = "AnyadirALaVentana";
				synchronized (lockAnyadirALaVentana) {
					lockAnyadirALaVentana.notifyAll();
				}
				eliminarPanel();

			}
		});
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					t.start();
					removeKeyListener(this);
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
	}
	public void eliminarPanel() {
		Gestion.ventanaJuego.remove(this);
        Gestion.ventanaJuego.repaint();
	}
}
