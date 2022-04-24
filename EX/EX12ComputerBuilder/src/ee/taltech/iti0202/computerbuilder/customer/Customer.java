package ee.taltech.iti0202.computerbuilder.customer;

import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.computer.Computer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private BigDecimal balance;
    private final List<Component> components = new ArrayList<>();
    private final List<Computer> computers = new ArrayList<>();

    public Customer(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Component> getComponents() {
        return components;
    }

    public List<Computer> getComputers() {
        return computers;
    }
    public void addComputer(Computer computer) {
        if (!computers.contains(computer)) {
            this.computers.add(computer);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
