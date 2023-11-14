package Clases;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;


public class VentanaInicio extends JFrame{
	
	protected JPanel pnlCentral;
	protected JPanel pnlLocal;
	protected JButton nuevaOnline;
	protected JButton nuevaLocal;
	protected JButton cargarLocal;
	protected JButton opciones;
	protected JButton cerrar;
	
	public VentanaInicio() {
		
		
		pnlCentral = new JPanel(new GridLayout(4,1));
		
		
		
		
		pnlLocal = new JPanel(new GridLayout(1,2));
		
		nuevaOnline = new JButton("NUEVA PARTIDA ONLINE");
		pnlCentral.add(nuevaOnline);
		
		
		nuevaLocal = new JButton("<html><div style='text-align: center;'>NUEVA PARTIDA<br>LOCAL</div></html>");
		nuevaLocal.setHorizontalAlignment(SwingConstants.CENTER);
		nuevaLocal.setVerticalAlignment(SwingConstants.CENTER);
		pnlLocal.add(nuevaLocal);
		
		cargarLocal = new JButton("<html><div style='text-align: center;'>CARGAR PARTIDA<br>LOCAL</div></html>");
		cargarLocal.setHorizontalAlignment(SwingConstants.CENTER);
		cargarLocal.setVerticalAlignment(SwingConstants.CENTER);
		pnlLocal.add(cargarLocal);
		
		pnlCentral.add(pnlLocal);
		
		opciones = new JButton("AJUSTES");
		pnlCentral.add(opciones);
		
		cerrar = new JButton("CERRAR CLUEDO");
		pnlCentral.add(cerrar);
		
		
		JLabel lblLogo = new JLabel();
		ImageIcon iconoLogo = new ImageIcon(getClass().getResource("LogoCluedo.png"));
		Image imagenLogo = iconoLogo.getImage();
		Image tamanoLogo = imagenLogo.getScaledInstance(260, 80, java.awt.Image.SCALE_SMOOTH);
		iconoLogo = new ImageIcon(tamanoLogo);
		lblLogo.setIcon(iconoLogo);
		

	
		
		JPanel pnlVentana = new JPanel(new GridLayout(3,3)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon iconoFondo = new ImageIcon(getClass().getResource("FondoInicio.jpg"));
                Image imagenFondo = iconoFondo.getImage();
                g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        
		
		pnlVentana.add(new JLabel(" "));
		lblLogo.setHorizontalAlignment(JLabel.CENTER);
		pnlVentana.add(lblLogo);
		pnlVentana.add(new JLabel(" "));
		pnlVentana.add(new JLabel(" "));
		pnlVentana.add(pnlCentral);
		pnlVentana.add(new JLabel(" "));
		pnlVentana.add(new JLabel(" "));
		pnlVentana.add(new JLabel(" "));
		pnlVentana.add(new JLabel(" "));
		
		cerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();	
			}
		});
		
		opciones.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaAjustes();
				
			}
			
		});
		
		setContentPane(pnlVentana);
		
		
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		
		this.setTitle("CLUEDO");
		this.setVisible(true);
		
		Font defaultFont = nuevaOnline.getFont();
		System.out.println(defaultFont);
	}
	
	
	public static void main(String[] args) {
		
		VentanaInicio ventana = new VentanaInicio();
	}

}
