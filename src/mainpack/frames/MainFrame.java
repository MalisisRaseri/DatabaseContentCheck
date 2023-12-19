package mainpack.frames;

import mainpack.tables.OrderLinesTable;
import mainpack.tables.OrdersTable;
import mainpack.tables.ProductsTable;
import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    ProductsTable productsTable = new ProductsTable();
    OrdersTable orderTable = new OrdersTable();
    OrderLinesTable orderItemTable = new OrderLinesTable();
    JTable table = new JTable();
    private String mainName = DataBaseViewer.getTableName();
    public MainFrame(){
        setTitle(mainName);
        setSize(1000,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        switch (mainName) {
            case ("продукты"):
                table.setModel(productsTable);
                table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                table.setIntercellSpacing(new Dimension(10, 5));
                add(table.getTableHeader(), BorderLayout.NORTH);
                JScrollPane spp = new JScrollPane(table);
                spp.setPreferredSize(new Dimension(1000, 300));
                add(spp, BorderLayout.CENTER);
                break;

            case("заказы"):
                table.setModel(orderTable);
                table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                table.setIntercellSpacing(new Dimension(5, 5));
                add(table.getTableHeader(), BorderLayout.NORTH);
                JScrollPane spo = new JScrollPane(table);
                spo.setPreferredSize(new Dimension(1000, 300));
                add(spo, BorderLayout.CENTER);
                break;

            case ("позиции_заказа"):
                table.setModel(orderItemTable);
                table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                table.setIntercellSpacing(new Dimension(10, 5));
                add(table.getTableHeader(), BorderLayout.NORTH);
                JScrollPane spi = new JScrollPane(table);
                spi.setPreferredSize(new Dimension(1000, 300));
                add(spi, BorderLayout.CENTER);
                break;
        }

    }
}