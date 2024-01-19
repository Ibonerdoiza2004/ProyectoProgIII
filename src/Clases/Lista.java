package Clases;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

public class Lista extends JPanel{
	int filaEnTabla;
	int columnaEnTabla;
	private HashMap<Integer, Integer> rowYcolYaSel;
	
	TablaLista tbLista = null; //Esto es el modelo 
	JScrollPane sPane;
	JTable tb = new JTable();
	Image newimg;
	public TablaLista getTbLista() {
		return tbLista;
	}

	public void setTbLista(TablaLista tbLista) {
		this.tbLista = tbLista;
	}

	public Lista(JPanel panel, int numJugador) {
		rowYcolYaSel = new HashMap<Integer, Integer>();
		for (Asesinato carta:Gestion.jugadores.get(numJugador).lista.keySet()) {
			for(Boolean bool : Gestion.jugadores.get(numJugador).lista.get(carta)) {
				if(bool) {
					rowYcolYaSel.put(Gestion.datosPartida.todasLasCartas.indexOf(carta),  Gestion.jugadores.get(numJugador).lista.get(carta).indexOf(bool));
					break;
				}
			}
		}
		tbLista = new TablaLista();
		tb = new JTable(tbLista);
		sPane= new JScrollPane(tb);
		tb.setRowHeight((int)(panel.getHeight()/(double)tb.getRowCount()));
		for (int i=0;i<tb.getColumnCount()-1;i++) {
			tb.getColumnModel().getColumn(i).setPreferredWidth((int)(((double)panel.getWidth()/((double)tb.getColumnCount()+1))));
		}
		tb.getColumnModel().getColumn(4).setPreferredWidth((int)(((double)panel.getWidth()*2.0/((double)tb.getColumnCount()+1))));
		sPane.setBounds(0,0,panel.getWidth(),panel.getHeight());
		panel.add(sPane);
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("tachado.png"));
        Image image = imageIcon.getImage();
        newimg = image.getScaledInstance(panel.getWidth()/(tb.getColumnCount()+1), tb.getRowHeight(),  java.awt.Image.SCALE_SMOOTH); // redimensiona la imagen
		tb.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				filaEnTabla = tb.rowAtPoint(e.getPoint());
				columnaEnTabla = tb.columnAtPoint(e.getPoint());
				if (filaEnTabla >= 0 && columnaEnTabla >= 1&&columnaEnTabla<4) {
			        //Mirar si ya había algún sospechoso marcado
		            if(rowYcolYaSel.containsKey(filaEnTabla)) {
		            	Gestion.jugadores.get(numJugador).lista.get(Gestion.datosPartida.todasLasCartas.get(filaEnTabla)).set(rowYcolYaSel.get(filaEnTabla), false);
		            	Gestion.jugadores.get(numJugador).lista.get(Gestion.datosPartida.todasLasCartas.get(filaEnTabla)).set(columnaEnTabla, true);
		            	rowYcolYaSel.replace(filaEnTabla, columnaEnTabla);
		            }else {
		            	rowYcolYaSel.put(filaEnTabla, columnaEnTabla);
		            	Gestion.jugadores.get(numJugador).lista.get(Gestion.datosPartida.todasLasCartas.get(filaEnTabla)).set(columnaEnTabla, true);
		            }
					//filasYaSeleccionadas.add(filaEnTabla);
					tb.repaint();
				} else  {
					filaEnTabla = 0;
					columnaEnTabla = 0;
				}
				
			}
		});
		
		tb.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		    private JLabel lblSel = new JLabel();

		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
		            boolean hasFocus, int row, int column) {
		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        if (column == 0||column==4) {
		            return c;
		        }
		        for (Integer r: rowYcolYaSel.keySet()) {
		        	if (r == row && rowYcolYaSel.get(r) == column) {
		        		lblSel = lblTachado();
		                return lblSel;
		        	}
		        }
		        
		        
		            
		        return c;
		    }
		});
	}
	
	
	public JLabel lblTachado() {
		JLabel lblSel = new JLabel();
        
        lblSel.setIcon(new ImageIcon(newimg));
        return lblSel;
	}

	private class TablaLista implements TableModel{
		
		private ArrayList<Object> nombresEnums = new ArrayList<Object>();
		private ArrayList<Boolean> seleccionado = new ArrayList<Boolean>();
		private JPanel pnlRadio;
		private JRadioButton seleccion1;
		private JRadioButton seleccion2;
		private JRadioButton seleccion3;
		
		public TablaLista() {
			for (Sospechosos personaje: Sospechosos.values()) {
				nombresEnums.add(personaje);
				seleccionado.add(false);
			}
			for (Armas arma: Armas.values()) {
				nombresEnums.add(arma);
				seleccionado.add(false);
			}
			for (Sitio sitio: Sitio.values()) {
				nombresEnums.add(sitio);
				seleccionado.add(false);
			}
		}

		@Override
		public int getRowCount() {
			return nombresEnums.size();
		}

		@Override
		public int getColumnCount() {
			return 5;
		}

		private static final String[] cabeceras = {"Nombre","100%","Duda","0%","Anotaciones"};
		@Override
		public String getColumnName(int columnIndex) {
			return cabeceras[columnIndex];
		}

		private static final Class<?>[] clases = {Object.class, Object.class,Object.class,Object.class,String.class};
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return clases[columnIndex];
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if(columnIndex==4) {
				return true;
			}
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return nombresEnums.get(rowIndex);
			case 4:
				return Gestion.jugadores.get(Gestion.getNumTurno()).anotaciones.get(rowIndex);
			default:
				return null;
			}
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	        if (columnIndex == 4 && aValue != null && aValue instanceof String) {
	        	 Gestion.jugadores.get(Gestion.getNumTurno()).anotaciones.set(rowIndex, aValue.toString());
	        }
		}

		@Override
		public void addTableModelListener(TableModelListener l) {
			
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			
		}

	}
	
}
