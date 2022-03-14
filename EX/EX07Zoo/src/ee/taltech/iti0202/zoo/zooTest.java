package ee.taltech.iti0202.zoo;

import ee.taltech.iti0202.zoo.animal.Animal;
import ee.taltech.iti0202.zoo.animal.Lamb;
import ee.taltech.iti0202.zoo.animal.Monkey;
import ee.taltech.iti0202.zoo.animal.Turtle;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ZooTest {
    Zoo zoo1 = new Zoo();

    Animal lamb1 = new Lamb(Animal.Type.MAMMAL, "Lemmi", "Maa", 4);
    Animal monkey = new Monkey(Animal.Type.MAMMAL, "Tripp", "juhu", 3);
    Animal turtle = new Turtle(Animal.Type.AMPHIBIAN, "Turt", "shh", 3);
    Animal animal2 = new Animal(Animal.Type.BIRD, "Leo", "Piu", 2);
    Animal animal3 = new Animal(Animal.Type.FISH, "Grill", "KuluKulu", 3);

    Caretaker caretaker1 = new Caretaker("Peeter", List.of(Animal.Type.BIRD, Animal.Type.FISH));
    Caretaker caretaker2 = new Caretaker("Liina", List.of(Animal.Type.MAMMAL, Animal.Type.AMPHIBIAN));


    @org.junit.jupiter.api.Test
    void testAnimals() {
        Assertions.assertEquals(lamb1.getName(), "Lemmi");
        assertFalse(lamb1.isHungry());
        Assertions.assertEquals(animal2.getCurrentVoice(), "Piu");
        Assertions.assertEquals(turtle.getCurrentVoice(), "");
    }
    @org.junit.jupiter.api.Test
    void testZooGetAnimals() {
        zoo1.addAnimal(lamb1);
        zoo1.addAnimal(animal2);
        zoo1.addAnimal(animal3);

        Assertions.assertEquals(zoo1.getAnimals().size(), 3);
    }

    @org.junit.jupiter.api.Test
    void testCaretakers() {
        Assertions.assertEquals(caretaker1.getName(), "Peeter");
        Assertions.assertEquals(caretaker1.getKnowsHowToFeed(), List.of(Animal.Type.BIRD, Animal.Type.FISH));
    }
    @org.junit.jupiter.api.Test
    void getHungryAnimals() {
        zoo1.addAnimal(lamb1);
        zoo1.addAnimal(monkey);
        zoo1.addAnimal(animal2);
        zoo1.newDayButton();
        zoo1.newDayButton();
        Assertions.assertEquals(zoo1.getHungryAnimals().size(), 1);
        zoo1.newDayButton();
        Assertions.assertEquals(zoo1.getHungryAnimals().size(), 2);
    }

    @org.junit.jupiter.api.Test
    void getCurrentSounds() {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("Lemmi (MAMMAL)", "Mää");
        zoo1.addAnimal(lamb1);
        Assertions.assertEquals(zoo1.getCurrentSounds(), testMap);
    }

    @org.junit.jupiter.api.Test
    void theMostEfficientCaretaker() {
        zoo1.addAnimal(turtle);
        zoo1.addAnimal(monkey);
        zoo1.addAnimal(animal2);
        zoo1.newDayButton();
        zoo1.newDayButton();
        zoo1.newDayButton();
        zoo1.addCaretaker(caretaker1);
        zoo1.addCaretaker(caretaker2);
        Assertions.assertEquals(zoo1.theMostEfficientCaretaker(), caretaker1);
    }

    @org.junit.jupiter.api.Test
    void feedAllHungryAnimals() {
        zoo1.addAnimal(turtle);
        zoo1.addAnimal(monkey);
        zoo1.addAnimal(animal3);
        zoo1.addCaretaker(caretaker1);
        zoo1.addCaretaker(caretaker2);
        zoo1.newDayButton();
        zoo1.newDayButton();
        zoo1.newDayButton();
        zoo1.feedAllHungryAnimals();
        Assertions.assertEquals(zoo1.getHungryAnimals().size(), 0);
    }
}
