package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixExceptions;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.orb.SpaceOrb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Locale;
import java.util.Optional;

public class SpaceOven extends Oven implements Fixable {
    private boolean alwaysFixed;
    private boolean canBeFixed;
    private int timesFixed;
    final int twentyFive = 25;
    final int forty = 40;
    final int fifteen = 15;
    final int ten = 10;
    final int five = 5;
    /**
     * Constructor .
     */
    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        this.name = name;
        this.resourceStorage = resourceStorage;
        this.createdOrbs = 0;
        this.timesFixed = 0;
        this.alwaysFixed = false;
        this.canBeFixed = false;
    }

    /**
     * @return
     */
    public int getCreatedOrbs() {
        return this.createdOrbs;
    }
    /**
     * @return boolean.
     */
    @Override
    public boolean isBroken() {
        if (this.alwaysFixed) {
            return false;
        } else {
            return this.createdOrbs == twentyFive;
        }
    }
    /**
     * @return optional.
     */
    @Override
    public Optional<Orb> craftOrb() {
        if (!isBroken()) {
            if (this.resourceStorage.hasEnoughResource("meteorite stone".toUpperCase(Locale.ROOT), 1)
                    && this.resourceStorage.hasEnoughResource("star fragment".toUpperCase(Locale.ROOT), fifteen)) {
                this.resourceStorage.takeResource("meteorite stone".toUpperCase(Locale.ROOT), 1);
                this.resourceStorage.takeResource("star fragment".toUpperCase(Locale.ROOT), fifteen);
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
        if (!isBroken() || getTimesFixed() >= five) {
            throw new CannotFixExceptions(this, CannotFixExceptions.Reason.IS_NOT_BROKEN);
        } else if (isBroken() && !getResourceStorage().hasEnoughResource("LIQUID SILVER", forty)
                && !getResourceStorage().hasEnoughResource("STAR ESSENCE", ten)) {
            throw new CannotFixExceptions(this, CannotFixExceptions.Reason.NOT_ENOUGH_RESOURCES);
        } else if (resourceStorage.hasEnoughResource("LIQUID SILVER", forty)) {
            resourceStorage.takeResource("LIQUID SILVER", forty);
            this.timesFixed++;
            this.createdOrbs = 0;
        } else if (resourceStorage.hasEnoughResource("STAR ESSENCE", ten)) {
            resourceStorage.takeResource("STAR ESSENCE", ten);
            this.timesFixed++;
            this.createdOrbs = 0;
        }
        if (getTimesFixed() == five) {
            this.alwaysFixed = true;
        }
    }

    /**
     * @return int.
     */
    @Override
    public int getTimesFixed() {
        return this.timesFixed;
    }
}
