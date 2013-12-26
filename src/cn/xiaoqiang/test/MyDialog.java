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
		//myTable.getValueAt(0,0);   //第一个值为行，第二个值为列。
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

		/** * 返回表格title */
		public String getColumnName(int column) {
			return columnName[column];
		}

		/** * 返回不同类型的数据 */
		public Class getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}

		/** * 返回 true 单元格可编辑 */
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if (columnIndex > 1) {
				return false;
			} else {
				return true;
			}
		}

		/** * 得到编辑后的值 */
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
