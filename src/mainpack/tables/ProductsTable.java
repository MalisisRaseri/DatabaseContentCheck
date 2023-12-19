package mainpack.tables;

import mainpack.models.Products;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ProductsTable implements TableModel {
    public static final String[] COLUMNS = new String[] {
            "Артикул",
            "Наименование",
            "Цвет",
            "Цена",
            "Остаток на складе"
    };

    @Override
    public int getRowCount() {
        return Products.getProductInstance().getProductsCount();
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
            case 1:
            case 2:
                return String.class;
            case 3:
            case 4:
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
        Products product = Products.getProductInstance().getProduct(rowIndex);
        switch (columnIndex) {
            case 0: return product.getItem();
            case 1: return product.getItem_name();
            case 2: return product.getItem_color();
            case 3: return product.getItem_price();
            case 4: return product.getQuantity_left();
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