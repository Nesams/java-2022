package ee.taltech.iti0202.stock.product;
import ee.taltech.iti0202.stock.exceptions.StockException;

public class Product {
    private final String name;
    private final int price;
    private static int idcounter = 0;
    private final int id;

    /**
     * Create a new product with the given name and price.
     * <p>
     * If the price is negative, throw a StockException with a NEGATIVE_PRICE reason.
     *
     * @param name Name of the product.
     * @param price Price of the product.
     * @throws StockException NEGATIVE_PRICE
     */
    public Product(String name, int price) throws StockException {
        this.name = name;
        getNextId();
        this.id = idcounter;
        if (price < 0) {
        throw  new StockException(StockException.Reason.NEGATIVE_PRICE);
        } else {
            this.price = price;
        }
    }
    /**
     * Returns the next id.
     *
     * This value has to increment with every call.
     *
     * @return The next id.
     */
    public static int getNextId() {
        return idcounter++;
    }
    /**
     * Returns id of the product.
     * 
     * @return id of the product.
     */
    public int getId() {
        return id;
    }
    /**
     * Returns the name of the product.
     * 
     * @return the name of the product.
     */
    public String getName() {
        return this.name;
    }
    /**
     * Returns the price of the product.
     * 
     * @return the price of the product.
     */
    public int getPrice() {
        return this.price;
    }

    public String nameToString() {
        return this.getName();
    }
}
