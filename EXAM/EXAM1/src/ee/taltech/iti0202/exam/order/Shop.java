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
                if (getCheapestProduct(itemName) != null) {
                    orders.get(orderNumber).addProducts(getCheapestProduct(itemName));
                    usedProducts.add(getCheapestProduct(itemName));
                    products.remove(getCheapestProduct(itemName));
                    productMap.get(getCheapestProduct(itemName).getName()).remove(getCheapestProduct(itemName));
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public Product getCheapestProduct(String itemName) {
        if (productMap.get(itemName).size() != 0) {
            return productMap.get(itemName).stream()
                    .sorted(Comparator.comparing(Product::getPrice))
                    .collect(Collectors.toList()).get(0);
        }
        return null;
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
//    public static void main(String[] args) {
//
//        Shop topShop = new Shop();
//        Product p1 = new Product("TV", 100);
//        Product p2 = new Product("Radio", 60);
//        Product p3 = new Product("TV", 90);
//        topShop.addProduct(p1);
//        topShop.addProduct(p2);
//        topShop.addProduct(p3);
//        int orderNumber1 = topShop.createNewOrder();
//        System.out.println(orderNumber1); // 1
//        System.out.println(topShop.addProductToOrder(orderNumber1, "Radio")); // true
//        System.out.println(topShop.addProductToOrder(orderNumber1, "Cheese")); // false
//        System.out.println(topShop.addProductToOrder(orderNumber1, "Radio")); // false
//
//        Product p4 = new Product("TV", 100);
//        System.out.println(topShop.addProduct(p3)); // false - same product instance not allowed
//        System.out.println(topShop.addProduct(p4)); // true - another product with same values is allowed
//
//        int orderNumber2 = topShop.createNewOrder();
//        System.out.println(orderNumber2);
//        System.out.println(topShop.addProductToOrder(orderNumber2, "TV")); // true
//        System.out.println(topShop.getOrderSum(orderNumber2)); // 90
//        System.out.println(topShop.addProductToOrder(orderNumber2, "TV")); // true
//        System.out.println(topShop.getOrderSum(orderNumber2)); // 190
//        System.out.println(topShop.addProductToOrder(orderNumber2, "TV")); // true
//        System.out.println(topShop.getOrderSum(orderNumber2)); // 290
//        System.out.println(topShop.addProductToOrder(orderNumber2, "TV")); // false
//        System.out.println(topShop.getOrderSum(orderNumber2)); // 290
//
//// cancel order
//        topShop.cancelOrder(orderNumber1); // free all the products from order 1
//        int orderNumber3 = topShop.createNewOrder();
//        System.out.println(orderNumber3); // 3
//        System.out.println(topShop.addProductToOrder(orderNumber3, "Radio")); // true
//        System.out.println(topShop.getOrderSum(orderNumber3)); // 60
//    }
}
