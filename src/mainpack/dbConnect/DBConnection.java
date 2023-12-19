package mainpack.dbConnect;

import mainpack.frames.DataBaseViewer;

import java.sql.*;

public class DBConnection implements ConnectionPool, getProductsList, getOrdersList, getOrderLinesList {
    String url = DataBaseViewer.getUrl();                                                    // "jdbc:mysql://localhost:3306/accessToProduct"
    String user = "root";
    String psw = "MyFirstRoot";

    public void checkUrl() {
        try (Connection connection = DriverManager.getConnection(url, user, psw);
             Statement statement = connection.createStatement()) {
            statement.executeQuery("SELECT * FROM Products");
            statement.executeQuery("SELECT * FROM Orders");
            statement.executeQuery("SELECT * FROM Order_lines");
            DataBaseViewer.setUlrError(true);
        } catch (SQLException ex) {
            DataBaseViewer.setUlrError(false);
            System.out.println("Неверный адрес базы данных");
        }
    }
}
//    public List<Products> findAllProducts() {
//        List<Products> products = new ArrayList<>();
//        try(Connection con = ConnectionPool.getConnection()){
//            Statement statement = con.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM Products");
//            while (resultSet.next()) {
//                String item = resultSet.getString(1);
//                String item_name = resultSet.getString(2);
//                String item_color = resultSet.getString(3);
//                Integer item_price = resultSet.getInt(4);
//                Integer quantity_left = resultSet.getInt(5);
//                Products product = new Products(item, item_name, item_color, item_price, quantity_left);
//                products.add(product);
//                DataBaseViewer.setUlrError(true);
//            }
//
//        } catch (SQLException ex) {
//            DataBaseViewer.setUlrError(false);
//            System.out.println("Неверный адрес базы данных");
//        }
//        return products;
//    }


//    public List<Orders> findAllOrders() {
//        List<Orders> orders = new ArrayList<>();
//        try(Connection con = ConnectionPool.getConnection()){
//            Statement statement = con.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM Orders");
//            while (resultSet.next()) {
//                Integer id = resultSet.getInt(1);
//                LocalDate registration_date = resultSet.getDate(2).toLocalDate();
//                String customer_name = resultSet.getString(3);
//                String phone_num = resultSet.getString(4);
//                String email = resultSet.getString(5);
//                String delivery_address = resultSet.getString(6);
//                String order_status = resultSet.getString(7);
//                LocalDate shipment_date = resultSet.getDate(8).toLocalDate();
//                Orders order = new Orders(id, registration_date,customer_name,phone_num,email,delivery_address,order_status, shipment_date);
//                orders.add(order);
//                DataBaseViewer.setUlrError(true);
//            }
//          } catch (SQLException ex) {
//            DataBaseViewer.setUlrError(false);
//            System.out.println("Неверный адрес базы данных");
//        }
//        return orders;
//    }
//    public List<OrderLines> findAllOrdersItems() {
//        List<OrderLines> orderLines = new ArrayList<>();
//        try(Connection con = ConnectionPool.getConnection()){
//            Statement statement = con.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM Order_lines");;
//            while (resultSet.next()) {
//                Integer order_id = resultSet.getInt(1);
//                String item = resultSet.getString(2);
//                Integer order_price = resultSet.getInt(3);
//                Integer order_quantity = resultSet.getInt(4);
//                OrderLines orderLine = new OrderLines(order_id, item, order_price, order_quantity);
//                orderLines.add(orderLine);
//                DataBaseViewer.setUlrError(true);
//            }
//
//        } catch (SQLException ex) {
//            DataBaseViewer.setUlrError(false);
//            System.out.println("Неверный адрес базы данных");
//        }
//        return orderLines;
//    }
//}


