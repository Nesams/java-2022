package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixExceptions;
import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class MagicOven extends Oven implements Fixable{
    private int timesFixed;
    private boolean canBeFixed;

    /**
     * Constructor.
     */
    public MagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        this.name = name;
        this.resourceStorage = resourceStorage;
        this.createdOrbs = 0;
        this.timesFixed = 0;
        this.canBeFixed = false;
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
            if (this.resourceStorage.hasEnoughResource("GOLD", 1)
                    && this.resourceStorage.hasEnoughResource("DUST", 3)) {
                this.resourceStorage.takeResource("DUST", 1);
                this.resourceStorage.takeResource("DUST", 3);
                if (this.createdOrbs % 2 == 0 && this.createdOrbs != 0) {
                    MagicOrb mOrb = new MagicOrb(getName());
                    mOrb.charge("GOLD", 1);
                    mOrb.charge("DUST", 3);
                    this.createdOrbs += 1;
                    return Optional.of(mOrb);
                } else {
                    Orb orb = new Orb(getName());
                    orb.charge("GOLD", 1);
                    orb.charge("DUST", 3);
                    this.createdOrbs += 1;
                    return Optional.of(orb);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void fix() throws CannotFixExceptions {
        if (!isBroken()) {
            throw new CannotFixExceptions(this, CannotFixExceptions.Reason.IS_NOT_BROKEN);
        }
        if (this.timesFixed >= 10) {
            this.canBeFixed = false;
            throw new CannotFixExceptions(this, CannotFixExceptions.Reason.FIXED_MAXIMUM_TIMES);
        }
        if (!(resourceStorage.hasEnoughResource("CLAY", 25 + this.timesFixed * 25))
                && resourceStorage.hasEnoughResource("FREEZING POWDER", 100 + timesFixed * 25)) {
            throw new CannotFixExceptions(this, CannotFixExceptions.Reason.NOT_ENOUGH_RESOURCES);
        }
        if ((resourceStorage.hasEnoughResource("CLAY", 25 + this.timesFixed * 25))
                && resourceStorage.hasEnoughResource("FREEZING POWDER", 100 + timesFixed * 25)) {
            resourceStorage.takeResource("CLAY", 25 + this.timesFixed * 25);
            resourceStorage.takeResource("FREEZING POWDER", 100 + timesFixed * 25);
            this.timesFixed++;
            this.createdOrbs = 0;
            this.canBeFixed = true;
        }
    }

    @Override
    public int getTimesFixed() {
        return this.timesFixed;
    }
}
