
import java.util.ArrayList;

public class User {
    private String name;
    private Subscription subscription;
    private Cart cart;
    private ArrayList<Order> orders;
    private Address shippingAddress;
    private Address billingAddress;

    public User(String name, Subscription subscription, Cart cart) {
        this.name = name;
        this.subscription = subscription;  // normal, gold, platinum, silver membership
        this.cart = cart;
        this.orders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription role) {
        this.subscription = role;
    }

    public void viewCart() {
        cart.viewCartDetails();
    }

    public void setShippingAddress(Address newAddress){
        this.shippingAddress = newAddress;
    }

    public void setBillingAddress(Address newAddress) {
        this.billingAddress = newAddress;
    }

    public void addToCart(Media product, int quantity) {
        cart.addProductToCart(product, quantity);
    }

    public void removeFromCart(Media product) {
        cart.removeProductFromCart(product);
    }

    public void viewOrders() {
        for (Order order : orders) {
            order.printOrderDetails();
        }
    }

    public void checkout() {
        Order order = new Order(cart, this.subscription);
        Address shippingAddress = new Address("123 Main St", "", "Springfield", "IL", "62701", "USA");
        Address billingAddress = new Address("123 Main St", "", "Springfield", "IL", "62701", "USA");
        order.setShippingAddress(shippingAddress);
        order.setBillingAddress(billingAddress);
        order.setOrderStatus(OrderStatus.PLACED);


        order.setDateCreated(new Date(2024, 1, 1));

        order.setUserName(this.name);
        orders.add(order);
    }

    public void checkout(Address shippingAddress, Address billingAddress, Date date) {
        Order order = new Order(cart, this.subscription);
        order.setShippingAddress(shippingAddress);
        order.setBillingAddress(billingAddress);
        order.setOrderStatus(OrderStatus.PLACED);
        order.setDateCreated(date);
        order.setUserName(this.name);
        order.setDateShipped(date);
        orders.add(order);
    }
}


