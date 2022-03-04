package ee.taltech.iti0202.mysticorbs.orb;

public class SpaceOrb extends Orb{
    /**
     * Constructor.
     */
    public SpaceOrb(String creator) {
        super(creator);
        this.creator = creator;
        this.energy = 100;
    }
    /**
     * @param resource
     * @param amount
     */
    @Override
    public void charge(String resource, int amount) { }
    /**
     * @return string.
     */
    public String toString() {
        return "SpaceOrb by " + this.creator;
    }
    /**
     * @param orb
     * @return boolean.
     */
    public boolean absorb(Orb orb) {
        if (orb.energy < this.energy) {
            this.energy += orb.getEnergy();
            orb.energy = 0;
            return true;
        }
        return false;
    }
}
