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
        this.createdOrbs = 0;
    }
    /**
     * @return boolean.
     */
    @Override
    public boolean isBroken() {
        return false;
    }

    /**
     * @return optional.
     */
    public Optional<Orb> craftOrb() {
        if (!isBroken()) {
            if (this.resourceStorage.hasEnoughResource("gold", 1)
                    && this.resourceStorage.hasEnoughResource("dust", 3)) {
                this.resourceStorage.takeResource("gold", 1);
                this.resourceStorage.takeResource("dust", 1);
                this.createdOrbs += 1;
                return Optional.of(new Orb(this.name));
            }
        }
        return Optional.empty();
    }
}
