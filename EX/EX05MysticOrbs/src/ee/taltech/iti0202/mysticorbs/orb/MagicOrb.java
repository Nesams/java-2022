package ee.taltech.iti0202.mysticorbs.orb;

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
        if (!resource.equals("dust") && resource.contains(" ")) {
            this.energy += resource.length() * amount;
        }
    }
    /**
     * @return string.
     */
    public String toString() {
        return "MagicOrb by " + creator;
    }
}
