package mainpack.models;

import mainpack.dbConnect.DBConnection;

import java.sql.SQLException;
import java.util.List;

public class OrderLines {
    private Integer order_id;
    private String item;
    private Integer order_price;
    private Integer order_quantity;

    private static OrderLines orders_linesList;

    static {
        try {
            orders_linesList = new OrderLines();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<OrderLines> orderLines= null;


    public OrderLines(Integer order_id, String item, Integer order_price, Integer order_quantity) {
        setOrder_id(order_id);
        setItem(item);
        setOrder_price(order_price);
        setOrder_quantity(order_quantity);

    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getOrder_price() {
        return order_price;
    }

    public void setOrder_price(Integer order_price) {
        this.order_price = order_price;
    }

    public Integer getOrder_quantity() {
        return order_quantity;
    }

    public void setOrder_quantity(Integer order_quantity) {
        this.order_quantity = order_quantity;
    }


    public OrderLines() throws SQLException {
        orderLines = (List<OrderLines>) new DBConnection().getOrderLinesList();
    }

    public static OrderLines getOrderLinesInstance() {
        return (OrderLines) orders_linesList;
    }
    public int getOrderLinesCount() {
        return orderLines.size();
    }

    public OrderLines getOrderLines(int index) {
        return orderLines.get(index);
    }


    @Override
    public String toString() {
        return "Order details in line {order id = " + order_id + ", item = " +
                item + ", price = " + order_price + ", quantity  = " +
                order_quantity + '}';
    }
}