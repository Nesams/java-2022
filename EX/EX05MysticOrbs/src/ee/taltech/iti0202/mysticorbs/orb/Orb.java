package ee.taltech.iti0202.mysticorbs.orb;

import java.util.Locale;

public class Orb {
    public String creator;
    public int energy;

    /**
     * Constructor.
     */
    public Orb(String creator) {
        this.creator = creator;
        this.energy = 0;
    }
    /**
     * @param resource
     * @param amount
     */
    public void charge(String resource, int amount) {
        resource = resource.trim();
        if (!resource.toUpperCase(Locale.ROOT).equals("DUST") && (amount > 0) && !(resource.length() == 0)) {
            this.energy += resource.length() * amount;
        }
    }
    /**
     * @return int.
     */
    public int getEnergy() {
        return this.energy;
    }

    public String toString() {
        return  "Orb by " + this.creator;
    }
}
