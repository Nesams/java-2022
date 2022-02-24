package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class MagicOven extends Oven {
    /**
     * Constructor.
     */
    public MagicOven(String name, ResourceStorage resourceStorage) {
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
        return createdOrbs == 5;
    }
    /**
     * @return optional.
     */
    @Override
    public Optional<Orb> craftOrb() {
        if (!isBroken()) {
            if (this.resourceStorage.hasEnoughResource("gold", 1)
                    && this.resourceStorage.hasEnoughResource("dust", 3)) {
                this.resourceStorage.takeResource("gold", 1);
                this.resourceStorage.takeResource("dust", 3);
                if (this.createdOrbs % 2 == 0) {
                    new Orb(this.name);
                } else {
                    new MagicOrb(this.name);
                }
                this.createdOrbs += 1;
            }
        }
        return Optional.empty();
    }
}
