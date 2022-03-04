package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;
public class MagicOven extends Oven implements Fixable {
    private int timesFixed;
    private boolean canBeFixed;
    final int twentyFive = 25;
    final int hundred = 100;
    final int ten = 10;

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
    public void fix() throws CannotFixException {
        if (!isBroken()) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        }
        if (this.timesFixed >= ten) {
            this.canBeFixed = false;
            throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
        }
        if (!(resourceStorage.hasEnoughResource("CLAY", twentyFive + this.timesFixed * twentyFive))
                && resourceStorage.hasEnoughResource("FREEZING POWDER", hundred + timesFixed * twentyFive)) {
            throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
        }
        if ((resourceStorage.hasEnoughResource("CLAY", twentyFive + this.timesFixed * twentyFive))
                && resourceStorage.hasEnoughResource("FREEZING POWDER", hundred + timesFixed * twentyFive)) {
            resourceStorage.takeResource("CLAY", twentyFive + this.timesFixed * twentyFive);
            resourceStorage.takeResource("FREEZING POWDER", hundred + timesFixed * twentyFive);
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
