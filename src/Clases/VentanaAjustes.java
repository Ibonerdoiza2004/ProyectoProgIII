package Clases;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import javazoom.jl.decoder.JavaLayerException;

public class VentanaAjustes extends JFrame {
	
	private JLabel lblAjustes;
	private JButton btnMusica;
	private JButton btnCerrar;
	
	public VentanaAjustes() {
		
		this.setSize((int) Gestion.sizePantalla.getWidth()*1/3, (int) Gestion.sizePantalla.getHeight()*1/3);
		
		this.setLayout(null);
		
		lblAjustes = new JLabel("AJUSTES");
		lblAjustes.setBounds((int) this.getWidth()*1/3, (int)this.getHeight()*1/7, (int) this.getWidth()*1/3, (int) this.getHeight()*1/7);
		Font totFont = lblAjustes.getFont();
		lblAjustes.setFont(new Font(totFont.getName(), totFont.getStyle(), 30));
		lblAjustes.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjustes.setForeground(Color.WHITE);
		this.add(lblAjustes);
		
		btnMusica = new JButton("");
		if (Gestion.dejarDeSonar.get()==false) {
			btnMusica.setText("Parar Música");
		} else {
			btnMusica.setText("Reproducir Música");
		}
		btnMusica.setBounds((int) this.getWidth()*1/3, (int)this.getHeight()*3/7, (int) this.getWidth()*1/3, (int) this.getHeight()*1/7);
		this.add(btnMusica);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds((int)this.getWidth()*1/3, (int)this.getHeight()*5/7, (int) this.getWidth()*1/3, (int) this.getHeight()*1/7);
		this.add(btnCerrar);
		
		ImageIcon fondo = new ImageIcon(getClass().getResource("FondoAjustes2.jpg"));
		Image image = fondo.getImage();
		Image newImage = image.getScaledInstance((int) Gestion.sizePantalla.getWidth()*1/9, (int) Gestion.sizePantalla.getHeight()*1/9, Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImage);
		
		JLabel label = new JLabel();
		JLabel label2 = new JLabel();
		JLabel label3 = new JLabel();
		JLabel label4 = new JLabel();
		JLabel label5 = new JLabel();
		JLabel label6 = new JLabel();
		JLabel label7 = new JLabel();
		JLabel label8 = new JLabel();
		JLabel label9 = new JLabel();
		
		label.setIcon(newIcon);
		label2.setIcon(newIcon);
		label3.setIcon(newIcon);
		label4.setIcon(newIcon);
		label5.setIcon(newIcon);
		label6.setIcon(newIcon);
		label7.setIcon(newIcon);
		label8.setIcon(newIcon);
		label9.setIcon(newIcon);
		
		label.setBounds(0,0,(int) Gestion.sizePantalla.getWidth()*1/3, (int) Gestion.sizePantalla.getHeight()*1/3);
		label2.setBounds(0,0-(int) Gestion.sizePantalla.getHeight()*1/9,(int) Gestion.sizePantalla.getWidth()*1/3, (int) Gestion.sizePantalla.getHeight()*1/3);
		label3.setBounds(0,0+(int) Gestion.sizePantalla.getHeight()*1/9,(int) Gestion.sizePantalla.getWidth()*1/3, (int) Gestion.sizePantalla.getHeight()*1/3);
		
		label4.setBounds(0+(int) Gestion.sizePantalla.getWidth()*1/9,0,(int) Gestion.sizePantalla.getWidth()*1/3, (int) Gestion.sizePantalla.getHeight()*1/3);
		label5.setBounds(0+(int) Gestion.sizePantalla.getWidth()*1/9,0-(int) Gestion.sizePantalla.getHeight()*1/9,(int) Gestion.sizePantalla.getWidth()*1/3, (int) Gestion.sizePantalla.getHeight()*1/3);
		label6.setBounds(0+(int) Gestion.sizePantalla.getWidth()*1/9,0+(int) Gestion.sizePantalla.getHeight()*1/9,(int) Gestion.sizePantalla.getWidth()*1/3, (int) Gestion.sizePantalla.getHeight()*1/3);
		
		label7.setBounds(0+((int) Gestion.sizePantalla.getWidth()*1/9)*2,0,(int) Gestion.sizePantalla.getWidth()*1/3, (int) Gestion.sizePantalla.getHeight()*1/3);
		label8.setBounds(0+((int) Gestion.sizePantalla.getWidth()*1/9)*2,0-(int) Gestion.sizePantalla.getHeight()*1/9,(int) Gestion.sizePantalla.getWidth()*1/3, (int) Gestion.sizePantalla.getHeight()*1/3);
		label9.setBounds(0+((int) Gestion.sizePantalla.getWidth()*1/9)*2,0+(int) Gestion.sizePantalla.getHeight()*1/9,(int) Gestion.sizePantalla.getWidth()*1/3, (int) Gestion.sizePantalla.getHeight()*1/3);
		
		this.add(label);
		this.add(label2);
		this.add(label3);
		
		this.add(label4);
		this.add(label5);
		this.add(label6);
		
		this.add(label7);
		this.add(label8);
		this.add(label9);
		
		this.setLocation((int) Gestion.sizePantalla.getWidth()*1/3, (int) Gestion.sizePantalla.getHeight()*1/3);
        this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(true);
		this.setVisible(true);
		
		
		
		btnCerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
		btnMusica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Gestion.dejarDeSonar.get() == true) {
					Gestion.tMusica = new Thread(new Runnable() {
						@Override
							public void run() {
								Gestion.sonar();
									
							}
					});
			    	Gestion.tMusica.start();
					btnMusica.setText("Parar música");
					Gestion.dejarDeSonar.set(false);
				} else {
					Gestion.dejarDeSonar.set(true);
					Gestion.player.close();
					Gestion.tMusica.interrupt();
					btnMusica.setText("Reproducir música");
					
					
				}
			}
			
		});
	}
}