package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private String name;
    private int money;
    private List<Book> ownedBooks;
    /**
     * Constructor.
     */
    public Person(String name, int money) {
        this.name = name;
        this.money = money;
        this.ownedBooks = new ArrayList<>();
    }

    public int getMoney() {
        return this.money;
    }

    public String getName() {
        return this.name;
    }
    public List<Book> getBooks() {
        return ownedBooks;
    }
    /**
     * Person buys the book and returns true if the deal is done, false if something went wrong.
     */
    public boolean buyBook(Book book) {
        if (book != null && this.money >= book.getPrice()) {
            book.setOwner(this);
            this.ownedBooks.add(book);
            this.money -= book.getPrice();
            return true;
        }
        return false;
    }
    /**
     * Person sells the book and returns true if the deal is done, false if something went wrong.
     */
    public boolean sellBook(Book book) {
        if (book != null && this.ownedBooks.contains(book)) {
            book.setOwner(null);
            this.ownedBooks.remove(book);
            this.money += book.getPrice();
            return true;
        }
        return false;
    }
}
