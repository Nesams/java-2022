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
    public Type getType(){
        return this.ringType;
    }
    public Material getMaterial(){
        return this.ringMaterial;
    }

}