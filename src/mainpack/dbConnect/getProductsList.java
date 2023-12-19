package mainpack.dbConnect;

import mainpack.dbConnect.ConnectionPool;
import mainpack.frames.DataBaseViewer;
import mainpack.frames.MainFrame;
import mainpack.models.Products;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface getProductsList {
    public default List<Products> getProductsList() throws SQLException {
        List<Products> productsList = new ArrayList<>();

        try(Connection con = ConnectionPool.getConnection()){
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM products");

            while (rs.next()) {

                String item = rs.getString(1);
                String item_name = rs.getString(2);
                String item_color = rs.getString(3);
                Integer item_price = rs.getInt(4);
                Integer quantity_left = rs.getInt(5);
                Products products = new Products(item, item_name, item_color, item_price, quantity_left);
                productsList.add(products);
                DataBaseViewer.setUlrError(true);
            }

        } catch (SQLException ex) {
            DataBaseViewer.setUlrError(false);
            System.out.println("Неверный адрес базы данных");
        }
        return productsList;
    }
}
