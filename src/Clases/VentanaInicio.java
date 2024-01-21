package Clases;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import BaseDeDatos.MainBD;
import javazoom.jl.decoder.JavaLayerException;


public class VentanaInicio extends JPanel{
	
	protected JPanel pnlCentral;
	protected JPanel pnlLocal;
	protected JButton nuevaOnline;
	protected JButton nuevaLocal;
	protected JButton cargarLocal;
	protected JButton opciones;
	protected JButton cerrar;
	VentanaAjustes v;
	
	
	
	private static MainBD bd;
	
	public VentanaInicio() throws FileNotFoundException, JavaLayerException {
		
		Gestion.datosPartida = new Contenedor();
		
		pnlCentral = new JPanel(new GridLayout(3,1));
		
		pnlLocal = new JPanel(new GridLayout(1,2));
		
	
		nuevaLocal = new JButton("<html><div style='text-align: center;'>NUEVA PARTIDA<br>LOCAL</div></html>");
		nuevaLocal.setHorizontalAlignment(SwingConstants.CENTER);
		nuevaLocal.setVerticalAlignment(SwingConstants.CENTER);
		pnlLocal  .add(nuevaLocal);
		
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
		        new VentanaNJugadores(bd);
		        eliminarPanel();
			}
		});
		
		
		cargarLocal.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String nombreArchivo = JOptionPane.showInputDialog("Por favor, introduce el nombre del archivo:");
		        if (nombreArchivo != null) {
		            try {
		                FileInputStream fileIn = new FileInputStream(nombreArchivo + ".dat");
		                ObjectInputStream in = new ObjectInputStream(fileIn);
		                Gestion.numTurno = in.readInt();
		                Gestion.jugadores = (ArrayList<Jugador>) in.readObject();
		                Gestion.acusacion = (ArrayList<Asesinato>) in.readObject();
		                Gestion.cartasEnsenyadas = (HashMap<Asesinato, Jugador>) in.readObject();
		                //Gestion.acusacion  = (Contenedor) in.readObject();
		                //Gestion.siguientePanel = (JPanel) in.readObject();
		                in.close();
		                fileIn.close();
		                System.out.printf("Los datos se han cargado desde %s.dat", nombreArchivo);
		            } catch (IOException i) {
		                i.printStackTrace();
		            } catch (ClassNotFoundException c) {
		                System.out.println("No se encontró la clase.");
		                c.printStackTrace();
		            }
		        } else {
		            System.out.println("No se introdujo ningún nombre de archivo.");
		        }
		    }
		});
		
		cerrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Gestion.ventanaJuego.dispose();
				Gestion.tMusica.interrupt();
			}
		});
		
		opciones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						for (Component component : pnlCentral.getComponents()) {
						    if (component instanceof JButton) {
						        component.setEnabled(false);
						    }
						}
						for (Component component : pnlLocal.getComponents()) {
						    if (component instanceof JButton) {
						        component.setEnabled(false);
						    }
						}
						VentanaAjustes v =new VentanaAjustes();
						Gestion.ventanaJuego.revalidate();
						Gestion.ventanaJuego.repaint();
						Gestion.dPane.revalidate();
						Gestion.dPane.repaint();
						v.revalidate();
						v.repaint();
						String lockAjustesCerrado = "AjustesCerrado";
						synchronized (lockAjustesCerrado) {
							try {
								lockAjustesCerrado.wait();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
						for (Component component : pnlCentral.getComponents()) {
						    if (component instanceof JButton) {
						        component.setEnabled(true);
						    }
						}
						for (Component component : pnlLocal.getComponents()) {
						    if (component instanceof JButton) {
						        component.setEnabled(true);
						    }
						}
					}
				});
				t.start();	
			}
		});
		
		
		Gestion.ventanaJuego = new JFrame();
		//Cargar aquí la BD:
		bd = new MainBD();
		bd.iniciarBD();
		
		
		Gestion.ventanaJuego.addWindowListener (new WindowAdapter() {
			public void windowOpened (WindowEvent e) {
				Gestion.tMusica = new Thread(new Runnable() {
					@Override
						public void run() {
							Gestion.sonar();
								
						}
					});
		    		Gestion.tMusica.start();
			}
	});
		
	
	Gestion.ventanaJuego.addWindowListener (new WindowAdapter() {
		public void windowClosed (WindowEvent e) {
			Gestion.player.close();
			Gestion.dejarDeSonar.set(true);
		   	}
	});

		Gestion.ventanaJuego.setLayout(null);
		Gestion.ventanaJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Gestion.ventanaJuego.setSize(Gestion.sizePantalla);
		this.setSize(Gestion.sizePantalla);
		Gestion.dPane= new JDesktopPane();
		Gestion.dPane.setOpaque(false);
		Gestion.dPane.setBounds(0,0, (int)Gestion.sizePantalla.getWidth(),(int)Gestion.sizePantalla.getHeight());
		Gestion.ventanaJuego.add(Gestion.dPane);
		Gestion.ventanaJuego.add(this);
		Gestion.ventanaJuego.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Gestion.ventanaJuego.setUndecorated(true);
		
		Gestion.ventanaJuego.setVisible(true);
		
		Gestion.vInicio=this;
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
	
	public void eliminarPanel() {
		Gestion.ventanaJuego.remove(this);
        Gestion.ventanaJuego.repaint();
	}
	public void deshabilitarBotones() {
		
	}
}