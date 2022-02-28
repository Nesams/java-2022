package ee.taltech.iti0202.mysticorbs.storage;

import java.util.HashMap;
import java.util.Locale;

public class ResourceStorage {
    private HashMap<String, Integer> resourceMap = new HashMap<>();

    /**
     * @return boolean.
     */
    public boolean isEmpty() {
        return resourceMap.isEmpty();
    }
    /**
     * @param resource
     * @param amount
     */
    public void addResource(String resource, int amount) {
        resource = resource.toUpperCase(Locale.ROOT).trim();
        if (amount > 0) {
            resourceMap.put(resource, resourceMap.getOrDefault(resource, 0) + amount);
        }
    }
    /**
     * @param resource
     * @return int.
     */
    public int getResourceAmount(String resource) {
        resource = resource.toUpperCase(Locale.ROOT);
        if (this.resourceMap.containsKey(resource)) {
            return resourceMap.get(resource);
        }
        return 0;
    }
    /**
     * @param resource
     * @param amount
     * @return boolean.
     */
    public boolean hasEnoughResource(String resource, int amount) {
        resource = resource.toUpperCase(Locale.ROOT);
        if (amount >= 1) {
            return getResourceAmount(resource) >= amount;
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
            this.resourceMap.replace(resource, newAmount);
            if (this.resourceMap.get(resource) == 0) {
                this.resourceMap.remove(resource);
            }
            return true;
        }
        return false;
    }
}
