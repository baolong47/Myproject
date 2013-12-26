package cn.xiaoqiang.test;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class MyDialog extends JDialog {
	MyDialog() {
		Object[][] rows = { { new Boolean(false), "22" },
				{ new Boolean(false), "22" } };
		String[] columnName = { "CheckBox", "22" };
		JPanel contentPane = new JPanel();
		MyTable myTable = new MyTable();
		//myTable.getValueAt(0,0);   //��һ��ֵΪ�У��ڶ���ֵΪ�С�
		myTable.setColumnName(columnName);
		myTable.setRows(rows);
		JTable jtable = new JTable(myTable);
		JScrollPane scrollPane = new JScrollPane(jtable);
		scrollPane.setPreferredSize(new Dimension(490, 310));
		contentPane.add(scrollPane);
		this.add(contentPane);
		this.setBounds(300, 400, 500, 400);
		this.setVisible(true);
	}

	public class MyTable extends AbstractTableModel {
		private String[] columnName;
		private Object[][] rows;

		public int getColumnCount() {
			return columnName.length;
		}

		public int getRowCount() {
			return rows.length;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			return rows[rowIndex][columnIndex];
		}

		/** * ���ر��title */
		public String getColumnName(int column) {
			return columnName[column];
		}

		/** * ���ز�ͬ���͵����� */
		public Class getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}

		/** * ���� true ��Ԫ��ɱ༭ */
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if (columnIndex > 1) {
				return false;
			} else {
				return true;
			}
		}

		/** * �õ��༭���ֵ */
		public void setValueAt(Object value, int rowIndex, int columnIndex) {
			rows[rowIndex][columnIndex] = value;
		}

		public void setColumnName(String[] columnName) {
			this.columnName = columnName;
		}

		public void setRows(Object[][] rows) {
			this.rows = rows;
		}
	}

	public static void main(String[] args) {
		MyDialog md = new MyDialog();
		
	}
}
