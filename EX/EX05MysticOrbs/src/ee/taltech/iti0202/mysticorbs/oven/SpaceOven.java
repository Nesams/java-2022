package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.orb.SpaceOrb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class SpaceOven extends Oven{
    /**
     * Constructor.
     */
    public SpaceOven(String name, ResourceStorage resourceStorage) {
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
        return this.createdOrbs == 25;
    }
    /**
     * @return optional.
     */
    @Override
    public Optional<Orb> craftOrb() {
        if (!isBroken()) {
            if (this.resourceStorage.hasEnoughResource("meteorite stone", 1)
                    && this.resourceStorage.hasEnoughResource("star fragment", 15)) {
                this.resourceStorage.takeResource("meteorite stone", 1);
                this.resourceStorage.takeResource("star fragment", 15);
                this.createdOrbs += 1;
                return Optional.of(new SpaceOrb(this.name));
            } else {
                if (this.resourceStorage.hasEnoughResource("pearl", 1)
                        && this.resourceStorage.hasEnoughResource("silver", 1)) {
                    this.resourceStorage.takeResource("pearl", 1);
                    this.resourceStorage.takeResource("silver", 1);
                    this.createdOrbs += 1;
                    return Optional.of(new Orb(this.name));
                }
            }
        }
        if (this.resourceStorage.hasEnoughResource("pearl", 1)
                && this.resourceStorage.hasEnoughResource("silver", 1)) {
            this.resourceStorage.takeResource("pearl", 1);
            this.resourceStorage.takeResource("silver", 1);
            this.createdOrbs += 1;
            return Optional.of(new Orb(this.name));
        }
        return Optional.empty();
    }
}
