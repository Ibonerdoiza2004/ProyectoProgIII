package Clases;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import BaseDeDatos.MainBD;

public class VentanaNJugadores extends JPanel {
	
	protected JLabel lblTots;
	protected JLabel lblJugs;
	protected JLabel lblBots;
	protected JSpinner spnTots;
	protected JSpinner spnJugs;
	protected JLabel nBots;
	protected JButton continuar;
	
	private static MainBD Bd;
	private static VentanaRegistrarUsuario vr;
	private BotonRedondo btnRegistro;
	
	protected static int numVecesRegistrado = 0;
	private int contador = 0;

	public VentanaNJugadores(MainBD bd) {
		
		this.Bd = bd;
		vr = new VentanaRegistrarUsuario(VentanaNJugadores.this, this.Bd);
		
		Gestion.jugadores.clear();
		lblTots = new JLabel("Nº Jugadores Totales");
		Font totFont = lblTots.getFont();
		lblTots.setFont(new Font(totFont.getName(), totFont.getStyle(), 30));
		lblTots.setForeground(Color.WHITE);
		lblTots.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblJugs = new JLabel("Nº Jugadores Reales");
		lblJugs.setFont(new Font(totFont.getName(), totFont.getStyle(), 30));
		lblJugs.setForeground(Color.WHITE);
		lblJugs.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblBots = new JLabel("Nº de IAs");
		lblBots.setFont(new Font(totFont.getName(), totFont.getStyle(), 30));
		lblBots.setForeground(Color.WHITE);
		lblBots.setHorizontalAlignment(SwingConstants.CENTER);
		
		spnTots = new JSpinner(new SpinnerNumberModel(3, 3, 6, 1));
		JComponent cmpTots = spnTots.getEditor();
		
		JFormattedTextField ftfTots = ((JSpinner.DefaultEditor) cmpTots).getTextField();
		ftfTots.setFont(new Font(totFont.getName(), totFont.getStyle(), 60));
		ftfTots.setForeground(Color.WHITE);
		ftfTots.setHorizontalAlignment(SwingConstants.CENTER);
		ftfTots.setEditable(false);
		spnTots.setOpaque(false);
		spnTots.setBorder(null);
		cmpTots.setOpaque(false);
		cmpTots.setBorder(null);
		ftfTots.setOpaque(false);
		ftfTots.setBorder(null);

		
		spnJugs = new JSpinner(new SpinnerNumberModel(3, 1, 3, 1));
		JComponent cmpJugs = spnJugs.getEditor();
		JFormattedTextField ftfJugs = ((JSpinner.DefaultEditor) cmpJugs).getTextField();
		ftfJugs.setFont(new Font(totFont.getName(), totFont.getStyle(), 60));
		ftfJugs.setForeground(Color.WHITE);
		ftfJugs.setHorizontalAlignment(SwingConstants.CENTER);
		ftfJugs.setEditable(false);
		ftfJugs.setEditable(false);
		spnJugs.setOpaque(false);
		spnJugs.setBorder(null);
		cmpJugs.setOpaque(false);
		cmpJugs.setBorder(null);
		ftfJugs.setOpaque(false);
		ftfJugs.setBorder(null);
		
		nBots = new JLabel("0");
		nBots.setFont(new Font(totFont.getName(), totFont.getStyle(), 60));
		nBots.setForeground(Color.WHITE);
		nBots.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel pnlTots = new JPanel(new GridLayout(2,1));
		pnlTots.setOpaque(false);
		pnlTots.add(lblTots);
		pnlTots.add(spnTots);
		
		JPanel pnlJugs = new JPanel(new GridLayout(2,1));
		pnlJugs.setOpaque(false);
		pnlJugs.add(lblJugs);
		pnlJugs.add(spnJugs);
		
		JPanel pnlBots = new JPanel(new GridLayout(2,1));
		pnlBots.setOpaque(false);
		pnlBots.add(lblBots);
		pnlBots.add(nBots);
		
		JPanel pnlPaneles = new JPanel(new GridLayout(1,3));
		pnlPaneles.setOpaque(false);
		pnlPaneles.add(pnlTots);
		pnlPaneles.add(pnlJugs);
		pnlPaneles.add(pnlBots);
		
		this.setLayout(new GridLayout(3,1));
        
        JLabel lblSel = new JLabel("SELECCIONA EL NUMERO DE JUGADORES");
		Font selFont = lblSel.getFont();
		lblSel.setFont(new Font(selFont.getName(), selFont.BOLD, 45));
		lblSel.setHorizontalAlignment(SwingConstants.CENTER);
		lblSel.setForeground(Color.WHITE);

		
		JPanel pnlN = new JPanel(new GridLayout(2, 1));
		JPanel pnlArriba = new JPanel(new GridLayout(2, 1));
		pnlArriba.setOpaque(false);
		pnlArriba.add(new JLabel(""));

		JPanel pnlRegistro = new JPanel(new GridLayout(1, 2));
		pnlRegistro.setOpaque(false);
		btnRegistro = new BotonRedondo("Registrar");

		// Ajustar el tamaño del botón
		Dimension btnSize = new Dimension(150, 50);
		btnRegistro.setPreferredSize(btnSize);

		ImageIcon iconoLogo = new ImageIcon(BotonRedondo.class.getResource("Registro.png"));
		Image img = iconoLogo.getImage();
		Image newImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH); 
		iconoLogo = new ImageIcon(newImg);
		btnRegistro.getLblLogo().setIcon(iconoLogo);
		
		
		//Listener:
		btnRegistro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (numVecesRegistrado >= ((int) spnJugs.getValue())-1) { //Después del último registro no se puede volver a pulsar el botón
					btnRegistro.setEnabled(false);
				}
				//Meter todo esto en un if (para el setEnabled)
				if (vr != null) {
					vr.getTfUsuario().setText("");
					vr.getTfContrasenya().setText("");
					vr.setVisible(true);
				} else {
					vr = new VentanaRegistrarUsuario(VentanaNJugadores.this, Bd);
					vr.setVisible(true);
				}
			}
		});
		
		//Añadir labels para ajustar las coordenadas
		pnlRegistro.add(new JLabel("")); pnlRegistro.add(new JLabel("")); pnlRegistro.add(new JLabel(""));
		pnlRegistro.add(btnRegistro);
		pnlRegistro.add(btnRegistro.getLblLogo());
		pnlRegistro.add(new JLabel("")); pnlRegistro.add(new JLabel(""));
		pnlArriba.add(pnlRegistro);

		pnlN.add(pnlArriba);
		pnlN.setOpaque(false);
		pnlN.add(lblSel);
		this.add(pnlN);
		this.add(pnlPaneles);
        
        JPanel pnlSur = new JPanel(new GridLayout(1,3));
        pnlSur.setOpaque(false);
		pnlSur.add(new JLabel(" "));
		
		JPanel pnlCentroSur = new JPanel(new GridLayout(3,1));
		pnlCentroSur.setOpaque(false);
		continuar = new JButton("CONTINUAR");
		pnlCentroSur.add(new JLabel(" "));
		pnlCentroSur.add(continuar);
		pnlCentroSur.add(new JLabel(" "));
		
		pnlSur.add(pnlCentroSur);
		pnlSur.add(new JLabel(" "));
		this.add(pnlSur);
		
		
		setSize(Gestion.sizePantalla);
		Gestion.ventanaJuego.add(this);
		revalidate();
		
		spnTots.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				SpinnerNumberModel modelo2 = (SpinnerNumberModel) spnJugs.getModel();
				Number valor1 = (Number) spnTots.getValue();
				int valorInt1 = valor1.intValue();
		        modelo2.setMaximum(valorInt1);
		        
		        Number valor2 = (Number) spnJugs.getValue();
				int valorInt2 = valor2.intValue();
		        if (valorInt1 < valorInt2) {
		        	modelo2.setValue(valor1);
		        }
			}
		});
		
		spnJugs.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				//Aquí, eliminar los los jugadores registrados para añadir los nuevos:
				btnRegistro.setEnabled(true);
				bd.eliminarJugadores();
				numVecesRegistrado = 0;
				
				Number valor1 = (Number) spnTots.getValue();
				int valorInt1 = valor1.intValue();
				Number valor2 = (Number) spnJugs.getValue();
				int valorInt2 = valor2.intValue();

				int resta = valorInt1 - valorInt2;

				nBots.setText(resta+"");
			}
		});
		 
		continuar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(numVecesRegistrado);
				if (numVecesRegistrado < (int) spnJugs.getValue()) { // numVecesRegistrado < (int) spnJugs.getValue()
					JOptionPane.showMessageDialog(null, "Todavía faltan " + 
				(((int) spnJugs.getValue()) - numVecesRegistrado) + " jugadores por registrarse", "Error en login/registro", JOptionPane.ERROR_MESSAGE );
					//bd.cogerJugs(VentanaNJugadores.this);
				}
				
				else {
					bd.cogerJugs(VentanaNJugadores.this); //HACERLO DESPÚES DE QUE TODOS SE REGISTREN
					
					Number valor1 = (Number) spnTots.getValue();
					int valorInt1 = valor1.intValue();
					Number valor2 = (Number) spnJugs.getValue();
					int valorInt2 = valor2.intValue();
					int resta = valorInt1 - valorInt2;
					
					int i=0;
					while (i<valorInt2 & valorInt1!=0) {
						if (i==0) {
							i++;
							Jugador j1 = new Jugador(new Personaje(), false);
							Gestion.jugadores.add(j1);
							valorInt1--;
						} else if (i==1) {
							i++;
							Jugador j2 = new Jugador(new Personaje(), false);
							Gestion.jugadores.add(j2);
							valorInt1--;
						} else if (i==2) {
							i++;
							Jugador j3 = new Jugador(new Personaje(), false);
							Gestion.jugadores.add(j3);
							valorInt1--;
						} else if (i==3) {
							i++;
							Jugador j4 = new Jugador(new Personaje(), false);
							Gestion.jugadores.add(j4);
							valorInt1--;
						} else if (i==4) {
							i++;
							Jugador j5 = new Jugador(new Personaje(), false);
							Gestion.jugadores.add(j5);
							valorInt1--;
						} else if (i==5) {
							i++;
							Jugador j6 = new Jugador(new Personaje(), false);
							Gestion.jugadores.add(j6);
							valorInt1--;
						}
					
					}
					
					while (valorInt1!=0) {
						if (resta==5) {
							resta--;
							Jugador j2 = new Jugador(new Personaje(), true);
							Gestion.jugadores.add(j2);
							valorInt1--;
						}else if (resta==4) {
							resta--;
							Jugador j3 = new Jugador(new Personaje(), true);
							Gestion.jugadores.add(j3);
							valorInt1--;
						}else if (resta==3) {
							resta--;
							Jugador j4 = new Jugador(new Personaje(), true);
							Gestion.jugadores.add(j4);
							valorInt1--;
						} else if (resta==2) {
							resta--;
							Jugador j5 = new Jugador(new Personaje(), true);
							Gestion.jugadores.add(j5);
							valorInt1--;	
						} else if (resta==1) {
							Jugador j6 = new Jugador(new Personaje(), true);
							Gestion.jugadores.add(j6);
							valorInt1--;
						}
					}
					if (valorInt1 == 0) {
						new VentanaSeleccionPersonaje(Bd);
						eliminarPanel();
					}
				}
				
			}
		});;
	}
	
	public void eliminarPanel() {
		Gestion.ventanaJuego.remove(this);
        Gestion.ventanaJuego.repaint();
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon iconoFondo = new ImageIcon(getClass().getResource("FondoInicioDifuminado.jpg"));
        Image imagenFondo = iconoFondo.getImage();
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
    }

	public JSpinner getSpnJugs() {
		return spnJugs;
	}

	
	public static void main(String[] args) { //Únicamente para probar
		// TODO Auto-generated method stub
		Gestion.ventanaJuego= new JFrame();
		Gestion.ventanaJuego.setLayout(null);
		Gestion.ventanaJuego.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Gestion.ventanaJuego.setSize(Gestion.sizePantalla);
		Gestion.ventanaJuego.setUndecorated(true);
		Gestion.ventanaJuego.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Gestion.ventanaJuego.setVisible(true);
		VentanaNJugadores v = new VentanaNJugadores(new MainBD());

	}

}

