package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class Oven {
    public String name;
    public ResourceStorage resourceStorage;
    public int createdOrbs;

    /**
     * Constructor.
     */
    public Oven(String name, ResourceStorage resourceStorage) {
        this.name = name;
        this.resourceStorage = resourceStorage;
        this.createdOrbs = 0;
    }
    /**
     * @return string.
     */
    public String getName() {
        return this.name;
    }
    /**
     * @return resourceStorage.
     */
    public ResourceStorage getResourceStorage() {
        return this.resourceStorage;
    }
    /**
     * @return createdOrbsAmount.
     */
    public int getCreatedOrbsAmount() {
        return this.createdOrbs;
    }
    /**
     * @return boolean.
     */
    public boolean isBroken() {
        return createdOrbs == 15;
    }
    /**
     * @return optional.
     */
    public Optional<Orb> craftOrb() {
        if (!isBroken()) {
            if (this.resourceStorage.hasEnoughResource("pearl", 1)
                    && this.resourceStorage.hasEnoughResource("silver", 1)) {
                this.resourceStorage.takeResource("pearl", 1);
                this.resourceStorage.takeResource("silver", 1);
                this.createdOrbs += 1;
                return Optional.of(new Orb(this.name));
            }
        }
        return Optional.empty();
    }
}
