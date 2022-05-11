package ee.taltech.iti0202.exam.order;

import java.util.ArrayList;

public class Order {
    private static int id;
    private ArrayList<Product> products;
    private Boolean cancelled;

    public Order(int id) {
        this.id = id;
        this.products = new ArrayList<>();
        this.cancelled = false;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void cancelled() {
        products.clear();
    }

    public void addProducts(Product product) {
        products.add(product);
    }
}
