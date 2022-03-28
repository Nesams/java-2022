package ee.taltech.iti0202.kt.mail;
import java.util.ArrayList;
import java.util.List;

public class Postman {
    private final String name;
    private final Integer age;
    private final ArrayList<Letter> letters;
    final static int postmanAgeLimit = 40;

    /**
     * Create a postman with the name and the age.
     */
    public Postman(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.letters = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<Letter> getLetters() {
        return this.letters;
    }

    public int getLettersLimit() {
        int limit = 0;
        if (this.age >= postmanAgeLimit) {
            limit = this.age - this.name.length();
        } else {
            limit = this.age + this.name.length();
        }
        return limit;
    }

    /**
     * Adds a letter to postman.
     * The letter can be added if the name of the postman and the name of the letter's address
     * start with the same symbol.
     * Also, each postman has a letter limit.
     * If the age of the postman is 40 or larger, then the limit of the letters is: age - name length
     * If the age of the postman is below 40, the limit is age + name length.
     * If the first letters do not match or the letter limit is reached, returns false.
     * Otherwise returns true and letter is added to postman.
     */
    public boolean addLetter(Letter letter) {
        int limit = getLettersLimit();
        if (this.name.charAt(0) == letter.getAddress().charAt(0) && this.letters.size() + 1 <= limit) {
            this.letters.add(letter);
            return true;
        }
        return false;
    }
}
