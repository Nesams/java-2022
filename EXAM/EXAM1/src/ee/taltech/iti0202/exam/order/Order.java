package ee.taltech.iti0202.exam.order;

import java.util.ArrayList;

public class Order {
    private static int id;
    private ArrayList<Product> products;

    public Order(int id) {
        this.id = id;
        this.products = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
    public void addProducts(Product product) {
        products.add(product);
    }
}
