package ee.taltech.iti0202.lotr;

public class Person {
    private final String race;
    private final String name;
    private Ring ring;

    public Person(String race, String name, Ring ring) {
        this.race = race;
        this.name = name;
        this.ring = ring;
    }
    public Person(String race, String name) {
        this.race = race;
        this.name = name;
    }
    public void setRing(Ring ring) {
        this.ring = ring;
    }
    public String getRace() {
        return this.race;
    }
    public String getName() {
        return this.name;
    }
    public Ring getRing() {
        return this.ring;
    }
    public String isSauron() {
        if (this.name.equals("Sauron") && this.ring.getType().equals(Ring.Type.THE_ONE) &&
                this.ring.getMaterial().equals(Ring.Material.GOLD)) {
            return  "Affirmative";
        }
        if (this.name.equals("Sauron") && this.ring.getType().equals(Ring.Type.THE_ONE) &&
                !this.ring.getMaterial().equals(Ring.Material.GOLD)) {
            return "No, the ring is fake!";
        }
        if (!this.name.equals("Sauron") && this.ring.getType().equals(Ring.Type.THE_ONE) &&
                this.ring.getMaterial().equals(Ring.Material.GOLD)) {
            return "No, he just stole the ring";
        }
        if (this.name.equals("Sauron") && this.ring == null || !this.ring.getType().equals(Ring.Type.THE_ONE)) {
            return "No, but he's claiming to be";
        } else {
            return "No";
        }
    }
}