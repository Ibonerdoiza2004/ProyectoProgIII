package Clases;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class VentanaInicio extends JPanel{
	
	protected JPanel pnlCentral;
	protected JPanel pnlLocal;
	protected JButton nuevaOnline;
	protected JButton nuevaLocal;
	protected JButton cargarLocal;
	protected JButton opciones;
	protected JButton cerrar;
	Player player;
	
	public VentanaInicio() throws FileNotFoundException, JavaLayerException {
		
		
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
		Image tamanoLogo = imagenLogo.getScaledInstance(400, 150, java.awt.Image.SCALE_SMOOTH);
		iconoLogo = new ImageIcon(tamanoLogo);
		lblLogo.setIcon(iconoLogo);
	
		setLayout(new GridLayout(3,3));
        
        
		
		this.add(new JLabel(" "));
		lblLogo.setHorizontalAlignment(JLabel.CENTER);
		this.add(lblLogo);
		this.add(new JLabel(" "));
		this.add(new JLabel(" "));
		this.add(pnlCentral);
		this.add(new JLabel(" "));
		this.add(new JLabel(" "));
		this.add(new JLabel(" "));
		this.add(new JLabel(" "));
		nuevaLocal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		        new VentanaNJugadores();
		        eliminarPanel();
			}
		});
		
		cerrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Gestion.ventanaJuego.dispose();	
			}
		});
		
		opciones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaAjustes();
			}
		});
		
		
		Gestion.ventanaJuego = new JFrame();
		
		
		Gestion.ventanaJuego.addWindowListener (new WindowAdapter() {
			public void windowOpened (WindowEvent e) {
		    		   
				Thread t = new Thread(new Runnable() {
					@Override
						public void run() {
							sonar();
								
						}
					});
		    		t.start();
			}
	});
		
	
	Gestion.ventanaJuego.addWindowListener (new WindowAdapter() {
		public void windowClosed (WindowEvent e) {
			player.close();
		   	}
	});

		Gestion.ventanaJuego.setLayout(null);
		Gestion.ventanaJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Gestion.ventanaJuego.setSize(Gestion.sizePantalla);
		this.setSize(Gestion.sizePantalla);
		Gestion.ventanaJuego.add(this);
		Gestion.ventanaJuego.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Gestion.ventanaJuego.setUndecorated(true);
		Gestion.ventanaJuego.setVisible(true);
		
		Font defaultFont = nuevaOnline.getFont();
		System.out.println(defaultFont);
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon iconoFondo = new ImageIcon(getClass().getResource("FondoInicio.jpg"));
        Image imagenFondo = iconoFondo.getImage();
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
    }
	
	public static void main(String[] args) throws FileNotFoundException, JavaLayerException {
		VentanaInicio ventana = new VentanaInicio();
	}
	public void sonar() {
		try {
			player = new Player (new FileInputStream ("src/Clases/musicaproyecto.mp3"));
			player.play ();
			player.close ();
			sonar();
		} catch (JavaLayerException | FileNotFoundException e) {
			e.printStackTrace();
		}			
	}
	public void eliminarPanel() {
		Gestion.ventanaJuego.remove(this);
        Gestion.ventanaJuego.repaint();
	}

}
