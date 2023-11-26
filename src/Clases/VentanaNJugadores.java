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
	protected JButton continuar;

	public VentanaNJugadores() {
		
		
		lblTots = new JLabel("Nº Jugadores Totales");
		Font totFont = lblTots.getFont();
		lblTots.setFont(new Font(totFont.getName(), totFont.getStyle(), 20));
		lblTots.setForeground(Color.WHITE);
		lblTots.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblJugs = new JLabel("Nº Jugadores Reales");
		lblJugs.setFont(new Font(totFont.getName(), totFont.getStyle(), 20));
		lblJugs.setForeground(Color.WHITE);
		lblJugs.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblBots = new JLabel("Nº de IAs");
		lblBots.setFont(new Font(totFont.getName(), totFont.getStyle(), 20));
		lblBots.setForeground(Color.WHITE);
		lblBots.setHorizontalAlignment(SwingConstants.CENTER);
		
		spnTots = new JSpinner(new SpinnerNumberModel(3, 3, 6, 1));
		JComponent cmpTots = spnTots.getEditor();
		JFormattedTextField ftfTots = ((JSpinner.DefaultEditor) cmpTots).getTextField();
		ftfTots.setFont(new Font(totFont.getName(), totFont.getStyle(), 50));
		ftfTots.setForeground(Color.WHITE);
		ftfTots.setHorizontalAlignment(SwingConstants.CENTER);
		ftfTots.setEditable(false);
		spnTots.setOpaque(false);
		spnTots.setBorder(null);
		cmpTots.setOpaque(false);
		cmpTots.setBorder(null);
		ftfTots.setOpaque(false);
		ftfTots.setBorder(null);
		
		
		spnJugs = new JSpinner(new SpinnerNumberModel(3, 3, 3, 1));
		JComponent cmpJugs = spnJugs.getEditor();
		JFormattedTextField ftfJugs = ((JSpinner.DefaultEditor) cmpJugs).getTextField();
		ftfJugs.setFont(new Font(totFont.getName(), totFont.getStyle(), 50));
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
		nBots.setFont(new Font(totFont.getName(), totFont.getStyle(), 50));
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
		
		JPanel pnlVentana = new JPanel(new GridLayout(3,1)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon iconoFondo = new ImageIcon(getClass().getResource("FondoInicioDifuminado.jpg"));
                Image imagenFondo = iconoFondo.getImage();
                g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        JLabel lblSel = new JLabel("SELECCIONA EL NUMERO DE JUGADORES");
		Font selFont = lblSel.getFont();
		lblSel.setFont(new Font(selFont.getName(), selFont.BOLD, 35));
		lblSel.setHorizontalAlignment(SwingConstants.CENTER);
		lblSel.setForeground(Color.WHITE);
		pnlVentana.add(lblSel);
		pnlVentana.add(pnlPaneles);
        
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
		pnlVentana.add(pnlSur);
		
		this.add(pnlVentana);
	
		this.setSize(400,400);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
		this.setUndecorated(true);
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
