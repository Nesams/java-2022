package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixExceptions;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.orb.SpaceOrb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Locale;
import java.util.Optional;

public class SpaceOven extends Oven implements Fixable{
    private boolean alwaysFixed;
    private int timesFixed;
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
        if (alwaysFixed) {
            return true;
        } else {
            return this.createdOrbs == 25;
        }
    }
    /**
     * @return optional.
     */
    @Override
    public Optional<Orb> craftOrb() {
        if (!isBroken()) {
            if (this.resourceStorage.hasEnoughResource("meteorite stone".toUpperCase(Locale.ROOT), 1)
                    && this.resourceStorage.hasEnoughResource("star fragment".toUpperCase(Locale.ROOT), 15)) {
                this.resourceStorage.takeResource("meteorite stone".toUpperCase(Locale.ROOT), 1);
                this.resourceStorage.takeResource("star fragment".toUpperCase(Locale.ROOT), 15);
                this.createdOrbs += 1;
                return Optional.of(new SpaceOrb(this.name));
            } else {
                if (this.resourceStorage.hasEnoughResource("PEARL", 1)
                        && this.resourceStorage.hasEnoughResource("SILVER", 1)) {
                    this.resourceStorage.takeResource("PEARL", 1);
                    this.resourceStorage.takeResource("SILVER", 1);
                    this.createdOrbs += 1;
                    return Optional.of(new Orb(this.name));
                }
            }
        }
        if (this.resourceStorage.hasEnoughResource("PEARL", 1)
                && this.resourceStorage.hasEnoughResource("SILVER", 1)) {
            this.resourceStorage.takeResource("PEARL", 1);
            this.resourceStorage.takeResource("SILVER", 1);
            this.createdOrbs += 1;
            return Optional.of(new Orb(this.name));
        }
        return Optional.empty();
    }

    /**
     * @throws CannotFixExceptions
     */
    @Override
    public void fix() throws CannotFixExceptions {
        if (!isBroken() || getTimesFixed() >= 5) {
            throw new CannotFixExceptions(this, CannotFixExceptions.Reason.IS_NOT_BROKEN);
        } else if (isBroken() && !getResourceStorage().hasEnoughResource("LIQUID SILVER", 40)
                && !getResourceStorage().hasEnoughResource("STAR ESSENCE", 10)) {
            throw new CannotFixExceptions(this, CannotFixExceptions.Reason.NOT_ENOUGH_RESOURCES);
        } else if (resourceStorage.hasEnoughResource("LIQUID SILVER", 40)) {
            resourceStorage.takeResource("LIQUID SILVER", 40);
            this.timesFixed++;
            this.createdOrbs = 0;
        } else if (resourceStorage.hasEnoughResource("STAR ESSENCE", 10)) {
            resourceStorage.takeResource("STAR ESSENCE", 10);
            this.timesFixed++;
            this.createdOrbs = 0;
        }
        if (getTimesFixed() == 5) {
            this.alwaysFixed = true;
        }
    }

    /**
     * @return int.
     */
    @Override
    public int getTimesFixed() {
        return timesFixed;
    }
}
