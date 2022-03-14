package ee.taltech.iti0202.zoo.animal;

public class Lamb extends Animal {
    public Lamb(Type type, String name, String voice, int tilHungry) {
        super(type, name, voice, tilHungry);
        this.hungry = false;
        this.type = Type.MAMMAL;
        this.voice = "Mää";
        this.currentVoice = this.voice;
    }
    @Override
    public void setTilHungry() {
    }
}
