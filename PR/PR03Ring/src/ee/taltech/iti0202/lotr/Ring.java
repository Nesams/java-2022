package ee.taltech.iti0202.lotr;


public class Ring {
    private final Type ringType;
    private final Material ringMaterial;


    enum Type {
        THE_ONE, GOLDEN, NENYA, OTHER
    }
    enum Material {
        GOLD, SILVER, BRONZE, PLASTIC, DIAMOND
    }
    public Ring(Type type, Material material) {
        this.ringType = type;
        this.ringMaterial = material;
    }
    public Type getType() {
        return this.ringType;
    }
    public Material getMaterial() {
        return this.ringMaterial;
    }
    public static void main(String[] args) {

// LOTR simplified play through
        Ring theRing = new Ring(Type.THE_ONE, Material.GOLD);
        Person sauron = new Person("Maiar", "Sauron");
        sauron.setRing(theRing);
// after some 4000 years, Gollum got the ring
        Person gollum = new Person("Hobbit", "Gollum");
// let's remove ring from Sauron
        sauron.setRing(null);
        gollum.setRing(theRing);
// after about 500 years, Bilbo got the ring
        Person bilbo = new Person("Hobbit", "Bilbo Baggins");
        gollum.setRing(null);
        bilbo.setRing(theRing);
// after 60 years, Frodo got the ring
        Person frodo = new Person("Hobbit", "Frodo Baggins");
        bilbo.setRing(null);
        frodo.setRing(theRing);
// check Sauron
        Ring fakeOne = new Ring(Type.THE_ONE, Material.PLASTIC);
        sauron.setRing(fakeOne);
        System.out.println(sauron.isSauron()); // No, the ring is fake!
        System.out.println(frodo.isSauron()); // No, he just stole the ring
        Ring nenya = new Ring(Type.NENYA, Material.DIAMOND);
        sauron.setRing(nenya);
        System.out.println(sauron.isSauron()); // No, but he's claiming to be
        frodo.setRing(nenya);
        System.out.println(frodo.isSauron()); // No
        sauron.setRing(theRing);
        System.out.println(sauron.isSauron()); // Affirmative
    }
}
