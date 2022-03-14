package ee.taltech.iti0202.zoo.animal;

public class Monkey extends Animal {
    public Monkey(Type type, String name, String voice, int tilHungry) {
        super(type, name, voice, tilHungry);
        this.currentVoice = this.getVoice();
        this.hungryVoice  = "BANANA";
        this.type = Type.MAMMAL;
    }
}
