package ee.taltech.iti0202.mysticorbs.storage;

import java.util.HashMap;
import java.util.Locale;

public class ResourceStorage {
    private HashMap<String, Integer> resourceMap = new HashMap<>();

    /**
     * @return boolean.
     */
    public boolean isEmpty() {
        if (resourceMap.isEmpty()) {
            return true;
        }
        return false;
    }
    /**
     * @param resource
     * @param amount
     */
    public void addResource(String resource, int amount) {
        resource = resource.toUpperCase(Locale.ROOT);
        resourceMap.put(resource, resourceMap.getOrDefault(resource, 0) + amount);
    }
    /**
     * @param resource
     * @return int.
     */
    public int getResourceAmount(String resource) {
        resource = resource.toUpperCase(Locale.ROOT);
        return resourceMap.get(resource);
    }
    /**
     * @param resource
     * @param amount
     * @return boolean.
     */
    public boolean hasEnoughResource(String resource, int amount) {
        resource = resource.toUpperCase(Locale.ROOT);
        if (amount >= 1) {
            return resourceMap.get(resource) >= amount;
        }
        return false;
    }
    /**
     * @param resource
     * @param amount
     * @return boolean.
     */
    public boolean takeResource(String resource, int amount) {
        resource = resource.toUpperCase(Locale.ROOT);
        if (hasEnoughResource(resource, amount)) {
            int newAmount = resourceMap.get(resource) - amount;
            resourceMap.replace(resource, newAmount);
            return true;
        }
        return false;
    }
}
