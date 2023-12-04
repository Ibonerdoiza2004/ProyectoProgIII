package Clases;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class Lista extends JPanel{
	
	TablaLista tbLista = null; //Esto es el modelo 
	JScrollPane sPane;
	public TablaLista getTbLista() {
		return tbLista;
	}

	public void setTbLista(TablaLista tbLista) {
		this.tbLista = tbLista;
	}

	public Lista(JPanel panel) {
		tbLista = new TablaLista();
		JTable tb = new JTable(tbLista);
		sPane= new JScrollPane(tb);
		tb.setRowHeight((int)(panel.getHeight()/(double)tb.getRowCount()));
		for (int i=0;i<tb.getColumnCount();i++) {
			System.out.println(tb.getColumnCount());
			tb.getColumnModel().getColumn(i).setPreferredWidth((int)(((double)panel.getWidth()/(double)tb.getColumnCount())));
		}
		sPane.setBounds(0,0,panel.getWidth(),panel.getHeight());
		panel.add(sPane);
	}

	private class TablaLista implements TableModel{
		
		private ArrayList<Object> nombresEnums = new ArrayList<Object>();
		private ArrayList<Boolean> seleccionado = new ArrayList<Boolean>();
		private JPanel pnlRadio;
		private JRadioButton seleccion1;
		private JRadioButton seleccion2;
		private JRadioButton seleccion3;
		
		public TablaLista() {
			for (Sospechosos sospechoso: Sospechosos.values()) {
				nombresEnums.add(sospechoso);
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
			return 4;
		}

		private static final String[] cabeceras = {"Nombre","100%","Duda","0%"};
		@Override
		public String getColumnName(int columnIndex) {
			return cabeceras[columnIndex];
		}

		private static final Class<?>[] clases = {Object.class, Object.class,Object.class,Object.class};
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return clases[columnIndex];
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return nombresEnums.get(rowIndex);
//			case 1:
//				return seleccionado.get(rowIndex);
			default:
				return null;
			}
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//	        if (columnIndex == 1 && aValue instanceof Boolean) {
//	        	seleccionado.set(rowIndex, (Boolean) aValue);
//	        }
		}

		@Override
		public void addTableModelListener(TableModelListener l) {
			
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			
		}

	}
	
}
