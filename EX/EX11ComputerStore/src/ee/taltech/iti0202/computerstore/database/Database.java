package ee.taltech.iti0202.computerstore.database;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Database {
    private final Map<Integer, Component> components = new HashMap<>();
    private static Database instance = null;

    public static Database getInstance() {
        if (instance.equals(null)) {
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

    public void deleteComponent(int id) throws ProductNotFoundException {
        if (!components.containsKey(id)) {
            throw new ProductNotFoundException();
        } else {
            components.remove(id);
        }
    }

    public void increaseComponentStock(int id, int amount) throws ProductNotFoundException, IllegalArgumentException {
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException();
            }
            this.components.get(id).increaseAmount(amount);
        } catch (NullPointerException e) {
            throw new ProductNotFoundException();
        }
    }

    public void decreaseComponentStock(int id, int amount) throws OutOfStockException, ProductNotFoundException, IllegalArgumentException  {
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException();
            }
            if (components.get(id).getAmount() < amount) {
                throw new OutOfStockException();
            } else {
                this.components.get(id).increaseAmount(amount);
            }
        } catch (NullPointerException e) {
            throw new ProductNotFoundException();
        }
    }

    public Map<Integer, Component> getComponents() {
      return components;
    }

    public void resetEntireDatabase() {
        components.clear();
    }

    public void saveToFile(String location) throws IOException{
        Path path = Paths.get(location);
        Gson gson = new Gson();
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                writer.write(gson.toJson(this, Database.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String location) throws IOException {
        Path path = Paths.get(location);
        Gson gson = new Gson();
        instance = new Database();
        try (Scanner scanner = new Scanner(path)) {
         while (scanner.hasNext()) {
             String line = scanner.nextLine();
             instance = gson.fromJson(line, Database.class);
         }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}