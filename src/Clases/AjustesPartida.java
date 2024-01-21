package Clases;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AjustesPartida extends JInternalFrame{
	private JLabel lblAjustes;
	private JButton guardar;
	private JButton btnMusica;
	private JButton btnMenu;
	private JButton btnCerrar;
	
	public AjustesPartida() {
		
		
		setTitle("Ventana de ajustes");
		setClosable(true);
		this.setSize((int) Gestion.sizePantalla.getWidth()*1/3, (int) Gestion.sizePantalla.getHeight()*1/3);
		this.setLocation((int)Gestion.sizePantalla.getWidth()/2 - this.getWidth()/2, (int)Gestion.sizePantalla.getHeight()/2 - this.getHeight()/2);
		this.setLayout(null);
		Gestion.ventanaJuego.add(this);
		lblAjustes = new JLabel("AJUSTES");
		lblAjustes.setBounds((int) this.getWidth()*1/3, (int)this.getHeight()*1/9, (int) this.getWidth()*1/3, (int) this.getHeight()*1/9);
		Font totFont = lblAjustes.getFont();
		lblAjustes.setFont(new Font(totFont.getName(), totFont.getStyle(), 30));
		lblAjustes.setForeground(Color.WHITE);
		lblAjustes.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblAjustes);
		
		btnMusica = new JButton("");
		if (Gestion.dejarDeSonar.get()==false) {
			btnMusica.setText("Parar Música");
		} else {
			btnMusica.setText("Reproducir Música");
		}
		btnMusica.setBounds((int) this.getWidth()*1/3, (int)this.getHeight()*3/9, (int) this.getWidth()*1/3, (int) this.getHeight()*1/9);
		this.add(btnMusica);
		
		guardar = new JButton("Guardar");
		guardar.setBounds((int) this.getWidth()*1/3, (int)this.getHeight()*5/9, (int) this.getWidth()*1/6, (int) this.getHeight()*1/9);
		this.add(guardar);
		
		btnCerrar = new JButton("Seguir");
		btnCerrar.setBounds((int)this.getWidth()*1/3+(int)this.getWidth()*1/6, (int)this.getHeight()*5/9, (int) this.getWidth()*1/6, (int) this.getHeight()*1/9);
		this.add(btnCerrar);
		
		btnMenu = new JButton("Salir al Menú");
		btnMenu.setBounds((int) this.getWidth()*1/3, (int)this.getHeight()*7/9, (int) this.getWidth()*1/3, (int) this.getHeight()*1/9);
		this.add(btnMenu);
        
		JLabel label = new JLabel();
		JLabel label2 = new JLabel();
		JLabel label3 = new JLabel();
		JLabel label4 = new JLabel();
		JLabel label5 = new JLabel();
		JLabel label6 = new JLabel();
		JLabel label7 = new JLabel();
		JLabel label8 = new JLabel();
		JLabel label9 = new JLabel();
		
		ImageIcon fondo = new ImageIcon(getClass().getResource("FondoAjustes2.jpg"));
		Image image = fondo.getImage();
		Image newImage = image.getScaledInstance((int) Gestion.sizePantalla.getWidth()*1/9, (int) Gestion.sizePantalla.getHeight()*1/9, Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImage);
		
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
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		
		
		btnCerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
		guardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Falta el metodo
				
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
		
		btnMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
	}
	

	
	
	public static void main (String[]args){
		AjustesPartida v = new AjustesPartida();
	}

	}
	

