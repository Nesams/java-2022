package ee.taltech.iti0202.computerbuilder.database;


import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class Database {
    private final Map<Integer, Component> components = new HashMap<>();
    private static Database instance = null;

    public Database() {
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void saveComponent(Component component) throws ProductAlreadyExistsException {
        if (components.containsValue(component)) {
            throw new ProductAlreadyExistsException();
        } else {
            components.put(component.getId(), component);
        }
    }

    public void increaseComponentStock(int id, int amount) throws ProductNotFoundException,
            IllegalArgumentException, NullPointerException {
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException();
            } else {
                this.components.get(id).increaseAmount(amount);
            }
        } catch (NullPointerException e) {
            throw new ProductNotFoundException();
        }
    }

    public void decreaseComponentStock(int id, int amount) throws OutOfStockException,
            ProductNotFoundException, IllegalArgumentException, NullPointerException {
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException();
            } else if (components.get(id).getAmount() < amount) {
                throw new OutOfStockException();
            } else {
                this.components.get(id).decreaseAmount(amount);
            }
        } catch (NullPointerException e) {
            throw new ProductNotFoundException();
        }
    }

    public Map<Integer, Component> getComponents() {

        return components;
    }
    public Map<Integer, Component> getBattery() {
        return components.entrySet().stream()
                .filter(x -> x.getValue().getType().equals(Component.Type.BATTERY))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public Map<Integer, Component> getComputerCases() {
        return components.entrySet().stream()
                .filter(x -> x.getValue().getType().equals(Component.Type.CASE))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public Map<Integer, Component> getCPU() {
        return components.entrySet().stream()
                .filter(x -> x.getValue().getType().equals(Component.Type.CPU))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public Map<Integer, Component> getGPU() {
        return components.entrySet().stream()
                .filter(x -> x.getValue().getType().equals(Component.Type.GPU))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public Map<Integer, Component> getHDD() {
        return components.entrySet().stream()
                .filter(x -> x.getValue().getType().equals(Component.Type.HDD))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public Map<Integer, Component> getKeyboards() {
        return components.entrySet().stream()
                .filter(x -> x.getValue().getType().equals(Component.Type.KEYBOARD))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public Map<Integer, Component> getMotherBoards() {
        return components.entrySet().stream()
                .filter(x -> x.getValue().getType().equals(Component.Type.MOTHERBOARD))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public Map<Integer, Component> getPSU() {
        return components.entrySet().stream()
                .filter(x -> x.getValue().getType().equals(Component.Type.PSU))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public Map<Integer, Component> getRAM() {
        return components.entrySet().stream()
                .filter(x -> x.getValue().getType().equals(Component.Type.RAM))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public Map<Integer, Component> getScreen() {
        return components.entrySet().stream()
                .filter(x -> x.getValue().getType().equals(Component.Type.SCREEN))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public Map<Integer, Component> getSSD() {
        return components.entrySet().stream()
                .filter(x -> x.getValue().getType().equals(Component.Type.SSD))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public Map<Integer, Component> getTouchpad() {
        return components.entrySet().stream()
                .filter(x -> x.getValue().getType().equals(Component.Type.TOUCHPAD))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
