package mainpack.dbConnect;

import mainpack.frames.DataBaseViewer;
import mainpack.models.OrderLines;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface getOrderLinesList {
    public default List<OrderLines> getOrderLinesList() throws SQLException {
        List<OrderLines> orders_linesList = new ArrayList<>();
        try (Connection con = ConnectionPool.getConnection()) {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM order_lines");
            while (rs.next()) {

                Integer order_id = rs.getInt(1);
                String item = rs.getString(2);
                Integer order_price = rs.getInt(3);
                Integer order_quantity = rs.getInt(4);
                OrderLines orderLines = new OrderLines(order_id, item, order_price, order_quantity);
                orders_linesList.add(orderLines);
                DataBaseViewer.setUlrError(true);
            }

        } catch (SQLException ex) {
            DataBaseViewer.setUlrError(false);
            System.out.println("Неверный адрес базы данных");

        }
        return orders_linesList;
    }

}