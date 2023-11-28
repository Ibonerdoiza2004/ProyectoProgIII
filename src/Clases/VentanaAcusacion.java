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
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;



public class VentanaAcusacion extends JFrame{
	
	//Atributos:
	private JPanel pnlCombo;
	private JPanel pnlLabelYFotos;
	
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
	
	private JPanel pnlFotoSospechoso;
	private JPanel pnlFotoArma;
	private JPanel pnlFotoLugar;
	
	private JTable tablaLista;
	private TablaLista modeloTabla;
	
	//private HashMap<Point, HashMap<JPanel, ArrayList<JRadioButton>>> mapaCordBotones = new HashMap<Point, HashMap<JPanel, ArrayList<JRadioButton>>>();
	/**
	 * Para que solo se pueda seleccinar una opción en la JTable, guardo la fila
	 * y la columna en un HashMap para así poder controlarlo
	 * Clave: Fila, Valor: Columna
	 */
	int filaEnTabla;
	int columnaEnTabla;
	private HashMap<Integer, Integer> rowYcolYaSel = new HashMap<Integer, Integer>();
	
	public JTable getTablaLista() {
		return tablaLista;
	}

	public void setTablaLista(JTable tablaLista) {
		this.tablaLista = tablaLista;
	}

	public VentanaAcusacion() {
		
		setSize(new Dimension(640,480));
		setLocationRelativeTo( null );
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("VENTANA ACUSACION");
		
		Border border = BorderFactory.createLineBorder(Color.BLUE, 5, true);
		
		pnlLabelYFotos = new JPanel(new GridLayout(3,2));
		
		lblSospechoso = new JLabel("Sospechoso");
		pnlLabelYFotos.add(lblSospechoso);
		pnlFotoSospechoso = new JPanel();
		pnlFotoSospechoso.setBorder(border);
		pnlLabelYFotos.add(pnlFotoSospechoso);
		lblArma = new JLabel("Arma");
		pnlLabelYFotos.add(lblArma);
		pnlFotoArma = new JPanel();
		pnlFotoArma.setBorder(border);
		pnlLabelYFotos.add(pnlFotoArma);
		lblLugar = new JLabel("Lugar");
		pnlLabelYFotos.add(lblLugar);
		pnlFotoLugar = new JPanel();
		pnlFotoLugar.setBorder(border);
		pnlLabelYFotos.add(pnlFotoLugar);
       
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
		//cargarMapaRecursive(1, -1);
		
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
		        repaint();
		        revalidate();
				
			}
		});
		
		getContentPane().add(pnlLabelYFotos, BorderLayout.CENTER);
		//getContentPane().add(new JScrollPane(jlSospechoso), BorderLayout.EAST);
		
		
		modeloTabla = new TablaLista();
		tablaLista = new JTable(modeloTabla);
		//tablaLista.setRowHeight(200);
		
		/**
		 * Listener para coger las coordenadas en la tabla
		 * y poder marcar tu elección
		 */
		tablaLista.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				filaEnTabla = tablaLista.rowAtPoint(e.getPoint());
				columnaEnTabla = tablaLista.columnAtPoint(e.getPoint());
				if (filaEnTabla >= 0 && columnaEnTabla >= 1) {
					//filasYaSeleccionadas.add(filaEnTabla);
					tablaLista.repaint();
				} else  {
					filaEnTabla = 0;
					columnaEnTabla = 0;
				}
				
			}
		});
		
		tablaLista.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		    private JLabel lblSel = new JLabel();

		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
		            boolean hasFocus, int row, int column) {
		        table.setRowHeight(35);
		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        if (column == 0) {
		            return c;
		        }
		        //Mirar si ya había algún sospechoso marcado
		        for (Integer r: rowYcolYaSel.keySet()) {
		        	if (r == row && rowYcolYaSel.get(r) == column) {
		        		lblSel = lblTachado();
		                return lblSel;
		        	}
		        }
		        
		        if (filaEnTabla >= 0 && filaEnTabla == row && column == columnaEnTabla) {
		            try {
		            	lblSel = lblTachado();
		                rowYcolYaSel.put(filaEnTabla, columnaEnTabla);
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		            return lblSel;
		        }
		        return c;
		    }
		});
		
//		tablaLista.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//
//			@Override
//			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
//					boolean hasFocus, int row, int column) {
//				table.setRowHeight(35);
//				return super.
//			}
//		});
		
//		tablaLista.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField() ) {
//			@Override
//			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
//					int column) {
//			}
//			
//			//Para obtener el valor al editar en la tabla
//			@Override
//			public Object getCellEditorValue() {
//				
//			}
//			
//		});
		
		getContentPane().add(new JScrollPane(tablaLista), BorderLayout.EAST);
		
	}
	
	public JLabel lblTachado() {
		JLabel lblSel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("tachado.png"));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(tablaLista.getColumnModel().getColumn(0).getWidth(), 35,  java.awt.Image.SCALE_SMOOTH); // redimensiona la imagen
        lblSel.setIcon(new ImageIcon(newimg));
        return lblSel;
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
	
	
	public static void main(String[] args) {
		VentanaAcusacion vent = new VentanaAcusacion();
		vent.setVisible( true );
	}

}
