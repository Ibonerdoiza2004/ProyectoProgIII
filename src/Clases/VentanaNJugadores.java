package Clases;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VentanaNJugadores extends JFrame {
	
	protected JLabel lblTots;
	protected JLabel lblJugs;
	protected JLabel lblBots;
	protected JSpinner spnTots;
	protected JSpinner spnJugs;
	protected JLabel nBots;

	public VentanaNJugadores() {
		this.setLayout(new GridLayout(3,1));
		
		lblTots = new JLabel("Nº Jugadores Totales");
		lblTots.setHorizontalAlignment(SwingConstants.CENTER);
		lblJugs = new JLabel("Nº Jugadores Reales");
		lblJugs.setHorizontalAlignment(SwingConstants.CENTER);
		lblBots = new JLabel("Nº de IAs");
		lblBots.setHorizontalAlignment(SwingConstants.CENTER);
		
		spnTots = new JSpinner(new SpinnerNumberModel(3, 3, 6, 1));
		spnJugs = new JSpinner(new SpinnerNumberModel(3, 3, 3, 1));
		
		nBots = new JLabel("0");
		nBots.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		
		JPanel pnlTots = new JPanel(new GridLayout(2,1));
		pnlTots.add(lblTots);
		pnlTots.add(spnTots);
		
		JPanel pnlJugs = new JPanel(new GridLayout(2,1));
		pnlJugs.add(lblJugs);
		pnlJugs.add(spnJugs);
		
		JPanel pnlBots = new JPanel(new GridLayout(2,1));
		pnlBots.add(lblBots);
		pnlBots.add(nBots);
		
		JPanel pnlPaneles = new JPanel(new GridLayout(1,3));
		pnlPaneles.add(pnlTots);
		pnlPaneles.add(pnlJugs);
		pnlPaneles.add(pnlBots);
		
		JLabel lblSel = new JLabel("SELECCIONA EL NUMERO DE JUGADORES");
		lblSel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblSel);
		this.add(pnlPaneles);
		this.add(new JLabel(" "));
	
		this.setSize(400,400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
		this.setVisible(true);
		
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
				
				Number valor1 = (Number) spnTots.getValue();
				int valorInt1 = valor1.intValue();
				Number valor2 = (Number) spnJugs.getValue();
				int valorInt2 = valor2.intValue();

				int resta = valorInt1 - valorInt2;

				nBots.setText(resta+"");
			}
		});
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VentanaNJugadores v = new VentanaNJugadores();

	}

}
