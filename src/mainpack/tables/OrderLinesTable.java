package mainpack.tables;

import mainpack.models.OrderLines;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class OrderLinesTable implements TableModel {

    public static final String[] COLUMNS = new String[] {
            "Код записи заказа",
            "Артикул товара",
            "Цена",
            "Количество"
    };
    @Override
    public int getRowCount() {
        return OrderLines.getOrderLinesInstance().getOrderLinesCount();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUMNS[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
            case 3:
                return Integer.class;
        }
        throw new IllegalArgumentException("Неизвестный столбец");
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OrderLines orderLines = OrderLines.getOrderLinesInstance().getOrderLines(rowIndex);
        switch (columnIndex) {
            case 0: return orderLines.getOrder_id();
            case 1: return orderLines.getItem();
            case 2: return orderLines.getOrder_price();
            case 3: return orderLines.getOrder_quantity();
        }
        throw new IllegalArgumentException("Неизвестный столбец");
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