package Clases;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;



public class VentanaAcusacion extends JPanel{
	
	//Atributos:
	private JPanel pnlCombo;
	private JPanel pnlLabels;
	private JPanel pnlFotos;
	
	private JComboBox<Sospechosos> cbSospechoso;
	private JComboBox<Armas> cbArma;
	private JComboBox<Sitio> cbLugar;
	
	//Para los listeners:
	private Sospechosos sospechosoSel;
	private Armas armaSel;
	private Sitio sitioSel; //Para cuado esté en el centro para la acusación final 
	private JLabel lblPorHabitacion;
	
	private JLabel lblSospechoso;
	private JLabel lblArma;
	private JLabel lblLugar;
	
	Sospechoso sospechosoElegido;
	Arma armaElegida;
	Lugar lugarElegido;
	
	private JPanel pnlFotoSospechoso;
	private JPanel pnlFotoArma;
	private JPanel pnlFotoLugar;
	int anchoCarta;
	int altoCarta = (int)Gestion.sizePantalla.getHeight()/3;
	private boolean activarSiAcusacionFinal = false;
	
	//private HashMap<Point, HashMap<JPanel, ArrayList<JRadioButton>>> mapaCordBotones = new HashMap<Point, HashMap<JPanel, ArrayList<JRadioButton>>>();
	/**
	 * Para que solo se pueda seleccinar una opción en la JTable, guardo la fila
	 * y la columna en un HashMap para así poder controlarlo
	 * Clave: Fila, Valor: Columna
	 */
	int filaEnTabla;
	int columnaEnTabla;
	private HashMap<Integer, Integer> rowYcolYaSel = new HashMap<Integer, Integer>();
	

	public VentanaAcusacion() {
		
		setSize(Gestion.sizePantalla);
		setLayout(null);
		anchoCarta = altoCarta*Gestion.datosPartida.armas.get(0).getFoto().getIconWidth()/Gestion.datosPartida.armas.get(0).getFoto().getIconHeight();
		Border border = BorderFactory.createLineBorder(Color.BLACK, 5, true);
		
		pnlLabels= new JPanel(new GridLayout(3,1));
		pnlFotos= new JPanel(new GridLayout(3,1));
		
		Font font = new Font("Times New Roman", Font.BOLD, 25);
		lblSospechoso = new JLabel("Sospechoso", SwingConstants.CENTER);
		lblSospechoso.setOpaque(false);
		lblSospechoso.setFont(font);
		pnlLabels.add(lblSospechoso);
		pnlFotoSospechoso = new JPanel();
		pnlFotoSospechoso.setBorder(border);
		pnlFotos.add(pnlFotoSospechoso);
		lblArma = new JLabel("Arma", SwingConstants.CENTER);
		lblArma.setFont(font);
		lblArma.setOpaque(false);
		pnlLabels.add(lblArma);
		pnlFotoArma = new JPanel();
		pnlFotoArma.setBorder(border);
		pnlFotos.add(pnlFotoArma);
		lblLugar = new JLabel("Lugar", SwingConstants.CENTER);
		lblLugar.setFont(font);
		lblLugar.setOpaque(false);
		pnlLabels.add(lblLugar);
		pnlFotoLugar = new JPanel();
		pnlFotoLugar.setBorder(border);
		pnlFotos.add(pnlFotoLugar);
       
		pnlCombo = new JPanel(new GridLayout(3,1));
		
		cbSospechoso = new JComboBox<Sospechosos>();
		for (Sospechosos personaje: Sospechosos.values()) {
			//System.out.println(sospechoso);
			cbSospechoso.addItem(personaje);
		}
		cbArma = new JComboBox<Armas>();
		for (Armas arma: Armas.values()) {
			cbArma.addItem(arma);
		}
		cbLugar = new JComboBox<Sitio>();
		for (Sitio sitio: Sitio.values()) {
			cbLugar.addItem(sitio);
		}
		lblPorHabitacion = new JLabel();
		
		//Listeners en combos para los labels:
		cbSospechoso.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sospechosoSel = (Sospechosos) cbSospechoso.getSelectedItem();
				//System.out.println(sospechosoSel);
			}
		});
		
		cbArma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				armaSel = (Armas) cbArma.getSelectedItem();
				
			}
		});
		
		cbLugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sitioSel = (Sitio) cbLugar.getSelectedItem();
				
			}
		});
		
		//Listeners a los combos:
		cbArma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Armas a = (Armas) cbArma.getSelectedItem();
				String arma = a.toString();
				JLabel lblSel = new JLabel();
		        ImageIcon imageIcon = new ImageIcon("src/cartasArmas/"+arma+".png");
		        Image image = imageIcon.getImage();
		        Image newimg = image.getScaledInstance(pnlFotoLugar.getWidth()-10, pnlFotoLugar.getHeight(),  java.awt.Image.SCALE_SMOOTH); // redimensiona la imagen
		        lblSel.setIcon(new ImageIcon(newimg));
		        pnlFotoArma.removeAll();
		        pnlFotoArma.add(lblSel,BorderLayout.CENTER);
		        Gestion.ventanaJuego.repaint();
		        Gestion.ventanaJuego.revalidate();
				
			}
		});
		
		cbSospechoso.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Sospechosos nom = (Sospechosos) cbSospechoso.getSelectedItem();
				String sospechoso = nom.toString();
				JLabel lblSel = new JLabel();
				ImageIcon imageIcon = new ImageIcon("src/cartasSospechosos/"+sospechoso+".png");
		        Image image = imageIcon.getImage();
		        Image newimg = image.getScaledInstance(pnlFotoLugar.getWidth()-10, pnlFotoLugar.getHeight(),  java.awt.Image.SCALE_SMOOTH); // redimensiona la imagen
		        lblSel.setIcon(new ImageIcon(newimg));
		        pnlFotoSospechoso.removeAll();
		        pnlFotoSospechoso.add(lblSel, BorderLayout.CENTER);
		        Gestion.ventanaJuego.repaint();
		        Gestion.ventanaJuego.revalidate();
				
			}
		});
		
		//Primero añadir estos combos
		pnlCombo.add(cbSospechoso);
		pnlCombo.add(cbArma);
		
		//Llamar al método para decidir que componente poner
		if (activarSiAcusacionFinal) {
			cbLugar = (JComboBox<Sitio>) devuelveComp();
			pnlCombo.add(cbLugar);
		} else {
			lblPorHabitacion = (JLabel) devuelveComp();
			pnlCombo.add(lblPorHabitacion);
		}
		pnlCombo.setBounds(anchoCarta/4, 0, (int)Gestion.sizePantalla.getWidth()/2-2*anchoCarta-200, (int)Gestion.sizePantalla.getHeight());
		this.add(pnlCombo);
		pnlLabels.setBounds((int)Gestion.sizePantalla.getWidth()/2-3*anchoCarta/2-200, 0, 200, (int)Gestion.sizePantalla.getHeight());
		this.add(pnlLabels);
		pnlFotos.setBounds((int)Gestion.sizePantalla.getWidth()/2-5*anchoCarta/4, 0, anchoCarta, (int)Gestion.sizePantalla.getHeight());
		this.add(pnlFotos);
		JPanel panelLista = new JPanel(null);
		panelLista.setBounds((int)Gestion.sizePantalla.getWidth()/2, 0, (int)(Gestion.sizePantalla.getWidth()/2) ,(int)Gestion.sizePantalla.getHeight()-150);
		panelLista.add(new Lista(panelLista, Gestion.getNumTurno()).sPane);
		this.add(panelLista);
		JButton btnAcusar= new JButton("Acusar");
		btnAcusar.setFont(new Font("Serif", Font.BOLD, 33));
		btnAcusar.setBounds((int)Gestion.sizePantalla.getWidth()/2, (int)Gestion.sizePantalla.getHeight()-150, (int)Gestion.sizePantalla.getWidth()/2, 150);
		btnAcusar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Gestion.acusacion = new ArrayList<>();
				System.out.println(cbSospechoso.getSelectedIndex());
				sospechosoElegido = Gestion.datosPartida.sospechosos.get(cbSospechoso.getSelectedIndex());
				Gestion.acusacion.add(sospechosoElegido);
				System.out.println(cbArma.getSelectedIndex());
				armaElegida = Gestion.datosPartida.armas.get(cbArma.getSelectedIndex());
				Gestion.acusacion.add(armaElegida);
				if(activarSiAcusacionFinal) {
					lugarElegido=Gestion.datosPartida.lugares.get(cbLugar.getSelectedIndex());
				}else {
					for (Lugar l:Gestion.datosPartida.lugares) {
						if (l.getNombre().equals(((Sitio)cbLugar.getSelectedItem()))) {
							lugarElegido = l;
						}
					}
					
				}
				Gestion.acusacion.add(lugarElegido);
				new VentanaDarCarta((Gestion.getNumTurno()+1)%Gestion.jugadores.size());
				eliminarPanel();
			}
		});
		
		this.add(btnAcusar);
		Gestion.ventanaJuego.add(this);
		this.revalidate();
		
	}
	
	private Component devuelveComp() {
		lblPorHabitacion.setText("");
		Sitio s;
		//Buscar la posición del jugador
		// Estas tres línes de abajo todavía no se pueden ejecutar pero tiene que hacerse así:
//		System.out.println(Gestion.jugadores.get(0));
		int[] posicionJugador = Gestion.jugadores.get(Gestion.getNumTurno()).posicion;
		int posibleHabitacion = Gestion.tablero.get(posicionJugador[0]).get(posicionJugador[1]);
		switch(posibleHabitacion) {
			case 2,3,4,5,6,7,8,9,10:
				s = Gestion.datosPartida.lugares.get(posibleHabitacion-2).getNombre();
				lblPorHabitacion.setText(s.toString());
				break;
		
			case 11:
				activarSiAcusacionFinal = true;
				return cbLugar; // Y luego le añado el listener
			default:
				String def = "<html>NO ESTÁS <br> EN NUNGUNA HABITACIÓN</html>"; //Para que en el JLabel se pueda hacer el salto de línea
				lblPorHabitacion.setText(def);
				break;
		}
		Border borde = BorderFactory.createLineBorder(Color.BLACK, 4);
		lblPorHabitacion.setBorder(borde);
		lblPorHabitacion.setFont(new Font("Times New Roman", Font.BOLD, 20));
		return lblPorHabitacion;
	}
	
	
	
	//Cargar mapa recursivamente:
//	public void cargarMapaRecursive(int row, int column) {
//		if (row == 22 && column == 1) { //Caso base
//			return;
//		}
//		if (column == 1) {
//			column = 0;
//			row ++;
//		} else  {
//			column = column + 1;
//		}
//		JPanel pnlRadio = new JPanel();
//		ArrayList<JRadioButton> radioButtons = new ArrayList<JRadioButton>();
//		JRadioButton seleccion1 = new JRadioButton("100%");
//		JRadioButton seleccion2 = new JRadioButton("Duda");
//		JRadioButton seleccion3 = new JRadioButton("0%");
//		pnlRadio.add(seleccion1);
//		pnlRadio.add(seleccion2);
//		pnlRadio.add(seleccion3);
//		radioButtons.add(seleccion1);
//		radioButtons.add(seleccion2);
//		radioButtons.add(seleccion3);
//		HashMap<JPanel, ArrayList<JRadioButton>> mapaRadios = new HashMap<JPanel, ArrayList<JRadioButton>>();
//		mapaRadios.put(pnlRadio, radioButtons);
//		
//		mapaCordBotones.put(new Point(row,column), mapaRadios);
//		System.out.println(row +", "+ column);
//		cargarMapaRecursive(row, column);
//		return;
//	}
	
	public void eliminarPanel() {
		Gestion.ventanaJuego.remove(this);
		Gestion.ventanaJuego.repaint();
	}
	public static void main(String[] args) {
		VentanaAcusacion vent = new VentanaAcusacion();
	}

}