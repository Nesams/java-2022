package ee.taltech.iti0202.mysticorbs.orb;

import java.util.Locale;

public class MagicOrb extends Orb {
    /**
     * Constructor.
     */
    public MagicOrb(String creator) {
        super(creator);
        this.creator = creator;
        this.energy = 0;
    }
    /**
     * @param resource
     * @param amount
     */
    @Override
    public void charge(String resource, int amount) {
        resource = resource.trim();
        if (!resource.toUpperCase(Locale.ROOT).equals("DUST") && (amount > 0) && (resource.length() != 0)) {
            this.energy += resource.length() * amount;
        }
    }
    /**
     * @return string.
     */
    public String toString() {
        return "MagicOrb by " + this.creator;
    }
}
