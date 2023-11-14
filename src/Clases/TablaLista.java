package Clases;

import javax.swing.JRadioButton;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class TablaLista implements TableModel{

	@Override
	public int getRowCount() {
		return 22;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	private static final String[] cabeceras = {"Nombre","Selección"};
	@Override
	public String getColumnName(int columnIndex) {
		return cabeceras[columnIndex];
	}

	private static final Class<?>[] clases = {Object.class, JRadioButton.class};
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
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		
	}

}
