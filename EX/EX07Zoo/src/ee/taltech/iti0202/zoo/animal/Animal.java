package ee.taltech.iti0202.zoo.animal;

public class Animal {
    private final String name;
    Type type;
    String hungryVoice;
    String currentVoice;
    boolean hungry;
    int tilHungry;
    String voice;

    public enum Type {
        MAMMAL, BIRD, FISH, AMPHIBIAN
    }
    public Animal(Type type, String name, String voice, int tilHungry) {
        this.type = type;
        this.name = name;
        this.voice = voice;
        this.currentVoice = this.voice;
        this.hungryVoice = "";
        this.tilHungry = tilHungry;
        this.hungry = false;
    }

    public Type getType() {
        return type;
    }

    public boolean isHungry() {
        return hungry;
    }

    public String getName() {
        return name;
    }

    public String getVoice() {
        return voice;
    }

    public String getCurrentVoice() {
        return currentVoice;
    }

    public void setTilHungry() {
        if (this.tilHungry > 0) {
            this.tilHungry--;
        }
        if (this.tilHungry == 0) {
            this.hungry = true;
            this.currentVoice = this.hungryVoice;
        }
    }
    public boolean feedAnimal() {
        if (this.isHungry()) {
            this.hungry = false;
            this.currentVoice = this.voice;
            return true;
        }
        return false;
    }
}
