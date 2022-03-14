package ee.taltech.iti0202.zoo;

import ee.taltech.iti0202.zoo.animal.Animal;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    private String name;
    private List<Animal.Type> knowsHowToFeed;

    public Caretaker(String name, List<Animal.Type> knowsHowToFeed) {
        this.name = name;
        this.knowsHowToFeed = knowsHowToFeed;
    }

    public String getName() {
        return name;
    }

    public List<Animal.Type> getKnowsHowToFeed() {
        return knowsHowToFeed;
    }

    public List<Animal> feedAnimals(List<Animal> hungryAnimals) {
        for (Animal animal:hungryAnimals) {
            if (this.knowsHowToFeed.contains(animal.getType())) {
                animal.feedAnimal();
                if (animal.feedAnimal()) {
                    hungryAnimals.remove(animal);
                }
            }
        }
        return hungryAnimals;
    }
}
