package Clases;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;



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
	
	private JList<SospechosoItem> jlSospechoso;
	private JList<ArmaItem> jlArma;
	private JList<JCheckBox> jlLugar;
	
	private JCheckBox chSospechoso;
	private JCheckBox chArma;
	private JCheckBox chLugar;
	
	public VentanaAcusacion() {
		
		setSize(new Dimension(640,480));
		setLocationRelativeTo( null );
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("VENTANA ACUSACION");
		
		pnlCombo = new JPanel();
		
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
		
//		JCheckBox[] nm = {new JCheckBox("Andoni"), new JCheckBox("Jenny"), new JCheckBox("Carlos"), 
//				new JCheckBox("Asier"), new JCheckBox("Nekane"), new JCheckBox("Iñaki")};
//		
//		jlSospechoso = new JList<JCheckBox>(nm);
//		jlSospechoso.add(new JCheckBox("sanddsndj"));
////		for (Sospechosos s: Sospechosos.values()) {
////			JCheckBox checkSos = new JCheckBox(s.name());
////			jlSospechoso.add(checkSos);
////		}
		
		JPanel pnlListas = new JPanel();
		
        DefaultListModel<SospechosoItem> listModel = new DefaultListModel<>();
        jlSospechoso = new JList<>(listModel);

        for (Sospechosos sospechoso : Sospechosos.values()) {
            listModel.addElement(new SospechosoItem(sospechoso, false));
        }

        jlSospechoso.setCellRenderer(new SospechosoRenderer());

        jlSospechoso.addListSelectionListener(e -> {
            int selectedIndex = jlSospechoso.getSelectedIndex();
            if (selectedIndex != -1) {
                SospechosoItem item = listModel.getElementAt(selectedIndex);
                item.setChecked(!item.isChecked());
                jlSospechoso.repaint();
                jlSospechoso.clearSelection();
            }
        });
		
        DefaultListModel<ArmaItem> modeloLista = new DefaultListModel<>();
        jlArma = new JList<>(modeloLista);

        for (Armas arma : Armas.values()) {
            modeloLista.addElement(new ArmaItem(arma, false));
        }

        jlArma.setCellRenderer(new ArmaRenderer());

        jlArma.addListSelectionListener(e -> {
            int selectedIndex = jlArma.getSelectedIndex();
            if (selectedIndex != -1) {
                ArmaItem item = modeloLista.getElementAt(selectedIndex);
                item.setChecked(!item.isChecked());
                jlArma.repaint();
                jlArma.clearSelection();
            }
        });
        
        
		
        pnlListas.add(jlSospechoso);
        pnlListas.add(jlArma);
        
        getContentPane().add(pnlListas, BorderLayout.EAST);
        
        
        
        
		//getContentPane().add(new JScrollPane(jlSospechoso), BorderLayout.EAST);
		
		
	}
	
	
	
	//Clases internas no anónimas para los JCheckBox
	
    private static class SospechosoItem {
        private Sospechosos sospechoso;
        private boolean isChecked;

        public SospechosoItem(Sospechosos sospechoso, boolean isChecked) {
            this.sospechoso = sospechoso;
            this.isChecked = isChecked;
        }

        public Sospechosos getSospechoso() {
            return sospechoso;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        @Override
        public String toString() {
            return sospechoso.name();
        }
    }
    
    
    private static class ArmaItem {
        private Armas arma;
        private boolean isChecked;

        public ArmaItem(Armas arma, boolean isChecked) {
            this.arma = arma;
            this.isChecked = isChecked;
        }

        public Armas getSospechoso() {
            return arma;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        @Override
        public String toString() {
            return arma.name();
        }
    }
    
    
    private static class SitioItem {
        private Sitio sitio;
        private boolean isChecked;

        public SitioItem(Sitio sitio, boolean isChecked) {
            this.sitio = sitio;
            this.isChecked = isChecked;
        }

        public Sitio getSospechoso() {
            return sitio;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        @Override
        public String toString() {
            return sitio.name();
        }
    }
	
	
	   private static class SospechosoRenderer extends JCheckBox implements ListCellRenderer<SospechosoItem> {
	        @Override
	        public Component getListCellRendererComponent(JList<? extends SospechosoItem> list, SospechosoItem value, int index,
	                                                      boolean isSelected, boolean cellHasFocus) {
	            setSelected(value.isChecked());
	            setText(value.toString());
	            setFont(list.getFont());
	            setBackground(list.getBackground());
	            setForeground(list.getForeground());
	            return this;
	        }
	    }
	   
	   private static class ArmaRenderer extends JCheckBox implements ListCellRenderer<ArmaItem> {
	        @Override
	        public Component getListCellRendererComponent(JList<? extends ArmaItem> list, ArmaItem value, int index,
	                                                      boolean isSelected, boolean cellHasFocus) {
	            setSelected(value.isChecked());
	            setText(value.toString());
	            setFont(list.getFont());
	            setBackground(list.getBackground());
	            setForeground(list.getForeground());
	            return this;
	        }
	    }
	   
//	   private static class ArmaRenderer extends JCheckBox implements ListCellRenderer<ArmaItem> {
//	        @Override
//	        public Component getListCellRendererComponent(JList<? extends ArmaItem> list, ArmaItem value, int index,
//	                                                      boolean isSelected, boolean cellHasFocus) {
//	            setSelected(value.isChecked());
//	            setText(value.toString());
//	            setFont(list.getFont());
//	            setBackground(list.getBackground());
//	            setForeground(list.getForeground());
//	            return this;
//	        }
//	    }
	
	
	public static void main(String[] args) {
		VentanaAcusacion vent = new VentanaAcusacion();
		vent.setVisible( true );
	}

}
