package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class InfinityMagicOven extends MagicOven {
    /**
     * @param name
     * @param resourceStorage
     */
    public InfinityMagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        this.name = name;
        this.resourceStorage = resourceStorage;
    }
    /**
     * @return boolean.
     */
    @Override
    public boolean isBroken() {
        return false;
    }
}
