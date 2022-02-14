package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;

public class Person {

    private String name;
    private int money;
    private ArrayList<Book> ownedBooks;

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

    public boolean buyBook(Book book) {
        if (book.buy(this)) {
            book.setOwner(this);
            this.ownedBooks.add(book);
            this.money -= book.getPrice();
            return true;
        }
        return false;
    }

    public boolean sellBook(Book book) {
        if (book != null && this.ownedBooks.contains(book)) {
            book.setOwner(this);
            this.ownedBooks.remove(book);
            this.money += book.getPrice();
            return true;
        }
        return false;
    }
}