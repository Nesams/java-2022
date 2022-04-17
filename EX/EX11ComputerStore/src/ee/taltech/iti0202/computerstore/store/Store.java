package ee.taltech.iti0202.computerstore.store;
import ee.taltech.iti0202.computerstore.Customer;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.database.Database;
import ee.taltech.iti0202.computerstore.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.math.BigDecimal;
import java.util.stream.Collectors;

public class Store {
    private String name;
    private BigDecimal balance;
    private BigDecimal profitMargin;
    private Database database;

    public Store(String name, BigDecimal balance, BigDecimal profitMargin) throws IllegalAccessException {
        if (profitMargin.intValue() < 1) {
            throw new IllegalAccessException();
        } else {
            this.name = name;
            this.balance = balance;
            this.profitMargin = profitMargin;
            this.database = Database.getInstance();
        }
    }

    public Component purchaseComponent(int id, Customer customer) throws OutOfStockException,
            ProductNotFoundException,
            NotEnoughMoneyException {
        try {
            Component component = database.getComponents().get(id);
            int price = component.getPrice().intValue() * profitMargin.intValue();
            if (customer.getBalance().intValue() >= price) {
                database.decreaseComponentStock(id, 1);
                customer.setBalance(BigDecimal.valueOf(customer.getBalance().intValue() - price));
                this.balance = BigDecimal.valueOf(this.balance.intValue() + price);
                customer.getComponents().add(component);
                return component;
            } else {
                throw new NotEnoughMoneyException();
            }
        } catch (NullPointerException | ProductNotFoundException e) {
            throw new ProductNotFoundException();
        }
    }

    public List<Component> getAvailableComponents() {
        return database.getComponents()
                .values().stream()
                .filter(component -> component.getAmount() > 0)
                .collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByAmount() {
        return database.getComponents()
                .values().stream()
                .sorted(Comparator.comparing(Component::getAmount))
                .collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByName() {
        return database.getComponents()
                .values().stream()
                .sorted(Comparator.comparing(Component::getName))
                .collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByPrice() {
        return database.getComponents()
                .values().stream()
                .sorted(Comparator.comparing(Component::getPrice))
                .collect(Collectors.toList());
    }

    public List<Component> filterByType(Component.Type type) {
        return database.getComponents()
                .values().stream()
                .filter(component -> component.getType().equals(type))
                .collect(Collectors.toList());
    }

    public BigDecimal getInventoryValue() {
        List<Component> componentsValues = (List<Component>) database.getComponents().values();
        int inventoryValue = 0;
        for (int i = 0; i < componentsValues.size(); i++) {
            inventoryValue += componentsValues.get(i).getPrice().intValue() * profitMargin.intValue() * componentsValues.get(i).getAmount();
        }
        return BigDecimal.valueOf(inventoryValue);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getProfitMargin() {
        return this.profitMargin;
    }

    public void setProfitMargin(BigDecimal profitMargin) {
        if (profitMargin.intValue() < 1) {
            throw new IllegalArgumentException();
        } else {
            this.profitMargin = profitMargin;
        }
    }
}