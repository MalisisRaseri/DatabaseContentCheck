package mainpack.models;

import mainpack.dbConnect.DBConnection;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Orders {
    private Integer id;
    private LocalDate registration_date;
    private String customer_name;
    private String phone_num;
    private String email;
    private String delivery_address;
    private String order_status;
    private LocalDate shipment_date;



    private static Orders orderList;

    static {
        try {
            orderList = new Orders();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Orders> orders = null;

    public Orders(Integer id, LocalDate registration_date, String customer_name,
                  String phone_num, String email, String delivery_address,
                  String order_status, LocalDate shipment_date) {
        setId(id);
        setRegistration_date(registration_date);
        setCustomer_name(customer_name);
        setPhone_num(phone_num);
        setEmail(email);
        setDelivery_address(delivery_address);
        setOrder_status(order_status);
        setShipment_date(shipment_date);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(LocalDate registration_date) {
        this.registration_date = registration_date;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public LocalDate getShipment_date() {
        return shipment_date;
    }

    public void setShipment_date(LocalDate shipment_date) {
        this.shipment_date = shipment_date;
    }

    public Orders() throws SQLException { orders = new DBConnection().getOrdersList();}
    public Orders getOrder(int index) {
        return orders.get(index);
    }
    public static Orders getOrderInstance() {
        return orderList;
    }
    public int getOrderCount() {
        return orders.size();
    }

    @Override
    public String toString() {
        return "Orders {id = " + id + ", reg date = " +
                registration_date + ", customer info = " + customer_name +
                ", customer phone number = " + phone_num + ", customer email = " +
                email + ", delivery address = " + delivery_address +
                ", order status = " + order_status + ", shipment date = " +
                shipment_date + '}';
    }
}
