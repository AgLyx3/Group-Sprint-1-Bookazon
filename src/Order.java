import java.util.ArrayList;

enum OrderStatus {
    PLACED, PROCESSING, SHIPPED, DELIVERED, CANCELLED
}

public class Order {
    private String dateCreated;
    private String dateShipped;
    private String userName;
    private OrderStatus orderStatus;
    private Address shippingAddress;
    private Address billingAddress;
    private ArrayList<CartItem> items;
    private double orderPrice;

    public Order(Cart cart, String subscription) {
        this.items = cart.getItems();
        this.orderPrice = calculatePrice(subscription);
    }

    public void setShippingAddress(Address newAddress) {
        this.shippingAddress.setAddress(newAddress);
    }

    public void setBillingAddress(Address newAddress) {
        this.billingAddress.setAddress(newAddress);
    }

    public void setOrderStatus(OrderStatus status) {
        this.orderStatus = status;
    }

    public void setDateCreated(String date) {
        this.dateCreated = date;
    }

    public void setDateShipped(String date) {
        this.dateShipped = date;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public void printOrderDetails() {
        System.out.println("Order Details:");
        System.out.println("Date Created: " + dateCreated);
        System.out.println("Date Shipped: " + dateShipped);
        System.out.println("User Name: " + userName);
        System.out.println("Order Status: " + orderStatus);
        System.out.println("Shipping Address: " + shippingAddress.getAddressLine1() + ", " + shippingAddress.getAddressLine2() + ", " + shippingAddress.getAddressCity() + ", " + shippingAddress.getAddressState() + ", " + shippingAddress.getAddressZip() + ", " + shippingAddress.getAddressCountry());
        System.out.println("Billing Address: " + billingAddress.getAddressLine1() + ", " + billingAddress.getAddressLine2() + ", " + billingAddress.getAddressCity() + ", " + billingAddress.getAddressState() + ", " + billingAddress.getAddressZip() + ", " + billingAddress.getAddressCountry());
        System.out.println("Order Price: $" + orderPrice);
    }

    public double calculatePrice(String subscription) {
        double totalPrice = 0.0;

        for (CartItem item : items) {
            totalPrice += item.getTotalPrice();
        }

        if (subscription == "gold") {
            totalPrice *= 0.15; // 15% discount for prime members
        } else if (subscription == "platinum") {
            totalPrice *= 0.10; // 10% discount for platinum members
        } else if (subscription == "silver") {
            totalPrice *= 0.05; // 5% discount for silver members
        } 

        return totalPrice;
    }
}
