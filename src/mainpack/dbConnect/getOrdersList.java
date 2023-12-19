package mainpack.dbConnect;

import mainpack.dbConnect.ConnectionPool;
import mainpack.frames.DataBaseViewer;
import mainpack.frames.MainFrame;
import mainpack.models.Orders;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface getOrdersList {
    public default List<Orders> getOrdersList() throws SQLException {
        List<Orders> ordersList = new ArrayList<>();
        try(Connection con = ConnectionPool.getConnection()){
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM orders");
            while(rs.next()){

                Integer id = rs.getInt(1);
                LocalDate registration_date = (rs.getDate(2) != null? rs.getDate(2).toLocalDate(): null);
                String customer_name = rs.getString(3);
                String phone_num = rs.getString(4);
                String email  = rs.getString(5);
                String delivery_address = rs.getString(6);
                String order_status = rs.getString(7);
                LocalDate shipment_date = (rs.getDate(8) != null? rs.getDate(8).toLocalDate(): null);
                Orders orders = new Orders(id, registration_date, customer_name, phone_num, email, delivery_address, order_status, shipment_date);
                ordersList.add(orders);
                DataBaseViewer.setUlrError(true);
            }
        }
        catch(SQLException se) {
            DataBaseViewer.setUlrError(false);
            System.out.println(se);
            System.out.println("Неверный адрес базы данных");
        }
        return ordersList;
    }
}

