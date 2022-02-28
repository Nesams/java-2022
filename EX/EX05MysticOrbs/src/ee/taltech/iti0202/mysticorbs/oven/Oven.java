package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixExceptions;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import javax.naming.CannotProceedException;
import java.util.Optional;

public class Oven {
    private boolean canBeFixed;
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
        this.canBeFixed = true;
    }

    /**
     * @return string.
     */
    public String getName() {
        return this.name;
    }

    /**
     * H.
     */
    public boolean getCanBeFixed() {
        return this.canBeFixed;
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
        if (createdOrbs == 15) {
            this.canBeFixed = false;
            return true;
        }
        return false;
    }

    /**
     * @return optional.
     */
    public Optional<Orb> craftOrb() {
        if (!isBroken()) {
            if (this.resourceStorage.hasEnoughResource("PEARL", 1)
                    && this.resourceStorage.hasEnoughResource("SILVER", 1)) {
                this.resourceStorage.takeResource("PEARL", 1);
                this.resourceStorage.takeResource("SILVER", 1);
                this.createdOrbs += 1;
                Orb orb = new Orb(getName());
                orb.charge("PEARL", 1);
                orb.charge("PEARL", 1);
                return Optional.of(orb);
            }
        }
        return Optional.empty();
    }

    /**
     * H.
     */
    public void fix() throws CannotFixExceptions {
        throw new CannotFixExceptions(this, CannotFixExceptions.Reason.IS_NOT_BROKEN);
    }
}
