package ee.taltech.iti0202.computerbuilder.store;

import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.computer.Computer;
import ee.taltech.iti0202.computerbuilder.computer.Type;
import ee.taltech.iti0202.computerbuilder.computer.UseCase;
import ee.taltech.iti0202.computerbuilder.computerFactory.ComputerFactory;
import ee.taltech.iti0202.computerbuilder.customer.Customer;
import ee.taltech.iti0202.computerbuilder.database.Database;
import ee.taltech.iti0202.computerbuilder.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComputerStore {
    private final ComputerFactory computerFactory;
    private String name;
    private BigDecimal balance;
    private BigDecimal profitMargin;
    private Database database;

    public ComputerStore(String name, BigDecimal balance, BigDecimal profitMargin) throws IllegalArgumentException {
        if (profitMargin.intValue() < 1) {
            throw new IllegalArgumentException();
        } else {
            this.name = name;
            this.balance = balance;
            this.profitMargin = profitMargin;
            this.database = Database.getInstance();
            this.computerFactory = ComputerFactory.getInstance(this);
        }
    }

    public Component purchaseComponent(int id, Customer customer) throws OutOfStockException,
            ProductNotFoundException,
            NotEnoughMoneyException,
            NullPointerException {
        try {
            Component component = database.getComponents().get(id);
            int price = component.getPrice() * profitMargin.intValue();
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

    public Computer purchaseComputer(Customer customer, UseCase useCase, Type type)
            throws NotEnoughMoneyException, ProductNotFoundException {
        try {
            Computer computer = computerFactory.assembleComputer(customer.getBalance().intValue(), useCase, type);

            customer.addComputer(computer);

            customer.setBalance(BigDecimal.valueOf(customer.getBalance().intValue() - computer.getComputerValue()));

            this.balance = BigDecimal.valueOf(this.balance.intValue() + computer.getComputerValue());

            return computer;
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return null;
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
        List<Component> componentsValues = new ArrayList<>(database.getComponents().values());
        int inventoryValue = 0;
        for (int i = 0; i < componentsValues.size(); i++) {
            inventoryValue += componentsValues.get(i).getPrice()
                    * profitMargin.intValue() * componentsValues.get(i).getAmount();
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

    public void setProfitMargin(BigDecimal profitMargin) throws IllegalArgumentException {
        if (profitMargin.intValue() < 1) {
            throw new IllegalArgumentException();
        } else {
            this.profitMargin = profitMargin;
        }
    }
}

