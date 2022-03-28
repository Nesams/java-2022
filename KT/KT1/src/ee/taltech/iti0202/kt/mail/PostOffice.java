package ee.taltech.iti0202.kt.mail;
import java.util.ArrayList;
import java.util.List;

public class PostOffice {
    private final String location;
    private final ArrayList<Letter> letters;
    private final ArrayList<Postman> postmen;
    final static int forty = 40;

    /**
     * Create a post office with the location.
     */
    public PostOffice(String location) {
        this.location = location;
        this.letters = new ArrayList<>();
        this.postmen = new ArrayList<>();
    }

    /**
     * Adds a letter to the post office.
     * Letter is added only if the letter's destination city matches the location of the office.
     */
    public void addLetter(Letter letter) {
        if (letter.getDestinationCity().equals(location)) {
            this.letters.add(letter);
        }
    }

    /**
     * Adds a postman to the office.
     * If there is a postman with the same first letter already in the office,
     * then this postman is not added.
     * Otherwise postman is added to the office.
     */
    public void addPostman(Postman postman) {
        int count = 0;
        for (Postman postman1: postmen) {
            if (postman1.getName().charAt(0) == postman.getName().charAt(0)) {
                count++;
            }
        }
        if (count == 0) {
            postmen.add(postman);
        }
    }

    /**
     * Returns all the letter in the post office.
     */
    public List<Letter> getAllLetters() {

        return this.letters;
    }

    /**
     * Returns all the postmen in the office.
     */
    public List<Postman> getPostmen() {
        return this.postmen;
    }

    /**
     * Divide letter in the office to postmen.
     * The division algorithm is as follows:
     * Each letter is assigned to each postman.
     * If the postman can take this letter (addLetter method), then this letter
     * is added to the postman and removed from the office.
     * If no postman can take the letter, then this letter remains in the office.
     */
    public void divideLetters() {
        int i = 0;
        for (Postman postman:postmen) {
            if (postman.addLetter(letters.get(i))) {
                letters.remove(letters.get(i));
            }
            i++;
        }
    }

    public static void main(String[] args) {
        PostOffice postOffice = new PostOffice("Tallinn");

        Postman postman = new Postman("Martin", forty);

        postOffice.addPostman(postman);

        postOffice.addLetter(new Letter("Toomas", "Tartu", "Rahu tn"));
        postOffice.addLetter(new Letter("Erki", "Tallinn", "Männi tee"));

        postOffice.divideLetters();

        System.out.println(postman.getLetters());   // [City: Tallinn, Address: Männi tee, Recipient: Erki]
    }
}
