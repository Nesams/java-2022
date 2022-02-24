package ee.taltech.iti0202.mysticorbs.orb;

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
        if (!resource.equals("dust") && resource.contains(" ")) {
            this.energy += resource.length() * amount;
        }
    }
    /**
     * @return int.
     */
    public int getEnergy() {
        return this.energy;
    }

    /**
     * @return string.
     */
    public String toString() {
        return  "Orb by " + this.creator;
    }
}
