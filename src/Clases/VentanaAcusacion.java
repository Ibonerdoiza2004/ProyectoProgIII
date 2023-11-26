package Clases;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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
import javax.swing.table.DefaultTableCellRenderer;



public class VentanaAcusacion extends JFrame{
	
	//Atributos:
	private JPanel pnlCombo;
	private JPanel pnlLabel; 
	
	private JComboBox<Sospechosos> cbSospechoso;
	private JComboBox<Armas> cbArma;
	private JComboBox<Sitio> cbLugar;
	
	//Para los listeners:
	private Sospechosos sospechosoSel;
	private Armas armaSel;
	private Sitio sitioSel;
	
	private JLabel lblSospechoso;
	private JLabel lblArma;
	private JLabel lblLugar;
	
	private JTable tablaLista;
	private TablaLista modeloTabla;
	
	private HashMap<Point, HashMap<JPanel, ArrayList<JRadioButton>>> mapaCordBotones = new HashMap<Point, HashMap<JPanel, ArrayList<JRadioButton>>>();
//	private JList<SospechosoItem> jlSospechoso;
//	private JList<ArmaItem> jlArma;
//	private JList<JCheckBox> jlLugar;
//	
//	private JCheckBox chSospechoso;
//	private JCheckBox chArma;
//	private JCheckBox chLugar;
	
	public VentanaAcusacion() {
		
		setSize(new Dimension(640,480));
		setLocationRelativeTo( null );
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("VENTANA ACUSACION");
       
		pnlCombo = new JPanel(new GridLayout(3,1));
		
		cbSospechoso = new JComboBox<Sospechosos>();
		for (Sospechosos sospechoso: Sospechosos.values()) {
			//System.out.println(sospechoso);
			cbSospechoso.addItem(sospechoso);
		}
		cbArma = new JComboBox<Armas>();
		for (Armas arma: Armas.values()) {
			cbArma.addItem(arma);
		}
		cbLugar = new JComboBox<Sitio>();
		for (Sitio sitio: Sitio.values()) {
			cbLugar.addItem(sitio);
		}
		
		//Cargo el mapa de JRadioButtons para la JTable:
		cargarMapaRecursive(1, -1);
		
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
		
		pnlCombo.add(cbSospechoso);
		pnlCombo.add(cbArma);
		pnlCombo.add(cbLugar);
		
		getContentPane().add(pnlCombo, BorderLayout.WEST);
		
		
		pnlLabel = new JPanel(new GridLayout(3,1));
		
		lblSospechoso = new JLabel("Sospechoso");
		lblArma = new JLabel("Arma");
		lblLugar = new JLabel("Lugar");
		pnlLabel.add(lblSospechoso);
		pnlLabel.add(lblArma);
		pnlLabel.add(lblLugar);
		
		getContentPane().add(pnlLabel, BorderLayout.CENTER);
		//getContentPane().add(new JScrollPane(jlSospechoso), BorderLayout.EAST);
		
		modeloTabla = new TablaLista();
		tablaLista = new JTable(modeloTabla);
		//tablaLista.setRowHeight(200);
		
		tablaLista.setDefaultRenderer(JPanel.class, new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				table.setRowHeight(35);

				for (Point p: mapaCordBotones.keySet()) {
					for (JPanel key: mapaCordBotones.get(p).keySet()) {
						return key;
					}
				}
				return null;
			}
		});
		
		tablaLista.setDefaultEditor(JPanel.class, new DefaultCellEditor(new JTextField() ) {

			boolean lanzado;
			JRadioButton seleccion1;
			JRadioButton seleccion2;
			JRadioButton seleccion3;
			@Override
			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
					int column) {
				
				JPanel pnlRadio = new JPanel();
				JRadioButton seleccion1 = new JRadioButton("100%");
				JRadioButton seleccion2 = new JRadioButton("Duda");
				JRadioButton seleccion3 = new JRadioButton("0%");
				pnlRadio.add(seleccion1);
				pnlRadio.add(seleccion2);
				pnlRadio.add(seleccion3);
				if (column != 1) {
					lanzado = false;
					return super.getTableCellEditorComponent(table, value, isSelected, row, column);
				}
				for (Point p: mapaCordBotones.keySet()) {
					if (p.getX() == row && p.getY() == column) {
						for (JPanel pnl: mapaCordBotones.get(p).keySet()) {
							return pnl;
						}
					}
					//return null;
				}
				return null;
			}
			
			//Para obtener el valor al editar en la tabla
			@Override
			public Object getCellEditorValue() {
				for (Point p: mapaCordBotones.keySet()) {
					for (JPanel pnl: mapaCordBotones.get(p).keySet()) {
						for (JRadioButton rad: mapaCordBotones.get(p).get(pnl)) {
							if (rad.isSelected()) {
								System.out.println("TRUE");
								rad.setSelected( true );
								return true;
							}
						}
					}
				}
				return false;
			}
			
		});
		
		getContentPane().add(new JScrollPane(tablaLista), BorderLayout.EAST);
		
		//Probar con KeyListener:
		tablaLista.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
	}
	
	
	
	//Cargar mapa recursivamente:
	public void cargarMapaRecursive(int row, int column) {
		if (row == 22 && column == 1) { //Caso base
			return;
		}
		if (column == 1) {
			column = 0;
			row ++;
		} else  {
			column = column + 1;
		}
		JPanel pnlRadio = new JPanel();
		ArrayList<JRadioButton> radioButtons = new ArrayList<JRadioButton>();
		JRadioButton seleccion1 = new JRadioButton("100%");
		JRadioButton seleccion2 = new JRadioButton("Duda");
		JRadioButton seleccion3 = new JRadioButton("0%");
		pnlRadio.add(seleccion1);
		pnlRadio.add(seleccion2);
		pnlRadio.add(seleccion3);
		radioButtons.add(seleccion1);
		radioButtons.add(seleccion2);
		radioButtons.add(seleccion3);
		HashMap<JPanel, ArrayList<JRadioButton>> mapaRadios = new HashMap<JPanel, ArrayList<JRadioButton>>();
		mapaRadios.put(pnlRadio, radioButtons);
		
		mapaCordBotones.put(new Point(row,column), mapaRadios);
		System.out.println(row +", "+ column);
		cargarMapaRecursive(row, column);
		return;
	}
	
	
	public static void main(String[] args) {
		VentanaAcusacion vent = new VentanaAcusacion();
		vent.setVisible( true );
	}

}
