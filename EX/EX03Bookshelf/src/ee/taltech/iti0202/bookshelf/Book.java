package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
    static Map<String, List<Book>> authorMap = new HashMap<>();

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
    public static Book getlastaddedbook() {
        return lastAddedBook;
    }

    /**
     * Can buyer buy the book or not.
     *
     * @return bool
     */
    public boolean buy(Person buyer) {
        if (buyer == null && this.owner != null) {
            this.owner.sellBook(this);
            this.owner = null;
            return false;
        }
        if (buyer == this.owner || buyer.getMoney() < this.price) {
            return false;
        }
        if (this.price <= buyer.getMoney() && !(this.owner == buyer)) {
            if (this.owner != null && this.owner.getBooks().contains(this)) {
                this.owner.sellBook(this);
                buyer.buyBook(this);
            }
            buyer.buyBook(this);
            return true;
        } else {
            return false;
        }
    }
    /**
     * Constructor for new books.
     */
    public static Book of(String title, String author, int yearOfPublishing, int price) {
        Book newBook = new Book(title, author, yearOfPublishing, price);
        if (!books.isEmpty()) {
            for (Book book : books) {
                if (!book.author.equals(newBook.author) && !book.title.equals(newBook.title)
                        && !(book.yearOfPublishing == newBook.yearOfPublishing)) {
                    books.add(newBook);
                    lastAddedBook = newBook;
                    authorMap.putIfAbsent(newBook.author.toUpperCase(Locale.ROOT), new ArrayList<>());
                    authorMap.get(newBook.author.toUpperCase(Locale.ROOT)).add(newBook);
                    return newBook;
                }
                return newBook;
            }
        } else {
            books.add(newBook);
            lastAddedBook = newBook;
            authorMap.putIfAbsent(newBook.author.toUpperCase(Locale.ROOT), new ArrayList<>());
            authorMap.get(newBook.author.toUpperCase(Locale.ROOT)).add(newBook);
            return newBook;
        }
        return newBook;
    }
    /**
     * Makes a new Book object by title and price.
     */
    public static Book of(String title, int price) {
        if (books.isEmpty()) {
            return null;
        } else {
            String author1 = lastAddedBook.getAuthor();
            int yearOfPublishing1 = lastAddedBook.getYearOfPublishing();
            Book newBook = new Book(title, author1, yearOfPublishing1, price);
            for (Book book :books) {
                if (!book.author.equals(newBook.author) && !book.title.equals(newBook.title)
                        && !(book.yearOfPublishing == newBook.yearOfPublishing)) {
                    books.add(newBook);
                    lastAddedBook = newBook;
                    authorMap.putIfAbsent(newBook.author.toUpperCase(Locale.ROOT), new ArrayList<>());
                    authorMap.get(newBook.author.toUpperCase(Locale.ROOT)).add(newBook);
                    return newBook;
                } else {
                    return newBook;
                }
            }
            return newBook;
        }
    }
    /**
     * Returns books by owner.
     */
    public static List<Book> getBooksByOwner(Person owner) {
        return owner.getBooks();
    }
    /**
     * Method that removes book from the book objects list.
     */
    public static boolean removeBook(Book book) {
        if (books.contains(book)) {
            books.remove(book);
            authorMap.get(book.author.toUpperCase(Locale.ROOT)).remove(book);
            if (book.owner != null){
                book.owner.sellBook(book);
            }
            return true;
        } else {
            if (book == null) {
                return false;
            }
            return false;
        }
    }
    /**
     * Returns a list of books from the same author.
     */
    public static List<Book> getBooksByAuthor(String author) {
        return authorMap.get(author.toUpperCase(Locale.ROOT));
    }
}

