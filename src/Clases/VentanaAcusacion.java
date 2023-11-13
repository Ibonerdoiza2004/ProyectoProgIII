package Clases;

import java.awt.Checkbox;
import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class VentanaAcusacion extends JFrame{
	
	//Atributos:
	private JPanel pnlCombo;
	
	private JComboBox<Sospechoso> cbSospechoso;
	private JComboBox<Arma> cbArma;
	private JComboBox<Lugar> cbLugar;
	
	private JLabel lblSospechoso;
	private JLabel lblArma;
	private JLabel lblLugar;
	
	private JList<JCheckBox> jlSospechoso;
	private JList<JCheckBox> jlArma;
	private JList<JCheckBox> jlLugar;
	
	private JCheckBox chSospechoso;
	private JCheckBox chArma;
	private JCheckBox chLugar;
	
	public VentanaAcusacion() {
		
		//No necesito ni size ni location
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("VENTANA ACUSACION");
		
		pnlCombo = new JPanel();
		cbSospechoso = new JComboBox<Sospechoso>();
		//for 
	}
	

}
