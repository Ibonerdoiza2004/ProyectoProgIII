package Clases;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

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
		this.add(lblAjustes);
		
		btnMusica = new JButton("Parar Música");
		btnMusica.setBounds((int) this.getWidth()*1/3, (int)this.getHeight()*3/7, (int) this.getWidth()*1/3, (int) this.getHeight()*1/7);
		this.add(btnMusica);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds((int)this.getWidth()*1/3, (int)this.getHeight()*5/7, (int) this.getWidth()*1/3, (int) this.getHeight()*1/7);
		this.add(btnCerrar);
		
		
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
				if (btnMusica.getText().equals("Parar Música")) {
					btnMusica.setText("Reproducir Música");
				} else {
					btnMusica.setText("Parar Música");
				}
			}
			
		});
	}
}