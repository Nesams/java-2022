package ee.taltech.iti0202.zoo;

import ee.taltech.iti0202.zoo.animal.Animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zoo {
    private final List<Caretaker> caretakers;
    private final List<Animal> animals;

    public Zoo() {
        this.caretakers = new ArrayList<>();
        this.animals = new ArrayList<>();
    }
    public List<Animal> getAnimals() {
        return animals;
    }
    public void addCaretaker(Caretaker caretaker) {
        this.caretakers.add(caretaker);
    }
    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }
    public List<Animal> getHungryAnimals() {
        List<Animal> hungryAnimals = new ArrayList<>();
        for (Animal animal: this.animals) {
            if (animal.isHungry()) {
                hungryAnimals.add(animal);
            }
        }
        return hungryAnimals;
    }
    public Map<String, String> getCurrentSounds() {
        Map<String, String> currentSounds = new HashMap<>();
        for (Animal animal: this.animals) {
            String animalType = animal.getName() + " (" + animal.getType() + ")";
            String animalSound = animal.getCurrentVoice();
            currentSounds.put(animalType, animalSound);
        }
        return currentSounds;
    }
    public Caretaker theMostEfficientCaretaker() {
        List<Animal> hungryAnimals = getHungryAnimals();
        int cantFeed = 1000;
        Caretaker best = null;
        for (Caretaker caretaker:this.caretakers) {
            if (caretaker.feedAnimals(hungryAnimals).size() < cantFeed) {
                cantFeed = caretaker.feedAnimals(hungryAnimals).size();
                best = caretaker;
            }
        }
        return best;
    }
    public List<Animal> feedAllHungryAnimals() {
        List<Animal> hungryAnimals = getHungryAnimals();
        for (Caretaker caretaker:caretakers) {
            if (!getHungryAnimals().isEmpty()) {
                for (Animal animal:getHungryAnimals()) {
                    if (caretaker.getKnowsHowToFeed().contains(animal.getType())) {
                        animal.feedAnimal();
                        hungryAnimals.remove(animal);
                        getHungryAnimals().remove(animal);
                    }
                }
            } else {
                break;
            }
        }
        return hungryAnimals;
    }
    public void newDayButton() {
        for (Animal animal:this.animals) {
            animal.setTilHungry();
        }
    }
}
