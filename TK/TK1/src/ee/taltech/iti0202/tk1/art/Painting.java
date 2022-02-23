package ee.taltech.iti0202.tk1.art;

public class Painting {
    private String title;
    private String author;
    /**
     * Constructor.
     */
    public Painting(String title, String author) {
        this.title = title;
        this.author = author;
    }
    /**
     * Constructor.
     */
    public Painting(String title) {
        this.title = title;
        this.author = null;
    }
    /**
     * Setter.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Setter.
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    /**
     * Getter.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Getter.
     */
    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        if (author != null) {
            return  "This is a painting named " + title + " and made by " + author + ".";
        }
        return  "This is a painting named " + title + " and made by an unknown artist.";
    }
}
