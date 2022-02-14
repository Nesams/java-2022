package ee.taltech.iti0202.bookshelf;

import java.util.*;
import java.util.HashMap;

public class Book {

    private final int id;
    private Person owner;
    private final String author;
    private final int yearOfPublishing;
    private final int price;
    private final String title;
    private static int booksIdcount;
    private static ArrayList<Book> books = new ArrayList<>();
    private static Book lastAddedBook;
    static Map<String, List<Book>> AuthorMap = new HashMap<>();

    /**
     * Adds to the Id value.
     */
    public static int getAndIncrementNextId() {
        int followingId = booksIdcount;
        booksIdcount++;
        return followingId;
    }

    /**
     * Goes Constructor.
     */
    public Book(String title, String author, int yearOfPublishing, int price) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
        this.price = price;
        this.owner = null;
        this.id = getAndIncrementNextId();
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getYearOfPublishing() {
        return this.yearOfPublishing;
    }

    public Person getOwner() {
        return this.owner;
    }

    public int getPrice() {
        return this.price;
    }

    public int getId() {
        return this.id;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    /**
     * Can buyer buy the book or not.
     *
     * @return bool
     */
    public boolean buy(Person buyer) {
        if (buyer == null && this.owner != null) {
            this.owner.buyBook(this);
            this.owner = null;
            return false;
        }
        if (buyer == this.owner || buyer.getMoney() < this.price) {
            return false;
        }
        if (this.price <= buyer.getMoney() && !(this.owner == buyer)) {
            if (this.owner != null && this.owner.getBooks().contains(this)) {
                this.owner.sellBook(this);
            }
            buyer.buyBook(this);
            return true;
        } else {
            return false;
        }
    }

    public static Book of(String title, String author, int yearOfPublishing, int price) {
        Book newBook = new Book(title, author, yearOfPublishing, price);
        if (!books.contains(newBook)) {
            books.add(newBook);
            lastAddedBook = newBook;
            AuthorMap.put(newBook.author.toUpperCase(Locale.ROOT), new ArrayList<>());
            AuthorMap.get(newBook.author.toUpperCase(Locale.ROOT)).add(newBook);
        }
        return newBook;
    }
    public static Book of(String title, int price) {
        if (!(books == null)){
            if (books.isEmpty()) {
                return null;
            } else {
                String author = lastAddedBook.author;
                int yearOfPublishing = lastAddedBook.yearOfPublishing;
                Book newBook = new Book(title, author, yearOfPublishing, price);
                if (!books.contains(newBook)) {
                    books.add(newBook);
                    lastAddedBook = newBook;
                    AuthorMap.put(newBook.author.toUpperCase(Locale.ROOT), new ArrayList<>());
                    AuthorMap.get(newBook.author.toUpperCase(Locale.ROOT)).add(newBook);
                }
            }
        return null;
        }
        return null;
    }
    public static ArrayList getBooksByOwner(Person owner){
        return owner.getBooks();
    }
    public static boolean removeBook(Book book) {
        if (books.contains(book)) {
            books.remove(book);
            book.owner.sellBook(book);
            return true;
        } else {
            if (book == null) {
                return false;
            }
            return false;
        }
    }
    public static List<Book> getBooksByAuthor(String author) {
        return AuthorMap.get(author.toUpperCase(Locale.ROOT));
    }
}

