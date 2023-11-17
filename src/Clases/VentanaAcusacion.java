package Clases;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
		
		tablaLista.setDefaultRenderer(Boolean.class, new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				table.setRowHeight(35);
				JPanel pnl = new JPanel();
//				pnl.add(new JRadioButton("100%"));
//				pnl.add(new JRadioButton("jsijs"));
				JRadioButton r1 = new JRadioButton("100%");
				JRadioButton r2 = new JRadioButton("Duda");
				JRadioButton r3 = new JRadioButton("0%");
				pnl.add(r1);
				pnl.add(r2);
				pnl.add(r3);
				return pnl;
			}
		});
		
		//SEGUIR AQUI------------------------------------------------------------------------------
//		tablaLista.setDefaultEditor(JPanel.class, new DefaultCellEditor(new JTextField() ) {
//
//			@Override
//			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
//					int column) {
//				return super.getTableCellEditorComponent(table, value, isSelected, row, column);
//				
//			}
//			
//		});
		
		getContentPane().add(new JScrollPane(tablaLista), BorderLayout.EAST);
	}
	
	
	public static void main(String[] args) {
		VentanaAcusacion vent = new VentanaAcusacion();
		vent.setVisible( true );
	}

}