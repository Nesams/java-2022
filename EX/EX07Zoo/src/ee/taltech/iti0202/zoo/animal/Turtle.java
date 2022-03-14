package ee.taltech.iti0202.zoo.animal;

public class Turtle extends Animal {
    public Turtle(Type type, String name, String voice, int tilHungry) {
        super(type, name, voice, tilHungry);
        this.type = Type.AMPHIBIAN;
        this.voice = "";
        this.currentVoice = this.voice;
    }
}
