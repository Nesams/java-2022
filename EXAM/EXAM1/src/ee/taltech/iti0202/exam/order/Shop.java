package ee.taltech.iti0202.exam.order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Shop {
    private static ArrayList<Product> products = new ArrayList<>();
    private static int idCounter = 1;
    private static HashMap<Integer, Order> orders = new HashMap<>();
    private static HashMap<Order, Integer> orderss = new HashMap<>();
    private  static HashMap<String, ArrayList<Product>> productMap = new HashMap<>();
    private static ArrayList<Product> usedProducts = new ArrayList<>();
    public Shop() {
        
    }
    
    public boolean addProduct(Product product) {
        if (!products.contains(product)) {
           products.add(product);
           ArrayList<Product> products1 = productMap.get(product.getName());
           if (products1 == null) {
               products1 = new ArrayList<>();
               products1.add(product);
               productMap.put(product.getName(), products1);
           } else {
               products1.add(product);
           }
           return true;
        }
        return false;
    }

    public int createNewOrder() {
        Order newOrder = new Order(idCounter);
        orders.put(newOrder.getId(), newOrder);
        orderss.put(newOrder, newOrder.getId());
        idCounter++;
        return newOrder.getId();
    }

    public boolean addProductToOrder(int orderNumber, String itemName) {
        if (orderss.containsValue(orderNumber)) {
            if (productMap.containsKey(itemName)) {
                orders.get(orderNumber).addProducts(getCheapestProduct(itemName));
                usedProducts.add(getCheapestProduct(itemName));
                products.remove(getCheapestProduct(itemName));
                productMap.get(getCheapestProduct(itemName).getName()).remove(getCheapestProduct(itemName));
                return true;
            }
        }
        return false;
    }

    public Product getCheapestProduct(String itemName) {
        return productMap.get(itemName).stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList()).get(0);
    }

    public int getOrderSum(int orderNumber) {
        if (orders.containsKey(orderNumber)) {
            int orderSum = 0;
            ArrayList<Product> orderItems = orders.get(orderNumber).getProducts();
            if (orderItems.size() == 0) {
                return 0;
            } else {
                for (Product product : orderItems) {
                    orderSum += product.getPrice();
                }
                return orderSum;
            }
        }
        return -1;
    }

    public boolean cancelOrder(int orderNumber) {
        if (!orders.containsKey(orderNumber)) {
            return false;
        }
        else if (orders.get(orderNumber).getCancelled()) {
            return false;
        } else {
            orders.get(orderNumber).cancelled();
            for (Product product : orders.get(orderNumber).getProducts()) {
                usedProducts.remove(product);
                products.add(product);
                productMap.get(product.getName()).add(product);

            }
            return true;
        }
    }

    public List<Product> getAvailableProducts() {
        return products;
    }
}
