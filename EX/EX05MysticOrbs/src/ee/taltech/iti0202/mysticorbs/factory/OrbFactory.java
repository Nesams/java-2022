package ee.taltech.iti0202.mysticorbs.factory;

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

    /**
     *Constructor.
     */
    public OrbFactory(ResourceStorage resourceStorage) {
        this.resourceStorage = resourceStorage;
        this.ovensList = new ArrayList<>();
        this.orbsList = new ArrayList<>();
    }

    /**
     * @param oven
     */
    public void addOven(Oven oven) {
        if (oven.getResourceStorage() == this.resourceStorage) {
            if (!ovensList.contains(oven)) {
                ovensList.add(oven);
            }
        }
    }
    /**
     * @return ovensList
     */
    public List<Oven> getOvens() {
        return ovensList;
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
            o.craftOrb();
            if (!o.craftOrb().equals(Optional.empty())) {
                this.orbsList.add(o.craftOrb().get());
            }
        }
        return this.orbsList.size();
    }

    /**
     * @param cycles
     * @return int.
     */
    public int produceOrbs(int cycles) {
        int i = 0;
        while (i < cycles) {
            for (Oven o: ovensList) {
                o.craftOrb();
                if (!o.craftOrb().equals(Optional.empty())) {
                    this.orbsList.add(o.craftOrb().get());
                }
            }
            i ++;
        }
        return this.orbsList.size();
    }
}
