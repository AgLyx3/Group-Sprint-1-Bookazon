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
    private Subscription subscription;

    public Order(Cart cart, Subscription subscription) {
        this.items = cart.getItems();
        this.orderPrice = calculatePrice();
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

    
    public double calculatePrice() {
        double totalPrice = 0.0;
        for (CartItem item : items) {
            totalPrice += item.getTotalPrice();
        }
        return subscription.applyDiscount(totalPrice);
    }
   
}

interface Subscription {
    double applyDiscount(double price);
}

class GoldSubscription implements Subscription {
    public double applyDiscount(double price) {
        return price * 0.85; // 15% discount
    }
}

class PlatinumSubscription implements Subscription {
    public double applyDiscount(double price) {
        return price * 0.90; // 10% discount
    }
}

class SilverSubscription implements Subscription {
    public double applyDiscount(double price) {
        return price * 0.95; // 5% discount
    }
}

class NoSubscription implements Subscription {
    public double applyDiscount(double price) {
        return price; // No discount
    }
}
