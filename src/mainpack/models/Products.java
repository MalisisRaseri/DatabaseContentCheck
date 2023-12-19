package mainpack.models;

import mainpack.dbConnect.DBConnection;

import java.sql.SQLException;
import java.util.List;

public class Products {
    private String item;
    private String item_name;
    private String item_color;
    private int item_price;
    private int quantity_left;

    private static Products productList;

    static {
        try {
            productList = new Products();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Products> products = null;
    public Products(String item, String item_name, String item_color, int item_price, int quantity_left) {
        setItem(item);
        setItem_name(item_name);
        setItem_color(item_color);
        setItem_price(item_price);
        setQuantity_left(quantity_left);
    }

    public Products() throws SQLException {
        products = new DBConnection().getProductsList();
    }
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_color() {
        return item_color;
    }

    public void setItem_color(String item_color) {
        this.item_color = item_color;
    }

    public int getItem_price() {
        return item_price;
    }

    public void setItem_price(int item_price) {
        this.item_price = item_price;
    }

    public int getQuantity_left() {
        return quantity_left;
    }

    public void setQuantity_left(int quantity_left) {
        this.quantity_left = quantity_left;
    }

    public int getProductsCount() {
        return products.size();
    }
    public Products getProduct(int index) {
        return products.get(index);
    }
    public static Products getProductInstance() {
        return productList;
    }


    @Override
    public String toString() {
        return "Product {item = " + item + ", name = " +
                item_name + ", color = " + item_color + ", price = " +
                item_price + ", quantity left in stock = " + quantity_left + '}';
    }



}