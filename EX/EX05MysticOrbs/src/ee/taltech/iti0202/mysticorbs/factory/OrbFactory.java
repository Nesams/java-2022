package ee.taltech.iti0202.mysticorbs.factory;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixExceptions;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.oven.Oven;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrbFactory {
    private ResourceStorage resourceStorage;
    private ArrayList<Oven> ovensList;
    private ArrayList<Orb> orbsList;
    private List<Oven> cannotFixOvens;

    /**
     *Constructor.
     */
    public OrbFactory(ResourceStorage resourceStorage) {
        this.resourceStorage = resourceStorage;
        this.ovensList = new ArrayList<>();
        this.orbsList = new ArrayList<>();
        this.cannotFixOvens = new ArrayList<>();
    }

    /**
     * @param oven
     */
    public void addOven(Oven oven) {
        if (oven.getResourceStorage().equals(this.resourceStorage)) {
            if (!ovensList.contains(oven)) {
                this.ovensList.add(oven);
            }
        }
    }
    /**
     * @return ovensList
     */
    public List<Oven> getOvens() {
        return this.ovensList;
    }
    /**
     * @return producedOrbList and clear it.
     */
    public List<Orb> getAndClearProducedOrbsList() {
        ArrayList<Orb> orbss = new ArrayList<>(this.orbsList);
        this.orbsList.clear();
        return orbss;
    }

    /**
     * @return int.
     */
    public int produceOrbs() {
        for (Oven o: ovensList) {
            if (o.isBroken()) {
                try {
                    o.fix();
                } catch (CannotFixExceptions ex) {
                    if (!o.getCanBeFixed()) {
                        this.cannotFixOvens.add(o);
                    }
                }
            }
            Optional<Orb> newOrb = o.craftOrb();
            newOrb.ifPresent(orb -> this.orbsList.add(orb));
        }
        return this.orbsList.size();
    }

    /**
     * @param cycles
     * @return int.
     */
    public int produceOrbs(int cycles) {
        int i = 0;
        int orbsSum = 0;
        while (i < cycles) {
            orbsSum += produceOrbs();
            i++;
        }
        return orbsSum;
    }
    /**
     * H.
     */
    public List<Oven> getOvensThatCannotBeFixed() {
        return this.cannotFixOvens;
    }
    /**
     * H.
     */
    public void getRidOfOvensThatCannotBeFixed() {
        ArrayList<Oven> canBeFixed = new ArrayList<>();
        for (Oven o: getOvens()) {
            if (o.getCanBeFixed()) {
                canBeFixed.add(o);
            }
        }
        this.ovensList.clear();
        this.ovensList = canBeFixed;
    }
}
